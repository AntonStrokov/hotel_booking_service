package com.example.hotelbooking.hotel_booking_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("execution(* com.example.hotelbooking.hotel_booking_service.controller..*(..)) || " +
			"execution(* com.example.hotelbooking.hotel_booking_service.service..*(..))")
	public void applicationPackagePointcut() {
	}

	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();

		log.info("Enter: {}.{}() with argument[s] = {}", className, methodName, Arrays.toString(args));

		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			long executionTime = System.currentTimeMillis() - start;

			log.info("Exit: {}.{}() with result = {}. Execution time: {} ms",
					className, methodName, result, executionTime);
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(args), className, methodName);
			throw e;
		} catch (Throwable e) {
			log.error("Exception in {}.{}(): {}", className, methodName, e.getMessage());
			throw e;
		}
	}
}
