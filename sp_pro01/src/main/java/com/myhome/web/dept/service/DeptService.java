package com.myhome.web.dept.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.dept.model.DeptDAO;
import com.myhome.web.dept.model.DeptDTO;

@Service
public class DeptService {
	
	@Autowired
	private DeptDAO dao;
	
	public List<DeptDTO> getAll() {
		List<DeptDTO> datas = dao.searchAll();
		return datas;
	}
	
	public List<DeptDTO> getPage(int pageNumber) {
		int start = (pageNumber - 1) * 10 + 1;
		int end = start + 9;
		
		List<DeptDTO> datas = dao.searchPage(start, end);
		return datas;
	}
	
	public List<DeptDTO> getPage(int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		List<DeptDTO> datas = dao.searchPage(start, end);
		return datas;
	}
	
	public List<DeptDTO> getPage(int pageNumber, int count, String sort) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		List<DeptDTO> datas = dao.searchPage(start, end, sort);
		return datas;
	}
	
	public List<Integer> getPageNumberList() {
		int rowCount = dao.rowCount();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / 10;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public List<Integer> getPageNumberList(int count) {
		int rowCount = dao.rowCount();
		// 여기에 페이지 번호를 가지는 리스트를 만든다.
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / count;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public DeptDTO getDeptId(String id) {
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int deptId = Integer.parseInt(id);
			DeptDTO data = _getDeptId(deptId);
			return data;
		}
		return null;
	}
	
	public DeptDTO getDeptId(int id) {
		DeptDTO data = _getDeptId(id);
		return data;
	}
	
	private DeptDTO _getDeptId(int id) {
		// public 메서드에서 dao 객체를 생성하게 해야함.
		DeptDTO data = dao.searchDeptId(id);
		return data;
	}
	
	/*
	public DEPT_SERVICE_STATUS addDept(DeptDTO data) {
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		if(_getDeptId(data.getDeptId()) != null) {
			status = DEPT_SERVICE_STATUS.DEPT_ID_DUPLICATED;
		}
		if(!_existManager(data.getMngId())) {
			status = DEPT_SERVICE_STATUS.MNG_ID_NOT_EXISTS;
		}
		if(!_existLocation(data.getLocId())) {
			status = DEPT_SERVICE_STATUS.LOC_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.insertDept(data)) {
					dao.commit();
				} else {
					status = DEPT_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
		}
		
		return status;
	}
	
	public boolean existsManager(String id) {
		boolean result = _existManager(Integer.parseInt(id));
		return result;
	}
	
	public boolean existsLocation(String id) {
		boolean result = _existLocation(Integer.parseInt(id));
		return result;
	}
	
	private boolean _existManager(int id) {
		return dao.existManager(id);
	}
	
	private boolean _existLocation(int id) {
		return dao.existLocation(id);
	}

	public DEPT_SERVICE_STATUS modifyDept(DeptDTO data) {
		// addDept 를 구현한 것과 유사하게 수정한 데이터에 대해
		// 문제가 발생한 경우 상세 오류를 구분할 수 있게 만들고
		// Controller 에 반환할 수 있게 한다.
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		if(!_existManager(data.getMngId())) {
			status = DEPT_SERVICE_STATUS.MNG_ID_NOT_EXISTS;
		}
		if(!_existLocation(data.getLocId())) {
			status = DEPT_SERVICE_STATUS.LOC_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.updateDept(data)) {
					dao.commit();
				} else {
					status = DEPT_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
		}
		
		return status;
	}

	public DEPT_SERVICE_STATUS deleteDept(String id) {
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		if(_getDeptId(Integer.parseInt(id)) == null) {
			status = DEPT_SERVICE_STATUS.DEPT_ID_NOT_EXISTS;
		}
		
		boolean result = dao.deleteDept(Integer.parseInt(id));
		if(result) {
			dao.commit();
		} else {
			status = DEPT_SERVICE_STATUS.FAILED;
			dao.rollback();
		}
		
		return status;
	}
	*/
}
