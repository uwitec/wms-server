package com.yorma.wms.service.impl;

import com.yorma.wms.dao.api.LocationDao;
import com.yorma.wms.dao.api.StorageDao;
import com.yorma.wms.dao.memory.impl.LocationDaoImpl;
import com.yorma.wms.dao.memory.impl.StorageDaoImpl;
import com.yorma.wms.service.impl.StorageServiceImpl;

public class StorageServiceImplZbus extends StorageServiceImpl {

	private StorageDao storageDao=new StorageDaoImpl();
	private LocationDao locationDao = new LocationDaoImpl();
	
	public StorageServiceImplZbus() {
		this.setStorageDao(storageDao);
		this.setLocationDao(locationDao);
	}
	
}
