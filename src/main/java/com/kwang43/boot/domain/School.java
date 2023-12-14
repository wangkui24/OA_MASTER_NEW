package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/12/13 10:55
 */

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name="school")
@ApiModel
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("学校id")
    private Long id;

    @Column
    @ApiModelProperty("学校名")
    private String name;


}