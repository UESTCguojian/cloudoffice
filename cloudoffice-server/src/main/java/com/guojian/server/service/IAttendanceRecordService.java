package com.guojian.server.service;

import com.guojian.server.pojo.AttendanceRecord;
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
public interface IAttendanceRecordService extends IService<AttendanceRecord> {

    RespBean clockIn(String position);
    RespBean clockOut(String position);
    RespBean queryByEmployeeId(int employeeId,int size,int cur);
    RespBean queryByDate(String date);
    void setAbsent();
}
