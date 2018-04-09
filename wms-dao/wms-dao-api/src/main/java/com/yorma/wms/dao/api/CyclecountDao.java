package com.yorma.wms.dao.api;

import java.util.List;
import java.util.Map;

import com.yorma.wms.dao.base.api.BaseDao;
import com.yorma.wms.entity.Cyclecount;


/**
 * 盘点信息Dao 接口类
 * 
 * @Copyright: Copyright (c) 2017 Yorma All Rights Reserved
 * @Company: 济南悦码信息科技有限公司
 * @author su 
 * @version 1.00.00
 * @date 2017年12月06日
 * @version V1.0
 */
public interface CyclecountDao extends BaseDao<Cyclecount> {

	/**
	 * 根据条件查询盘点信息
	 * @param parames	参数集合
	 * @return	盘点信息实体集合
	 */
	List<Cyclecount> getCyclecount(Map<String, Object> parames);
	
	/**
	 * 根据条件查询盘点信息（分页）
	 * @param parames	参数集合
	 * @param pageNumber	当前页
	 * @param pageSize	每页显示条数
	 * @return 盘点信息实体集合
	 */
	List<Cyclecount> getCyclecount(Map<String, Object> parames,Integer pageNumber,Integer pageSize);
	
	/**
	 * 根据条件获取盘点信息参数个数
	 * @param parames	参数集合
	 * @return	盘点信息参数个数
	 */
	Integer getCounts(Map<String, Object> parames);
	
	/**
	 * 根据盘点单号查询盘点信息条数
	 * @param cyclecountNo	盘点单号
	 * @return	盘点信息条数
	 */
	int getCountCyclecountByCyclecountNo(String cyclecountNo);
	
	/**
	 * 根据盘点单号查询盘点单状态
	 * @param cyclecountNo	盘点单号
	 * @return	盘点单状态
	 */
	Integer getStatusByCyclecountNo(String cyclecountNo);
	
	/**
	 * 根据盘点单号修改盘点单状态
	 * @param cyclecountNo	盘点单号
	 * @param status	盘点单状态
	 * @return	影响行数
	 */
	int updateStatusByCyclecountNo(String cyclecountNo,int status);
	
	/**
	 * 根据盘点单号查询盘点信息
	 * @param cyclecountNo	盘点单号
	 * @return	盘点信息实体
	 */
	Cyclecount getCyclecountByCyclecountNo(String cyclecountNo);
	
	/**
	 * 根据盘点单号删除盘点信息
	 * @param cyclecountNo	盘点单号
	 * @return	影响行数
	 */
	int removeCyclecountByCyclecountNo(String cyclecountNo);
	
}
