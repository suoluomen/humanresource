package com.shandong.human.resource.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;

/**
 * JavaBean工具类
 * 主要包括：
 * 1.将一个对象里面的字段进行解码处理
 *
 * @author tyee.noprom@qq.com
 * @time 2/12/16 4:17 PM.
 */
public class BeanUtil {

    /**
     * 将一个对象里面的字段进行解码处理
     *
     * @param object object
     * @param <T>    范型
     * @throws Exception
     */
    public static <T> void decodeBean(T object) throws Exception {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        T objCopy = (T) clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
        for (Field field : fields) {
            // 将字符串类型的decode
            if (field.getType().equals(String.class)) {
                String fieldName = field.getName();
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getMethodName = "get" + firstLetter + fieldName.substring(1);
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                Method getMethod = clazz.getMethod(getMethodName);
                Method setMethod = clazz.getMethod(setMethodName, new Class[]{field.getType()});
                String result = (String) getMethod.invoke(objCopy);

                if (!StringUtils.isEmpty(result)) {
                    setMethod.invoke(objCopy, URLDecoder.decode(result, "utf-8"));
                }
            }
        }
    }
}
