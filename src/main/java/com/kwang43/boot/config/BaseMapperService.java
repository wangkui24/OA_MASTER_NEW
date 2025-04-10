package com.kwang43.boot.config;

import com.kwang43.boot.domain.OaUsers;
import com.kwang43.boot.model.dto.OaUsersBaseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BaseMapperService {
    private final ModelMapper modelMapper;

    public BaseMapperService() {
        this.modelMapper = new ModelMapper();
    }

    public OaUsersBaseDto getOaUsersBaseDto(OaUsers oaUsers) {
        return modelMapper.map(oaUsers, OaUsersBaseDto.class);
    }
}
