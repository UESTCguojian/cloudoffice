package com.guojian.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("noa_node")
@ApiModel(value="Node对象", description="")
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "节点名称")
    private String name;

    @ApiModelProperty(value = "节点描述")
    private String description;

    @ApiModelProperty(value = "创建人")
    @TableField("creater_id")
    private Integer createrId;

    @ApiModelProperty(value = "创建时间")
    @TableField("creater_name")
    private String createrName;

    @ApiModelProperty(value = "所属部门ID")
    @TableField("department_id")
    private Integer departmentId;

    @ApiModelProperty(value = "部门名称")
    @TableField("department_name")
    private String departmentName;


}
