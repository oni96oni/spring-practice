package com.myhome.web.upload.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.upload.model.FileUploadDAO;
import com.myhome.web.upload.model.FileUploadDTO;

@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadDAO dao;
	
	@Transactional
	public int upload(MultipartFile file, FileUploadDTO data) throws Exception {
		data.setFileName(file.getOriginalFilename());
		data.setFileSize(file.getSize());
		data.setContentType(file.getContentType());
		data.setLocation(data.getLocation() + "/" + data.getFileName());
		data.setUrl(data.getLocation() + "/" + data.getFileName());
		
		int count = dao.selectCount(data.getBid());
		
		if(count >= 3) {
			// 업로드 수량 초과
			return -1;
		} 
		
		boolean result = dao.insertData(data);
		if(result) {
			try {
				file.transferTo(new File(data.getLocation() + "/" + data.getFileName()));
			} catch (Exception e) {
				throw new Exception("서버에 파일 업로드를 실패하였습니다.");
			}
			return 1;
		} else {
			// 업로드 실패
			return 0;
		}
		
	}
}
