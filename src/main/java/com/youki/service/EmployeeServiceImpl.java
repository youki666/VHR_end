package com.youki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youki.dao.EmployeeMapper;
import com.youki.domain.Employee;


@Service
public class EmployeeServiceImpl  implements EmployeeService{
   @Autowired
    EmployeeMapper em;

@Override
public Employee selectByPrimaryKey(Integer empId) {
	
	return em.selectByPrimaryKey(empId);
}

@Override
public List<Employee> selectByLimitAndOffset(Integer offset, Integer limit) {
	
	return em.selectByLimitAndOffset(offset, limit);
}

@Override
public int deleteByPrimaryKey(Integer empId) {
	
	return em.deleteByPrimaryKey(empId);
}

@Override
public int insert(Employee record) {
	
	return em.insert(record);
}

@Override
public Employee selectEmployeeByName(String empName) {
	
	return em.selectEmployeeByName(empName);
}

@Override
public int countEmps() {
	
	return em.countEmps();
}

@Override
public int updateEmpById(Integer empId, Employee employee) {
	
	return em.updateEmpById(empId, employee);
}


}
