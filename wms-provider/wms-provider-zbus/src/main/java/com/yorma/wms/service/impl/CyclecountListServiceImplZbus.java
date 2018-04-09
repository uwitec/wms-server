package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.CyclecountListDao;
import com.yorma.wms.dao.memory.impl.CyclecountListDaoImpl;

public class CyclecountListServiceImplZbus extends CyclecountListServiceImpl {

	
	private CyclecountListDao cyclecountListDao = new CyclecountListDaoImpl();
	
	public CyclecountListServiceImplZbus() {
			this.setCyclecountDao(cyclecountListDao);
	}
	
}
