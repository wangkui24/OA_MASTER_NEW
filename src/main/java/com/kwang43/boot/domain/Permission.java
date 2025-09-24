package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
@Table(name="permission")
public class Permission implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("权限id")
    private Long id;

    @Column
    @ApiModelProperty("权限名称")
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;
}
