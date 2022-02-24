package com.guojian.server.service;

import com.guojian.server.pojo.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
public interface IAttendanceService extends IService<Attendance> {
    public RespBean setLeave(int employeeId, LocalDate date);
    public RespBean setRelax(int employeeId, LocalDate date);
    public RespBean queryAttendance(int year,int month);
    public RespBean queryAttendanceDaysByYear(int year);
    public RespBean queryAttendanceDaysByMonth(int year,int month);
    public RespBean queryAbsentByYear(int year);
    public RespBean queryLeaveByYear(int year);
    public RespBean queryEarlyQuitByYear(int year);
    public RespBean queryAbsentByMonth(int year,int month);
    public RespBean queryLeaveByMonth(int year,int month);
    public RespBean queryEarlyQuitByMonth(int year,int month);
    public RespBean queryAttendanceRecord(int year);
    public RespBean getWorkTimeByMonth(int year,int month);
}
