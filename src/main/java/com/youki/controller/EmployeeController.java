package com.youki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youki.domain.Employee;
import com.youki.service.EmployeeService;
import com.youki.util.JsonMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
@Api(value = "Employee管理", tags = "Employee管理API")
@Controller
@RequestMapping(value="/emp")
public class EmployeeController {
	@Autowired
    EmployeeService employeeService;
	
	@RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsg getEmpList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
		int limit = 5;
		int offset = (pageNo-1)*limit;
		List<Employee> employee = employeeService.selectByLimitAndOffset(offset, limit);
		//获取总的记录数
        int totalItems = employeeService.countEmps();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;
        Map<String, Object> msg = new HashMap<>();
        msg.put("employee", employee);
        msg.put("totalItems",totalItems);
        msg.put("temp", temp);
        msg.put("totalPages", totalPages);
        msg.put("curPage", curPage);
        
        
		return  JsonMsg.success().addInfo("msg", msg);
	}
	
	 ////检查员工姓名是否重复
	 @RequestMapping(value = "/checkEmpName", method = RequestMethod.GET)
	 @ResponseBody
	 public JsonMsg checkEmpName(@RequestParam("empName") String empName) {
		 
		//对输入的姓名与邮箱格式进行验证
	        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
	        if(!empName.matches(regName)){
	            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
	        }
	        Employee employee = employeeService.selectEmployeeByName(empName);
	       
	        if (employee != null){
	        	return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
	        }else {
	        	return JsonMsg.success();
	        }
	 }
	 
	//addEmp
		 @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
		 
			 @ResponseBody
			    public JsonMsg addEmp(Employee employee) {
			    	 int res = employeeService.insert(employee);
			         if (res == 1){
			             return JsonMsg.success();
			         }else {
			             return JsonMsg.fail();
			         }
		 }
		  /**
		     * 新增记录后，查询最新的页数
		     * @return
		     */
		    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
		    @ResponseBody
		    public JsonMsg getTotalPage(){
		        int totalItems = employeeService.countEmps();
		        //获取总的页数
		        int temp = totalItems / 5;
		        int totalPages = (totalItems % 5 == 0) ? temp : temp+1;
		        return JsonMsg.success().addInfo("totalPages", totalPages);
		    }
	
	
	    //getEmpById
	  @ApiOperation(value="通过id获取employee", notes="youki")
	  @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
	    @RequestMapping(value = "/getEmpById/{empId}", method = RequestMethod.GET)
	    @ResponseBody
	    public  JsonMsg getEmpById(@PathVariable("empId") Integer empId) {
	    	Employee employee = employeeService.selectByPrimaryKey(empId);
	    	System.out.println("=======!!!!===");
	    	System.out.println(employee);
	    	System.out.println("=======!!!!===");
	    	 if (employee != null){
	             return JsonMsg.success().addInfo("employee", employee);
	         }else {
	             return JsonMsg.fail();
	         }
	    }
	  
	  
	//updateEmp
	    @RequestMapping(value = "/updateEmp/{empId}", method = RequestMethod.PUT)
	    @ResponseBody
	    public JsonMsg updateEmp(@PathVariable("empId") Integer empId,  Employee employee){
	       
	       int res = employeeService.updateEmpById(empId, employee);
	       
	       if(res != 1) {
	    	   return JsonMsg.fail().addInfo("emp_update_error", "更改异常");
	       }
	        return JsonMsg.success();
	    }
	    /**
	     * 员工删除操作
	     * @param empId
	     * @return
	     */
	    @RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.DELETE)
	    @ResponseBody
	    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId){
	        int res = 0;
	        if (empId > 0){
	            res = employeeService.deleteByPrimaryKey(empId);
	        }
	        if (res != 1){
	            return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
	        }
	        return JsonMsg.success();
	    }
	    

}
