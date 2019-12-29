package com.list.customer2.customer2client.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SingleAspect {
    @Pointcut("@annotation(com.list.customer2.customer2client.entity.MyAnnotation)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(JoinPoint joinPoint){
        //返回的值
        Object result = null;
        try {
            //获取方法的参数
            Object[] args = joinPoint.getArgs();
            log.info("前置通知：{}",joinPoint.getClass());
            log.info("参数入参：{}",args[0]);
            result = ((ProceedingJoinPoint)joinPoint).proceed(args);
            log.info("后置通知：{}",joinPoint.getClass());
        } catch (Throwable throwable) {
            //异常通知：回滚事务rollback();e.print
            throwable.printStackTrace();
        }finally {
            //最终通知：释放资源release()
        }
        return result;
    }
}
