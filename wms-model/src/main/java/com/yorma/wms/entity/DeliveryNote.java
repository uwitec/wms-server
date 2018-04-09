/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.0.0
 */

package com.yorma.wms.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出货单(DELIVERY_NOTE)
 * 
 * @author subiao
 * @version 1.0.0 2017-09-14
 */
public class DeliveryNote {
	/** 版本号 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 812455416031096756L;

	/**  */
	private Long id;

	/** 出货单号 */
	private String deliveryNoteNo;

	/** 出货类型:301 正常出货 */
	private String deliveryType;

	/** 货主代码(海关10位编码或社会统一代码) */
	private String ownerCode;

	/** 收货人代码 */
	private String consigneeCode;

	/** 结算方代码 */
	private String paymentCode;

	/** 仓库代码 */
	private String warehouse;

	/** 提运单号 */
	private String billNo;

	/** 总包装件数 */
	private String packs;

	/** 总金额 */
	private BigDecimal amount;

	/** 币制 */
	private String currency;

	/** 净重 */
	private BigDecimal netWeight;

	/** 毛重 */
	private BigDecimal grossWeight;

	/** 体积 */
	private BigDecimal volume;

	/** 预计出货日期 */
	private Date expeateDeliverDate;

	/** 备注 */
	private String remark;

	/** 创建人 */
	private String createBy;

	/** 创建日期 */
	private Date createDate;

	/** 修改人 */
	private String lastUpdateBy;

	/** 修改日期 */
	private Date lastUpdateDate;

	/** 状态 */
	private Integer status;

	/** 出库策略 */
	private String deliveryMode;

	/** 可拆包装 */
	private Boolean isDetachable;
	
	/** 出库销售订单单号 */
    private String orderSo;

	/**
	 * 无参构造方法
	 */
	public DeliveryNote() {

	}

	/**
	 * 有参构造方法
	 */
	public DeliveryNote(Long id, String deliveryNoteNo, String deliveryType, String ownerCode, String consigneeCode,
			String paymentCode, String warehouse, String billNo, String packs, BigDecimal amount, String currency,
			BigDecimal netWeight, BigDecimal grossWeight, BigDecimal volume, Date expeateDeliverDate, String remark,
			String createBy, Date createDate, String lastUpdateBy, Date lastUpdateDate, Integer status,
			String deliveryMode, Boolean isDetachable) {

		this.id = id;
		this.deliveryNoteNo = deliveryNoteNo;
		this.deliveryType = deliveryType;
		this.ownerCode = ownerCode;
		this.consigneeCode = consigneeCode;
		this.paymentCode = paymentCode;
		this.warehouse = warehouse;
		this.billNo = billNo;
		this.packs = packs;
		this.amount = amount;
		this.currency = currency;
		this.netWeight = netWeight;
		this.grossWeight = grossWeight;
		this.volume = volume;
		this.expeateDeliverDate = expeateDeliverDate;
		this.remark = remark;
		this.createBy = createBy;
		this.createDate = createDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateDate = lastUpdateDate;
		this.status = status;
		this.deliveryMode = deliveryMode;
		this.isDetachable = isDetachable;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取出货单号
	 * 
	 * @return 出货单号
	 */
	public String getDeliveryNoteNo() {
		return this.deliveryNoteNo;
	}

	/**
	 * 设置出货单号
	 * 
	 * @param deliveryNoteNo
	 *            出货单号
	 */
	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}

	/**
	 * 获取出货类型:301 正常出货
	 * 
	 * @return 出货类型
	 */
	public String getDeliveryType() {
		return this.deliveryType == null ? "301" : deliveryType;
	}

	/**
	 * 设置出货类型:301 正常出货
	 * 
	 * @param deliveryType
	 *            出货类型
	 */
	public void setDeliveryType(String deliveryType) {

		this.deliveryType = deliveryType;
	}

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
	 * 获取收货人代码
	 * 
	 * @return 收货人代码
	 */
	public String getConsigneeCode() {
		return this.consigneeCode;
	}

	/**
	 * 设置收货人代码
	 * 
	 * @param consigneeCode
	 *            收货人代码
	 */
	public void setConsigneeCode(String consigneeCode) {
		this.consigneeCode = consigneeCode;
	}

	/**
	 * 获取结算方代码
	 * 
	 * @return 结算方代码
	 */
	public String getPaymentCode() {
		return this.paymentCode;
	}

	/**
	 * 设置结算方代码
	 * 
	 * @param paymentCode
	 *            结算方代码
	 */
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	/**
	 * 获取仓库代码
	 * 
	 * @return 仓库代码
	 */
	public String getWarehouse() {
		return this.warehouse;
	}

	/**
	 * 设置仓库代码
	 * 
	 * @param warehouse
	 *            仓库代码
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * 获取提运单号
	 * 
	 * @return 提运单号
	 */
	public String getBillNo() {
		return this.billNo;
	}

