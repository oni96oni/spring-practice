package com.myhome.web.comment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.comment.controller.CommentController;
import com.myhome.web.comment.model.CommentDAO;
import com.myhome.web.comment.model.CommentDTO;

@Service
public class CommentService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	private CommentDAO dao;
	
	public boolean add(CommentDTO data) {
		logger.info("add(data={})", data);
		boolean result = dao.insertData(data);
		return result;
	}
	
	public CommentDTO getData(int id) {
		logger.info("getData(id={})", id);
		CommentDTO data = dao.selectData(id);
		return data;
	}

	public List<CommentDTO> getDatas(int id) {
		logger.info("getDatas(id={})", id);
		List<CommentDTO> datas = dao.selectDatas(id);
		return datas;
	}

	public boolean remove(CommentDTO data) {
		logger.info("remove(data={})", data);
		boolean result = dao.deleteData(data);
		return result;
	}

	public boolean modify(CommentDTO data) {
		logger.info("modify(data={})", data);
		boolean result = dao.updateData(data);
		return result;
	}

}
