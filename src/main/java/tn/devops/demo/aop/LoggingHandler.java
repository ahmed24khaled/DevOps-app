package tn.devops.demo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@After(value = "execution(* tn.devops.demo.controllers.*.*(..))")
	public void controller(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint.getSignature().getName());
	}
}