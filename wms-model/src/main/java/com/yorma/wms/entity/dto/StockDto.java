package com.yorma.wms.entity.dto;

import java.math.BigDecimal;

import com.yorma.wms.entity.Stock;

/**
 * 库存Dto
 * 
 * @author subiao 2017年9月19日
 */
public class StockDto extends Stock {

	/** 分配数量 */
	private Integer deliveryQty;

	/** 货主名 */
	private String departName;

	/** 集装容器单位 */
	private String palletUnit;
	/** 明细uuid */
	private String asnUuid;
	/** 入库货物信息创建人 */
	private String plCreateBy;

	private String departCode;
	
	private String shippingCode;
	
	private String paymentCode;
	
	private String billNo;
	
	private BigDecimal amount;
	
	private String remark;
	
	private String asnPackingNo;
	
	private String orderNo;
	 
	private String po;
	
	private String invoceNo;
	
	/**
	 * 获取分配量
	 * 
	 * @return 分配量
	 */
	public Integer getDeliveryQty() {
		return deliveryQty;
	}

	/**
	 * 设置分配量
	 * 
	 * @param deliveryQty
	 *            分配量
	 */
	public void setDeliveryQty(Integer deliveryQty) {
		this.deliveryQty = deliveryQty;
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
	 * 获取集装容器单位
	 * 
	 * @return 集装容器单位
	 */
	public String getPalletUnit() {
		return palletUnit;
	}

	/**
	 * 设置包装容器单位
	 * 
	 * @param palletUnit
	 *            包装容器单位
	 */
	public void setPalletUnit(String palletUnit) {
		this.palletUnit = palletUnit;
	}

	/**
	 * 计算分配量
	 * 
	 * @return
	 */
	public Integer deliveryQty() {
		// 在库数量
		// 预分配数量
		// 求可分配数量
		if (null != getInStock() && null != getAllocatedStock() && null != getPreAllocationStock()) {
			return getInStock() - getAllocatedStock() - getPreAllocationStock();
		}
		return 0;
	}

	/**
	 * 获取明细UUID
	 * 
	 * @return 明细UUID
	 */
	public String getAsnUuid() {
		return asnUuid;
	}

	/**
	 * 获取明细UUID
	 * 
	 * @param asnUuid
	 *            明细UUID
	 */
	public void setAsnUuid(String asnUuid) {
		this.asnUuid = asnUuid;
	}

	/**
	 * 获取入库货物信息创建人
	 * 
	 * @return 入库货物信息创建人
	 */
	public String getPlCreateBy() {
		return plCreateBy;
	}

	/**
	 * 获取入库货物信息创建人
	 * 
	 * @param plCreateBy
	 *            入库货物信息创建人
	 */
	public void setPlCreateBy(String plCreateBy) {
		this.plCreateBy = plCreateBy;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAsnPackingNo() {
		return asnPackingNo;
	}

	public void setAsnPackingNo(String asnPackingNo) {
		this.asnPackingNo = asnPackingNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getInvoceNo() {
		return invoceNo;
	}

	public void setInvoceNo(String invoceNo) {
		this.invoceNo = invoceNo;
	}

	@Override
	public String toString() {
		return "StockDto [getDeliveryQty()=" + getDeliveryQty() + ", getDepartName()=" + getDepartName()
				+ ", getPalletUnit()=" + getPalletUnit() + ", deliveryQty()=" + deliveryQty() + ", getAsnUuid()="
				+ getAsnUuid() + ", getPlCreateBy()=" + getPlCreateBy() + ", getDepartCode()=" + getDepartCode()
				+ ", getShippingCode()=" + getShippingCode() + ", getPaymentCode()=" + getPaymentCode()
				+ ", getBillNo()=" + getBillNo() + ", getAmount()=" + getAmount() + ", getRemark()=" + getRemark()
				+ ", getAsnPackingNo()=" + getAsnPackingNo() + ", getOrderNo()=" + getOrderNo() + ", getPo()=" + getPo()
				+ ", getInvoceNo()=" + getInvoceNo() + ", getId()=" + getId() + ", getUuid()=" + getUuid()
				+ ", getEntryDate()=" + getEntryDate() + ", getEntryType()=" + getEntryType() + ", getOwnerCode()="
				+ getOwnerCode() + ", getPn()=" + getPn() + ", getSku()=" + getSku() + ", getBarcode()=" + getBarcode()
				+ ", getGoodsUuid()=" + getGoodsUuid() + ", getItem()=" + getItem() + ", getEmsNo()=" + getEmsNo()
				+ ", getEntryQty()=" + getEntryQty() + ", getInStock()=" + getInStock() + ", getPreAllocationStock()="
				+ getPreAllocationStock() + ", getAllocatedStock()=" + getAllocatedStock() + ", getMu()=" + getMu()
				+ ", getWarehouse()=" + getWarehouse() + ", getStorage()=" + getStorage() + ", getLocation()="
				+ getLocation() + ", getPackagingNo()=" + getPackagingNo() + ", getPalletNo()=" + getPalletNo()
				+ ", getIsQualifed()=" + getIsQualifed() + ", getAmounts()=" + getAmounts() + ", getCurrency()="
				+ getCurrency() + ", getPlUuid()=" + getPlUuid() + ", getReceivingNoteNo()=" + getReceivingNoteNo()
				+ ", getPackagingQty()=" + getPackagingQty() + ", getSecondaryPackagingQty()="
				+ getSecondaryPackagingQty() + ", getVersion()=" + getVersion() + ", getExpirationDate()="
				+ getExpirationDate() + ", getManufactureLotNo()=" + getManufactureLotNo() + ", getReceivingLotNo()="
				+ getReceivingLotNo() + ", getStatus()=" + getStatus() + ", getGoodsName()=" + getGoodsName()
				+ ", getGoodsModel()=" + getGoodsModel() + ", getPackagingKey()=" + getPackagingKey() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
