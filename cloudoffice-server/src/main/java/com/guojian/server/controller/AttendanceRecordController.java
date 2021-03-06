package com.guojian.server.controller;


import com.guojian.server.pojo.AdminLoginParam;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceRecordService;
import com.guojian.server.service.IAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

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
@Api("考勤记录管理")
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
    @GetMapping("/queryByEmployeeId")
    public RespBean queryByEmployeeId(int employeeId,int size,int cur)
    {
        return attendanceRecordService.queryByEmployeeId(employeeId,cur,size);
    }

    @ApiOperation(value = "查询某员工某天的记录")
    @GetMapping("/queryByDate")
    public RespBean queryByDate(String date)
    {
        return attendanceRecordService.queryByDate(date);
    }
}
