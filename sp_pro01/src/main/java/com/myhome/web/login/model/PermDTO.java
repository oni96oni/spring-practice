package com.myhome.web.login.model;

public class PermDTO {
	private int empId;
	private String tableName;
	private boolean pRead;
	private boolean pAdd;
	private boolean pUpdate;
	private boolean pDelete;
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public boolean ispRead() {
		return pRead;
	}
	
	public void setpRead(boolean pRead) {
		this.pRead = pRead;
	}
	
	public boolean ispAdd() {
		return pAdd;
	}
	
	public void setpAdd(boolean pAdd) {
		this.pAdd = pAdd;
	}
	
	public boolean ispUpdate() {
		return pUpdate;
	}
	
	public void setpUpdate(boolean pUpdate) {
		this.pUpdate = pUpdate;
	}
	
	public boolean ispDelete() {
		return pDelete;
	}
	
	public void setpDelete(boolean pDelete) {
		this.pDelete = pDelete;
	}
	
	@Override
	public String toString() {
		return "PermDTO [empId=" + empId + ", tableName=" + tableName + ", pRead=" + pRead + ", pAdd=" + pAdd
				+ ", pUpdate=" + pUpdate + ", pDelete=" + pDelete + "]";
	}
}
