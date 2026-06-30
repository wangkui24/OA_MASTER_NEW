package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeQueryDto extends QueryBase implements Serializable {

    private static final long serialVersionUID = 749724619346154799L;

    private Integer id;

    private String name;

    private String email;

    private String mobile;

    private String deptName;

    private String roleName;

    private String status;

    private Date onboardDateFrom;

    private Date onboardDateTo;
}
