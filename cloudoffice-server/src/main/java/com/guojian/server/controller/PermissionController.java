package com.guojian.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guojian.server.pojo.Menu;
import com.guojian.server.pojo.MenuRole;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.pojo.Role;
import com.guojian.server.service.IMenuRoleService;
import com.guojian.server.service.IMenuService;
import com.guojian.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guojian
 * @since 2022-02-23-2022/2/23
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController
{
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation("获取所有的角色")
    @GetMapping("/")
    public List<Role> getAllRoles()
    {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role)
    {
        if(!role.getName().startsWith("ROLE_"))
        {
            role.setName("ROLE_" + role.getName());
        }
        if(roleService.save(role))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation("修改角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role)
    {
        if(!role.getName().startsWith("ROLE_"))
        {
            role.setName("ROLE_" + role.getName());
        }
        if(roleService.updateById(role))
        {
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation("根据ID删除角色")
    @DeleteMapping("/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id)
    {
        if(roleService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("/")
    public RespBean deleteRolesByIds(@RequestBody Integer[] ids)
    {
        if(roleService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "获取所有的菜单及其子菜单")
    @GetMapping("/menu")
    public List<Menu> getAllMenus()
    {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色的ID查询其菜单的ID")
    @GetMapping("/menuIds/{roleId}")
    public List<Integer> getMenuIdsByRoleId(@PathVariable Integer roleId)
    {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", roleId))
                .stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation("更新角色菜单对应关系")
    @PutMapping("/roleMenus")
    public RespBean updateMenuRole(Integer roleId, Integer[] menuIds)
    {
        return menuRoleService.updateMenuRole(roleId, menuIds);
    }
}
