package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 收货单service
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */

public interface ReceivingNoteService extends BaseService<ReceivingNote> {

	/**
	 * 添加(修改)收货单
	 * 
	 * @param receivingNote
	 *            添加信息
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null，ResponseMessage中的success为false、 status: 10004</li>
	 *         <li>若在添加（修改）过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>添加（修改）失败但无异常,ResponseMessage中的success:false,data: status:
	 *         10014</li>
	 *         <li>收货单号生成失败,ResponseMessage中的success:false,data: status: 10020
	 *         </li>
	 *         <li>添加成功,ResponseMessage中的success:true,data:{@code "id,收货单号"}
	 *         修改成功,ResponseMessage中的success:true,data:{@code "id"}</li>
	 *         </ul>
	 */
	@Override
	ResponseMessage save(ReceivingNote entity);

	/**
	 * 查询（条件）收货单
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在根据条件获取储位实体集合的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>根据条件获取储位实体集合成功,ResponseMessage中的success:true,data:
	 *         {@code List<ReceivingNoteDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getReceivingNote(Map<String, Object> map, Map<String, Object> map1);
	
	/**
	 * 根据条件查询收货单
	 * @param map	将查询条件放入map集合
	 * @param map1	将查询条件放入map1集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false, Status:10003,Msg:异常信息]
	 * 					[Success:false, Status:10012,Msg:参数[pageNumber]或[pageSize]为null]
	 * 					[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 					[Success:true ,Data:new ResponseData<>(当前页, 总页数, 总条数, List<入库单Dto实体>]
	 * 				</pre>
	 */
	ResponseMessage getReceivingNote(Map<String, Object> map, Map<String, Object> map1, Integer pageNumber, Integer pageSize);

	/**
	 * 修改状态
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @param status
	 *            状态码
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在修改状态过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>修改状态成功,ResponseMessage中的success:true,data: {@code 影响行数}</li>
	 *         <li>修改状态失败,ResponseMessage中的success:false,status: "60006"</li>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         </ul>
	 */
	ResponseMessage updateStatus(String receivingNoteNo, int status);
	
	/**
	 * 修改状态
	 * @param receivingNoteNo 收货单号
	 * @param status 状态码
	 * @param lastName 修改人
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在修改状态过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>修改状态成功,ResponseMessage中的success:true,data: {@code 影响行数}</li>
	 *         <li>修改状态失败,ResponseMessage中的success:false,status: "60006"</li>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         </ul>
	 */
	ResponseMessage updateStatus(String receivingNoteNo, int status,String lastName);

	/**
	 * 根据入库单号弃审
	 * @param deliveryNoteNo	出库单号
	 * @return	Responsemessage对象
	 * 		<pre>
	 * 			[Successs:false ,Status:10012 , mag:入库单号为null或者空字符串]
	 * 			[Successs:false ,Status:60021 , mag:该入库单已分配，不可撤审]
	 * 			[Successs:true ,data:影响行数 ]
	 * 		</pre>
	 */
	ResponseMessage revokeAudit(String receivingNoteNo);
	
	/**
	 * 根据收货单号删除收货单、明细、入库货物信息（事务）
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @return
	 * 		<ul>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         <li>若在删除过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>若在删除过程中状态以为入库，ResponseMessage中的success为false、 status: 60003
	 *         </li>
	 *         <li>删除成功,ResponseMessage中的success:false,data: {@code true}</li>
	 *         <li>连接失败,ResponseMessage中的success:false,data: status: 60005</li>
	 *         </ul>
	 */
	ResponseMessage delete(String receivingNoteNo);
	
	/**
	 * 保存入库单及明细(入库订单生成)
	 * @param receivingNote
	 * @param receivingNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60002] 单号重复 
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	public ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,List<ReceivingNoteAsn> receivingNoteAsns,String po);

	/**
	 * 保存入库单及明细
	 * @param receivingNote
	 * @param receivingNoteAsns
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10004] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60013,msg:该入库订单已审核] 
	 * 				[success: false status:60002,msg:单号重复]
	 * 				[success: false status:10014] 影响行数为0，保存失败
	 * 				[success: true 	data:入库单表头id] 操作成功
	 *         </pre>
	 */
	ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,List<ReceivingNoteAsn> receivingNoteAsns);
	
}
