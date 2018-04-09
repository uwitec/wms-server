package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.dto.MapParameters;

/**
 * 报表Service
 * @author su
 * 2018年3月27日
 */
public interface ReportFormServicce {

	/**
	 * 入库明细报表
	 * @param parameters 参数实体
	 * @return ResponseMessage对象
	 * 			<pre>
	 * 				[Success:false,Status:10012,msg:参数为null]
	 * 				[Success:false,Status:10003,msg:异常信息]
	 * 				[Success:true,Data:ResponseData<>{当前页，总页数，总条数，入库明细集合}]
	 *         </pre>
	 */
	ResponseMessage listReceivingNoteAsn(MapParameters parameters);
	
	/**
	 * 出库明细报表
	 * @param parameters 参数实体
	 * @return 返回ResponseMessage对象
	 * 			<pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  data:ResponseData<>{当前页，总页数，总条数，明细实体集合}]
	 * 			</pre>
	 */
	ResponseMessage listDeliveryNoteAsn(MapParameters parameters);
	
	/**
	 * 出库报表
	* @param parameters 参数实体
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 参数为空
	 * 				[success: true 	data:ResponseDate<>{当前页，总页数，总条数，拣货Dto实体集合}] 操作成功
	 *         </pre>
	 */
	ResponseMessage listDeliveryPackingList(MapParameters parameters);

	/**
	 * 查询料号明细收发存
	 * @param parameters
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:10012] 参数为空
	 * 				[success: true 	data:分页：ResponseDate<>{当前页，总页数，总条数，料号明细收发存Dto实体集合}，未分页：料号明细收发存Dto实体集合] 操作成功
	 *         </pre>
	 */
	ResponseMessage listInAndOutOfStock(MapParameters parameters);
	
}
