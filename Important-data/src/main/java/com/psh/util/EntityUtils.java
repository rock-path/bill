package com.psh.util;

import com.psh.entity.BillPassword;
import com.psh.entity.response.ResBillPassword;
import com.psh.entity.vo.BillPasswordEncryption;
import com.psh.hik.common.Constant;
import com.psh.hik.domain.BaseResultModel;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtils {
    public static BaseResultModel check(BillPassword entity) {
        Map map = new HashMap<>();
        if (StringUtils.isBlank(entity.getSoftwareName())) {
            map.put(Constant.SOFTWARENAME, Constant.SOFTWAREFAIL);
        }
        if (StringUtils.isBlank(entity.getAccount())) {
            map.put(Constant.ACCOUNT, Constant.ACCOUNTFAIL);
        }
        if (StringUtils.isBlank(entity.getPassword())) {
            map.put(Constant.PASSWORD, Constant.PASSWORDFAIL);
        }
        if (map.isEmpty()) {
            return null;
        }
        return BaseResultModel.success(map.toString());
    }


    /**
     * 加密
     *
     * @param entity
     */
    public static void encryption(BillPassword entity, String key) {
        Field[] entityFields = BillPassword.class.getDeclaredFields();
        Field[] declaredFields = BillPasswordEncryption.class.getDeclaredFields();
        for (int a = 0; a < entityFields.length; a++) {
            for (int b = 0; b < declaredFields.length; b++) {
                if (entityFields[a].getName().equals(declaredFields[b].getName())) {
                    try {
                        String ss = entityFields[a].get(entity).toString();
                        if (StringUtils.isNotBlank(ss)) {
                            entityFields[a].set(entity, AESUtils.encrypt(ss, key));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 解密
     *
     * @param entity
     */
    public static void decryption(ResBillPassword entity, String key) {
        Field[] entityFields = ResBillPassword.class.getDeclaredFields();
        Field[] declaredFields = BillPasswordEncryption.class.getDeclaredFields();
        for (int a = 0; a < entityFields.length; a++) {
            for (int b = 0; b < declaredFields.length; b++) {
                if (entityFields[a].getName().equals(declaredFields[b].getName())) {
                    try {
                        String ss = entityFields[a].get(entity).toString();
                        if (StringUtils.isNotBlank(ss)) {
                            entityFields[a].set(entity, AESUtils.decrypt(ss, key));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    public static Map<String, String> isNotBlank(Object obj) {
        Map<String, String> map = new HashMap<>();
        Field[] entityFields = obj.getClass().getDeclaredFields();
        for (int a = 0; a < entityFields.length; a++) {
            try {
                if (StringUtils.isNotBlank(entityFields[a].get(obj).toString())) {
                    map.put(entityFields[a].getName(), entityFields[a].get(obj).toString());
                }
            } catch (Exception e) {

            }

        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    public static List<ResBillPassword> serch(List<ResBillPassword> res, Map<String, String> map) {
        if (res == null && res.size() <= 0) {
            return null;
        }
        List<ResBillPassword> list = new ArrayList<>();
        Field[] entityFields = res.get(0).getClass().getDeclaredFields();
        for (ResBillPassword resBillPassword : res) {
            serchField(entityFields, resBillPassword, map, list);
        }

        return list;
    }

    private static void serchField(Field[] entityFields, ResBillPassword resBillPassword, Map<String, String> map, List<ResBillPassword> list) {
        for (int a = 0; a < entityFields.length; a++) {
            if (map.containsKey(entityFields[a].getName())) {
                //需要查询的列
                String ss = null;
                try {
                    ss = entityFields[a].get(resBillPassword).toString();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (accordWith(entityFields[a].getName(), ss, map)) {
                    list.add(resBillPassword);
                    break;
                }
            }
        }

    }


    private static boolean accordWith(String name, String ss, Map<String, String> map) {
        switch (name) {
            case "softwareName":
                return equ(name, ss, map);
            case "account":
                return equ(name, ss, map);
            case "password":
                return equ(name, ss, map);
            case "described":
                return like(name, ss, map);
        }
        return false;
    }

    private static boolean equ(String name, String ss, Map<String, String> map) {
        if (StringUtils.isNotBlank(map.get(name)) && StringUtils.isNotBlank(ss) && map.get(name).equals(ss)) {
            return true;
        }
        return false;
    }

    private static boolean like(String name, String ss, Map<String, String> map) {
        if (StringUtils.isNotBlank(map.get(name)) && StringUtils.isNotBlank(ss) && ss.indexOf(map.get(name)) >= 0) {
            return true;
        }
        return false;
    }

}
