package com.springapp.myforum.validation;

import com.springapp.myforum.model.Theme;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MessageValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return Theme.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "required.message", "Message can not be empty");
    }
}
