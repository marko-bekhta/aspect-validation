package com.acme;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Marko Bekhta
 */

@Aspect
public aspect ValidationAspect {

	private ExecutableValidator executableValidator = Validation.buildDefaultValidatorFactory().getValidator().forExecutables();

	@Pointcut("@annotation(validated)")
	public void annotationPointCutDefinition(Validated validated) {
	}

	@Pointcut("execution(* *(..))")
	public void atExecution(JoinPoint jp) {
	}

	@Before("annotationPointCutDefinition(validated) && atExecution(joinPoint)")
	public void aroundAdvice(Validated validated, JoinPoint joinPoint) {
		Method method = ( (MethodSignature) joinPoint.getSignature() ).getMethod();
		Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters( joinPoint.getTarget(), method, joinPoint.getArgs() );
		if ( !violations.isEmpty() ) {
			throw new ConstraintViolationException( violations );
		}
		System.out.println( "Finished validating method parameters" );
	}

	@After("annotationPointCutDefinition(validated) && atExecution(joinPoint)")
	public void printNewLine(Validated validated, JoinPoint joinPoint) {
		// do the validation of return value
		// executableValidator.validateReturnValue(  )
		System.out.println( "Finished validating method return value" );
	}
}
