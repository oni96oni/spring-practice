package com.myhome.web.upload.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class FileUploadDAO {
	
	@Autowired
	private SqlSession session;
	
	public int selectCount(int bid) {
		return 0;
	}

	public boolean insertData(FileUploadDTO data) {
		return false;
	}

}
