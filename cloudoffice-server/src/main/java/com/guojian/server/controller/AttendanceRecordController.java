package com.guojian.server.controller;


import com.guojian.server.pojo.AdminLoginParam;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceRecordService;
import com.guojian.server.service.IAttendanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/attendance-record")
public class AttendanceRecordController {

    @Autowired
    private IAttendanceService attendanceService;
    @Autowired
    private IAttendanceRecordService attendanceRecordService;
    @ApiOperation(value = "上班打卡")
    @PostMapping("/clockin")
    public RespBean clockIn(@RequestBody String position)
    {
        return attendanceRecordService.clockIn(position);
    }

    @ApiOperation(value = "下班打卡")
    @PostMapping("/clockout")
    public RespBean clockOut(@RequestBody String position)
    {
        return attendanceRecordService.clockOut(position);
    }

    @ApiOperation(value = "查询某员工的考勤记录")
    @PostMapping("/queryByEmployeeId")
    public RespBean queryByEmployeeId(@RequestBody int employeeId)
    {
        return attendanceRecordService.queryByEmployeeId(employeeId);
    }

}
