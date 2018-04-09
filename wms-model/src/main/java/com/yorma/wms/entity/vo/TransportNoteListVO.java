package com.yorma.wms.entity.vo;

import com.yorma.wms.entity.TransportNoteList;

public class TransportNoteListVO extends TransportNoteList {

	/**
	 * 计数(qty*n)
	 */
	private String countQty;
	
	/**
	 * 总数
	 */
	private Integer sumQty;
	
	/**
	 * 组织机构名称
	 */
	private String departName;

	/**
	 * GET 计数(qty*n)
	 * @return 计数(qty*n)
	 */
	public String getCountQty() {
		return countQty;
	}

	/**
	 * SET 计数(qty*n)
	 * @param countQty 计数(qty*n)
	 */
	public void setCountQty(String countQty) {
		this.countQty = countQty;
	}

	/**
	 * GET 总数
	 * @return 总数
	 */
	public Integer getSumQty() {
		return sumQty;
	}

	/**
	 * SET 总数
	 * @param sumQty 总数
	 */
	public void setSumQty(Integer sumQty) {
		this.sumQty = sumQty;
	}
	
	/**
	 * GET 组织机构名称
	 * @return 组织机构名称
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * SET 组织机构名称
	 * @param departName 组织机构名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Override
	public String toString() {
		return "TransportNoteListVO [getCountQty()=" + getCountQty() + ", getSumQty()=" + getSumQty()
				+ ", getDepartName()=" + getDepartName() + ", getId()=" + getId() + ", getUuid()=" + getUuid()
				+ ", getTransportNoteNo()=" + getTransportNoteNo() + ", getOwnerCode()=" + getOwnerCode()
				+ ", getPlUuid()=" + getPlUuid() + ", getPalletNo()=" + getPalletNo() + ", getPackagingNo()="
				+ getPackagingNo() + ", getPackagingKey()=" + getPackagingKey() + ", getMu()=" + getMu() + ", getPn()="
				+ getPn() + ", getDeliveryQty()=" + getDeliveryQty() + ", getGoodsName()=" + getGoodsName()
				+ ", getDeliveryNoteNo()=" + getDeliveryNoteNo() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
