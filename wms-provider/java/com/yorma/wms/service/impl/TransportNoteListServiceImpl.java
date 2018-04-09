package com.yorma.wms.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.TransportNoteListDao;
import com.yorma.wms.entity.TransportNoteList;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;
import com.yorma.wms.entity.dto.TransportNoteListDto;
import com.yorma.wms.service.api.TransportNoteListService;
import com.yorma.wms.service.impl.base.BaseServiceImpl;

public class TransportNoteListServiceImpl extends BaseServiceImpl<TransportNoteList> implements TransportNoteListService {
	
	private static final Logger logger=LoggerFactory.getLogger(TransportNoteList.class);
	
	private TransportNoteListDao transportNoteListDao;
	private DeliveryPackingListDao deliveryPackingListDao;

	/**
	 * 根据运送单号查询运输配送明细
	 */
	@Override
	public ResponseMessage getTransportNoteListDtoByTransportNoteNo(String transportNoteNo) {
		try {
			if (isBlank(transportNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			List<TransportNoteListDto> transportNoteListDtos=transportNoteListDao.getTransportNoteListDtoByTransportNoteNo(transportNoteNo);
			return new ResponseMessage(true, transportNoteListDtos); 
		} catch (Exception e) {
			logger.error("TransportNoteListService:方法[getTransportNoteListDtoByTransportNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据UUID删除运输配送信息
	 */
	@Override
	public ResponseMessage removeTransportNoteListByUUID(String uuid) {
		try {
			if (isBlank(uuid)) {
				return new ResponseMessage(false, "10012");
			}
			TransportNoteList transportNoteList=transportNoteListDao.getTransportNoteListByUUID(uuid);
			int rows=transportNoteListDao.removeTransportNoteListByUUID(uuid);
			rows=deliveryPackingListDao.updateTranspotyNoteNoByUUID(null, transportNoteList.getPlUuid());
			return new ResponseMessage(true, rows);
		} catch (Exception e) {
			logger.error("TransportNoteListService:方法[removeTransportNoteListByUUID]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	/**
	 * 根据拣货信息生成与描述配送明细
	 */
	@Override
	public ResponseMessage saveTransportNoteList(List<DeliveryPackingListDto> packingListDtos, String transportNoteNo) {
		try {
			if (packingListDtos == null || packingListDtos.isEmpty() || isBlank(transportNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			
			for (DeliveryPackingListDto deliveryPackingListDto : packingListDtos) {
				TransportNoteList transportNoteList=new TransportNoteList();
				transportNoteList.setUuid(UUID.randomUUID().toString());
				transportNoteList.setTransportNoteNo(transportNoteNo);
				transportNoteList.setDeliveryNoteNo(deliveryPackingListDto.getDeliveryNoteNo());
				transportNoteList.setDeliveryQty(deliveryPackingListDto.getDeliveryQty());
				transportNoteList.setGoodsName(deliveryPackingListDto.getGoodsName());
				transportNoteList.setMu(deliveryPackingListDto.getMu());
				transportNoteList.setOwnerCode(deliveryPackingListDto.getDepartCode());
				transportNoteList.setPackagingKey(deliveryPackingListDto.getPackagingKey());
				transportNoteList.setPackagingNo(deliveryPackingListDto.getPackagingNo());
				transportNoteList.setPalletNo(deliveryPackingListDto.getPalletNo());
				transportNoteList.setPlUuid(deliveryPackingListDto.getUuid());
				transportNoteList.setPn(deliveryPackingListDto.getPn());
				int rows=transportNoteListDao.save(transportNoteList);
				if (rows ==0) {
					return new ResponseMessage(false, "10014");
				}
				rows=deliveryPackingListDao.updateTranspotyNoteNoByUUID(transportNoteNo, deliveryPackingListDto.getUuid());
				if (rows ==0) {
					return new ResponseMessage(false, "10014");
				}
			}
			return new ResponseMessage(true, Boolean.valueOf(true));
		} catch (Exception e) {
			logger.error("TransportNoteListService:方法[saveTransportNoteList]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	
	/**
	 * 根据运送单号查询配送明细
	 * @param transportNoteNo	配送单号
	 * @return	ResponseMessage对象
	 * 				<pre>
	 * 					[success:false,status:10012]参数为null或空字符串
	 * 					[success:false,status:10003]异常信息
	 * 					[success:true，date:List<配送明细实体>]操作成功
	 * 				</pre>
	 */
	@Override
	public ResponseMessage getTransportNoteListByTransportNoteNo(String transportNoteNo) {
		try {
			if (isBlank(transportNoteNo)) {
				return new ResponseMessage(false, "10012");
			}
			return new ResponseMessage(true, transportNoteListDao.getTransportNoteListByTransportNoteNo(transportNoteNo)); 
		} catch (Exception e) {
			logger.error("TransportNoteListService:方法[getTransportNoteListByTransportNoteNo]出现异常，异常信息是 :{}", e);
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}
	}
	
	
	public void setTransportNoteListDao(TransportNoteListDao transportNoteListDao){
		super.setBaseDao(transportNoteListDao);
		this.transportNoteListDao=transportNoteListDao;
	}
	 public void  setDeliveryPackingListDao(DeliveryPackingListDao deliveryPackingListDao){
		 this.deliveryPackingListDao=deliveryPackingListDao;
	 }
	
}
