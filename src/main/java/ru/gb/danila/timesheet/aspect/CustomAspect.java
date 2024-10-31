package ru.gb.danila.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.gb.danila.timesheet.annotations.Recover;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class CustomAspect {
    @Pointcut("@annotation(ru.gb.danila.timesheet.annotations.Recover)")
    public void recoverMethodsPointcut(){}

    @Around("recoverMethodsPointcut()")
    public Object recoverMethod(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if(method.isAnnotationPresent(Recover.class)){
            Object result = null;
            try{
                result = joinPoint.proceed();
                return result;
            } catch (Throwable e) {
                log.error("throw error {}  message {} with method {}", e.getClass(), e.getMessage(), method.getName());
                return getDefault(result);
            }
        }else{
            throw new IllegalArgumentException("Recover annotation may be used with method!");
        }
    }

    private Object getDefault(Object result) {
        if(result != null) {
            Class<?> clazz = result.getClass();
            if (clazz.isPrimitive()) {
                return 0;
            }
        }

        return null;
    }
}
