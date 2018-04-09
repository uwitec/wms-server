package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.ReceivingNoteAsnDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.ReceivingNoteAsn;
import com.yorma.wms.entity.dto.ReceivingNoteAsnDTO;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:收货明细Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月25日
 * @version 1.00.00
 * @history:
 *
 */
public class ReceivingNoteAsnDaoImpl extends BaseDaoImpl<ReceivingNoteAsn>implements ReceivingNoteAsnDao {

	private static final Logger logger = LoggerFactory.getLogger(ReceivingNoteAsnDaoImpl.class);
	
	/**
	 * 收货明细查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	public List<ReceivingNoteAsn> getReceivingNoteAsnByNoteNo(String receivingNoteNo,Integer status) {
		String sql="select * from  receiving_note_asn  where RECEIVING_NOTE_NO=? ";
		List<Object> params=new ArrayList<>();
		params.add(receivingNoteNo);
		if (status !=null) {
			sql+=" and status=? ";
			params.add(status);
		}
		sql+=" ORDER BY PN,CREATE_DATE DESC ";
		return memory.query(sql, new BeanListHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), params.toArray());
	}

	/**
	 * 根据入库单号查询已分配的明细信息条数
	 * @param receivingNoteNo	入库单号
	 * @return	信息条数
	 */
	@Override
	public Integer getReceivingNoteAsnCountByReceivingNoteNo(String receivingNoteNo) {
		String sql="SELECT COUNT(id) from receiving_note_asn WHERE STATUS <>0 AND RECEIVING_NOTE_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), receivingNoteNo);
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), receivingNoteNo);
	}
	
	/**
	 * 根据明细UUID查询明细信息
	 */
	@Override
	public ReceivingNoteAsn getReceivingNoteAsnByUUID(String uuid) {
		String sql="select * from receiving_note_asn where UUID=?";
		return memory.query(sql, new BeanHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), uuid);
	}
	
	/**
	 * 根据uuid删除数据
	 */
	@Override
	public int removeByUUId(String uuid) {
		String sql = "delete from receiving_note_asn where UUID=?";
		if (null != connection){
			return memory.update(connection, sql, uuid);
		}
		return memory.update(sql, new Object[]{uuid});
	}
	/**
	 * 根据收货单号删除数据
	 */
	@Override
	public int delete(String receivingNoteNo) {
		String sql = "delete from receiving_note_asn where RECEIVING_NOTE_NO=?";
		if (null != connection){
			return memory.update(connection, sql, receivingNoteNo);
		}
		return memory.update(sql, new Object[]{receivingNoteNo});
	}
	
	/**
	 * 根据明细UUID修改明细状态
	 */
	@Override
	public int updateStatusByAsnUUID(Integer status, String asnUuid) {
		String sql="update receiving_note_asn set status=? where UUID=? ";
		if (null != connection) {
			return memory.update(connection ,sql, new Object[]{status,asnUuid});
		}
		return memory.update(sql, new Object[]{status,asnUuid});
	}
	
	/**
	 * 根据明细UUID修改合并批次号
	 * @param mergeLotNo	合并批次号
	 * @param asnUuid	明细UUID
	 * @return		 影响行数
	 */
	@Override
	public int updateMergeLotNoByAsnUUID(String mergeLotNo, String asnUuid) {
		String sql="update receiving_note_asn set MERGE_LOT_NO=? where UUID=? ";
		if (null != connection) {
			return memory.update(connection ,sql, new Object[]{mergeLotNo,asnUuid});
		}
		return memory.update(sql, new Object[]{mergeLotNo,asnUuid});
	}
	
	/**
	 * 根据合并批次号修改明细预告到货状态
	 * @param 	status		状态
	 * @param mergeLotNo	合并批次号
	 * @return		影响行数
	 */
	@Override
	public int updateStatusByMergeLotNo(Integer status, String mergeLotNo) {
		String sql="update receiving_note_asn set status=? where MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.update(connection ,sql, new Object[]{status,mergeLotNo});
		}
		return memory.update(sql, new Object[]{status,mergeLotNo});
	}
	
	/**
	 * 根据入库单号查询合并后明细预告的预收总量。验收数量、合并批次号、品名、料号。包装数
	 * @param receivingNoteNo	入库单号
	 * @return	明细预告实体集合
	 */
	@Override
	public List<ReceivingNoteAsn> getReceivingNoteAsnByreceivingNoteNo(String receivingNoteNo,Integer status) {
		String sql="SELECT receiving_note_asn.MERGE_LOT_NO, receiving_note_asn.EXPECT_QTY, ACCEPTANCE_QTY, receiving_note_asn.PN, 	receiving_note_asn.GOODS_NAME, receiving_note_asn.PACKAGING_QTY FROM (SELECT receiving_note_asn.RECEIVING_NOTE_NO, receiving_note_asn.status, receiving_note_asn.MERGE_LOT_NO, SUM(EXPECT_QTY) AS EXPECT_QTY, ACCEPTANCE_QTY, receiving_note_asn.PN, 	receiving_note_asn.GOODS_NAME, receiving_note_asn.PACKAGING_QTY FROM receiving_note_asn WHERE  receiving_note_asn.RECEIVING_NOTE_NO = ? AND status = ? GROUP BY receiving_note_asn.MERGE_LOT_NO) receiving_note_asn, receiving_packing_list packingList WHERE receiving_note_asn.MERGE_LOT_NO = packingList.MERGE_LOT_NO AND packingList.LOCATION IS NULL GROUP BY receiving_note_asn.MERGE_LOT_NO ";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), new Object[]{receivingNoteNo,status}); 
		}
		return memory.query(sql, new BeanListHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), new Object[]{receivingNoteNo,status});
	}
	
	/**
	 * 根据合并批次号查询明细预告
	 * @param mergeLotNo	合并批次号
	 * @return	明细预告实体集合
	 */
	@Override
	public List<ReceivingNoteAsn> getReceivingNoteAsnByMergerLotNo(String mergeLotNo) {
		String sql="SELECT * FROM receiving_note_asn WHERE MERGE_LOT_NO=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), mergeLotNo);
		}
		return memory.query(connection, sql, new BeanListHandler<ReceivingNoteAsn>(ReceivingNoteAsn.class), mergeLotNo);
	}
	
	/**
	 * 根据商品UUID查询入库明细预告信息条数
	 * @param goodsUuid 商品UUID
	 * @return 入库明细预告信息条数
	 */
	@Override
	public int getReceivingNoteAsnCountByGoodsUUID(String goodsUuid) {
		String sql="SELECT COUNT(*) FROM receiving_note_asn WHERE GOODS_UUID=?";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnHandler<Integer>(Integer.class), new Object[]{goodsUuid});
		}
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), new Object[]{goodsUuid});
	}
	
	/**
	 * 根据条件查询所有已结束的明细条数
	 * @param parames
	 * @return
	 */
	@Override
	public int countReceivingNoteAsn(Map<String, Object> parames) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("SELECT COUNT(*) FROM receiving_note_asn AS receivingNoteAsn LEFT JOIN receiving_note AS receivingNote ON receivingNoteAsn.RECEIVING_NOTE_NO = receivingNote.RECEIVING_NOTE_NO WHERE receivingNote.`STATUS` = 60  ");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
							&& !"% %".equals(parames.get(key))) {
					sql.append(" AND "+key+"?");
					params.add(parames.get(key));
				}
			}
		}
		return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 根据条件查询所有已结束的明细信息
	 * @param parames 条件集合
	 * @param pageNumber	
	 * @param pageSize
	 * @return 已结束的明细信息
	 */
	@Override
	public List<ReceivingNoteAsnDTO> listReceivingNoteAsn(Map<String, Object> parames, int pageNumber,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		
		sql.append("SELECT receivingNoteAsn.*,receivingNote.OWNER_CODE FROM receiving_note_asn AS receivingNoteAsn LEFT JOIN receiving_note AS receivingNote ON receivingNoteAsn.RECEIVING_NOTE_NO = receivingNote.RECEIVING_NOTE_NO WHERE receivingNote.`STATUS` = 60  ");
		if (parames != null) {
			for (String key : parames.keySet()) {
				if (parames.get(key) != null && !"".equals(parames.get(key)) && !"%%".equals(parames.get(key))
							&& !"% %".equals(parames.get(key))) {
					sql.append(" AND "+key+"?");
					params.add(parames.get(key));
				}
			}
		}
		
		sql.append(" order by CREATE_DATE desc");
		if (pageNumber !=0 &&pageSize!=0) {
			params.add((pageNumber-1)*pageSize);
			params.add(pageSize);
			sql.append(" limit ?,?");
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Dao：[listReceivingNoteAsn], sql:{}", sql.toString());
			logger.debug("Dao：[listReceivingNoteAsn], params:{}", params.toArray());
		}
		
		return memory.query(sql.toString(), new BeanListHandler<ReceivingNoteAsnDTO>(ReceivingNoteAsnDTO.class), params.toArray());
	}
	
}
