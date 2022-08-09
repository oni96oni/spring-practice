package com.myhome.web.dept.model;

public class DeptDTO {
	private int deptId;
	private String deptName;
	private int mngId;
	private int locId;
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public int getMngId() {
		return mngId;
	}
	
	public void setMngId(int mngId) {
		this.mngId = mngId;
	}
	
	public int getLocId() {
		return locId;
	}
	
	public void setLocId(int locId) {
		this.locId = locId;
	}
	
	@Override
	public String toString() {
		return "DeptDTO [deptId=" + deptId + ", deptName=" + deptName + ", mngId=" + mngId + ", locId=" + locId + "]";
	}
}
