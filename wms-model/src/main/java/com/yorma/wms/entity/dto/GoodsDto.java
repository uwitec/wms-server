package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.Goods;

/**
 * 商品Dto
 * 
 * @author subiao 2017年9月19日
 */
public class GoodsDto extends Goods {

	/** 货主名称 */
	private String departName;

	/** 包装键 */
	private String pac;
	/** 默认计量单位 */
	private String defaultUnitName;
	/** 总在库量 */
	private Integer sumInStock;
	/** 总预分配量 */
	private Integer sumPreAllocationStock;
	/** 总已分配量 */
	private Integer sumAllocationStock;

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

	/**
	 * 获取包装键
	 * 
	 * @return 包装键
	 */
	public String getPac() {
		return pac;
	}

	/**
	 * 设置包装键
	 * 
	 * @param pac
	 *            包装键
	 */
	public void setPac(String pac) {
		this.pac = pac;
	}

	/**
	 * 获取默认计量单位
	 * 
	 * @return 默认计量单位
	 */
	public String getDefaultUnitName() {
		return defaultUnitName;
	}

	/**
	 * 设置默认计量单位
	 * 
	 * @param defaultUnitName
	 *            默认计量单位
	 */
	public void setDefaultUnitName(String defaultUnitName) {
		this.defaultUnitName = defaultUnitName;
	}

	/**
	 * 获取总在库量
	 * @return	总在库量
	 */
	public Integer getSumInStock() {
		return sumInStock;
	}

	/**
	 * 设置总在库量
	 * @param sumInStock 总在库量
	 */
	public void setSumInStock(Integer sumInStock) {
		this.sumInStock = sumInStock;
	}

	/**
	 * 获取总预分配量
	 * @return	总预分配量
	 */
	public Integer getSumPreAllocationStock() {
		return sumPreAllocationStock;
	}

	/**
	 * 设置总预分配量
	 * @param sumPreAllocationStock 总预分配量
	 */
	public void setSumPreAllocationStock(Integer sumPreAllocationStock) {
		this.sumPreAllocationStock = sumPreAllocationStock;
	}

	/**
	 * 获取总已分配量
	 * @return	总已分配量
	 */
	public Integer getSumAllocationStock() {
		return sumAllocationStock;
	}

	/**
	 * 设置总已分配量
	 * @param sumAllocationStock 设置总已分配量
	 */
	public void setSumAllocationStock(Integer sumAllocationStock) {
		this.sumAllocationStock = sumAllocationStock;
	}

	@Override
	public String toString() {
		return "GoodsDto [getDepartName()=" + getDepartName() + ", getPac()=" + getPac() + ", getDefaultUnitName()="
				+ getDefaultUnitName() + ", getSumInStock()=" + getSumInStock() + ", getSumPreAllocationStock()="
				+ getSumPreAllocationStock() + ", getSumAllocationStock()=" + getSumAllocationStock() + ", getId()="
				+ getId() + ", getUuid()=" + getUuid() + ", getSku()=" + getSku() + ", getPn()=" + getPn()
				+ ", getBarcode()=" + getBarcode() + ", getOwnerCode()=" + getOwnerCode() + ", getName()=" + getName()
				+ ", getModel()=" + getModel() + ", getDefaultUnit()=" + getDefaultUnit() + ", getStatus()="
				+ getStatus() + ", getLength()=" + getLength() + ", getWidth()=" + getWidth() + ", getHeight()="
				+ getHeight() + ", getStorageType()=" + getStorageType() + ", getCategory()=" + getCategory()
				+ ", getStockLowest()=" + getStockLowest() + ", getStockHighest()=" + getStockHighest()
				+ ", getExpWarnDays()=" + getExpWarnDays() + ", getShelfLifeDays()=" + getShelfLifeDays()
				+ ", getSalePrice()=" + getSalePrice() + ", getPurchasePrice()=" + getPurchasePrice()
				+ ", getDeliveryMode()=" + getDeliveryMode() + ", getUnpackingMode()=" + getUnpackingMode()
				+ ", getInventoryChecking()=" + getInventoryChecking() + ", getDefaultItemNo()=" + getDefaultItemNo()
				+ ", getDefaultEmsNo()=" + getDefaultEmsNo() + ", getIsEnable()=" + getIsEnable() + ", getMemo()="
				+ getMemo() + ", getChargingStandand()=" + getChargingStandand() + ", getAttachments()="
				+ getAttachments() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()=" + getCreateDate()
				+ ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateDate()=" + getLastUpdateDate()
				+ ", getWeight()=" + getWeight() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
