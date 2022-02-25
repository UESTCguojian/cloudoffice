package com.guojian.server.controller;


import com.guojian.server.pojo.Nation;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.INationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/system/config/nation")
public class NationController 
{
    @Autowired
    private INationService nationService;

    @ApiOperation(value = "获取所有的民族信息")
    @GetMapping("/")
    public List<Nation> getAllNations()
    {
        return nationService.list();
    }

    @ApiOperation(value = "添加民族信息")
    @PostMapping("/")
    public RespBean addNation(@RequestBody Nation nation)
    {
        if(nationService.save(nation))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新民族信息")
    @PutMapping("/")
    public RespBean updateNation(@RequestBody Nation nation)
    {
        if(nationService.updateById(nation))
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除单个民族")
    @DeleteMapping("/{id}")
    public RespBean deleteNation(@PathVariable Integer id)
    {
        if(nationService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除民族")
    @DeleteMapping("/")
    public RespBean deleteNations(@RequestBody Integer[] ids)
    {
        if(nationService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
