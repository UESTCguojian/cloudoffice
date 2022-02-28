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
@TableName("noa_table")
@ApiModel(value="Table对象", description="")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "审批表流程id")
    @TableField("process_id")
    private Integer processId;

    @ApiModelProperty(value = "审批表内容id")
    @TableField("content_id")
    private Integer contentId;

    @ApiModelProperty(value = "员工id")
    @TableField("employee_id")
    private Integer employeeId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态")
    private Integer state;


}
