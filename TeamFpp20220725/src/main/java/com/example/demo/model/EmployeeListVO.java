package com.example.demo.model;
import java.util.*;
public class EmployeeListVO {
    List<Employee>   employees;
    
    public EmployeeListVO() {}
	public EmployeeListVO(List<Employee> employees) {		
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
    
}
