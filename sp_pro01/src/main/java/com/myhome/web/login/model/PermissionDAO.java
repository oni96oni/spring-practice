package com.myhome.web.login.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDAO {
	
	@Autowired
	private SqlSession session;
	
	public boolean selectData(PermDTO data) {
		PermDTO res = session.selectOne("permissionMapper.selectData", data);
		
		if(res != null) {
			data.setpRead(res.ispRead());
			data.setpAdd(res.ispAdd());
			data.setpUpdate(res.ispUpdate());
			data.setpDelete(res.ispDelete());
			return true;
		} else {
			return false;
		}
		
	}
}
