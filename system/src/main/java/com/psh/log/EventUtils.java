package com.psh.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class EventUtils {


    /**
     * 判断某个字段是否为空或者空字符串
     *
     * @param map      异常字段
     * @param object   类
     * @param fieldSet 需要校验的字段
     */
    private static Map<String, Object> isFiledColumnBlock(Map<String, Object> map, Object object, Set<String> fieldSet) {
        final Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fieldSet.contains(fields[i].getName())) {
                boolean annotationPresent = fields[i].isAnnotationPresent(FiledColumn.class);
                if (annotationPresent) {
                    // 获取注解值
                    String name = fields[i].getAnnotation(FiledColumn.class).value();
                    try {
                        String cloumn = "";
                        if (fields[i].get(object) != null) {
                            cloumn = fields[i].get(object).toString();
                        }
//                        System.out.println(fields[i].getName() + "---------" + name + "----" + cloumn);
                        if (map == null) {
                            map = new HashMap<>();
                        }
                        if (cloumn == null || "".equals(cloumn.trim())) {
                            map.put(fields[i].getName(), name + "为空");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return map;
    }



    /**
     * 获取实体类中除了map集合中的所有属性名称及注解内容
     */
    public static Map<String, String> getFiledColumn(Object instance, Map<String, String> fieldMap) throws NoSuchFieldException {
        Map<String, String> map = new HashMap();
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean b = false;
        for (int i = 0; i < fields.length; i++) {
            // 除过fieldMap中的属性，其他属性都获取
            if (!fieldMap.containsValue(fields[i].getName())) {
//                Field field=clazz.getDeclaredField(fields[i].getName());
                boolean annotationPresent = fields[i].isAnnotationPresent(FiledColumn.class);
                if (annotationPresent) {
                    // 获取注解值
                    String name = fields[i].getAnnotation(FiledColumn.class).value();
                    map.put(fields[i].getName(), name);
                }
            }
        }
        return map;
    }


}