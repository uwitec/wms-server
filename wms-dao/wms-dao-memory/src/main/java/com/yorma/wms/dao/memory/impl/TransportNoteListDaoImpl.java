package com.yorma.wms.dao.memory.impl;

import java.util.List;

import com.yorma.wms.dao.api.TransportNoteListDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.TransportNoteList;
import com.yorma.wms.entity.dto.TransportNoteListDto;
import com.yorma.wms.entity.vo.TransportNoteListVO;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;

/**
 * 运送单明细DaoImpl
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 2017年11月03日
 * @version 1.00.00
 * @date 2017年11月03日
 * @version V1.0
 */
public class TransportNoteListDaoImpl extends BaseDaoImpl<TransportNoteList> implements TransportNoteListDao {

	/**
	 * 根据运送单号查询运输配送明细
	 */
	@Override
	public List<TransportNoteListDto> getTransportNoteListDtoByTransportNoteNo(String transportNoteNo) {
		String sql="select depart.DEPART_NAME,transportNoteList.* from transport_note_list transportNoteList LEFT JOIN sys_depart depart on transportNoteList.OWNER_CODE=depart.DEPART_CODE where transportNoteList.TRANSPORT_NOTE_NO=?";
		return memory.query(sql, new BeanListHandler<TransportNoteListDto>(TransportNoteListDto.class), transportNoteNo);
	}
	
	/**
	 * 根据运送单号查询运输配送明细
	 * @param transportNoteNo
	 * @return List<运输配送明细VO>
	 */
	@Override
	public List<TransportNoteListVO> listByTransportNoteNo(String transportNoteNo) {
		String sql="select depart.DEPART_NAME,transportNoteList.* from transport_note_list transportNoteList LEFT JOIN sys_depart depart on transportNoteList.OWNER_CODE=depart.DEPART_CODE where transportNoteList.TRANSPORT_NOTE_NO=?";
		return memory.query(sql, new BeanListHandler<TransportNoteListVO>(TransportNoteListVO.class), transportNoteNo);
	}
	
	/**
	 * 根据UUID删除运输配送明细
	 */
	@Override
	public int removeTransportNoteListByUUID(String uuid) {
		String sql="delete from transport_note_list where uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, uuid);
		}
		return memory.update(sql, uuid);
	}
	
	/**
	 * 根据uuid查询运输配送明细
	 */
	@Override
	public TransportNoteList getTransportNoteListByUUID(String uuid) {
		String sql="select * from transport_note_list where uuid=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<TransportNoteList>(TransportNoteList.class), uuid);
		}
		return memory.query(sql, new BeanHandler<TransportNoteList>(TransportNoteList.class), uuid);
	}
	
	/**
	 * 根据拣货信息UUID删除配送运输明细
	 * @param deliveryPackingListUuid	拣货信息UUID
	 * @return	影响行数
	 */
	@Override
	public int removeTransportNoteListByPLUUID(String deliveryPackingListUuid) {
		String sql="delete from transport_note_list where pl_uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, deliveryPackingListUuid);
		}
		return memory.update(sql, deliveryPackingListUuid);
	}
	
	/**
	 * 根据配送单号删除配送明细
	 * @param TransportNoteNO	配送单号
	 * @return	影响行数
	 */
	@Override
	public int removeTransportNoteListByTransportNoteNO(String TransportNOteNO) {
		String sql="delete from transport_note_list where TRANSPORT_NOTE_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, TransportNOteNO);
		}
		return memory.update(sql, TransportNOteNO);
	}
	
	/**
	 * 根据运送单号查询配送明细
	 * @param TransportNOteNO	运送单号
	 * @return	List<配送明细实体>
	 */
	@Override
	public List<TransportNoteList> getTransportNoteListByTransportNoteNo(String transportNoteNo) {
		String sql="select * from transport_note_list where TRANSPORT_NOTE_NO=?";
		return memory.query(sql, new BeanListHandler<TransportNoteList>(TransportNoteList.class), transportNoteNo);
	}
	
}
