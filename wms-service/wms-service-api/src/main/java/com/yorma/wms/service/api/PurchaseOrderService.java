package com.yorma.wms.service.api;

import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.PurchaseOrder;
import com.yorma.wms.service.base.api.BaseService;

public interface PurchaseOrderService extends BaseService<PurchaseOrder> {
	
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
	ResponseMessage getPurchaseOrderByPo(String po);
	
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
	ResponseMessage removePurchaseOrderByOrderPo(String po);
	
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
	ResponseMessage updateIsAuditByPo(String po, boolean isAudit);
	
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
	ResponseMessage getPurchaseOrderAsReceivingByPo(String po);
	
	/**
	 * 保存入库订单及明细(关务可用)
	 * @param purchaseOrder		入库订单表头
	 * @param purchaseDetails	入库订单明细集合
	 * @return	ResponsMessage对象
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003, msg:异常信息] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60013, msg:订单已审核] 
	 * 				[success: false status:60002, msg:单号重复]
	 * 				[success: false status:60043, msg:该报关单已生成订单]
	 * 				[success: true 	data: 订单表头id]  操作成功
	 *         </pre>
	 */
	ResponseMessage savePurchaseOrderAndDetails(PurchaseOrder purchaseOrder,List<PurchaseDetails> purchaseDetailss);
	
	
}
