package com.guojian.server.controller;


import com.guojian.server.pojo.Position;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IPositionService;
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
@RequestMapping("/system/config/pos")
public class PositionController
{
    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有的职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions()
    {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position)
    {
        position.setCreateDate(LocalDateTime.now());
        System.out.println(position.getId());
        if(positionService.save(position))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position)
    {
        if(positionService.updateById(position))
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除单个职位")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id)
    {
        if(positionService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean deletePositions(@RequestBody Integer[] ids)
    {
        if(positionService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
