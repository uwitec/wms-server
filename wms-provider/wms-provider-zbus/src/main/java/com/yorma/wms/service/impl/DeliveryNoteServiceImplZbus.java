package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.api.SalesOrderDao;
import com.yorma.wms.dao.api.StatusHistoryDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.impl.DeliveryNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.DeliveryPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.SalesOrderDaoImpl;
import com.yorma.wms.dao.memory.impl.StatusHistoryDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.DeliveryNoteAsn;

public class DeliveryNoteServiceImplZbus extends DeliveryNoteServiceImpl {

	private DeliveryNoteDao deliveryNoteDao = new DeliveryNoteDaoImpl();
	private DeliveryNoteAsnDao deliveryNoteAsnDao = new DeliveryNoteAsnDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();
	private StatusHistoryDao statusHistoryDao = new StatusHistoryDaoImpl();
	private StockDao stockDao = new StockDaoImpl();
	private SalesOrderDao orderDao = new SalesOrderDaoImpl();

	private static SerialNumberService serialNumberService;

	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}

	public DeliveryNoteServiceImplZbus() {
		config();

		super.setSerialNumberService(serialNumberService);
		this.setStockDao(stockDao);
		this.setSalesOrderDao(orderDao);
		this.setDeliveryNoteDao(deliveryNoteDao);
		this.setStatusHistoryDao(statusHistoryDao);
		this.setDeliveryNoteAsnDao(deliveryNoteAsnDao);
		this.setDeliveryPackingListDao(deliveryPackingListDao);

	}

	/**
	 * 添加或修改出货单并添加历史状态
	 */
	@Override
	public ResponseMessage save(DeliveryNote dn) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			deliveryNoteDao.setConnection(conn);
			statusHistoryDao.setConnection(conn);
			ResponseMessage responseMessage = super.save(dn);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			deliveryNoteDao.setConnection(null);
			statusHistoryDao.setConnection(null);
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
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e1));
				}
			}
		}
	}

	/**
	 * 删除出货单（明细、入库信息并核减预分配或已分配数量）
	 * 
	 * @param deliveryNoteNo
	 *            删除条件：出货单号
	 */
	@Override
	public ResponseMessage delete(String deliveryNoteNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			orderDao.setConnection(conn);
			ResponseMessage responseMessage = super.delete(deliveryNoteNo);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			stockDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
			deliveryPackingListDao.setConnection(null);
			orderDao.setConnection(null);
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
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e1));
				}
			}
		}
	}

	/**
	 * 保存出库单及明细(出库订单生成)
	 */
	@Override
	public ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote, List<DeliveryNoteAsn> deliveryNoteAsns,String so) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			orderDao.setConnection(conn);
			statusHistoryDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveDeliveryNoteAndAsn(deliveryNote, deliveryNoteAsns,so);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			orderDao.setConnection(null);
			statusHistoryDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
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
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e1));
				}
			}
		}
	}

	/**
	 * 保存出库单表头及明细
	 */
	@Override
	public ResponseMessage saveDeliveryNoteAndAsn(DeliveryNote deliveryNote, List<DeliveryNoteAsn> deliveryNoteAsns) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveDeliveryNoteAndAsn(deliveryNote, deliveryNoteAsns);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			deliveryNoteDao.setConnection(null);
			deliveryNoteAsnDao.setConnection(null);
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
