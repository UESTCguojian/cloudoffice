package com.guojian.server.mapper;

import com.guojian.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Component
public interface MenuMapper extends BaseMapper<Menu>
{
    /**
     * 根据用户id获取菜单列表
     * @param id 用户id
     * @return 菜单列表
     */
    List<Menu> getMenusByAdminId(Integer id);


    /**
     * 获取当前所有的菜单
     * @return 菜单列表
     */
    List<Menu> getAllMenus();

    /**
     * 返回所有的菜单以及其对应的角色
     * @return 菜单以及其对应的角色
     */
    List<Menu> getAllMenusWithRole();


}
