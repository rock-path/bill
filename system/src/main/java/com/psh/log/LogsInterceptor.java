package com.psh.log;

import cn.hutool.json.JSONUtil;
import com.psh.entity.BillLogResource;
import com.psh.entity.BillLogs;
import com.psh.hik.common.UserInfo;
import com.psh.hik.domain.BaseResultModel;
import com.psh.hik.util.UserContextHandler;
import com.psh.mapper.BillLogResourceMapper;
import com.psh.mapper.BillLogsMapper;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.psh.log.LogConstant.API;
import static com.psh.log.LogConstant.CH2;

@Component
@Aspect
public class LogsInterceptor {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private BillLogsMapper mapper;


    @Resource
    private LogsUtils logsUtils;

    @Around("execution(public * com.psh.controller..*.*(..))")
    public Object apiAround(ProceedingJoinPoint pPoint) throws Throwable {
        String url = getUrl();
        List list = redisTemplate.opsForHash().values(LogConstant.LOGS);
        //获取redis中的数据
        List<LogResource> li = getList(list);
        LogResource logResource = url(url, li);
        Object object = null;
        Object data = null;
        if (logResource != null) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Map<String, Object> params = getNameAndValue(pPoint);
            String ip = getRequestIP(request);
            //获取参数名称和值
            Object[] args = pPoint.getArgs();
            //是否发生异常
            Boolean enableException = true;
            //获取当前登录用户的用户名
            String recordName = getUsername(request);
            //日志内容
            List<Logs> recordAddList = new ArrayList<>();
            try {
                //前置
                logsUtils.getPro(ip, params, logResource, recordAddList, recordName);
                //返回
                object = pPoint.proceed(args);
                BaseResultModel ba = (BaseResultModel) object;
                data = ba.getData();
            } catch (Exception e) {
                //异常
                enableException = false;
                throw e;
            } finally {
                //后置,发生异常需要删除之前的数据
                logsUtils.getAfter( ip, params, logResource, enableException, recordAddList, data);
                //记录日志
                recordAdd(recordAddList);
            }
            return object;
        }
        return pPoint.proceed();
    }



    private String getUrl() {
        HttpServletRequest request = RequestUtils.getHttpServletRequest();
        String url = request.getRequestURI();
        if (url.indexOf(API) >= 0) {
            url = url.substring(url.indexOf(API));
            return url;
        } else {
            return null;
        }
    }

    private LogResource url(String url, List<LogResource> li) {
        LogResource logResource = null;
        // 将员工对象序列化为Json字符串
        if (url != null) {
            for (int a = 0; a < li.size(); a++) {
                if (url.indexOf(li.get(a).getResource()) >= 0) {
                    logResource = li.get(a);
                    break;
                }
            }
            return logResource;
        }
        return null;
    }

    private List<LogResource> getList(List list) {
        List<LogResource> li = new ArrayList<>();
        for (int a = 0; a < list.size(); a++) {
            String jsonString = JSONUtil.toJsonStr(list.get(a));
            // 将Json字符串反序列化为Java对象
            LogResource logResource = JSONUtil.toBean(jsonString, LogResource.class);
            li.add(logResource);
        }
        return li;
    }


    private void recordAdd(List<Logs> recordAddList) {
        if (recordAddList != null && recordAddList.size() > 0) {
            for (Logs recordAdd : recordAddList) {
                //记录日志
                BillLogs billLogs = new BillLogs();
                BeanUtils.copyProperties(recordAdd, billLogs);
                mapper.insert(billLogs);
            }
        }
    }


    /**
     * 获取请求IP，去除掉虚拟IP
     */
    private static String getRequestIP(HttpServletRequest request) {
        String ip = NetworkUtil.getIpAddress(request).trim();
        if (ip.contains(CH2)) {
            ip = ip.split(CH2)[0].trim();
        }
        return ip;
    }

    /**
     * 获取参数Map集合
     *
     * @param joinPoint
     * @return
     */
    private static Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }

    /**
     * 获取username
     *
     * @param request
     */
    public static String getUsername(HttpServletRequest request) {
        UserInfo currentUser = UserContextHandler.getCurrentUser();
        if (currentUser != null) {
            String userNotesName = currentUser.getUserNotesName();
            if (userNotesName != null && userNotesName.length() > 0) {
                return userNotesName;
            }
        }

        String username = request.getRemoteUser();
        if (StringUtils.isNotBlank(username)) {
            return username;
        }

//        HttpSession session = request.getSession();
//        if (session != null) {
//            Object assertionObj = session.getAttribute("_const_cas_assertion_");
//            if (assertionObj != null) {
//                Assertion assertion = (Assertion) assertionObj;
//                final AttributePrincipal principal = assertion.getPrincipal();
//                if (principal != null && !StringUtils.isEmpty(principal.getName())) {
//                    username = principal.getName();
//                }
//            }
//        }

        return username;
    }


}