package com.pi.controller;

import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * контроллер регистрации
 */
@Controller
public class RegistrationController {

    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * получить страницу регистрации
     * @return страницу регистрации
     */
    @GetMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    /**
     * процесс регистрации
     * @param fullName фио
     * @param inn инн
     * @param birthday дата рождения
     * @param login логин
     * @param password пароль
     * @param attributes
     * @return страницу регистрации или логина
     */
    @PostMapping(value = "/registration/process")
    public String process(
            @RequestParam("fullName") String fullName,
            @RequestParam("inn") String inn,
            @RequestParam("birthday") String birthday,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            RedirectAttributes attributes) {
        String result = personService.registration(fullName, inn, birthday, login, password);
        if(result != null){
            attributes.addFlashAttribute("error", result);
            return "redirect:/registration";
        }else{
            return "redirect:/login";
        }

    }
}
