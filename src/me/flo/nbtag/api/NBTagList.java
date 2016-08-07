package me.flo.nbtag.api;

import java.util.List;

/**
 * Created by Florian on 05.07.16 in me.flo.nbtag.api
 */
public interface NBTagList extends Iterable, Directaccessable<List<?>>, NBTagBase {
    boolean isList(int i);
    boolean isCompound(int i);
    NBTagCompound getCompound(int i);
    NBTagList getList(int i);
    NBTagObject getObject(int i);
    void remove(int i);
    boolean isEmpty();
    int size();
}
