package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/11 15:11
 */

@Data
@ToString
@Entity
@ApiModel
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("员工id")
    private Long id;

    @Column
    @ApiModelProperty("员工姓名")
    private String name;

    @Column
    @ApiModelProperty("员工昵称")
    private String nickName;

    @Column
    @ApiModelProperty("员工手机号")
    private String cellphone;

    @Column
    @ApiModelProperty("员工邮箱")
    private String email;

    @Column
    @ApiModelProperty("员工当前状态")
    private Integer status;

    @Column
    @ApiModelProperty("员工归属部门")
    private Integer deptId;

    @Column
    @ApiModelProperty("员工所属院校")
    private Integer schoolId;

    @Column
    @ApiModelProperty("雇佣时间")
    private Date employmentDatetime;

    @Column
    @ApiModelProperty("创建时间")
    private Date createDatetime;

    @Column
    @ApiModelProperty("创建人")
    private String createBy;

    @Column
    @ApiModelProperty("备注")
    private String remark;

}