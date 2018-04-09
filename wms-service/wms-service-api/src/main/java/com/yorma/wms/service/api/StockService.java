package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.StockDto;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 库存service
 * 
 * @Description:
 * @author su
 * @date 2017年8月10日
 * @version V1.0
 */
public interface StockService extends BaseService<Stock> {

	/**
	 * 查询（条件）库存
	 * 
	 * @param map
	 *            将查询条件放入map集合
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在查询（条件）库存的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>查询（条件）库存成功,ResponseMessage中的success:true,data:
	 *         {@code List<Stock>}</li>
	 *         </ul>
	 */
	ResponseMessage getStock(Map<String, Object> params);
	
	/**
	 * 查询库存（累计相同料号。品名、包装键、入库数并且未分配的库存量）
	 * @param params
	 * @return 返回ResponseMessage对象
	 * 			<pre>
	 * 				[Success:false, Status:10003,Msg:异常信息]
	 * 				[Success:true, Data:List<库存VO实体>]
	 * 			</pre>
	 */
	ResponseMessage listStockVO(Map<String, Object> params);
	
	
	/**
	 * 根据条件查询库存信息
	 * @param params	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	返回ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false ,Status:10003,Msg:异常信息]
	 * 					[Success:false ,Status:10012,Msg:参数[pageNumber]或[pageSize] 为 null]
	 * 					[success: true data:new ResponseData<>(当前页, 总页数(1页), 总条数, null)] 无数据(总条数为0)
	 * 					[Success:true , Data:new ResponseData<>(当前页, 总页数, 总条数, List<库存实体>]
	 * 				</pre>
	 */
	ResponseMessage getStock(Map<String, Object> params, Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据条件查询库存总在库量，总预分配量，总已分配量，总包装数，可用库存量
	 * @param params	参数集合
	 * @return	返回ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false ,Status:10003,Msg:异常信息]
	 * 					[Success:true , Data:库存VO实体]
	 * 				</pre>
	 */
	ResponseMessage getStockQty(Map<String, Object> params);
	
	/**
	 * 自动计算分配(单/合)
	 * @param deliveryStrategy
	 * @param paramtes
	 * @param asnUuids
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若在自动计算可分配量的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>若库存不足，ResponseMessage中的success为false，status：60007</li>
	 *         <li>若没有库存，responseMessage中的success为false，status：60008</li>
	 *         <li>自动计算可分配量成功,ResponseMessage中的success:true,data:
	 *         {@code List<StockDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getSelfMotionStock(String deliveryStrategy, Map<String, String> paramtes,List<String> asnUuids);

	/**
	 * 根据商品UUID、明细UUID、仓库代码查询库存
	 * 
	 * @param goodsUuid
	 * @param asnUuid
	 * @param warehouseCode
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若库存不足，ResponseMessage中的Success为false、status：60007</li>
	 *         <li>若在根据商品UUID、明细UUID、仓库代码查询库存的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>根据商品UUID、明细UUID、仓库代码查询库存成功,ResponseMessage中的success:true,data:
	 *         {@code List<StockDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getStockByGoodsUUID(String goodsUuid, String asnUuid, String warehouseCode);
	/**
	 * 根据出库策略，生产批次号，商品UUID，主单位，仓库代码查询可分配库存量
	 * @param deliveryStrategy	出库策略
	 * @param goodsUuid			商品UUID
	 * @param mu				主单位
	 * @param warehouseCode		仓库代码
	 * @param manufactureLotNo	生产批次号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若在根据商品UUID、明细UUID、仓库代码查询库存的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>根据商品UUID、明细UUID、仓库代码查询库存成功,ResponseMessage中的success:true,data:
	 *         {@code List<StockDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getStockByGoodsUUID(String deliveryStrategy,String goodsUuid, String mu, String warehouseCode,String manufactureLotNo);
	
	/**
	 * 根据商品UUID、出货单号查询库存
	 * @param deliveryNoteNo
	 * @param goodsUuid
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若已无库存，ResponseMessage中的Success为false、status：60008</li>
	 *         <li>若在过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>操作成功,ResponseMessage中的success:true,data:
	 *         {@code List<StockDto>}</li>
	 *         </ul>
	 */
	ResponseMessage getStockByNoteNoAndGoodsUUID(String deliveryNoteNo,String goodsUuid);
	
	/**
	 * 修改预分配量（拣货分配）
	 * @param stockdtoList
	 * @param asnUuids
	 * @param isMerged
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
	 * 					[Success:false,Status:10014]影响行数为0
	 * 					[Success:false,Status:60010]并发问题修改预分配量失败
	 * 					[Success:false,Status:60007]库存不足
	 * 					[Success:false,Status:10003]异常信息
	 *					 [Success:false,Status:60032]请填写分配量
	 * 					[Success:true]操作成功
	 * 				</pre>
	 */
	ResponseMessage updatePreAllocationStock(List<StockDto> stockdtoList,List<String> asnUuids,boolean isMerged);
	
