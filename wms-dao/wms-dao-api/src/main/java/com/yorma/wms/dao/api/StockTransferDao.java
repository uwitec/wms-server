package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.StockTransfer;

/**
 * 移库单表头Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年12月13日
 * @version 1.00.00
 * @date 2017年12月13日
 * @version V1.0
 */
public interface StockTransferDao extends BaseDao<StockTransfer> {

	/**
	 * 根据条件查询移库单表头
	 * @param parames	参数集合
	 * @return	移库单表头实体集合
	 */
	List<StockTransfer> getStockTransfer(Map<String, Object> parames);
	
	/**
	 * 根据条件查询移库单表头(分页)
	 * @param parames 	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return	移库单表头实体集合
	 */
	List<StockTransfer> getStockTransfer(Map<String, Object> parames,Integer pageNumber,Integer pageSize);
	
	/**
	 * 根据条件查询移库单表头信息条数
	 * @param parames	参数集合
	 * @return	移库单表头信息条数
	 */
	Integer getcounts(Map<String, Object> parames);
	
	/**
	 * 根据移库单号查询移库表头信息
	 * @param transferNo	移库单号
	 * @return	移库表头实体
	 */
	StockTransfer getStockTransferByTransferNo(String transferNo);
	
	/**
	 * 根据移库单号删除移库单表头新西
	 * @param transferNo	移库单号
	 * @return	影响行数
	 */
	int removeStockTransferByTransferNo(String transferNo);
	
}
