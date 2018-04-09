package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.SalesDetailsDao;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.service.api.SalesDetailsService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class SalesDetailsServiceImpl extends BaseServiceImpl<SalesDetails> implements SalesDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(SalesDetails.class);
	
	private SalesDetailsDao salesDetailsDao;
	
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
	@Override
	public ResponseMessage getSalesDetailsByOrderSO(String orderSo) {
		try {
			if (isBlank(orderSo)) {
				return new ResponseMessage(false, "10012");
			}
			List<SalesDetails> salesDetails = salesDetailsDao.getSalesDetailsByOrderSO(orderSo);
			return new ResponseMessage(true, salesDetails);
		} catch (Exception e) {
			logger.error("SalesDetailsService:方法[getSalesDetailsByOrderId]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
			
		}
	}
	
	/**
	 * 根据订单号生成虚拟出库订单明细
	 * @param so	订单号
	 * @return	ResponseMessage
	 * 			<pre>
	 * 				[Success: false status: 10012] 参数为null或者空字符串
	 *  			[Success: false status: 10003] 异常信息
	 *  			[Success: false data: List<出库销售订单明细实体>] 操作成功
	 * 			</pre?
	 */
	@Override
	public ResponseMessage getSalesDetailsAsDerliveryAsnByOrderSO(String orderSo) {
		try {
			if (isBlank(orderSo)) {
				return new ResponseMessage(false, "10012");
			}
			List<SalesDetails> salesDetailses = salesDetailsDao.getSalesDetailsByOrderSO(orderSo);
			List<DeliveryNoteAsn> deliveryNoteAsns = new ArrayList<>();
			for (SalesDetails salesDetails : salesDetailses) {
				DeliveryNoteAsn deliveryNoteAsn = new DeliveryNoteAsn();
				deliveryNoteAsn.setAmount(salesDetails.getAmount());
				deliveryNoteAsn.setBatchAttribute(salesDetails.getBatchAttribute());
				deliveryNoteAsn.setCurrency(salesDetails.getCurrency());
				deliveryNoteAsn.setEmsNo(salesDetails.getEmsNo());
				deliveryNoteAsn.setExpectQty(salesDetails.getExpectQty());
				deliveryNoteAsn.setExpiryDate(salesDetails.getExpiryDate());
				deliveryNoteAsn.setGoodsModel(salesDetails.getGoodsModel());
				deliveryNoteAsn.setGoodsName(salesDetails.getGoodsName());
				deliveryNoteAsn.setGoodsUuid(salesDetails.getGoodsUuid());
				deliveryNoteAsn.setGrossWeight(salesDetails.getGrossWeight());
				deliveryNoteAsn.setInvoiceNo(salesDetails.getInvoiceNo());
				deliveryNoteAsn.setIsMerged(null);
				deliveryNoteAsn.setItem(salesDetails.getItem());
				deliveryNoteAsn.setLotNo(salesDetails.getLotNo());
				deliveryNoteAsn.setMu(salesDetails.getMu());
				deliveryNoteAsn.setNetWeight(salesDetails.getNetWeight());
				deliveryNoteAsn.setPn(salesDetails.getPn());
				deliveryNoteAsn.setProductionDate(salesDetails.getProductionDate());
				deliveryNoteAsn.setVolume(salesDetails.getVolume());
				deliveryNoteAsn.setOrderNo(salesDetails.getSo());
				deliveryNoteAsn.setPackagingKey(salesDetails.getPackagingKey());
				deliveryNoteAsn.setUnitPrice(salesDetails.getUnitPrice());
				deliveryNoteAsn.setStatus(0);
				deliveryNoteAsns.add(deliveryNoteAsn);
			}
			return new ResponseMessage(true, deliveryNoteAsns);
		} catch (Exception e) {
			logger.error("SalesDetailsService:方法[getSalesDetailsAsDerliveryAsnByOrderId]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setSalesDetailsDao(SalesDetailsDao salesDetailsDao){
		super.setBaseDao(salesDetailsDao);
		this.salesDetailsDao=salesDetailsDao;
	}
	
}
