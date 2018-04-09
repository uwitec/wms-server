package com.yorma.wms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.PurchaseDetailsDao;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.service.api.PurchaseDetailsService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class PurchaseDetailsServiceImpl extends BaseServiceImpl<PurchaseDetails> implements PurchaseDetailsService {

	private static final Logger logger=LoggerFactory.getLogger(PurchaseDetails.class);
	
	private PurchaseDetailsDao purchaseDetailsDao;
	
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
	@Override
	public ResponseMessage getPurchaseDetailsByOrderPo(String orderPo) {
		try {
			if (isBlank(orderPo)) {
				return new ResponseMessage(false, "10012");
			}
			List<PurchaseDetails> purchaseDetails=purchaseDetailsDao.getPurchaseDetailsByOrderPo(orderPo);
			return new ResponseMessage(true, purchaseDetails);
		} catch (Exception e) {
			logger.error("PurchaseDetailsService:方法[getPurchaseDetailsByOrderId]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage getPurchaseDetailsAsReceivingAsnByOrderPo(String orderPo) {
		try {
			if (isBlank(orderPo)) {
				return new ResponseMessage(false, "10012");
			}
			List<PurchaseDetails> purchaseDetailses=purchaseDetailsDao.getPurchaseDetailsByOrderPo(orderPo);
			List<ReceivingNoteAsn> receivingNoteAsns = new ArrayList<>();
			for (PurchaseDetails purchaseDetails : purchaseDetailses) {
				ReceivingNoteAsn receivingNoteAsn = new ReceivingNoteAsn();
				receivingNoteAsn.setAmount(purchaseDetails.getAmount());
				receivingNoteAsn.setBatchAttribute(purchaseDetails.getBatchAttribute());
				receivingNoteAsn.setCurrency(purchaseDetails.getCurrency());
				receivingNoteAsn.setEmsNo(purchaseDetails.getEmsNo());
				receivingNoteAsn.setExpectQty(purchaseDetails.getExpectQty());
				receivingNoteAsn.setExpiryDate(purchaseDetails.getExpiryDate());
				receivingNoteAsn.setGoodsModel(purchaseDetails.getGoodsModel());
				receivingNoteAsn.setGoodsName(purchaseDetails.getGoodsName());
				receivingNoteAsn.setGoodsUuid(purchaseDetails.getGoodsUuid());
				receivingNoteAsn.setGrossWeight(purchaseDetails.getGrossWeight());
				receivingNoteAsn.setInvoiceNo(purchaseDetails.getInvoiceNo());
				receivingNoteAsn.setStatus(0);
				receivingNoteAsn.setIsQualifd(purchaseDetails.getIsQualifd());
				receivingNoteAsn.setItem(purchaseDetails.getItem());
				receivingNoteAsn.setLotNo(purchaseDetails.getLotNo());
				receivingNoteAsn.setMu(purchaseDetails.getMu());
				receivingNoteAsn.setNetWeight(purchaseDetails.getNetWeight());
				receivingNoteAsn.setPn(purchaseDetails.getPn());
				receivingNoteAsn.setProductionDate(purchaseDetails.getProductionDate());
				receivingNoteAsn.setVolume(purchaseDetails.getVolume());
				receivingNoteAsn.setPo(purchaseDetails.getPo());
				receivingNoteAsn.setPackagingKey(purchaseDetails.getPackagingKey());
				receivingNoteAsn.setUnitPrice(purchaseDetails.getUnitPrice());
				receivingNoteAsns.add(receivingNoteAsn);
			}
			return new ResponseMessage(true, receivingNoteAsns);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PurchaseDetailsService:方法[getPurchaseDetailsAsReceivingAsnByOrderId]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setPurchaseDetailsDao(PurchaseDetailsDao purchaseDetailsDao){
		super.setBaseDao(purchaseDetailsDao);
		this.purchaseDetailsDao=purchaseDetailsDao;
	}
	
}
