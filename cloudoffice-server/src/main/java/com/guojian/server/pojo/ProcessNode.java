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
@TableName("noa_process_node")
@ApiModel(value="ProcessNode对象", description="")
public class ProcessNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "审批节点id")
    @TableField("node_id")
    private Integer nodeId;

    @ApiModelProperty(value = "审批流程id")
    @TableField("process_id")
    private Integer processId;

    @ApiModelProperty(value = "在审批流程中的顺序")
    private Integer number;


}
