package com.guojian.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author guojian
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("work_attendance_record")
@ApiModel(value="AttendanceRecord对象", description="")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工ID")
    @TableField("employee_id")
    private Integer employeeId;

    @ApiModelProperty(value = "打卡时间")
    @TableField("attendance_time")
    private LocalTime attendanceTime;

    @ApiModelProperty(value = "打卡日期")
    @TableField("attendance_date")
    private LocalDate attendanceDate;

    @ApiModelProperty(value = "打卡类型 1-上班打卡 2-下班打卡 ")
    @TableField("attendance_type")
    private Integer attendanceType;

    @ApiModelProperty(value = "打卡地点")
    private String position;

    @ApiModelProperty(value = "是否迟到")
    @TableField("is_late")
    private Integer isLate;


}