	/**
	 * 根据合并明细撤销拣货分配
	 * @param mergeLotNos	合并明细集合
	 * @return		ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false,Status:10012]参数为null
	 * 					[Success:false,Status:10014]影响行数为0
	 * 					[Success:false,Status:60010]并发问题修改预分配量失败
	 * 					[Success:false,Status:60031]有记录已拣货确认
	 * 					[Success:false,Status:10003]异常信息
	 * 					[Success:true]操作成功
	 * 				</pre>
	 */
	ResponseMessage revokePreAllocationStock(List<String> mergeLotNos);
	

	/**
	 * 已分配核增同时预分配量核减并修改将是否拣货信息修改为拣货
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在已分配核增同时预分配量核减的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>已分配核增同时预分配量核减成功,ResponseMessage中的success:true,data:
	 *         {@code true}</li>
	 *         </ul>
	 */
	ResponseMessage updateAllocatedStock(List<String> deliveryPackingListUuids);

	/**
	 * 在库量核减同时已分配量核减并修改拣货信息状态位
	 * 
	 * @param deliveryPackingListUuids
	 *            入库货物信息UUID集合
	 * @param deliveryLotNo
	 *            出库批次
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在库量核减同时已分配量核减的过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>在库量核减同时已分配量核减成功,ResponseMessage中的success:true,data:
	 *         {@code boolean:true}</li>
	 *         </ul>
	 */
	ResponseMessage updateInstock(List<String> deliveryPackingListUuids, String deliveryLotNo);

	/**
	 * 批量添加库存并修改收货单状态
	 * 
	 * @param stockList
	 *            库存集合
	 * @param receivingNoteNo
	 *            收货单号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在字符串为null或者空字符，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若在集合为null，ResponseMessage中的success为false、 status: 10004</li>
	 *         <li>若在批量添加库存并修改收货单状态过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>若在收货单状态不为到货状态时，ResponseMessage中的success为false、 status: 60006
	 *         </li>
	 *         <li>批量添加库存并修改收货单状态成功,ResponseMessage中的success:true,data:
	 *         {@code true}</li>
	 *         </ul>
	 */
	ResponseMessage addBatchByStockAndUpdateStatus(List<Stock> stockList, String receivingNoteNo);

	/**
	 * 批量添加库存
	 * @param params
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在字符串为null或者空字符，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若在集合为null，ResponseMessage中的success为false、 status: 10004</li>
	 *         <li>若集合数据错误，ResponseMessage中的success为false、 status: 60011</li>
	 *         <li>若在批量添加库存并同时添加收货单、明细、入库货物信息过程中出现了异常信息，ResponseMessage中的success为false、
	 *         status: 10003、msg：异常的说明信息</li>
	 *         <li>批量添加库存成功,ResponseMessage中的success:true </li>
	 *         </ul>
	 */
	ResponseMessage createBatchByStockAndReceiving(List<Stock> params);
	
	/**
	 * 库存汇总
	 * 
	 * @param map
	 *            条件
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若在库存汇总的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>库存汇总成功,ResponseMessage中的success:true,data:
	 *         {@code List<Stock>}</li>
	 *         </ul>
	 */
	ResponseMessage getStockStatistics(Map<String, Object> params);
	
	/**
	 * 库存汇总（分页）
	 * @param parames	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return	ResponseMessage对象
	 * 			  <pre>
	 * 				[success:false, status:10012,msg:参数为null]
	 * 				[success:false, status:10003,msg:异常信息]
	 * 				[success:true, Data:ResponseData<>{当前页，总页数，总条数，库存VO实体集合}]
	 * 			  </pre>
	 */
	ResponseMessage getStockStatistics(Map<String, Object> parames, Integer pageNumber, Integer pageSize);
	
	/**
	 * 移库操作
	 * @param transferNo	移库单号
	 * @return	返回Responsemessage对象
	 * 				[Success:false Status:10012] 参数为null
	 * 				[Success:false Status:10020] 单号生成失败
	 * 				[Success:false Status:10014] 返回影响行数为0
	 * 				[Success:false Status:60008] 已无库存
	 * 				[Success:false Status:60007] 库存不足
	 * 				[Success:false Status:60010] 并发问题
	 * 				[Success:false Status:10003] 异常信息
	 * 				[Success:false Status:60016] 该移库记录已被审核，不能进行该操作
	 * 				[Success:true] 操作成功
	 */
	ResponseMessage stockTransfer(String transferNo);
	
	
	
}
