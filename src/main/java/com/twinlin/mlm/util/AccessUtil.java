package com.twinlin.mlm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessUtil
{
    public static <T> void setValue (T t, String fieldName, Object value)
    {
        try
        { // 得到所有聲明的成員
            Field field = t.getClass ().getDeclaredField (fieldName);
            if (!field.isAccessible ())
                field.setAccessible (true);
            field.set (t, value);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace ();
        }
    }

    public static Object getService (String serviceName, String methodName)
            throws Exception
    {
        Class<?> clazz = Class.forName (serviceName);
        Method method = clazz.getDeclaredMethod (methodName);
        return method.invoke (clazz);
    }

    public static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat ("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}