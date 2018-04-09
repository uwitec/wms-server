package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 出货单service
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryNoteService extends BaseService<DeliveryNote> {

	/**
	 * 添加（修改）出货单
	 * 
	 * @param entity
	 *            收货单实体
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success:false,status:10020]:生成单号失败
	 * 				[success: false status:60020，msg:该出库单已审核] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:添加：出库单表头id,出库单号；修改：id] 操作成功
	 *         </pre>
	 */
	@Override
	ResponseMessage save(DeliveryNote entity);

	/**
	 * 查询（条件）出货单
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *          <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<出库单Dto实体>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryNote(Map<String, Object> map);
	
	/**
	 *  根据条件查询出货单
	 * @param map	将查询条件放入map集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 *          <pre>
	 *          	[Success: false,Status:10012] 参数[pageNumber]或[pageSize] 为 null
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 				[success: true 	data:new ResponseData<>(当前页, 总页数, 总条数, List<出库单Dto实体>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryNote(Map<String, Object> map,Integer pageNumber, Integer pageSize);

	/**
	 * 根据出货单号查询出货单状态
	 * 
	 * @param deliveryNoteNo
	 *            收货单号
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:出库单状态码] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryNoByNoteNo(String deliveryNoteNo);

	/**
	 * 根据出货单号修改出货单状态
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态码
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60006，msg:状态不能跳过] 
	 * 				[success: false status:10014] 影响行数为0，修改出库单状态失败
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	ResponseMessage updateStatus(String deliveryNoteNo, int status);
	
	/**
	 * 根据出货单号修改出货单状态
	 * @param deliveryNoteNo  出货单号
	 * @param status  状态码
	 * @param lastName 修改人
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60006，msg:状态不能跳过] 
	 * 				[success: false status:10014] 影响行数为0，修改出库单状态失败
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	ResponseMessage updateStatus(String deliveryNoteNo, int status,String lastName);
	
	/**
	 * 根据出库单号弃审
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return Responsemessage对象
	 * 
	 *         <pre>
	 * 			[Success:false ,Status:10012 , mag:出库单号为null或者空字符串]
	 * 			[Success:false ,Status:60021 , mag:该出库单已分配，不可撤审]
	 * 			[Success:false ,Status:10003 , msg:异常信息]
	 * 			[Successs:true ,data:影响行数 ]
	 *         </pre>
	 */
	ResponseMessage revokeAudit(String deliveryNoteNo);

	/**
	 * 删除出货单（明细、入库信息并核减预分配或已分配数量
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return Responsemessage对象
	 *         <pre>
	 * 			[Success:false ,Status:10012 , mag:出库单号为null或者空字符串]
	 * 			[Success:false ,Status:60003 , mag:该出库单拣货确认，不可撤审]
	 * 			[Success:false ,Status:10014 , msg:修改订单状态失败]
	 * 			[Success:false ,Status:10003 , msg:异常信息]
	 * 			[Successs:true ,data:true]
	 *         </pre>
	 */
	ResponseMessage delete(String deliveryNoteNo);
	
	/**
	 * 保存出库单及明细(出库订单生成)
	 * 
	 * @param deliveryNote
	 * @param deliveryNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: false status:60002，msg:单号重复] 
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote,List<DeliveryNoteAsn> deliveryNoteAsns,String so);

	/**
	 * 保存出库单表头及明细
	 * @param deliveryNote		出库单表头实体
	 * @param deliveryNoteAsns	出库单明细预告集合
	 * @return ResponseMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013，msg:该出库单已审核] 
	 * 				[success: false status:60002，msg:单号重复] 
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:出库单表头id] 操作成功
	 *         </pre>
	 */
	ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote,List<DeliveryNoteAsn> deliveryNoteAsns);
	
}
