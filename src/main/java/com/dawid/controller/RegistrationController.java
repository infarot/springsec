package com.dawid.controller;

import com.dawid.crm.UserCrm;
import com.dawid.entity.User;
import com.dawid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("crmUser", new UserCrm());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("crmUser") UserCrm userCrm, BindingResult theBindingResult, Model theModel) {
        // form validation
        if (theBindingResult.hasErrors()) {
            System.out.println(theBindingResult.getAllErrors());
            theModel.addAttribute("registrationError",theBindingResult.getAllErrors());
            return "registration-form";
        }
        // check the database if user already exists
        User existing = userService.findByUserName(userCrm.getUserName());
        if (existing != null) {
            theModel.addAttribute("crmUser", new UserCrm());
            theModel.addAttribute("registrationError", "User name already exists.");
            return "registration-form";
        }
        // save user in the database
        userService.save(userCrm);
        return "registration-confirmation";
    }
}
