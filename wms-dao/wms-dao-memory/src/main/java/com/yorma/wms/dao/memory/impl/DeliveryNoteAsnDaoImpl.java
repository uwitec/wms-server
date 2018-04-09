package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.DeliveryNoteAsnDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.DeliveryNoteAsn;
import com.yorma.wms.entity.dto.DeliveryNoteAsnDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:出货明细Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年9月01日
 * @version 1.00.00
 * @history:
 *
 */
public class DeliveryNoteAsnDaoImpl extends BaseDaoImpl<DeliveryNoteAsn>implements DeliveryNoteAsnDao {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryNoteAsnDaoImpl.class);

	/**
	 * 出货单查询操作及明细的分配总量
	 */
	public List<DeliveryNoteAsnDto> getDeliveryNoteAsnDtoByNoteNo(String deliveryNoteNo) {
		String sql = "SELECT  sum( deliveryPackingList.DELIVERY_QTY ) AS deliveryQtySum, deliveryNoteAsn.* FROM delivery_note_asn deliveryNoteAsn LEFT JOIN delivery_packing_list deliveryPackingList ON deliveryNoteAsn.MERGE_LOT_NO = deliveryPackingList.MERGE_LOT_NO WHERE deliveryNoteAsn.DELIVERY_NOTE_NO =? GROUP BY deliveryNoteAsn.UUID ";
		return memory.query(sql, new BeanListHandler<DeliveryNoteAsnDto>(DeliveryNoteAsnDto.class), deliveryNoteNo);
	}

	/**
	 * 根据出货单号查询出库明细预告及预分配总量、拣货总量
	 */
	@Override
	public List<DeliveryNoteAsnDto> getDeliveryNoteAsnDtoAndExpectQtySumByNoteNo(String deliveryNoteNo) {
		String sql = "SELECT deliveryPackingList.deliveryQtySum, SUM(deliveryNoteAsn.EXPECT_QTY) as expectQtySum, deliveryNoteAsn.MU,deliveryNoteAsn.GOODS_UUID,deliveryNoteAsn.PN FROM delivery_note_asn deliveryNoteAsn LEFT JOIN (select SUM(packingList.delivery_qty) as deliveryQtySum, packingList.* from delivery_packing_list packingList where packingList.DELIVERY_NOTE_NO = ? GROUP BY GOODS_UUID) deliveryPackingList ON deliveryNoteAsn.DELIVERY_NOTE_NO = deliveryPackingList.DELIVERY_NOTE_NO AND deliveryPackingList.mu =deliveryNoteAsn.MU WHERE deliveryNoteAsn.DELIVERY_NOTE_NO = ? GROUP BY deliveryNoteAsn.GOODS_UUID";
		return memory.query(sql, new BeanListHandler<DeliveryNoteAsnDto>(DeliveryNoteAsnDto.class),
				new Object[] { deliveryNoteNo, deliveryNoteNo });
	}

	/**
	 * 根据uuid查询预出货货物信息
	 */
	@Override
	public DeliveryNoteAsn getDeliveryNoteAsnByUUID(String uuid) {
		String sql = "select * from delivery_note_asn where UUID=?";
		return memory.query(sql, new BeanHandler<DeliveryNoteAsn>(DeliveryNoteAsn.class), uuid);
	}

	/**
	 * 根据出货单号查询明细分配方式
	 */
	@Override
	public List<DeliveryNoteAsn> getIsMergedByNoteNo(String deliveryNoteNo, Boolean isMerged) {
		List<Object> params = new ArrayList<>();
		params.add(deliveryNoteNo);
		String sql = "select * from delivery_note_asn where DELIVERY_NOTE_NO=? and IS_MERGED is not null";
		if (isMerged != null) {
			sql += " and IS_MERGED= ? ";
			params.add(isMerged);
		}
		return memory.query(sql, new BeanListHandler<DeliveryNoteAsn>(DeliveryNoteAsn.class), params.toArray());
	}

	/**
	 * 根据出货单号查询出货单明细预告
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 * @return 出货单明细预告实体集合
	 */
	@Override
	public List<DeliveryNoteAsn> getDeliveryNoteAsnByDeliveryNoteNo(String deliveryNoteNo) {
		String sql = "SELECT * FROM delivery_note_asn WHERE DELIVERY_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<DeliveryNoteAsn>(DeliveryNoteAsn.class),
					deliveryNoteNo);
		}
		return memory.query(sql, new BeanListHandler<DeliveryNoteAsn>(DeliveryNoteAsn.class), deliveryNoteNo);
	}

	/**
	 * 根据出库单号统计明细合并后的品名，合并批次号，商品UUID，料号，总预收数量，总出库数量
	 * 
	 * @param deliveryNoteNo
	 * @return 明细DtoLIst
	 */
	@Override
	public List<DeliveryNoteAsnDto> getMergeDeliveryNoteAsn(String deliveryNoteNo) {
		String sql = "SELECT deliveryPackingList.deliveryQtySum, deliveryNoteAsn.MERGE_LOT_NO, deliveryNoteAsn.PN, deliveryNoteAsn.GOODS_NAME, deliveryNoteAsn.GOODS_UUID, SUM(deliveryNoteAsn.EXPECT_QTY) AS expectQtySum FROM delivery_note_asn deliveryNoteAsn, ( SELECT sum( delivery_packing_list.DELIVERY_QTY ) AS deliveryQtySum, delivery_packing_list.MERGE_LOT_NO FROM delivery_packing_list WHERE delivery_packing_list.DELIVERY_NOTE_NO =? GROUP BY delivery_packing_list.MERGE_LOT_NO ) deliveryPackingList WHERE deliveryNoteAsn.MERGE_LOT_NO = deliveryPackingList.MERGE_LOT_NO AND deliveryNoteAsn.DELIVERY_NOTE_NO =? GROUP BY deliveryNoteAsn.MERGE_LOT_NO";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<DeliveryNoteAsnDto>(DeliveryNoteAsnDto.class),
					new Object[] { deliveryNoteNo, deliveryNoteNo });
		}
		return memory.query(sql, new BeanListHandler<DeliveryNoteAsnDto>(DeliveryNoteAsnDto.class),
				new Object[] { deliveryNoteNo, deliveryNoteNo });
	}

	/**
	 * 根据商品UUID查询出库明细记录中未出库的信息条数
	 * 
	 * @param goodsUuid
	 *            商品UUID
	 * @return 出库明细记录中未出库的信息条数
	 */
	@Override
	public int getDeliveryNoteAsnCountByGoodsUUID(String goodsUuid) {
		String sql = "SELECT COUNT(*) FROM delivery_note_asn asn LEFT JOIN delivery_packing_list list ON asn.MERGE_LOT_NO = list.MERGE_LOT_NO WHERE list.`STATUS` < 20 AND asn.GOODS_UUID =? ";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), new Object[] { goodsUuid });
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[] { goodsUuid });
	}

	/**
	 * 根据出货单号或明细UUID修改明细是否合并分配
	 * 
	 * @param deliveryNoteNo
	 *            出库单号
	 * @param asnUuid
	 *            明细UUID
	 * @param isMerged
	 *            是否合并
	 * @return 影响行数
	 */
	@Override
	public int updateIsMergedByNoteNoOrAsnUUID(String deliveryNoteNo, String asnUuid, Boolean isMerged) {
		String sql = "update delivery_note_asn set IS_MERGED=? where  ";
		List<Object> params = new ArrayList<>();
		params.add(isMerged);
		if (deliveryNoteNo != null) {
			sql += "  DELIVERY_NOTE_NO=? ";
			params.add(deliveryNoteNo);
		}
		if (asnUuid != null) {
			sql += "  UUID=? ";
			params.add(asnUuid);
		}
		if (null != connection) {
			return memory.update(connection, sql, params.toArray());
		}
		return memory.update(sql, params.toArray());
	}

	/**
	 * 根据合并批次号修改明细分配方式为未分配和清空合并批次号
	 * 
	 * @param MergeLotNo
	 *            合并批次号
	 * @return 影响行数
	 */
	@Override
	public Integer updateDeliveryNoteAsnByMergeLotNo(String mergeLotNo) {
		String sql = "UPDATE delivery_note_asn SET IS_MERGED=NULL ,status=0, MERGE_LOT_NO=NULL,ALLOCATION_QTY =0 WHERE MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, mergeLotNo);
		}
		return memory.update(sql, mergeLotNo);
	}

	/**
	 * 根据uuid删除收货明细
	 */
	@Override
	public int removeByUUID(String uuid) {
		String sql = "delete from delivery_note_asn where uuid=?";
		if (null != connection) {
			return memory.update(connection, sql, uuid);
		}
		return memory.update(sql, uuid);
	}

	/**
	 * 根据出货单号删除收货明细
	 */
	@Override
	public int deleteDeliveryNoteAsn(String deliveryNoteNo) {
		String sql = "delete from delivery_note_asn where delivery_note_no=?";
		if (null != connection) {
			return memory.update(connection, sql, deliveryNoteNo);
		}
		return memory.update(sql, new Object[] { deliveryNoteNo });
	}

	/**
	 * 统计所有已结束的出库明细信息条数
	 * 
	 * @param parames
	 * @return
	 */
	@Override
	public Integer countDeliveryNoteAsn(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append(
				"SELECT COUNT(*) FROM delivery_note_asn deliveryNoteAsn LEFT JOIN delivery_note AS deliveryNote ON deliveryNoteAsn.DELIVERY_NOTE_NO = deliveryNote.DELIVERY_NOTE_NO WHERE deliveryNote.`STATUS` = 60 ");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" AND " + key + "?");
					params.add(parames.get(key));
				}
			}
		}

		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}

	/**
	 * 查询所有已结束的出库明细信息
	 * 
	 * @param parames
	 * @param pageNumber
	 * @param pageSize
	 * @return 已结束的出库明细信息
	 */
	@Override
	public List<DeliveryNoteAsnDto> listDeliveryNoteAsn(Map<String, Object> parames, int pageNumber,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append(
				"SELECT deliveryNoteAsn.*,deliveryNote.OWNER_CODE FROM delivery_note_asn deliveryNoteAsn LEFT JOIN delivery_note AS deliveryNote ON deliveryNoteAsn.DELIVERY_NOTE_NO = deliveryNote.DELIVERY_NOTE_NO WHERE deliveryNote.`STATUS` = 60 ");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
						&& !"% %".equals(parames.get(key))) {
					sql.append(" AND " + key + "?");
					params.add(parames.get(key));
				}
			}
		}

		sql.append(" order by CREATE_DATE desc");
		if (pageNumber != 0 && pageSize != 0) {
			params.add((pageNumber - 1) * pageSize);
			params.add(pageSize);
			sql.append(" limit ?,?");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Dao：[listDeliveryNoteAsn], sql:{}", sql.toString());
			logger.debug("Dao：[listDeliveryNoteAsn], params:{}", params);
		}

		return memory.query(sql.toString(), new BeanListHandler<DeliveryNoteAsnDto>(DeliveryNoteAsnDto.class),
				params.toArray());
	}

	/**
	 * 根据合并单号修改明细的实际分配量
	 * 
	 * @param deliveryQty
	 *            明细的实际分配量
	 * @param mergeLotNo
	 *            合并单号
	 * @return
	 */
	@Override
	public Integer updateDeliveryQtyByMergeLotNo(Integer deliveryQty, String mergeLotNo) {
		String sql = "UPDATE delivery_note_asn SET ALLOCATION_QTY = ? WHERE MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.update(connection, sql, new Object[] { deliveryQty, mergeLotNo });
		}
		return memory.update(sql, new Object[] { deliveryQty, mergeLotNo });
	}

}
