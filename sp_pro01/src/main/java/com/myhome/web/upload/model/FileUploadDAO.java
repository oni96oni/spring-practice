package com.myhome.web.upload.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadDAO.class);
	
	@Autowired
	private SqlSession session;
	
	public int getCount(int bid) {
		logger.info("getCount(bid={})", bid);
		int res = session.selectOne("fileUploadMapper.getCount", bid);
		return res;
	}

	public boolean insertData(FileUploadDTO data) {
		logger.info("insertData(data={})", data);
		int res = session.insert("fileUploadMapper.insertData", data);
		System.out.println(res);
		return res == 1 ? true : false;
	}

	public List<FileUploadDTO> selectDatas(int bId) {
		List<FileUploadDTO> res = session.selectList("fileUploadMapper.selectDatas", bId);
		return res;
	}

}
