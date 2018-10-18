package com.hfakhraei.samples.springboot.security.simlpe.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description goes here.
 *
 * @author Hossein Fakhraei (HFakhraei@outlook.com)
 * @version 1 18 October 2018
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public String root(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
            return "redirect:/secure/main";
        else
            return "redirect:/signIn";
    }

    @RequestMapping(value = "/signIn")
    public String signIn(Model model)
    {
        return "signIn";
    }

    @RequestMapping("/secure/main")
    public String home(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

            return "secure/main";
        } else {
            return "redirect:/signIn";
        }
    }

    @RequestMapping("/signOut")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/signIn";
    }

    //@RequestMapping(value = "/error", produces = {"text/html"})
    public String handleError(Model model) {
        return "error";
    }
}
