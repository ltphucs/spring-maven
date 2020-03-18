package com.cg.tutorial.controller;

import com.cg.tutorial.model.User;
import com.cg.tutorial.model.UserDetailsCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@SessionAttributes({"currentUser"})
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * This method handles login GET requests. If users is already logged-in and
     * tries to goto login page again, will be redirected to list page.
     */
    @GetMapping("/login")
    public String loginPage(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }

    @PostMapping("/postLogin")
    public void postLogin(Model model, HttpSession session) {
        log.info("postLogin()");
        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication();
        //
        validatePrinciple(authentication.getPrincipal());
        //
        User loggedInUser = ((UserDetailsCustom) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUser", loggedInUser.getUsername());
        session.setAttribute("userId", loggedInUser.getId());
        session.setAttribute("username", loggedInUser.getUsername());
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDetailsCustom)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    /**
     * This method handles logout requests. Toggle the handlers if you are
     * RememberMe functionality is useless in your app.
     */
    @GetMapping("/logout")
    public ModelAndView logoutPage(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();

        return new ModelAndView("login");
    }

    @GetMapping("/doLogout")
    public ModelAndView doLogoutPage(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();

        return new ModelAndView("login");
    }
}
