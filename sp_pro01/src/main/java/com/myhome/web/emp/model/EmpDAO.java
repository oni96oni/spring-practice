package com.myhome.web.emp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.emp.service.EmpService;

@Repository
public class EmpDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpDAO.class);
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "empMapper.%s";
	
	public Map<String, Integer> checkSalaryRange(String id) {
		logger.info("checkSalaryRange(id={})", id);
		String mapId = String.format(mapper, "checkSalaryRange");
		Map<String, Integer> data = session.selectOne(mapId, id);
		return data;
	}
	
	public List<EmpDTO> selectAll() {
		logger.info("selectAll()");
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(int id, int start, int end) {
		logger.info("selectPage(id={}, start={}, end={})", id, start, end);
		String mapId = String.format(mapper, "selectPage");
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("deptId", id);
		page.put("start", start);
		page.put("end", end);
		List<EmpDTO> datas = session.selectList(mapId, page);
		return datas;
	}
	
	public int rowCount(int id) {
		logger.info("rowCount(id={})", id);
		String mapId = String.format(mapper, "rowCount");
		int count = session.selectOne(mapId, id);
		return count;
	}
	
	public EmpDetailDTO selectEmpDetail(int empId) {
		logger.info("selectEmpDetail(empId={})", empId);
		String mapId = String.format(mapper, "selectEmpDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}
	
	public boolean updateEmp(EmpDTO empsData) {
		logger.info("updateEmp(empsData={})", empsData);
		String mapId = String.format(mapper, "updateEmp");
		int result = session.update(mapId, empsData);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmpDetail(EmpDetailDTO empsDetailData) {
		logger.info("updateEmpDetail(empsDetailData={})", empsDetailData);
		String mapId = String.format(mapper, "updateEmpDetail");
		int result = session.update(mapId, empsDetailData);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean insertEmp(EmpDTO empsData) {
		logger.info("insertEmp(empsData={})", empsData);
		String mapId = String.format(mapper, "insertEmp");
		int result = session.insert(mapId, empsData);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public EmpDTO selectId(int id) {
		logger.info("selectId(id={})", id);
		String mapId = String.format(mapper, "selectId");
		EmpDTO data = session.selectOne(mapId, id);
		return data;
	}

	public boolean deleteId(int id) {
		logger.info("deleteId(id={})", id);
		String mapId = String.format(mapper, "deleteId");
		int result = session.delete(mapId, id);
		return result == 1 ? true : false;
	}
	
}
