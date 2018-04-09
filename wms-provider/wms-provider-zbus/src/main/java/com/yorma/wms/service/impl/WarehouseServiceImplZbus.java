package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.StorageDao;
import com.yorma.wms.dao.api.WarehouseDao;
import com.yorma.wms.dao.memory.impl.StorageDaoImpl;
import com.yorma.wms.dao.memory.impl.WarehouseDaoImpl;
import com.yorma.wms.service.impl.WarehouServiceImpl;

public class WarehouseServiceImplZbus extends WarehouServiceImpl{

	private WarehouseDao warehouseDao=new WarehouseDaoImpl();
	private StorageDao storageDao = new StorageDaoImpl();
	
	public WarehouseServiceImplZbus() {
		this.setWarehouseDao(warehouseDao);
		this.setStorageDao(storageDao);
	}
	
}
