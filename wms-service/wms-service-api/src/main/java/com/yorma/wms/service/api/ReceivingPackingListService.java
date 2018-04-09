package com.yorma.wms.service.api;

import java.util.Date;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.service.base.api.BaseService;


/**
 * 入库货物信息service
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */
public interface ReceivingPackingListService extends BaseService<ReceivingPackingList> {

	/**
	 * 批量添加(修改)入库明细并将收货单状态修改为已到货（状态码：30）（添加时生成包装条码，修改时生成集装条码）
	 * @param receivingPackingLists		
	 * @return
	 * 		返回ResponseMessage对象
	 *  	<ul>
	 *          <li>若参数集合为null，ResponseMessage中的success为false、
	 *         status: 10004</li>
	 *         <li>若在批量添加入库明细并将收货单状态修改为已到货的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>批量添加入库明细并将收货单状态修改为已到货成功,ResponseMessage中的success:true,data:
	 *         {@code true}</li>
	 *       </ul>
	 */
	ResponseMessage saveBatch(List<ReceivingPackingList> receivingPackingLists);
	
	
	/**
	 * 查询（条件）入库货物信息
	 * @param receivingNoteNo
	 *            	收货单号
	 * @return 	      
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *          <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在查询（条件）入库货物信息的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>查询（条件）入库货物信息成功,ResponseMessage中的success:true,data:
	 *         {@code List<ReceivingPackingList>}</li>
	 *       </ul>
	 */
	ResponseMessage getReceivingPackingListByNoteNo(String receivingNoteNo);
	
	/**
	 * 新增入库货物信息同时修改入库明细信息、修改入库单状态为30（已到货,一键到货上架）
	 * @param receivingPackingLists	
	 * 						入库货物信息集合
	 * @param receivingNoteAsns
	 * 						入库明细集合
	 * @return
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *          <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在操作过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>操作成功,ResponseMessage中的success:true</li>
	 *       </ul>
	 */
	ResponseMessage oneSaveBatch(List<ReceivingPackingList> receivingPackingLists,List<ReceivingNoteAsn> receivingNoteAsns);
	
	/**
	 * 根据入库单号、状态。开始日期。截止日期查询入库货物信息
	 * @param receivingNoteNo
	 * 				入库单号
	 * @param status
	 * 				状态码
	 * @param startDate
	 * 				起始日期
	 * @param endDate
	 * 				截止日期
	 * @return
	 * 				返回ResponseMessage对象
	 *  	<ul>
	 *          <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、
	 *         status: 10012</li>
	 *         <li>若在操作过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>操作成功,ResponseMessage中的success:true,data:
	 *         {@code List<ReceivingPackingList> }</li>
	 *       </ul>
	 */
	ResponseMessage getReceivingPackingListByNoteNo(String receivingNoteNo,Integer status,Date startDate,Date endDate);
	
	/**
	 * 添加多条明细和入库货物信息并删除单条明细（单条包装分配）
	 * @param receivingNoteAsns	入库明细预告集合
	 * @param asnUuid	入库明细预告UUID
	 * @param warehouse	仓库代码
	 * @param createBy	创建人
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null或空字符串]
	 * 					[Success:false,Status:10020,msg:单号生成失败]
	 * 					[Success:false,Status:60024,msg:该记录已到货，请撤销后操作]
	 * 					[Success:false,Status:60025,msg:请核实分配方式]
	 * 					[Success:false,Status:60022,msg:该记录已分配]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60036,msg:包装数量为异常]
	 * 					[Success:true] 操作成功
	 * 				</pre>
	 */
	ResponseMessage saveReceivingPackingListAndAsn(List<ReceivingNoteAsn> receivingNoteAsns, String asnUuid,String warehouse,String createBy);
	
	/**
	 * 添加多条明细和修改多条入库货物信息（合并包装分配）
	 * @param asnUuids		明细UUID集合
	 * @param receivingNoteAsn	合并明细实体
	 * @param warehouse	仓库代码
	 * @param createBy	创建人
	 * @return ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012,msg:参数为null或空字符串]
	 * 					[Success:false,Status:10020,msg:单号生成失败]
	 * 					[Success:false,Status:60022,msg:该记录已分配]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60036,msg:包装数量为异常]
	 * 					[Success:true] 操作成功
	 * 				</pre>
	 */
	ResponseMessage saveReceivingPackingListAndAsns(List<String> asnUuids, ReceivingNoteAsn receivingNoteAsn,String warehouse,String createBy);
	
}
