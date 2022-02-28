package com.guojian.server.controller;


import com.guojian.server.pojo.*;
import com.guojian.server.service.INodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guojian
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/node")
@Api(tags="审批节点控制")
public class NodeController {
    @Autowired
    INodeService nodeService;


    @ApiOperation("添加审批节点")
    @PostMapping("/addNode")
    public RespBean addNode(@RequestBody Node node)
    {
        return nodeService.addNode(node);
    }
    @ApiOperation("为审批节点添加审批人")
    @PostMapping("/addEmployeeToNode")
    public RespBean addEmployeeToNode( int nodeId,@RequestParam("employeeIds") List<Integer> employeeIds)
    {
        return nodeService.addEmployeeToNode(nodeId,employeeIds);
    }
    @ApiOperation("测试")
    @PostMapping("/test")
    public RespBean test( int nodeId,@RequestParam("employeeIds") List<Integer> employeeIds)
    {
        System.out.println(nodeId);
        System.out.println("--------------------");
        for(int i:employeeIds){
            System.out.println(i);
        }
        return null;
    }
    @ApiOperation("获取本部门所有员工，用来添加节点")
    @GetMapping("/getAllEmployees")
    public RespBean getAllEmployees()
    {
        return nodeService.getAllEmployees();
    }

    @ApiOperation("获取本部门所有审批节点")
    @GetMapping("/getAllNodes")
    public RespBean getAllNodes()
    {
        return nodeService.getAllNodes();
    }

    @ApiOperation("获取指定审批节点的详细信息")
    @GetMapping("/getNodeDetails/{id}")
    public RespBean getNodeDetails(@PathVariable Integer id)
    {
        return nodeService.getNodeDetails(id);
    }

    @ApiOperation("删除审批节点")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteNode(@PathVariable Integer id)
    {
        return nodeService.deleteNodeById(id);
    }

    @ApiOperation(value = "更新审批节点信息")
    @PutMapping("/update")
    public RespBean updateNode(@RequestBody Node node)
    {
        return nodeService.updateNode(node);
    }

    @ApiOperation(value = "更新审批节点下的员工")
    @PutMapping("/updateEmployeeNode")
    public RespBean updateEmployeeNode( int nodeId,int oldEmployeeId,int newEmployeeId)
    {
        return nodeService.updateEmployeeNode(nodeId,oldEmployeeId,newEmployeeId);
    }

}
