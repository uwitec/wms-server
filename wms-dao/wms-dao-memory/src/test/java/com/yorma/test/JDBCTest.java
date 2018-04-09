package com.yorma.test;

import com.yorma.wms.dao.api.CyclecountDao;
import com.yorma.wms.dao.memory.impl.CyclecountDaoImpl;
import com.yorma.wms.entity.Cyclecount;

public class JDBCTest {
	
	public static void main(String[] args) {
		CyclecountDao dao = new CyclecountDaoImpl();
		Cyclecount c=dao.find(1L);
		System.err.println(c);
	}

}
