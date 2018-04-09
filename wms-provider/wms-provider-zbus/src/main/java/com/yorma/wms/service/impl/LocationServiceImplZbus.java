package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.LocationDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.impl.LocationDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.service.impl.LocationServiceImpl;

public class LocationServiceImplZbus extends LocationServiceImpl {

	private LocationDao locationDao=new LocationDaoImpl();
	private StockDao stockDao = new StockDaoImpl();
	
	public LocationServiceImplZbus() {
		this.setLocationDao(locationDao);
		this.setStockDao(stockDao);
	}
}
