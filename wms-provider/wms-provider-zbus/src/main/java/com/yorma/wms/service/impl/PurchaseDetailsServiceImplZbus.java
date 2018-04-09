package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.PurchaseDetailsDao;
import com.yorma.wms.dao.memory.impl.PurchaseDetailsDaoImpl;

public class PurchaseDetailsServiceImplZbus extends PurchaseDetailsServiceImpl {

	
	private PurchaseDetailsDao purchaseDetailsDao = new PurchaseDetailsDaoImpl();
	
	public PurchaseDetailsServiceImplZbus() {
		this.setPurchaseDetailsDao(purchaseDetailsDao);
	}
	
}
