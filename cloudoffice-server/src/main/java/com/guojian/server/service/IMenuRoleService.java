package com.guojian.server.service;

import com.guojian.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
public interface IMenuRoleService extends IService<MenuRole>
{
    /**
     * 更新菜单和角色的对应关系：删除原来的角色与菜单的关联关系，再重新进行添加
     * @param roleId 角色Id
     * @param menuIds 菜单Id数组
     * @return 更新是否成功
     */
    RespBean updateMenuRole(Integer roleId, Integer[] menuIds);
}
