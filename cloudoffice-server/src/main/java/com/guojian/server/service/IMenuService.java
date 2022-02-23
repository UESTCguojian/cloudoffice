package com.guojian.server.service;

import com.guojian.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
public interface IMenuService extends IService<Menu>
{
    /**
     * 根据当前在线的用户ID获取能操作的菜单
     * @return 菜单列表
     */
    List<Menu> getMenusByAdminId();

    /**
     * 返回所有的菜单以及其对应的角色
     * @return 菜单以及其对应的角色
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有的菜单
     * @return 获取到的菜单列表
     */
    List<Menu> getAllMenus();
}
