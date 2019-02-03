package com.youki.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youki.domain.Employee;


public interface EmployeeMapper {
	int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer empId);
    //selectEmployeeByName
    Employee selectEmployeeByName(@Param("empName") String empName);
    // 分页查询
 	List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,
             @Param("limit") Integer limit);
 	//员工总数
 	int countEmps();
    //更新
    int updateEmpById(@Param("empId") Integer empId,@Param("employee") Employee employee);
}