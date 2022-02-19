package com.guojian.server.service;

import com.guojian.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

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
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户类
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
