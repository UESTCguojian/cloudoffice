package com.guojian.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("noa_notice")
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "审批表id")
    @TableField("table_id")
    private Integer tableId;

    @ApiModelProperty(value = "员工id")
    @TableField("employee_id")
    private Integer employeeId;

    @ApiModelProperty(value = "处理人id")
    @TableField("handler_id")
    private Integer handlerId;

    @ApiModelProperty(value = "处理结果")
    @TableField("handler_result")
    private Integer handlerResult;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "处理时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "是否完成")
    @TableField("is_finish")
    private Integer isFinish;

    @ApiModelProperty(value = "处理意见")
    @TableField("handle_reason")
    private String handleReason;


}
