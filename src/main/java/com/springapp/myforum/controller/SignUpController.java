package com.springapp.myforum.controller;

import com.springapp.myforum.model.User;
import com.springapp.myforum.repository.ForumRepository;
import com.springapp.myforum.validation.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private ForumRepository forumRepository;

    private SignUpValidator signUpValidator;

    @Autowired
    public SignUpController (ForumRepository forumRepository, SignUpValidator signUpValidator) {
        this.signUpValidator = signUpValidator;
        this.forumRepository = forumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String signUp (Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSignUp (User user, BindingResult bindingResult) {
        signUpValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        this.forumRepository.addUser(user);
        return "signup-success";
    }

}
