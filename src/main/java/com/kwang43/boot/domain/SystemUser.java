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
@Table(name="system_users")
public class SystemUser implements Serializable {
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
    @ApiModelProperty("账号邮箱")
    @NotBlank(message = "email can not be empty")
    private String email;

    @Column
    @ApiModelProperty("账号当前状态")
    @NotBlank(message = "status can not be empty")
    private BaseEnum.SystemUser.StatusEnum status;

    @ManyToOne
    @ApiModelProperty("账号当前Role")
    @NotBlank(message = "role can not be empty")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

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
}
