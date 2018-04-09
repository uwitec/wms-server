package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 出库货物信息service
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryPackingListService extends BaseService<DeliveryPackingList> {

	/**
	 * 根据出货单号及其他条件查询拣货信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态
	 * @param deliveryLotNo
	 *            出货批次
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryPackingListByNoteNo(String deliveryNoteNo, Integer status, String deliveryLotNo);

	/**
	 * 根据出货单号判断是否全部拣货
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	status:001:无拣货信息，002代表有拣货信息但未拣货确认，003代表有拣货信息并且已经拣货] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryPackingListBydeliveryNoteNo(String deliveryNoteNo);

	/**
	 * 根据明细UUID判断有无拣货信息
	 * 
	 * @param asnUuid
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	status:001:无拣货信息，002代表有拣货信息但未拣货确认，003代表有拣货信息并且已经拣货确认] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryPackingListCountByAsnUUID(String asnUuid);

	/**
	 * 根据出货单号删除入库货物信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60009] 出货货物删除失败，请确认出货单状态
	 * 				[success: false status:60010] 修改库存信息时出错
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	ResponseMessage deleteBatchByNoteNo(String deliveryNoteNo);

	/**
	 * 根据库存UUID查询拣货信息
	 * 
	 * @param stockUuid
	 *            库存UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryPackingListByStockUUID(String stockUuid);

	/**
	 * 根据条件查询拣货信息
	 * 
	 * @param parameters
	 *            参数条件
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体Dto>] 操作成功
	 *         </pre>
	 */
	ResponseMessage getDeliveryPackingList(Map<String, Object> parameters);
	

}
