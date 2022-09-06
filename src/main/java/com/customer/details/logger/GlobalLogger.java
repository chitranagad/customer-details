package com.customer.details.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class GlobalLogger {

	Logger logger = LoggerFactory.getLogger(GlobalLogger.class);

	@Pointcut(value = "execution(* com.customer.details.*.*.*(..) )")
	public void configPointCut() {

	}
    
	@Around("configPointCut()")
	public Object handleGlobalLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		logger.info("Class Name " + className + "  Method Name  " + methodName + "   Method Parameters  "
				+ mapper.writeValueAsString(args));
		Object obj = pjp.proceed();
		logger.info("Class Name " + className + "  Method Name  " + methodName + "   Method Parameters  "
				+ mapper.writeValueAsString(args) + "   Response from Method  " + mapper.writeValueAsString(obj));
		return obj;

	}

}
