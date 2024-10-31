package ru.gb.danila.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Around("@annotation(ru.gb.danila.timesheet.annotations.Recover)")
    public Object recoverMethod(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if(method.isAnnotationPresent(Recover.class)){
            if(!method.getReturnType().getName().equals("void")) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    logException(e, method);
                    logException(e, method);
                    return getDefault(method);
                }


            }else {
                try{
                    joinPoint.proceed();
                }catch (Throwable e){
                    logException(e, method);
                }

                return new Object();
            }


        }else{
            throw new IllegalArgumentException("Recover annotation may be used with method!");
        }
    }

    private void logException(Throwable e, Method method) {
        log.error("throw error {}  message {} with method {}", e.getClass(), e.getMessage(), method.getName());
    }

    private Object getDefault(Method method) {
        Class<?> returnType = method.getReturnType();
        if(returnType.isPrimitive()){
            return switch (returnType.getName()){
                case "int" -> 0;
                case "double" -> 0.0;
                case "long" -> 0L;
                case "short", "byte" -> String.valueOf(0);
                default -> throw new IllegalArgumentException("return void is not available");
            };
        }
        return null;
    }
}
