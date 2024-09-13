package com.kwang43.boot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@ApiModel
@NoArgsConstructor
@Table(name="oa_roles")
public class OaRoles  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("角色id")
    private Long id;

    @Getter
    @Column
    @ApiModelProperty("角色名")
    @NotBlank(message = "RoleName can not be empty")
    private String roleName;

    @Column
    @ApiModelProperty("账号创建时间")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @Column
    @ApiModelProperty("账号创建人")
    @CreatedBy
    private String createBy;

    @Column
    @ApiModelProperty("账号修改时间")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;

    @Column
    @ApiModelProperty("账号修改人")
    @LastModifiedBy
    private String updateBy;

    @Column
    @ApiModelProperty("备注说明")
    private String remark;
}
