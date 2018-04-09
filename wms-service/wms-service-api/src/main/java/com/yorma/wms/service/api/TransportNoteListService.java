package com.yorma.wms.service.api;

import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.TransportNoteList;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;
import com.yorma.wms.service.base.api.BaseService;

public interface TransportNoteListService extends BaseService<TransportNoteList> {

	/**
	 * 根据运送单号查询配送信息明细
	 * @param transportNoteNo
	 * @return	返回ResponseMessage对象
	 * 			<ul>
	 * 			<li>若在参数为空或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在根据运送单号查询配送信息明细的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据运送单号查询配送信息明细成功,ResponseMessage中的success:true,data:
	 *         {@code List<TransportNoteListDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getTransportNoteListDtoByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据运送单号查询配送信息明细
	 * @param transportNoteNo
	 * @return 返回ResponseMessage对象
	 */
	ResponseMessage ListByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 根据配送信息UUID删除配送信息明细同时修改拣货信息中的运送单号
	 * @param uuid
	 * @return 返回ResponseMessage对象
	 * 			<ul>
	 * 			<li>若在参数为空或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在根据配送信息UUID删除配送信息明细的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据配送信息UUID删除配送信息明细成功,ResponseMessage中的success:true,data:
	 *         {@code List<TransportNoteListDto>}</li>
	 *         </ul>
	 */
	ResponseMessage removeTransportNoteListByUUID(String uuid);
	
	/**
	 * 根据拣货信息生成配送明细同时添加拣货信息中的运送单号
	 * @param packingListDtos	拣货信息Dto集合
	 * @param transportNoteNo	配送单号	
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[success:false,status:10012]参数为null或空字符串
	 * 					[success:false,status:10003]异常信息
	 * 					[success:false,status:10014]影响行数为0，保存失败
	 * 					[success:true，date:true]操作成功
	 * 				</pre>
	 */
	ResponseMessage saveTransportNoteList(List<DeliveryPackingListDto> packingListDtos, String transportNoteNo);
	
	
	/**
	 * 根据运送单号查询配送明细
	 * @param transportNoteNo	配送单号
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[success:false,status:10012]参数为null或空字符串
	 * 					[success:false,status:10003]异常信息
	 * 					[success:true，date:List<配送明细实体>]操作成功
	 * 				</pre>
	 */
	ResponseMessage getTransportNoteListByTransportNoteNo(String transportNoteNo);
	
	
}
