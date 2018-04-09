package com.yorma.wms.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;
import com.yorma.wms.entity.dto.StockDto;
import com.yorma.wms.entity.vo.StockVO;
import com.yorma.wms.service.api.StockService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 库存service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月16日
 * @version V1.0
 */
public class StockServiceImpl extends BaseServiceImpl<Stock>implements StockService {

	private static final Logger logger = LoggerFactory.getLogger(Stock.class);

	private StockDao stockDao;

	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;

	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;
	private DeliveryNoteDao deliveryNoteDao;

	private StockTransferDao stockTransferDao;
	private StockTransferListDao stockTransferListDao;

	private StatusHistoryDao statusHistoryDao;
	private static SerialNumberService serialNumberService;

	/**
	 * 判断id是否为空，如果为空生成uuid放入Stock对象中
	 */
	@Override
	public ResponseMessage save(Stock st) {
		if (st == null) {
			return new ResponseMessage(false, "10004");
		}
		if (st.getId() == null) {
			UUID uuid = UUID.randomUUID();
			st.setUuid(uuid.toString());
		}
		return super.save(st);
	}

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
	@Override
	public ResponseMessage getStock(Map<String, Object> params) {
		try {
			return new ResponseMessage(true, stockDao.getStock(params));
		} catch (Exception e) {
			logger.error("StockService:方法[getStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 查询库存（累计相同料号。品名、包装键、入库数并且未分配的库存量）
	 * @param params
	 * @return 返回ResponseMessage对象
	 * 			<pre>
	 * 				[Success:false, Status:10003,Msg:异常信息]
	 * 				[Success:true, Data:List<库存VO实体>]
	 * 			</pre>
	 */
	@Override
	public ResponseMessage listStockVO(Map<String, Object> params) {
		try {
			/*
			 * 根据条件获取库存信息，如果为null则直接返回
			 */
			List<StockVO> stockVOs = stockDao.listStockVO(params);
			if (stockVOs == null || stockVOs.isEmpty()) {
				return new ResponseMessage(true, stockVOs);
			}
			/*
			 * 循环处理库存信息（如果是相同料号。品名、包装键、入库数并且未分配的库存信息，则累计条数，）
			 */
			//保存库存信息mao集合
			Map<String, StockVO> stockVOMap = new HashMap<>();
			//保存库存信息条数map集合
			Map<String,Integer> stockCount = new HashMap<>();
			List<StockVO> stockVOList = new ArrayList<>();
			for (StockVO stockVO : stockVOs) {
				//设置标识（料号+品名+包装键+在库量）
				String identifying = stockVO.getPn()+stockVO.getGoodsName()+stockVO.getPackagingKey()+stockVO.getInStock();
				/*
				 * 如果是未分配过得
				 */
				if (stockVO.getInStock() == stockVO.getEntryQty()) {
					if (stockVOMap.containsKey(identifying)) {
						int count = stockCount.get(identifying);
						StockVO stockmap = stockVOMap.get(identifying);
						stockmap.setPackQtyCount("1*"+count);
						stockmap.setInStockCount(stockVO.getInStock()+"*"+count);
						stockVOMap.put(identifying, stockmap);
						stockCount.put(identifying, count+1);
					}
					stockVO.setPackQtyCount("1*1");
					stockVO.setInStockCount(stockVO.getInStock()+"*1");
					stockVOMap.put(identifying, stockVO);
					stockCount.put(identifying, 1);
					continue;
				}
				stockVO.setPackQtyCount("1*1");
				stockVO.setInStockCount(stockVO.getInStock()+"*1");
				stockVOMap.put(identifying, stockVO);
				stockCount.put(identifying, 1);
			}
			stockVOList.addAll(stockVOMap.values());
			return new ResponseMessage(true, stockVOList);
		} catch (Exception e) {
			logger.error("StockService:方法[listStockVO]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
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
	@Override
	public ResponseMessage getStock(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		try {
			if (pageNumber == null || pageSize == null) {
				return new ResponseMessage(false, "10012", "参数[pageNumber]或[pageSize] 为 null");
			}
			List<Stock> stocks = new ArrayList<>();
			//获取总条数
			int counts=stockDao.getCounts(params);
			if (counts == 0) {
				return new ResponseMessage(true, new ResponseData<>(pageNumber, 1, counts, stocks));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("StockService:方法[getStock] :总条数[counts]:{}", counts);
			}
			//计算总页数
			int page = counts%pageSize == 0 ? counts/pageSize : counts/pageSize+1;
			
			stocks = stockDao.getStock(params, pageNumber, pageSize);
			
			return new ResponseMessage(true, new ResponseData<>(pageNumber, page, counts, stocks));
		} catch (Exception e) {
			logger.error("StockService:方法[getStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据条件查询库存总在库量，总预分配量，总已分配量，总包装数，可用库存量
	 * @param params	参数集合
	 * @return	返回ResponseMessage对象
	 * 				<pre>
	 * 					[Success:false ,Status:10003,Msg:异常信息]
	 * 					[Success:true , Data:库存VO实体]
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getStockQty(Map<String, Object> params) {
		try {
			List<Stock> stocks = stockDao.getStock(params);
			int sumInStock=0;
			int sumPreAllocationStock=0;
			int sumAllocationStock=0;
			BigDecimal sumPackagingQty=new BigDecimal(stocks.size());
			for (Stock stock : stocks) {
				sumInStock+=stock.getInStock();
				sumPreAllocationStock+=stock.getPreAllocationStock();
				sumAllocationStock+=stock.getAllocatedStock();
			}
			int sumQty = sumInStock-sumPreAllocationStock-sumAllocationStock;
			StockVO stockVO = new StockVO(sumInStock, sumPreAllocationStock, sumAllocationStock, sumPackagingQty, sumQty);
			
			return new ResponseMessage(true, stockVO);
		} catch (Exception e) {
			logger.error("StockService:方法[getStockQTY]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 自动计算分配(单/合)
	 * 
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
	@Override
	public ResponseMessage getSelfMotionStock(String deliveryStrategy, Map<String, String> paramtes,
			List<String> asnUuids) {
		try {
			int expectQty = 0;
			String manufactureLotNo = null;
			for (String asnUuid : asnUuids) {
				DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(asnUuid);
				expectQty += deliveryNoteAsn.getExpectQty();
				manufactureLotNo = deliveryNoteAsn.getLotNo();
			}
			paramtes.put("manufacture_Lot_No", manufactureLotNo);
			// 分配
			// 根据条件和出库策略查询库存信息
			List<StockDto> lis = stockDao.getStockByGoodsUUID(deliveryStrategy, false, paramtes);
			if (lis == null || lis.isEmpty()) {
				return new ResponseMessage(false, "60008");
			}
			// 自动计算分配库存
			List<StockDto> sdtoList = new ArrayList<>();
			// 用于累计库存总量
			int qty = 0;
			for (int i = 0; i < lis.size(); i++) {
				StockDto sdo = lis.get(i);
				qty += sdo.deliveryQty();
				if (qty < expectQty) {
					sdo.setDeliveryQty(sdo.deliveryQty());
					sdtoList.add(sdo);
				} else {
					int j = qty - expectQty;
					sdo.setDeliveryQty(sdo.deliveryQty() - j);
					sdtoList.add(sdo);
					break;
				}
			}
			// 判断库存是否足够
			if (qty < expectQty) {
				return new ResponseMessage(true, "60007", "库存不足", sdtoList);
			} else {
				return new ResponseMessage(true, sdtoList);
			}

		} catch (Exception e) {
			logger.error("StockService:方法[getSelfMotionStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据商品UUID查询库存
	 */
	@Override
	public ResponseMessage getStockByGoodsUUID(String goodsUuid, String asnUuid, String warehouseCode) {
		try {
			// 加入主单位条件
			if (isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}

			List<StockDto> stockDtos = new ArrayList<>();
			// 获取拣货的库存UUID和分配量（Map<库存UUID,分配量>）
			Map<String, Integer> map = stockDao.getStockUUIDAndDeliveryPackingListByAsnUUID(asnUuid);
			DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(asnUuid);
			if (map.size() != 0) {
				// 商品UUID，出库策略，(true为查询全部库存，false为查询不为零的库存)
				List<StockDto> sdtolist = stockDao.getStockByGoodsUUID(goodsUuid, deliveryNoteAsn.getMu(), "先进先出", true,
						warehouseCode);
				// 将分配过得库存信息归还（假数据）
				for (StockDto stockDto : sdtolist) {
					if (map.get(stockDto.getUuid()) != null) {
						if (stockDto.getInStock() - stockDto.getAllocatedStock() - stockDto.getPreAllocationStock()
								+ map.get(stockDto.getUuid()) > 0) {
							stockDto.setPreAllocationStock(
									stockDto.getPreAllocationStock() - map.get(stockDto.getUuid()));
							stockDtos.add(stockDto);
						}
					} else if (stockDto.getInStock() - stockDto.getAllocatedStock()
							- stockDto.getPreAllocationStock() > 0) {
						stockDtos.add(stockDto);
					}
				}
				return new ResponseMessage(true, stockDtos);
			} else {
				List<StockDto> sdtolist = stockDao.getStockByGoodsUUID(goodsUuid, deliveryNoteAsn.getMu(), "先进先出",
						false, warehouseCode);
				return new ResponseMessage(true, sdtolist);
			}
		} catch (Exception e) {
			logger.error("StockService:方法[getStockByGoodsUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出库策略，生产批次号，商品UUID，主单位，仓库代码查询可分配库存量
	 * 
	 * @param deliveryStrategy
	 *            出库策略
	 * @param goodsUuid
	 *            商品UUID
	 * @param mu
	 *            主单位
	 * @param warehouseCode
	 *            仓库代码
	 * @param manufactureLotNo
	 *            生产批次号
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若在根据商品UUID、明细UUID、仓库代码查询库存的过程中出现了异常信息，
	 *         ResponseMessage中的success为false、 status: 10003、msg：异常的说明信息</li>
	 *         <li>根据商品UUID、明细UUID、仓库代码查询库存成功,ResponseMessage中的success:true,
	 *         data: {@code List<StockDto>}</li>
	 *         </ul>
	 */
	@Override
	public ResponseMessage getStockByGoodsUUID(String deliveryStrategy, String goodsUuid, String mu,
			String warehouseCode, String manufactureLotNo) {
		try {
			// 加入主单位条件
			if (isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}
			Map<String, String> paramtes = new HashMap<>();
			paramtes.put("mu", mu);
			paramtes.put("goods_uuid", goodsUuid);
			paramtes.put("warehouse", warehouseCode);
			paramtes.put("manufacture_Lot_No", manufactureLotNo);

			List<StockDto> sdtolist = stockDao.getStockByGoodsUUID(deliveryStrategy, false, paramtes);
			return new ResponseMessage(true, sdtolist);

		} catch (Exception e) {
			logger.error("StockService:方法[getStockByGoodsUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据商品UUID、出货单号查询库存
	 * 
	 * @param deliveryNoteNo
	 * @param goodsUuid
	 * @return 返回ResponseMessage对象
	 *         <ul>
	 *         <li>若参数为null或者空字符串，ResponseMessage中的success为false、 status: 10012
	 *         </li>
	 *         <li>若已无库存，ResponseMessage中的Success为false、status：60008</li>
	 *         <li>若在自动计算可分配量的过程中出现了异常信息，ResponseMessage中的success为false、 status:
	 *         10003、msg：异常的说明信息</li>
	 *         <li>自动计算可分配量成功,ResponseMessage中的success:true,data:
	 *         {@code List<StockDto>}</li>
	 *         </ul>
	 */
	@Override
	public ResponseMessage getStockByNoteNoAndGoodsUUID(String deliveryNoteNo, String goodsUuid) {
		try {
			if (isBlank(deliveryNoteNo) || isBlank(goodsUuid)) {
				return new ResponseMessage(false, "10012");
			}
			// 根据出库单号获取出库单信息
			DeliveryNote deliveryNote = deliveryNoteDao.getDeliveryNoteByDeliveryNoteNo(deliveryNoteNo);
			List<DeliveryNoteAsnDto> deliveryNoteAsnDtos = deliveryNoteAsnDao
					.getDeliveryNoteAsnDtoAndExpectQtySumByNoteNo(deliveryNoteNo);
			String mu = "";
			for (DeliveryNoteAsnDto deliveryNoteAsnDto : deliveryNoteAsnDtos) {
				if (goodsUuid.equals(deliveryNoteAsnDto.getGoodsUuid())) {
					mu = deliveryNoteAsnDto.getMu();
				}
			}
			List<StockDto> stockDtos = new ArrayList<>();
			// 获取拣货的库存UUID和分配量（Map<库存UUID,分配量>）
			Map<String, Integer> stockUUIDAndQty = stockDao
					.getStockUUIDAndDeliveryPackingListByNoteNoAndGoodsUUID(deliveryNoteNo, goodsUuid);
			if (stockUUIDAndQty.size() != 0) {
				// 商品UUID，出库策略，(true为查询全部库存，false为查询不为零的库存)
				List<StockDto> sdtolist = stockDao.getStockByGoodsUUID(goodsUuid, mu, "先进先出", true,
						deliveryNote.getWarehouse());
				for (StockDto stockDto : sdtolist) {
					if (stockUUIDAndQty.containsKey(stockDto.getUuid())) {
						if (stockDto.getInStock() - stockDto.getAllocatedStock() - stockDto.getPreAllocationStock()
								+ stockUUIDAndQty.get(stockDto.getUuid()) > 0) {
							stockDto.setPreAllocationStock(
									stockDto.getPreAllocationStock() - stockUUIDAndQty.get(stockDto.getUuid()));
							stockDtos.add(stockDto);
						}
					} else if (stockDto.getInStock() - stockDto.getAllocatedStock()
							- stockDto.getPreAllocationStock() > 0) {
						stockDtos.add(stockDto);
					}
				}
				return new ResponseMessage(true, stockDtos);
			} else {
				List<StockDto> sdtolist = stockDao.getStockByGoodsUUID(goodsUuid, mu, "先进先出", false,
						deliveryNote.getWarehouse());
				return new ResponseMessage(true, sdtolist);
			}
		} catch (Exception e) {
			logger.error("StockService:方法[getStockByNoteNoAndGoodsUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 修改预分配量（拣货分配）
	 * 
	 * @param stockdtoList
	 * @param asnUuids
	 * @param isMerged
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10012]参数为null
	 * 					[Success:false,Status:10014]影响行数为0
	 * 					[Success:false,Status:60010]并发问题修改预分配量失败
	 * 					[Success:false,Status:60007]库存不足
	 * 					[Success:false,Status:10003]异常信息
	 *					 [Success:false,Status:60032]请填写分配量
	 * 					[Success:true]操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage updatePreAllocationStock(List<StockDto> stockdtoList, List<String> asnUuids,
			boolean isMerged) {
		try {
			if (stockdtoList.isEmpty() || asnUuids.isEmpty()) {
				return new ResponseMessage(false, "10012", String.format("异常信息：%s", "参数为null"));
			}
			ResponseMessage responseMessage = serialNumberService.getSerialNumber("ME",
					new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
			if (!responseMessage.getSuccess()) {
				return responseMessage;
			}
			String mergeLotNo = (String) responseMessage.getData();
			String asnUuid = null;
			String pn = null;
			String goodsUuid = null;
			String emsNo = null;
			String goodsModel = null;
			String goodsName = null;
			String item = null;
			String mu = null;
			String packagingKey = null;
			String sku = null;
			String deliveryNoteNo = null;
			for (String uuid : asnUuids) {
				DeliveryNoteAsn deliveryNoteAsn = deliveryNoteAsnDao.getDeliveryNoteAsnByUUID(uuid);
				asnUuid = deliveryNoteAsn.getUuid();
				pn = deliveryNoteAsn.getPn();
				goodsUuid = deliveryNoteAsn.getGoodsUuid();
				emsNo = deliveryNoteAsn.getEmsNo();
				goodsModel = deliveryNoteAsn.getGoodsModel();
				goodsName = deliveryNoteAsn.getGoodsName();
				item = deliveryNoteAsn.getItem();
				mu = deliveryNoteAsn.getMu();
				packagingKey = deliveryNoteAsn.getPackagingKey();
				sku = deliveryNoteAsn.getSku();
				deliveryNoteNo = deliveryNoteAsn.getDeliveryNoteNo();

				deliveryNoteAsn.setIsMerged(isMerged);
				deliveryNoteAsn.setMergeLotNo(mergeLotNo);
				deliveryNoteAsn.setStatus(10);
				int rows = deliveryNoteAsnDao.save(deliveryNoteAsn);
				if (rows == 0) {
					return new ResponseMessage(false, "10014", String.format("异常信息：%s", "修改明细信息时报"));
				}

			}
			boolean falg = false;
			for (StockDto stockDto : stockdtoList) {
				Stock stock = stockDao.getStockByUUID(stockDto.getUuid());
				if (stockDto.getDeliveryQty() != 0) {
					if (stock.getInStock() - stock.getPreAllocationStock() - stock.getAllocatedStock() >= stockDto
							.getDeliveryQty()) {
						DeliveryPackingList pl = new DeliveryPackingList();
						UUID uuid = UUID.randomUUID();
						pl.setUuid(uuid.toString());
						pl.setAnsUuid(asnUuid);
						pl.setPn(pn);
						pl.setGoodsUuid(goodsUuid);
						pl.setDeliveryNoteNo(deliveryNoteNo);
						pl.setEmsNo(emsNo);
						pl.setGoodsModel(goodsModel);
						pl.setGoodsName(goodsName);
						pl.setItem(item);
						pl.setMu(mu);
						pl.setPackagingKey(packagingKey);
						pl.setSku(sku);
						pl.setStockUuid(stockDto.getUuid());
						pl.setDeliveryQty(stockDto.getDeliveryQty());
						pl.setIsQualifd(stock.getIsQualifed());
						pl.setPackagingNo(stock.getPackagingNo());
						pl.setPalletNo(stock.getPalletNo());
						pl.setWarehouse(stock.getWarehouse());
						pl.setStorage(stock.getStorage());
						pl.setLocation(stock.getLocation());
						pl.setCreateBy(stockDto.getPlCreateBy());
						pl.setCreateDate(new Date());
						pl.setStatus(0);

						pl.setMergeLotNo(mergeLotNo);

						int rows = stockDao.updatePreAllocationStock(
								stock.getPreAllocationStock() + stockDto.getDeliveryQty(), stockDto.getUuid(),
								stock.getVersion());

						if (rows == 0) {
							return new ResponseMessage(false, "60010");
						}
						rows = deliveryPackingListDao.save(pl);
						if (rows == 0) {
							return new ResponseMessage(false, "0014");
						}
					} else {
						return new ResponseMessage(false, "60007", "库存不足");
					}
					falg = true;
				}
			}
			int rows = deliveryNoteDao.updateStatus(deliveryNoteNo, 30);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			if (falg) {
				return new ResponseMessage(true);
			}
			return new ResponseMessage(false, "60032", String.format("异常信息: %s", "请填写分配数量"));
		} catch (Exception e) {
			logger.error("StockService:方法[updatePreAllocationStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据合并明细撤销拣货分配
	 * 
	 * @param mergeLotNos
	 *            合并明细集合
	 * @return ResponseMessage对象
	 * 
	 *         <pre>
	 * 					[Success:false,Status:10012]参数为null
	 * 					[Success:false,Status:10014]影响行数为0
	 * 					[Success:false,Status:60010]并发问题修改预分配量失败
	 * 					[Success:false,Status:60031]有记录已拣货确认
	 * 					[Success:false,Status:10003]异常信息
	 * 					[Success:true]操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage revokePreAllocationStock(List<String> mergeLotNos) {
		try {
			if (mergeLotNos.isEmpty()) {
				return new ResponseMessage(false, "10012");
			}
			String deliveryNoteNo = null;
			for (String mergeLotNo : mergeLotNos) {

				List<DeliveryPackingList> deliveryPackingLists = deliveryPackingListDao
						.getDeliveryPackingListByMergeLotNo(mergeLotNo, null);

				for (DeliveryPackingList deliveryPackingList : deliveryPackingLists) {
					// 判断是否已拣货确认
					if (deliveryPackingList.getStatus() == 10) {
						return new ResponseMessage(false, "60031", String.format("异常信息：%s", "有记录已拣货确认"));
					}
					Stock stock = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());
					int rows = stockDao.updatePreAllocationStock(
							stock.getPreAllocationStock() - deliveryPackingList.getDeliveryQty(), stock.getUuid(),
							stock.getVersion());
					if (rows == 0) {
						return new ResponseMessage(false, "60010");
					}
					rows = deliveryPackingListDao.removeById(deliveryPackingList.getId());
					deliveryNoteNo = deliveryPackingList.getDeliveryNoteNo();
				}
				int rows = deliveryNoteAsnDao.updateDeliveryNoteAsnByMergeLotNo(mergeLotNo);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

			}
			/*
			 * 获取拣货信息行数，判断是否还有拣货信息，如没有回退出货单状态
			 */
			int counts = deliveryPackingListDao.getDeliveryPackingListCountsByNoteNo(deliveryNoteNo);
			if (counts == 0) {
				int rows = deliveryNoteDao.updateStatus(deliveryNoteNo, 20);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("StockService:方法[revokePreAllocationStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 已分配核增同时预分配量核减、修改是否拣货
	 */
	@Override
	public ResponseMessage updateAllocatedStock(List<String> deliveryPackingListUuids) {
		try {
			if (deliveryPackingListUuids.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}

			for (String uuid : deliveryPackingListUuids) {
				DeliveryPackingList deliveryPackingList = deliveryPackingListDao.getdiveryPackingListByUUID(uuid);
				if (deliveryPackingList.getStatus() == 10) {
					return new ResponseMessage(false, "60031", String.format("异常信息：%s", "该信息已拣货确认，请勿重复操作"));
				}
				Stock stock = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());

				int rows = stockDao.updateAllocatedStock(
						stock.getAllocatedStock() + deliveryPackingList.getDeliveryQty(),
						stock.getPreAllocationStock() - deliveryPackingList.getDeliveryQty(), stock.getUuid(),
						stock.getVersion());
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
				rows = deliveryPackingListDao.updateIsOrderPickingByUUID(10, uuid);
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
			}
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			logger.error("StockService:方法[updateAllocatedStock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 在库量核减同时已分配量核减并修改拣货信息状态位
	 */
	@Override
	public ResponseMessage updateInstock(List<String> deliveryPackingListUuids, String deliveryLotNo) {
		try {
			if (deliveryPackingListUuids.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			for (String uuid : deliveryPackingListUuids) {
				DeliveryPackingList deliveryPackingList = deliveryPackingListDao.getdiveryPackingListByUUID(uuid);
				if (deliveryPackingList.getStatus() == 0) {
					return new ResponseMessage(false, "60034", String.format("异常信息：%s", "有记录未拣货确认"));
				}
				if (deliveryPackingList.getStatus() == 20) {
					return new ResponseMessage(false, "60033", String.format("异常信息：%s", "有记录已出库"));
				}
				Stock stock = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());
				int rows = stockDao.updateInStock(stock.getAllocatedStock() - deliveryPackingList.getDeliveryQty(),
						stock.getInStock() - deliveryPackingList.getDeliveryQty(), stock.getUuid(), stock.getVersion());
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
				rows = deliveryPackingListDao.updateStatusByUUID(20, deliveryLotNo, uuid);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			logger.error("StockService:方法[updateInstock]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 批量添加库存并同时修改收货单状态
	 */
	@Override
	public ResponseMessage addBatchByStockAndUpdateStatus(List<Stock> stockList, String receivingNoteNo) {
		try {
			if (null == stockList) {
				return new ResponseMessage(false, "10004");
			}
			if (isBlank(receivingNoteNo)) {
				return new ResponseMessage(false, "10012");
			}

			for (Stock stock : stockList) {
				if (receivingPackingListDao.getReceivingPackingListByStatusAndUUID(10, stock.getPlUuid()) == 0) {
					return new ResponseMessage(false, "60036", String.format("异常信息：%s", "仅在上架状态下才可以入库"));
				}

				UUID uuid = UUID.randomUUID();
				stock.setUuid(uuid.toString());
				stock.setVersion(Integer.valueOf(1));
				int rows = stockDao.save(stock);
				if (rows == 0) {
					return new ResponseMessage(false, "60010");
				}
				rows = receivingPackingListDao.updateStatusByUUID(20, stock.getPlUuid());
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			int rows = receivingNoteDao.updateStatus(receivingNoteNo, 50);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true, true);
		} catch (Exception e) {
			logger.error("StockService:方法[addBatchByStockAndUpdateStatus]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 批量添加库存
	 */
	@Override
	public ResponseMessage createBatchByStockAndReceiving(List<Stock> params) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("StockService:j进入[createBatchByStockAndReceiving]:{}" + new Date());
			}
			if (params.isEmpty()) {
				return new ResponseMessage(false, "10004");
			}
			List<Stock> stocks = new ArrayList<>();
			for (Stock stock : params) {
				stock.setUuid(UUID.randomUUID().toString());
				stock.setEntryDate(new Date());
				stock.setEntryType("201"); // 暂定
				stock.setAllocatedStock(0);
				stock.setPreAllocationStock(0);
				stock.setPackagingQty(new BigDecimal(1));
				stock.setVersion(0);
				stocks.add(stock);
			}

			int[] rows = stockDao.createBatch(stocks);
			if (logger.isDebugEnabled()) {
				logger.debug("结束：影响行数" + rows.length + "时间：" + new Date());
			}
			return new ResponseMessage(true);

		} catch (Exception e) {
			logger.error("StockService:方法[createBatchByStockAndReceiving]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s,", e));
		}
	}

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
	@Override
	public ResponseMessage getStockStatistics(Map<String, Object> params) {
		try {
			List<Stock> stocks = stockDao.getStock(params);
			// 返回的集合
			List<Stock> returnStock = new ArrayList<>();
			// 暂时存储汇总的库存
			Map<String, Stock> sumStock = new LinkedHashMap<String, Stock>();
			for (Stock stock : stocks) {
				// 设置判断条件
				String judge = stock.getWarehouse() + stock.getOwnerCode() + stock.getGoodsUuid() + stock.getMu();
				// 判断是否有条件相同的库存记录，如果有汇总库存
				if (sumStock.containsKey(judge)) {
					Stock oldStock = sumStock.get(judge);
					int entryQty = stock.getEntryQty() + oldStock.getEntryQty();
					int inStock = stock.getInStock() + oldStock.getInStock();
					int allocatedStock = stock.getAllocatedStock() + oldStock.getAllocatedStock();
					int preAllocationStock = stock.getPreAllocationStock() + oldStock.getPreAllocationStock();
					stock.setEntryQty(entryQty);
					stock.setInStock(inStock);
					stock.setAllocatedStock(allocatedStock);
					stock.setPreAllocationStock(preAllocationStock);
					sumStock.put(judge, stock);
				} else {
					sumStock.put(judge, stock);
				}
			}
			returnStock.addAll(sumStock.values());
			return new ResponseMessage(true, returnStock);
		} catch (Exception e) {
			logger.error("StockService:方法[getStockStatistics]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 移库操作
	 * 
	 * @param stockTransfer
	 *            移库单表头实体
	 * @param stockTransferLists
	 *            移库明细实体集合
	 * @return 返回Responsemessage对象 [Success:false Status:10012] 参数为null
	 *         [Success:false Status:10020] 单号生成失败 [Success:false Status:10014]
	 *         返回影响行数为0 [Success:false Status:60008] 已无库存 [Success:false
	 *         Status:60007] 库存不足 [Success:false Status:60010] 并发问题
	 *         [Success:false Status:10003] 异常信息 [Success:false Status:60016]
	 *         该移库记录已被审核，不能进行该操作 [Success:true] 操作成功
	 */
	@Override
	public ResponseMessage stockTransfer(String transferNo) {
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}

			// 根据移库单号获取移库表头和表体集合
			StockTransfer stockTransfer = stockTransferDao.getStockTransferByTransferNo(transferNo);
			List<StockTransferList> stockTransferLists = stockTransferListDao
					.getStockTransferListByTransferNo(transferNo);
			if (stockTransfer.getIsAudit()) {
				return new ResponseMessage(false, "60016");
			}

			String receivingNoteNo = null;
			String deliveryNoteNo = null;
			String receivingMergeLotNo = null;
			String deliveryMergeLotNo = null;
			String fromWarehouse = stockTransfer.getFromWarehouse();
			String toWarehouse = stockTransfer.getToWarehouse();

			/*
			 * 如果是不同仓库生成出库单表头、入库单表头、状态历史
			 */
			if (!fromWarehouse.equals(toWarehouse)) {
				// 生成入库单号
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("RK",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				receivingNoteNo = (String) responseMessage.getData();
				// 生成出库单号
				responseMessage = serialNumberService.getSerialNumber("CK",
						new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				deliveryNoteNo = (String) responseMessage.getData();
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
				
				// 添加出库单表头
				int rows = deliveryNoteDao.save(new DeliveryNote(null, deliveryNoteNo, "401", stockTransfer.getOwnerCode(),
						null, null, stockTransfer.getFromWarehouse(), null, null, null, null, null, null, null, null,
						stockTransfer.getRemark(), stockTransfer.getCreateBy(), new Date(), null, null, 60, null, null));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				// 保存出库单状态历史
				rows = statusHistoryDao.save(new StatusHistory(null, deliveryNoteNo, null, new Date(),
						stockTransfer.getCreateBy(), "结束出货单", null, null, null, "1"));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				// 添加入库单表头
				rows = receivingNoteDao.save(new ReceivingNote(null, receivingNoteNo, "401", stockTransfer.getOwnerCode(),
						null, null, stockTransfer.getToWarehouse(), null, null, null, null, null, null, null, null,
						stockTransfer.getRemark(), stockTransfer.getCreateBy(), new Date(), null, null, 60));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}

				// 保存入库单状态历史
				rows = statusHistoryDao.save(new StatusHistory(null, receivingNoteNo, null, new Date(),
						stockTransfer.getCreateBy(), "结束入库单", null, null, null, "0"));
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}
			// 添加移库单表头
			stockTransfer.setDeliveryNoteNo(deliveryNoteNo);
			stockTransfer.setReceivingNoteNo(receivingNoteNo);
			stockTransfer.setTransferDate(new Date());
			stockTransfer.setIsAudit(true);
			int rows = stockTransferDao.save(stockTransfer);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}

			

			for (StockTransferList stockTransferList : stockTransferLists) {

				// 出库明细预告UUID
				String deliveryAsnUUID = UUID.randomUUID().toString();
				// 入库明细预告UUID
				String receivingAsnUUID = UUID.randomUUID().toString();
				// 入库货物信息UUID
				String receivingListUUID = UUID.randomUUID().toString();

				// 修改库存数
				// 通过商品UUID，仓库、库区、储位、单位查询库存信息
				List<Stock> stocks = stockDao.getStockByGoodsUUIDAndWarehouse(stockTransferList.getGoodsUuid(),
						stockTransferList.getFromWarehouse(), stockTransferList.getFromStorage(),
						stockTransferList.getFromLocation(), stockTransferList.getMu());
				if (stocks == null || stocks.isEmpty()) {
					// 已无库存
					return new ResponseMessage(false, "60008");
				}
				// 获取库存部分数据
				String stockUuid = null;
				Date entryDate = null;
				String entryType = null;
				String pn = null;
				String sku = null;
				String barcode = null;
				String item = null;
				String emsNo = null;
				String packagingNo = null;
				String palletNo = null;
				Boolean isQualifed = null;
				BigDecimal amounts = null;
				String currency = null;
				Date expirationDate = null;
				String manufactureLotNo = null;
				String packagingKey = null;

				/*
				 * 循环累计库存数
				 */
				int qty = 0;
				for (Stock stock : stocks) {
					// 获取计量单位
					String unit = stockDao.getUnit(stock.getMu());
					// 包装键
					packagingKey = "1/" + unit;
					// 出库货物信息UUID
					String deliveryListUUID = UUID.randomUUID().toString();
					// 获取可用库存(现库存-已分配量-预分配量)
					int newInStock = stock.getInStock() - stock.getPreAllocationStock() - stock.getAllocatedStock();
					if (newInStock == 0) {
						continue;
					}
					// 累计库存数
					qty += newInStock;
					// 判断移库数量是否大于累计数量
					if (stockTransferList.getTransferQty() > qty) {
						rows = stockDao.updateInStock(stock.getAllocatedStock(), stock.getInStock() - newInStock,
								stock.getUuid(), stock.getVersion());
						if (rows == 0) {
							return new ResponseMessage(false, "60010");
						}
						/*
						 * 如果是不同仓库生成出库货物信息
						 */
						if (!fromWarehouse.equals(toWarehouse)) {
							// 添加出库货物信息
							rows = deliveryPackingListDao.save(new DeliveryPackingList(null, deliveryListUUID,
									deliveryAsnUUID, deliveryNoteNo, stockTransferList.getGoodsUuid(), stock.getPn(),
									stockTransferList.getGoodsName(), null, stock.getItem(), stock.getEmsNo(),
									stock.getSku(), stockTransferList.getMu(), stockTransferList.getFromWarehouse(),
									stockTransferList.getFromStorage(), stockTransferList.getFromLocation(),
									stock.getUuid(), newInStock, stock.getPackagingNo(), packagingKey, null, null, null,
									stock.getPalletNo(), null, null, null, stock.getIsQualifed(),
									stockTransfer.getCreateBy(), new Date(), 20, null, null, deliveryMergeLotNo));
							if (rows == 0) {
								return new ResponseMessage(false, "10014");
							}
						}
						
					} else {
						// 剩余数量
						int surplusQty = qty - stockTransferList.getTransferQty();
						/**
						 * 修改原有数量
						 */
						rows = stockDao.updateInStock(stock.getAllocatedStock(),
								stock.getInStock() - newInStock + surplusQty, stock.getUuid(), stock.getVersion());
						if (rows == 0) {
							return new ResponseMessage(false, "60010");
						}
						stockUuid = stock.getUuid();
						entryDate = stock.getEntryDate();
						entryType = stock.getEntryType();
						pn = stock.getPn();
						sku = stock.getSku();
						barcode = stock.getBarcode();
						item = stock.getItem();
						emsNo = stock.getEmsNo();
						packagingNo = stock.getPackagingNo();
						palletNo = stock.getPalletNo();
						isQualifed = stock.getIsQualifed();
						amounts = stock.getAmounts();
						currency = stock.getCurrency();
						expirationDate = stock.getExpirationDate();
						manufactureLotNo = stock.getManufactureLotNo();
						/*
						 * 如果是不同仓库生成出库货物信息
						 */
						if (!fromWarehouse.equals(toWarehouse)) {
							// 添加出库货物信息
							rows = deliveryPackingListDao.save(new DeliveryPackingList(null, deliveryListUUID,
									deliveryAsnUUID, deliveryNoteNo, stockTransferList.getGoodsUuid(), pn,
									stockTransferList.getGoodsName(), null, item, emsNo, sku, stockTransferList.getMu(),
									stockTransferList.getFromWarehouse(), stockTransferList.getFromStorage(),
									stockTransferList.getFromLocation(), stockUuid, newInStock - surplusQty, packagingNo,
									packagingKey, null, null, null, palletNo, null, null, null, isQualifed,
									stockTransfer.getCreateBy(), new Date(), 20, null, null, deliveryMergeLotNo));
							if (rows == 0) {
								return new ResponseMessage(false, "10014");
							}
						}
						break;
					}
				}
				// 判断现可用库存数是否小于移库数量
				if (stockTransferList.getTransferQty() > qty) {
					return new ResponseMessage(false, "60007");
				}

				/*
				 * 如果是不同仓库生成出库明细预告、入库明细预告、入库货物信息
				 */
				if (!fromWarehouse.equals(toWarehouse)) {
					// 添加出库明细预告
					rows = deliveryNoteAsnDao.save(new DeliveryNoteAsn(null, deliveryAsnUUID, deliveryNoteNo,
							stockTransferList.getGoodsUuid(), item, emsNo, sku, stockTransferList.getMu(), pn,
							stockTransferList.getTransferQty(), null, stockTransferList.getGoodsName(), null, null, null,
							null, null, currency, packagingKey, stockTransfer.getCreateBy(), new Date(), null, null, null,
							null, null, null, null, packagingNo, false, amounts, deliveryMergeLotNo, 10));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}

					// 添加入库明细预告
					rows = receivingNoteAsnDao.save(new ReceivingNoteAsn(null, receivingAsnUUID, receivingNoteNo,
							stockTransferList.getGoodsUuid(), item, emsNo, sku, stockTransferList.getMu(), pn,
							stockTransferList.getGoodsName(), null, null, null, null, null, currency, packagingKey,
							stockTransferList.getTransferQty(), 1, stockTransferList.getTransferQty(),
							stockTransfer.getCreateBy(), new Date(), null, null, manufactureLotNo, null, null, null, null,
							packagingNo, isQualifed, 20, amounts, receivingMergeLotNo, false));

					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}

					// 添加入库货物信息
					rows = receivingPackingListDao.save(new ReceivingPackingList(null, receivingListUUID, receivingAsnUUID,
							receivingNoteNo, stockTransferList.getGoodsUuid(), pn, stockTransferList.getGoodsName(), null,
							item, emsNo, sku, stockTransferList.getMu(), stockTransferList.getToWarehouse(),
							stockTransferList.getToStorage(), stockTransferList.getToLocation(),
							stockTransferList.getTransferQty(), packagingNo, packagingKey, null, null, null, palletNo, null,
							null, null, null, null, isQualifed, stockTransfer.getCreateBy(), new Date(), null, null, 20,
							receivingMergeLotNo));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}

				}
				
				// 添加库存信息
				rows = stockDao.save(new Stock(null, UUID.randomUUID().toString(), entryDate, entryType,
						stockTransfer.getOwnerCode(), pn, sku, barcode, stockTransferList.getGoodsUuid(), item, emsNo,
						stockTransferList.getTransferQty(), stockTransferList.getTransferQty(), 0, 0,
						stockTransferList.getMu(), stockTransferList.getToWarehouse(), stockTransferList.getToStorage(),
						stockTransferList.getToLocation(), packagingNo, palletNo, isQualifed, amounts, currency,
						receivingListUUID, receivingNoteNo, null, null, 0, expirationDate, manufactureLotNo, null,
						stockTransferList.getGoodsName(), null, packagingKey));

				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
			}

			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("StockService:方法[stockTransfer]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	public void setStockDao(StockDao stockDao) {
		super.setBaseDao(stockDao);
		this.stockDao = stockDao;
	}

	public void setDeliveryNoteAsnDao(DeliveryNoteAsnDao deliveryNoteAsnDao) {
		this.deliveryNoteAsnDao = deliveryNoteAsnDao;
	}

	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		this.deliveryNoteDao = deliveryNoteDao;
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

	public static void setSerialNumberService(SerialNumberService serialNumberService) {
		StockServiceImpl.serialNumberService = serialNumberService;
	}

	public void setStockTransferDao(StockTransferDao stockTransferDao) {
		this.stockTransferDao = stockTransferDao;
	}

	public void setStockTransferListDao(StockTransferListDao stockTransferListDao) {
		this.stockTransferListDao = stockTransferListDao;
	}

	public void setStatusHistoryDao(StatusHistoryDao statusHistoryDao) {
		this.statusHistoryDao = statusHistoryDao;
	}

}
