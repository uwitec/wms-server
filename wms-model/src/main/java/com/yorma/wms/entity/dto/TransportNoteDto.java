package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.TransportNote;

public class TransportNoteDto extends TransportNote {

	/** 承运人名称 */
	private String departName;
	
	/** 收货人名称 */
	private String consigneeCompany;

	/**
	 * 获取承运人名称
	 * @return	承运人名称
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 设置承运人名称
	 * @param departName	承运人名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * 获取收货人名称
	 */
	public String getConsigneeCompany() {
		return consigneeCompany;
	}

	/**
	 * 设置收货人名称
	 */
	public void setConsigneeCompany(String consigneeCompany) {
		this.consigneeCompany = consigneeCompany;
	}

	@Override
	public String toString() {
		return "TransportNoteDto [getDepartName()=" + getDepartName() + ", getConsigneeCompany()="
				+ getConsigneeCompany() + ", getId()=" + getId() + ", getTransportNoteNo()=" + getTransportNoteNo()
				+ ", getWarehouse()=" + getWarehouse() + ", getTransportCode()=" + getTransportCode()
				+ ", getTruckType()=" + getTruckType() + ", getTruckNumber()=" + getTruckNumber()
				+ ", getConsigneeCode()=" + getConsigneeCode() + ", getAddress()=" + getAddress() + ", getRemark()="
				+ getRemark() + ", getConsigneeBy()=" + getConsigneeBy() + ", getConsigneePhone()="
				+ getConsigneePhone() + ", getConsigneeDate()=" + getConsigneeDate() + ", getConsigneeName()="
				+ getConsigneeName() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()=" + getCreateDate()
				+ ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateDate()=" + getLastUpdateDate()
				+ ", getStatus()=" + getStatus() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
