package com.statemachine.config;

import com.statemachine.enums.States;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**

 * @author DongLingXu
 * @description
 * @email
 * @date 2020/10/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface OrderOnTransition {
    States[] source() default {};

    States[] target() default {};
}
