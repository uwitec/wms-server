package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.StockTransferListDao;
import com.yorma.wms.dao.memory.impl.StockTransferListDaoImpl;

public class StockTransferListServiceImplZbus extends StockTransferListServiceImpl {

	private StockTransferListDao stockTransferListDao = new StockTransferListDaoImpl();
	
	public StockTransferListServiceImplZbus() {
		super.setStockTransferListDao(stockTransferListDao);
	}
	
}
