package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.PurchaseOrder;

public class PurchaseOrderDto extends PurchaseOrder {

	/** 收货人 */
	private String departName;
	/** 供应商 */
	private String supplierName;
	/** 结算方 */
	private String paymentName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	@Override
	public String toString() {
		return "PurchaseOrderDto [getDepartName()=" + getDepartName() + ", getSupplierName()=" + getSupplierName()
				+ ", getPaymentName()=" + getPaymentName() + ", getId()=" + getId() + ", getUuid()=" + getUuid()
				+ ", getPo()=" + getPo() + ", getOwnerCode()=" + getOwnerCode() + ", getSupplierCode()="
				+ getSupplierCode() + ", getPaymentCode()=" + getPaymentCode() + ", getPacks()=" + getPacks()
				+ ", getAmount()=" + getAmount() + ", getCurrency()=" + getCurrency() + ", getNetWeight()="
				+ getNetWeight() + ", getGrossWeight()=" + getGrossWeight() + ", getVolume()=" + getVolume()
				+ ", getOrderType()=" + getOrderType() + ", getWarehouse()=" + getWarehouse() + ", getCreateBy()="
				+ getCreateBy() + ", getCreateDate()=" + getCreateDate() + ", getIsAudit()=" + getIsAudit()
				+ ", getIsReceiving()=" + getIsReceiving() + ", getLastUpdateBy()=" + getLastUpdateBy()
				+ ", getLastUpdateDate()=" + getLastUpdateDate() + ", getRemark()=" + getRemark() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
