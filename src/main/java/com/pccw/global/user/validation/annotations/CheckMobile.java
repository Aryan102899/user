package com.pccw.global.user.validation.annotations;

import com.pccw.global.user.validation.validator.CheckMobileValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = CheckMobileValidator.class)
public @interface CheckMobile {
    String message() default "{check.mobile}";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    boolean optional() default false;
}
