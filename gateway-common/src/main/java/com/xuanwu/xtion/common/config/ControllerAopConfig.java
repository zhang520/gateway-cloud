package com.xuanwu.xtion.common.config;

import com.xuanwu.xtion.common.exception.AppException;
import com.xuanwu.xtion.common.response.RestHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ControllerAopConfig {

    @Around("execution(public * com.xuanwu.xtion.*.rest..*.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (AppException ex) {
            ex.printStackTrace();
            return RestHelper.failure(ex.getErrorCode());
        } catch (Throwable e) {
            e.printStackTrace();
            return RestHelper.failure();
        }
    }

}
