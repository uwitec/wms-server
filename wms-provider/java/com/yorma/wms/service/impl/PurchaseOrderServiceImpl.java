package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.PurchaseDetailsDao;
import com.yorma.wms.dao.api.PurchaseOrderDao;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.PurchaseOrder;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.dto.PurchaseOrderDto;
import com.yorma.wms.service.api.PurchaseOrderService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder>implements PurchaseOrderService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrder.class);

	private PurchaseOrderDao purchaseOrderDao;
	private PurchaseDetailsDao purchaseDetailsDao;
	private SerialNumberService serialNumberService;
	private GoodsDao goodsDao;

	/**
	 * 添加（修改）入库采购订单
	 * 
	 * @param 入库采购订单实体
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null
	 * 				[success: false status:10014] 保存失败
	 * 				[success: false status:10020] 生成单号失败
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013] 订单已审核，不可修改
	 * 				[success: true 	data:id,订单号] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage save(PurchaseOrder purchaseOrder) {
		try {
			if (purchaseOrder == null) {
				return new ResponseMessage(false, "10004");
			}
			if (purchaseOrder.getId() == null) {
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("P",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String po = (String) responseMessage.getData();
				purchaseOrder.setPo(po);
				purchaseOrder.setUuid(UUID.randomUUID().toString());

				int rows = purchaseOrderDao.save(purchaseOrder);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				Field field = purchaseOrder.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object value = field.get(purchaseOrder); // 得到此属性的值
				Object data = value + "," + purchaseOrder.getPo();
				return new ResponseMessage(true, data);
			}
			if (purchaseOrder.getIsAudit()) {
				return new ResponseMessage(false, "60013");
			}
			return super.save(purchaseOrder);
		} catch (Exception e) {
			logger.error("PurchaseOrderService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "1000.", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号查询入库采购订单信息
	 * 
	 * @param 订单号
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:入库采购订单Dto] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getPurchaseOrderByPo(String po) {
		try {
			if (isBlank(po)) {
				return new ResponseMessage(false, "10012");
			}
			PurchaseOrderDto purchaseOrderDto = purchaseOrderDao.getPurchaseOrderByPo(po);
			return new ResponseMessage(true, purchaseOrderDto);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PurchaseOrderService:方法[getPurchaseOrderByPo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据条件查询入库采购订单
	 */
	@Override
	public ResponseMessage searchParameter(List<Parameter> parameters) {
		try {
			List<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderDao.getPurchaseOrderDto(parameters);
			return new ResponseMessage(true, purchaseOrderDtos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PurchaseOrderService:方法[searchParameter]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号删除入库采购订单信息及订单明细
	 * 
	 * @param po 订单号
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013，msg:该订单已审核，请弃审后删除！]
	 * 				[success: true 	data: true]  操作成功
	 *         </pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage removePurchaseOrderByOrderPo(String po) {
		try {
			if (isBlank(po)) {
				return new ResponseMessage(false, "10012");
			}
			if (purchaseOrderDao.getPurchaseOrderAuditByPO(po)) {
				return new ResponseMessage(false, "60013", String.format("异常信息：%s", "该订单已审核，请弃审后删除！"));
			}
			int rows = purchaseOrderDao.removePurchaseOrderByPO(po);
			rows = purchaseDetailsDao.removePurchaseDetailsByOrderPo(po);
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PurchaseOrderService:方法[removePurchaseOrderById]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号修改审核状态
	 * @param po	订单号
	 * @param isAudit	是否审核
	 * @return	ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60012] 该订单已生成入库单 
	 * 				[success: false status:60013] 该订单已审核
	 * 				[success: false status:60014] 该订单还未审核不可撤销
	 * 				[success: false status:10014] 修改状态失败
	 * 				[success: true 	data: 影响行数]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage updateIsAuditByPo(String po, boolean isAudit) {
		try {
			if (isBlank(po)) {
				return new ResponseMessage(false, "10012");
			}
			PurchaseOrderDto orderDto = purchaseOrderDao.getPurchaseOrderByPo(po);
			if (orderDto.getIsReceiving()) {
				//该订单已生成入库单 
				return new ResponseMessage(false, "60012");
			}
			if (orderDto.getIsAudit() == isAudit) {
				if (isAudit) {
					//该订单已审核
					return new ResponseMessage(false, "60013",String.format("异常信息：%s", "该订单已审核！"));
				}
				//该订单还未审核不可撤销
				return new ResponseMessage(false, "60014",String.format("异常信息：%s", "该订单还未审核不可撤销!"));
			}
			int rows = purchaseOrderDao.updateIsAuditByPo(po, isAudit);
			if (rows == 0) {
				return new ResponseMessage(false, "10014", String.format("异常信息：%s", "操作失败，请核实数据后操作！"));
			}
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("PurchaseOrderService:方法[updateIsAuditByPo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号生成虚拟入库单
	 * @param po
	 * @return	ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success:false,status:10020]:生成单号失败
	 * 				[success: true 	data: 入库单实体]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getPurchaseOrderAsReceivingByPo(String po) {
		try {
			if (isBlank(po)) {
				return new ResponseMessage(false, "10012");
			}
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("RK",
					new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String receivingNoteNo=(String)responseMessage.getData();
			PurchaseOrderDto purchaseOrderDto = purchaseOrderDao.getPurchaseOrderByPo(po);
			ReceivingNote receivingNote = new ReceivingNote();
			receivingNote.setReceivingNoteNo(receivingNoteNo);
			receivingNote.setAmount(purchaseOrderDto.getAmount());
			receivingNote.setCurrency(purchaseOrderDto.getCurrency());
			receivingNote.setGrossWeight(purchaseOrderDto.getGrossWeight());
			receivingNote.setNetWeight(purchaseOrderDto.getNetWeight());
			receivingNote.setOwnerCode(purchaseOrderDto.getOwnerCode());
			receivingNote.setPacks(String.valueOf(purchaseOrderDto.getPacks()));
			receivingNote.setRemark(purchaseOrderDto.getRemark());
			receivingNote.setShippingCode(purchaseOrderDto.getSupplierCode());
			receivingNote.setStatus(10);
			receivingNote.setVolume(purchaseOrderDto.getVolume());
			receivingNote.setReceivingType("202");
			receivingNote.setOrderPo(po);
			
			return new ResponseMessage(true, receivingNote);
		} catch (Exception e) {
			logger.error("PurchaseOrderService:方法[getPurchaseOrderAsReceivingByPo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 保存入库订单及明细
	 * @param purchaseOrder		入库订单表头
	 * @param purchaseDetails	入库订单明细集合
	 * @return	ResponsMessage对象
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003, msg:异常信息] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60013, msg:订单已审核] 
	 * 				[success: false status:60002, msg:单号重复]
	 * 				[success: true 	data: 订单表头id]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage savePurchaseOrderAndDetails(PurchaseOrder purchaseOrder,
			List<PurchaseDetails> purchaseDetailss) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("savePurchaseOrderAndDetails: purchaseOrder:{}", purchaseOrder);
				logger.debug("savePurchaseOrderAndDetails: purchaseDetailss.size:{}", purchaseDetailss.size());
			}
			if (purchaseOrder==null) {
				return new ResponseMessage(false, "10004");
			}
			
			if (purchaseOrder.getIsAudit()) {
				return new ResponseMessage(false, "60013",String.format("异常信息：%s", "订单已审核"));
			}
			
			if (purchaseOrder.getId() == null) {
				if (purchaseOrderDao.getPurchaseOrderByPo(purchaseOrder.getPo()) != null) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复！"));
				}
				purchaseOrder.setUuid(UUID.randomUUID().toString());
				purchaseOrder.setCreateDate(new Date());
			}else {
				purchaseOrder.setLastUpdateDate(new Date());
			}
			int rows = purchaseOrderDao.save(purchaseOrder);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			Field field = purchaseOrder.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(purchaseOrder); // 得到此属性的值
			rows = purchaseDetailsDao.removePurchaseDetailsByOrderPo(purchaseOrder.getPo());
			if (purchaseDetailss != null && !purchaseDetailss.isEmpty()) {
				for (PurchaseDetails purchaseDetails : purchaseDetailss) {
					if (purchaseDetails.getId() == null) {
						Goods goods = goodsDao.getGoodsByDepartCodeAndPn(purchaseOrder.getOwnerCode(), purchaseDetails.getPn());
						purchaseDetails.setGoodsUuid(goods.getUuid());
						purchaseDetails.setGoodsModel(goods.getModel());
						purchaseDetails.setOrderPo(purchaseOrder.getPo());
						purchaseDetails.setCreateDate(new Date());
					}else {
						purchaseDetails.setId(null);
						purchaseDetails.setLastUpdateDate(new Date());
					}
					rows = purchaseDetailsDao.save(purchaseDetails);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			return new ResponseMessage(true, value);
		} catch (Exception e) {
			logger.error("PurchaseOrderService:方法[savePurchaseOrderAndDetails]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
		super.setBaseDao(purchaseOrderDao);
		this.purchaseOrderDao = purchaseOrderDao;
	}

	public void setSerialNumberService(SerialNumberService serialNumberService) {
		this.serialNumberService = serialNumberService;
	}

	public void setPurchaseDetailsDao(PurchaseDetailsDao purchaseDetailsDao) {
		this.purchaseDetailsDao = purchaseDetailsDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

}
