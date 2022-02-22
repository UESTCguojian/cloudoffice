package com.guojian.server.controller;


import com.guojian.server.pojo.Attendance;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    IAttendanceService attendanceService;

    @ApiOperation(value = "获取考勤记录")
    @PostMapping("/getAttendance")
    public RespBean getAttendanceByMonth(int year,int month){
        return attendanceService.queryAttendance(year,month);
    }

}
