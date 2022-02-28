package com.guojian.server.controller;


import com.guojian.server.mapper.AttendanceMapper;
import com.guojian.server.pojo.Attendance;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api("考勤管理")
public class AttendanceController {

    @Autowired
    IAttendanceService attendanceService;

    @ApiOperation(value = "获取每月考勤记录")
    @GetMapping("/getAttendance")
    public RespBean getAttendanceByMonth(int year,int month){
        return attendanceService.queryAttendance(year,month);
    }


    @ApiOperation(value = "获取年度考勤总览")
    @GetMapping("/getAttendanceDaysByYear")
    public RespBean getAttendanceDaysByYear(int year){
        return attendanceService.queryAttendanceDaysByYear(year);
    }
    @ApiOperation(value = "获取月度考勤总览")
    @GetMapping("/getAttendanceDaysByMonth")
    public RespBean getAttendanceDaysByMonth(int year,int month){
        return attendanceService.queryAttendanceDaysByMonth(year,month);
    }
    @ApiOperation(value = "获取员工一年的上下班的最早和最晚时间")
    @GetMapping("/queryAttendanceRecord")
    public RespBean queryAttendanceRecord(int year){
        return attendanceService.queryAttendanceRecord(year);
    }

    @ApiOperation(value = "获取年度缺席记录")
    @GetMapping("/getAbsentByYear")
    public RespBean getAbsentRecordByYear(int year){
        return attendanceService.queryAbsentByYear(year);
    }
    @ApiOperation(value = "获取月度缺席记录")
    @GetMapping("/getAbsentByMonth")
    public RespBean getAbsentRecordByMonth(int year,int month){
        return attendanceService.queryAbsentByMonth(year,month);
    }
    @ApiOperation(value = "获取年度出差记录")
    @GetMapping("/getLeaveByYear")
    public RespBean getLeaveRecordByYear(int year){
        return attendanceService.queryLeaveByYear(year);
    }
    @ApiOperation(value = "获取月度出差记录")
    @GetMapping("/getLeaveByMonth")
    public RespBean getLeaveRecordByMonth(int year,int month){
        return attendanceService.queryLeaveByMonth(year,month);
    }
    @ApiOperation(value = "获取年度早退记录")
    @GetMapping("/getEarlyQuitByYear")
    public RespBean getEarlyQuitRecordByYear(int year){
        return attendanceService.queryEarlyQuitByYear(year);
    }
    @ApiOperation(value = "获取月度早退记录")
    @GetMapping("/getEarlyQuitByMonth")
    public RespBean getEarlyQuitRecordByMonth(int year,int month){
        return attendanceService.queryEarlyQuitByMonth(year,month);
    }
    @ApiOperation(value = "获取本月工时")
    @GetMapping("/getWorkTimeByMonth")
    public RespBean getWorkTimeByMonth(int year,int month){
        return attendanceService.getWorkTimeByMonth(year,month);
    }

}
