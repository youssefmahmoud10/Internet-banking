package com.example.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:03:32 PM
 */

@Aspect
@Component
public class UserDataLogger {

	private static final Logger logger = LoggerFactory.getLogger(UserDataLogger.class);

	@Before("execution(public * com.example.demo.controllers.UserController.getUserData(..))")
	public void logging() {
		logger.info("Hello From AOP logger!");
	}

}