package com.youki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youki.domain.Department;
import com.youki.service.DepartmentService;
import com.youki.util.JsonMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Department管理", tags = "Department管理API")
@Controller
@RequestMapping(value="dept")
public class DepartmentConrtoller {
	@Autowired
    DepartmentService ds;
	
	@ApiOperation(value = "获取部门列表")
	@RequestMapping(value="/getDeptName", method=RequestMethod.GET)
	@ResponseBody
	public JsonMsg getDeptName() {
		List<Department> departmentList = ds.selectDeptList();
		System.out.println(departmentList);
		if(departmentList != null) {
		  return JsonMsg.success().addInfo("departmentList", departmentList);
		}
		return JsonMsg.fail();
	}

}
