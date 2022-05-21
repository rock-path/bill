package com.psh.log;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

import static com.psh.log.LogConstant.*;


@Component
public class LogsUtils {


    /***
     * 设置操作前的数据
     * @param ip                访问ip
     * @param params            调用接口的参数
     * @param logResource       日志资源信息
     * @param recordAddList     日志内容
     * @param recordName        当前登录人用户名
     */
    public void getPro(String ip, Map<String, Object> params, LogResource logResource, List<Logs> recordAddList, String recordName) {
        //获取修改前的数据，设置number
        switch (logResource.getAction()) {
            case action1:
                selectBefore(ip, recordName, logResource.getId(), logResource.getDescribed(), recordAddList);
                break;
            case action2:
                addBefore();
                break;
            case action3:
                deleteBefore();
                break;
            case action4:
                updateBefore();
                break;
        }

    }


    private Logs buildLogs(String ip, String recordName, Long logId) {
        return Logs.build(ip, recordName, logId);
    }


    private void selectBefore(String ip, String recordName, Long logId, String described, List<Logs> recordAddList) {
        //查询并没有造成数据变化，可后续扩展
        Logs logs = buildLogs(ip, recordName, logId);
        logs.setLogs(described);
        recordAddList.add(logs);
    }

    private void addBefore() {

    }


    private void updateBefore() {
    }

    private void deleteBefore() {
    }


    /***
     * 设置操作后的数据
     * @param ip
     * @param params
     * @param logResource
     * @param enableException
     * @param recordAddList
     * @param data
     */
    public void getAfter(String ip, Map<String, Object> params, LogResource logResource, Boolean enableException, List<Logs> recordAddList, Object data) {
        //这个系统的日志目前没有用到ip，暂且保留
        if (enableException) {
            //操作正常，执行后置操作，根据cla查找对应的日志记录方案
            if (logResource.getCla().indexOf("MeEventReportController") >= 0) {
                getMeEventReportControllerUpdate(logResource, params, recordAddList, data);
            } else if (logResource.getCla().indexOf("----") >= 0) {
                //目前只有上面这一个路径
            }
        } else {
            //如果发生异常会删除之前的记录

        }

    }

    private List<Logs> getMeEventReportControllerUpdate(LogResource log, Map<String, Object> params, List<Logs> recordAddList, Object data) {
        if ("新增".equals(log.getAction())) {

        } else if ("修改".equals(log.getAction())) {
            //获取之前的数据和之后的数据，然后得到日志内容
            StringBuilder sb = new StringBuilder();
//            String ss = getLog(log.getBefore(), log.getAfter(), "新增", sb);
        }
        return recordAddList;
    }


    private String getLog(Object before, Object after, String action, StringBuilder sb) {
        if (before != null) {

        } else {
            sb = filedColumn(after, action, sb);
        }
        return sb.toString();
    }


    /**
     * 将这个类的非空字段拼接为字符串
     *
     * @param object 类
     */
    private static StringBuilder filedColumn(Object object, String action, StringBuilder sb) {
        final Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            boolean annotationPresent = fields[i].isAnnotationPresent(FiledColumn.class);
            if (annotationPresent) {
                // 获取注解值
                String name = fields[i].getAnnotation(FiledColumn.class).value();
                try {
                    //System.out.println(fields[i].getName() + "---------" + name + "----" + fields[i].get(object));
                    if (fields[i].get(object) != null && !"".equals(fields[i].get(object))) {
                        sb.append(action).append("字段\"").append(name).append("\":").append(fields[i].get(object)).append(";");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb;
    }


}

