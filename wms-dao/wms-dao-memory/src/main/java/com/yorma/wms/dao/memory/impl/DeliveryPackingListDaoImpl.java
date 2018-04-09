package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.DeliveryPackingListDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.DeliveryPackingList;
import com.yorma.wms.entity.dto.DeliveryPackingListDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:出库货物信息Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月28日
 * @version 1.00.00
 * @history:
 *
 */
public class DeliveryPackingListDaoImpl extends BaseDaoImpl<DeliveryPackingList>implements DeliveryPackingListDao {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryPackingListDaoImpl.class);

	/**
	 * 出库货物信息查询操作
	 */
	public List<DeliveryPackingList> getDeliveryPackingListByNoteNo(String deliveryNoteNo) {

		String sql = "select * from delivery_packing_list where DELIVERY_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class),
					deliveryNoteNo);
		}
		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), deliveryNoteNo);
	}

	/**
	 * 查询出库货物信息
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByNoteNo(String deliveryNoteNo, Integer status,
			String deliveryLotNo) {
		String sql = "select deliveryPackingList.* from delivery_packing_list deliveryPackingList  where  deliveryPackingList.DELIVERY_NOTE_NO=?";
		List<Object> params = new ArrayList<>();
		params.add(deliveryNoteNo);
		if (status != null) {
			sql += " and status=? ";
			params.add(status);
		}

		if (deliveryLotNo != null && !"".equals(deliveryLotNo)) {
			sql += " and DELIVERY_LOT_NO=? ";
			params.add(deliveryLotNo);
		}

		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), params.toArray());
	}

	/**
	 * 根据明细Uuid查询拣货
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByAsnUUID(String asnUuid) {
		String sql = "select * from delivery_packing_list where ans_uuid=?";
		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), asnUuid);
	}

	/**
	 * 根据明细、是否拣货查询拣货信息
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByAsnUUID(String asnUuid, Integer status) {
		String sql = "select * from delivery_packing_list where ans_uuid=? and status=? ";
		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class),
				new Object[] { asnUuid, status });
	}

	/**
	 * 根据库存Uuid查询拣货信息
	 */
	@Override
	public DeliveryPackingList getDeliveryPackingListByStockUUID(String stockUuid, String asnUuid) {
		String sql = "select * from delivery_packing_list where STOCK_UUID=? and ANS_UUID=?";
		return memory.query(sql, new BeanHandler<DeliveryPackingList>(DeliveryPackingList.class),
				new Object[] { stockUuid, asnUuid });
	}

	/**
	 * 根据出货单号删除出库货物信息
	 */
	@Override
	public int deleteDeliveryPackingList(String deliveryNoteNo) {
		String sql = "delete from delivery_packing_list where delivery_note_no=?";
		if (null != connection) {
			return memory.update(connection, sql, deliveryNoteNo);
		}
		return memory.update(sql, new Object[] { deliveryNoteNo });
	}

	/**
	 * 根据明细UUID删除出库货物信息
	 */
	@Override
	public int deleteDeliveryPackingListByAsnUUID(String asnUuid) {
		String sql = "delete from delivery_packing_list where ANS_UUID=?";
		if (null != connection) {
			return memory.update(connection, sql, asnUuid);
		}
		return memory.update(sql, new Object[] { asnUuid });
	}

	/**
	 * 根据UUID查询拣货信息
	 */
	@Override
	public DeliveryPackingList getdiveryPackingListByUUID(String uuid) {
		String sql = "select * from delivery_packing_list where UUID=?";
		return memory.query(sql, new BeanHandler<DeliveryPackingList>(DeliveryPackingList.class), uuid);
	}

	/**
	 * 根据拣货信息UUID修改是否出库、出库批次
	 */
	@Override
	public int updateStatusByUUID(Integer status, String deliveryLotNo, String uuid, Date deliveryDate) {
		String sql = "update delivery_packing_list set status=? , DELIVERY_LOT_NO=?,DELIVERY_DATE=? where UUID=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[] { status, deliveryLotNo, deliveryDate, uuid });
		}
		return memory.update(sql, new Object[] { status, deliveryLotNo, deliveryDate, uuid });
	}

	/**
	 * 根据拣货信息UUID修改是否拣货
	 */
	@Override
	public int updateIsOrderPickingByUUID(Integer status, String uuid) {
		String sql = "update delivery_packing_list set status=? where UUID=?";
		return memory.update(connection, sql, new Object[] { status, uuid });
	}

	/**
	 * 根据库存UUID查询拣货信息
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByStockUUID(String stockUuid) {
		String sql = "SELECT * FROM delivery_packing_list where STOCK_UUID=? ";
		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), stockUuid);
	}

	/**
	 * 根据条件查询拣货信息
	 */
	@Override
	public List<DeliveryPackingListDto> getDeliveryPackingListDto(Map<String, Object> parameters) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT depart.DEPART_CODE, depart.DEPART_NAME, deliveryPackingList.* FROM delivery_packing_list deliveryPackingList LEFT JOIN delivery_note deliveryNote ON deliveryPackingList.DELIVERY_NOTE_NO = deliveryNote.DELIVERY_NOTE_NO LEFT JOIN sys_depart depart ON deliveryNote.OWNER_CODE = depart.DEPART_CODE where  deliveryPackingList.Status=10 and deliveryPackingList.TRANSPORT_NOTE_NO is  NULL");

		for (String key : parameters.keySet()) {

			if (parameters.get(key) != null && !"".equals(parameters.get(key)) && !"%%".equals(parameters.get(key))
					&& !"% %".equals(parameters.get(key))) {
				sql.append(" and " + key + " ? ");
				params.add(parameters.get(key));
			}
		}
		return memory.query(sql, new BeanListHandler<DeliveryPackingListDto>(DeliveryPackingListDto.class), params);
	}

	/**
	 * 根据运送单号修改拣货单为出库状态
	 */
	@Override
	public int updateStatusByTransportNoteNo(String transportNoteNo, Integer status, Date deliveryDate) {
		String sql = "update delivery_packing_list set status=?, DELIVERY_DATE=? where TRANSPORT_NOTE_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[] { status, deliveryDate, transportNoteNo });
		}
		return memory.update(sql, new Object[] { status, deliveryDate, transportNoteNo });
	}

	/**
	 * 根据UUID修改运送单号
	 */
	@Override
	public int updateTranspotyNoteNoByUUID(String transportNoteNo, String uuid) {
		String sql = "update delivery_packing_list set TRANSPORT_NOTE_NO=? where uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[] { transportNoteNo, uuid });
		}
		return memory.update(sql, new Object[] { transportNoteNo, uuid });
	}

	/**
	 * 根据出货单号、库存UUID查询拣货信息
	 */
	@Override
	public DeliveryPackingList getDeliveryPackingListByNoteNoAndStockUUID(String deliveryNoteNo, String stockUuid) {
		String sql = "select * from delivery_packing_list where DELIVERY_NOTE_NO=? and STOCK_UUID=?  ";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<DeliveryPackingList>(DeliveryPackingList.class),
					new Object[] { deliveryNoteNo, stockUuid });
		}
		return memory.query(sql, new BeanHandler<DeliveryPackingList>(DeliveryPackingList.class),
				new Object[] { deliveryNoteNo, stockUuid });

	}

	/**
	 * 根据运送单号查询拣货信息
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByTransportNoteNo(String transportNoteNo) {
		String sql = "select * from delivery_packing_list where TRANSPORT_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class),
					transportNoteNo);
		}
		return memory.query(sql, new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), transportNoteNo);
	}

	/**
	 * 根据合并批次号和是否拣货查询拣货货物信息
	 * 
	 * @param mergeLotNo
	 *            合并批次号
	 * @param status
	 *            是否拣货(null则不作为筛选条件)
	 * @return List<拣货信息>
	 */
	@Override
	public List<DeliveryPackingList> getDeliveryPackingListByMergeLotNo(String mergeLotNo, Integer status) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("SELECT * FROM delivery_packing_list WHERE MERGE_LOT_NO=? ");
		params.add(mergeLotNo);
		if (status != null) {
			sql.append(" AND status>=? ");
			params.add(status);
		}
		if (null != connection) {
			return memory.query(connection, sql.toString(),
					new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class), params.toArray());
		}
		return memory.query(sql.toString(), new BeanListHandler<DeliveryPackingList>(DeliveryPackingList.class),
				params.toArray());
	}

	/**
	 * 根据运送单号删除拣货信息中的运送单号
	 * 
	 * @param transportNoteNo
	 *            运送单号
	 * @return 影响行数
	 */
	@Override
	public int removeTransportNoteNoByTransportNoteNo(String transportNoteNo) {
		String sql = "UPDATE delivery_packing_list SET TRANSPORT_NOTE_NO=NULL WHERE TRANSPORT_NOTE_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, transportNoteNo);
		}
		return memory.update(sql, transportNoteNo);
	}

	/**
	 * 根据拣货UUID移除拣货信息中的配送单号
	 * 
	 * @param uuid
	 *            拣货UUID
	 * @return 影响行数
	 */
	@Override
	public int removeTransportNoteNoByUUID(String uuid) {
		String sql = "UPDATE delivery_packing_list SET TRANSPORT_NOTE_NO=NULL WHERE UUID=?";
		if (null != connection) {
			return memory.update(connection, sql, uuid);
		}
		return memory.update(sql, uuid);
	}

	/**
	 * 根据单号查询拣货信息条数
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @return 拣货信息条数
	 */
	@Override
	public int getDeliveryPackingListCountsByNoteNo(String deliveryNoteNo) {
		String sql = "SELECT COUNT(*) FROM delivery_packing_list WHERE DELIVERY_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class),
					new Object[] { deliveryNoteNo });
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[] { deliveryNoteNo });
	}

	/**
	 * 根据条件统计已出库的拣货信息条数
	 * 
	 * @param params
	 *            参数集合
	 * @return 已出库的拣货信息条数
	 */
	@Override
	public int countDeliveryPackingList(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append(
				"SELECT count(*) FROM delivery_packing_list deliveryPackingList LEFT JOIN delivery_note deliveryNote ON deliveryPackingList.DELIVERY_NOTE_NO = deliveryNote.DELIVERY_NOTE_NO WHERE deliveryPackingList.`STATUS` = 20");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" and " + key + " ? ");
					params.add(parames.get(key));
				}
			}
		}
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}

	/**
	 * 根据条件查询已出库的拣货信息
	 * 
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return 已出库的拣货信息Dto集合
	 */
	@Override
	public List<DeliveryPackingListDto> listDeliveryPackingListDto(Map<String, Object> parames, int pageNumber,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append(
				"SELECT deliveryPackingList.*, deliveryNote.OWNER_CODE AS departCode FROM delivery_packing_list deliveryPackingList LEFT JOIN delivery_note deliveryNote ON deliveryPackingList.DELIVERY_NOTE_NO = deliveryNote.DELIVERY_NOTE_NO WHERE deliveryPackingList.`STATUS` = 20");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" and " + key + " ? ");
					params.add(parames.get(key));
				}
			}
		}
		sql.append(" order by CREATE_DATE desc");
		if (pageNumber!=0 && pageSize !=0) {
			sql.append(" limit ?,? ");
			params.add((pageNumber-1)*pageSize);
			params.add(pageSize);
		}
		return memory.query(sql.toString(), new BeanListHandler<DeliveryPackingListDto>(DeliveryPackingListDto.class),
				params.toArray());
	}

	/**
	 * 根据合并批次号删除拣货信息
	 * 
	 * @param mergeLotNo
	 *            合并批次号
	 * @return
	 */
	@Override
	public int removeByMergeLotNo(String mergeLotNo) {
		String sql = "DELETE FROM delivery_packing_list WHERE MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[] { mergeLotNo });
		}
		return memory.update(sql, new Object[] { mergeLotNo });
	}

}
