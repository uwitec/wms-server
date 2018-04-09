package com.yorma.wms.dao.memory.impl;

import java.util.List;

import com.yorma.wms.dao.api.PackInfomationDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.PackInfomation;
import com.yorma.wms.entity.dto.PackInfomationDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;

/**
 * 
 * @Description:货物包装信息Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月25日
 * @version 1.00.00
 * @history:
 *
 */
public class PackInfomationDaoImpl extends BaseDaoImpl<PackInfomation> implements PackInfomationDao {

	/**
	 * 根据商品UUID查询包装信息
	 */
	@Override
	public List<PackInfomationDto> getPackInfomationDtoByUUID(String goodsUuid) {
		
		 String sql="SELECT packInfomation.*,goods.PN,sysDepart.DEPART_NAME AS departName,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5Name FROM pack_infomation packInfomation,goods goods,sys_depart sysDepart WHERE goods.OWNER_CODE=sysDepart.DEPART_CODE and goods.UUID=packInfomation.GOODS_UUID AND packInfomation.GOODS_UUID=? ";
		
		return memory.query(sql, new BeanListHandler<PackInfomationDto>(PackInfomationDto.class), goodsUuid);
	}
	
	/**
	 * 根据商品UUID查询包装信息
	 * 
	 * @param goodsUuid
	 * @return list<货物包装信息>
	 */
	@Override
	public List<PackInfomation> getPackInfomationByUUID(String goodsUuid) {
		String sql="SELECT * from pack_infomation where GOODS_UUID=? ";
		if (null != connection) {
			return memory.query(connection, sql, new BeanListHandler<PackInfomation>(PackInfomation.class), goodsUuid);
		}
		return memory.query(sql, new BeanListHandler<PackInfomation>(PackInfomation.class), goodsUuid);
	}
	
	/**
	 * 根据id查询包装信息
	 */
	@Override
	public PackInfomationDto getPackInfomationByid(Long id) {
		
		String sql="SELECT packInfomation.*,goods.PN,sysDepart.DEPART_NAME AS departName,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4Name,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=packInfomation.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5Name FROM pack_infomation packInfomation,goods goods,sys_depart sysDepart WHERE goods.OWNER_CODE=sysDepart.DEPART_CODE and goods.UUID=packInfomation.GOODS_UUID AND packInfomation.id=? ";
		
		return memory.query(sql, new BeanHandler<PackInfomationDto>(PackInfomationDto.class), id);
	}
	
	/**
	 * 根据商品UUID删除包装信息
	 */
	@Override
	public int removeByGoodsUUID(String goodsUuid) {
		String sql="delete from pack_infomation where goods_uuid=?";
		if (null != connection) {
			return memory.update(connection,sql, goodsUuid);
		}
		return memory.update(sql, goodsUuid);
	}
	
	/**
	 * 根据商品UUID查询该商品的默认包装键
	 */
	@Override
	public PackInfomation getPackInfomationIsDefaultByGoodsUUID(String uuid) {
		String sql="select * from pack_infomation where IS_DEFAULT=true and GOODS_UUID=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<PackInfomation>(PackInfomation.class), uuid);
		}
		return memory.query(sql, new BeanHandler<PackInfomation>(PackInfomation.class), uuid);
	}
	
}
