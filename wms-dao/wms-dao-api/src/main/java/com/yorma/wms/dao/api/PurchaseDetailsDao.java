package com.yorma.wms.dao.api;

import java.util.List;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.PurchaseDetails;

/**
 * 采购订单Dao 接口类
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 
 * @version 1.00.00
 * @date 2017年11月15日
 * @version V1.0
 */
public interface PurchaseDetailsDao extends BaseDao<PurchaseDetails> {

	/**
	 * 根据订单号删除入库采购订单明细
	 * @param orderPo	订单号
	 * @return	影响行数
	 */
	int removePurchaseDetailsByOrderPo(String orderPo);
	
	/**
	 * 根据订单号查询入库采购订单明细
	 * @param orderPo	订单号
	 * @return	list<入库采购订单明细实体>
	 */
	List<PurchaseDetails> getPurchaseDetailsByOrderPo(String orderPo);
	
	
}
