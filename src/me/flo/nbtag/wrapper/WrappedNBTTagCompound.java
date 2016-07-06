package me.flo.nbtag.wrapper;

import me.flo.nbtag.api.NBTagBase;
import me.flo.nbtag.api.NBTagCompound;
import me.flo.nbtag.api.NBTagList;
import me.flo.nbtag.reflection.NMSReflection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Florian on 02.07.16 in me.megyssstaa.citadel.util.nbt
 */
public class WrappedNBTTagCompound extends AbstractNBTTag implements NBTagCompound {

    protected static final Class<?> typeClass = NMSReflection.getNMSClass("NBTTagCompound");

    private final HashMap<String, Object> map;

    public WrappedNBTTagCompound(Object handle) {
        super(handle, typeClass);
        map = (HashMap) NMSReflection.getField(handle, "map");
    }

    public void add(String key, NBTagBase tag) {
        map.put(key, tag.getWrapper().getHandle());
    }

    @Override
    public boolean isList(String s) {
        return WrappedNBTTagList.typeClass.isInstance(map.get(s));
    }

    @Override
    public boolean isCompound(String s) {
        return WrappedNBTTagCompound.typeClass.isInstance(map.get(s));
    }

    @Override
    public NBTagCompound getCompound(String s) {
        return new WrappedNBTTagCompound(map.get(s));
    }

    @Override
    public boolean containsKey(String s) {
        return map.containsKey(s);
    }

    @Override
    public NBTagList getList(String s) {
        return new WrappedNBTTagList(map.get(s));
    }

    @Override
    public void remove(String s) {
        map.remove(s);
    }

    @Override
    public Set<String> getKeys() {
        return map.keySet();
    }

    @Override
    public Map<String, Object> directAccess() {
        return map;
    }
}
