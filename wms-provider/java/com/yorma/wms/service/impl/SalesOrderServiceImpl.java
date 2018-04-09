package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.SalesDetailsDao;
import com.yorma.wms.dao.api.SalesOrderDao;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.entity.SalesOrder;
import com.yorma.wms.entity.dto.SalesOrderDto;
import com.yorma.wms.service.api.SalesOrderService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class SalesOrderServiceImpl extends BaseServiceImpl<SalesOrder>implements SalesOrderService {

	private static final Logger logger = LoggerFactory.getLogger(SalesOrder.class);

	private SalesOrderDao salesOrderDao;
	private SalesDetailsDao salesDetailsDao;
	private GoodsDao goodsDao;
	private SerialNumberService serialNumberService;

	/**
	 * 添加（修改）入库采购订单
	 * 
	 * @param 入库采购订单实体
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null
	 * 				[success: false status:10020] 生成单号失败
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:id,订单号] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage save(SalesOrder salesOrder) {
		try {
			if (salesOrder == null) {
				return new ResponseMessage(false, "10004");
			}
			if (salesOrder.getId() == null) {
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("S",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String so = (String) responseMessage.getData();
				salesOrder.setSo(so);
				salesOrder.setUuid(UUID.randomUUID().toString());

				int rows = salesOrderDao.save(salesOrder);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				Field field = salesOrder.getClass().getDeclaredField("id");
				field.setAccessible(true);// 设置些属性是可以访问的
				Object value = field.get(salesOrder); // 得到此属性的值
				Object data = value + "," + salesOrder.getSo();
				return new ResponseMessage(true, data);
			}
			return super.save(salesOrder);
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[save]出现异常，异常信息是 :{}", e);
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
	 * 				[success: true 	data:入库采购订单实体] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getSalesOrderBySo(String so) {
		try {
			if (isBlank(so)) {
				return new ResponseMessage(false, "10012");
			}
			
			return new ResponseMessage(true, salesOrderDao.getSalesOrderDtoBySo(so));

		} catch (Exception e) {
			logger.error("SalesOrderService:方法[getSalesOrderBySo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据条件查询销售单
	 */
	@Override
	public ResponseMessage searchParameter(List<Parameter> parameters) {
		try {
			return new ResponseMessage(true, salesOrderDao.getSalesOrderDto(parameters));
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[searchParameter]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号删除入库采购订单信息及订单明细
	 * 
	 * @param 订单号
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013，msg:该订单已审核，请弃审后删除！]
	 * 				[success: true 	data: 影响行数]  操作成功
	 *         </pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage removeSalesOrderBySO(String so) {
		try {
			if (isBlank(so)) {
				return new ResponseMessage(false, "10012");
			}
			if (salesOrderDao.getSalesOrderAuditBySO(so)) {
				return new ResponseMessage(false, "60013", String.format("异常信息：%s", "该订单已审核，请弃审后删除！"));
			}
			int rows = salesOrderDao.removeSalesOrderBySO(so);
			rows = salesDetailsDao.removeSalesDetailsByOrderSO(so);
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[removeSalesOrderByID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据条件出库销售订单信息
	 * 
	 * @param 订单号
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data: List<出库销售订单信息Dto>]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getSalesOrderDto(Map<String, Object> parameters) {
		try {
			List<SalesOrderDto> salesOrderDtos = salesOrderDao.getSalesOrderDto(parameters);
			return new ResponseMessage(true, salesOrderDtos);
		} catch (Exception e) {

			logger.error("SalesOrderService:方法[getSalesOrderDto]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号修改审核状态
	 * @param so	订单号
	 * @param isAudit	是否审核
	 * @return	ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60012] 该订单已生成出库单 
	 * 				[success: false status:60013] 该订单已审核
	 * 				[success: false status:60014] 该订单还未审核不可撤销
	 * 				[success: true 	data: 影响行数]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage updateIsAuditBySo(String so, boolean isAudit) {
		try {
			if (isBlank(so)) {
				return new ResponseMessage(false, "10012");
			}
			SalesOrderDto orderDto=salesOrderDao.getSalesOrderDtoBySo(so);
			if (orderDto.getIsDelivery()) {
				//该订单已生成入库单 
				return new ResponseMessage(false, "60012");
			}
			if (orderDto.getIsAudit() == isAudit) {
				if (isAudit) {
					//该订单已审核
					return new ResponseMessage(false, "60013");
				}
				//该订单还未审核不可撤销
				return new ResponseMessage(false, "60014");
			}
			int rows=salesOrderDao.updateIsAuditBySo(so, isAudit);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[updateIsAuditBySo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据订单号生成虚拟出库单
	 * @param so
	 * @return	ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data: 出库单实体]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getSalesOrderAsDeliveryBySo(String so) {
		try {
			if (isBlank(so)) {
				return new ResponseMessage(false, "10012");
			}
			SalesOrderDto salesOrderDto = salesOrderDao.getSalesOrderDtoBySo(so);
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("CK",
					new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String deliveryNoteNo = (String) responseMessage.getData();
			DeliveryNote deliveryNote = new DeliveryNote();
			deliveryNote.setDeliveryNoteNo(deliveryNoteNo);
			deliveryNote.setAmount(salesOrderDto.getAmount());
			deliveryNote.setConsigneeCode(salesOrderDto.getConsigneeCode());
			deliveryNote.setCurrency(salesOrderDto.getCurrency());
			deliveryNote.setGrossWeight(salesOrderDto.getGrossWeight());
			deliveryNote.setNetWeight(salesOrderDto.getNetWeight());
			deliveryNote.setOwnerCode(salesOrderDto.getOwnerCode());
			deliveryNote.setPacks(String.valueOf(salesOrderDto.getPacks()));
			deliveryNote.setRemark(salesOrderDto.getRemark());
			deliveryNote.setStatus(10);
			deliveryNote.setVolume(salesOrderDto.getVolume());
			deliveryNote.setOrderSo(so);
			return new ResponseMessage(true, deliveryNote);
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[getSalesOrderAsDeliveryBySo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 保存出库订单表头和明细
	 * @param salesOrder		出库订单表头
	 * @param salesDetailss		出库订单名袭集合
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60013] 该订单已审核 
	 * 				[success: false status:60002] 单号重复 
	 * 				[success: true 	data: 订单表头id]  操作成功
	 * 			 </pre>
	 */
	@Override
	public ResponseMessage saveSalesOrderAndDetails(SalesOrder salesOrder, List<SalesDetails> salesDetailss) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveSalesOrderAndDetails: salesOrder:{}", salesOrder);
				logger.debug("saveSalesOrderAndDetails: salesDetailss.size:{}", salesDetailss.size());
			}
			if (salesOrder == null) {
				return new ResponseMessage(false, "10004");
			}
			if (salesOrder.getIsAudit()) {
				return new ResponseMessage(false, "60013", String.format("异常信息：%s", "该订单已审核！"));
			}
			if (salesOrder.getId() == null) {
				if (salesOrderDao.getSalesOrderDtoBySo(salesOrder.getSo())!=null) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "单号重复！"));
				}
				salesOrder.setUuid(UUID.randomUUID().toString());
				salesOrder.setCreateDate(new Date());
			}else {
				salesOrder.setLastUpdateDate(new Date());
			}
			int rows = salesOrderDao.save(salesOrder);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			Field field = salesOrder.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(salesOrder); // 得到此属性的值
			rows = salesDetailsDao.removeSalesDetailsByOrderSO(salesOrder.getSo());
			if (salesDetailss !=null && !salesDetailss.isEmpty()) {
				for (SalesDetails salesDetails : salesDetailss) {
					if (salesDetails.getId()==null) {
						Goods goods = goodsDao.getGoodsByDepartCodeAndPn(salesOrder.getOwnerCode(), salesDetails.getPn());
						salesDetails.setGoodsUuid(goods.getUuid());
						salesDetails.setGoodsModel(goods.getModel());
						salesDetails.setOrderSo(salesOrder.getSo());
						salesDetails.setCreateDate(new Date());
					}else {
						salesDetails.setId(null);
						salesDetails.setLastUpdateDate(new Date());
					}
					rows=salesDetailsDao.save(salesDetails);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			
			return new ResponseMessage(true, value);
		} catch (Exception e) {
			logger.error("SalesOrderService:方法[saveSalesOrderAndDetails]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setSalesOrderDao(SalesOrderDao salesOrderDao) {
		super.setBaseDao(salesOrderDao);
		this.salesOrderDao = salesOrderDao;
	}

	public void setSerialNumberService(SerialNumberService serialNumberService) {
		this.serialNumberService = serialNumberService;
	}

	public void setSalesDetailsDao(SalesDetailsDao salesDetailsDao) {
		this.salesDetailsDao = salesDetailsDao;
	}

	
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

}
