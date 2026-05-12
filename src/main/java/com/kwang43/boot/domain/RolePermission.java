package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@ApiModel
@Table(name = "role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -4243737360613791010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    @Column(name = "role_id")
    @ApiModelProperty("角色id")
    private Long roleId;

    @Column(name = "permission_id")
    @ApiModelProperty("权限id")
    private Long permissionId;
}