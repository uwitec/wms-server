package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.LocationDao;
import com.yorma.wms.dao.api.StorageDao;
import com.yorma.wms.entity.Storage;
import com.yorma.wms.service.api.StorageService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;
import com.yorma.wms.service.impl.util.CodeUtil;

/**
 * 库区service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月9日
 * @version V1.0
 */
public class StorageServiceImpl extends BaseServiceImpl<Storage>implements StorageService {

	private static final Logger logger = LoggerFactory.getLogger(Storage.class);

	private StorageDao storageDao;
	private LocationDao locationDao;

	/**
	 * 库区(修改)添加
	 */
	@Override
	public ResponseMessage saveStorage(Storage storage) {
		ResponseMessage res = new ResponseMessage();
		if (storage == null) {
			return new ResponseMessage(false, "10004");
		}
		try {
			if (isBlank(storage.getCode())) {
				Long id = storageDao.geiMaxId();
				if (id == null) {
					id = 0L;
				}
				// 将代码首字母，位数，最大id传入getCode方法生成默认库区代码
				String code = CodeUtil.getCode("S", 5, id);
				storage.setCode(code);
			} else {
				// 确保库区代码在数据库不会重复
				Storage storage2 = storageDao.checkCode(storage.getCode(), storage.getWarehouseCode());
				if (storage.getId() == null) {
					if (storage2 != null) {
						return new ResponseMessage(false, "60001");
					}
				}
			}
			int rows = storageDao.saveStorage(storage);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}

			Field field = storage.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object id = field.get(storage); // 得到此属性的值
			Object[] ob = new Object[] { id, storage.getCode() };
			res.setData(ob);

		} catch (Exception e) {
			logger.error("StorageService:方法[saveStorage]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("库区save异常：%s", e));
		}
		return res;
	}

	/**
	 * 查询库区
	 * 
	 * @param sql
	 *            用于拼接sql语句
	 * @param params
	 *            用于存放查询条件
	 */
	@Override
	public ResponseMessage getStorage(Map<String, Object> map) {
		try {
			List<Storage> storages = storageDao.getStorage(map);
			return new ResponseMessage(true, storages);
		} catch (Exception e) {
			logger.error("StorageService:方法[getStorage]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("库区get异常：%s", e));
		}
	}

	@Override
	public ResponseMessage removeById(Long id) {
		try {
			if (id == null) {
				return new ResponseMessage(false, "10004");
			}
			String code = storageDao.getStorageCodeById(id);
			int locationCounts= locationDao.getLocationCountsByStorageCode(code);
			if (locationCounts>0) {
				return new ResponseMessage(false, "60042", String.format("异常信息：%s", "请先删除储位信息！"));
			}
			return new ResponseMessage(true, storageDao.removeById(id));
		} catch (Exception e) {
			logger.error("StorageService:方法[removeById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("库区get异常：%s", e));
		}
	}
	
	
	public void setStorageDao(StorageDao storageDao) {
		super.setBaseDao(storageDao);
		this.storageDao = storageDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	

}
