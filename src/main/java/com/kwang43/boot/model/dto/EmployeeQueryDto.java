package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.core.PageableSearch;
import com.kwang43.boot.model.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/12 16:27
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeQueryDto extends PageableSearch implements Serializable {

    /*当一个类被序列化和反序列化时，Java 序列化 API 会生成一个默认的 serialVersionUID
    但是，如果类的结构发生更改（例如，添加或删除字段，更改字段的顺序，更改字段的数据类型等）则生成的默认 serialVersionUID 可能会更改 这可能导致序列化和反序列化出现问题
    为了解决这个问题，Java 提供了一种机制，允许开发人员在类中显式指定 serialVersionUID
    这样，即使类的结构发生变化 只要 serialVersionUID 不变 序列化和反序列化就不会出现问题*/

    private static final long serialVersionUID = 19980204L;

    private Long id;

    private String name;

    private String nickName;

    private String cellphone;

    private String email;

    private BaseEnum.Employee.GenderEnum gender;

    private Integer status;

    private Integer deptId;

    private Integer schoolId;

    private Date employmentDatetimeFrom;

    private Date employmentDatetimeEnd;

    private Date createDatetimeFrom;

    private Date createDatetimeEnd;

}