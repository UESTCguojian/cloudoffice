package com.guojian.server.service;

import com.guojian.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
public interface IDepartmentService extends IService<Department>
{
    /**
     * 获取当前所有的部门
     * @return 部门列表
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param department
     * @return 添加结果
     */
    RespBean addDepartment(Department department);


    /**
     * 根据ID删除部门
     * @param id 部门ID
     * @return 删除结果
     */
    RespBean deleteDepartmentById(Integer id);
}
