package com.pccw.global.user.validation.validator;

import com.pccw.global.user.utils.UserUtils;
import com.pccw.global.user.validation.annotations.CheckMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckMobileValidator implements ConstraintValidator<CheckMobile, String> {
    private boolean optional;

    @Override
    public void initialize(CheckMobile checkMobile) {
        optional = checkMobile.optional();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (optional && StringUtils.isBlank(s)) {
            return true;
        }

        if (StringUtils.isBlank(s)) {
            return false;
        }

        return UserUtils.validMobile(s);
    }
}