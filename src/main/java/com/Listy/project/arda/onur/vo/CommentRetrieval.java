package com.Listy.project.arda.onur.vo;

import com.Listy.project.arda.onur.constant.CriteriaTypes;
import com.Listy.project.arda.onur.exception.NoSuchAuthorException;
import com.Listy.project.arda.onur.exception.NoSuchCommentException;
import com.Listy.project.arda.onur.model.Comment;
import com.Listy.project.arda.onur.repository.CommentRepository;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@Value(staticConstructor = "of")
public class CommentRetrieval {

    private static final Page<Comment> emptyComments = Page.empty();
    CommentRepository commentRepository;

    public Page<Comment> getComments(String criteria, String word , Pageable pageable) {

        return switch (CriteriaTypes.valueOf(criteria.toUpperCase())){
            case AUTHOR -> Optional.of(this.commentRepository
                            .findCommentsByAuthorContainingIgnoreCase(word.toLowerCase() , pageable))
                    .filter(page -> !page.isEmpty())
                    .orElseThrow(() -> new NoSuchAuthorException(word));

            case COMMENT ->Optional.of(this.commentRepository
                            .findCommentsByCommentContainingIgnoreCase(word.toLowerCase(), pageable))
                    .filter(comment -> !comment.isEmpty())
                    .orElseThrow(() -> new NoSuchCommentException(word));

            default -> emptyComments;

        };
    }

}

