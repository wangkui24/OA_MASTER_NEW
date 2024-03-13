package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
@Table(name="system_roles")
public class SystemRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("角色id")
    private Long id;

    @Column
    @ApiModelProperty("角色名")
    @NotBlank(message = "RoleName can not be empty")
    private String roleName;
}
