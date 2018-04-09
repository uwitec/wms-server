package com.yorma.wms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.yorma.common.entity.dto.ResponseMessage;
import com.yorma.wms.dao.api.PackInfomationDao;
import com.yorma.wms.dao.memory.impl.PackInfomationDaoImpl;
import com.yorma.wms.dao.memory.util.MemoryFactory;
import com.yorma.wms.entity.PackInfomation;

public class PackInfomationServiceImplZbus extends PackInfomationServiceImpl {

	private PackInfomationDao packInfomationDao=new PackInfomationDaoImpl();
	
	public PackInfomationServiceImplZbus() {
		this.setPackInfomationDao(packInfomationDao);
	}
	
	/**
	 * 保存包装
	 */
	@Override
	public ResponseMessage save(PackInfomation entity) {
		Connection conn=null;
		try {
			conn=MemoryFactory.getInstance().getConnection();
			if (null == conn) {
				return new ResponseMessage(false, "60005");
			}
			conn.setAutoCommit(false);
			packInfomationDao.setConnection(conn);
			ResponseMessage responseMessage=super.save(entity);
			if (!responseMessage.getSuccess()) {
				conn.rollback();
			}
			conn.commit();
			packInfomationDao.setConnection(null);
			return responseMessage;
		} catch (Exception e) {
			if (null!=conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
		}finally {
			if (null !=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					return new ResponseMessage(false, "10003", String.format("异常信息：%s", e));
				}
			}
		}
	}
	
}
