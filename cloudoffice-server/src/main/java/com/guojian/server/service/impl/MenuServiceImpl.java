package com.guojian.server.service.impl;

import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.Menu;
import com.guojian.server.mapper.MenuMapper;
import com.guojian.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService
{
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据用户id获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId()
    {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        if(CollectionUtils.isEmpty(menus))
        {
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getAllMenusWithRole()
    {
        return menuMapper.getAllMenusWithRole();
    }
}
