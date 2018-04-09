package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.Warehouse;
import com.yorma.wms.entity.dto.WarehouseDto;

/**
 * 仓库Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年8月10日
 * @version 1.00.00
 * @date 2017年8月10日
 * @version V1.0
 */
public interface WarehouseDao extends BaseDao<Warehouse> {

	/**
	 * 仓库添加（修改）
	 * @param warehouse
	 * @return		影响行数
	 */
	int saveWarehouse(Warehouse warehouse);
	
	/**
	 * 查询仓库
	 * 
	 * @param map
	 *            查询条件
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	List<WarehouseDto> getWarehouse(Map<String, Object> map) throws Exception;

	/**
	 * 根据仓库代码或者仓库名称查询数据
	 * @param str	仓库名称或仓库代码
	 * @return		
	 */
	List<WarehouseDto> getWarehouseByCode(String code,String type);
	
	/**
	 * ID查询仓库
	 * 
	 * @return 将查询（所有/符合条件）的数据放入的list集合
	 */
	WarehouseDto getWarehouseById(Long id);

	/**
	 * 查询出最大id值用于生成默认的CODE
	 * 
	 * @return
	 */
	Long getMaxId();
	/**
	 * 判断仓库代码是否有相同的
	 * @param code
	 * @return
	 */
	Warehouse checkCode(String code);
	
	/**
	 * 根据仓库代码查询仓库ID
	 * @param code	仓库代码
	 * @return	仓库ID
	 */
	Long getWarehouseIdByCode(String code);
	
	/**
	 * 根据仓库ID查询仓库代码
	 * @param id	仓库ID
	 * @return	仓库代码
	 */
	String getWarehouseCodeById(Long id);

}
