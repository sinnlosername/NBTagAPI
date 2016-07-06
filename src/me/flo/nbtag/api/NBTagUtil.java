package me.flo.nbtag.api;

import me.flo.nbtag.reflection.Handle;
import me.flo.nbtag.wrapper.InstanceUtil;
import me.flo.nbtag.wrapper.WrappedNBTTagCompound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import java.lang.NullPointerException;

import java.lang.reflect.Method;

/**
 * Created by Florian on 06.07.16 in me.flo.nbtag.api
 */
public class NBTagUtil {

    public static NBTagCompound newCompound() {
        return InstanceUtil.newCompound();
    }

    public static NBTagList newList() {
        return InstanceUtil.newList();
    }

    public static NBTagObject newObject(Object value, Class<?> typeClass) {
        return InstanceUtil.newObject(value, typeClass);
    }

    public static NBTagCompound getTag(Entity a) {
        return new WrappedNBTTagCompound(notNull(getTag0(a)));
    }

    public static NBTagCompound getTag(ItemStack a) {
        return new WrappedNBTTagCompound(notNull(getTag0(a)));
    }

    public static boolean hasTag(Entity e) {
        return getTag0(e) != null;
    }

    public static boolean hasTag(ItemStack i) {
        return getTag0(i) != null;
    }

    private static Object getTag0(Entity entity) {
        final Handle<Entity> handle = Handle.fromEntity(entity);
        return getTag(handle.getHandle(), "getNBTTag", handle.getHandleClass());
    }

    private static Object getTag0(ItemStack itemStack) {
        final Handle<ItemStack> handle = Handle.fromItemStack(itemStack);
        return getTag(handle.getHandle(), "getTag",handle.getHandleClass());
    }

    private static Object getTag(Object o, String methodName, Class<?> c) {
        try {
            final Method m = c.getDeclaredMethod(methodName);
            m.setAccessible(true);
            return m.invoke(o);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    private static <T> T handleError(Exception e) {
        System.err.println("ERROR IN NBTAGUTIL");
        e.printStackTrace();
        return null;
    }

    private static Object notNull(Object o) {
        if (o==null) throw new NullPointerException();
        return o;
    }
}
