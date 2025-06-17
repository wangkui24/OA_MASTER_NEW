package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.BaseEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveOaUsersDto implements Serializable {

    private String nickName;

    private String email;

    private String cellphone;

    private BaseEnum.OaUser.StatusEnum status;

    private String roleName;

    private Date createDatetime;

    private String createBy;

    private Date updateDatetime;

    private String updateBy;

}
