package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.SalesOrder;

public class SalesOrderDto extends SalesOrder {

	/** 收货人 */
	private String departName;
	/** 客户 */
	private String consigneeName;
	/** 结算方 */
	private String paymentName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	@Override
	public String toString() {
		return "SalesOrderDto [getDepartName()=" + getDepartName() + ", getConsigneeName()=" + getConsigneeName()
				+ ", getPaymentName()=" + getPaymentName() + ", getId()=" + getId() + ", getUuid()=" + getUuid()
				+ ", getSo()=" + getSo() + ", getOwnerCode()=" + getOwnerCode() + ", getConsigneeCode()="
				+ getConsigneeCode() + ", getPaymentCode()=" + getPaymentCode() + ", getPacks()=" + getPacks()
				+ ", getAmount()=" + getAmount() + ", getCurrency()=" + getCurrency() + ", getNetWeight()="
				+ getNetWeight() + ", getGrossWeight()=" + getGrossWeight() + ", getVolume()=" + getVolume()
				+ ", getOrderType()=" + getOrderType() + ", getWarehouse()=" + getWarehouse() + ", getCreateBy()="
				+ getCreateBy() + ", getCreateDate()=" + getCreateDate() + ", getIsAudit()=" + getIsAudit()
				+ ", getIsDelivery()=" + getIsDelivery() + ", getLastUpdateBy()=" + getLastUpdateBy()
				+ ", getLastUpdateDate()=" + getLastUpdateDate() + ", getRemark()=" + getRemark() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
