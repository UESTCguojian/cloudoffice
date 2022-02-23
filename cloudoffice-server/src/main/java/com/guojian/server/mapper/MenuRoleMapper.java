package com.guojian.server.mapper;

import com.guojian.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@Component
public interface MenuRoleMapper extends BaseMapper<MenuRole>
{
    /**
     * 插入角色和菜单的对应关系
     * @param roleId 角色的id
     * @param menuIds 菜单id的列表
     * @return 插入结果
     */
    Integer insertRecord(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);
}
