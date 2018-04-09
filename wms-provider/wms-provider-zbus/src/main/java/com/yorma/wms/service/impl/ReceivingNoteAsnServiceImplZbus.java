package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingPackingListDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.ReceivingNoteAsn;

public class ReceivingNoteAsnServiceImplZbus extends ReceivingNoteAsnServiceImpl {

	private ReceivingNoteDao receivingNoteDao=new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao = new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao receivingPackingListDao = new ReceivingPackingListDaoImpl();

	public ReceivingNoteAsnServiceImplZbus() {
		this.setReceivingNoteDao(receivingNoteDao);
		this.setReceivingNoteAsnDao(receivingNoteAsnDao);
		this.setReceivingPackingListDao(receivingPackingListDao);
	}

	/**
	 * 根据明细UUID删除明细，并添加多条明细
	 */
	@Override
	public ResponseMessage batchByAsn(String asnUuid, List<ReceivingNoteAsn> asnList) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			receivingNoteAsnDao.setConnection(conn);
			
			ResponseMessage responseMessage = super.batchByAsn(asnUuid, asnList);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			receivingNoteAsnDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (Exception e1) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e1));
				}
			}
		}
	}
	
	/**
	 * 删除明细和入库货物信息
	 */
	public ResponseMessage delete(String uuid) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.delete(uuid);

			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
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
	 * 根据合并批次号修改明细预告到货状态并修改收货单状态
	 */
	@Override
	public ResponseMessage updateIsArrivedByMergeLotNo(Integer status, List<String> mergeLotNos, String receivingNoteNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			
			ResponseMessage responseMessage = super.updateIsArrivedByMergeLotNo(status,mergeLotNos,receivingNoteNo);

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
	 * 根据合并批次号撤销包装分配
	 */
	@Override
	public ResponseMessage revokePacking(List<String> mergeLotNos) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.revokePacking(mergeLotNos);

			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
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
	 * 根据合并批次号撤销到货
	 */
	@Override
	public ResponseMessage revokeArrived(List<String> mergeLotNos) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.revokeArrived(mergeLotNos);

			// 根据实际情况来写条件
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			// 提交事务
			conn.commit();
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
	
}
