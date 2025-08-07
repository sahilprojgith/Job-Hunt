package com.sahil.spring_boot_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //return type, fully qualified class name, method name (arguments)
    @Before("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..))") // this is point cut inside ()
    public void logMethodcall(JoinPoint jp){
        LOGGER.info("Method called" + jp.getSignature().getName());
    }
    @After("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method Executed" + jp.getSignature().getName());
    }
    @AfterThrowing("execution (* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodCrashed(JoinPoint jp) {
        LOGGER.info("Method has some issues "+jp.getSignature().getName());
    }



    @AfterReturning("execution (* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.spring_boot_rest.service.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method Executed Successfully "+jp.getSignature().getName());
    }



}
