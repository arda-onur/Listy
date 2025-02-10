package com.Listy.project.arda.onur.mapper;


import com.Listy.project.arda.onur.dto.RegisterRequestDto;
import com.Listy.project.arda.onur.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface RegisterMapper {

    RegisterRequestDto map (RegisterRequest registerRequest);
}
