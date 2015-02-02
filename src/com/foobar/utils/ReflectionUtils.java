package com.foobar.utils;

import com.sun.javafx.tools.packager.Param;

import java.lang.reflect.*;

/**
 * Created by Moch on 2/1/15.
 */
public class ReflectionUtils {

    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genricType = clazz.getGenericSuperclass();
        if (!(genricType instanceof Param)) {
            return Object.class;
        }

        Type[] parameters = ((ParameterizedType) genricType).getActualTypeArguments();
        if (index >= parameters.length || index < 0) {
            return Object.class;
        }
        if (!(parameters[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) parameters[index];
    }

    @SuppressWarnings("unchecked")
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                // Method 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    public static Field getDeclaredField(Object object, String filedName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                // Field 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    public static void makeAccessible(Field field) {
        if (!(Modifier.isPublic(field.getModifiers()))) {
            field.setAccessible(true);
        }
    }

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes) throws InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (null == method) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }

        method.setAccessible(true);
        try {
            return method.invoke(object, parameterTypes);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (null == field) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T> void setFieldValue(T object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (null == field) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
