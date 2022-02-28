package com.guojian.server.service;

import com.guojian.server.pojo.Process;
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
public interface IProcessService extends IService<Process> {

    RespBean addProcess(Process process);

    RespBean getProcessByTable();

    RespBean updateProcess(Process process);

    RespBean getAllProcesses();

    RespBean deleteProcessById(Integer id);

    RespBean getProcessDetails(Integer id);

    RespBean updateProcessNode(int processId, int oldNodeId, int newNodeId);

    RespBean addNodeToProcess( int processId,List<Integer> nodeIds);
}
