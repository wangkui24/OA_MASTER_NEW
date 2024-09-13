package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/16 13:45
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto implements Serializable {
    private Long id;

    private String name;

    private String nickName;

    private String cellphone;

    private String email;

    private BaseEnum.Employee.GenderEnum gender;

    private BaseEnum.Employee.EmployeeStatusEnum status;

    private Integer deptId;

    private Integer schoolId;

    private Date employmentDatetime;

    private Date createDatetime;

    private String createBy;

    private Date updateDatetime;

    private String updateBy;

    private String remark;
}