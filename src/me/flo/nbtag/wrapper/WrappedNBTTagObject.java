package me.flo.nbtag.wrapper;

import me.flo.nbtag.api.NBTagObject;
import me.flo.nbtag.reflection.NMSReflection;

import java.lang.reflect.Field;

/**
 * Created by Florian on 05.07.16 in me.flo.nbtag.wrapper
 */
public class WrappedNBTTagObject extends AbstractNBTTag implements NBTagObject {

    protected static Class<?> typeClass[] = new Class[] {
            NMSReflection.getNMSClass("NBTTagString"),
            NMSReflection.getNMSClass("NBTTagByte"),
            NMSReflection.getNMSClass("NBTTagShort"),
            NMSReflection.getNMSClass("NBTTagInt"),
            NMSReflection.getNMSClass("NBTTagLong"),
            NMSReflection.getNMSClass("NBTTagDouble"),
            NMSReflection.getNMSClass("NBTTagFloat")
    };

    private final Field dataField;

    public WrappedNBTTagObject(Object handle) {
        super(handle, handle.getClass());
        Field dataField1;
        try {
            dataField1 = getHandleClass().getDeclaredField("data");
        } catch (NoSuchFieldException e) {
            System.out.println("***** CANNOT FIND DATAFIELD IN NBTAGOBJECT ****");
            dataField1 = null;
        }
        dataField = dataField1;
        dataField.setAccessible(true);
    }


    @Override
    public void set(Object value) {
        try {
            dataField.set(getHandle(), value);
        } catch (IllegalAccessException e) {
            System.out.println("***** CANNOT SET IN DATAFIELD ****");
            e.printStackTrace();
        }
    }

    @Override
    public Object asObject() {
        try {
            return dataField.get(getHandle());
        } catch (IllegalAccessException e) {
            System.out.println("***** CANNOT FETCH FROM DATAFIELD ****");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String asString() {
        return (String) asObject();
    }

    @Override
    public Byte asByte() {
        return (byte) asObject();
    }

    @Override
    public Short asShort() {
        return (short) asObject();
    }

    @Override
    public Integer asInteger() {
        return (int) asObject();
    }

    @Override
    public Long asLong() {
        return (long) asObject();
    }

    @Override
    public Double asDouble() {
        return (double) asObject();
    }

    @Override
    public Float asFloat() {
        return (float) asObject();
    }

    @Override
    public <T> T autoCast() {
        return (T) asObject();
    }

    @Override
    public <T> T as(T[] t) {
        return (T) asObject();
    }

}
