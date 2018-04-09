package com.yorma.wms.service.api;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.service.base.api.BaseService;

public interface SalesDetailsService extends BaseService<SalesDetails> {

	/**
	 * 根据订单号查询出库销售订单明细
	 * @param so	订单号
	 * @return	ResponseMessage
	 * 			<pre>
	 * 				[Success: false status: 10012] 参数为null或者空字符串
	 *  			[Success: false status: 10003] 异常信息
	 *  			[Success: true data: List<出库销售订单明细实体>] 操作成功
	 * 			</pre?
	 */
	ResponseMessage getSalesDetailsByOrderSO(String orderSo);
	
	/**
	 * 根据订单号生成虚拟出库订单明细
	 * @param so	订单号
	 * @return	ResponseMessage
	 * 			<pre>
	 * 				[Success: false status: 10012] 参数为null或者空字符串
	 *  			[Success: false status: 10003] 异常信息
	 *  			[Success: true data: List<出库销售订单明细实体>] 操作成功
	 * 			</pre?
	 */
	ResponseMessage getSalesDetailsAsDerliveryAsnByOrderSO(String orderSo);
	
}
