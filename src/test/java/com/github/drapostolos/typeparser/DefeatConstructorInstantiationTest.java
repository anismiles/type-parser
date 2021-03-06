package com.github.drapostolos.typeparser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class DefeatConstructorInstantiationTest {

    @Test(expected = InvocationTargetException.class)
    public void shouldThrowAssertionErrorWhenInstantiatingUtility() throws Exception {
        throwExceptionWhenInstantiating(Util.class);
    }

    @Test(expected = InvocationTargetException.class)
    public void shouldThrowWhenInstantiatingDefaultStaticParsers() throws Exception {
        throwExceptionWhenInstantiating(DefaultStaticParsers.class);
    }

    @Test(expected = InvocationTargetException.class)
    public void shouldThrowWhenInstantiatingDefaultDynamicParsers() throws Exception {
        throwExceptionWhenInstantiating(DefaultDynamicParsers.class);
    }

    private void throwExceptionWhenInstantiating(Class<?> cls) throws Exception {
        Constructor<?> c = cls.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
    }

}
