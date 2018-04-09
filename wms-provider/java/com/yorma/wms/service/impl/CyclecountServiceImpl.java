package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.dao.api.CyclecountDao;
import com.yorma.wms.dao.api.CyclecountListDao;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.Cyclecount;
import com.yorma.wms.entity.CyclecountList;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.service.api.CyclecountService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 盘点Service接口实现
 * @author Su
 * 2017年12月6日
 */
public class CyclecountServiceImpl extends BaseServiceImpl<Cyclecount> implements CyclecountService {

	private static final Logger logger=LoggerFactory.getLogger(Cyclecount.class);
	
	private StockDao stockDao;
	private CyclecountDao cyclecountDao;
	private CyclecountListDao cyclecountListDao;
	
	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryNoteAsnDao deliveryNoteAsnDao;
	private DeliveryPackingListDao deliveryPackingListDao;
	
	private ReceivingNoteDao receivingNoteDao;
	private ReceivingNoteAsnDao receivingNoteAsnDao;
	private ReceivingPackingListDao receivingPackingListDao;
	
	private static SerialNumberService serialNumberService;
	
	/**
	 * 保存盘点信息
	 * @param entity	盘点实体
	 * @return	ResponseMessage对象
	 * 				[Success：false , Status:10004 ] 参数对象为null
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10014 ] 影响行数为0
	 * 				[Success：true , Data: "id,盘点单号"] 操作成功
	 */
	@Override
	public ResponseMessage save(Cyclecount entity) {
		try {

			if (entity == null) {
				return new ResponseMessage(false, 10004);
			}
			if (entity.getId()== null) {
				ResponseMessage responseMessage = serialNumberService.getSerialNumber("C", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
				if (!responseMessage.getSuccess()) {
					return responseMessage;
				}
				String cyclecountNo=(String)responseMessage.getData();
				entity.setCyclecountNo(cyclecountNo);
				entity.setCreateDate(new Date());
				
			}
			int rows = cyclecountDao.save(entity);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			Field field = entity.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(entity)+","+entity.getCyclecountNo(); // 得到此属性的值
			return new ResponseMessage(true, value);
		
		} catch (Exception e) {
			logger.error("CyclecountService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据条件查询盘点信息
	 * @param parames	参数集合
	 * @return	ResponseMessage对象
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: List<盘点实体>] 操作成功
	 */
	@Override
	public ResponseMessage getCyclecount(Map<String, Object> parames) {
		try {
			return new ResponseMessage(true, cyclecountDao.getCyclecount(parames));
		} catch (Exception e) {
			logger.error("CyclecountService:方法[getCyclecount]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 保存盘点单及明细
	 * @param cyclecount	盘点实体
	 * @param cyclecountLists	盘点明细实体集合
	 * @return	ResponseMessage对象
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10004 ] 盘点表头为null
	 * 				[Success：false , Status:60002 ] 盘点单号重复
	 * 				[Success：false , Status:10014 ] 保存失败
	 * 				[Success：true , Data: 盘点表头ID] 操作成功
	 */
	@Override
	public ResponseMessage saveCyclecount(Cyclecount cyclecount, List<CyclecountList> cyclecountLists) {
		try {
			if (cyclecount == null) {
				return new ResponseMessage(false, "10004", String.format("异常信息", "盘点单表头无数据！"));
			}
			if (cyclecount.getId()==null) {
				if (cyclecountDao.getCountCyclecountByCyclecountNo(cyclecount.getCyclecountNo()) != 0) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "盘点单号重复!"));
				}
				cyclecount.setCreateDate(new Date());
			}else {
				cyclecount.setLastUpdateDate(new Date());
			}
			int rows=cyclecountDao.save(cyclecount);
			if (rows == 0) {
				return new ResponseMessage(false, "10014", String.format("异常信息：", "保存盘点单失败，请核实表头数据！"));
			}
			//删除原有盘点明细
			rows = cyclecountListDao.removeCyclecountListByCyclecountNo(cyclecount.getCyclecountNo());
			/*
			 * 循环保存新的盘点明细
			 */
			if (cyclecountLists!= null && !cyclecountLists.isEmpty()) {
				for (CyclecountList cyclecountList : cyclecountLists) {
					cyclecountList.setId(null);
					cyclecountList.setCyclecountNo(cyclecount.getCyclecountNo());
					rows = cyclecountListDao.save(cyclecountList);
					if (rows == 0) {
						return new ResponseMessage(false, "10014", String.format("异常信息：", "保存盘点单失败，请核实表体数据！"));
					}
				}
				
			}
			Field field = cyclecount.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object value = field.get(cyclecount);
			return new ResponseMessage(true, value);
		} catch (Exception e) {
			logger.error("CyclecountService:方法[saveCyclecount]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据条件盘点库存
	 * @param parames
	 * @return  ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：true , Data: List<盘点明细>] 操作成功
	 * 			</pre>
	 */
	@Override
	public ResponseMessage getStockCyclecount(Map<String, Object> parames) {
		try {
			List<Stock> stocks = stockDao.getStock(parames);
			// 返回的集合
			List<CyclecountList> cyclecountLists = new ArrayList<>();
			// 暂时存储汇总的库存
			Map<String, CyclecountList> sumCyclecountList = new LinkedHashMap<String,CyclecountList>();
			for (Stock stock : stocks) {
				// 设置判断条件(仓库代码+库区代码+储位代码+货主+料号+主单位)
				String judge = stock.getWarehouse() + stock.getStorage() + stock.getLocation() + stock.getOwnerCode() + stock.getGoodsUuid() + stock.getMu();
				// 判断是否有条件相同的库存记录，如果有库存数累计
				if (sumCyclecountList.containsKey(judge)) {
					CyclecountList oldCyclecountList = sumCyclecountList.get(judge);
					//计算库存数
					int stockQty = stock.getInStock()+oldCyclecountList.getStockQty();
					oldCyclecountList.setStockQty(stockQty);
					sumCyclecountList.put(judge, oldCyclecountList);
				} else {
					CyclecountList cyclecountList = new CyclecountList(null, null, stock.getGoodsName(), stock.getGoodsModel(), stock.getItem(), stock.getPn(), stock.getSku(), null, stock.getMu(), stock.getInStock(), null, null, stock.getWarehouse(), stock.getStorage(), stock.getLocation(), stock.getGoodsUuid(), stock.getOwnerCode(), null, null); 
					sumCyclecountList.put(judge, cyclecountList);
				}
			}
			cyclecountLists.addAll(sumCyclecountList.values());
			return new ResponseMessage(true, cyclecountLists);
		} catch (Exception e) {
			logger.error("CyclecountService:方法[getStockCyclecount]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据盘点单号调整库存
	 * @param cyclecountNo	盘点单号
	 * @return	ResponseMessage对象
	 * 			<pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号或创建人为null
	 * 				[Success：false , Status:60040 ] 该记录已调整
	 * 				[Success：false , Status:60041 ] 盘点明细无数据
	 * 				[Success：false , Status:10014 ] 调整失败
	 * 				[Success：true ] 操作成功
	 * 			</pre>
	 */
	@Override
	public ResponseMessage adjustmentStock(String cyclecountNo,String createBy) {
		try {
			if (isBlank(cyclecountNo) || isBlank(createBy)) {
				return new ResponseMessage(false, "10012");
			}
			Integer status=cyclecountDao.getStatusByCyclecountNo(cyclecountNo);
			if (status!=null && status== 10) {
				return new ResponseMessage(false, "60040", String.format("异常信息：%s", "该记录已调整"));
			}
			List<CyclecountList> cyclecountLists = cyclecountListDao.getCyclecountListByCyclecountNo(cyclecountNo);
			if (cyclecountLists.isEmpty()) {
				return new ResponseMessage(false, "60041", String.format("异常信息：%s", "盘点明细无数据"));
			}
			for (CyclecountList cyclecountList : cyclecountLists) {
				//获取计量单位
				String unit = stockDao.getUnit(cyclecountList.getMu());
				//包装键
				String packagingKey="1/"+unit;
				
				/*
				 * 生成入库单
				 */
				if (cyclecountList.getProfitLoss()<0) {
					/*
					 * 生成入库单号
					 */
					ResponseMessage responseMessage = serialNumberService.getSerialNumber("RK", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String receivingNoteNo=(String)responseMessage.getData();
					/*
					 * 生成包装条码
					 */
					responseMessage = serialNumberService.getSerialNumber("PK", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					/*
					 * 合并批次号
					 */
					String packagingNo=(String)responseMessage.getData();
					responseMessage = serialNumberService.getSerialNumber("ME", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String mergeLotNo=(String)responseMessage.getData();
					//明细UUID
					String asnUuid=UUID.randomUUID().toString();
					//入库货物信息UUID
					String receivingPackingListUuid=UUID.randomUUID().toString();

					int rows = receivingNoteDao.save(new ReceivingNote(null, receivingNoteNo, "402", cyclecountList.getOwnerCode(), null, null, cyclecountList.getWarehouse(), null, "1", new BigDecimal(0), null, null, null, null, null, null, createBy, new Date(), null, null, 60)); 
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					rows = receivingNoteAsnDao.save(new ReceivingNoteAsn(null, asnUuid, receivingNoteNo, cyclecountList.getGoodsUuid(), cyclecountList.getItem(), null, cyclecountList.getSku(), cyclecountList.getMu(), cyclecountList.getPn(), cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), null, null, null, null, null, packagingKey, cyclecountList.getProfitLoss(), 1, cyclecountList.getProfitLoss(), createBy, new Date(), new BigDecimal(0), null, null, null, null, null, null, packagingNo, true, 20, null, mergeLotNo, false)); 
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					rows = receivingPackingListDao.save(new ReceivingPackingList(null, receivingPackingListUuid, asnUuid, receivingNoteNo, cyclecountList.getGoodsUuid(), cyclecountList.getPn(), cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), cyclecountList.getItem(), null, cyclecountList.getSku(), cyclecountList.getMu(), cyclecountList.getWarehouse(), cyclecountList.getStorage(), cyclecountList.getLocation(), cyclecountList.getProfitLoss(), packagingNo, packagingKey, null, null, null, null, null, null, null, null, null, true, createBy, new Date(), new BigDecimal(1), null, 20, mergeLotNo)); 
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					rows = stockDao.save(new Stock(null, UUID.randomUUID().toString(), new Date(), "402", cyclecountList.getOwnerCode(), cyclecountList.getPn(), cyclecountList.getSku(), null, cyclecountList.getGoodsUuid(),cyclecountList.getItem(), null, cyclecountList.getProfitLoss(), cyclecountList.getProfitLoss(), null, null, cyclecountList.getMu(), cyclecountList.getWarehouse(), cyclecountList.getStorage(), cyclecountList.getLocation(), packagingNo, null, true, null, null, receivingPackingListUuid, receivingNoteNo, new BigDecimal(1), null, 0, null, null, null, cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), packagingKey)); 
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					rows = cyclecountListDao.updateReceivingNoteNoById(receivingNoteNo, cyclecountList.getId());
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
				/*
				 * 生成出库单
				 */
				if (cyclecountList.getProfitLoss()>0) {
					/*
					 * 生成出库单号
					 */
					ResponseMessage responseMessage = serialNumberService.getSerialNumber("CK", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String deliveryNoteNo=(String)responseMessage.getData();
					/*
					 * 生成包装条码
					 */
					responseMessage = serialNumberService.getSerialNumber("PK", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String packagingNo=(String)responseMessage.getData();
					/*
					 * 合并批次号
					 */
					responseMessage = serialNumberService.getSerialNumber("ME", new SimpleDateFormat("yyyyMMdd").format(new Date()), 4);
					if (!responseMessage.getSuccess()) {
						return responseMessage;
					}
					String mergeLotNo=(String)responseMessage.getData();
					//明细UUID
					String asnUuid=UUID.randomUUID().toString();
					//拣货信息UUID
					String deliveryPackingListUuid=UUID.randomUUID().toString();
					
					int rows = deliveryNoteDao.save(new DeliveryNote(null, deliveryNoteNo, "402", cyclecountList.getOwnerCode(), null, null, cyclecountList.getWarehouse(), null, "1", null, null, null, null, null, new Date(), null, createBy, new Date(), null, null, 60, null, null));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					rows = deliveryNoteAsnDao.save(new DeliveryNoteAsn(null, asnUuid, deliveryNoteNo, cyclecountList.getGoodsUuid(), cyclecountList.getItem(), null, cyclecountList.getSku(), cyclecountList.getMu(), cyclecountList.getPn(), Math.abs(cyclecountList.getProfitLoss()), null, cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), null, null, null, null, null, packagingKey, createBy, new Date(), null, null, null, null, null, null, null, packagingNo, false, null, mergeLotNo, 20));
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
					/*
					 * 累计库存数
					 */
					List<Stock> stocks = stockDao.getStockByGoodsUUIDAndWarehouse(cyclecountList.getGoodsUuid(), cyclecountList.getWarehouse(), cyclecountList.getStorage(), cyclecountList.getLocation(), cyclecountList.getMu());
					
					int cumulativeQty=0;
					for (Stock stock : stocks) {
						cumulativeQty+=stock.getInStock()-stock.getAllocatedStock()-stock.getPreAllocationStock();
						if (cumulativeQty<Math.abs(cyclecountList.getProfitLoss())) {
							rows = deliveryPackingListDao.save(new DeliveryPackingList(null, deliveryPackingListUuid, asnUuid, deliveryNoteNo, cyclecountList.getGoodsUuid(), cyclecountList.getPn(), cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), cyclecountList.getItem(), null, cyclecountList.getSku(), cyclecountList.getMu(), cyclecountList.getWarehouse(), cyclecountList.getStorage(), cyclecountList.getLocation(), stock.getUuid(), stock.getInStock()-stock.getAllocatedStock()-stock.getPreAllocationStock(), packagingNo, packagingKey, null, null, null, null, null, null, null, true, createBy, new Date(), 20, "1", null, mergeLotNo)); 
							if (rows == 0) {
								return new ResponseMessage(false, "10014");
							}
							rows = stockDao.updateInStock(stock.getAllocatedStock(), stock.getAllocatedStock()+stock.getPreAllocationStock(), stock.getUuid(), stock.getVersion());
							if (rows == 0) {
								return new ResponseMessage(false, "10014",String.format("异常信息：%s", "调整库存失败"));
							}
							
						}else {
							//剩余的数量
							int qty = cumulativeQty-Math.abs(cyclecountList.getProfitLoss());
							//在库数量-剩余数量=实际出库数
							rows = deliveryPackingListDao.save(new DeliveryPackingList(null, deliveryPackingListUuid, asnUuid, deliveryNoteNo, cyclecountList.getGoodsUuid(), cyclecountList.getPn(), cyclecountList.getGoodsName(), cyclecountList.getGoodsModel(), cyclecountList.getItem(), null, cyclecountList.getSku(), cyclecountList.getMu(), cyclecountList.getWarehouse(), cyclecountList.getStorage(), cyclecountList.getLocation(), stock.getUuid(), stock.getInStock()-qty, packagingNo, packagingKey, null, null, null, null, null, null, null, true, createBy, new Date(), 20, "1", null, mergeLotNo)); 
							if (rows == 0) {
								return new ResponseMessage(false, "10014");
							}
							//已分配数量+预分配数量+剩余数量=实际库存数
							rows = stockDao.updateInStock(stock.getAllocatedStock(), stock.getAllocatedStock()+stock.getPreAllocationStock()+qty, stock.getUuid(), stock.getVersion());
							if (rows == 0) {
								return new ResponseMessage(false, "10014",String.format("异常信息：%s", "调整库存失败"));
							}
						}
					}
					rows = cyclecountListDao.updateDeliveryNoteNoById(deliveryNoteNo, cyclecountList.getId());
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			int rows = cyclecountDao.updateStatusByCyclecountNo(cyclecountNo, 10);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("CyclecountService:方法[adjustmentStock]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据盘点单号查询盘点单表头信息
	 * @param cyclecountNo	盘点单号
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号为null或空字符串
	 * 				[Success：true , Data: 盘点信息实体] 操作成功
	 * 			 </pre>
	 */
	@Override
	public ResponseMessage getCyclecountBycyclecountNo(String cyclecountNo) {
		try {
			if (isBlank(cyclecountNo)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, cyclecountDao.getCyclecountByCyclecountNo(cyclecountNo));
		} catch (Exception e) {
			logger.error("CyclecountService:方法[getCyclecountBycyclecountNo]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据盘点单号删除盘点单表头信息
	 * @param cyclecountNo
	 * @return	ResponseMessage对象
	 * 			 <pre>
	 * 				[Success：false , Status:10003 ] 异常信息
	 * 				[Success：false , Status:10012 ] 盘点单号为null或空字符串
	 * 				[Success：true ] 操作成功
	 * 			 </pre>
	 */
	@Override
	public ResponseMessage removeCyclecountBycyclecountNo(String cyclecountNo) {
		try {
			if (isBlank(cyclecountNo)) {
				return new ResponseMessage(false, "10012");
			}
			Integer status=cyclecountDao.getStatusByCyclecountNo(cyclecountNo);
			if (status!=null && status== 10) {
				return new ResponseMessage(false, "60040", String.format("异常信息：%s", "该记录已调整"));
			}
			int rows = cyclecountDao.removeCyclecountByCyclecountNo(cyclecountNo);
			rows=cyclecountListDao.removeCyclecountListByCyclecountNo(cyclecountNo);
			return new ResponseMessage(true);
		} catch (Exception e) {
			logger.error("CyclecountService:方法[removeCyclecountBycyclecountNo]出现异常，异常信息是 :", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	public void setyclecountDao(CyclecountDao cyclecountDao){
		this.setBaseDao(cyclecountDao);
		this.cyclecountDao=cyclecountDao;
	}
	
	public static void setSerialNumberService(SerialNumberService serialNumberService){
		CyclecountServiceImpl.serialNumberService=serialNumberService;
	}

	public void setCyclecountListDao(CyclecountListDao cyclecountListDao) {
		this.cyclecountListDao = cyclecountListDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
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
	
}
