package com.guojian.server.mapper;

import com.guojian.server.pojo.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
public interface AttendanceMapper extends BaseMapper<Attendance> {
    List<Attendance> getAttendanceByMonth(int year,int month,int employeeId);
    List<Attendance> getAttendanceByYear(int year,int employeeId);
    List<Attendance> getLeaveByYear(int year,int employeeId);
    List<Attendance> getAbsentByYear(int year,int employeeId);
    List<Attendance> getEarlyQuitByYear(int year,int employeeId);
    List<Attendance> getLeaveByMonth(int year,int month,int employeeId);
    List<Attendance> getAbsentByMonth(int year,int month,int employeeId);
    List<Attendance> getEarlyQuitByMonth(int year,int month,int employeeId);
    int countAttendanceByYear(int year,int employeeId);
    int countAttendanceByMonth(int year,int month,int employeeId);
    int countLeaveByYear(int year,int employeeId);
    int countAbsentByYear(int year,int employeeId);
    int countEarlyQuitByYear(int year,int employeeId);
    int countLeaveByMonth(int year,int month,int employeeId);
    int countAbsentByMonth(int year,int month,int employeeId);
    int countEarlyQuitByMonth(int year,int month,int employeeId);



}
