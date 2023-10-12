package com.kwang43.boot.model;

import lombok.Data;

import java.util.Date;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/12 16:27
 */
@Data
public class EmployeeQueryDto {
    private Long id;

    private String name;

    private String nickName;

    private String cellphone;

    private String email;

    private Integer status;

    private Integer deptId;

    private Integer schoolId;

    private Date employmentDatetime;

    private Date createDatetime;

    private String createBy;

    private Date updateDatetime;

    private String updateBy;

    private String remark;

}