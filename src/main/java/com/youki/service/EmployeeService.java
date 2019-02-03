package com.youki.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youki.domain.Employee;



public interface EmployeeService {
	
	Employee selectByPrimaryKey(Integer empId);
	
	List<Employee> selectByLimitAndOffset(Integer offset,Integer limit);
	
	int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);
    
    Employee selectEmployeeByName(@Param("empName") String empName);
    
    int countEmps();
    
    int updateEmpById( Integer empId,Employee employee);

}
