package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OaUsersDto {

    private String nickName;

    private String email;

    private String cellphone;

    private BaseEnum.OaUser.StatusEnum status;

    private String roleName;

}
