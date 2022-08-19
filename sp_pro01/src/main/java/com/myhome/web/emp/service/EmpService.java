package com.myhome.web.emp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.board.controller.BoardController;
import com.myhome.web.emp.model.EmpDAO;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.emp.model.EmpDetailDTO;

@Service
public class EmpService {
	
	@Autowired
	EmpDAO dao;
	
	public List<EmpDTO> getAll() {
		List<EmpDTO> datas = dao.selectAll();
		return datas;
	}

	public List<EmpDTO> getPage(EmpDTO data, int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		List<EmpDTO> datas = dao.selectPage(data.getDeptId(), start, end);
		
		return datas;
	}
	
	public List<Integer> getPageNumberList(EmpDTO data, int count) {
		int rowCount = dao.rowCount(data.getDeptId());
		
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / count;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public List<Integer> getPageNumberList(EmpDTO data) {
		return getPageNumberList(data, 10);
	}

	public EmpDetailDTO getEmpDetail(int empId) {
		EmpDetailDTO data = dao.selectEmpDetail(empId);
		return data;
	}

	public boolean setEmp(EmpDTO empsData, EmpDetailDTO empsDetailData) {
		boolean res1 = dao.updateEmp(empsData);
		
		// 직급에 맞는 급여 산정을 위한 코드
		int salary = _checkSalaryRange(dao, empsData, empsDetailData.getSalary());
		empsDetailData.setSalary(salary);
		
		boolean res2 = dao.updateEmpDetail(empsDetailData);
		
		if(res1 && res2) {
			return true;
		} else {
			return false;
		}
		
	}

	public boolean add(EmpDTO empsData, EmpDetailDTO empsDetailData) {
		boolean res1 = dao.insertEmp(empsData);
		
		// 직급에 맞는 급여 산정을 위한 코드
		int salary = _checkSalaryRange(dao, empsData, empsDetailData.getSalary());
		empsDetailData.setSalary(salary);
		
		boolean res2 = dao.updateEmpDetail(empsDetailData);
		
		if(res1 && res2) {
			return true;
		} else {
			return false;
		}
		
	}

	public EmpDTO getId(String id) {
		EmpDTO data = dao.selectId(Integer.parseInt(id));
		return data;
	}
	
	public String getProfileImagePath(HttpServletRequest request, String imagePath, EmpDTO empsData) {
		String realPath = request.getServletContext().getRealPath(imagePath) + empsData.getEmpId() + ".png";
		
		File file = new File(realPath);
		if(file.exists()) {
			return imagePath + empsData.getEmpId() + ".png";
		} else {
			return imagePath + "profile.png";
		}
	}
	
	public Map<String, Integer> getSalaryRange(String jobId) {
		return dao.checkSalaryRange(jobId);
	}
	
	private int _checkSalaryRange(EmpDAO dao, EmpDTO dto, int salary) {
		Map<String, Integer> salaryRange = dao.checkSalaryRange(dto.getJobId());
		
		if(salaryRange.get("minSalary") > salary) {
			return salaryRange.get("minSalary");
		} else if(salaryRange.get("maxSalary") < salary) {
			return salaryRange.get("maxSalary");
		}
		return salary;
	}

	public boolean removeId(int id) {
		boolean result = dao.deleteId(id);
		return result;
	}
	
}
