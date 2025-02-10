package com.Listy.project.arda.onur.controller;

import com.Listy.project.arda.onur.exception.NoSuchAuthorException;
import com.Listy.project.arda.onur.exception.NoSuchCommentException;
import com.Listy.project.arda.onur.mapper.CommentMapper;
import com.Listy.project.arda.onur.model.Comment;
import com.Listy.project.arda.onur.request.CommentRequest;
import com.Listy.project.arda.onur.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @PostMapping
    public String createComment(@ModelAttribute CommentRequest commentRequest) {
        commentService.createComment(commentMapper.map(commentRequest));
        return "redirect:/index";
    }

    @GetMapping
    public String listComments(@RequestParam String criteria, @RequestParam String word, RedirectAttributes redirectAttributes) {

        try {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("date").descending());
            Page<Comment> comments = commentService.getComments(criteria, word, pageable);
            redirectAttributes.addFlashAttribute("comments", comments);
        } catch (NoSuchAuthorException | NoSuchCommentException ex) {
           redirectAttributes.addFlashAttribute("comments", Page.empty());
        }

        return "redirect:/index";
    }


}
