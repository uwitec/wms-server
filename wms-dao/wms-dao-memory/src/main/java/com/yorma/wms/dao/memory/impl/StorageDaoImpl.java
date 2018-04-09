 package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.api.StorageDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Storage;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:库区Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月10日
 * @version 1.00.00
 * @history:
 *
 */
public class StorageDaoImpl extends BaseDaoImpl<Storage> implements StorageDao {

	/**
	 * 库区添加
	 */
	public int saveStorage(Storage storage) {
		if (storage.getId()==null) {
			return memory.create(Storage.class, storage);
		} else {
			return memory.update(Storage.class, storage);
		}
		
	}
	
	/**
	 * 库区查询操作
	 * @return：查询（所有/符合条件）的数据
	 */
	@Override
	public List<Storage> getStorage(Map<String, Object> map) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("select * from storage ");
		if (map.size() != 0) {
			boolean flag = false;
			String sql1 = "";
			for (String key : map.keySet()) {
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
			System.out.println("Region-sql:"+sql);
		}
		List<Storage> lir=memory.query(sql, new BeanListHandler<Storage>(Storage.class), params);
		return lir;
	}
	
	/**
	 * 获取最大ID
	 */
	public Long geiMaxId() {
		String sql="select max(id) from storage ";
		return memory.query(sql, new ColumnHandler<Long>(Long.class), new Object[]{});
	}
	
	/**
	 * 根据库区代码获取数据
	 * @param code
	 * @return		库区实体
	 */
	@Override
	public Storage checkCode(String code,String warehouseCode) {
		String sql="select * from storage where code=? and WAREHOUSE_CODE=?";
		return memory.query(sql, new BeanHandler<Storage>(Storage.class), new Object[]{code,warehouseCode});
	}
	
	/**
	 * 根据仓库代码查询库区信息条数
	 * @param warehouseCode	仓库嗲吗
	 * @return	库区信息条数
	 */
	@Override
	public Integer getStorageCountsByWarehouseCode(String warehouseCode) {
		String sql="SELECT COUNT(ID) FROM `storage` WHERE WAREHOUSE_CODE=?";
		return memory.query(sql, new ColumnHandler<Integer>(Integer.class), warehouseCode);
	}
	
	/**
	 * 根据库区ID查询库区代码
	 * @param id	库区ID
	 * @return	库区代码
	 */
	@Override
	public String getStorageCodeById(Long id) {
		String sql="SELECT `CODE` FROM `storage` WHERE ID=?";
		return memory.query(sql, new ColumnHandler<String>(String.class), id);
	}
	
}
