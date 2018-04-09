package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.dto.LocationDto;

/**
 * 储位Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月7日
 * @version 1.00.00
 * @date 2017年8月07日
 * @version V1.0
 */
public interface LocationDao extends BaseDao<Location>{
	
	/**
	 * 添加（修改）储位
	 * @param location	储位实体
	 * @return	影响行数
	 */
	int saveLocation(Location location);
	
	/**
	 * 查询储位
	 * @param map
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<Location> getLocation(Map<String, Object> map);

	/**
	 * 查询储位信息及仓库全称
	 * @param map
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<LocationDto> getLocationAndWarehouse(Map<String, Object> map);
	
	/**
	 * 获取最大ID用于生成储位的CODE默认数据
	 * @return	最大id
	 */
	Long getMaxId();
	
	/**
	 * 根据储位代码获取数据
	 * @param code
	 * @return	储位实体
	 */
	/**
	 * 根据储位代码、库区代码。仓库代码获取数据（用于判断储位代码是否重复）	
	 * @param code			储位代码
	 * @param storageCode	库区代码
	 * @param warehouseCode 仓库代码
	 * @return	储位实体
	 */
	Location checkCode(String code,String storageCode, String warehouseCode);
	
	/**
	 * 查询储位信息中的所有仓库，库区，储位
	 * @return	List<储位信息实体>
	 */
	List<Location> getWarehouse();
	
	/**
	 * 根据库区代码查询储位信息条数
	 * @param storageCode	库区代码
	 * @return	储位条数
	 */
	Integer getLocationCountsByStorageCode(String storageCode);
	
	/**
	 * 根据储位ID查询储位代码
	 * @param id	储位ID
	 * @return	储位代码
	 */
	String getLocationCodeById(Long id);
	
}
