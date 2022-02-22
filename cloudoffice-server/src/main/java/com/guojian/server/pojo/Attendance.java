package com.guojian.server.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@TableName("work_attendance")
@ApiModel(value="Attendance对象", description="")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工ID")
    @TableField("employee_id")
    private Integer employeeId;

    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "工时")
    @TableField("work_time")
    private Double workTime;

    @ApiModelProperty(value = "打卡状态 1-正常打卡 2-迟到 3-早退 4-缺席 5-出差")
    @TableField("attendance_state")
    private Integer attendanceState;

    @ApiModelProperty(value = "创建时间")
    @TableField(value="create_time",fill=FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value="update_time",fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
