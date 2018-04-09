package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.TransportNoteListDao;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.TransportNoteListDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;

public class TransportNoteListServiceImplZbus extends TransportNoteListServiceImpl {

	private TransportNoteListDao transportNoteListDao = new TransportNoteListDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();

	public TransportNoteListServiceImplZbus() {
		
		this.setTransportNoteListDao(transportNoteListDao);
		this.setDeliveryPackingListDao(deliveryPackingListDao);
	}

	@Override
	public ResponseMessage removeTransportNoteListByUUID(String uuid) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			transportNoteListDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.removeTransportNoteListByUUID(uuid);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			transportNoteListDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					e.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
			}
		}
	}

	/**
	 * 根据拣货信息生成与描述配送明细
	 */
	@Override
	public ResponseMessage saveTransportNoteList(List<DeliveryPackingListDto> packingListDtos, String transportNoteNo) {
		Connection conn = null;
		try {
			
			conn = MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			transportNoteListDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveTransportNoteList(packingListDtos, transportNoteNo);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			transportNoteListDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					e.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
			}
		}
	}

	
	
	
	
}
