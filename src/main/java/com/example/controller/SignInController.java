package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @RequestMapping("toSignIn")
    public String toSignIn() {
        return "signIn";
    }

    @RequestMapping("signIn")
    public String signIn(User user) {
        userService.signIn(user);
        return "redirect:login";
    }
}
