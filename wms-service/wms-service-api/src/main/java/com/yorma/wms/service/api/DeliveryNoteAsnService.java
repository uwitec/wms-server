package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 出货预告明细service
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public interface DeliveryNoteAsnService extends BaseService<DeliveryNoteAsn> {

	/**
	 * 根据收货单号查询出货明细Dto
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true,Data:List<明细Dto实体>]
	 * 				</pre>
	 */
	ResponseMessage getDeliveryNoteAsnByNoteNo(String deliveryNoteNo);

	/**
	 * 根据uuid删除出货预告明细和拣货信息
	 * 
	 * @param uuid
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 * 				[Success:false,Status:10012]参数为null
     *              [success:false,status:10003,msg:异常说明信息]
     *              [success:false,status:60003,msg:有记录已拣货确认]
     *              [success:false,status:60010,msg:修改库存时失败]
     *              [success:true]
	 * 			</pre>
	 */
	ResponseMessage removeByUUID(String uuid);

	/**
	 * 根据明细UUID查询明细信息
	 * 
	 * @param UUID
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true，Data:明细实体]
	 * 				</pre>
	 */
	ResponseMessage getDeliveryNoteAsnByUUID(String uuid);
	
	/**
	 * 根据单号查询相同商品合并后的明细（暂定）
	 * @param deliveryNoteNo	出库单号
	 * @return 返回ResponseMessage对象
	 *         		<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true，Data:List<明细Dto实体>]
	 * 				</pre>
	 */
	ResponseMessage getDeliveryNoteAsnMergeByDeliveryNoteNo(String deliveryNoteNo);
	
	/**
	 * 判断单条或多条明细是否能分配和多条明细是否符合合并条件
	 * @param deliveryNoteAsnUuid
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:false,status:60029,msg:有记录已拣货确认]
     *                  [success:false,status:60028,msg:明细商品种类不同]
     *                  [success:true]
	 * 				</pre>
	 */
	ResponseMessage  judgeDeliveryNoteAsn(List<String> deliveryNoteAsnUuids);
	
	/**
	 * 根据明细uuid合并明细信息
	 * @param deliveryNoteAsnUuid
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
     *                  [success:false,status:10003,msg:异常说明信息]
     *                  [success:true,data:合并后明细实体]
	 * 				</pre>
	 */
	ResponseMessage getDeliveryNoteAsnMergeByAsnUUID(List<String> deliveryNoteAsnUuid);
	
	/**
	 * 根据出货单号查询明细分配方式
	 * 
	 * @param deliveryNoteNo
	 * @return 返回ResponseMessage对象
	 * 
	 *         <pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  status: 001(没有分配过明细)、002(单个明细分配)、003(合并明细分配)]
	 *         </pre>
	 */
	ResponseMessage getIsMergedByNoteNo(String deliveryNoteNo);
	
	/**
	 * 根据出库单号统计明细合并后的品名，合并批次号，商品UUID，料号，总预收数量，总出库数量
	 * @param deliveryNoteNo	出库单号
	 * @return	返回ResponseMessage对象
	 * 			<pre>
	 * 			[Success: false status:10012] 参数为null
	 * 			[Success: false	status:10003] 异常信息
	 * 			[Success： true  data:List<明细Dto>]
	 * 			</pre>
	 */
	ResponseMessage getMergeDeliveryNoteAsn(String deliveryNoteNo);
	
	
}
