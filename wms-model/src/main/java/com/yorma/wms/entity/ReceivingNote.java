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
 * 收货单(RECEIVING_NOTE)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-24
 */
public class ReceivingNote {
	/** 版本号 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -7998899966193472880L;

	/**  */
	private Long id;

	/** 收货单号 */
	private String receivingNoteNo;

	/** 收货类型 */
	private String receivingType;

	/** 货主代码(海关10位编码或社会统一代码) */
	private String ownerCode;

	/** 发货人代码 */
	private String shippingCode;

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

	/** 预计到货日期 */
	private Date expeateArrivalDate;

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

	/** 入库采购订单单号 */
	private String orderPo;

	/**
	 * 无参构造
	 */
	public ReceivingNote() {
	}

	/**
	 * 有参构造
	 */
	public ReceivingNote(Long id, String receivingNoteNo, String receivingType, String ownerCode, String shippingCode,
			String paymentCode, String warehouse, String billNo, String packs, BigDecimal amount, String currency,
			BigDecimal netWeight, BigDecimal grossWeight, BigDecimal volume, Date expeateArrivalDate, String remark,
			String createBy, Date createDate, String lastUpdateBy, Date lastUpdateDate, Integer status) {

		this.id = id;
		this.receivingNoteNo = receivingNoteNo;
		this.receivingType = receivingType;
		this.ownerCode = ownerCode;
		this.shippingCode = shippingCode;
		this.paymentCode = paymentCode;
		this.warehouse = warehouse;
		this.billNo = billNo;
		this.packs = packs;
		this.amount = amount;
		this.currency = currency;
		this.netWeight = netWeight;
		this.grossWeight = grossWeight;
		this.volume = volume;
		this.expeateArrivalDate = expeateArrivalDate;
		this.remark = remark;
		this.createBy = createBy;
		this.createDate = createDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateDate = lastUpdateDate;
		this.status = status;
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
	 * 获取收货单号
	 * 
	 * @return 收货单号
	 */
	public String getReceivingNoteNo() {
		return this.receivingNoteNo;
	}

	/**
	 * 设置收货单号
	 * 
	 * @param receivingNoteNo
	 *            收货单号
	 */
	public void setReceivingNoteNo(String receivingNoteNo) {
		this.receivingNoteNo = receivingNoteNo;
	}

	/**
	 * 获取收货类型
	 * 
	 * @return 收货类型
	 */
	public String getReceivingType() {
		return this.receivingType;
	}

	/**
	 * 设置收货类型
	 * 
	 * @param receivingType
	 *            收货类型
	 */
	public void setReceivingType(String receivingType) {
		this.receivingType = receivingType;
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
	 * 获取发货人代码
	 * 
	 * @return 发货人代码
	 */
	public String getShippingCode() {
		return this.shippingCode;
	}

	/**
	 * 设置发货人代码
	 * 
	 * @param shippingCode
	 *            发货人代码
	 */
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
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
		if (amount == null) {
			amount = new BigDecimal(0.00);
		}
		return this.amount;
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
		if (netWeight == null) {
			netWeight = new BigDecimal(0.00);
		}
		return this.netWeight;
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
		if (grossWeight == null) {
			grossWeight = new BigDecimal(0.00);
		}
		return this.grossWeight;
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
		if (volume == null) {
			volume = new BigDecimal(0.00);
		}
		return this.volume;
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
	 * 获取预计到货日期
	 * 
	 * @return 预计到货日期
	 */
	public Date getExpeateArrivalDate() {
		return this.expeateArrivalDate;
	}

	/**
	 * 设置预计到货日期
	 * 
	 * @param expeateArrivalDate
	 *            预计到货日期
	 */
	public void setExpeateArrivalDate(Date expeateArrivalDate) {
		this.expeateArrivalDate = expeateArrivalDate;
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
	 * 获取入库采购订单单号
	 * 
	 * @return 入库采购订单单号
	 */
	public String getOrderPo() {
		return this.orderPo;
	}

	/**
	 * 设置入库采购订单单号
	 * 
	 * @param orderPo
	 *            入库采购订单单号
	 */
	public void setOrderPo(String orderPo) {
		this.orderPo = orderPo;
	}

	@Override
	public String toString() {
		return "ReceivingNote [id=" + id + ", receivingNoteNo=" + receivingNoteNo + ", receivingType=" + receivingType
				+ ", ownerCode=" + ownerCode + ", shippingCode=" + shippingCode + ", paymentCode=" + paymentCode
				+ ", warehouse=" + warehouse + ", billNo=" + billNo + ", packs=" + packs + ", amount=" + amount
				+ ", currency=" + currency + ", netWeight=" + netWeight + ", grossWeight=" + grossWeight + ", volume="
				+ volume + ", expeateArrivalDate=" + expeateArrivalDate + ", remark=" + remark + ", createBy="
				+ createBy + ", createDate=" + createDate + ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate="
				+ lastUpdateDate + ", status=" + status + ", orderPo=" + orderPo + "]";
	}

}