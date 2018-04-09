package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.SalesDetails;

/**
 * 销售单明细Dao
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月15日
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public interface SalesDetailsDao extends BaseDao<SalesDetails> {

	/**
	 * 根据订单号删除出库销售订单明细
	 * @param so	订单号
	 * @return	影响行数
	 */
	int removeSalesDetailsByOrderSO(String orderSo);
	
	/**
	 * 根据订单号查询出库销售订单明细
	 * @param so	订单号
	 * @return	List<出库销售订单明细实体>
	 */
	List<SalesDetails> getSalesDetailsByOrderSO(String orderSo);	
	
	
	
}
