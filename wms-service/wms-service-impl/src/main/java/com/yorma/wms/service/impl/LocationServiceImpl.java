package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.LocationDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.dto.LocationDto;
import com.yorma.wms.service.api.LocationService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;
import com.yorma.wms.service.impl.util.CodeUtil;

/**
 * 储位、库位service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月9日
 * @version V1.0
 */
public class LocationServiceImpl extends BaseServiceImpl<Location>implements LocationService {

	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

	private LocationDao locationDao;
	private StockDao stockDao;

	/**
	 * 添加(修改)储位
	 * 
	 * @param location
	 *            储位实体类
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10004]参数为null
	 * 					[Success:false, Status:60001, Msg:储位代码重复]
	 * 					[Success:false, Status:10014]影响行数为0，保存失败
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:储位id,储位代码]
	 * 				</pre>
	 */
	public ResponseMessage saveLocation(Location location) {
		ResponseMessage res = new ResponseMessage();
		try {
			if (location == null) {
				return new ResponseMessage(false, "10004");
			}
			
			if (location.getId() == null) {
				if (isBlank(location.getCode())) {
					Long id = locationDao.getMaxId();
					if (id == null) {
						id = 0L;
					}
					// 将代码首字母，位数，最大id传入getCode方法生成默认储位代码
					String code = CodeUtil.getCode("L", 5, id);
					location.setCode(code);
					/*
					 * 判断数据库中是否存在仓库。库区、储位代码都相同的数据
					 */
					Location location2 = locationDao.checkCode(location.getCode(), location.getStorageCode(),
							location.getWarehouseCode());
						if (location2 != null) {
							return new ResponseMessage(false, "60001");
						}
				}
			}
			int rows = locationDao.saveLocation(location);
			if (rows > 0) {
				Field field = location.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object id = field.get(location); // 得到此属性的值
				Object[] ob = new Object[] { id, location.getCode() };
				res.setData(ob);
			} else {
				return new ResponseMessage(false, "10014");
			}
		} catch (Exception e) {
			logger.error("LocationService:方法[saveLocation]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("储位save异常信息：%s", e));
		}
		return res;
	}

	/**
	 * 批量添加
	 */
	@Override
	public ResponseMessage createBatch(List<Location> entitys) {
		try {
			if ( entitys == null||entitys.isEmpty() ) {
				return new ResponseMessage(false, "10004");
			}
			for (Location location : entitys) {
				if (isBlank(location.getCode())) {
					Long id = locationDao.getMaxId();
					if (id == null) {
						id = 0L;
					}
					// 将代码首字母，位数，最大id传入getCode方法生成默认储位代码
					String code = CodeUtil.getCode("L", 5, id);
					location.setCode(code);
				} else {
					Location oldLocation = locationDao.checkCode(location.getCode(), location.getStorageCode(),
							location.getWarehouseCode());
					if (location.getId() == null) {
						if (oldLocation != null) {
							return new ResponseMessage(false, "60001");
						}
					} else {
						if (oldLocation != null && oldLocation.getId() != location.getId()) {
							return new ResponseMessage(false, "60001");
						}
					}

				}
				location.setIsEnable(true);
			}
			int[] rows = locationDao.createBatch(entitys);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			
			logger.error("LocationService:方法[createBatch]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * ※暂时保留（不确定） 查询（条件）库位
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:List<储位实体>]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getLocation(Map<String, Object> map) {

		try {
			List<Location> locations = locationDao.getLocation(map);
			return new ResponseMessage(true, locations);
		} catch (Exception e) {
			logger.error("LocationService:方法[getLocation]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("储位get异常信息：%s", e));
		}
	}

	/**
	 * 查询（条件）储位及仓库全称
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false, Status:10003, Msg:异常信息]
	 * 					[Success:true, Data:List<储位Dto实体>]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getLocationAndWarehouse(Map<String, Object> map) {
		try {
			List<LocationDto> locationDtos = locationDao.getLocationAndWarehouse(map);
			return new ResponseMessage(true, locationDtos);
		} catch (Exception e) {
			logger.error("LocationService:方法[getLocationAndWarehouse]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("储位get异常信息：%s", e));
		}
	}

	/**
	 * 查询所有储位信息的仓库代码、库区代码、储位代码
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success：false,Status:10003,msg:异常信息]
	 * 					[Success:ture,Data:Map<String,Object>(key:仓库代码库区代码储位代码，value："1"(用于占位，没有特殊意义))]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getWarehouse() {
		try {
			Map<String, Object> map = new HashMap<>();
			
			for (Location location : locationDao.getWarehouse()) {
				map.put(location.getWarehouseCode()+location.getStorageCode()+location.getCode(), "1");
			}
			return new ResponseMessage(true, map);
		} catch (Exception e) {
			logger.error("LocationService:方法[getWarehouse]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("储位get异常信息：%s", e));
		}
	}
	
	@Override
	public ResponseMessage removeById(Long id) {
		try {
			if (id == null ) {
				return new ResponseMessage(false, "10004");
			}
			Location location = locationDao.find(id);
			int stockCounts=stockDao.getStockCountsByLocationCode(location.getCode(),location.getWarehouseCode(),location.getStorageCode());
			if (stockCounts>0) {
				return new ResponseMessage(false, "60042", String.format("异常信息：%s", "该储位下存在库存记录，请核实后操作"));
			}
			return new ResponseMessage(true, locationDao.removeById(id));
		} catch (Exception e) {
			logger.error("LocationService:方法[removeById]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("储位get异常信息：%s", e));
		}
	}
	
	public void setLocationDao(LocationDao locationDao) {
		super.setBaseDao(locationDao);
		this.locationDao = locationDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

}
