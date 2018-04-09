package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
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
import com.yorma.wms.dao.memory.impl.DeliveryNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.StatusHistoryDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.impl.StockTransferDaoImpl;
import com.yorma.wms.dao.memory.impl.StockTransferListDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.StockTransfer;
import com.yorma.wms.entity.StockTransferList;

public class StockTransferServiceImplZbus extends StockTransferServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(StockTransferServiceImplZbus.class);
	
	private StockTransferDao stockTransferDao = new StockTransferDaoImpl();
	private StockTransferListDao stockTransferListDao = new StockTransferListDaoImpl();
	
	private DeliveryNoteDao deliveryNoteDao = new DeliveryNoteDaoImpl();
	private DeliveryNoteAsnDao deliveryNoteAsnDao = new DeliveryNoteAsnDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();
	
	private ReceivingNoteDao receivingNoteDao = new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao = new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao receivingPackingListDao = new ReceivingPackingListDaoImpl();
	
	private StockDao stockDao = new StockDaoImpl();
	
	private StatusHistoryDao statusHistoryDao = new StatusHistoryDaoImpl();
	private SerialNumberService serialNumberService;
	
	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	public StockTransferServiceImplZbus() {
		config();
		super.setStockTransferDao(stockTransferDao);
		super.setStockTransferListDao(stockTransferListDao);
		
		super.setDeliveryNoteDao(deliveryNoteDao);
		super.setDeliveryNoteAsnDao(deliveryNoteAsnDao);
		super.setDeliveryPackingListDao(deliveryPackingListDao);
		
		super.setReceivingNoteDao(receivingNoteDao);
		super.setReceivingNoteAsnDao(receivingNoteAsnDao);
		super.setReceivingPackingListDao(receivingPackingListDao);
		
		super.setStockDao(stockDao);
		
		super.setStatusHistoryDao(statusHistoryDao);
		super.setSerialNumberService(serialNumberService);
	}
	
	/**
	 * 保存移库单表头和表体
	 */
	@Override
	public ResponseMessage saveStockTransFerAndList(StockTransfer stockTransfer,
			List<StockTransferList> stockTransferLists) {
		Connection conn =null;
		try {
			if (stockTransfer==null || stockTransferLists.size()==0) {
				return new ResponseMessage(false, "10012");
			}
			conn=MemoryFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			stockTransferDao.setConnection(conn);
			stockTransferListDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveStockTransFerAndList(stockTransfer, stockTransferLists);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			
			conn.commit();
			stockTransferDao.setConnection(null);
			stockTransferListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
				}
			}
		}
	}
	
	/**
	 * 根据移库单号删除移库单表头和表体
	 */
	@Override
	public ResponseMessage removeStockTransferAndListByTransferNo(String transferNo) {
		Connection conn =null;
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			conn=MemoryFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			stockTransferDao.setConnection(conn);
			stockTransferListDao.setConnection(conn);
			ResponseMessage responseMessage = super.removeStockTransferAndListByTransferNo(transferNo);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			
			conn.commit();
			stockTransferDao.setConnection(null);
			stockTransferListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
				}
			}
		}
	}
	
	/**
	 *  撤销移库操作
	 */
	@Override
	public ResponseMessage revokeStockTransferByTransferNo(String transferNo) {
		Connection conn =null;
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			conn=MemoryFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			stockTransferDao.setConnection(conn);
			stockTransferListDao.setConnection(conn);
			
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			
			stockDao.setConnection(conn);
			ResponseMessage responseMessage = super.revokeStockTransferByTransferNo(transferNo);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			
			conn.commit();
			stockTransferDao.setConnection(null);
			stockTransferListDao.setConnection(null);

			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
			
			stockDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息:%s", e));
				}
			}
		}
	}
	
	/**
	 * 移库操作
	 */
	@Override
	public ResponseMessage stockTransfer(List<StockTransferList> stockTransferLists, String createName) {
		Connection conn = null;
		try {
			if (stockTransferLists == null || stockTransferLists.isEmpty()) {
				return new ResponseMessage(false, "10004","参数1[stockTransferLists]");
			}
			if (isBlank(createName)) {
				return new ResponseMessage(false, "10012", "参数2[createName]");
			}
			conn=MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			statusHistoryDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			stockTransferDao.setConnection(conn);
			stockTransferListDao.setConnection(conn);
			
			ResponseMessage responseMessage = super.stockTransfer(stockTransferLists,createName);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			
			conn.commit();
			stockDao.setConnection(null);
			statusHistoryDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
			stockTransferDao.setConnection(null);
			stockTransferListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null!=conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.debug("回滚失败：{}", e1);
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
				}
			}
		}
	}
	
}
