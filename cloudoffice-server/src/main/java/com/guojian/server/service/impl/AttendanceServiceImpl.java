package com.guojian.server.service.impl;

import com.guojian.server.commons.AttendanceState;
import com.guojian.server.mapper.AttendanceRecordMapper;
import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.Attendance;
import com.guojian.server.mapper.AttendanceMapper;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

    @Autowired
    AttendanceRecordMapper attendanceRecordMapper;
    @Autowired
    AttendanceMapper attendanceMapper;


    /**
     * @param employeeId:
    	 * @param date:
      * @return RespBean
     * @author smy
     * @description 为员工设置出差 TODO 权限校验
     * @date 2022/2/22 15:38
     */
    @Override
    public RespBean setLeave(int employeeId, LocalDate date) {
        Attendance attendance=new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setAttendanceState(AttendanceState.LEAVE);
        attendance.setDate(date);
        attendance.setWorkTime(0.0);
        int insert = attendanceMapper.insert(attendance);
        if(insert==1) return RespBean.success("设置出差成功");
        return RespBean.error("设置失败，请重试");
    }

    /**
     * @param employeeId:
    	 * @param date:
      * @return RespBean
     * @author smy
     * @description 为员工设置休假 TODO 权限校验
     * @date 2022/2/22 15:37
     */
    @Override
    public RespBean setRelax(int employeeId, LocalDate date) {
        Attendance attendance=new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setAttendanceState(AttendanceState.RELAX);
        attendance.setDate(date);
        attendance.setWorkTime(0.0);
        int insert = attendanceMapper.insert(attendance);
        if(insert==1) return RespBean.success("设置休假成功");
        return RespBean.error("设置失败，请重试");
    }

    /**
     * @param year:
    	 * @param month:
      * @return RespBean
     * @author smy
     * @description 根据指定年份和月份获取用户的出勤记录
     * @date 2022/2/22 16:02
     */
    @Override
    public RespBean queryAttendance(int year, int month) {
        //TODO  修改成获取所有员工的ID
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> attendanceByMonth = attendanceMapper.getAttendanceByMonth(year, month, adminId);
        return RespBean.success("查询成功",attendanceByMonth);
    }
}
