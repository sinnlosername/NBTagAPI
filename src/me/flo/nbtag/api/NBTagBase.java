package me.flo.nbtag.api;

import me.flo.nbtag.wrapper.AbstractWrapper;

/**
 * Created by Florian on 05.07.16 in me.flo.nbtag.api
 */
public interface NBTagBase {
    AbstractWrapper getWrapper();
    boolean isList();
    boolean isCompound();
    boolean isObject();
}
