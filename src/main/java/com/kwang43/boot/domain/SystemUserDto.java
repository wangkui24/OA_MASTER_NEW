package com.kwang43.boot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwang43.boot.model.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
public class SystemUserDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("账号id")
    private Long id;

    @Column
    @ApiModelProperty("账号姓名")
    @NotBlank(message = "username can not be empty")
    private String username;

    @Column
    @ApiModelProperty("账号密码")
    @NotBlank(message = "password can not be empty")
    private String password;

    @Column
    @ApiModelProperty("登陆token")
    private String token;

    @Column
    @ApiModelProperty("账号邮箱")
    @NotBlank(message = "email can not be empty")
    private String email;

    @Column
    @ApiModelProperty("账号当前状态")
    @NotBlank(message = "status can not be empty")
    private BaseEnum.SystemUser.StatusEnum status;

    @Column
    @ApiModelProperty("账号当前Role")
    @NotBlank(message = "role can not be empty")
    private String roleName;

    @Column
    @ApiModelProperty("账号创建时间")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    @NotBlank(message = "createDatetime can not be empty")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @Column
    @ApiModelProperty("账号创建人")
    @CreatedBy
    @NotBlank(message = "createBy can not be empty")
    private String createBy;

    public SystemUserDto(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.token = systemUser.getToken();
        this.email= systemUser.getEmail();
        this.status = systemUser.getStatus();
        this.createDatetime = systemUser.getCreateDatetime();
        this.createBy = systemUser.getCreateBy();
        if (systemUser.getSystemRole() != null) {
            this.roleName = systemUser.getSystemRole().getRoleName();
        }
    }
}
