package com.yorma.wms.entity.dto;

import java.util.Map;

public class MapParameters {

	/** 当前页 */
	private int pageNumber;
	
	/** 每页显示条数 */
	private int pageSize;
	
	/** 参数集合(map形式) */
	private Map<String, Object> paramters;

	/** 是否启用分页 */
	private Boolean isEnable;
	
	/**
	 * 无参构造
	 */
	public MapParameters() {
	}

	/**
	 * 有参构造
	 * @param pageNumber 当前页 
	 * @param pageSize 每页显示条数 
	 */
	public MapParameters(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/**
	 * 有参构造
	 * @param paramters
	 */
	public MapParameters(Map<String, Object> paramters) {
		super();
		this.paramters = paramters;
	}

	/**
	 * 有参构造
	 * @param pageNumber 当前页 
	 * @param pageSize 每页显示条数 
	 * @param paramters 参数集合(map形式)
	 */
	public MapParameters(int pageNumber, int pageSize, Map<String, Object> paramters) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.paramters = paramters;
	}

	/**
	 * GET 当前页 
	 * @return 当前页 
	 */
	public int getPageNumber() {
		isEnable();
		return pageNumber;
	}

	/**
	 * SET 当前页 
	 * @param pageNumber 当前页 
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * GET 每页显示条数
	 * @return 每页显示条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * SET 每页显示条数
	 * @param pageSize 每页显示条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * GET 参数集合(map形式)
	 * @return 参数集合(map形式)
	 */
	public Map<String, Object> getParamters() {
		return paramters;
	}

	/**
	 * SET 参数集合(map形式)
	 * @param paramters 参数集合(map形式)
	 */
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}

	/**
	 * GET 是否启用分页
	 * @return true:
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * 判断是否启用分页
	 */
	private void isEnable(){
		isEnable = pageNumber != 0 ? true : false;
		if (!isEnable) {
			return;
		}
		isEnable = pageSize != 0 ? true : false;
		return;
	}
	
}
