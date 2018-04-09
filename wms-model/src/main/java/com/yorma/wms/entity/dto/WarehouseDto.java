package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.Warehouse;

/**
 * 仓库Dto
 * 
 * @author subiao 2017年9月20日
 */
public class WarehouseDto extends Warehouse {

	/** 货主名 */
	private String departName;

	/**
	 * 获取货主名
	 * 
	 * @return 货主名
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 设置货主名
	 * 
	 * @param departName
	 *            货主名
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Override
	public String toString() {
		return "WarehouseDto [ getDepartName()=" + getDepartName() + ", getId()=" + getId() + ", getCode()=" + getCode()
				+ ", getName()=" + getName() + ", getFullName()=" + getFullName() + ", getType()=" + getType()
				+ ", getOwnerCode()=" + getOwnerCode() + ", getAddress()=" + getAddress() + ", getContacts()="
				+ getContacts() + ", getPhones()=" + getPhones() + ", getFax()=" + getFax() + ", getDescription()="
				+ getDescription() + ", getMemo()=" + getMemo() + ", getIsEnable()=" + getIsEnable()
				+ ", getCreateBy()=" + getCreateBy() + ", getCreateDate()=" + getCreateDate() + ", getLastUpdateBy()="
				+ getLastUpdateBy() + ", getLastUpdateDate()=" + getLastUpdateDate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
