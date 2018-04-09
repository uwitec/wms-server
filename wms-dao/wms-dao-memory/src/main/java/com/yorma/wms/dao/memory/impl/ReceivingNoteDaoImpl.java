package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.ReceivingNoteDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.ReceivingNote;
import com.yorma.wms.entity.dto.ReceivingNoteDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:收货单Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月25日
 * @version 1.00.00
 * @history:
 *
 */
public class ReceivingNoteDaoImpl extends BaseDaoImpl<ReceivingNote>implements ReceivingNoteDao {

	/**
	 * 根据单号查询状态
	 */
	@Override
	public Integer getStatus(String receivingNoteNo) {
		String sql = "select l.CODE from receiving_note n,status_list l where n.STATUS=l.CODE and l.TYPE=0 and n.RECEIVING_NOTE_NO=?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), receivingNoteNo);
	}

	/**
	 * 根据单号修改状态
	 */
	@Override
	public int updateStatus(String receivingNoteNo, int status) {
		String sql = "update receiving_note set STATUS = ? where RECEIVING_NOTE_NO=?";
		if (null != connection) {
			return memory.update(connection,sql, new Object[] { status, receivingNoteNo });
		}
		return memory.update(sql, new Object[] { status, receivingNoteNo });
	}
	

	/**
	 * 更新状态并添加 最后修改时间、和最后修改人
	 * @param receivingNoteNo
	 * @param status
	 * @param lastDate
	 * @param lastName
	 * @return
	 */
	@Override
	public int updateStatus(String receivingNoteNo, int status, Date lastDate, String lastName) {
		String sql = "update receiving_note set STATUS = ?,LAST_UPDATE_DATE=?,LAST_UPDATE_BY=? where RECEIVING_NOTE_NO=?";
		if (null != connection) {
			return memory.update(connection,sql, new Object[] { status,lastDate,lastName, receivingNoteNo });
		}
		return memory.update(sql, new Object[] { status,lastDate,lastName, receivingNoteNo });
	}

