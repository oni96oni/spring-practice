package com.myhome.web.login.vo;

public class LoginVO {

	private int empId;
	private int deptId;
	private String empName;
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Override
	public String toString() {
		return "LoginVO [empId=" + empId + ", deptId=" + deptId + ", empName=" + empName + "]";
	}
}
