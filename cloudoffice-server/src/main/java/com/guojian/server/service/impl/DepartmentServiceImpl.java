package com.guojian.server.service.impl;

import com.guojian.server.pojo.Department;
import com.guojian.server.mapper.DepartmentMapper;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments()
    {
        return departmentMapper.getAllDepartmentsById(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        if(null == department.getParentId())
        {
            return RespBean.error("未指定部门的上级部门，请重新添加");
        }
        if(this.getById(department.getParentId()) == null)
        {
            return RespBean.error("指定的上级部门不存在，请重新添加");
        }
        department.setEnabled(true);
        if(this.save(department))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }




    @Override
    public RespBean deleteDepartmentById(Integer id)
    {
        Department department = this.getById(id);
        if(department == null)
        {
            return RespBean.error("请指定有效的部门ID");
        }
        List<Department> children = departmentMapper.getAllDepartmentsById(id);
        // 当能查到子部门信息，且子部门大于0个时，不能删除该部门
        if(children != null && children.size() > 0)
        {
            return RespBean.error("该部门还有子部门，不能进行删除");
        }
        if (this.removeById(id))
        {
            return RespBean.success("成功删除");
        }
        return RespBean.error("删除失败");
    }
}
