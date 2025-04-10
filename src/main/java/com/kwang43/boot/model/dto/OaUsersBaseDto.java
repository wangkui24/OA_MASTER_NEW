package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OaUsersBaseDto {

    @Column
    private String nickName;

    @Column
    @NotBlank(message = "email can not be empty")
    private String email;

    @Column
    @NotBlank(message = "cellphone can not be empty")
    private String cellphone;

    @Column
    @NotBlank(message = "status can not be empty")
    private BaseEnum.OaUser.StatusEnum status;

    @Column
    @NotBlank(message = "roleId can not be empty")
    private Integer roleId;

}
