package com.guojian.server.controller;

import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.AdminLoginParam;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 * @author guojian
 * @create 2022-02-18-2022/2/18
 */
@Api(tags = "LoginController")
@RestController
public class LoginController
{
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request)
    {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean logout()
    {
        return RespBean.success("注销成功");
    }

    @ApiOperation("获取当前用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal)
    {
        if(null == principal)
        {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }
}
