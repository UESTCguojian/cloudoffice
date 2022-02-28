package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guojian.server.mapper.EmployeeMapper;
import com.guojian.server.mapper.NodeMapper;
import com.guojian.server.mapper.ProcessNodeMapper;
import com.guojian.server.pojo.*;
import com.guojian.server.mapper.ProcessMapper;
import com.guojian.server.pojo.Process;
import com.guojian.server.service.INodeService;
import com.guojian.server.service.IProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guojian.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-27
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements IProcessService {

    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private ProcessNodeMapper processNodeMapper;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private INodeService nodeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    /*
    *
     * @Author Substatham
     * @Description //TODO
     * @Date 14:15 2022/2/28
     * @Param [process, nodeIds]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    @Transactional
    public RespBean addProcess(Process process) {
        Admin admin=AdminUtils.getCurrentAdmin();
        Integer departmentId = getCurrentDepartmentId();
        process.setDepartmentId(departmentId);
        process.setCreateTime(LocalDateTime.now());
        process.setCreaterId(admin.getId());
        process.setCreaterName(admin.getName());
        processMapper.insert(process);

//        int index=1;
//        for(Integer id:nodeIds){
//            ProcessNode processNode = new ProcessNode();
//            processNode.setNodeId(id);
//            processNode.setProcessId(process.getId());
//            processNode.setNumber(index++);
//            processNodeMapper.insert(processNode);
//        }
        return RespBean.success("新建流程成功");
    }

    @Override
    public RespBean getProcessByTable() {
        return null;
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO
     * @Date 14:17 2022/2/28
     * @Param [process]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    public RespBean updateProcess(Process process) {
        if(processMapper.updateById(process)>0){
            return RespBean.success("修改流程成功");
        }
        return RespBean.error("修改流程失败");

    }
    /*
    *
     * @Author Substatham
     * @Description //TODO
     * @Date 14:19 2022/2/28
     * @Param []
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    public RespBean getAllProcesses() {
        Integer departmentId =getCurrentDepartmentId();
        QueryWrapper<Process> wrapper=new QueryWrapper<>();
        wrapper.eq("department_id",departmentId);
        List<Process> processList = processMapper.selectList(wrapper);
        return RespBean.success("查询本部门流程成功",processList);
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO 判断当前是否有审批表使用该流程，如果有，不能删除
     * @Date 14:26 2022/2/28
     * @Param [id]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    @Transactional
    public RespBean deleteProcessById(Integer id) {

        QueryWrapper<ProcessNode> wrapper=new QueryWrapper<>();
        wrapper.eq("process_id",id);
        processNodeMapper.delete(wrapper);
        processMapper.deleteById(id);
        return RespBean.success("删除审批流程成功");
    }
/*
*
 * @Author Substatham
 * @Description //TODO 
 * @Date 14:50 2022/2/28
 * @Param [id]
 * @return com.guojian.server.pojo.RespBean
 **/
    @Override
    public RespBean getProcessDetails(Integer id) {
        Process process = processMapper.selectById(id);
        QueryWrapper<ProcessNode> wrapper=new QueryWrapper<>();
        wrapper.eq("process_id",id);
        List<ProcessNode> processNodes = processNodeMapper.selectList(wrapper);
        List<Node> nodeList=new ArrayList<>();
        HashMap<String,Object> res=new HashMap<>();
        res.put("流程信息",process);
        String key="节点";
        int index=1;
        for(ProcessNode processNode:processNodes){
            RespBean nodeDetails = nodeService.getNodeDetails(processNode.getNodeId());
            res.put(key+index++,nodeDetails);
        }
        return RespBean.success("查询流程详情成功",res);
    }
/*
*
 * @Author Substatham
 * @Description //TODO 
 * @Date 14:49 2022/2/28
 * @Param [processId, oldNodeId, newNodeId]
 * @return com.guojian.server.pojo.RespBean
 **/
    @Override
    public RespBean updateProcessNode(int processId, int oldNodeId, int newNodeId) {
        QueryWrapper<ProcessNode> wrapper=new QueryWrapper<>();
        wrapper.eq("process_id",processId).eq("node_id",oldNodeId);
        ProcessNode processNode = processNodeMapper.selectOne(wrapper);
        processNode.setNodeId(newNodeId);
        processNodeMapper.updateById(processNode);
        return RespBean.success("流程节点映射修改成功");
    }
/*
*
 * @Author Substatham
 * @Description //TODO 增加逻辑判断，不能重复添加
 * @Date 17:05 2022/2/28
 * @Param [processId, nodeIds]
 * @return com.guojian.server.pojo.RespBean
 **/
    @Override
    public RespBean addNodeToProcess( int processId,List<Integer> nodeIds) {

        int index=1;
        for(Integer id:nodeIds){
            ProcessNode processNode = new ProcessNode();
            processNode.setNodeId(id);
            processNode.setProcessId(processId);
            processNode.setNumber(index++);
            processNodeMapper.insert(processNode);
        }
        return RespBean.success("流程节点映射添加成功");
    }

    private Integer getCurrentDepartmentId(){
        Admin admin = AdminUtils.getCurrentAdmin();
        Employee employee = employeeMapper.selectById(admin.getEmployeeId());
        Integer departmentId = employee.getDepartmentId();
        return departmentId;
    }
}
