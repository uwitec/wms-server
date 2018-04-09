package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.DeliveryNote;

/**
 * 出货单Dto
 * 
 * @author subiao 2017年9月19日
 */
public class DeliveryNoteDto extends DeliveryNote {

	/** 货主名称 */
	private String departName;

	/** 状态名称 */
	private String statusName;

	/** 收货人 */
	private String consigne;
	/** 结算方 */
	private String payment;

	/**
	 * 获取货主名称
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

	/**
	 * 获取状态名称
	 * 
	 * @return 状态名称
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * 设置状态名称
	 * 
	 * @param statusName
	 *            状态名称
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * 获取收货人
	 * 
	 * @return 收货人
	 */
	public String getConsigne() {
		return consigne;
	}

	/**
	 * 设置收货人
	 * 
	 * @param consigne
	 *            收货人
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	/**
	 * 获取结算方
	 * 
	 * @return 结算方
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * 设置结算方
	 * 
	 * @param payment
	 *            结算方
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "DeliveryNoteDto [getDepartName()=" + getDepartName() + ", getStatusName()=" + getStatusName()
				+ ", getConsigne()=" + getConsigne() + ", getPayment()=" + getPayment() + ", getId()=" + getId()
				+ ", getDeliveryNoteNo()=" + getDeliveryNoteNo() + ", getDeliveryType()=" + getDeliveryType()
				+ ", getOwnerCode()=" + getOwnerCode() + ", getConsigneeCode()=" + getConsigneeCode()
				+ ", getPaymentCode()=" + getPaymentCode() + ", getWarehouse()=" + getWarehouse() + ", getBillNo()="
				+ getBillNo() + ", getPacks()=" + getPacks() + ", getAmount()=" + getAmount() + ", getCurrency()="
				+ getCurrency() + ", getNetWeight()=" + getNetWeight() + ", getGrossWeight()=" + getGrossWeight()
				+ ", getVolume()=" + getVolume() + ", getExpeateDeliverDate()=" + getExpeateDeliverDate()
				+ ", getRemark()=" + getRemark() + ", getCreateBy()=" + getCreateBy() + ", getCreateDate()="
				+ getCreateDate() + ", getLastUpdateBy()=" + getLastUpdateBy() + ", getLastUpdateDate()="
				+ getLastUpdateDate() + ", getStatus()=" + getStatus() + ", getDeliveryMode()=" + getDeliveryMode()
				+ ", getIsDetachable()=" + getIsDetachable() + ", getOrderSo()=" + getOrderSo() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
