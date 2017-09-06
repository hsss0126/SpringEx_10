package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {

	@Pointcut("within(com.javalec.ex.*)")
	public void pointCutMethod() {}

	@Around("pointCutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {

		String str = joinPoint.getSignature().toShortString();
		System.out.println(str +" 실행");
		long st = System.currentTimeMillis();

		try {
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(str + " 종료");
			System.out.println("실행시간: " + (et - st));
		}
	}

	@Before("pointCutMethod()")
	public void beforeAop() {
		System.out.println("실행 전에");
	}

	@AfterReturning("pointCutMethod()")
	public void afterReturnAop() {
		System.out.println("정상 리턴후");
	}

	@After("pointCutMethod()")
	public void afterAop() {
		System.out.println("그냥 실행 후");
	}

	@AfterThrowing("pointCutMethod()")
	public void afterThrowingAop() {
		System.out.println("에러 후에");
	}


}
