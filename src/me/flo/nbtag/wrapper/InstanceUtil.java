package me.flo.nbtag.wrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Florian on 06.07.16 in me.flo.nbtag.wrapper
 */
public class InstanceUtil {

    public static WrappedNBTTagCompound newCompound() {
        try {
            return new WrappedNBTTagCompound(WrappedNBTTagCompound.typeClass.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return handleError(e);
        }
    }

    public static WrappedNBTTagList newList() {
        try {
            return new WrappedNBTTagList(WrappedNBTTagList.typeClass.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return handleError(e);
        }
    }

    public static WrappedNBTTagObject newObject(Object defaultValue, Class<?> tc) {
        try {
            for (Class<?> typeClas : WrappedNBTTagObject.typeClass) {
                for (Constructor<?> constructor : typeClas.getDeclaredConstructors()) {
                    if (constructor.getParameterTypes()[0].equals(tc)) return new WrappedNBTTagObject(constructor.newInstance(defaultValue));
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return handleError(e);
        }
        throw new IllegalArgumentException("Cannot find current class for new Object! Illegal Typeclass: "+tc.getSimpleName());
    }


    private static <T> T handleError(Exception e) {
        System.err.println("ERROR IN INSTANCE UTIL");
        e.printStackTrace();
        return null;
    }
}
