package com.yorma.wms.service.api;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.StockTransferList;
import com.yorma.wms.service.base.api.BaseService;

public interface StockTransferListService extends BaseService<StockTransferList> {

	/**
	 * 根据移库单号查询移库明细信息
	 * @param transferNo	移库单号
	 * @return	responsemessage 对象
	 * 				[Success：false Status：10012] 参数为null
	 * 				[Success：false	Status:10003] 异常信息
	 * 				[Success：true  Data:移库单表体实体集合] 操作成功
	 */
	ResponseMessage getStockTransferListByTransferNo(String transferNo);
	
}
