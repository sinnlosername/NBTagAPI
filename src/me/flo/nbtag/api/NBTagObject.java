package me.flo.nbtag.api;

/**
 * Created by Florian on 05.07.16 in me.flo.nbtag.api
 */

public interface NBTagObject extends NBTagBase {
    void set(Object value);
    Object asObject();
    String asString();
    Byte asByte();
    Short asShort();
    Integer asInteger();
    Long asLong();
    Double asDouble();
    Float asFloat();
    <T> T autoCast();
    <T> T as(T[] t);
}
