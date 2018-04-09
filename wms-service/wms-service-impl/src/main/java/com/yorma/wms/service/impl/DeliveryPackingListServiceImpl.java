package com.yorma.wms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;
import com.yorma.wms.service.api.DeliveryPackingListService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

/**
 * 出库货物信息service实现
 * 
 * @Description:
 * @author su
 * @date 2017年8月28日
 * @version V1.0
 */
public class DeliveryPackingListServiceImpl extends BaseServiceImpl<DeliveryPackingList>
		implements DeliveryPackingListService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryPackingList.class);

	private StockDao stockDao;
	private DeliveryNoteDao deliveryNoteDao;
	private DeliveryPackingListDao deliveryPackingListDao;

	@Override
	public ResponseMessage save(DeliveryPackingList dpl) {
		if (dpl == null) {
			return new ResponseMessage(false, "10004");
		}
		if (dpl.getId() == null) {
			UUID uuid = UUID.randomUUID();
			dpl.setUuid(uuid.toString());
		}
		return super.save(dpl);
	}

	/**
	 * 根据出货单号及其他条件查询拣货信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @param status
	 *            状态
	 * @param deliveryLotNo
	 *            出货批次
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryPackingListByNoteNo(String deliveryNoteNo, Integer status, String deliveryLotNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryPackingList> lil = deliveryPackingListDao.getDeliveryPackingListByNoteNo(deliveryNoteNo,
					status, deliveryLotNo);
			return new ResponseMessage(true, lil);
		} catch (Exception e) {
			logger.error("DeliveryPackingListService:方法[getDeliveryPackingListByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出货单号判断是否全部拣货
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	status:001:无拣货信息，002代表有拣货信息但未拣货确认，003代表有拣货信息并且已经拣货] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryPackingListBydeliveryNoteNo(String deliveryNoteNo) {
		try {
			if (deliveryNoteNo == null) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryPackingList> packingLists = deliveryPackingListDao.getDeliveryPackingListByNoteNo(deliveryNoteNo, null, null);
			if (packingLists == null ||packingLists.isEmpty()) {
				//001代表没有拣货信息
				return new ResponseMessage(true, "001");
			}
			packingLists = deliveryPackingListDao.getDeliveryPackingListByNoteNo(deliveryNoteNo, 10, null);
			if (packingLists!=null && !packingLists.isEmpty()) {
				//003代表有拣货信息并且已经拣货  
				return new ResponseMessage(true, "003");
			} else {
				//002代表有拣货信息但为拣货确认
				return new ResponseMessage(true, "002");
			}
		} catch (Exception e){
			logger.error("DeliveryPackingListService:方法[getDeliveryPackingListBydeliveryNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}

	}

	/**
	 * 根据明细UUID判断有无拣货信息
	 * 
	 * @param asnUuid
	 *            明细UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	status:001:无拣货信息，002代表有拣货信息但未拣货确认，003代表有拣货信息并且已经拣货确认] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryPackingListCountByAsnUUID(String asnUuid) {
		try {
			if (isBlank(asnUuid)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryPackingList> deliveryPackingLists=deliveryPackingListDao.getDeliveryPackingListByAsnUUID(asnUuid);
			if (deliveryPackingLists == null || deliveryPackingLists.isEmpty()) {
				//001代表该明细没有拣货信息
				return new ResponseMessage(true, "001");
			}
			List<DeliveryPackingList> packingLists = deliveryPackingListDao.getDeliveryPackingListByAsnUUID(asnUuid,10);
			
			if (packingLists !=null && !packingLists.isEmpty()) {
				//003代表该明细有拣货信息并且已拣货确认
				return new ResponseMessage(true, "003");
			} else {
				//002代表该明细有拣货信息但未拣货确认
				return new ResponseMessage(true, "002");
				
			}
		} catch (Exception e) {
			logger.error("DeliveryPackingListService:方法[getDeliveryPackingListCountByAsnUUID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据出货单号删除入库货物信息
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: false status:60009] 出货货物删除失败，请确认出货单状态
	 * 				[success: false status:60010] 修改库存信息时出错
	 * 				[success: true 	data:影响行数] 操作成功
	 *         </pre>
	 */
	@SuppressWarnings("unused")
	@Override
	public ResponseMessage deleteBatchByNoteNo(String deliveryNoteNo) {
		try {
			if (isBlank(deliveryNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			// 为判断出货单状态是否在出库之前
			int status = deliveryNoteDao.getStatus(deliveryNoteNo);
			if (status >= 50) {
				return new ResponseMessage(false, "60009");
			}
			// 获取拣货信息，用于判断是否有拣货信息，和修改库存信息
			List<DeliveryPackingList> dePL = deliveryPackingListDao.getDeliveryPackingListByNoteNo(deliveryNoteNo);
			// 当出货单状态为已分配时，需要核减库存中的预分配量
			if (deliveryNoteDao.getStatus(deliveryNoteNo) == 30) {
				for (DeliveryPackingList dpl : dePL) {
					Stock s = stockDao.getStockByUUID(dpl.getStockUuid());
					int rows = stockDao.updatePreAllocationStock(s.getPreAllocationStock() - dpl.getDeliveryQty(),
							s.getUuid(), s.getVersion());
					if (rows == 0) {
						return new ResponseMessage(false, "60010");
					}
				}
			}
			// 挡出货单状态为已拣货时，需要核减库存中的已分配量
			if (deliveryNoteDao.getStatus(deliveryNoteNo) == 40) {
				for (DeliveryPackingList dpl : dePL) {
					Stock s = stockDao.getStockByUUID(dpl.getStockUuid());
					int rows = stockDao.updateAllocatedStock(s.getAllocatedStock() - dpl.getDeliveryQty(),
							s.getPreAllocationStock(), s.getUuid(), s.getVersion());
					if (rows == 0) {
						return new ResponseMessage(false, "60010", String.format("异常信息：%s", "修改库存信息时出错"));
					}
				}
			}
			int rows = deliveryPackingListDao.deleteDeliveryPackingList(deliveryNoteNo);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("DeliveryPackingListService:方法[deleteBatchByNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据库存UUID查询拣货信息
	 * 
	 * @param stockUuid
	 *            库存UUID
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryPackingListByStockUUID(String stockUuid) {
		try {
			if (isBlank(stockUuid)) {
				return new ResponseMessage(false, "10012");
			}
			List<DeliveryPackingList> packingLists = deliveryPackingListDao
					.getDeliveryPackingListByStockUUID(stockUuid);
			return new ResponseMessage(true, packingLists);
		} catch (Exception e) {
			logger.error("DeliveryPackingListService:方法[getDeliveryPackingListByStockUUID]出现异常，异常信息是 : {}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	/**
	 * 根据条件查询拣货信息
	 * 
	 * @param parameters
	 *            参数条件
	 * @return 返回ResponseMessage对象
	 *        <pre>
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data:List<拣货信息实体Dto>] 操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage getDeliveryPackingList(Map<String, Object> parameters) {
		try {
			List<DeliveryPackingListDto> deliveryPackingListDtos = deliveryPackingListDao
					.getDeliveryPackingListDto(parameters);
			return new ResponseMessage(true, deliveryPackingListDtos);
		} catch (Exception e) {
			logger.error("DeliveryPackingListService:方法[getDeliveryPackingList]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}

	
	
	
	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao) {
		super.setBaseDao(deliveryPackingListDao);
		this.deliveryPackingListDao = deliveryPackingListDao;
	}

	public void setDeliveryNoteDao(DeliveryNoteDao deliveryNoteDao) {
		this.deliveryNoteDao = deliveryNoteDao;
	}



}
