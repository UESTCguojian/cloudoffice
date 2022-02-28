package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.guojian.server.mapper.EmployeeMapper;
import com.guojian.server.mapper.EmployeeNodeMapper;
import com.guojian.server.mapper.ProcessNodeMapper;
import com.guojian.server.pojo.*;
import com.guojian.server.mapper.NodeMapper;
import com.guojian.server.service.IEmployeeService;
import com.guojian.server.service.INodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guojian.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {

    @Autowired
    private NodeMapper nodemapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeNodeMapper employeenodeMapper;
    @Autowired
    private ProcessNodeMapper processNodeMapper;
   /*
   *
    * @Author Substatham
    * @Description //TODO
    * @Date 16:44 2022/2/27
    * @Param [node, adminIds]
    * @return com.guojian.server.pojo.RespBean
    **/
    @Override
    @Transactional
    public RespBean addNode(Node node) {
        Integer departmentId = getCurrentDepartmentId();
        node.setDepartmentId(departmentId);
        //将节点加入数据库
        nodemapper.insert(node);

//        //将节点和用户的映射加入数据库
//        for(Integer id : adminIds ){
//            EmployeeNode employeeNode = new EmployeeNode();
//            employeeNode.setEmployeeId(id);
//            employeeNode.setNodeId(node.getId());
//            employeenodeMapper.insert(employeeNode);
//        }
        return RespBean.success("新建审批节点成功");
    }
/*
*
 * @Author Substatham
 * @Description //TODO 
 * @Date 15:42 2022/2/27
 * @Param []
 * @return com.guojian.server.pojo.RespBean
 **/
    @Override
    public RespBean getAllNodes() {
        Admin admin = AdminUtils.getCurrentAdmin();
        Employee employee = employeeMapper.selectById(admin.getEmployeeId());
        Integer departmentId = employee.getDepartmentId();
        System.out.println(departmentId);
        QueryWrapper<Node> wrapper=new QueryWrapper<>();
        wrapper.eq("department_id",departmentId);
        List<Node> nodes = nodemapper.selectList(wrapper);
        return RespBean.success("查询本部门审批节点成功",nodes);
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO 只有当前节点没有流程用到时才能删除，删除节点时也要删除映射表
     * @Date 15:45 2022/2/27
     * @Param [id]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    @Transactional
    public RespBean deleteNodeById(Integer id) {
        QueryWrapper<ProcessNode> wq=new QueryWrapper<>();
        wq.eq("node_id",id);
        List<ProcessNode> processNodes = processNodeMapper.selectList(wq);
        if(processNodes.size()>0){
            return RespBean.error("当前节点已被这些流程使用，不能进行删除",processNodes);
        }
        QueryWrapper<EmployeeNode> wrapper=new QueryWrapper<>();
        wrapper.eq("node_id",id);
        employeenodeMapper.delete(wrapper);
       nodemapper.deleteById(id);
        return RespBean.success("删除审批节点成功");
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO  对前端传来的值进行校验，空值或者零值不更新
     * @Date 15:45 2022/2/27
     * @Param [node]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    public RespBean updateNode(Node node) {
//        UpdateWrapper<Node> wrapper=new UpdateWrapper<>();
//        if(node.getDepartmentId()!=0){
//            wrapper.set("department_id",node.getDepartmentId());
//        }
//        if(node.getCreaterName()!=null){
//            wrapper.set("creater_name",node.getCreaterName());
//        }
        if(nodemapper.updateById(node)>0){
            return RespBean.success("修改审批节点成功");
        }
        return RespBean.error("修改审批节点失败");
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO
     * @Date 16:47 2022/2/27
     * @Param []
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    public RespBean getAllEmployees() {
        int departmentId = getCurrentDepartmentId();
        QueryWrapper<Employee> wrapper=new QueryWrapper<>();
        wrapper.eq("departmentId",departmentId);
        List<Employee> employees = employeeMapper.selectList(wrapper);
        return RespBean.success("查询部门所有员工成功",employees);
    }

    /*
    *
     * @Author Substatham
     * @Description //TODO
     * @Date 20:09 2022/2/27
     * @Param [NodeId]
     * @return com.guojian.server.pojo.RespBean
     **/
    @Override
    public RespBean getNodeDetails(Integer NodeId) {
        Node node = nodemapper.selectById(NodeId);
        QueryWrapper<EmployeeNode> wrapper=new QueryWrapper<>();
        wrapper.eq("node_id",NodeId);
        List<EmployeeNode> employeeNodes = employeenodeMapper.selectList(wrapper);
        List<Employee> employeeList=new ArrayList<>();
        for(EmployeeNode employeeNode:employeeNodes){
            Employee employee = employeeMapper.selectById(employeeNode.getEmployeeId());
            employeeList.add(employee);
        }
        HashMap<String, Object> res=new HashMap<>();
        res.put("节点信息",node);
        res.put("审批人信息",employeeList);
        return RespBean.success("查询节点详情成功",res);
    }

    @Override
    public RespBean updateEmployeeNode(int nodeId, int oldEmployeeId, int newEmployeeId) {
        QueryWrapper<EmployeeNode> wrapper=new QueryWrapper<>();
        wrapper.eq("node_id",nodeId).eq("employee_id",oldEmployeeId);
        EmployeeNode employeeNode = employeenodeMapper.selectOne(wrapper);
        employeeNode.setEmployeeId(newEmployeeId);
        employeenodeMapper.updateById(employeeNode);
        return RespBean.success("节点员工映射修改成功");
    }

    @Override
    public RespBean addEmployeeToNode(int nodeId, List<Integer> employeeIds) {
                //将节点和用户的映射加入数据库
        for(Integer id :employeeIds ){
            EmployeeNode employeeNode = new EmployeeNode();
            employeeNode.setEmployeeId(id);
            employeeNode.setNodeId(nodeId);
            employeenodeMapper.insert(employeeNode);
        }

        return RespBean.success("节点员工映射建立成功");
    }

    private Integer getCurrentDepartmentId(){
        Admin admin = AdminUtils.getCurrentAdmin();
        Employee employee = employeeMapper.selectById(admin.getEmployeeId());
        Integer departmentId = employee.getDepartmentId();
        return departmentId;
    }
}
