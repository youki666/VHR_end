package com.youki;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.youki.domain.Employee;
import com.youki.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VhrmsApplicationTests {

	@Autowired
	EmployeeService es;
	@Test
	public void test() {
		Employee e = es.selectByPrimaryKey(2);
		System.out.println(e.getEmpName());
	}

}