	/**
	 * 设置提运单号
	 * 
	 * @param billNo
	 *            提运单号
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * 获取总包装件数
	 * 
	 * @return 总包装件数
	 */
	public String getPacks() {
		return this.packs == null ? "0" : packs;
	}

	/**
	 * 设置总包装件数
	 * 
	 * @param packs
	 *            总包装件数
	 */
	public void setPacks(String packs) {
		this.packs = packs;
	}

	/**
	 * 获取总金额
	 * 
	 * @return 总金额
	 */
	public BigDecimal getAmount() {
		return this.amount == null ? new BigDecimal(0.00) : amount;
	}

	/**
	 * 设置总金额
	 * 
	 * @param amount
	 *            总金额
	 */
	public void setAmount(BigDecimal amount) {

		this.amount = amount;
	}

	/**
	 * 获取币制
	 * 
	 * @return 币制
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * 设置币制
	 * 
	 * @param currency
	 *            币制
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 获取净重
	 * 
	 * @return 净重
	 */
	public BigDecimal getNetWeight() {
		return this.netWeight == null ? new BigDecimal(0.00) : netWeight;
	}

	/**
	 * 设置净重
	 * 
	 * @param netWeight
	 *            净重
	 */
	public void setNetWeight(BigDecimal netWeight) {

		this.netWeight = netWeight;
	}

	/**
	 * 获取毛重
	 * 
	 * @return 毛重
	 */
	public BigDecimal getGrossWeight() {
		return this.grossWeight == null ? new BigDecimal(0.00) : grossWeight;
	}

	/**
	 * 设置毛重
	 * 
	 * @param grossWeight
	 *            毛重
	 */
	public void setGrossWeight(BigDecimal grossWeight) {

		this.grossWeight = grossWeight;
	}

	/**
	 * 获取体积
	 * 
	 * @return 体积
	 */
	public BigDecimal getVolume() {
		return this.volume == null ? new BigDecimal(0.00) : volume;
	}

	/**
	 * 设置体积
	 * 
	 * @param volume
	 *            体积
	 */
	public void setVolume(BigDecimal volume) {

		this.volume = volume;
	}

	/**
	 * 获取预计出货日期
	 * 
	 * @return 预计出货日期
	 */
	public Date getExpeateDeliverDate() {
		return this.expeateDeliverDate;
	}

	/**
	 * 设置预计出货日期
	 * 
	 * @param expeateDeliverDate
	 *            预计出货日期
	 */
	public void setExpeateDeliverDate(Date expeateDeliverDate) {
		this.expeateDeliverDate = expeateDeliverDate;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取创建人
	 * 
	 * @return 创建人
	 */
	public String getCreateBy() {
		return this.createBy;
	}

	/**
	 * 设置创建人
	 * 
	 * @param createBy
	 *            创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取创建日期
	 * 
	 * @return 创建日期
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置创建日期
	 * 
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改人
	 * 
	 * @return 修改人
	 */
	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * 设置修改人
	 * 
	 * @param lastUpdateBy
	 *            修改人
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * 获取修改日期
	 * 
	 * @return 修改日期
	 */
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * 设置修改日期
	 * 
	 * @param lastUpdateDate
	 *            修改日期
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取出库策略
	 * 
	 * @return 出库策略
	 */
	public String getDeliveryMode() {
		return this.deliveryMode;
	}

	/**
	 * 设置出库策略
	 * 
	 * @param deliveryMode
	 *            出库策略
	 */
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * 获取可拆包装
	 * 
	 * @return 可拆包装
	 */
	public Boolean getIsDetachable() {
		return this.isDetachable;
	}

	/**
	 * 设置可拆包装
	 * 
	 * @param isDetachable
	 *            可拆包装
	 */
	public void setIsDetachable(Boolean isDetachable) {
		this.isDetachable = isDetachable;
	}
	
	/**
     * 获取出库销售订单单号
     * 
     * @return 出库销售订单单号
     */
    public String getOrderSo() {
        return this.orderSo;
    }

    /**
     * 设置出库销售订单单号
     * 
     * @param orderSo
     *          出库销售订单单号
     */
    public void setOrderSo(String orderSo) {
        this.orderSo = orderSo;
    }

	@Override
	public String toString() {
		return "DeliveryNote [id=" + id + ", deliveryNoteNo=" + deliveryNoteNo + ", deliveryType=" + deliveryType
				+ ", ownerCode=" + ownerCode + ", consigneeCode=" + consigneeCode + ", paymentCode=" + paymentCode
				+ ", warehouse=" + warehouse + ", billNo=" + billNo + ", packs=" + packs + ", amount=" + amount
				+ ", currency=" + currency + ", netWeight=" + netWeight + ", grossWeight=" + grossWeight + ", volume="
				+ volume + ", expeateDeliverDate=" + expeateDeliverDate + ", remark=" + remark + ", createBy="
				+ createBy + ", createDate=" + createDate + ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate="
				+ lastUpdateDate + ", status=" + status + ", deliveryMode=" + deliveryMode + ", isDetachable="
				+ isDetachable + ", orderSo=" + orderSo + "]";
	}

}