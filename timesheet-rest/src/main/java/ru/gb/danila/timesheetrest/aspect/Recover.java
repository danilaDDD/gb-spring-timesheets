package ru.gb.danila.timesheetrest.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Recover {
    Class<? extends Throwable>[] noRecoverFor() default {};
}
