package me.flo.nbtag.wrapper;

import me.flo.nbtag.api.NBTagBase;

/**
 * Created by Florian on 02.07.16 in me.megyssstaa.citadel.util.nbt
 */
public class AbstractNBTTag extends AbstractWrapper implements NBTagBase {

    public AbstractNBTTag(Object handle, Class<?> handleClass) {
        super(handle, handleClass);
    }

    @Override
    public boolean isList() {
        return isList(getHandle());
    }

    @Override
    public boolean isCompound() {
        return isCompound(getHandle());
    }

    @Override
    public boolean isObject() {
        return isObject(getHandle());
    }

    @Override
    public AbstractWrapper getWrapper() {
        return this;
    }

    public static boolean isList(Object o) {
        return WrappedNBTTagList.typeClass.isInstance(o);
    }

    public static boolean isCompound(Object o) {
        return WrappedNBTTagCompound.typeClass.isInstance(o);
    }

    public static boolean isObject(Object o) {
        for (Class<?> typeClas : WrappedNBTTagObject.typeClass)
            if (typeClas.isInstance(o)) return true;
        return false;
    }

}
