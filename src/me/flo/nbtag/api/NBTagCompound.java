package me.flo.nbtag.api;

import me.flo.nbtag.util.Directaccessable;

import java.util.Map;
import java.util.Set;

/**
 * Created by Florian on 05.07.16 in me.flo.nbtag.api
 */
public interface NBTagCompound extends Directaccessable<Map<String, ?>>, NBTagBase {

    void add(String key, NBTagBase tag);

    boolean isList(String s);

    boolean isCompound(String s);

    NBTagCompound getCompound(String s);

    NBTagObject getObject(String s);

    boolean containsKey(String s);

    NBTagList getList(String s);

    void remove(String s);

    Set<String> getKeys();
}
