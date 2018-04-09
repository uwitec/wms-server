package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.PurchaseDetailsDao;
import com.yorma.wms.dao.api.PurchaseOrderDao;
import com.yorma.wms.dao.memory.impl.GoodsDaoImpl;
import com.yorma.wms.dao.memory.impl.PurchaseDetailsDaoImpl;
import com.yorma.wms.dao.memory.impl.PurchaseOrderDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.PurchaseDetails;
import com.yorma.wms.entity.PurchaseOrder;

public class PurchaseOrderServiceImplZbus extends PurchaseOrderServiceImpl {

	private PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDaoImpl();
	private PurchaseDetailsDao purchaseDetailsDao = new PurchaseDetailsDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private SerialNumberService serialNumberService;

	private void config() {
		serialNumberService=ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	
	public PurchaseOrderServiceImplZbus() {
		config();
		this.setPurchaseOrderDao(purchaseOrderDao);
		this.setPurchaseDetailsDao(purchaseDetailsDao);
		this.setGoodsDao(goodsDao);
		super.setSerialNumberService(serialNumberService);
	}
	
	@Override
	public ResponseMessage removePurchaseOrderByOrderPo(String po) {
		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			purchaseOrderDao.setConnection(conn);
			purchaseDetailsDao.setConnection(conn);
			ResponseMessage responseMessage=super.removePurchaseOrderByOrderPo(po);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			purchaseOrderDao.setConnection(null);
			purchaseDetailsDao.setConnection(null);
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
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
				}
			}
		}
	}
	
	/**
	 * 保存入库订单及明细
	 */
	@Override
	public ResponseMessage savePurchaseOrderAndDetails(PurchaseOrder purchaseOrder,
			List<PurchaseDetails> purchaseDetailss) {
		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			purchaseOrderDao.setConnection(conn);
			purchaseDetailsDao.setConnection(conn);
			ResponseMessage responseMessage=super.savePurchaseOrderAndDetails(purchaseOrder,purchaseDetailss);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			purchaseOrderDao.setConnection(null);
			purchaseDetailsDao.setConnection(null);
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
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
				}
			}
		}
	}
	
}
