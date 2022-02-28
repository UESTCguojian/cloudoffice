package com.guojian.server.controller;


import com.guojian.server.pojo.Node;
import com.guojian.server.pojo.Process;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.INodeService;
import com.guojian.server.service.IProcessService;

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
@RequestMapping("/process")
@Api(tags = "用户自定义审批流程管理")
public class ProcessController {
    @Autowired
    IProcessService processService;


    @ApiOperation("添加审批流程")
    @PostMapping("/addProcess")
    public RespBean addProcess(@RequestBody Process process)
    {
        return processService.addProcess(process);
    }
    @ApiOperation("为审批流程添加审批节点")
    @PostMapping("/addNodeToProcess")
    public RespBean addNodeToProcess(  int processId,@RequestParam("employeeIds") List<Integer> nodeIds)
    {
        return processService.addNodeToProcess(processId,nodeIds);

    }


    @ApiOperation("根据审批单获取审批流程")
    @GetMapping("/getProcessByTable")
    public RespBean getProcessByTable()
    {
        return processService.getProcessByTable();
    }
    @ApiOperation("获取本部门所有审批流程")
    @GetMapping("/getAllProcesses")
    public RespBean getAllProcesses()
    {
        return processService.getAllProcesses();
    }

    @ApiOperation("删除审批流程")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteProcess(@PathVariable Integer id)
    {
        return processService.deleteProcessById(id);
    }

    @ApiOperation(value = "更新审批流程信息")
    @PutMapping("/update")
    public RespBean updateProcess(@RequestBody Process process)
    {
        return processService.updateProcess(process);
    }

    @ApiOperation("获取指定审批流程的详细信息")
    @GetMapping("/getProcessDetails/{id}")
    public RespBean getProcessDetails(@PathVariable Integer id)
    {
        return processService.getProcessDetails(id);
    }
    @ApiOperation(value = "更新审批流程下的节点")
    @PutMapping("/updateProcessNode")
    public RespBean updateProcessNode(@RequestBody int processId,int oldNodeId,int newNodeId)
    {
        return processService.updateProcessNode(processId,oldNodeId,newNodeId);
    }
}
