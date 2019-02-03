package com.youki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youki.dao.DepartmentMapper;
import com.youki.domain.Department;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
       DepartmentMapper dm;

	@Override
	public List<Department> selectDeptList() {
		
		return dm.selectDeptList();
	}
	

}
