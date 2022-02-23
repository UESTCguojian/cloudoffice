package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guojian.server.commons.AttendanceState;
import com.guojian.server.mapper.AttendanceRecordMapper;
import com.guojian.server.pojo.*;
import com.guojian.server.mapper.AttendanceMapper;
import com.guojian.server.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * @param year:
     * @return RespBean
     * @author smy
     * @description 获取员工一年的考勤总结
     * @date 2022/2/23 10:47
     */
    @Override
    public RespBean queryAttendanceDaysByYear(int year) {
        //TODO  修改成获取所有员工的ID
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        int attendance = attendanceMapper.countAttendanceByYear(year,adminId);
        int absent = attendanceMapper.countAbsentByYear(year, adminId);
        int earlyQuit = attendanceMapper.countEarlyQuitByYear(year, adminId);
        int leave = attendanceMapper.countLeaveByYear(year, adminId);

        Map<String,Map> res=new HashMap<>();
        Map<String,Integer> attendanceMap=new HashMap<>();
        attendanceMap.put("all",0);
        for(int i=1;i<13;i++){
            int days=attendanceMapper.countAttendanceByMonth(year,i,adminId);
            attendanceMap.put(i+"",days);
            attendanceMap.put("all",attendanceMap.get("all")+days);
        }
        Map<String,Integer> absentMap=new HashMap<>();
        absentMap.put("all",0);
        for(int i=1;i<13;i++){
            int days=attendanceMapper.countAbsentByMonth(year,i,adminId);
            absentMap.put(i+"",days);
            absentMap.put("all",absentMap.get("all")+days);
        }
        Map<String,Integer> earlyQuitMap=new HashMap<>();
        earlyQuitMap.put("all",0);
        for(int i=1;i<13;i++){
            int days=attendanceMapper.countEarlyQuitByMonth(year,i,adminId);
            earlyQuitMap.put(i+"",days);
            earlyQuitMap.put("all",earlyQuitMap.get("all")+days);
        }
        Map<String,Integer> leaveMap=new HashMap<>();
        leaveMap.put("all",0);
        for(int i=1;i<13;i++){
            int days=attendanceMapper.countLeaveByMonth(year,i,adminId);
            leaveMap.put(i+"",days);
            leaveMap.put("all",leaveMap.get("all")+days);
        }
        res.put("Attendance",attendanceMap);
        res.put("Absent",absentMap);
        res.put("EarlyQuit",earlyQuitMap);
        res.put("Leave",leaveMap);
        return RespBean.success("查询成功",res);
    }


    /**
     * @param year:
     * @return RespBean
     * @author smy
     * @description 获取员工一个月的考勤总结
     * @date 2022/2/23 10:47
     */
    @Override
    public RespBean queryAttendanceDaysByMonth(int year,int month) {
        //TODO  修改成获取所有员工的ID
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        int attendance = attendanceMapper.countAttendanceByMonth(year,month,adminId);
        int absent = attendanceMapper.countAbsentByMonth(year,month,adminId);
        int earlyQuit = attendanceMapper.countEarlyQuitByMonth(year,month,adminId);
        int leave = attendanceMapper.countLeaveByMonth(year,month,adminId);

        Map<String,Integer> map=new HashMap<>();
        map.put("Attendance",attendance);
        map.put("Absent",absent);
        map.put("EarlyQuit",earlyQuit);
        map.put("Leave",leave);
        return RespBean.success("查询成功",map);
    }

    @Override
    public RespBean queryAbsentByYear(int year) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> absentByYear = attendanceMapper.getAbsentByYear(year, adminId);
        return RespBean.success("查询成功",absentByYear);
    }

    @Override
    public RespBean queryLeaveByYear(int year) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> leaveByYear = attendanceMapper.getLeaveByYear(year, adminId);
        return RespBean.success("查询成功",leaveByYear);
    }

    @Override
    public RespBean queryEarlyQuitByYear(int year) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> earlyQuitByYear = attendanceMapper.getEarlyQuitByYear(year, adminId);
        return RespBean.success("查询成功",earlyQuitByYear);
    }

    @Override
    public RespBean queryAbsentByMonth(int year, int month) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> absentByMonth = attendanceMapper.getAbsentByMonth(year,month, adminId);
        return RespBean.success("查询成功",absentByMonth);
    }

    @Override
    public RespBean queryLeaveByMonth(int year, int month) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> leaveByMonth = attendanceMapper.getLeaveByMonth(year,month, adminId);
        return RespBean.success("查询成功",leaveByMonth);
    }

    @Override
    public RespBean queryEarlyQuitByMonth(int year, int month) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> earlyQuitByMonth = attendanceMapper.getEarlyQuitByMonth(year,month, adminId);
        return RespBean.success("查询成功",earlyQuitByMonth);
    }

    /**
     * @param year:
     * @return RespBean
     * @author smy
     * @description 获取员工一年的上下班的最早和最晚时间
     * @date 2022/2/23 11:01
     */
    @Override
    public RespBean queryAttendanceRecord(int year) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        QueryWrapper<AttendanceRecord> wrapper=new QueryWrapper<>();
        wrapper.eq("attendance_type",2);
        wrapper.orderByDesc("attendance_time");
        wrapper.eq("employee_id",adminId);
        Page<AttendanceRecord> page=new Page<>(1,0);
        List<AttendanceRecord> clockOutRecords = attendanceRecordMapper.selectList(wrapper);
        LocalTime clockOutLatestTime=clockOutRecords.get(0).getAttendanceTime();
        LocalTime clockOutEarlistTime=clockOutRecords.get(clockOutRecords.size()-1).getAttendanceTime();
        wrapper=new QueryWrapper<>();
        wrapper.eq("attendance_type",1);
        wrapper.orderByDesc("attendance_time");
        wrapper.eq("employee_id",adminId);
        List<AttendanceRecord> clockInRecords = attendanceRecordMapper.selectList(wrapper);
        LocalTime clockInLatestTime=clockInRecords.get(0).getAttendanceTime();

        LocalTime clockInEarlistTime=clockInRecords.get(clockInRecords.size()-1).getAttendanceTime();
        Map<String,LocalTime> res=new HashMap<>();
        res.put("clockOutLatestTime",clockOutLatestTime);
        res.put("clockOutEarlistTime",clockOutEarlistTime);
        res.put("clockInEarlistTime",clockInEarlistTime);
        res.put("clockInLatestTime",clockInLatestTime);
        return  RespBean.success("查询成功",res);
    }

    @Override
    public RespBean getWorkTimeByMonth(int year, int month) {
        int res=0;
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Attendance> attendanceByMonth = attendanceMapper.getAttendanceByMonth(year, month, adminId);
        int attendance = attendanceMapper.countAttendanceByMonth(year,month,adminId);
        for(Attendance a:attendanceByMonth){
            res+=a.getWorkTime();
        }
        Map<String,Float> map=new HashMap<>();
        map.put("本月工时", (float) res);
        map.put("平均工时",(float)res/(float)attendance);
        map.put("出勤天数",(float)attendance);
        return RespBean.success("查询成功",map);
    }


}
