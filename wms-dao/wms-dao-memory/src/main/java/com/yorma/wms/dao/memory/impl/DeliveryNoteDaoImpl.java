package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.DeliveryNoteDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.DeliveryNote;
import com.yorma.wms.entity.dto.DeliveryNoteDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:出货单Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月9日
 * @version 1.00.00
 * @history:
 *
 */
public class DeliveryNoteDaoImpl extends BaseDaoImpl<DeliveryNote>implements DeliveryNoteDao {

	/**
	 * 出货单查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	public List<DeliveryNoteDto> getDeliveryNoteAndDepart(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		//当前页
		Integer pageNumber=null;
		//每页显示条数
		Integer pageSize=null;

		sql.append("SELECT deliveryNote.*, (select DEPART_NAME from  sys_depart where deliveryNote.OWNER_CODE=DEPART_CODE) as departName, (select DEPART_NAME from  sys_depart where deliveryNote.CONSIGNEE_CODE=DEPART_CODE) as consigne , (select DEPART_NAME from  sys_depart where deliveryNote.PAYMENT_CODE=DEPART_CODE) as payment FROM delivery_note deliveryNote LEFT JOIN delivery_note_asn deliveryNoteAsn ON deliveryNote.DELIVERY_NOTE_NO = deliveryNoteAsn.DELIVERY_NOTE_NO WHERE 1 = 1 ");
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				if ("pageSize".equals(key)) {
					pageSize=(Integer) map.get(key);
					map.put(key, null);
				}
				if ("pageNumber".equals(key)) {
					pageNumber=(Integer) map.get(key);
					map.put(key, null);
				}
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {

					if (key.equals("statusHistory.OPERATE_TIME<=") || key.equals("statusHistoryOPERATE_TIME>=")) {
						sql1 += " and statusHistory.STATUS_ID=50 and " + key + " ? ";
					}else {
						sql1 += " and " + key + " ? ";
					}
					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" GROUP BY deliveryNote.DELIVERY_NOTE_NO ORDER BY deliveryNote.CREATE_DATE DESC");
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,?");
			params.add(pageNumber*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql, new BeanListHandler<DeliveryNoteDto>(DeliveryNoteDto.class), params);
	}

	/**
	 * 根据条件查询出货单 (分页)
	 * @param map	查询条件
	 * @param pageNumber	当前页
	 * @param pageSize		每页显示数
	 * @return	将查询（所有/符合条件）的数据放入的list集合
	 */
	@Override
	public List<DeliveryNoteDto> getDeliveryNoteAndDepart(Map<String, Object> map, Integer pageNumber,
			Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("SELECT deliveryNote.*, (select DEPART_NAME from  sys_depart where deliveryNote.OWNER_CODE=DEPART_CODE) as departName, (select DEPART_NAME from  sys_depart where deliveryNote.CONSIGNEE_CODE=DEPART_CODE) as consigne , (select DEPART_NAME from  sys_depart where deliveryNote.PAYMENT_CODE=DEPART_CODE) as payment FROM delivery_note deliveryNote LEFT JOIN delivery_note_asn deliveryNoteAsn ON deliveryNote.DELIVERY_NOTE_NO = deliveryNoteAsn.DELIVERY_NOTE_NO WHERE 1 = 1 ");
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {

					if (key.equals("statusHistory.OPERATE_TIME<=") || key.equals("statusHistoryOPERATE_TIME>=")) {
						sql1 += " and statusHistory.STATUS_ID=50 and " + key + " ? ";
					}else {
						sql1 += " and " + key + " ? ";
					}
					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" GROUP BY deliveryNote.DELIVERY_NOTE_NO ORDER BY deliveryNote.CREATE_DATE DESC");
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,?");
			params.add((pageNumber-1)*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql, new BeanListHandler<DeliveryNoteDto>(DeliveryNoteDto.class), params);
	}
	
	
	@Override
	public int getCounts(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		
		sql.append("SELECT COUNT(*) FROM (SELECT deliveryNote.*, (select DEPART_NAME from  sys_depart where deliveryNote.OWNER_CODE=DEPART_CODE) as departName, (select DEPART_NAME from  sys_depart where deliveryNote.CONSIGNEE_CODE=DEPART_CODE) as consigne , (select DEPART_NAME from  sys_depart where deliveryNote.PAYMENT_CODE=DEPART_CODE) as payment FROM delivery_note deliveryNote LEFT JOIN delivery_note_asn deliveryNoteAsn ON deliveryNote.DELIVERY_NOTE_NO = deliveryNoteAsn.DELIVERY_NOTE_NO WHERE 1 = 1 ");
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {

					if (key.equals("statusHistory.OPERATE_TIME<=") || key.equals("statusHistoryOPERATE_TIME>=")) {
						sql1 += " and statusHistory.STATUS_ID=50 and " + key + " ? ";
					}else {
						sql1 += " and " + key + " ? ";
					}
					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" GROUP BY deliveryNote.DELIVERY_NOTE_NO ORDER BY deliveryNote.CREATE_DATE DESC ) deliveryNote");
		
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据出货单号查询状态码
	 */
	@Override
	public int getStatus(String deliveryNoteNo) {
		String sql = "select l.code from delivery_note n,status_list l where n.STATUS = l.code and l.type=1 and n.delivery_note_no=?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), deliveryNoteNo);
	}

	/**
	 * 根据出货单号修改状态码
	 */
	@Override
	public int updateStatus(String deliveryNoteNo, int status) {
		String sql = "update delivery_note set status=? where delivery_note_no=?";
		if (null !=connection) {
			return memory.update(connection,sql, new Object[] { status, deliveryNoteNo });
		}
		return memory.update(sql, new Object[] { status, deliveryNoteNo });
	}

	/**
	 * 根据出货单号修改状态码
	 * @param deliveryNoteNo 出货单号
	 * @param status  状态码
	 * @param lastName 修改人
	 * @param laseDate 修改时间
	 * @return
	 */
	@Override
	public int updateStatus(String deliveryNoteNo, int status, String lastName, Date laseDate) {
		String sql = "update delivery_note set status=?,LAST_UPDATE_BY=?,LAST_UPDATE_DATE=? where delivery_note_no=?";
		if (null !=connection) {
			return memory.update(connection,sql, new Object[] { status, lastName,laseDate, deliveryNoteNo });
		}
		return memory.update(sql, new Object[] { status, lastName,laseDate,  deliveryNoteNo });
	}
	
	/**
	 * 根据出货单号删除
	 */
	@Override
	public int deleteDeliveryNoteByNoteNo(String deliveryNoteNo) {
		String sql = "delete from delivery_note where delivery_note_no=?";
		if (null != connection) {
			return memory.update(connection, sql, deliveryNoteNo);
		}
		return memory.update(sql, new Object[] { deliveryNoteNo });
	}
	
	/**
	 * 根据出货单号查询出货单数据
	 */
	@Override
	public DeliveryNote getDeliveryNoteByDeliveryNoteNo(String deliveryNoteNo) {
		String sql="select * from delivery_note where DELIVERY_NOTE_NO=?";
		return memory.query(sql, new BeanHandler<DeliveryNote>(DeliveryNote.class), deliveryNoteNo);
	}

}
