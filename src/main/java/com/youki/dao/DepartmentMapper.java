package com.youki.dao;

import java.util.List;

import com.youki.domain.Department;

public interface DepartmentMapper {
	
	 //selectDeptList
    List<Department> selectDeptList();

    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}