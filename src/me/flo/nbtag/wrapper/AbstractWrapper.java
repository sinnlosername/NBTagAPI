package me.flo.nbtag.wrapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Florian on 02.07.16 in me.megyssstaa.citadel.util.nbt
 */

@EqualsAndHashCode(of = "handle")
@Getter
public class AbstractWrapper {

    private final Object handle;
    private final Class<?> handleClass;

    public AbstractWrapper(Object handle, Class<?> handleClass) {
        if (!handleClass.isInstance(handle)) throw new IllegalArgumentException(handle.getClass().getSimpleName()+ " cannot be handled from wrapper with "+handleClass.getSimpleName());
        this.handle = handle;
        this.handleClass = handleClass;
    }

    @Override
    public String toString() {
        return handle.toString();
    }

}
