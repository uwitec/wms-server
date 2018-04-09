package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.StockTransferList;

/**
 * 移库单明细Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年12月13日
 * @version 1.00.00
 * @date 2017年12月13日
 * @version V1.0
 */
public interface StockTransferListDao extends BaseDao<StockTransferList> {

	/**
	 * 根据移库单号删除移库明细
	 * @param transferNo	移库单号
	 * @return	影响行数
	 */
	int removeStockTransferListByTransferNo(String transferNo);
	
	/**
	 * 根据移库单号查询移库明细信息
	 * @param transFerNo	移库单号
	 * @return	移库明细信息实体集合
	 */
	List<StockTransferList> getStockTransferListByTransferNo(String transferNo);
	
}
