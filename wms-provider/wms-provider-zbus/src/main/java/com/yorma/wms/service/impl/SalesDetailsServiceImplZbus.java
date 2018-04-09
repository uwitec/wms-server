package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.SalesDetailsDao;
import com.yorma.wms.dao.memory.impl.SalesDetailsDaoImpl;

public class SalesDetailsServiceImplZbus extends SalesDetailsServiceImpl {

	private SalesDetailsDao salesDetailsDao = new SalesDetailsDaoImpl();
	
	public SalesDetailsServiceImplZbus() {
		this.setSalesDetailsDao(salesDetailsDao);
	}
	
}
