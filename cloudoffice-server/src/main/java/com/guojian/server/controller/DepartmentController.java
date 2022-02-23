package com.guojian.server.controller;


import com.guojian.server.pojo.Department;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IDepartmentService;
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
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/sys/basic/department")
public class DepartmentController
{
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department)
    {
        return departmentService.addDepartment(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id)
    {
        return departmentService.deleteDepartmentById(id);
    }
}
