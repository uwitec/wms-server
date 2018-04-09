package com.yorma.wms.service.api;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.service.base.api.BaseService;

public interface PurchaseDetailsService extends BaseService<PurchaseDetails> {

	/**
	 * 根据订单号查询入库采购订单明细
	 * @param orderId	
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false	status:10012] 参数为null
	 * 					[Success:false	status:10003] 异常信息
	 * 					[Success:true	data: List<入库采购订单明细实体>]	 操作成功
	 * 				</pre>
	 */
	ResponseMessage getPurchaseDetailsByOrderPo(String orderPo);
	
	/**
	 * 根据订单明细生成虚拟入库预告明细
	 * @param orderId
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false	status:10012] 参数为null
	 * 					[Success:false	status:10003] 异常信息
	 * 					[Success:true	data: List<入库单明细实体>]	 操作成功
	 * 				</pre>
	 */
	ResponseMessage getPurchaseDetailsAsReceivingAsnByOrderPo(String orderPo);
	
}
