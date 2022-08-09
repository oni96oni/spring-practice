package com.myhome.web.emp.model;

public class EmpDTO {
	private int empId;
	private String empName;
	private String email;
	private String jobName;
	private String jobId;
	private String deptName;
	private int deptId;
	private String firstName;
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = Integer.parseInt(empId);
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		String fullName[] = empName.split(" ", 2);
		if(fullName.length == 2) {
			this.firstName = fullName[0];
			this.lastName = fullName[1];
		}
		this.empName = empName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public void setDeptId(String deptId) {
		this.deptId = Integer.parseInt(deptId);
	}
	
	@Override
	public String toString() {
		return "EmpDTO [empId=" + empId + ", empName=" + empName + ", email=" + email + ", jobName=" + jobName
				+ ", jobId=" + jobId + ", deptName=" + deptName + ", deptId=" + deptId + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
}
