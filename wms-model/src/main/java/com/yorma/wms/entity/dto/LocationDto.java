package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.Location;


public class LocationDto extends Location {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5191356803391176369L;
	
	
	/** 仓库全称  */
	private String FullName;

	/**
	 * 获取仓库全称
	 * @return	获取仓库简称
	 */
	public String getFullName() {
		return FullName;
	}

	/**
	 * 设置长裤全称
	 * @param fullName	仓库全称
	 */
	public void setFullName(String fullName) {
		FullName = fullName;
	}

	@Override
	public String toString() {
		return "LocationDto [getFullName()=" + getFullName() + ", getId()=" + getId() + ", getCode()=" + getCode()
				+ ", getBarcode()=" + getBarcode() + ", getStorageCode()=" + getStorageCode() + ", getWarehouseCode()="
				+ getWarehouseCode() + ", getLoadWeight()=" + getLoadWeight() + ", getVolume()=" + getVolume()
				+ ", getWidth()=" + getWidth() + ", getLength()=" + getLength() + ", getHeight()=" + getHeight()
				+ ", getType()=" + getType() + ", getKind()=" + getKind() + ", getTag()=" + getTag()
				+ ", getIsEnable()=" + getIsEnable() + ", getDescription()=" + getDescription() + ", getMemo()="
				+ getMemo() + ", getCreateBy()=" + getCreateBy() + ", getLastUpdateBy()=" + getLastUpdateBy()
				+ ", getCreateDate()=" + getCreateDate() + ", getLastUpdateDate()=" + getLastUpdateDate()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}
