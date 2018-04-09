package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.CyclecountDao;
import com.yorma.wms.dao.api.CyclecountListDao;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.impl.CyclecountDaoImpl;
import com.yorma.wms.dao.memory.impl.CyclecountListDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.Cyclecount;
import com.yorma.wms.entity.CyclecountList;

public class CyclecountServiceImplZbus extends CyclecountServiceImpl {

	private StockDao stockDao = new StockDaoImpl();
	private CyclecountDao cyclecountDao = new CyclecountDaoImpl();
	private CyclecountListDao cyclecountListDao = new CyclecountListDaoImpl();
	
	private DeliveryNoteDao deliveryNoteDao = new DeliveryNoteDaoImpl();
	private DeliveryNoteAsnDao deliveryNoteAsnDao = new DeliveryNoteAsnDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();
	
	private ReceivingNoteDao receivingNoteDao = new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao = new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao receivingPackingListDao = new ReceivingPackingListDaoImpl();
	
	
	
	private static SerialNumberService serialNumberService;

	
	private void config() {
		serialNumberService =ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	@SuppressWarnings("static-access")
	public CyclecountServiceImplZbus() {
		config();
		this.setStockDao(stockDao);
		this.setyclecountDao(cyclecountDao);
		this.setCyclecountListDao(cyclecountListDao);
		this.setDeliveryNoteDao(deliveryNoteDao);
		this.setDeliveryNoteAsnDao(deliveryNoteAsnDao);
		this.setDeliveryPackingListDao(deliveryPackingListDao);
		this.setReceivingNoteDao(receivingNoteDao);
		this.setReceivingNoteAsnDao(receivingNoteAsnDao);
		this.setReceivingPackingListDao(receivingPackingListDao);
		this.setSerialNumberService(serialNumberService);
	}
	
	/**
	 * 保存盘点单及明细
	 */
	@Override
	public ResponseMessage saveCyclecount(Cyclecount cyclecount, List<CyclecountList> cyclecountLists) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			cyclecountDao.setConnection(conn);
			cyclecountListDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveCyclecount(cyclecount, cyclecountLists);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
				return responseMessage;
			}
			conn.commit();
			cyclecountDao.setConnection(null);
			cyclecountListDao.setConnection(null);
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
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
				}
			}
		}
	}
	
	/**
	 * 根据盘点单号调整库存
	 */
	@Override
	public ResponseMessage adjustmentStock(String cyclecountNo, String createBy) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			cyclecountDao.setConnection(conn);
			cyclecountListDao.setConnection(conn);
			stockDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.adjustmentStock(cyclecountNo, createBy);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
				return responseMessage;
			}
			conn.commit();
			cyclecountDao.setConnection(null);
			cyclecountListDao.setConnection(null);
			stockDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
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
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
				}
			}
		}
	}
	
	/**
	 * 根据盘点单号删除盘点单表头信息
	 */
	@Override
	public ResponseMessage removeCyclecountBycyclecountNo(String cyclecountNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			cyclecountDao.setConnection(conn);
			cyclecountListDao.setConnection(conn);
			ResponseMessage responseMessage = super.removeCyclecountBycyclecountNo(cyclecountNo);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
				return responseMessage;
			}
			conn.commit();
			cyclecountDao.setConnection(null);
			cyclecountListDao.setConnection(null);
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
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
				}
			}
		}
	}
	
}
