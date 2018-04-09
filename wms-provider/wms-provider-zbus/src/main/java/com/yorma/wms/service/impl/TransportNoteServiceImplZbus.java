package com.yorma.wms.service.impl;


import java.sql.Connection;
import java.sql.SQLException;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.api.TransportNoteDao;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.impl.TransportNoteDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;

public class TransportNoteServiceImplZbus extends TransportNoteServiceImpl {

	private StockDao stockDao = new StockDaoImpl();
	private TransportNoteDao transportNoteDao=new TransportNoteDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao=new DeliveryPackingListDaoImpl();
	
	
	
	public TransportNoteServiceImplZbus() {
		this.setStockDao(stockDao);
		this.setTransportNoteDao(transportNoteDao);
		this.setDeliveryPackingListDao(deliveryPackingListDao);
	}
	
	@Override
	public ResponseMessage updateStatusByTransportNoteNo(String transportNoteNo, int status) {
		Connection conn =null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			transportNoteDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.updateStatusByTransportNoteNo(transportNoteNo, status);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			stockDao.setConnection(null);
			transportNoteDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
			}
			
		}
		
	}
	
		
	
}
