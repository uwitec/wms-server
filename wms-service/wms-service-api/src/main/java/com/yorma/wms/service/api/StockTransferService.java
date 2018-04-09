package com.yorma.wms.service.api;

import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.entity.StockTransfer;
import com.yorma.wms.entity.StockTransferList;
import com.yorma.wms.service.base.api.BaseService;

/**
 * 移库单表头Service 
 * @author Su
 * 2017年12月13日
 */
public interface StockTransferService extends BaseService<StockTransfer> {

	/**
	 * 根据条件查询移库单表头
	 * @param parames	参数集合
	 * @return	Responsemessage对象
	 * 			 <pre>
	 * 				[Success：false	Status:10003]	异常信息
	 * 				[Success：true	Data：List<移库单表头实体>]
	 * 			 </pre>
	 */
	ResponseMessage getStockTransfer(Map<String, Object> parames);
	
	/**
	 * 根据条件查询移库单表头(分页)
	 * @param parames	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return	Responsemessage对象
	 * 			 <pre>
	 * 				[Success：false	Status:10003]	异常信息
	 * 				[Success：true	Data：ResponseData<>(当前页, 总页数, 总条数, List<移库单表头实体>)]
	 * 			 </pre>
	 */
	ResponseMessage getStockTransfer(Map<String, Object> parames,Integer pageNumber,Integer pageSize);
	
	/**
	 * 保存移库单表头和表体
	 * 
	 * @param stockTransfer
	 *            移库单表头实体
	 * @param stockTransferLists
	 *            移库单表体实体集合
	 * @return ResponseMessage对象
	 * 		   [Success：false Status：10012] 参数为null
	 *         [Success：false Status:10003] 异常信息 
	 *         [Success：false Status:10014] 保存时影响行数为0 
	 *         [Success:false Status:60016] 该移库记录已被审核，不能进行该操作
	 *         [Success:false Status:60019] 移库单号重复 
	 *         [Success:false Status:60035] 移库单字段NULL值，请核实信息
	 *         [Success：true, Data:移库单表头id]
	 *         操作成功
	 */
	ResponseMessage saveStockTransFerAndList(StockTransfer stockTransfer,List<StockTransferList> stockTransferLists);
	
	/**
	 * 根据移库单号删除移库单表头和表体
	 * @param transferNo	移库单号
	 * @return	responsemessage 对象
	 * 				[Success：false Status：10012] 参数为null
	 * 				[Success：false	Status:10003] 异常信息
	 * 				[Success：false	Status:60016] 该移库记录已被审核，不能进行该操作
	 * 				[Success：true  Data:影响行数] 操作成功
	 */
	ResponseMessage removeStockTransferAndListByTransferNo(String transferNo);
	
	/**
	 * 根据移库单号查询移库单表头
	 * @param transferNo	移库单号
	 * @return	responsemessage 对象
	 * 				[Success：false Status：10012] 参数为null
	 * 				[Success：false	Status:10003] 异常信息
	 * 				[Success：true  Data:移库单表头实体] 操作成功
	 */
	ResponseMessage getStockTransferByTransferNo(String transferNo);
	
	/**
	 * 撤销移库操作
	 * 
	 * @param transferNo
	 *            移库单号
	 * @return responseMessage对象 [Success：false Status：10012] 参数为null
	 *         [Success：false Status：60017] 库存已被使用，无法撤销移库操作 
	 *         [Success：false Status：60018] 该移库信息还未审核 
	 *         [Success：false Status：60010] 并发问题，操作不成功
	 *         [Success：false Status：60014] 影响航数为0 
	 *         [Success：false Status:10003]异常信息
	 *          [Success：false Status:60044]同仓库下不能弃审
	 *         [Success：true ] 操作成功
	 */
	ResponseMessage revokeStockTransferByTransferNo(String transferNo);
	
	/**
	 * 根据商品UUID、仓库查询库存储位和库区
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库代码
	 * @return 	responseMessage对象
	 *  			[Success：false	Status:10003] 异常信息
	 *  			[Success：true	Data:储位实体集合] 操作成功
	 */
	ResponseMessage getStockStorageAndLocationByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String location);
	
	/**
	 * 根据商品UUID、仓库、库区、储位查询库存中的主单位
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库
	 * @param stroage	库区
	 * @param location	储位
	 * @return 	responseMessage对象
	 * 				[Success：false Status：10012] 参数为null
	 *  			[Success：false	Status:10003] 异常信息
	 *  			[Success：true	Data:List<String>] 操作成功
	 */
	ResponseMessage getStockMuByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String storage,String location);
	
	/**
	 * 移库操作同时生成移库单表头及表体(同仓库移库不会生成出入库单)
	 * @param stockTransferLists	移库单表体实体集合
	 * @param createName	创建人
	 * @return	responseMessage对象
	 * 				<pre>
	 * 					[Success:false,status:10003,mag:异常信息]
	 * 					[Success:false,status:10004,msg:参数1[stockTransferLists]为null]
	 * 					[Success:false,status:10012,msg:参数2[createName]为null或空字符串]
	 * 					[Success:false,status:10014]影响行数为0
	 * 					[Success:false,status:10020,msg:出入库单号生成错误]
	 * 					[Success:false,status:60007,msg:库存不足！]
	 * 					[Success:false,status:60041,msg:移库数量异常！] 为null或者为0
	 * 					[Success:true,date:影响行数]
	 * 				</pre>
	 */
	ResponseMessage stockTransfer(List<StockTransferList> stockTransferLists, String createName);
	
	
}
