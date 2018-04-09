package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.ReceivingNote;

/**
 * 收货单Dto
 * 
 * @author subiao 2017年9月19日
 */
public class ReceivingNoteDto extends ReceivingNote {

	/** 货主名称 */
	private String departName;
	/** 发货人 */
	private String shippingName;
	/** 结算方 */
	private String paymentName;
	/** 状态名 */
	private String statusName;
	

	/**
	 * 获取状态名
	 * 
	 * @return 状态名
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * 设置状态名
	 * 
	 * @param statusName
	 *            状态名
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

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
	 * 获取发货人
	 * 
	 * @return 发货人
	 */
	public String getShippingName() {
		return shippingName;
	}
	/**
	 * 设置发货人
	 * 
	 * @param shippingName
	 *            发货人
	 */
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	/**
	 * 获取结算方
	 * 
	 * @return 结算方
	 */
	public String getPaymentName() {
		return paymentName;
	}
	/**
	 * 设置结算方
	 * 
	 * @param paymentName
	 *            结算方
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	@Override
	public String toString() {
		return "ReceivingNoteDto [getStatusName()=" + getStatusName() + ", getDepartName()=" + getDepartName()
				+ ", getShippingName()=" + getShippingName() + ", getPaymentName()=" + getPaymentName() + ", getId()="
				+ getId() + ", getReceivingNoteNo()=" + getReceivingNoteNo() + ", getReceivingType()="
				+ getReceivingType() + ", getOwnerCode()=" + getOwnerCode() + ", getShippingCode()=" + getShippingCode()
				+ ", getPaymentCode()=" + getPaymentCode() + ", getWarehouse()=" + getWarehouse() + ", getBillNo()="
				+ getBillNo() + ", getPacks()=" + getPacks() + ", getAmount()=" + getAmount() + ", getCurrency()="
				+ getCurrency() + ", getNetWeight()=" + getNetWeight() + ", getGrossWeight()=" + getGrossWeight()
				+ ", getVolume()=" + getVolume() + ", getExpeateArrivalDate()=" + getExpeateArrivalDate()
				+ ", getRemark()=" + getRemark() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()="
				+ getCreateDate() + ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateDate()="
				+ getLastUpdateDate() + ", getStatus()=" + getStatus() + ", getOrderPo()=" + getOrderPo()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
