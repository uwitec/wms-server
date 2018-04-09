package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.entity.SalesOrder;
import com.yorma.wms.service.base.api.BaseService;

public interface SalesOrderService extends BaseService<SalesOrder> {

	/**
	 * 根据订单号查询出库销售订单信息
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
	ResponseMessage getSalesOrderBySo(String so);
	
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
	ResponseMessage removeSalesOrderBySO(String so);
	
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
	ResponseMessage getSalesOrderDto(Map<String, Object> parameters);
	
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
	ResponseMessage updateIsAuditBySo(String so, boolean isAudit);
	
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
	ResponseMessage getSalesOrderAsDeliveryBySo(String so);
	
	/**
	 * 保存出库订单表头和明细(关务可用)
	 * @param salesOrder		出库订单表头
	 * @param salesDetailss		出库订单名袭集合
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60013] 该订单已审核 
	 * 				[success: false status:60002] 单号重复 
	 * 				[success: false status:60043] 该报关单已生成订单
	 * 				[success: true 	data: 订单表头id]  操作成功
	 * 			 </pre>
	 */
	ResponseMessage saveSalesOrderAndDetails(SalesOrder salesOrder,List<SalesDetails> salesDetailss);
	
}
