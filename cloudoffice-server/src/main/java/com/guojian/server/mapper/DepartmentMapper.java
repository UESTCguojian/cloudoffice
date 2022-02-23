package com.guojian.server.mapper;

import com.guojian.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Component
public interface DepartmentMapper extends BaseMapper<Department>
{
    /**
     * 获取目标ID部门下所有的子部门列表
     * @return 部门列表
     */
    List<Department> getAllDepartmentsById(Integer id);

    /**
     * 根据ID查询部门信息
     * @param id
     * @return
     */
    Department getDepartmentById(int id);
}
