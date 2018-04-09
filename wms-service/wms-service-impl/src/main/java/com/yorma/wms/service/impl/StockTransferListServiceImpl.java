package com.yorma.wms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.StockTransferListDao;
import com.yorma.wms.entity.StockTransferList;
import com.yorma.wms.service.api.StockTransferListService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class StockTransferListServiceImpl extends BaseServiceImpl<StockTransferList> implements StockTransferListService {

	private static final Logger logger = LoggerFactory.getLogger(StockTransferList.class);
	
	private StockTransferListDao stockTransferListDao;
	
	/**
	 * 根据移库单号查询移库明细信息
	 * @param transferNo	移库单号
	 * @return	responsemessage 对象
	 * 				[Success：false Status：10012] 参数为null
	 * 				[Success：false	Status:10003] 异常信息
	 * 				[Success：true  Data:移库单表体实体集合] 操作成功
	 */
	@Override
	public ResponseMessage getStockTransferListByTransferNo(String transferNo) {
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, stockTransferListDao.getStockTransferListByTransferNo(transferNo));
		} catch (Exception e) {
			logger.error("StockTransferListService:方法[getStockTransferListByTransferNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setStockTransferListDao(StockTransferListDao stockTransferListDao){
		super.setBaseDao(stockTransferListDao);
		this.stockTransferListDao=stockTransferListDao;
	}
	
}
