package com.myhome.web.upload.model;

public class FileUploadDTO {
	private int id;
	private int bid;
	private String fileName;
	private String location;
	private String url;
	private long fileSize;
	private String contentType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	@Override
	public String toString() {
		return "FileUploadDTO [id=" + id + ", bid=" + bid + ", fileName=" + fileName + ", location=" + location
				+ ", url=" + url + ", fileSize=" + fileSize + ", contentType=" + contentType + "]";
	}
	
	
}
