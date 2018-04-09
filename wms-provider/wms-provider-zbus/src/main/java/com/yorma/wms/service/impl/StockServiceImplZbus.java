package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.yorma.wms.entity.Stock;
import com.yorma.wms.entity.dto.StockDto;

public class StockServiceImplZbus extends StockServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(StockServiceImplZbus.class);
	
	private StockDao stockDao = new StockDaoImpl();
	private DeliveryNoteAsnDao deliveryNoteAsnDao = new DeliveryNoteAsnDaoImpl();
	private DeliveryPackingListDao deliveryPackingListDao = new DeliveryPackingListDaoImpl();
	private DeliveryNoteDao deliveryNoteDao = new DeliveryNoteDaoImpl();
	private ReceivingNoteDao receivingNoteDao = new ReceivingNoteDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao=new ReceivingNoteAsnDaoImpl();
	private ReceivingPackingListDao	receivingPackingListDao=new ReceivingPackingListDaoImpl();
	private StockTransferDao stockTransferDao = new StockTransferDaoImpl();
	private StockTransferListDao stockTransferListDao = new StockTransferListDaoImpl();
	private StatusHistoryDao statusHistoryDao = new StatusHistoryDaoImpl();
	private static SerialNumberService serialNumberService;
	
	private void config() {
		serialNumberService = ClientBootstrapUtils.getClientBootstrap().createProxy(SerialNumberService.class);
	}
	
	public StockServiceImplZbus() {
		config();
		super.setSerialNumberService(serialNumberService);
		super.setStockDao(stockDao);
		super.setReceivingNoteDao(receivingNoteDao);
		super.setReceivingNoteAsnDao(receivingNoteAsnDao);
		super.setReceivingPackingListDao(receivingPackingListDao);
		super.setDeliveryNoteDao(deliveryNoteDao);
		super.setDeliveryNoteAsnDao(deliveryNoteAsnDao);
		super.setDeliveryPackingListDao(deliveryPackingListDao);
		super.setStockTransferDao(stockTransferDao);
		super.setStockTransferListDao(stockTransferListDao);
		super.setStatusHistoryDao(statusHistoryDao);
	}


	/**
	 * 修改预分配量（拣货分配）
	 */
	@Override
	public ResponseMessage updatePreAllocationStock(List<StockDto> stockdtoList, List<String> asnUuids,
			boolean isMerged) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.updatePreAllocationStock(stockdtoList, asnUuids,isMerged);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
				return responseMessage;
			}
			conn.commit();
			stockDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
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
	 * 根据合并明细撤销拣货分配
	 */
	@Override
	public ResponseMessage revokePreAllocationStock(List<String> mergeLotNos) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.revokePreAllocationStock(mergeLotNos);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
				return responseMessage;
			}
			conn.commit();
			stockDao.setConnection(null);
			deliveryNoteDao.setConnection(null);
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
	 * 修改已分配量(分批)
	 */
	@Override
	public ResponseMessage updateAllocatedStock(List<String> deliveryPackingListUuids) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.updateAllocatedStock(deliveryPackingListUuids);
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			
			conn.commit();
			stockDao.setConnection(null);
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
	 * 修改在库量（分批出库）
	 */
	@Override
	public ResponseMessage updateInstock(List<String> deliveryPackingListUuids,String deliveryLotNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.updateInstock(deliveryPackingListUuids,deliveryLotNo);
			if (!responseMessage.getSuccess()) {
				if (null != conn)
					conn.rollback();
			}
			conn.commit();
			stockDao.setConnection(null);
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
	 * 批量添加库存并同时修改收货单状态
	 */
	@Override
	public ResponseMessage addBatchByStockAndUpdateStatus(List<Stock> stockList, String receivingNoteNo) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			ResponseMessage responseMessage = super.addBatchByStockAndUpdateStatus(stockList, receivingNoteNo);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			stockDao.setConnection(null);
			receivingNoteDao.setConnection(null);
			receivingNoteAsnDao.setConnection(null);
			receivingPackingListDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.debug("回滚失败：{}", e1);
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
	 * 批量添加库存
	 */
	@Override
	public ResponseMessage createBatchByStockAndReceiving(List<Stock> params) {
		ResponseMessage responseMessage = new ResponseMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("StockServiceImplZbus进入[createBatchByStockAndReceiving]方法时间"+new Date());
			}
			List<Stock> stocks=new ArrayList<>();
			List<Stock> returnStock = new ArrayList<>();
			int choice = 0;
			int rows=0;
			int saverows=params.size()%1000==0 ? params.size()/1000 : params.size()/1000+1;
			if (logger.isDebugEnabled()) {
				logger.debug("~~~~~~~~~~进入for循环：~~~~~~~~~~:"+params.size());
			}
			for (Stock stock : params) {
				if (logger.isDebugEnabled()) {
					logger.debug("~~~~~~~~~~第"+choice+"次循环：~~~~~~~~~~:"+params.size());
				}
				choice++;
				if (choice<1000) {
					stocks.add(stock);
				}
				if (rows<saverows) {
					if (choice == 1000) {
						try {
							responseMessage = super.createBatchByStockAndReceiving(stocks);
							rows++;
							if (!responseMessage.getSuccess()) {
								returnStock.addAll(stocks);
							}
							
							stocks=new ArrayList<>();
							choice=0;
						} catch (Exception e) {
							returnStock.addAll(stocks);
						}
					}else  {
						if(rows*1000+choice==params.size()){
							try {
								responseMessage = super.createBatchByStockAndReceiving(stocks);
								rows++;
								if (!responseMessage.getSuccess()) {
									returnStock.addAll(stocks);
								}
								
								stocks=new ArrayList<>();
								choice=0;
							} catch (Exception e) {
								returnStock.addAll(stocks);
							}
						}
					}
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("StockServiceImplZbus结束[createBatchByStockAndReceiving]方法时间"+new Date());
			}
			if (returnStock.isEmpty()) {
				responseMessage.setSuccess(true);
				responseMessage.setData(null);
			}else {
				responseMessage.setSuccess(true);
				responseMessage.setData(returnStock);
			}
			return responseMessage;
	}
	

	/**
	 * 移库操作
	 */
	@Override
	public ResponseMessage stockTransfer(String transferNo) {
		Connection conn = null;
		try {
			if (isBlank(transferNo)) {
				return new ResponseMessage(false, "10012");
			}
			conn=MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			stockDao.setConnection(conn);
			deliveryNoteDao.setConnection(conn);
			deliveryNoteAsnDao.setConnection(conn);
			deliveryPackingListDao.setConnection(conn);
			receivingNoteDao.setConnection(conn);
			receivingNoteAsnDao.setConnection(conn);
			receivingPackingListDao.setConnection(conn);
			stockTransferDao.setConnection(conn);
			stockTransferListDao.setConnection(conn);
			
			ResponseMessage responseMessage = super.stockTransfer(transferNo);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			
			conn.commit();
			stockDao.setConnection(null);
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
