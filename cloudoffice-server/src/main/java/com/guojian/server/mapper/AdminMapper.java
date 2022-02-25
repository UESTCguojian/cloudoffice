package com.guojian.server.mapper;

import com.guojian.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
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
public interface AdminMapper extends BaseMapper<Admin>
{
    /**
     * 获取所有的操作员
     * @return 操作员列表
     */
    List<Admin> getAllAdmins();
}
