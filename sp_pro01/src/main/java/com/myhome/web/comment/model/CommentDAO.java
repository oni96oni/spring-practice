package com.myhome.web.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.comment.controller.CommentController;

@Repository
public class CommentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentDAO.class);
	
	@Autowired
	private SqlSession session;
	
	public boolean insertData(CommentDTO data) {
		logger.info("insertData(data={})", data);
		String mapId = String.format("commentMapper.%s", "insertData");
		int res = session.insert(mapId, data);
		return res == 1 ? true : false;
	}
	

	public CommentDTO selectData(int id) {
		logger.info("selectData(id={})", id);
		String mapId = String.format("commentMapper.%s", "selectData");
		CommentDTO res = session.selectOne(mapId, id);
		return res;
	}

	public List<CommentDTO> selectDatas(int id) {
		logger.info("selectDatas(id={})", id);
		String mapId = String.format("commentMapper.%s", "selectDatas");
		List<CommentDTO> res = session.selectList(mapId, id);
		return res;
	}
	
	public boolean deleteData(CommentDTO data) {
		logger.info("deleteData(data={})", data);
		String mapId = String.format("commentMapper.%s", "deleteData");
		int res = session.update(mapId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(CommentDTO data) {
		logger.info("updateData(data={})", data);
		String mapId = String.format("commentMapper.%s", "updateData");
		int res = session.update(mapId, data);
		return res == 1 ? true : false;
	}
	
}