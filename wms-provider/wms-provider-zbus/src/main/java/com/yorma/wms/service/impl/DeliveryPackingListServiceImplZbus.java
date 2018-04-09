package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.memory.impl.DeliveryNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;

public class DeliveryPackingListServiceImplZbus extends DeliveryPackingListServiceImpl {

	private DeliveryNoteDao deliveryNoteDao=new DeliveryNoteDaoImpl();
	
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();

	public DeliveryPackingListServiceImplZbus() {
		this.setDeliveryPackingListDao(deliveryPackingListDao);
		
		this.setDeliveryNoteDao(deliveryNoteDao);
	}

	@Override
	public ResponseMessage deleteBatchByNoteNo(String deliveryNoteNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.deleteBatchByNoteNo(deliveryNoteNo);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			deliveryPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
				}
			}
		}
	}

}
