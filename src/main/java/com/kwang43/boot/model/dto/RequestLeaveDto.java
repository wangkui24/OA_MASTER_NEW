package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.model.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLeaveDto implements Serializable {

    private Integer employeeId;

    private Integer leaveTypeId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDatetime;

    private String reason;

    private BaseEnum.RequestLeave.StatusEnum status;

    private Integer approverId;

    private Date createDatetime;

    private String createBy;

    private Date updateDatetime;

    private String updateBy;
}
