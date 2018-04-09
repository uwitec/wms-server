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
 * 入库采购订单(PURCHASE_ORDER)
 * 
 * @author su
 * @version 1.0.0 2017-11-24
 */
public class PurchaseOrder {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -2776537689620989655L;

    /**  */
    private Long id;

    /**  */
    private String uuid;

    /** 订单号 */
    private String po;

    /** 收货人 */
    private String ownerCode;

    /** 供应商 */
    private String supplierCode;

    /** 结算方 */
    private String paymentCode;

    /** 总包装件数 */
    private Integer packs;

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

    /** 入库采购订单类型(WS:完税 BS:保税) */
    private String orderType;

    /** 仓库代码 */
    private String warehouse;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;

    /** 审核 */
    private boolean isAudit;

    /** 是否生成入库单 */
    private boolean isReceiving;

    /** 更新人 */
    private String lastUpdateBy;

    /** 更新时间 */
    private Date lastUpdateDate;

    /** 备注 */
    private String remark;
    
    /** 海关编号(客户端编号) */
    private String customsCode;

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
     * 获取
     * 
     * @return 
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * 设置
     * 
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取订单号
     * 
     * @return 订单号
     */
    public String getPo() {
        return this.po;
    }

    /**
     * 设置订单号
     * 
     * @param po
     *          订单号
     */
    public void setPo(String po) {
        this.po = po;
    }

    /**
     * 获取收货人
     * 
     * @return 收货人
     */
    public String getOwnerCode() {
        return this.ownerCode;
    }

    /**
     * 设置收货人
     * 
     * @param ownerCode
     *          收货人
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * 获取供应商
     * 
     * @return 供应商
     */
    public String getSupplierCode() {
        return this.supplierCode;
    }

    /**
     * 设置供应商
     * 
     * @param supplierCode
     *          供应商
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * 获取结算方
     * 
     * @return 结算方
     */
    public String getPaymentCode() {
        return this.paymentCode;
    }

    /**
     * 设置结算方
     * 
     * @param paymentCode
     *          结算方
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取总包装件数
     * 
     * @return 总包装件数
     */
    public Integer getPacks() {
        return this.packs == null ? 0 : packs;
    }

    /**
     * 设置总包装件数
     * 
     * @param packs
     *          总包装件数
     */
    public void setPacks(Integer packs) {
        this.packs = packs;
    }

    /**
     * 获取总金额
     * 
     * @return 总金额
     */
    public BigDecimal getAmount() {
        return this.amount == null ? new BigDecimal(0) : amount;
    }

    /**
     * 设置总金额
     * 
     * @param amount
     *          总金额
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
     *          币制
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
        return this.netWeight == null ? new BigDecimal(0) : netWeight;
    }

    /**
     * 设置净重
     * 
     * @param netWeight
     *          净重
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
        return this.grossWeight == null ? new BigDecimal(0) : grossWeight;
    }

    /**
     * 设置毛重
     * 
     * @param grossWeight
     *          毛重
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
        return this.volume == null ? new BigDecimal(0) : volume;
    }

    /**
     * 设置体积
     * 
     * @param volume
     *          体积
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * 获取入库采购订单类型(WS:完税 BS:保税)
     * 
     * @return 入库采购订单类型(WS
     */
    public String getOrderType() {
        return this.orderType;
    }

    /**
     * 设置入库采购订单类型(WS:完税 BS:保税)
     * 
     * @param orderType
     *          入库采购订单类型(WS
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
     *          仓库代码
     */
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
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
     *          创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置创建时间
     * 
     * @param createDate
     *          创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取审核
     * 
     * @return 审核
     */
    public boolean getIsAudit() {
        return this.isAudit;
    }

    /**
     * 设置审核
     * 
     * @param isAudit
     *          审核
     */
    public void setIsAudit(boolean isAudit) {
        this.isAudit = isAudit;
    }

    /**
     * 获取是否生成入库单
     * 
     * @return 是否生成入库单
     */
    public boolean getIsReceiving() {
        return this.isReceiving;
    }

    /**
     * 设置是否生成入库单
     * 
     * @param isReceiving
     *          是否生成入库单
     */
    public void setIsReceiving(boolean isReceiving) {
        this.isReceiving = isReceiving;
    }

    /**
     * 获取更新人
     * 
     * @return 更新人
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * 设置更新人
     * 
     * @param lastUpdateBy
     *          更新人
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 设置更新时间
     * 
     * @param lastUpdateDate
     *          更新时间
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
     *          备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取海关编号(客户端编号)
     * 
     * @return 海关编号(客户端编号)
     */
    public String getCustomsCode() {
        return this.customsCode;
    }

    /**
     * 设置海关编号(客户端编号)
     * 
     * @param customsCode
     *          海关编号(客户端编号)
     */
    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", uuid=" + uuid + ", po=" + po + ", ownerCode=" + ownerCode
				+ ", supplierCode=" + supplierCode + ", paymentCode=" + paymentCode + ", packs=" + packs + ", amount="
				+ amount + ", currency=" + currency + ", netWeight=" + netWeight + ", grossWeight=" + grossWeight
				+ ", volume=" + volume + ", orderType=" + orderType + ", warehouse=" + warehouse + ", createBy="
				+ createBy + ", createDate=" + createDate + ", isAudit=" + isAudit + ", isReceiving=" + isReceiving
				+ ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate=" + lastUpdateDate + ", remark=" + remark
				+ ", customsCode=" + customsCode + "]";
	}
    
    
    
}