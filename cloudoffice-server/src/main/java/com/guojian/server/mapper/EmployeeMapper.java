package com.guojian.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guojian.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
public interface EmployeeMapper extends BaseMapper<Employee>
{
    /**
     * 分页查询所有员工
     * @param page 分页信息
     * @param employee 筛选信息
     * @param beginDateScope 入职年限的数组
     * @return 查询结果
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 获取员工信息，如果有id则查询指定员工
     * @param id 员工id
     * @return 员工列表
     */
    List<Employee> getEmployee(Integer id);

}
