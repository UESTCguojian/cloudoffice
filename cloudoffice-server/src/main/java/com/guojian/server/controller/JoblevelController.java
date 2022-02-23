package com.guojian.server.controller;


import com.guojian.server.pojo.Joblevel;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@RequestMapping("/system/config/JobLevel")
public class JoblevelController
{
    @Autowired
    private IJoblevelService jobLevelService;

    @ApiOperation(value = "获取所有职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels()
    {
        return jobLevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel jobLevel)
    {
        jobLevel.setCreateDate(LocalDateTime.now());
        if(jobLevelService.save(jobLevel))
        {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "修改职称信息")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel jobLevel)
    {
        if(jobLevelService.updateById(jobLevel))
        {
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id)
    {
        if(jobLevelService.removeById(id))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public RespBean deleteJobLevels(@RequestBody Integer[] ids)
    {
        if(jobLevelService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
