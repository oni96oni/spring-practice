package com.myhome.web.dept.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO {

	@Autowired
	private SqlSession session;
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	
	public List<DeptDTO> searchPage(int start, int end) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	public List<DeptDTO> searchPage(int start, int end, String sort) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		
		switch(sort) {
			case "deptId":
				page.put("sort", 1); break;
			case "deptName":
				page.put("sort", 2); break;
			case "mngId":
				page.put("sort", 3); break;
			case "locId":
				page.put("sort", 4); break;
		}
		
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	public int rowCount() {
		int count = session.selectOne("deptMapper.deptRowCount");
		return count;
	}
	
	public DeptDTO searchDeptId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public boolean insertDept(DeptDTO data) {
		int result = session.insert("deptMapper.deptInsert", data);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateDept(DeptDTO data) {
		// 업데이트 용 맵퍼와 SQL 구문을 작성하여 이 메서드가 동작하게 한다.
		int result = session.update("deptMapper.deptUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean existManager(int id) {
		int result = session.selectOne("deptMapper.existManager", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean existLocation(int id) {
		int result = session.selectOne("deptMapper.existLocation", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}

}
