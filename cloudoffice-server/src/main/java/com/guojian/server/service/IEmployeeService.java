package com.guojian.server.service;

import com.guojian.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guojian.server.pojo.RespBean;
import com.guojian.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guojian
 * @since 2022-02-18
 */
public interface IEmployeeService extends IService<Employee>
{
    /**
     * 获取所有员工(分页)
     * @param currentPage 当前页
     * @param size 单页查询结果数目
     * @param employee 筛选条件
     * @param beginDateScope 日期范围
     * @return 查询结果
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 添加员工
     * @param employee 员工信息
     * @return 添加结果
     */
    RespBean addEmployee(Employee employee);

    /**
     * 获取工号
     * @return 结果
     */
    RespBean maxWorkId();

    /**
     * 获取员工信息，如果有id则查询指定员工
     * @param id 员工id
     * @return 员工列表
     */
    List<Employee> getEmployee(Integer id);
}
