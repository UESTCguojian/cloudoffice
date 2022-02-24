/**
 * @projectName: cloudoffice
 * @package: com.guojian.server.commons
 * @className: ScheduledTask
 * @author: Substatham
 * @date: 2022/2/23 15:06
 * @version: 1.0
 */

package com.guojian.server.commons;


import com.guojian.server.service.IAttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    IAttendanceRecordService attendanceRecordService;

    @Scheduled(cron = "0 0 23 * * ? ")
    public void setAbsent(){
        attendanceRecordService.setAbsent();
    }
}
