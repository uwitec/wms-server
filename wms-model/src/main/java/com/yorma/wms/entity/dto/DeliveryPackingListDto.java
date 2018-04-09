package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.DeliveryPackingList;

public class DeliveryPackingListDto extends DeliveryPackingList {

	/** 货主代码 */
	private String departCode;
	/** 货主名称 */
	private String departName;

	/**
	 * 获取货主代码
	 * 
	 * @return 货主代码
	 */
	public String getDepartCode() {
		return departCode;
	}

	/**
	 * 设置货主代码
	 * 
	 * @param departCode
	 *            货主代码
	 */
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	/**
	 * 获取货主明成
	 * 
	 * @return 货主名称
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 设置货主名称
	 * 
	 * @param departName
	 *            货主名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Override
	public String toString() {
		return "DeliveryPackingListDto [getDepartCode()=" + getDepartCode() + ", getDepartName()=" + getDepartName()
				+ ", getId()=" + getId() + ", getUuid()=" + getUuid() + ", getAnsUuid()=" + getAnsUuid()
				+ ", getDeliveryNoteNo()=" + getDeliveryNoteNo() + ", getGoodsUuid()=" + getGoodsUuid() + ", getPn()="
				+ getPn() + ", getGoodsName()=" + getGoodsName() + ", getGoodsModel()=" + getGoodsModel()
				+ ", getItem()=" + getItem() + ", getEmsNo()=" + getEmsNo() + ", getSku()=" + getSku() + ", getMu()="
				+ getMu() + ", getWarehouse()=" + getWarehouse() + ", getStorage()=" + getStorage() + ", getLocation()="
				+ getLocation() + ", getStockUuid()=" + getStockUuid() + ", getDeliveryQty()=" + getDeliveryQty()
				+ ", getPackagingNo()=" + getPackagingNo() + ", getPackagingKey()=" + getPackagingKey()
				+ ", getPackagingGrossWeight()=" + getPackagingGrossWeight() + ", getPackagingNetWeight()="
				+ getPackagingNetWeight() + ", getPackagingVolume()=" + getPackagingVolume() + ", getPalletNo()="
				+ getPalletNo() + ", getPalletGrossWeight()=" + getPalletGrossWeight() + ", getPalletNetWeiht()="
				+ getPalletNetWeiht() + ", getPalletArea()=" + getPalletArea() + ", getIsQualifd()=" + getIsQualifd()
				+ ", getCreateBy()=" + getCreateBy() + ", getCreateDate()=" + getCreateDate() + ", getStatus()="
				+ getStatus() + ", getDeliveryLotNo()=" + getDeliveryLotNo() + ", getTransportNoteNo()="
				+ getTransportNoteNo() + ", getMergeLotNo()=" + getMergeLotNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
