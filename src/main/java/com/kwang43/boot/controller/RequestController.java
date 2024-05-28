package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.RequestLeaveDto;
import com.kwang43.boot.service.RequestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestLeave;

    @PostMapping("/leave")
    @ApiOperation(value="申请休假", notes="")
    public Response<Boolean> requestLeave(@RequestBody RequestLeaveDto requestLeaveDto) {
        return requestLeave.requestLeave(requestLeaveDto);
    }
}