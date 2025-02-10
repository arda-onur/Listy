package com.Listy.project.arda.onur.mapper;

import com.Listy.project.arda.onur.dto.CommentRequestDto;
import com.Listy.project.arda.onur.request.CommentRequest;
import org.mapstruct.Mapper;

@Mapper(config= MapperConfiguration.class)
public interface CommentMapper {

         CommentRequestDto map (CommentRequest request);
}
