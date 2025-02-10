package com.Listy.project.arda.onur.repository;

import com.Listy.project.arda.onur.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findCommentsByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Comment> findCommentsByCommentContainingIgnoreCase(String comment, Pageable pageable);
}
