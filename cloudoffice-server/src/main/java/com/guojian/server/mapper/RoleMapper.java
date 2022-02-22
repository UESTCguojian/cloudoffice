package com.guojian.server.mapper;

import com.guojian.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role>
{
    /**
     * 根据用户id获取角色列表
     * @param adminId 用户id
     * @return 角色列表
     */
    List<Role> getRoles(Integer adminId);
}
