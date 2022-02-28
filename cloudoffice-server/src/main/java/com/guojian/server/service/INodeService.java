package com.guojian.server.service;

import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.Node;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-27
 */
public interface INodeService extends IService<Node> {

    RespBean addNode(Node node);

    RespBean getAllNodes();

    RespBean deleteNodeById(Integer id);

    RespBean updateNode(Node node);

    RespBean getAllEmployees();

    RespBean getNodeDetails(Integer id);

    RespBean updateEmployeeNode(int nodeId, int oldEmployeeId, int newEmployeeId);

    RespBean addEmployeeToNode(int nodeId, List<Integer> employeeIds);
}
