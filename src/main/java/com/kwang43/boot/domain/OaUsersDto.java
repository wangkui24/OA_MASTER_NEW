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
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
public class OaUsersDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("账号id")
    private Long id;

    @Column
    @ApiModelProperty("账号昵称")
    private String nickName;

    @Column
    @ApiModelProperty("账号邮箱")
    @NotBlank(message = "email can not be empty")
    private String email;

    @Column
    @ApiModelProperty("账号手机号")
    @NotBlank(message = "cellphone can not be empty")
    private String cellphone;

    @Column
    @ApiModelProperty("员工当前状态")
    @NotBlank(message = "status can not be empty")
    private BaseEnum.OaUser.StatusEnum status;

    @Column
    @ApiModelProperty("账号当前角色名")
    @NotBlank(message = "roleName can not be empty")
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

    @Column
    @ApiModelProperty("账号修改时间")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;

    @Column
    @ApiModelProperty("账号修改人")
    @LastModifiedBy
    private String updateBy;

    public OaUsersDto(Employee employee) {
        this.id = employee.getId();
        this.nickName = employee.getNickName();
        this.email = employee.getEmail();
        this.cellphone = employee.getCellphone();
        this.createDatetime = employee.getCreateDatetime();
        this.createBy = employee.getCreateBy();
        this.updateDatetime = employee.getUpdateDatetime();
        this.updateBy = employee.getUpdateBy();
        if (employee.getOaRoles() != null) {
            this.roleName = employee.getOaRoles().getRoleName();
        }
    }
}
