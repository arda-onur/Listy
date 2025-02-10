package com.Listy.project.arda.onur.mapper;

import com.Listy.project.arda.onur.dto.AuthenticationRequestDto;
import com.Listy.project.arda.onur.request.AuthenticationRequest;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface AuthenticationMapper {

    AuthenticationRequestDto map (AuthenticationRequest authenticationRequest);
}
