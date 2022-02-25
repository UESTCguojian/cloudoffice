package com.guojian.server.controller;


import com.guojian.server.pojo.PoliticsStatus;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IPoliticsStatusService;
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
@RequestMapping("/system/config/politics")
public class PoliticsStatusController
{
    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @ApiOperation(value = "获取所有的政治面貌信息")
    @GetMapping("/")
    public List<PoliticsStatus> getAllPoliticsStatuss()
    {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "添加政治面貌信息")
    @PostMapping("/")
    public RespBean addPoliticsStatus(@RequestBody PoliticsStatus politicsStatus)
    {
        if(politicsStatusService.save(politicsStatus))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新政治面貌信息")
    @PutMapping("/")
    public RespBean updatePoliticsStatus(@RequestBody PoliticsStatus politicsStatus)
    {
        if(politicsStatusService.updateById(politicsStatus))
        {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除单个政治面貌")
    @DeleteMapping("/{id}")
    public RespBean deletePoliticsStatus(@PathVariable Integer id)
    {
        if(politicsStatusService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除政治面貌")
    @DeleteMapping("/")
    public RespBean deletePoliticsStatuss(@RequestBody Integer[] ids)
    {
        if(politicsStatusService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
