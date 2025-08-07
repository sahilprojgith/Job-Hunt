package com.sahil.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceMonitorAspect {
    public static final Logger LOGGER= LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.telusko.spring_boot_rest.service.JobService.*(..))")
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {

        long start=System.currentTimeMillis();
        Object obj= jp.proceed();
        long end=System.currentTimeMillis();

        LOGGER.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
        return obj;
    }

}
