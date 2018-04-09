package com.yorma.wms.dao.memory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yorma.wms.dao.api.WarehouseDao;
import com.yorma.wms.dao.memory.base.BaseDaoImpl;
import com.yorma.wms.entity.Warehouse;
import com.yorma.wms.entity.dto.WarehouseDto;

import cn.ffcs.memory.BeanHandler;
import cn.ffcs.memory.BeanListHandler;
import cn.ffcs.memory.ColumnHandler;

/**
 * 
 * @Description:仓库Dao层实现
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author 苏飚 2017年8月10日
 * @version 1.00.00
 * @history:
 *
 */
public class WarehouseDaoImpl extends BaseDaoImpl<Warehouse>implements WarehouseDao {

	private static final Logger logger = LoggerFactory.getLogger(Warehouse.class);
	
	public int saveWarehouse(Warehouse warehouse){
		if (warehouse.getId()==null) {
			return memory.create(Warehouse.class, warehouse);
		}else {
			return memory.update(Warehouse.class, warehouse);
		}
		
	}
	
	/**
	 * 仓库查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	@Override
	public List<WarehouseDto> getWarehouse(Map<String, Object> map) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<>();

		sql.append("select w.*,s.DEPART_NAME from warehouse w LEFT JOIN sys_depart s on w.OWNER_CODE=s.DEPART_CODE where 1=1");
		if (map.size() != 0) {
			String sql1 = "";
			for (String key : map.keySet()) {
				// 判断value值不为null和空字符串 、拼接sql语句
				if (map.get(key) != null && !"".equals(map.get(key)) && !"%%".equals(map.get(key)) && !"% %".equals(map.get(key))) {
					sql1 += " and " + key + " ? ";
					params.add(map.get(key));
				}
			}
			sql.append(sql1);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("WarehouseDaoImpl: [getWarehouse] 方法sql：{}", sql.toString());
			
		}
		List<WarehouseDto> liw = memory.query(sql, new BeanListHandler<WarehouseDto>(WarehouseDto.class), params);
		return liw;
	}

	/**
	 * 根据仓库代码或者仓库名称查询数据
	 * @param code
	 * @param name
	 * @return
	 */
	@Override
	public List<WarehouseDto> getWarehouseByCode(String code,String type) {
		StringBuffer sql=new StringBuffer();
		List<Object> params = new ArrayList<>();
		sql.append("select depart.DEPART_NAME, warehouse.* from warehouse left join sys_depart depart on warehouse.OWNER_CODE=depart.DEPART_CODE where IS_ENABLE=true");
		if (null != type && !"".endsWith(type)) {
			sql.append(" and TYPE != ? ");
			params.add(type);
		}
		if (null != code && !"".endsWith(code)) {
			sql.append(" and code like '%"+code+"%' or FULL_NAME like '%"+code+"%'");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("WarehouseDaoImpl: [getWarehouseByCode] 方法sql：{}", sql.toString());
			logger.debug("WarehouseDaoImpl: [getWarehouseByCode] 方法参数code：{}", code);
			logger.debug("WarehouseDaoImpl: [getWarehouseByCode] 方法参数type：{}", type);
		}
		return memory.query(sql.toString(), new BeanListHandler<WarehouseDto>(WarehouseDto.class), params.toArray());
	}
	
	/**
	 * 仓库id查询操作
	 * 
	 * @return：查询（所有/符合条件）的数据
	 */
	@Override
	public WarehouseDto getWarehouseById(Long id) {
		String sql = "select warehouse.*,depart.DEPART_NAME,depart.DEPART_CODE from warehouse LEFT JOIN sys_depart depart on warehouse.OWNER_CODE=depart.DEPART_CODE where warehouse.ID=?";
		if (logger.isDebugEnabled()) {
			logger.debug("WarehouseDaoImpl: [getWarehouseById] 方法sql：{}", sql.toString());
			logger.debug("WarehouseDaoImpl: [getWarehouseById] 方法参数id：{}", id);
		}
		return memory.query(sql, new BeanHandler<WarehouseDto>(WarehouseDto.class), id);
	}

	/**
	 * 获取最大id用于生成CODE
	 */
	public Long getMaxId() {
		String sql="select max(id) from warehouse";

		return memory.query(sql, new ColumnHandler<Long>(Long.class), new Object[]{});
	}
	
	@Override
	public Warehouse checkCode(String code) {
		String sql="select * from warehouse where code=?";
		if (logger.isDebugEnabled()) {
			logger.debug("WarehouseDaoImpl: [checkCode] 方法sql：{}", sql.toString());
			logger.debug("WarehouseDaoImpl: [checkCode] 方法参数code：{}", code);
		}
		return memory.query(sql, new BeanHandler<Warehouse>(Warehouse.class) , code);
	}
	
	/**
	 * 根据仓库代码查询仓库ID
	 * @param code	仓库代码
	 * @return	仓库ID
	 */
	@Override
	public Long getWarehouseIdByCode(String code) {
		String sql="SELECT ID FROM warehouse WHERE `CODE`=?";
		return memory.query(sql, new ColumnHandler<Long>(Long.class), code);
	}
	
	/**
	 * 根据仓库ID查询仓库代码
	 * @param id	仓库ID
	 * @return	仓库代码
	 */
	@Override
	public String getWarehouseCodeById(Long id) {
		String sql="SELECT `CODE`ID FROM warehouse WHERE ID=?";
		return memory.query(sql, new ColumnHandler<String>(String.class), id);
	}
	
}
