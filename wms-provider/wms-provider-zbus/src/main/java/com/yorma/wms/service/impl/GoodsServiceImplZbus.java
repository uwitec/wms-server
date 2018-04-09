package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.api.PackInfomationDao;
import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.api.StockDao;
import com.yorma.wms.dao.memory.impl.GoodsDaoImpl;
import com.yorma.wms.dao.memory.impl.PackInfomationDaoImpl;
import com.yorma.wms.dao.memory.impl.ReceivingNoteAsnDaoImpl;
import com.yorma.wms.dao.memory.impl.StockDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.service.impl.GoodsServiceImpl;

public class GoodsServiceImplZbus extends GoodsServiceImpl {

	private StockDao stockDao = new StockDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private PackInfomationDao packInfomationDao=new PackInfomationDaoImpl();
	private ReceivingNoteAsnDao receivingNoteAsnDao = new ReceivingNoteAsnDaoImpl();

	public GoodsServiceImplZbus() {
		this.setStockDao(stockDao);
		this.setGoodsDao(goodsDao);
		this.setPackInfomationDao(packInfomationDao);
		this.setReceivingNoteAsnDao(receivingNoteAsnDao);
	}

	/**
	 * 批量添加商品
	 */
	@Override
	public ResponseMessage createBatchByGoods(List<Goods> goodss) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			goodsDao.setConnection(conn);
			ResponseMessage responseMessage = super.createBatchByGoods(goodss);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			goodsDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
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
	 * 删除商品同时删除该商品的包装信息
	 */
	@Override
	public ResponseMessage removeById(Long id) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			if (conn == null) {
				return new ResponseMessage(false, "60005"); // 状态问题
			}
			conn.setAutoCommit(false);
			goodsDao.setConnection(conn);
			packInfomationDao.setConnection(conn);
			ResponseMessage responseMessage = super.removeById(id);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			goodsDao.setConnection(null);
			packInfomationDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
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
	 * 保存商品信息及包装键
	 */
	@Override
	public ResponseMessage saveGoodsAndPackInfomation(Goods goods, List<PackInfomation> packInfomations) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			goodsDao.setConnection(conn);
			packInfomationDao.setConnection(conn);
			ResponseMessage responseMessage = super.saveGoodsAndPackInfomation(goods,packInfomations);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			goodsDao.setConnection(null);
			packInfomationDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
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
	 * 批量添加商品及包装信息
	 */
	@Override
	public ResponseMessage createBatchByGoodsAndPackInfomation(List<Goods> goodsList,
			List<PackInfomation> packInfomations) {
		Connection conn = null;
		try {
			conn = MemoryFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			goodsDao.setConnection(conn);
			packInfomationDao.setConnection(conn);
			ResponseMessage responseMessage = super.createBatchByGoodsAndPackInfomation(goodsList,packInfomations);
			if (!responseMessage.getSuccess()) {
				if (null != conn) {
					conn.rollback();
				}
			}
			conn.commit();
			goodsDao.setConnection(null);
			packInfomationDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (Exception e2) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e2));
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
