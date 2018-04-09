package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.PurchaseOrderDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.api.StatusHistoryDao;
import com.yorma.wms.dao.memory.impl.PurchaseOrderDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingPackingListDaoImpl;
import com.yorma.wms.dao.memory.impl.StatusHistoryDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.ReceivingNoteAsn;

public class ReceivingNoteServiceImplZbus extends ReceivingNoteServiceImpl {

	private StatusHistoryDao statusHistoryDao= new StatusHistoryDaoImpl();
	private PurchaseOrderDao orderDao = new PurchaseOrderDaoImpl();
	private ReceivingNoteDao receivingNoteDao = new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao = new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao receivingPackingListDao = new ReceivingPackingListDaoImpl();
	

	private static SerialNumberService serialNumberService;

	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}

	public ReceivingNoteServiceImplZbus() {
		config();
		super.setSerialNumberService(serialNumberService);
		this.setReceivingNoteDao(receivingNoteDao);
		this.setPurchaseOrderDao(orderDao);
		this.setReceivingNoteAsnDao(receivingNoteAsnDao);
		this.setReceivingPackingListDao(receivingPackingListDao);
		this.setStatusHistoryDao(statusHistoryDao);
	}

	/**
	 * 修改状态
	 */
	@Override
	public ResponseMessage updateStatus(String receivingNoteNo, int status) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			ResponseMessage responseMessage = super.updateStatus(receivingNoteNo,status);
			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
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
	 * 根据收货单号删除入库单、明细（事务）
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public ResponseMessage delete(String receivingNoteNo) {

		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			orderDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.delete(receivingNoteNo);
			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
			orderDao.setConnection(null);
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
	 * 保存入库单及明细(入库订单生成)
	 */
	@Override
	public ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,
			List<ReceivingNoteAsn> receivingNoteAsns,String po) {

		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			orderDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			statusHistoryDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveReceivingNoteAndAsn(receivingNote,receivingNoteAsns,po);
			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
			orderDao.setConnection(null);
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
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
	 * 保存入库单及明细
	 */
	@Override
	public ResponseMessage saveReceivingNoteAndAsn(ReceivingNote receivingNote,
			List<ReceivingNoteAsn> receivingNoteAsns) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveReceivingNoteAndAsn(receivingNote,receivingNoteAsns);
			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
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
