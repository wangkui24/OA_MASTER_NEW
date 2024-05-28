package com.kwang43.boot.service.impl;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.model.BaseEnum.RequestLeave.StatusEnum;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.Leave;
import com.kwang43.boot.model.dto.RequestLeaveDto;
import com.kwang43.boot.repository.LeaveRepository;
import com.kwang43.boot.service.RequestService;
import com.kwang43.boot.utils.DateUtils;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public Response<Boolean> requestLeave(RequestLeaveDto requestLeaveDto) {
        try {
            log.info("requestLeaveDto: {}", requestLeaveDto);
            Leave leave = new Leave();
            leave.setEmployeeId(requestLeaveDto.getEmployeeId());
            leave.setLeaveTypeId(requestLeaveDto.getLeaveTypeId());
            leave.setStartDatetime(requestLeaveDto.getStartDatetime());
            leave.setEndDatetime(requestLeaveDto.getEndDatetime());
            leave.setReason(requestLeaveDto.getReason());
            leave.setStatus(StatusEnum.PENDING);
            if(requestLeaveDto.getApproverId() != null){
                leave.setApproverId(requestLeaveDto.getApproverId());
            }
            leave.setCreateDatetime(DateUtils.getNowTime());
            leave.setCreateBy(requestLeaveDto.getCreateBy());
            if(requestLeaveDto.getUpdateDatetime() != null){
                leave.setUpdateDatetime(requestLeaveDto.getUpdateDatetime());
            }
            if(StringUtils.isNotEmpty(requestLeaveDto.getUpdateBy())){
                leave.setUpdateBy(requestLeaveDto.getUpdateBy());
            }
            leaveRepository.save(leave);
            return new Response<>(true);
        }catch (Exception e) {
            log.error("request leave error [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.RequestLeave.REQUEST_LEAVE_FAILED);
        }
    }
}
