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
@TableName("noa_process")
@ApiModel(value="Process对象", description="")
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "创建人ID")
    @TableField("creater_id")
    private Integer createrId;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("creater_name")
    private String createrName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "所属部门名称")
    @TableField("department_name")
    private String departmentName;

    @ApiModelProperty(value = "所属部门ID")
    @TableField("department_id")
    private Integer departmentId;

    @ApiModelProperty(value = "审批名称")
    private String name;

    @ApiModelProperty(value = "审批描述")
    private String description;


}
