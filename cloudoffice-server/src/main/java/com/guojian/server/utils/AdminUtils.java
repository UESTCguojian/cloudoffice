package com.guojian.server.utils;

import com.guojian.server.mapper.EmployeeMapper;
import com.guojian.server.pojo.Admin;
import com.guojian.server.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 *
 * @author guojian
 * @since 1.0.0
 */
public class AdminUtils {
//	@Autowired
//	static private EmployeeMapper employeeMapper;
	/**
	 * 获取当前登录操作员
	 *
	 * @return
	 */
	public static Admin getCurrentAdmin() {
		return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

//	public static Integer getCurrentDepartmentId() {
//		Admin admin=getCurrentAdmin();
//		System.out.println(admin.getId());
//		Employee employee = employeeMapper.selectById(admin.getEmployeeId());
//		return employee.getDepartmentId();
//	}

}