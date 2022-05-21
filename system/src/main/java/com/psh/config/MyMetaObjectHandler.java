package com.psh.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.psh.hik.common.UserInfo;
import com.psh.hik.util.UserContextHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author Adams
 * @version v1.0
 * @date 2020/4/28 16:06
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        UserInfo user = UserContextHandler.getCurrentUser();
        if (null == user) {
            user = new UserInfo();
            user.setUserNotesName("System");
        }
        this.setFieldValByName("user", user.getUserNotesName(), metaObject);
        this.setFieldValByName("crname", user.getUserNotesName(), metaObject);
        this.setFieldValByName("chname", user.getUserNotesName(), metaObject);
        this.setFieldValByName("deleted", "0", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取本地化线程中的user
        UserInfo user = UserContextHandler.getCurrentUser();
        if (null == user) {
            user = new UserInfo();
            //设置用户中文名
            user.setUserNotesName("SystemTwo");
        }
        this.setFieldValByName("user", user.getUserNotesName(), metaObject);
        this.setFieldValByName("chname", user.getUserNotesName(), metaObject);
    }
}
