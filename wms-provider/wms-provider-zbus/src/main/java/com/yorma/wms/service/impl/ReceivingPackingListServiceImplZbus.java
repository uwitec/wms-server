package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingPackingListDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.ReceivingPackingList;

public class ReceivingPackingListServiceImplZbus extends ReceivingPackingListServiceImpl {

	private ReceivingNoteDao receivingNoteDao=new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao=new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao receivingPackingListDao=new ReceivingPackingListDaoImpl();
	
	private static SerialNumberService serialNumberService;

	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	
	public ReceivingPackingListServiceImplZbus() {
		config();
		super.setSerialNumberService(serialNumberService);
		this.setReceivingNoteDao(receivingNoteDao);
		this.setReceivingNoteAsnDao(receivingNoteAsnDao);
		this.setReceivingPackingListDao(receivingPackingListDao);
	}
	
	/**
	 * 批量添加入库明细并将收货单状态修改为已到货（状态码：30）
	 */
	@Override
	public ResponseMessage saveBatch(List<ReceivingPackingList> PLlist) {
		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.saveBatch(PLlist);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
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
	
	/**
	 * 新增入库货物信息同时修改入库明细信息、修改入库单状态为30（已到货）
	 */
	@Override
	public ResponseMessage oneSaveBatch(List<ReceivingPackingList> receivingPackingLists,
			List<ReceivingNoteAsn> receivingNoteAsns) {
		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.oneSaveBatch(receivingPackingLists,receivingNoteAsns);;
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
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
	
	/**
	 * 批量修改入库货物信息并生成集装条码
	 */
	@Override
	public ResponseMessage updateBatch(List<ReceivingPackingList> entitys) {
		Connection conn =null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.updateBatch(entitys);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			receivingPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
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
	
	/**
	 * 添加多条明细和入库货物信息并删除单条明细（单条包装分配）
	 */
	@Override
	public ResponseMessage saveReceivingPackingListAndAsn(List<ReceivingNoteAsn> receivingNoteAsns, String asnUuid,
			String warehouse, String createBy) {
		Connection conn =null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.saveReceivingPackingListAndAsn(receivingNoteAsns,asnUuid,warehouse,createBy);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
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
	
	/**
	 * 添加多条明细和修改多条入库货物信息（合并包装分配）
	 */
	@Override
	public ResponseMessage saveReceivingPackingListAndAsns(List<String> asnUuids, ReceivingNoteAsn receivingNoteAsn,
			String warehouse, String createBy) {
		Connection conn =null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage=super.saveReceivingPackingListAndAsns(asnUuids,receivingNoteAsn,warehouse,createBy);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
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
