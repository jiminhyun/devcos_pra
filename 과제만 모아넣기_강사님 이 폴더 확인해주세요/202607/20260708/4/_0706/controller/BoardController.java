package com.example.assignment._0706.controller;

import com.example.assignment._0706.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @GetMapping("/")
    public String boardList(HttpSession session, Model model) {
        setSession(session, model);
        return "board-list";
    }

    @GetMapping("/write")
    public String write(HttpSession session, Model model) {
        setSession(session, model);
        return "board-write";
    }

    @GetMapping("/detail")
    public String detail(
            @RequestParam("id") Long id,
            HttpSession session,
            Model model
    ) {
        setSession(session, model);
        model.addAttribute("id", id);
        return "/board-detail";
    }

    @GetMapping("/update")
    public String update(
            @RequestParam("id") Long id,
            HttpSession session,
            Model model
    ) {
        setSession(session, model);
        model.addAttribute("id", id);
        return "/board-update";
    }

    private void setSession(HttpSession session, Model model) {
        String userId = (String) session.getAttribute(SessionConst.USER_ID);
        String userName = (String) session.getAttribute(SessionConst.USER_NAME);

        model.addAttribute(SessionConst.USER_ID, userId);
        model.addAttribute(SessionConst.USER_NAME, userName);
    }
}
