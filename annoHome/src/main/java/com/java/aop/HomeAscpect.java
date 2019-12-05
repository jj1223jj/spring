package com.java.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 1201 - 1. -> pom에 \추가
@Component
@Aspect
public class HomeAscpect {
	public static Logger logger = Logger.getLogger(HomeAscpect.class.getName());
	public static final String logMsg = "LogMsg==================";
	
	@Around(value = "within(com.java..*)")
	public Object advice(ProceedingJoinPoint joinPoint)throws Throwable{
		Object obj=null;
		
		try {
			
			logger.info(logMsg + joinPoint.getTarget().getClass().getName() + "\t\t" + joinPoint.getSignature().getName());
			obj=joinPoint.proceed();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
		
		// servlet-context.xml에 가서aop설정
	}
	
	/* annotation 설정 후 servlet.xml에서 component-scan, autoproxy설정 */
}
