package com.myhome.web.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.upload.model.FileUploadDAO;
import com.myhome.web.upload.model.FileUploadDTO;

@Service
public class FileUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	@Autowired
	private FileUploadDAO dao;
	
	@Transactional
	public int upload(MultipartFile file, FileUploadDTO data) throws Exception {
		logger.info("upload(file={}, data={})", file, data);
		
		File folder = new File(data.getLocation());
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		UUID uuid = UUID.nameUUIDFromBytes(file.getBytes());
		
		data.setFileName(file.getOriginalFilename());
		data.setUuidName(uuid.toString());
		data.setFileSize((int)file.getSize());
		data.setContentType(file.getContentType());
		
		int count = dao.getCount(data.getbId());
		
		if(count >= 3) {
			// 업로드 수량 초과.
			return -1;
		}
		
		boolean result = dao.insertData(data);
		if(result) {
			try {
				file.transferTo(new File(data.getLocation() + File.separatorChar + data.getUuidName()));
				return 1;
			} catch (IOException e) {
				throw new Exception("서버에 파일 업로드를 실패하였습니다.");
			}
		} else {
			// 업로드 실패
			return 0;
		}
	}

	public List<FileUploadDTO> getDatas(int bId) {
		List<FileUploadDTO> datas = dao.selectDatas(bId);
		return datas;
	}
}
