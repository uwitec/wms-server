package com.yorma.wms.service.api;

import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.TransportNote;
import com.yorma.wms.service.base.api.BaseService;

public interface TransportNoteService extends BaseService<TransportNote> {

	/**
	 * 根据条件查询运送单信息
	 * 
	 * @param parameters
	 *            条件
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在根据条件查询运送单信息的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据条件查询运送单信息成功,ResponseMessage中的success:true,data:
	 *         {@code List<TransportNoteDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getTransportNoteDto(Map<String, Object> parameters);
	
	/**
	 * 根据条件查询运送单信息
	 * @param parameters  条件
	 * @param pageNumber  当前页
	 * @param pageSize	  每页显示条数
	 * @return 返回ResponseMessage对象
	 *         <pre>
	 *         	  [success:false, status:10012, msg:参数为空]
	 *         	  [success:false, status:10003, msg:异常信息]
	 *         	  [success:true, data:ResponseDate<>{当前页，总页数，总条数，运送单Dto集合}]
	 *         </pre>
	 */
	ResponseMessage getTransportNoteDto(Map<String, Object> parameters,Integer pageNumber,Integer pageSize);

	/**
	 * 根据运送单号删除运送单信息
	 * 
	 * @param transportNoteNo
	 *            运送单号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若c参数为空或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在据运送单号删除运送单信息的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>据运送单号删除运送单信息成功,ResponseMessage中的success:true,data:
	 *         {@code 影响行数}</li>
	 *         </ul>
	 */
	ResponseMessage removeTransportNoteByTransportNoteNo(String transportNoteNo);
	
	/**
	 * 修改状态，如果状态为装载则修改拣货信息为出库
	 * @param transportNoteNo
	 * @param status
	 * @return  返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为空或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在修改状态，如果状态为装载则修改拣货信息为出库的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>修改状态，如果状态为装载则修改拣货信息为出库成功,ResponseMessage中的success:true,data:
	 *         {@code boolean值true}</li>
	 *         </ul>
	 */
	ResponseMessage updateStatusByTransportNoteNo(String transportNoteNo,int status);
	
	/**
	 * 根据运送单号查询运送信息
	 * @param transportNoteNo	运送单号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为空或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在根据运送单号查询运送信息的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据运送单号查询运送信息成功,ResponseMessage中的success:true,data:
	 *         {@code TransportNoteDto}</li>
	 *         </ul>
	 */
	ResponseMessage getTransportNoteDtoByTransportNoteNo(String transportNoteNo);
	
	
	
	
}
