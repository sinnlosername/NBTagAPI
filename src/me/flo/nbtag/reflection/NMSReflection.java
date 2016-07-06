package me.flo.nbtag.reflection;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Florian on 01.06.16 in me.megyssstaa.citadel.util.packet
 */
public class NMSReflection {

    static final String version = Bukkit.getServer().getClass().getName().split("\\.")[3];
    private static final HashMap<String, Class<?>> cache = new HashMap<>();

    public static Class<?> getNMSClass(String NMSClassName) {
        if (cache.containsKey(NMSClassName)) return cache.get(NMSClassName);
        try {
            final Class<?> c = Class.forName("net.minecraft.server." + version + "." + NMSClassName);
            cache.put(NMSClassName, c);
            return c;
        } catch (Exception e) {
            return handleError(e);
        }
    }

    public static Class<?> getOBSClass(String OBSClassName){
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + OBSClassName);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    public static boolean isPacket(Object o, String packetName) {
        return getNMSClass(packetName).isInstance(o);
    }

    public static void setField(Object o, String name, Object value) {
        setField(o, name, value, o.getClass());
    }

    public static void setField(Object o, String name, Object value, Class<?> clazz) {
        try {
            final Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(o, value);
        } catch (Exception e) {
            handleError(e);
        }
    }

    public static Object getTag(ItemStack item) {
        try {
            final Handle<ItemStack> h = Handle.fromItemStack(item);
            final Object nmsItem = h.getHandle();
            final Method getTagMethod = nmsItem.getClass().getDeclaredMethod("getTag");
            return getTagMethod.invoke(nmsItem);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    public static ItemStack toCraftStack(Object nmsItem) {
        try {
            final Class<?> nmsItemStackClass = getNMSClass("ItemStack");
            final Class<?> craftItemStackClass = getOBSClass("inventory.CraftItemStack");

            final Constructor constructor = craftItemStackClass.getDeclaredConstructor(nmsItemStackClass);
            constructor.setAccessible(true);

            final Object craftItem = constructor.newInstance(nmsItemStackClass.cast(nmsItem));

            return (ItemStack) craftItem;
        } catch (Exception e) {
            return (ItemStack) handleError(e);
        }
    }

    public static Object getTag(Object nmsItem) {
        try {
            final Method getTagMethod = nmsItem.getClass().getDeclaredMethod("getTag");
            return getTagMethod.invoke(nmsItem);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    public static Object getField(Object o, String name) {
        return getField(o, name, o.getClass());
    }

    public static Object getField(Object o, String name, Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(o);
        } catch (Exception e) {
            return handleError(e);
        }

    }

    private static <T> T handleError(Exception e) {
        System.err.println("ERROR IN REFLECTION");
        e.printStackTrace();
        return null;
    }


}
