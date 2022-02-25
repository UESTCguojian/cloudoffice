package com.guojian.server.controller;


import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAdminService;
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
@RequestMapping("/system/admin")
public class AdminController
{
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有的操作员")
    @GetMapping("/")
    public List<Admin> getAllAdminWithRole()
    {
        return adminService.getAllAdminsWithRole();
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin)
    {
        if(adminService.updateById(admin))
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id)
    {
        if(adminService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新操作员的角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] roleIds)
    {
        return adminService.updateAdminRole(adminId, roleIds);
    }

}
