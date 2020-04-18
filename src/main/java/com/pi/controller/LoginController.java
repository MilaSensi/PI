package com.pi.controller;

import com.pi.model.SecurityPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Контроллер авторизации
 */
@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationManager = authenticationManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    /**
     * Получить страницу логина
     */
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * Авторизация пользователя
     * @param username Логин пользователя
     * @param password Пароль пользователя
     */
    @PostMapping(value = "/login/process")
    public String process(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            RedirectAttributes attributes) {
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpServletResponse wrapper = new HttpServletResponseWrapper(servletResponse) {
                @Override
                public void sendRedirect(String location) throws IOException {
                }
            };
            try {
                authenticationSuccessHandler.onAuthenticationSuccess(servletRequest, wrapper, authentication);
            } catch (Exception ex) {
            }
            if(((SecurityPerson) authentication.getPrincipal()).getPersonType().getCode().equals("CLIENT")){
                return "redirect:/client";
            }else{
                return "redirect:/orders";
            }

        } catch (AuthenticationException e) {
            try {
                authenticationFailureHandler.onAuthenticationFailure(servletRequest, servletResponse, e);
            } catch (Exception ex) {
            }
            attributes.addFlashAttribute("error", getExceptionCode(e, username));
            return "redirect:/login";
        }
    }

    private String getExceptionCode(AuthenticationException e, String username) {
        if (e instanceof LockedException) {
            return "Пользователь заблокирован " + username;
        } else if (e instanceof BadCredentialsException) {
            return "Неверный логин или пароль " + username;
        } else {
            return "Пользователь не найден " + username;
        }
    }
}
