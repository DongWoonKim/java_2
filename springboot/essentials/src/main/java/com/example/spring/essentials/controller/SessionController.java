package com.example.spring.essentials.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

    // 로그인 페이지
    @GetMapping("/login")
    public String login(
            HttpSession session,
            Model model
    ) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            model.addAttribute("username", username);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String username,
            HttpSession session
    ) {
        session.setAttribute("username", username);
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logoutExc(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:login";
    }

}
