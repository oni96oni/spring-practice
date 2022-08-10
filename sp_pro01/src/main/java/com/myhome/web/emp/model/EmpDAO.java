package com.myhome.web.emp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	@Autowired
	private SqlSession session;
	
	/* 
	private String mapper = "empMapper.%s";
	public Map<String, Integer> checkSalaryRange(String id) {
		String mapId = String.format(mapper, "checkSalaryRange");
		Map<String, Integer> data = session.selectOne(mapId, id);
		return data;
	}
	
	public List<EmpDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(int id, int start, int end) {
		String mapId = String.format(mapper, "selectPage");
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("deptId", id);
		page.put("start", start);
		page.put("end", end);
		List<EmpDTO> datas = session.selectList(mapId, page);
		return datas;
	}
	
	public int rowCount(int id) {
		String mapId = String.format(mapper, "rowCount");
		int count = session.selectOne(mapId, id);
		return count;
	}
	
	public EmpDetailDTO selectEmpDetail(int empId) {
		String mapId = String.format(mapper, "selectEmpDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}
	
	public boolean updateEmp(EmpDTO empsData) {
		String mapId = String.format(mapper, "updateEmp");
		int result = session.update(mapId, empsData);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmpDetail(EmpDetailDTO empsDetailData) {
		String mapId = String.format(mapper, "updateEmpDetail");
		int result = session.update(mapId, empsDetailData);
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
	
	public boolean insertEmp(EmpDTO empsData) {
		String mapId = String.format(mapper, "insertEmp");
		int result = session.insert(mapId, empsData);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public EmpDTO selectId(int id) {
		String mapId = String.format(mapper, "selectId");
		EmpDTO data = session.selectOne(mapId, id);
		return data;
	}

	public boolean deleteId(int id) {
		String mapId = String.format(mapper, "deleteId");
		int result = session.delete(mapId, id);
		return result == 1 ? true : false;
	}
	*/
}
