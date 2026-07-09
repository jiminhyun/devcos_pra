package com.example.assignment._0702;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardController {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @GetMapping("/dashboard")
    public String dashboard(@CookieValue(value="lastVisit", required=false) String lastVisit, @CookieValue(value="theme", defaultValue="light") String theme, HttpSession session, Model model, HttpServletResponse response) {

        String username = (String) session.getAttribute("username");
        if(username == null) {
            return "redirect:/login";
        }
        model.addAttribute("theme", theme); //이용자 처리 후에
        model.addAttribute("username", username);
        if(lastVisit != null) { //쿠키가 만약 없으면 안 보임!
            long currentTime = Long.parseLong(lastVisit);
            String readAbleTime = Instant.ofEpochMilli(currentTime)
                    .atZone(ZoneId.systemDefault()) // 지역 기준
                    .format(formatter);
            model.addAttribute("lastVisit", readAbleTime);
        }
        Cookie visitTime = new Cookie("lastVisit", String.valueOf(System.currentTimeMillis()));
        visitTime.setMaxAge(3600*24*7);
        visitTime.setPath("/");
        visitTime.setHttpOnly(true);
        response.addCookie(visitTime);
        return "dashboard";

    }

    @PostMapping("/dashboard")
    public String loginExc(@RequestParam String username, HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/dashboard";
    }

    @GetMapping("/theme")
    public String theme(@RequestParam String mode, HttpServletResponse response) {
        String swMode = "dark".equals(mode) ? "dark" : "light";
        Cookie theme = new Cookie("theme", swMode);
        theme.setPath("/");
        theme.setHttpOnly(true);
        theme.setMaxAge(3600*24*7);
        response.addCookie(theme);
        return "redirect:/dashboard";
    }
}
