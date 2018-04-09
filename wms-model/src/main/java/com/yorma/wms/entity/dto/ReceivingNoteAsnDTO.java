package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.ReceivingNoteAsn;

/**
 * 入库预告明细DTO
 * @author su
 * 2018年3月23日
 */
public class ReceivingNoteAsnDTO extends ReceivingNoteAsn {

	/** 货主代码(海关10位编码或社会统一代码) */
	private String ownerCode;
	
	/**
	 * 获取货主代码(海关10位编码或社会统一代码)
	 * 
	 * @return 货主代码(海关10位编码或社会统一代码)
	 */
	public String getOwnerCode() {
		return this.ownerCode;
	}

	/**
	 * 设置货主代码(海关10位编码或社会统一代码)
	 * 
	 * @param ownerCode
	 *            货主代码(海关10位编码或社会统一代码)
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	@Override
	public String toString() {
		return "ReceivingNoteAsnDTO [ownerCode=" + ownerCode + ", getOwnerCode()=" + getOwnerCode() + ", getId()="
				+ getId() + ", getUuid()=" + getUuid() + ", getReceivingNoteNo()=" + getReceivingNoteNo()
				+ ", getGoodsUuid()=" + getGoodsUuid() + ", getItem()=" + getItem() + ", getEmsNo()=" + getEmsNo()
				+ ", getSku()=" + getSku() + ", getMu()=" + getMu() + ", getPn()=" + getPn() + ", getGoodsName()="
				+ getGoodsName() + ", getGoodsModel()=" + getGoodsModel() + ", getGrossWeight()=" + getGrossWeight()
				+ ", getNetWeight()=" + getNetWeight() + ", getVolume()=" + getVolume() + ", getBatchAttribute()="
				+ getBatchAttribute() + ", getCurrency()=" + getCurrency() + ", getPackagingKey()=" + getPackagingKey()
				+ ", getExpectQty()=" + getExpectQty() + ", getPackagingQty()=" + getPackagingQty()
				+ ", getAcceptanceQty()=" + getAcceptanceQty() + ", getCreateBy()=" + getCreateBy()
				+ ", getCreateDate()=" + getCreateDate() + ", getAmount()=" + getAmount() + ", getInvoiceNo()="
				+ getInvoiceNo() + ", getLotNo()=" + getLotNo() + ", getProductionDate()=" + getProductionDate()
				+ ", getExpiryDate()=" + getExpiryDate() + ", getOrderNo()=" + getOrderNo() + ", getPo()=" + getPo()
				+ ", getPackagingNo()=" + getPackagingNo() + ", getIsQualifd()=" + getIsQualifd() + ", getStatus()="
				+ getStatus() + ", getUnitPrice()=" + getUnitPrice() + ", getMergeLotNo()=" + getMergeLotNo()
				+ ", getIsMerge()=" + getIsMerge() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
