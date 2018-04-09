package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yorma.wms.dao.api.ReceivingPackingListDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.ReceivingPackingList;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:入库货物信息Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月25日
 * @version 1.00.00
 * @history:
 *
 */
public class ReceivingPackingListDaoImpl extends BaseDaoImpl<ReceivingPackingList> implements ReceivingPackingListDao {

	/**
	 * 入库货物信息查询操作
	 * @return：查询（所有/符合条件）的数据
	 */
	public List<ReceivingPackingList> getReceivingPackingListByNoteNo(String receivingNoteNo,Integer status,Date startDate,Date endDate) {
		List<Object> params=new ArrayList<>();
		String sql="select  receiving_packing_list.* from receiving_packing_list LEFT JOIN receiving_note_asn asn ON receiving_packing_list.MERGE_LOT_NO = asn.MERGE_LOT_NO where  asn.status = 20 AND receiving_packing_list.RECEIVING_NOTE_NO=? ";
		params.add(receivingNoteNo);
		if (status !=null) {
			sql+=" and receiving_packing_list.status=? ";
			params.add(status);
		}
		if (startDate != null) {
			sql+=" and receiving_packing_list.CREATE_DATE >=? ";
			params.add(startDate);
		}
		if (endDate != null) {
			sql+="  and receiving_packing_list.CREATE_DATE <=? ";
			params.add(endDate);
		}
		sql+="GROUP BY receiving_packing_list.uuid ORDER BY CREATE_DATE DESC";
		return memory.query(sql, new BeanListHandler<ReceivingPackingList>(ReceivingPackingList.class), params.toArray());
	}
	

	
	/**
	 * 根据uuid删除数据
	 * @param ANS_UUID
	 * @return
	 */
	@Override
	public int removeByAsnUUId(String asnUuid) {
		String sql="delete from receiving_packing_list where ANS_UUID=?";
		if (null!=connection) {
			return memory.update(connection, sql, asnUuid);
		}
		return memory.update(sql, new Object[]{asnUuid});
	}
	
	/**
	 * 根据收货单号删除数据
	 * @param receivingNoteNo
	 * @return
	 */
	@Override
	public int delete(String receivingNoteNo) {
		String sql="delete from receiving_packing_list where RECEIVING_NOTE_NO=?";
		if (null!=connection) {
			return memory.update(connection, sql, receivingNoteNo);
		}
		return memory.update(sql, new Object[]{receivingNoteNo});
	}
	
	/**
	 * 根据入库货物信息UUID查询数据
	 */
	@Override
	public ReceivingPackingList getReceivingPackingListByUUID(String uuid) {
		String sql="select * from receiving_packing_list where uuid=?";
		if (null !=connection) {
			return memory.query(connection, sql, new BeanHandler<ReceivingPackingList>(ReceivingPackingList.class), uuid);
		}
		return memory.query( sql, new BeanHandler<ReceivingPackingList>(ReceivingPackingList.class), uuid);
	}
	
	/**
	 * 根据入库货物信息UUID修改状态
	 */
	@Override
	public int updateStatusByUUID(Integer status, String uuid) {
		String sql="update receiving_packing_list set status=? where uuid=?";
		if (null != connection) {
			return memory.update(connection ,sql, new Object[]{status,uuid});
		}
		return memory.update(sql, new Object[]{status,uuid});
	}
	
	/**
	 * 根据合并批次号删除入库货物信息
	 * @param mergeLotNo
	 * @return	影响行数
	 */
	@Override
	public int deleteReceivingPackingListByMergeLotNo(String mergeLotNo) {
		String sql="DELETE FROM receiving_packing_list WHERE merge_lot_no=?";
		if (null != connection) {
			return memory.update(connection, sql, mergeLotNo);
		}
		return memory.update(connection, sql, mergeLotNo);
	}
	
	/**
	 * 根据合并批次号查询入库货物信息
	 * @param mergeLotNo	合并批次号
	 * @return	 List<入库货物信息实体>
	 */
	@Override
	public List<ReceivingPackingList> getReceivingPackingListByMergeLotNo(String mergeLotNo) {
		String sql="SELECT * FROM receiving_packing_list WHERE MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<ReceivingPackingList>(ReceivingPackingList.class), mergeLotNo);
		}
		return memory.query(sql, new BeanListHandler<ReceivingPackingList>(ReceivingPackingList.class), mergeLotNo);
	}
	
	/**
	 * 根据合并批次号查询已上架的入库货物信息
	 * @param mergeLotNo	合并批次号
	 * @return	List<入库货物信息实体>
	 */
	@Override
	public List<ReceivingPackingList> getLocationByMergeLotNo(String mergeLotNo) {
		String sql="SELECT * FROM receiving_packing_list WHERE MERGE_LOT_NO=? AND LOCATION IS NOT NULL";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<ReceivingPackingList>(ReceivingPackingList.class), mergeLotNo);
		}
		return memory.query(sql, new BeanListHandler<ReceivingPackingList>(ReceivingPackingList.class), mergeLotNo);
	}
	
	/**
	 * 根据状态和入库信息UUID查询入库货物信息条数
	 * @param status	状态
	 * @param uuid		入库货物信息UUID
	 * @return			信息条数
	 */
	@Override
	public Integer getReceivingPackingListByStatusAndUUID(Integer status, String uuid) {
		String sql="SELECT COUNT(id) from receiving_packing_list WHERE STATUS=? AND UUID=? ";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), new Object[]{status,uuid});
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[]{status,uuid});
	}
	
}
