package com.kwang43.boot.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwang43.boot.model.BaseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name="leave_request")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    @Column
    @ApiModelProperty("员工id")
    private Integer employeeId;

    @Column
    @ApiModelProperty("休假类型id")
    private Integer leaveTypeId;

    @Column
    @ApiModelProperty("开始时间")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDatetime;

    @Column
    @ApiModelProperty("结束时间")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDatetime;

    @Column
    @ApiModelProperty("休假原因")
    private String reason;

    @Column
    @ApiModelProperty("状态")
    private BaseEnum.RequestLeave.StatusEnum status;

    @Column
    @ApiModelProperty("审批人id")
    private Integer approverId;

    @Column
    @ApiModelProperty("创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @Column
    @ApiModelProperty("创建人")
    @CreatedBy
    @NotBlank(message = "createBy can not be empty")
    private String createBy;

    @Column
    @ApiModelProperty("修改时间")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;

    @Column
    @ApiModelProperty("更新人")
    @LastModifiedBy
    private String updateBy;

}
