package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.common.service.api.SerialNumberService;
import com.yorma.wms.config.ClientBootstrapUtils;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.SalesDetailsDao;
import com.yorma.wms.dao.api.SalesOrderDao;
import com.yorma.wms.dao.memory.impl.GoodsDaoImpl;
import com.yorma.wms.dao.memory.impl.SalesDetailsDaoImpl;
import com.yorma.wms.dao.memory.impl.SalesOrderDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.SalesDetails;
import com.yorma.wms.entity.SalesOrder;

public class SalesOrderServiceImplZbus extends SalesOrderServiceImpl {

	private SalesOrderDao salesOrderDao =  new SalesOrderDaoImpl();
	private SalesDetailsDao salesDetailsDao = new SalesDetailsDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private SerialNumberService serialNumberService;

	private void config() {
		serialNumberService=ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	
	public SalesOrderServiceImplZbus() {
		config();
		this.setSalesOrderDao(salesOrderDao);
		this.setSalesDetailsDao(salesDetailsDao);
		this.setSerialNumberService(serialNumberService);
		this.setGoodsDao(goodsDao);
	}
	
	/**
	 * 根据订单号删除入库采购订单信息及订单明细
	 * 
	 * @param 订单号
	 * @return ResponsMessage
	 * 
	 *         <pre>
	 * 				[success: false status:10012] 参数为null或为空字符串
	 * 				[success: false status:10003] 异常信息 
	 * 				[success: true 	data: 影响行数]  操作成功
	 *         </pre>
	 */
	@Override
	public ResponseMessage removeSalesOrderBySO(String so) {
		Connection conn = null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			salesOrderDao.setConnection(conn);
			salesDetailsDao.setConnection(conn);
			ResponseMessage responseMessage=super.removeSalesOrderBySO(so);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			salesOrderDao.setConnection(null);
			salesDetailsDao.setConnection(null);
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
	 * 保存出库订单表头和明细
	 */
	@Override
	public ResponseMessage saveSalesOrderAndDetails(SalesOrder salesOrder, List<SalesDetails> salesDetailss) {
		Connection conn = null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			salesOrderDao.setConnection(conn);
			salesDetailsDao.setConnection(conn);
			ResponseMessage responseMessage=super.saveSalesOrderAndDetails(salesOrder,salesDetailss);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			salesOrderDao.setConnection(null);
			salesDetailsDao.setConnection(null);
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
