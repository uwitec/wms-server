package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.impl.DeliveryNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;


public class DeliveryNoteAsnServiceImplZbus extends DeliveryNoteAsnServiceImpl {

	private StockDao stockDao=new StockDaoImpl();
	private DeliveryNoteDao deliveryNoteDao=new DeliveryNoteDaoImpl();
	private DeliveryNoteAsnDao deliveryNoteAsnDao=new DeliveryNoteAsnDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao=new DeliveryPackingListDaoImpl();
	
	private  SerialNumberService serialNumberService;

	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}

	
	public DeliveryNoteAsnServiceImplZbus() {
		config();
		this.setStockDao(stockDao);
		this.setDeliveryNoteDao(deliveryNoteDao);
		this.setDeliveryNoteAsnDao(deliveryNoteAsnDao);
		this.setDeliveryPackingListDao(deliveryPackingListDao);
		super.setSerialNumberService(serialNumberService);
	}
	
	/**
	 * 根据明细UUID删除明细的同时删除该明细下的所有拣货信息（事务）
	 */
	@Override
	public ResponseMessage removeByUUID(String uuid) {

		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.removeByUUID(uuid);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			stockDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e1));
				}
			}
		}
	
	}
}
