package com.kwang43.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
@Table(name="oa_roles")
public class OaRoles extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6584599873967033561L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("角色id")
    private Long id;

    @Column
    @ApiModelProperty("角色名")
    @NotBlank(message = "RoleName can not be empty")
    private String roleName;

    @Column
    @ApiModelProperty("角色描述")
    private String description;

    @Column
    @ApiModelProperty("备注说明")
    private String remark;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Permission> permission;

}
