package com.guojian.server.mapper;

import com.guojian.server.pojo.AdminRole;
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
public interface AdminRoleMapper extends BaseMapper<AdminRole>
{
    /**
     * 为操作员添加角色
     * @param adminId 操作员Id
     * @param roleIds 角色Id数组
     * @return 成功插入的数目
     */
    Integer addRolesForAdmin(@Param("adminId") Integer adminId, @Param("roleIds") Integer[] roleIds);
}
