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

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/11 15:11
 */

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
@Table(name="employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("员工id")
    private Long id;

    @Column
    @ApiModelProperty("员工姓名")
    @NotBlank(message = "username can not be empty")
    private String name;

    @Column
    @ApiModelProperty("员工昵称")
    @NotBlank(message = "nickname can not be empty")
    private String nickName;

    @Column
    @ApiModelProperty("员工手机号")
    @NotBlank(message = "cellphone can not be empty")
    private String cellphone;

    @Column
    @ApiModelProperty("员工邮箱")
    @NotBlank(message = "email can not be empty")
    private String email;

    @Column
    @ApiModelProperty("员工性别")
    @NotBlank(message = "gender can not be empty")
    private BaseEnum.Employee.GenderEnum gender;

    @Column
    @ApiModelProperty("员工当前状态")
    @NotBlank(message = "status can not be empty")
    private BaseEnum.Employee.StatusEnum status;

    @Column
    @ApiModelProperty("员工归属部门")
    @NotBlank(message = "department can not be empty")
    private Integer deptId;

    @ManyToOne
    @ApiModelProperty("员工所属院校")
    @NotBlank(message = "school can not be empty")
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @Column
    @ApiModelProperty("雇佣时间")
    @Temporal(TemporalType.DATE)
    @NotBlank(message = "employmentDatetime can not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date employmentDatetime;

    @Column
    @ApiModelProperty("创建时间")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    @NotBlank(message = "createDatetime can not be empty")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @Column
    @ApiModelProperty("创建人")
    @CreatedBy
    @NotBlank(message = "createBy can not be empty")
    private String createBy;

    @Column
    @ApiModelProperty("修改时间")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;

    @Column
    @ApiModelProperty("更新人")
    @LastModifiedBy
    private String updateBy;

    @Column
    @ApiModelProperty("备注")
    private String remark;

}