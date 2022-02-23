package com.guojian.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guojian.server.pojo.MenuRole;
import com.guojian.server.mapper.MenuRoleMapper;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService
{
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    /**
     * 更新菜单和角色的对应关系：删除原来的角色与菜单的关联关系，再重新进行添加
     * @param roleId 角色Id
     * @param menuIds 菜单Id数组
     * @return 更新是否成功
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer roleId, Integer[] menuIds)
    {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", roleId));
        if(null == menuIds || menuIds.length == 0)
        {
            return RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(roleId, menuIds);
        if(menuIds.length == result)
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
