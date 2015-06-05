package com.springapp.myforum.validation;

import com.springapp.myforum.model.User;
import com.springapp.myforum.repository.ForumRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class SignUpValidator implements Validator {

    private ForumRepository forumRepository;

    @Autowired
    public SignUpValidator (ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Username is required.");

        List<String> names = forumRepository.getNames();
        int length = names.size();
        int k = 0;
        while (k < length){
            if (user.getName().toLowerCase().equals(names.get(k).toLowerCase())) {
                errors.rejectValue("name", "name.notValid", "Username already in use");
            }
            k++;
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Passwird is required.");

        if (!(user.getPassword().equals(user.getPasswordConfirm()))) {
            errors.rejectValue("passwordConfirm", "passwordConfirm.passwordDontMatch", "Passwords dont match.");
        }

        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email adress is not valid");
        }

    }
}
