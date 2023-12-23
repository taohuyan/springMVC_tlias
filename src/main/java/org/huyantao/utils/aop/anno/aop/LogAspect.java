package org.huyantao.utils.aop.anno.aop;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.huyantao.Mapper.OperateLogMapper;
import org.huyantao.pojo.OperateLog;
import org.huyantao.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author:呼延涛
 * @version:1.0
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.huyantao.utils.aop.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String classname = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] operateargs = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(operateargs);

        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        long time =end - begin;

        String returnvalue = JSONObject.toJSONString(result);

        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,classname,methodName,methodParams,returnvalue,time);
        operateLogMapper.insert(operateLog);
        log.info("AOP的操作步骤");

        return result;
    }
}
