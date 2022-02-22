package com.guojian.server.service;

import com.guojian.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.Menu;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.pojo.Role;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
public interface IAdminService extends IService<Admin>
{
    /**
     * 登陆之后返回Token
     * @param username 用户名
     * @param password 密码
     * @param code 校验码
     * @param request 登陆请求
     * @return 登陆Token
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户类
     * @param username 用户名
     * @return 用户类
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id获取角色列表
     *
     * @param adminId 用户id
     * @return 角色列表
     */
    List<Role> getRoles(Integer adminId);
}
