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
    List<Menu> getMenusByAdminId();
}
