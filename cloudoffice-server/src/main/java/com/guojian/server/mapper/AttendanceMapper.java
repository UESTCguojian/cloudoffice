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

}
