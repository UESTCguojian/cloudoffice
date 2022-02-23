package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guojian.server.commons.AttendanceState;
import com.guojian.server.mapper.AttendanceMapper;
import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.Attendance;
import com.guojian.server.pojo.AttendanceRecord;
import com.guojian.server.mapper.AttendanceRecordMapper;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAttendanceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements IAttendanceRecordService {

    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;
    /**
     * @param position:
      * @return RespBean
     * @author smy
     * @description 上班打卡 TODO 位置判断
     * @date 2022/2/22 9:42
     */
    @Override
    public RespBean clockIn(String position) {
        //TODO  修改成获取所有员工的ID
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(adminId);
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        QueryWrapper<AttendanceRecord> wrapper=new QueryWrapper();
        wrapper.eq("employee_id",adminId);
        wrapper.eq("attendance_type",1);
        wrapper.eq("attendance_date",localDate);
        AttendanceRecord record=baseMapper.selectOne(wrapper);
        if(record!=null){
            return RespBean.error("已经上班打卡，无需重复打卡");
        }
        record=new AttendanceRecord();
        record.setEmployeeId(adminId);
        record.setAttendanceTime(localTime);
        record.setAttendanceDate(localDate);
        record.setAttendanceType(1);
        record.setPosition(position);
        record.setIsLate(0);
        System.out.println("date:"+record.getAttendanceDate());
        System.out.println("time:"+record.getAttendanceTime());
        int res=attendanceRecordMapper.insert(record);
        if(res==1) {
            return RespBean.success("上班打卡成功", null);
        }
        return RespBean.error("打卡失败，请重试");
    }

    /**
     * @param position:
      * @return RespBean
     * @author smy
     * @description 下班打卡 TODO 位置的判断
     * @date 2022/2/22 10:49
     */
    @Override
    @Transactional
    public RespBean clockOut(String position) {
        //TODO  修改成获取所有员工的ID
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(adminId);
        LocalDate date=LocalDate.now();
        QueryWrapper<AttendanceRecord> wrapper=new QueryWrapper();
        wrapper.eq("employee_id",adminId);
        wrapper.eq("attendance_type",1);
        wrapper.eq("attendance_date",date);
        AttendanceRecord record=baseMapper.selectOne(wrapper);
        if(record==null){
            return RespBean.error("还未上班，不能打卡");
        }
        LocalDateTime localDateTime=LocalDateTime.now();
        Duration duration=Duration.between(record.getAttendanceTime(),localDateTime);
        wrapper=new QueryWrapper();
        wrapper.eq("employee_id",adminId);
        wrapper.eq("attendance_type",2);
        wrapper.eq("attendance_date",date);
         record=baseMapper.selectOne(wrapper);
        if(record!=null){
            return RespBean.error("已经下班打卡，无需再次打卡");
        }

        AttendanceRecord attendanceRecord=new AttendanceRecord();
        attendanceRecord.setPosition(position);
        attendanceRecord.setAttendanceTime(LocalTime.now());
        attendanceRecord.setEmployeeId(adminId);
        attendanceRecord.setAttendanceType(2);
        attendanceRecord.setAttendanceDate(date);
        attendanceRecord.setIsLate(0);
        int insert = baseMapper.insert(attendanceRecord);
        if(insert!=1) { return RespBean.error("打卡失败，请重试"); }
        Attendance attendance =new Attendance();
        attendance.setEmployeeId(adminId);
        attendance.setDate(date);
        attendance.setWorkTime((double)duration.toHours());
        if(duration.toHours()<8){
            attendance.setAttendanceState(AttendanceState.EARLY_QUIT);
            //return RespBean.error("工时不够，不能下班");
        }else{
            attendance.setAttendanceState(AttendanceState.ATTEND);
        }
        final int insert1 = attendanceMapper.insert(attendance);
        return RespBean.success("下班打卡成功");
    }

    /**
     * @param employeeId:
      * @return RespBean
     * @author smy
     * @description 查询某员工的打卡记录 TODO 分页查询
     * @date 2022/2/22 16:17
     */
    @Override
    public RespBean queryByEmployeeId(int employeeId,int cur,int size) {
        Page<AttendanceRecord> page=new Page<>(cur,size);
        QueryWrapper<AttendanceRecord> wrapper=new QueryWrapper<>();
        wrapper.eq("employee_id",employeeId);
        Page<AttendanceRecord> page1 = attendanceRecordMapper.selectPage(page, wrapper);
       // List<AttendanceRecord> attendanceRecords = attendanceRecordMapper.selectList(wrapper);
        return RespBean.success("查询成功",page1.getRecords());
    }

    /**
     * @param date:
      * @return RespBean
     * @author smy
     * @description 查询指定日期的出勤记录
     * @date 2022/2/22 16:17
     */
    @Override
    public RespBean queryByDate(String date) {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        QueryWrapper<AttendanceRecord> wrapper=new QueryWrapper<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate =LocalDate.parse(date,fmt);

        wrapper.eq("attendance_date",localDate);
        wrapper.eq("employee_id",adminId);
        List<AttendanceRecord> attendanceRecords = attendanceRecordMapper.selectList(wrapper);
        return RespBean.success("查询成功",attendanceRecords);
    }

    /**
     * @return void
     * @author smy
     * @description TODO
     * @date 2022/2/23 15:27
     */
    @Override
    public void setAbsent() {
        //TODO 获取所有员工ID，如果今日没有下班记录，设置为缺席
    }
}
