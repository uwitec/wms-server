package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.common.entity.dto.Parameter;
import com.yorma.wms.dao.api.GoodsDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Goods;
import com.yorma.wms.entity.dto.GoodsDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;
import cn.ffcs.memory.ColumnListHandler;

/**
 * 
 * @Description:商品基础资料Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月7日
 * @version 1.00.00
 * @history:
 *
 */
public class GoodsDaoImpl extends BaseDaoImpl<Goods>implements GoodsDao {

	/**
	 * 商品基础资料查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	@Override
	public List<GoodsDto> getGoods(Map<String, Object> map) {
		// 用于存放map集合里的查询条件
		List<Object> params = new ArrayList<>();
		// 用于拼接SQL语句
		StringBuffer sql = new StringBuffer();

		sql.append(
				"SELECT depart.DEPART_NAME as departName,dictItem.ITEM_NAME AS defaultUnitName,a.pac,goods.*FROM goods LEFT JOIN sys_depart depart ON depart.DEPART_CODE=goods.OWNER_CODE LEFT JOIN dict_item dictItem on dictItem.ITEM_CODE=goods.DEFAULT_UNIT and dictItem.DICT_CODE='JLDW' LEFT JOIN(SELECT*FROM(SELECT p.goods_uuid uid,CONCAT(IFNULL(CONCAT(p.`QTY1`),''),IFNULL(CONCAT('-',p.`QTY2`),''),IFNULL(CONCAT('-',p.`QTY3`),''),IFNULL(CONCAT('-',p.`QTY4`),''),IFNULL(CONCAT('-',p.`QTY5`),''),'/',IFNULL(CONCAT(p.`UNIT1`),''),IFNULL(CONCAT('-',p.`UNIT2`),''),IFNULL(CONCAT('-',p.`UNIT3`),''),IFNULL(CONCAT('-',p.`UNIT4`),''),IFNULL(CONCAT('-',p.`UNIT5`),''))pac FROM(SELECT pp.`GOODS_UUID`,pp.`ID`,pp.`IS_DEFAULT`,pp.`QTY1`,pp.`QTY2`,pp.`QTY3`,pp.`QTY4`,pp.`QTY5`,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5 FROM pack_infomation pp)p ORDER BY IS_DEFAULT DESC)b GROUP BY uid)a ON goods.`UUID`=a.uid WHERE 1=1 ");

		// 拼接SQL语句查询条件，并将map集合里的查询条件放入List<Object> params集合中
		
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key))
						&& !"% %".equals(map.get(key))) {
					sql1 += "and " + key + " ? ";

					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" ORDER BY goods.CREATE_DATE DESC");
		return memory.query(sql, new BeanListHandler<GoodsDto>(GoodsDto.class), params);
	}

	/**
	 * 商品资料查询（分页）
	 * 
	 * @param map
	 *            参数
	 * @param pageNumber
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 * @return List<商品Dto实体>
	 */
	@Override
	public List<GoodsDto> getGoods(Map<String, Object> map, Integer pageNumber, Integer pageSize) {

		// 用于存放map集合里的查询条件
		List<Object> params = new ArrayList<>();
		// 用于拼接SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT stock.IN_STOCK AS sumInStock,stock.ALLOCATED_STOCK AS sumAllocationStock,	stock.PRE_ALLOCATION_STOCK AS sumPreAllocationStock, depart.DEPART_NAME as departName,dictItem.ITEM_NAME AS defaultUnitName,a.pac,goods.*FROM goods LEFT JOIN sys_depart depart ON depart.DEPART_CODE=goods.OWNER_CODE LEFT JOIN dict_item dictItem on dictItem.ITEM_CODE=goods.DEFAULT_UNIT and dictItem.DICT_CODE='JLDW' LEFT JOIN(SELECT*FROM(SELECT p.goods_uuid uid,CONCAT(IFNULL(CONCAT(p.`QTY1`),''),IFNULL(CONCAT('-',p.`QTY2`),''),IFNULL(CONCAT('-',p.`QTY3`),''),IFNULL(CONCAT('-',p.`QTY4`),''),IFNULL(CONCAT('-',p.`QTY5`),''),'/',IFNULL(CONCAT(p.`UNIT1`),''),IFNULL(CONCAT('-',p.`UNIT2`),''),IFNULL(CONCAT('-',p.`UNIT3`),''),IFNULL(CONCAT('-',p.`UNIT4`),''),IFNULL(CONCAT('-',p.`UNIT5`),''))pac FROM(SELECT pp.`GOODS_UUID`,pp.`ID`,pp.`IS_DEFAULT`,pp.`QTY1`,pp.`QTY2`,pp.`QTY3`,pp.`QTY4`,pp.`QTY5`,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5 FROM pack_infomation pp)p ORDER BY IS_DEFAULT DESC)b GROUP BY uid)a ON goods.`UUID`=a.uid LEFT JOIN (SELECT SUM(s.IN_STOCK) AS IN_STOCK,SUM(s.ALLOCATED_STOCK) AS ALLOCATED_STOCK,SUM(s.PRE_ALLOCATION_STOCK) AS PRE_ALLOCATION_STOCK,GOODS_UUID FROM stock s GROUP BY s.GOODS_UUID) stock ON stock.GOODS_UUID = goods.UUID WHERE 1=1 ");
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key))
						&& !"% %".equals(map.get(key))) {
					sql1 += "and " + key + " ? ";

					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" ORDER BY goods.CREATE_DATE DESC ");
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,?");
			params.add((pageNumber-1) * pageSize);
			params.add(pageSize);
		}
		return  memory.query(sql, new BeanListHandler<GoodsDto>(GoodsDto.class), params);
	
	}

	/**
	 * 根据条件查询商品信息条数
	 * @param map	查询条件
	 * @return	商品信息条数
	 */
	@Override
	public int getCounts(Map<String, Object> map) {
		// 用于存放map集合里的查询条件
				List<Object> params = new ArrayList<>();
				// 用于拼接SQL语句
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT COUNT(*) FROM (SELECT depart.DEPART_NAME as departName,dictItem.ITEM_NAME AS defaultUnitName,a.pac,goods.*FROM goods LEFT JOIN sys_depart depart ON depart.DEPART_CODE=goods.OWNER_CODE LEFT JOIN dict_item dictItem on dictItem.ITEM_CODE=goods.DEFAULT_UNIT and dictItem.DICT_CODE='JLDW' LEFT JOIN(SELECT*FROM(SELECT p.goods_uuid uid,CONCAT(IFNULL(CONCAT(p.`QTY1`),''),IFNULL(CONCAT('-',p.`QTY2`),''),IFNULL(CONCAT('-',p.`QTY3`),''),IFNULL(CONCAT('-',p.`QTY4`),''),IFNULL(CONCAT('-',p.`QTY5`),''),'/',IFNULL(CONCAT(p.`UNIT1`),''),IFNULL(CONCAT('-',p.`UNIT2`),''),IFNULL(CONCAT('-',p.`UNIT3`),''),IFNULL(CONCAT('-',p.`UNIT4`),''),IFNULL(CONCAT('-',p.`UNIT5`),''))pac FROM(SELECT pp.`GOODS_UUID`,pp.`ID`,pp.`IS_DEFAULT`,pp.`QTY1`,pp.`QTY2`,pp.`QTY3`,pp.`QTY4`,pp.`QTY5`,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5 FROM pack_infomation pp)p ORDER BY IS_DEFAULT DESC)b GROUP BY uid)a ON goods.`UUID`=a.uid WHERE 1=1 ");
				if (map.size() != 0) {
					String sql1 = "";
					for (String key : map.keySet()) {
						if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key))
								&& !"% %".equals(map.get(key))) {
							sql1 += "and " + key + " ? ";

							params.add(map.get(key));
						}
					}
					sql.append(sql1);
				}
				sql.append(" ) goods ");
				return memory.query(sql.toString(), new ColumnHandler<Integer>(Integer.class), params.toArray());
	}
	
	/**
	 * 商品查询（分页）
	 * 
	 * @param parameters
	 *            参数集合
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<GoodsDto> getGoods(List<Parameter> parameters, Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		String sql1 = "SELECT depart.DEPART_NAME as departName,dictItem.ITEM_NAME AS defaultUnitName,a.pac,goods.*FROM goods LEFT JOIN sys_depart depart ON depart.DEPART_CODE=goods.OWNER_CODE LEFT JOIN dict_item dictItem on dictItem.ITEM_CODE=goods.DEFAULT_UNIT and dictItem.DICT_CODE='JLDW' LEFT JOIN(SELECT*FROM(SELECT p.goods_uuid uid,CONCAT(IFNULL(CONCAT(p.`QTY1`),''),IFNULL(CONCAT('-',p.`QTY2`),''),IFNULL(CONCAT('-',p.`QTY3`),''),IFNULL(CONCAT('-',p.`QTY4`),''),IFNULL(CONCAT('-',p.`QTY5`),''),'/',IFNULL(CONCAT(p.`UNIT1`),''),IFNULL(CONCAT('-',p.`UNIT2`),''),IFNULL(CONCAT('-',p.`UNIT3`),''),IFNULL(CONCAT('-',p.`UNIT4`),''),IFNULL(CONCAT('-',p.`UNIT5`),''))pac FROM(SELECT pp.`GOODS_UUID`,pp.`ID`,pp.`IS_DEFAULT`,pp.`QTY1`,pp.`QTY2`,pp.`QTY3`,pp.`QTY4`,pp.`QTY5`,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT1 AND dit.`DICT_CODE`='JLDW')AS UNIT1,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT2 AND dit.`DICT_CODE`='JLDW')AS UNIT2,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT3 AND dit.`DICT_CODE`='JLDW')AS UNIT3,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT4 AND dit.`DICT_CODE`='JLDW')AS UNIT4,(SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE`=pp.UNIT5 AND dit.`DICT_CODE`='JLDW')AS UNIT5 FROM pack_infomation pp)p ORDER BY IS_DEFAULT DESC)b GROUP BY uid)a ON goods.`UUID`=a.uid ";
		sql.append(this.generalSelectSQL(sql1, parameters, params));
		sql.append(" ORDER BY goods.CREATE_DATE limit ?,?");
		params.add(pageNumber * pageSize);
		params.add(pageSize);
		return memory.query(sql, new BeanListHandler<GoodsDto>(GoodsDto.class), params);
	}

	/**
	 * 根据id查询数据
	 */
	@Override
	public GoodsDto getGoodsById(Long id) {
		String sql = "select goods.*,depart.depart_name from goods LEFT JOIN sys_depart depart on  goods.OWNER_CODE=depart.DEPART_CODE where  goods.id= ?";
		return memory.query(sql, new BeanHandler<GoodsDto>(GoodsDto.class), id);
	}

	/**
	 * 根据UUID查询数据
	 */
	@Override
	public GoodsDto getGoodsDtoByUUID(String uuid) {
		String sql = "select goods.*,depart.depart_name from goods LEFT JOIN sys_depart depart on  goods.OWNER_CODE=depart.DEPART_CODE where goods.uuid= ?";
		return memory.query(sql, new BeanHandler<GoodsDto>(GoodsDto.class), uuid);
	}

	/**
	 * 根据UUID查询商品资料
	 * 
	 * @param uuid
	 *            商品UUID
	 * @return 商品实体
	 */
	@Override
	public Goods getGoodsByUUID(String uuid) {
		String sql = "select * from goods where uuid=?";
		if (null != connection) {
			return memory.query(connection, sql, new BeanHandler<Goods>(Goods.class), uuid);
		}
		return memory.query(sql, new BeanHandler<Goods>(Goods.class), uuid);
	}

	/**
	 * 根据货主代码和料号查询商品信息
	 */
	@Override
	public Goods getGoodsByDepartCodeAndPn(String departCode, String pn) {
		String sql = "select * from goods where OWNER_CODE=? and pn=?";
		return memory.query(sql, new BeanHandler<Goods>(Goods.class), new Object[] { departCode, pn });
	}

	/**
	 * 查询说有商品UUID
	 * 
	 * @return 商品UUID集合
	 */
	@Override
	public List<String> getUUIDByGoodsUUID() {
		String sql = "select uuid from goods ";
		if (null != connection) {
			return memory.query(connection, sql, new ColumnListHandler<String>(String.class), new Object[] {});
		}
		return memory.query(sql, new ColumnListHandler<String>(String.class), new Object[] {});
	}

	/**
	 * 查询商品基础资料及默认包装键（含分页）
	 * 
	 * @param map
	 *            查询条件
	 * @return List<商品Dto实体>
	 */
	@Override
	public List<GoodsDto> getGoodsAndPackInfomation(Map<String, Object> map) {
		// 用于存放map集合里的查询条件
		List<Object> params = new ArrayList<>();
		// 用于拼接SQL语句
		StringBuffer sql = new StringBuffer();

		sql.append(
				"SELECT depart.DEPART_NAME AS departName, dictItem.ITEM_NAME AS defaultUnitName, a.pac, goods.* FROM goods LEFT JOIN sys_depart depart ON depart.DEPART_CODE = goods.OWNER_CODE LEFT JOIN dict_item dictItem ON dictItem.ITEM_CODE = goods.DEFAULT_UNIT AND dictItem.DICT_CODE = 'JLDW' LEFT JOIN ( SELECT * FROM ( SELECT p.goods_uuid uid, CONCAT( IFNULL(CONCAT(p.`QTY1`), ''), IFNULL(CONCAT('-', p.`QTY2`), ''), IFNULL(CONCAT('-', p.`QTY3`), ''), IFNULL(CONCAT('-', p.`QTY4`), ''), IFNULL(CONCAT('-', p.`QTY5`), ''), 	'/', IFNULL(CONCAT(p.`UNIT1`), ''), IFNULL(CONCAT('-', p.`UNIT2`), ''), IFNULL(CONCAT('-', p.`UNIT3`), ''), IFNULL(CONCAT('-', p.`UNIT4`), ''), IFNULL(CONCAT('-', p.`UNIT5`), '') ) pac FROM ( SELECT pp.`GOODS_UUID`, pp.`ID`, pp.`IS_DEFAULT`, 	pp.`QTY1`, pp.`QTY2`, pp.`QTY3`, pp.`QTY4`, pp.`QTY5`, ( SELECT dit.item_name 	FROM 	dict_item dit 	WHERE dit.`ITEM_CODE` = pp.UNIT1 AND dit.`DICT_CODE` = 'JLDW' ) AS UNIT1, ( SELECT 	dit.item_name 	FROM 	dict_item dit 	WHERE 	dit.`ITEM_CODE` = pp.UNIT2 AND dit.`DICT_CODE` = 'JLDW' ) AS UNIT2, ( SELECT dit.item_name FROM dict_item dit WHERE dit.`ITEM_CODE` = pp.UNIT3 	AND dit.`DICT_CODE` = 'JLDW' ) AS UNIT3, ( SELECT dit.item_name FROM dict_item dit 	WHERE dit.`ITEM_CODE` = pp.UNIT4 AND dit.`DICT_CODE` = 'JLDW' ) AS UNIT4, ( SELECT 	dit.item_name FROM dict_item dit 	WHERE 	dit.`ITEM_CODE` = pp.UNIT5 	AND dit.`DICT_CODE` = 'JLDW' ) AS UNIT5 FROM 	pack_infomation pp ) p	WHERE p.IS_DEFAULT=1 ORDER BY IS_DEFAULT DESC ) b GROUP BY uid ) a ON goods.`UUID` = a.uid WHERE 1 = 1 ");

		// 拼接SQL语句查询条件，并将map集合里的查询条件放入List<Object> params集合中
		Integer pageNumber = null;
		Integer pageSize = null;
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				if ("pageSize".equals(key)) {
					pageSize = (Integer) map.get(key);
					map.put(key, null);
				}
				if ("pageNumber".equals(key)) {
					pageNumber = (Integer) map.get(key);
					map.put(key, null);
				}
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key))
						&& !"% %".equals(map.get(key))) {
					sql1 += "and " + key + " ? ";

					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		sql.append(" ORDER BY goods.CREATE_DATE DESC");
		if (pageNumber != null && pageSize != null) {
			sql.append(" limit ?,?");
			params.add(pageNumber * pageSize);
			params.add(pageSize);
		}
		return memory.query(sql, new BeanListHandler<GoodsDto>(GoodsDto.class), params);
	}

}
