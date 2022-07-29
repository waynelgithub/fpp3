package com.example.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeListVO;

import java.util.*;
@Controller
//@RequestMapping("/ngemployee")
public class MyEmployeeControllerTestJsp {	
	EmployeeListVO  vo;
	
	
	public MyEmployeeControllerTestJsp() {
		Employee empOne = new Employee(1,"Lokesh","Gupta","howtodoinjava@gmail.com");
	    Employee empTwo = new Employee(2,"Amit","Singhal","asinghal@yahoo.com");
	    Employee empThree = new Employee(3,"Kirti","Mishra","kmishra@gmail.com");
	    List<Employee> data=new ArrayList<>();
	    data.add(empOne);data.add(empTwo);data.add(empThree);
	    vo=new EmployeeListVO(data);
		
	}
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String mainEmployee(ModelMap model) {	
		return "jsp/mainEmployee2";
	}
	@RequestMapping(value="/employees",method = RequestMethod.GET)
	public @ResponseBody EmployeeListVO getAllEmployee(ModelMap model) {

		return vo;
	}
	@RequestMapping(value="/employees/{id}",method = RequestMethod.PUT)
	public @ResponseBody BodyBuilder editEmployee(
			@PathVariable("id") int id, @RequestBody Employee employee) {
		
		List<Employee> le =vo.getEmployees();
		int updateStatus=0;
		
		for(int i =0; i<le.size();i++)
		{
			if(le.get(i).getId() == id) {
				le.set(i, employee);
				updateStatus++;
				break;
			}
		}
		if (updateStatus >0)
		{
			vo.setEmployees(le);
			
			return ResponseEntity.status(HttpStatus.OK);
		}
	    
		return ResponseEntity.status(HttpStatus.BAD_REQUEST);
		
	}
	
//	@RequestMapping(value="/employees/{id}",method = RequestMethod.PUT)
//	public ModelAndView editEmployee2(
//			@PathVariable("id") int id, @RequestBody Employee employee) {
//		
//		List<Employee> le =vo.getEmployees();
//		int updateStatus=0;
//		
//		for(int i =0; i<le.size();i++)
//		{
//			if(le.get(i).getId() == id) {
//				le.set(i, employee);
//				updateStatus++;
//				break;
//			}
//		}
//		if (updateStatus >0)
//		{
//			vo.setEmployees(le);
//			
//			//return ResponseEntity.status(HttpStatus.OK);
//			return new ModelAndView();
//		}
//	    
//		//return ResponseEntity.status(HttpStatus.BAD_REQUEST);
//		return new ModelAndView();
//		
//	}
	
}
