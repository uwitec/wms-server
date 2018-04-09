package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.dto.MapParameters;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 收货明细service
 * 
 * @Description:
 * @author su
 * @date 2017年8月25日
 * @version V1.0
 */
public interface ReceivingNoteAsnService extends BaseService<ReceivingNoteAsn> {

	/**
	 * 根据收货单号查询收货明细
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 * @param status
	 *            状态码
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         <li>若在操作的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>操作成功,ResponseMessage中的success:true,data:
	 *         {@code List<ReceivingNoteAsn>}</li>
	 *         </ul>
	 */
	ResponseMessage getReceivingNoteAsnByNoteNo(String receivingNoteNo, Integer status);

	/**
	 * 根据入库单号查询合并后明细预告的预收总量。验收数量、合并批次号、品名、料号、包装数量
	 * 
	 * @param receivingNoteNo
	 *            入库单号
	 * @return ResponseMessage对象
	 *         <pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true,Data:明细预告实体集合]
	 *         </pre>
	 */
	ResponseMessage getReceivingNoteAsnByreceivingNoteNo(String receivingNoteNo, Integer status);

	/**
	 * 明细合并
	 * 
	 * @param asnUuids
	 *            明细UUID集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10004,msg:参数为null]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:false,Status:60022,msg:有记录已分配]
	 * 					[Success:false,Status:60025,msg:存在单条分配的记录]
	 * 					[Success:false,Status:60028,msg:明细商品种类不同]
	 * 					[Success:true,Data:明细实体]
	 *         </pre>
	 */
	ResponseMessage mergeReceivingNoteAsn(List<String> asnUuids);

	/**
	 * 根据合并批次号修改明细预告到货状态并修改收货单状态
	 * 
	 * @param status
	 *            状态
	 * @param mergeLotNo
	 *            合并批次号集合
	 * @param receivingNoteNo
	 *            入库单号
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true,Data:影响行数]
	 *         </pre>
	 */
	public ResponseMessage updateIsArrivedByMergeLotNo(Integer status, List<String> mergeLotNos,
			String receivingNoteNo);

	/**
	 * 根据合并批次号撤销包装分配
	 * 
	 * @param mergerLotNo
	 *            合并批次号
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0，修改状态失败]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true]
	 *         </pre>
	 */
	ResponseMessage revokePacking(List<String> mergeLotNos);

	/**
	 * 根据合并批次号撤销到货
	 * 
	 * @param mergerLotNo
	 *            合并批次号
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10012,msg:参数为null]
	 * 					[Success:false,Status:10014,msg:影响行数为0，修改失败]
	 * 					[Success:false,Status:60027,msg:已有记录上架]
	 * 					[Success:false,Status:10003,msg:异常信息]
	 * 					[Success:true]
	 *         </pre>
	 */
	ResponseMessage revokeArrived(List<String> mergeLotNos);

	/**
	 * 根据明细UUID删除明细、入库货物信息（事务）
	 * 
	 * @param uuid
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         <li>若在根据uuid删除明细、入库货物信息（事务）的过程中出现了异常信息，
	 *         ResponseMessage中的success为false、 status: 10003、msg：异常的说明信息</li>
	 *         <li>若收货单状态为入库则无法删除，ResponseMessage中的success为false、 status: 60003
	 *         </li>
	 *         <li>根据uuid删除明细、入库货物信息（事务）成功,ResponseMessage中的success:true,data:
	 *         {@code true}</li>
	 *         </ul>
	 */
	ResponseMessage delete(String uuid);

	/**
	 * 根据asnUUID删除明细，并添加多条明细
	 * 
	 * @param asnUuid
	 * @param asnList
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若字符串参数为null或者空字符串，ResponseMessage中的success为false、 status:
	 *         10012</li>
	 *         <li>若参数集合为null，ResponseMessage中的success为false、 status: 10004</li>
	 *         <li>若在根据asnUUID删除明细，并添加多条明细的过程中出现了异常信息，
	 *         ResponseMessage中的success为false、 status: 10003、msg：异常的说明信息</li>
	 *         <li>根据asnUUID删除明细，并添加多条明细成功,ResponseMessage中的success:true,data:
	 *         {@code true}</li>
	 *         </ul>
	 * 
	 */
	ResponseMessage batchByAsn(String asnUuid, List<ReceivingNoteAsn> asnList);

}
