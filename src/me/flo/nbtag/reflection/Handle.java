package me.flo.nbtag.reflection;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Florian on 01.07.16 in me.megyssstaa.citadel.util.packet.util
 */
@Getter
public class Handle<T> {

    private final T bukkitObject;
    private final Object handle;
    private final Class<?> handleClass;

    public Handle(T bukkitObject, Object handle, Class<?> c) {
        this.bukkitObject = bukkitObject;
        this.handle = handle;
        this.handleClass = c;
    }

    public Handle(T bukkitObject, Object handle) {
        this(bukkitObject, handle, handle.getClass());
    }

    public static Handle<ItemStack> fromItemStack(ItemStack item) {
        try {
            final Class<?> craftItemStackClass = item.getClass();
            final Method asNMSCopyMethod = craftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class);
            final Object nmsItem = asNMSCopyMethod.invoke(null, item);
            return new Handle(item, nmsItem);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return handleError(e);
        }
    }

    public static Handle<Entity> fromEntity(Entity e) {
        try {
            return new Handle(e, e.getClass().getDeclaredMethod("getHandle").invoke(e));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            return handleError(ex);
        }
    }

    public static Handle<Player> fromEntity(Player e) {
        try {
            return new Handle(e, e.getClass().getDeclaredMethod("getHandle").invoke(e));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            return handleError(ex);
        }
    }

    private static <T> T handleError(Exception e) {
        System.err.println("ERROR IN REFLECTION -> Handle");
        e.printStackTrace();
        return null;
    }

}
