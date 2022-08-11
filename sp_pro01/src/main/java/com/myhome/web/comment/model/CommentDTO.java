package com.myhome.web.comment.model;

import java.sql.Date;

public class CommentDTO {
	private int id;
	private int bId;
	private int empId;
	private String empName;
	private String content;
	private Date createDate;
	private boolean deleted;
	private boolean hidden;
	private int likeCnt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getbId() {
		return bId;
	}
	
	public void setbId(int bId) {
		this.bId = bId;
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public int getLikeCnt() {
		return likeCnt;
	}
	
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", bId=" + bId + ", empId=" + empId + ", empName=" + empName + ", content="
				+ content + ", createDate=" + createDate + ", deleted=" + deleted + ", hidden=" + hidden + ", likeCnt="
				+ likeCnt + "]";
	}
	
}
