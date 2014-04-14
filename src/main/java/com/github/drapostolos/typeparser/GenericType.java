package com.github.drapostolos.typeparser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Define a generic type (known in compile time) by sub-classing this class. <br/>
 * Below example defines a {@code List<String>}: <br/>
 * <code>
 *  new {@code GenericType<List<Integer>>} () {};
 * </code><br/>
 * Note the ending "{}".
 * <p/>
 * Use a subclass of this class as an argument to
 * {@link StringToTypeParser#parse(String, GenericType)} when you want to convert a string to a
 * generic type known at compile time.
 * <p/>
 * Additionally, use a subclass of this class when you want to register or unregister a
 * {@link TypeParser} for a generic type, by using these methods:
 * {@link StringToTypeParserBuilder#registerTypeParser(GenericType, TypeParser)},
 * {@link StringToTypeParserBuilder#unregisterTypeParser(GenericType)}.
 * 
 * @param <T> a generic type, example: {@code List<String>}
 * @see <a href="https://github.com/drapostolos/type-parser/wiki/User-Guide">User-Guide</a>
 */
public abstract class GenericType<T> {

    private final Type type;

    public GenericType() {
        if (GenericType.class != getClass().getSuperclass()) {
            String errorMsg = "'%s' must be a direct subclass of '%s'";
            errorMsg = String.format(errorMsg, getClass().getName(), GenericType.class.getName());
            throw new IllegalArgumentException(errorMsg);
        }
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType superClass = (ParameterizedType) t;
            type = superClass.getActualTypeArguments()[0];
        } else {
            String message = "'%s' must be parameterized (for example \"new GenericType<List<Integer>>(){}\"), "
                    + "it can not be of raw type \"new GenericType(){}\".";
            throw new IllegalStateException(String.format(message, getClass().getName()));
        }
    }

    final Type getType() {
        return type;
    }

    @Override
    final public String toString() {
        return type.toString();
    }
}