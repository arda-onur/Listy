package com.Listy.project.arda.onur.service;

import com.Listy.project.arda.onur.dto.CommentRequestDto;
import com.Listy.project.arda.onur.model.Comment;
import com.Listy.project.arda.onur.repository.CommentRepository;
import com.Listy.project.arda.onur.vo.CommentRetrieval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private  final CommentRepository commentRepository;


    public Comment createComment(CommentRequestDto commentRequestDto) {
        Objects.requireNonNull(commentRequestDto, "commentRequestDto cannot be null");

        String username =  SecurityContextHolder
                                  .getContext()
                                  .getAuthentication()
                                  .getName();

           log.info("Creating comment with username {}", username);

        Comment comment = Comment
                .builder()
                .author(username)
                .comment(commentRequestDto.comment())
                .date(LocalDate.now())
                .build();

        commentRepository.save(comment);

        return comment;
    }

    public Page<Comment> getComments(String criteria, String word, Pageable pageable) {

        log.info("Getting comments with criteria {}", criteria);

       return CommentRetrieval.of(commentRepository).getComments(criteria,word,pageable);
    }


}
