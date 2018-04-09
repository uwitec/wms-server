package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.LocationDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Location;
import com.yorma.wms.entity.dto.LocationDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:储位、库位Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月9日
 * @version 1.00.00
 * @history:
 *
 */
public class LocationDaoImpl extends BaseDaoImpl<Location> implements LocationDao {

	/**
	 * 储位添加（修改）
	 */
	public int saveLocation(Location location) {
		if (location.getId()==null) {
			return memory.create(Location.class, location);
		}else {
			return memory.update(Location.class, location);
		}
		
	}
	
	
	/**
	 * 储位、库位查询操作
	 */
	public List<Location> getLocation(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("select * from location ");
		if (map.size() != 0) {
			boolean flag = false;
			String sql1 = "";
			for (String key : map.keySet()) {
				//判断value值不为null和空字符串  、拼接sql语句
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
					if (!flag ) {
						sql1 += " where ";
						flag=true;
					} else {
						sql1 += " and ";
					}
					sql1 +=  key + " ? ";
					params.add(map.get(key));
				}
			}
			sql.append(sql1);
			sql.append(" ORDER BY `CODE` ");
		}
		return memory.query(sql, new BeanListHandler<Location>(Location.class), params);
	}

	/**
	 * 查询储位信息及仓库全称
	 */
	@Override
	public List<LocationDto> getLocationAndWarehouse(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("select warehouse.FULL_NAME,location.* from location ,warehouse where location.WAREHOUSE_CODE=warehouse.CODE");
		if (map.size() != 0) {
			for (String key : map.keySet()) {
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
					sql.append(" and "+key+"=? ");
					params.add(map.get(key));
				}
			}
		}
		return memory.query(sql, new BeanListHandler<LocationDto>(LocationDto.class), params);
	}
	
	/**
	 * 获取最大ID
	 */
	public Long getMaxId() {
		String sql="select max(id) from location";
		return memory.query(sql, new ColumnHandler<Long>(Long.class), new Object[]{});
	}
	
	/**
	 * 根据储位代码、库区代码、仓库代码查询储位信息
	 */
	public Location checkCode(String code,String storageCode, String warehouseCode) {
		String sql="select * from location where code=? and  STORAGE_CODE=? and WAREHOUSE_CODE=?";
		return memory.query(sql, new BeanHandler<Location>(Location.class), new Object[]{ code, storageCode, warehouseCode});
	}
	
	/**
	 * 查询储位信息中的所有仓库，库区，储位
	 * @return	List<储位信息实体>
	 */
	@Override
	public List<Location> getWarehouse() {
		String sql="select WAREHOUSE_CODE,STORAGE_CODE,code from location";
		
		if (null !=connection) {
			return memory.query(connection, sql, new BeanListHandler<Location>(Location.class), new Object[]{});
		}
		return memory.query( sql, new BeanListHandler<Location>(Location.class), new Object[]{});
	}
	
	/**
	 * 根据库区代码查询储位信息条数
	 * @param storageCode	库区代码
	 * @return	储位条数
	 */
	@Override
	public Integer getLocationCountsByStorageCode(String storageCode) {
		String sql="SELECT COUNT(ID) FROM location WHERE STORAGE_CODE=?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), storageCode);
	}
	
	/**
	 * 根据储位ID查询储位代码
	 * @param id	储位ID
	 * @return	储位代码
	 */
	@Override
	public String getLocationCodeById(Long id) {
		String sql="SELECT `CODE` FROM location WHERE ID=?";
		return memory.query(sql, new ColumnHandler<String>(String.class), id);
	}
	
}