	/**
	 * 收货单查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	public List<ReceivingNoteDto> getReceivingNote(Map<String, Object> map,Map<String, Object> map1) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		String sql1 = "";
		sql.append("SELECT(SELECT DEPART_NAME FROM sys_depart WHERE r.OWNER_CODE = DEPART_CODE ) AS departName,(select DEPART_NAME from sys_depart where r.SHIPPING_CODE=DEPART_CODE) AS shippingName,(select DEPART_NAME from sys_depart where r.PAYMENT_CODE=DEPART_CODE) AS paymentName,r.*, h.CREATE_TIME FROM receiving_note r LEFT JOIN sys_depart d ON r.OWNER_CODE = d.DEPART_CODE LEFT JOIN ( SELECT * FROM status_history e WHERE e.status_id = 50 AND e.type = '0' ) h ON r.RECEIVING_NOTE_NO = h.APPLY_ID LEFT JOIN ( SELECT * FROM receiving_note_asn WHERE 1 = 1 ");
		
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
				sql1 += " and " + key + " ? ";
				params.add(map.get(key));
			}
		}
		sql1+="GROUP BY RECEIVING_NOTE_NO ) a ON r.RECEIVING_NOTE_NO = a.RECEIVING_NOTE_NO WHERE 1 = 1";
		sql.append(sql1);
		if (map1.size() != 0) {
			String sql2="";
			for (String key : map1.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map1.get(key) != null && !"".equals(map1.get(key)) && !"%%".equals(map1.get(key)) && !"% %".equals(map1.get(key))) {
					sql2 += " and " + key + " ? ";
					params.add(map1.get(key));
				}
			}
			sql.append(sql2);
		}
		sql.append(" ORDER BY r.RECEIVING_NOTE_NO DESC, r.CREATE_DATE DESC ");
		return memory.query(sql, new BeanListHandler<ReceivingNoteDto>(ReceivingNoteDto.class), params);
	}
	
	/**
	 * 根据条件查询收货单信息
	 * @param map	条件一
	 * @param map1	条件二
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	 将查询（所有/符合条件）的数据放入的list集合
	 */
	@Override
	public List<ReceivingNoteDto> getReceivingNote(Map<String, Object> map, Map<String, Object> map1,
			Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		String sql1 = "";
		sql.append("SELECT(SELECT DEPART_NAME FROM sys_depart WHERE r.OWNER_CODE = DEPART_CODE ) AS departName,(select DEPART_NAME from sys_depart where r.SHIPPING_CODE=DEPART_CODE) AS shippingName,(select DEPART_NAME from sys_depart where r.PAYMENT_CODE=DEPART_CODE) AS paymentName,r.*, h.CREATE_TIME FROM receiving_note r LEFT JOIN sys_depart d ON r.OWNER_CODE = d.DEPART_CODE LEFT JOIN ( SELECT * FROM status_history e WHERE e.status_id = 50 AND e.type = '0' ) h ON r.RECEIVING_NOTE_NO = h.APPLY_ID LEFT JOIN ( SELECT * FROM receiving_note_asn WHERE 1 = 1 ");
		
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
				sql1 += " and " + key + " ? ";
				params.add(map.get(key));
			}
		}
		sql1+="GROUP BY RECEIVING_NOTE_NO ) a ON r.RECEIVING_NOTE_NO = a.RECEIVING_NOTE_NO WHERE 1 = 1";
		sql.append(sql1);
		if (map1.size() != 0) {
			String sql2="";
			for (String key : map1.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map1.get(key) != null && !"".equals(map1.get(key)) && !"%%".equals(map1.get(key)) && !"% %".equals(map1.get(key))) {
					sql2 += " and " + key + " ? ";
					params.add(map1.get(key));
				}
			}
			sql.append(sql2);
		}
		sql.append(" ORDER BY r.RECEIVING_NOTE_NO DESC, r.CREATE_DATE DESC ");
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,? ");
			params.add((pageNumber-1)*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql.toString(), new BeanListHandler<ReceivingNoteDto>(ReceivingNoteDto.class), params.toArray());
	}
	
	
	@Override
	public int getCounts(Map<String, Object> map, Map<String, Object> map1) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		String sql1 = "";
		sql.append("SELECT COUNT(*) FROM (SELECT(SELECT DEPART_NAME FROM sys_depart WHERE r.OWNER_CODE = DEPART_CODE ) AS departName,(select DEPART_NAME from sys_depart where r.SHIPPING_CODE=DEPART_CODE) AS shippingName,(select DEPART_NAME from sys_depart where r.PAYMENT_CODE=DEPART_CODE) AS paymentName,r.*, h.CREATE_TIME FROM receiving_note r LEFT JOIN sys_depart d ON r.OWNER_CODE = d.DEPART_CODE LEFT JOIN ( SELECT * FROM status_history e WHERE e.status_id = 50 AND e.type = '0' ) h ON r.RECEIVING_NOTE_NO = h.APPLY_ID LEFT JOIN ( SELECT * FROM receiving_note_asn WHERE 1 = 1 ");
		
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
				sql1 += " and " + key + " ? ";
				params.add(map.get(key));
			}
		}
		sql1+="GROUP BY RECEIVING_NOTE_NO ) a ON r.RECEIVING_NOTE_NO = a.RECEIVING_NOTE_NO WHERE 1 = 1";
		sql.append(sql1);
		if (map1.size() != 0) {
			String sql2="";
			for (String key : map1.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map1.get(key) != null && !"".equals(map1.get(key)) && !"%%".equals(map1.get(key)) && !"% %".equals(map1.get(key))) {
					sql2 += " and " + key + " ? ";
					params.add(map1.get(key));
				}
			}
			sql.append(sql2);
		}
		sql.append(" ORDER BY r.RECEIVING_NOTE_NO DESC, r.CREATE_DATE DESC  ) receivingNote ");
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据收货单号删除数据
	 */
	@Override
	public int delete(String receivingNoteNo) {
		String sql="delete from receiving_note where RECEIVING_NOTE_NO=?";
		if (null != connection){
			return memory.update(connection, sql, receivingNoteNo);
		}
		return memory.update(sql, new Object[]{receivingNoteNo});
	}
	
	/**
	 * 根据收货单号查询入库表头信息
	 * @param receivingNoteNo	收货单号
	 * @return	入库表头信息实体
	 */
	@Override
	public ReceivingNote getReceivingNoteByReceivingNoteNo(String receivingNoteNo) {
		String sql="select * from receiving_note where RECEIVING_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<ReceivingNote>(ReceivingNote.class), receivingNoteNo);
		}
		return memory.query(sql, new BeanHandler<ReceivingNote>(ReceivingNote.class), receivingNoteNo);
	}
}
