package com.yorma.wms.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.api.TransportNoteDao;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.TransportNote;
import com.yorma.wms.entity.dto.TransportNoteDto;
import com.yorma.wms.service.api.TransportNoteService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class TransportNoteServiceImpl extends BaseServiceImpl<TransportNote> implements TransportNoteService {

	private static final Logger logger = LoggerFactory.getLogger(TransportNote.class);
	
	private StockDao stockDao;
	private TransportNoteDao transportNoteDao;
	private DeliveryPackingListDao	deliveryPackingListDao;
	
	
	
	
	
	@Override
	public ResponseMessage save(TransportNote transportNote) {
		try {
			
			if (transportNote.getId() == null) {
				if (transportNoteDao.getTransportNoteByTransportNoteNo(transportNote.getTransportNoteNo()) != null) {
					return new ResponseMessage(false, "60002", String.format("异常信息：%s", "配送单号重复！"));
				}
				transportNote.setCreateDate(new Date());
			}else {
				transportNote.setLastUpdateDate(new Date());
			}
			int rows=transportNoteDao.save(transportNote);
			if (rows == 0) {
				return new ResponseMessage(false, "10014");
			}
			Field field = transportNote.getClass().getDeclaredField("id");
			field.setAccessible(true);// 设置些属性是可以访问的
			Object id = field.get(transportNote); // 得到此属性的值
			return new ResponseMessage(true, id);
			
		} catch (Exception e) {
			logger.error("TransportNoteService:方法[save]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据条件查询送货单信息
	 */
	@Override
	public ResponseMessage getTransportNoteDto(Map<String, Object> parameters) {
		try {
			List<TransportNoteDto> transportNoteDtos=transportNoteDao.getTransportNoteDto(parameters);
			
			return new ResponseMessage(true, transportNoteDtos);
		} catch (Exception e) {
			logger.error("TransportNoteService:方法[getTransportNoteDto]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据运送单号删除运送单信息
	 */
	@Override
	public ResponseMessage removeTransportNoteByTransportNoteNo(String transportNoteNo) {
		try {
			if (isBlank(transportNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			int rows=transportNoteDao.removeByTransportNoteNo(transportNoteNo);
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("TransportNoteService:方法[removeTransportNoteByTransportNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 修改状态，如果状态为装载则修改拣货信息为出库
	 */
	@Override
	public ResponseMessage updateStatusByTransportNoteNo(String transportNoteNo, int status) {
		try {
			if (isBlank(transportNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			TransportNote transportNote=transportNoteDao.getTransportNoteByTransportNoteNo(transportNoteNo);
			if (transportNote.getStatus()+10 == status) {
				int rows =transportNoteDao.updateStatusByTransportNoteNo(transportNoteNo, status);
				if (rows == 0) {
					return new ResponseMessage(false, "10014");
				}
				if (status == 30) {
					List<DeliveryPackingList> deliveryPackingLists=deliveryPackingListDao.getDeliveryPackingListByTransportNoteNo(transportNoteNo);
					for (DeliveryPackingList packingList : deliveryPackingLists) {
						DeliveryPackingList deliveryPackingList = deliveryPackingListDao.getdiveryPackingListByUUID(packingList.getUuid());
						Stock stock = stockDao.getStockByUUID(deliveryPackingList.getStockUuid());
						 rows = stockDao.updateInStock(stock.getAllocatedStock() - deliveryPackingList.getDeliveryQty(),
								stock.getInStock() - deliveryPackingList.getDeliveryQty(), stock.getUuid(), stock.getVersion());
						if (rows == 0) {
							return new ResponseMessage(false, "60010");
						}
					}
					rows=deliveryPackingListDao.updateStatusByTransportNoteNo(transportNoteNo, 20);
					if (rows == 0) {
						return new ResponseMessage(false, "10014");
					}
				}
			}
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			logger.error("TransportNoteService:方法[updateStatusByTransportNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据运送单号查询运送信息
	 */
	@Override
	public ResponseMessage getTransportNoteDtoByTransportNoteNo(String transportNoteNo) {
		try {
			if (isBlank(transportNoteNo)) {
				return new ResponseMessage(false, 10012);
			}
			TransportNoteDto transportNoteDto=transportNoteDao.getTransportNoteDtoByTransportNoteNo(transportNoteNo);
			return new ResponseMessage(true, transportNoteDto);
			
		} catch (Exception e) {
			logger.error("TransportNoteService:方法[getTransportNoteDtoByTransportNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setTransportNoteDao(TransportNoteDao transportNoteDao){
		super.setBaseDao(transportNoteDao);
		this.transportNoteDao=transportNoteDao;
	}
	
	
	public void setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao){
		this.deliveryPackingListDao=deliveryPackingListDao;
	}
	
	public void setStockDao(StockDao stockDao){
		this.stockDao=stockDao;
	}

	
}
