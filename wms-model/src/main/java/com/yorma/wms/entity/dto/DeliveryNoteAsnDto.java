package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.DeliveryNoteAsn;

/**
 * 出货明细Dto
 * 
 * @author subiao 2017年10月11日
 */
public class DeliveryNoteAsnDto extends DeliveryNoteAsn {

	/** 拣货总数量 */
	private Integer deliveryQtySum;
	/** 总预分配量 */
	private Integer expectQtySum;

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

	/**
	 * 获取拣货总数量
	 * 
	 * @return 拣货总数量
	 */
	public Integer getDeliveryQtySum() {
		if (deliveryQtySum == null) {
			deliveryQtySum = 0;
		}
		return deliveryQtySum;
	}

	/**
	 * 设置拣货总数量
	 * 
	 * @param deliveryQtySum
	 *            拣货总数量
	 */
	public void setDeliveryQtySum(Integer deliveryQtySum) {
		this.deliveryQtySum = deliveryQtySum;
	}

	/**
	 * 获取总预分配量
	 * 
	 * @return 总预分配量
	 */
	public Integer getExpectQtySum() {
		if (expectQtySum == null) {
			expectQtySum = 0;
		}
		return expectQtySum;
	}

	/**
	 * 设置总预分配量
	 * 
	 * @param expectQtySum
	 *            总预分配量
	 */
	public void setExpectQtySum(Integer expectQtySum) {
		this.expectQtySum = expectQtySum;
	}

	@Override
	public String toString() {
		return "DeliveryNoteAsnDto [getDeliveryQtySum()=" + getDeliveryQtySum() + ", getExpectQtySum()="
				+ getExpectQtySum() + ", getId()=" + getId() + ", getUuid()=" + getUuid() + ", getDeliveryNoteNo()="
				+ getDeliveryNoteNo() + ", getGoodsUuid()=" + getGoodsUuid() + ", getItem()=" + getItem()
				+ ", getEmsNo()=" + getEmsNo() + ", getSku()=" + getSku() + ", getMu()=" + getMu() + ", getPn()="
				+ getPn() + ", getExpectQty()=" + getExpectQty() + ", getAllocationQty()=" + getAllocationQty()
				+ ", getGoodsName()=" + getGoodsName() + ", getGoodsModel()=" + getGoodsModel() + ", getGrossWeight()="
				+ getGrossWeight() + ", getNetWeight()=" + getNetWeight() + ", getVolume()=" + getVolume()
				+ ", getBatchAttribute()=" + getBatchAttribute() + ", getCurrency()=" + getCurrency()
				+ ", getPackagingKey()=" + getPackagingKey() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()="
				+ getCreateDate() + ", getAmount()=" + getAmount() + ", getInvoiceNo()=" + getInvoiceNo()
				+ ", getLotNo()=" + getLotNo() + ", getProductionDate()=" + getProductionDate() + ", getExpiryDate()="
				+ getExpiryDate() + ", getOrderNo()=" + getOrderNo() + ", getPo()=" + getPo() + ", getPackagingNo()="
				+ getPackagingNo() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
