package com.Listy.project.arda.onur.controller;




import com.Listy.project.arda.onur.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping
@RequiredArgsConstructor
public class ViewController {

    private final CommentService commentService;


    @GetMapping("/login")
    public String getLoginPage() {


        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @GetMapping("/index")
    public String getIndexPage(Model model)
    {
        if (!model.containsAttribute("comments")) {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("date").descending());
            model.addAttribute("comments", this.commentService.getComments("comment", "iyi", pageable));
        }
        return "index";
    }


}
