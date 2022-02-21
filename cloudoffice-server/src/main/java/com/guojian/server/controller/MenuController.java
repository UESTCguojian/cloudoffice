package com.guojian.server.controller;


import com.guojian.server.pojo.Menu;
import com.guojian.server.service.IAdminService;
import com.guojian.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system")
public class MenuController
{
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByUserId()
    {
        return menuService.getMenusByAdminId();
    }
}
