package com.myhome.web.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.board.service.BoardService;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "boardMapper.%s";
	
	
	public List<BoardDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<BoardDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(BoardDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public BoardDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		BoardDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public boolean updateData(BoardDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean deleteStaticsData(BoardStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(BoardDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public BoardStaticsDTO selectStatics(BoardStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "selectStatics");
		BoardStaticsDTO data = session.selectOne(mapperId, staticsData);
		return data;
	}
	
	public boolean updateLike(BoardDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(BoardStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateViewCnt(BoardDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean insertStatics(BoardStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, staticsData);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(BoardStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
}
