package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.Storage;

/**
 * 库区Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月10日
 * @version 1.00.00
 * @date 2017年8月10日
 * @version V1.0
 */
public interface StorageDao extends BaseDao<Storage> {

	/**
	 * 添加（修改）库区
	 * @param location
	 * @return		影响行数
	 */
	int saveStorage(Storage storage);
	
	/**
	 * 查询库区
	 * @param map
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<Storage> getStorage(Map<String, Object> map) throws Exception;
	
	/**
	 * 获取最大ID用于生成默认CODE
	 * @return		最大id
	 */
	Long geiMaxId();
	
	/**
	 * 根据库区代码获取数据
	 * @param code
	 * @return		库区实体
	 */
	Storage checkCode(String code,String warehouseCode);
	
	/**
	 * 根据仓库代码查询库区信息条数
	 * @param warehouseCode	仓库嗲吗
	 * @return	库区信息条数
	 */
	Integer getStorageCountsByWarehouseCode(String warehouseCode);
	
	/**
	 * 根据库区ID查询库区代码
	 * @param id	库区ID
	 * @return	库区代码
	 */
	String getStorageCodeById(Long id);
	
}
