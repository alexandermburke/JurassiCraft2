package net.reuxertz.ecoapi.util;

import java.lang.reflect.Field;

public class AccessHelper
{
    public static Object getStaticValue(final String className, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException
    {
        // Get the private field
        final Field field = Class.forName(className).getDeclaredField(fieldName);
        // Allow modification on the field
        field.setAccessible(true);
        // Return the Obect corresponding to the field
        return field.get(Class.forName(className));
    }
}
