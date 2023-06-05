package com.pccw.global.user.validation.validator;

import com.pccw.global.user.utils.UserUtils;
import com.pccw.global.user.validation.annotations.CheckEmail;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    private boolean optional;

    @Override
    public void initialize(CheckEmail checkEmail) {
        optional = checkEmail.optional();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (optional && StringUtils.isBlank(s)) {
            return true;
        }
        if (StringUtils.isBlank(s)) {
            return false;
        }
        return UserUtils.validEmail(s);
    }
}
