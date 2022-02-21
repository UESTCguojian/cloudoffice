package com.guojian.server.service;

import com.guojian.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.Menu;
import com.guojian.server.pojo.RespBean;
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
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户类
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
