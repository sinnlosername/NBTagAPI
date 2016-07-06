package me.flo.nbtag.wrapper;

import me.flo.nbtag.api.NBTagCompound;
import me.flo.nbtag.api.NBTagList;
import me.flo.nbtag.reflection.NMSReflection;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Florian on 02.07.16 in me.megyssstaa.citadel.util.nbt
 */
public class WrappedNBTTagList extends AbstractNBTTag implements NBTagList {

    protected static final Class<?> typeClass = NMSReflection.getNMSClass("NBTTagList");

    private final List<?> list;

    public WrappedNBTTagList(Object handle) {
        super(handle, typeClass);
        list = (List) NMSReflection.getField(handle, "list");
    }

    @Override
    public boolean isList(int i) {
        return WrappedNBTTagList.typeClass.isInstance(list.get(i));
    }

    @Override
    public boolean isCompound(int i) {
        return WrappedNBTTagCompound.typeClass.isInstance(list.get(i));
    }

    @Override
    public NBTagCompound getCompound(int i) {
        return new WrappedNBTTagCompound(list.get(i));
    }

    @Override
    public NBTagList getList(int i) {
        return new WrappedNBTTagList(list.get(i));
    }

    @Override
    public void remove(int i) {
        list.remove(i);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return list.iterator();
    }

    @Override
    public List<?> directAccess() {
        return list;
    }

}
