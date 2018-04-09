package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.TransportNoteDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.TransportNote;
import com.yorma.wms.entity.dto.TransportNoteDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 运送单DaoImpl
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月03日
 * @version 1.00.00
 * @date 2017年11月03日
 * @version V1.0
 */
public class TransportNoteDaoImpl extends BaseDaoImpl<TransportNote> implements TransportNoteDao {

	@Override
	public List<TransportNoteDto> getTransportNoteDto(Map<String, Object> parameters) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select depart.DEPART_NAME,transportNote.* from transport_note transportNote LEFT JOIN sys_depart depart on transportNote.TRANSPORT_CODE=depart.DEPART_CODE LEFT JOIN transport_note_list transportNoteList ON transportNote.TRANSPORT_NOTE_NO=transportNoteList.TRANSPORT_NOTE_NO where 1=1 ");
		String sql1 = "";
		for (String key : parameters.keySet()) {
			if (parameters.get(key) != null && !"".equals(parameters.get(key)) && !"%%".equals(parameters.get(key))
					&& !"% %".equals(parameters.get(key))) {
				sql1 = " and " + key + " ? ";
				sql.append(sql1);
				params.add(parameters.get(key));
			}
		}
		sql.append(" GROUP BY transportNote.TRANSPORT_NOTE_NO ORDER BY CREATE_DATE desc  ");
		return memory.query(sql, new BeanListHandler<TransportNoteDto>(TransportNoteDto.class), params);
	}
	
	/**
	 * 根据条件查询运送单信息条数
	 * @param parameters 条件参数
	 * @return 运送单信息条数
	 */
	@Override
	public int getgetTransportNoteDtoCounts(Map<String, Object> parameters) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT COUNT(*) FROM (SELECT transportNote.* from transport_note transportNote LEFT JOIN sys_depart depart on transportNote.TRANSPORT_CODE=depart.DEPART_CODE LEFT JOIN transport_note_list transportNoteList ON transportNote.TRANSPORT_NOTE_NO=transportNoteList.TRANSPORT_NOTE_NO where 1=1 ");
		String sql1 = "";
		for (String key : parameters.keySet()) {
			if (parameters.get(key) != null && !"".equals(parameters.get(key)) && !"%%".equals(parameters.get(key))
					&& !"% %".equals(parameters.get(key))) {
				sql1 = " and " + key + " ? ";
				sql.append(sql1);
				params.add(parameters.get(key));
			}
		}
		sql.append(" GROUP BY transportNote.TRANSPORT_NOTE_NO ORDER BY CREATE_DATE desc) t");
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), params);
	}
	
	
	@Override
	public List<TransportNoteDto> getTransportNoteDto(Map<String, Object> parameters, Integer pageNumber,
			Integer pageSize) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select depart.DEPART_NAME,transportNote.* from transport_note transportNote LEFT JOIN sys_depart depart on transportNote.TRANSPORT_CODE=depart.DEPART_CODE LEFT JOIN transport_note_list transportNoteList ON transportNote.TRANSPORT_NOTE_NO=transportNoteList.TRANSPORT_NOTE_NO where 1=1 ");
		String sql1 = "";
		for (String key : parameters.keySet()) {
			if (parameters.get(key) != null && !"".equals(parameters.get(key)) && !"%%".equals(parameters.get(key))
					&& !"% %".equals(parameters.get(key))) {
				sql1 = " and " + key + " ? ";
				sql.append(sql1);
				params.add(parameters.get(key));
			}
		}
		sql.append(" GROUP BY transportNote.TRANSPORT_NOTE_NO ORDER BY CREATE_DATE desc  ");
		params.add((pageNumber-1)*pageSize);
		params.add(pageSize);
		sql.append(" limit ?,?");
		
		return memory.query(sql, new BeanListHandler<TransportNoteDto>(TransportNoteDto.class), params);
	}
	
	/**
	 * 根据运送单号删除运送单
	 */
	@Override
	public int removeByTransportNoteNo(String transportNoteNo) {
		String sql="delete from transport_note where transport_note_no=?";
		return memory.update(sql, transportNoteNo);
	}
	
	/**
	 * 根据运送单号查询运送单信息
	 */
	@Override
	public TransportNote getTransportNoteByTransportNoteNo(String transportNoteNo) {
		String sql="select * from transport_note where transport_note_no=?";
		return memory.query(sql, new BeanHandler<TransportNote>(TransportNote.class), transportNoteNo);
	}
	
	/**
	 * 根据运送单号修改状态
	 */
	@Override
	public int updateStatusByTransportNoteNo(String transportNoteNo, int status) {
		String sql="update transport_note set status=? where transport_note_no=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[]{status,transportNoteNo});
		}
		return memory.update(sql, new Object[]{status,transportNoteNo});
	}
	
	/**
	 * 根据运送单号查询运送单信息
	 */
	@Override
	public TransportNoteDto getTransportNoteDtoByTransportNoteNo(String transportNoteNo) {
		String sql="SELECT depart.DEPART_NAME, (select DEPART_NAME from sys_depart where CONSIGNEE_CODE=DEPART_CODE) as consigneeCompany, transportNote.* FROM transport_note transportNote LEFT JOIN sys_depart depart ON transportNote.TRANSPORT_CODE = depart.DEPART_CODE WHERE transportNote.TRANSPORT_NOTE_NO=?";
		return memory.query(sql, new BeanHandler<TransportNoteDto>(TransportNoteDto.class), transportNoteNo);
	}
	
}
