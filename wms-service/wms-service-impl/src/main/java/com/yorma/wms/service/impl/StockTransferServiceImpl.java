package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseData;
import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.api.StatusHistoryDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.api.StockTransferDao;
import com.yorma.wms.dao.api.StockTransferListDao;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.entity.StatusHistory;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.StockTransfer;
import com.yorma.wms.entity.StockTransferList;
import com.yorma.wms.service.api.StockTransferService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 移库单表头Service实现
 * 
 * @author Su 2017年12月13日
 */
public class StockTransferServiceImpl extends BaseServiceImpl<StockTransfer>implements StockTransferService {

	private static final Logger logger = LoggerFactory.getLogger(StockTransfer.class);

	private StockTransferDao stockTransferDao;
	private StockTransferListDao stockTransferListDao;

	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;

	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;

	private StockDao stockDao;
	
	private StatusHistoryDao statusHistoryDao;
	private SerialNumberService serialNumberService;

	/**
	 * 根据条件查询移库单表头
	 * 
	 * @param parames
	 *            参数集合
	 * @return Responsemessage对象 [Success：false Status:10003] 异常信息 [Success：true
	 *         Data：List<移库单表头实体>]
	 */
	@Override
	public ResponseMessage getStockTransfer(Map<String, Object> parames) {
		try {
			return new ResponseMessage(true, stockTransferDao.getStockTransfer(parames));
		} catch (Exception e) {
			logger.error("StockTransferService:方法[getStockTransfer]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

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
	@Override
	public ResponseMessage getStockTransfer(Map<String, Object> parames, Integer pageNumber, Integer pageSize) {
		try {
			if (pageNumber == null) {
				return new ResponseMessage(false, "10012", "参数[pageNumber]为空");
			}
			if (pageSize == null) {
				return new ResponseMessage(false, "10012", "参数[pageSize]为空");
			}
			List<StockTransfer> stockTransfers = new ArrayList<>();
			int counts = stockTransferDao.getcounts(parames);
			if (counts == 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, stockTransfers));
			}
			int page = getTotal(counts, pageSize);
			stockTransfers = stockTransferDao.getStockTransfer(parames, pageNumber, pageSize);
			
			return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, stockTransfers));
			
		} catch (Exception e) {
			logger.error("StockTransferService:方法[getStockTransfer](分页)出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
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
	 *         [Success:false Status:60002] 移库单号重复 
	 *         [Success:false Status:60035] 移库单字段NULL值，请核实信息
	 *         [Success：true, Data:移库单表头id]
	 *         操作成功
	 */
	@Override
	public ResponseMessage saveStockTransFerAndList(StockTransfer stockTransfer,
			List<StockTransferList> stockTransferLists) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("saveStockTransFerAndList  stockTransfer: {}", stockTransfer);
				logger.debug("saveStockTransFerAndList  stockTransferLists.size: {}", stockTransferLists.size());
			}
			if (stockTransfer == null || stockTransferLists.isEmpty()) {
				return new ResponseMessage(false, "10012");
			}
			// 判断是否已审核
			if (stockTransfer.getIsAudit()) {
				return new ResponseMessage(false, "60016");
			}
			if (isBlank(stockTransfer.getFromWarehouse()) || isBlank(stockTransfer.getOwnerCode())
					|| isBlank(stockTransfer.getToWarehouse())) {
				return new ResponseMessage(false, "60035", String.format("异常信息：%s", "请核实信息"));
			}
			if (stockTransfer.getId() == null) {
				stockTransfer.setCreateDate(new Date());
				// 判断移库单号是否重复
				if (stockTransferDao.getStockTransferByTransferNo(stockTransfer.getTransferNo()) != null) {
					return new ResponseMessage(false, "60002",String.format("异常信息：%s", "移库单号重复"));
				}
			}
			int rows = stockTransferDao.save(stockTransfer);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			// 删除移库单表体
			rows = stockTransferListDao.removeStockTransferListByTransferNo(stockTransfer.getTransferNo());

			for (StockTransferList stockTransferList : stockTransferLists) {
				if (isBlank(stockTransferList.getFromLocation()) || isBlank(stockTransferList.getFromStorage())
						|| isBlank(stockTransferList.getGoodsName()) || isBlank(stockTransferList.getMu()) || isBlank(stockTransferList.getPn())
						|| isBlank(stockTransferList.getToLocation()) || isBlank(stockTransferList.getToStorage()) || stockTransferList.getTransferQty()==0) {
					return new ResponseMessage(false, "60035", String.format("异常信息：%s", "请核实信息"));

				}
				
				stockTransferList.setId(null);
				stockTransferList.setUuid(UUID.randomUUID().toString());
				rows = stockTransferListDao.save(stockTransferList);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

			}

			// 得到Id属性
			Field field = stockTransfer.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(stockTransfer); // 得到此属性的值
			return new ResponseMessage(true, value);

		} catch (Exception e) {
			logger.error("StockTransferService:方法[saveStockTransFerAndList]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据移库单号删除移库单表头和表体
	 * 
	 * @param transferNo
	 *            移库单号
	 * @return responsemessage 对象 [Success：false Status：10012] 参数为null
	 *         [Success：false Status:10003] 异常信息 [Success：false Status:60016]
	 *         该移库记录已被审核，不能进行该操作 [Success：true Data:影响行数] 操作成功
	 */
	@Override
	public ResponseMessage removeStockTransferAndListByTransferNo(String transferNo) {
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			if (stockTransferDao.getStockTransferByTransferNo(transferNo).getIsAudit()) {
				return new ResponseMessage(false, "60016");
			}
			int rows = stockTransferDao.removeStockTransferByTransferNo(transferNo);
			rows = stockTransferListDao.removeStockTransferListByTransferNo(transferNo);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("StockTransferService:方法[removeStockTransferAndListByTransferNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据移库单号查询移库单表头
	 * 
	 * @param transferNo
	 *            移库单号
	 * @return responsemessage 对象 [Success：false Status：10012] 参数为null
	 *         [Success：false Status:10003] 异常信息 [Success：true Data:移库单表头实体]
	 *         操作成功
	 */
	@Override
	public ResponseMessage getStockTransferByTransferNo(String transferNo) {
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, stockTransferDao.getStockTransferByTransferNo(transferNo));
		} catch (Exception e) {
			logger.error("StockTransferService:方法[getStockTransferByTransferNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}
	}

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
	@Override
	public ResponseMessage revokeStockTransferByTransferNo(String transferNo) {
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			StockTransfer stockTransfer = stockTransferDao.getStockTransferByTransferNo(transferNo);
			if (!stockTransfer.getIsAudit()) {
				return new ResponseMessage(false, "60018");
			}
			
			//判断出入库单号是否为空，若为空则代表同仓库下移库，不能进行撤销操作
			if (isBlank(stockTransfer.getDeliveryNoteNo()) || isBlank(stockTransfer.getReceivingNoteNo())) {
				return new ResponseMessage(false, "60044", "同仓库下不能弃审");
			}
			for (ReceivingPackingList receivingPackingList : receivingPackingListDao
					.getReceivingPackingListByNoteNo(stockTransfer.getReceivingNoteNo(), null, null, null)) {

				Stock stock = stockDao.getStockByPLUUID(receivingPackingList.getUuid());

				// 判断库存是否被占用
				if (stock.getEntryQty() != stock.getInStock() || stock.getPreAllocationStock() != 0
						|| stock.getAllocatedStock() != 0) {
					return new ResponseMessage(false, "60017");
				}
				// 删除移库操作生成的库存信息
				int rows = stockDao.removeStockByUUID(stock.getUuid(), stock.getVersion());
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
			}

			// 回增库存数量
			for (DeliveryPackingList deliveryPackingList : deliveryPackingListDao
					.getDeliveryPackingListByNoteNo(stockTransfer.getDeliveryNoteNo())) {
				Stock stock = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());
				int rows = stockDao.updateInStock(stock.getAllocatedStock(),
						stock.getInStock() + deliveryPackingList.getDeliveryQty(), stock.getUuid(), stock.getVersion());
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
			}
			// 删除出库单相关信息
			int rows = deliveryPackingListDao.deleteDeliveryPackingList(stockTransfer.getDeliveryNoteNo());
			rows = deliveryNoteAsnDao.deleteDeliveryNoteAsn(stockTransfer.getDeliveryNoteNo());
			rows = deliveryNoteDao.deleteDeliveryNoteByNoteNo(stockTransfer.getDeliveryNoteNo());

			// 删除入库单相关信息
			rows = receivingPackingListDao.delete(stockTransfer.getReceivingNoteNo());
			rows = receivingNoteAsnDao.delete(stockTransfer.getReceivingNoteNo());
			rows = receivingNoteDao.delete(stockTransfer.getReceivingNoteNo());

			// 修改移库单表头相关数据
			stockTransfer.setIsAudit(false);
			stockTransfer.setDeliveryNoteNo(null);
			stockTransfer.setReceivingNoteNo(null);
			rows = stockTransferDao.save(stockTransfer);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("StockTransferService:方法[revokeStockTransferByTransferNo]出现异常，异常信息是 :{]", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据商品UUID、仓库查询库存储位和库区
	 * @param goodsUuid	商品UUID
	 * @param warehouse	仓库代码
	 * @return 	responseMessage对象
	 *  			[Success：false	Status:10003] 异常信息
	 *  			[Success：true	Data:储位实体集合] 操作成功
	 */
	@Override
	public ResponseMessage getStockStorageAndLocationByGoodsUUIDAndWarehouse(String goodsUuid,String warehouse,String location) {
		try {
			
			return new ResponseMessage(true, stockDao.getStockStorageAndLocatiomByGoodsUUIDAndWarehouse(goodsUuid, warehouse,location));
		} catch (Exception e) {
			logger.error("StockTransferService:方法[getStockStorageAndLocationByGoodsUUIDAndWarehouse]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage getStockMuByGoodsUUIDAndWarehouse(String goodsUuid, String warehouse, String storage,
			String location) {
		try {
			if (isBlank(goodsUuid) || isBlank(warehouse) || isBlank(location) || isBlank(storage)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, stockDao.getStockMuByGoodsUUIDAndWarehouse(goodsUuid, warehouse, storage, location));
		} catch (Exception e) {
			logger.error("StockTransferService:方法[getStockMuByGoodsUUIDAndWarehouse]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage stockTransfer(List<StockTransferList> stockTransferLists, String createName) {
		try {
			if (stockTransferLists == null || stockTransferLists.isEmpty()) {
				return new ResponseMessage(false, "10004","参数1[stockTransferLists]");
			}
			if (isBlank(createName)) {
				return new ResponseMessage(false, "10012", "参数2[createName]");
			}
			String receivingNoteNo=null;
			String deliveryNoteNo=null;
			String receivingMergeLotNo =null;
			String deliveryMergeLotNo =null;
			String ownerCode=null;
			String fromWarehouse =stockTransferLists.get(0).getFromWarehouse();
			String toWarehouse =stockTransferLists.get(0).getToWarehouse();
			
			// 生成入库单号
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("RK",
					new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String receivingNoteNoNew = (String) responseMessage.getData();
			// 生成出库单号
			responseMessage = serialNumberService.getSerialNumber("CK",
					new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String deliveryNoteNoNew = (String) responseMessage.getData();
			
			for (StockTransferList stockTransferList : stockTransferLists) {
				
				Stock stock = stockDao.getStockByUUID(stockTransferList.getStockUuid());
				//可用数量
				int qty = stock.getInStock()-stock.getAllocatedStock()-stock.getPreAllocationStock();
				//占用数量
				int occupyQty = stock.getInStock()-qty;
				if (stockTransferLists.size()>1) {
					stockTransferList.setTransferQty(qty);
				}
				if (stockTransferList.getTransferQty() == null || stockTransferList.getTransferQty() ==0) {
					return new ResponseMessage(false, "60041", "移库数量异常！");
				}
				if (qty<stockTransferList.getTransferQty()) {
					return new ResponseMessage(false, "60007", "库存不足！");
				}
				
				ownerCode=stock.getOwnerCode();
				// 出库明细预告UUID
				String deliveryAsnUUID = UUID.randomUUID().toString();
				// 入库明细预告UUID
				String receivingAsnUUID = UUID.randomUUID().toString();
				// 入库货物信息UUID
				String receivingListUUID = UUID.randomUUID().toString();
				/*
				 * 如果是不同仓库之间移库则生成出入库单明细及出入库货物信息
				 */
				if (!stockTransferList.getFromWarehouse().equals(stockTransferList.getToWarehouse())) {
					receivingNoteNo = receivingNoteNoNew;
					deliveryNoteNo = deliveryNoteNoNew;
					
					// 生成入库合并批次号
					responseMessage = serialNumberService.getSerialNumber("ME",
							new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					receivingMergeLotNo = (String) responseMessage.getData();
					// 生成出库合并批次号
					responseMessage = serialNumberService.getSerialNumber("ME",
							new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					deliveryMergeLotNo = (String) responseMessage.getData();
					
					// 添加出库明细预告
					 int rows = deliveryNoteAsnDao.save(new DeliveryNoteAsn(null, deliveryAsnUUID, deliveryNoteNo,
							 stock.getGoodsUuid(), stock.getItem(), stock.getEmsNo(), stock.getSku(), stock.getMu(), stock.getPn(),
							stockTransferList.getTransferQty(), null, stock.getGoodsName(), stock.getGoodsModel(), null, null,
							null, null, stock.getCurrency(), stock.getPackagingKey(), createName, new Date(), null, null, null, null,
							null, null, null, stock.getPackagingNo(), false, stock.getAmounts(), deliveryMergeLotNo, 10));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}

					// 添加出库货物信息
					rows = deliveryPackingListDao.save(new DeliveryPackingList(null, UUID.randomUUID().toString(),
							deliveryAsnUUID, deliveryNoteNo, stock.getGoodsUuid(), stock.getPn(),
							stockTransferList.getGoodsName(), stock.getGoodsModel(), stock.getItem(), stock.getEmsNo(), stock.getSku(), stock.getMu(),
							stockTransferList.getFromWarehouse(), stockTransferList.getFromStorage(),
							stockTransferList.getFromLocation(), stockTransferList.getStockUuid(), stockTransferList.getTransferQty(),stock.getPackagingNo(),
							stock.getPackagingKey(), null, null, null,  stock.getPalletNo(), null, null, null, stock.getIsQualifed(),
							createName, new Date(), 20, null, null, deliveryMergeLotNo));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					
					// 添加入库明细预告
					rows = receivingNoteAsnDao.save(new ReceivingNoteAsn(null, receivingAsnUUID, receivingNoteNo,
							stock.getGoodsUuid(), stock.getItem(), stock.getEmsNo(), stock.getSku(), stock.getMu(), stock.getPn(),
							stock.getGoodsName(), stock.getGoodsModel(), null, null, null, null, stock.getCurrency(), stock.getPackagingKey(),
							stockTransferList.getTransferQty(), 1, stockTransferList.getTransferQty(),
							createName, new Date(), stock.getAmounts(), null, stock.getManufactureLotNo(), null, null, null, null,
							stock.getPackagingNo(), stock.getIsQualifed(), 20, stock.getAmounts(), receivingMergeLotNo, false));

					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}

					// 添加入库货物信息
					rows = receivingPackingListDao.save(new ReceivingPackingList(null, receivingListUUID, receivingAsnUUID,
							receivingNoteNo, stock.getGoodsUuid(), stock.getPn(), stock.getGoodsName(), stock.getGoodsModel(),
							stock.getItem(), stock.getEmsNo(), stock.getSku(), stock.getMu(), stockTransferList.getToWarehouse(),
							stockTransferList.getToStorage(), stockTransferList.getToLocation(),
							stockTransferList.getTransferQty(), stock.getPackagingNo(), stock.getPackagingKey(), null, null, null,
							stock.getPalletNo(), null, null, null, null, null, stock.getIsQualifed(), createName, new Date(),
							stock.getPackagingQty(), stock.getSecondaryPackagingQty(), 20, receivingMergeLotNo));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					
				}
				//修改库存信息
				stock.setInStock(occupyQty+qty-stockTransferList.getTransferQty());
				int rows = stockDao.save(stock);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				
				// 添加库存信息
				rows = stockDao.save(new Stock(null, UUID.randomUUID().toString(), stock.getEntryDate(), stock.getEntryType(),
						ownerCode, stock.getPn(), stock.getSku(), stock.getBarcode(), stock.getGoodsUuid(), stock.getItem(), stock.getEmsNo(),
						stockTransferList.getTransferQty(), stockTransferList.getTransferQty(), 0, 0,
						stock.getMu(), stockTransferList.getToWarehouse(), stockTransferList.getToStorage(),
						stockTransferList.getToLocation(), stock.getPackagingNo(), stock.getPalletNo(), stock.getIsQualifed(), stock.getAmounts(), stock.getCurrency(),
						receivingListUUID, receivingNoteNo, stock.getPackagingQty(), null, 0, stock.getExpirationDate(), stock.getManufactureLotNo(), stock.getReceivingLotNo(),
						stock.getGoodsName(), stock.getGoodsModel(), stock.getPackagingKey()));

				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				
				/*
				 * 保存移库单表体
				 */
				stockTransferList.setUuid(UUID.randomUUID().toString());
				stockTransferList.setPn(stock.getPn());
				stockTransferList.setMu(stock.getMu());
				stockTransferList.setGoodsUuid(stock.getGoodsUuid());
				stockTransferList.setGoodsName(stock.getGoodsName());
				rows = stockTransferListDao.save(stockTransferList);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				
			}
			/*
			 * 如果是不同仓库之间移库则生成出入库单
			 */
			if (!fromWarehouse.equals(toWarehouse)) { 
				
				// 添加出库单表头
				int rows = deliveryNoteDao.save(new DeliveryNote(null, deliveryNoteNo, "401", ownerCode,
						null, null, fromWarehouse, null, null, null, null, null, null, null, null,
						null, createName, new Date(), null, null, 60, null, null));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				// 保存出库单状态历史
				rows = statusHistoryDao.save(new StatusHistory(null, deliveryNoteNo, null, new Date(),
						createName, "结束出货单", null, null, null, "1"));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				// 添加入库单表头
				rows = receivingNoteDao.save(new ReceivingNote(null, receivingNoteNo, "401", ownerCode,
						null, null, toWarehouse, null, null, null, null, null, null, null, null,
						null, createName, new Date(), null, null, 60));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				// 保存入库单状态历史
				rows = statusHistoryDao.save(new StatusHistory(null, receivingNoteNo, null, new Date(),
						createName, "结束入库单", null, null, null, "0"));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				  
			}	
			
			StockTransfer stockTransfer = new StockTransfer(null, stockTransferLists.get(0).getTransferNo(), null, new Date(), deliveryNoteNo, receivingNoteNo, ownerCode, createName, new Date(), null, stockTransferLists.get(0).getFromWarehouse(), stockTransferLists.get(0).getToWarehouse(), true); 
			int rows = stockTransferDao.save(stockTransfer);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("StockTransferService:方法[stockTransfer]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setStockTransferDao(StockTransferDao stockTransferDao) {
		super.setBaseDao(stockTransferDao);
		this.stockTransferDao = stockTransferDao;
	}

	public void setStockTransferListDao(StockTransferListDao stockTransferListDao) {
		this.stockTransferListDao = stockTransferListDao;
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		this.deliveryNoteDao = deliveryNoteDao;
	}

	public void setDeliveryNoteAsnDao(DeliveryNoteAsnDao deliveryNoteAsnDao) {
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
	}

	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	public void setReceivingNoteDao(ReceivingNoteDao receivingNoteDao) {
		this.receivingNoteDao = receivingNoteDao;
	}

	public void setReceivingNoteAsnDao(ReceivingNoteAsnDao receivingNoteAsnDao) {
		this.receivingNoteAsnDao = receivingNoteAsnDao;
	}

	public void setReceivingPackingListDao(ReceivingPackingListDao receivingPackingListDao) {
		this.receivingPackingListDao = receivingPackingListDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public void setSerialNumberService(SerialNumberService serialNumberService) {
		this.serialNumberService = serialNumberService;
	}
	
	public void setStatusHistoryDao(StatusHistoryDao statusHistoryDao) {
		this.statusHistoryDao = statusHistoryDao;
	}
	
}
