package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/12/25 10:53
 */

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name="department")
public class Dept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("部门id")
    private Long id;

    @Column
    @ApiModelProperty("部门名")
    private String name;
}