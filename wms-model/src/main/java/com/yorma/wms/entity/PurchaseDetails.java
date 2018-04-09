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
 * 入库采购订单明细(PURCHASE_DETAILS)
 * 
 * @author su
 * @version 1.0.0 2017-11-24
 */
public class PurchaseDetails {
	/** 版本号 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8440059827111805845L;

	/**  */
	private Long id;

	/** 订单表头的订单号 */
    private String orderPo;

	/** 订单号 */
	private String po;

	/** 商品UUID */
	private String goodsUuid;

	/** 预收数量 */
	private Integer expectQty;

	/** 税率 */
	private String taxRate;

	/** 金额 */
	private BigDecimal amount;

	/** 币制 */
	private String currency;

	/** 主单位代码 */
	private String mu;

	/** 料号 */
	private String pn;

	/** 账册号 */
	private String emsNo;

	/** 项号 */
	private String item;

	/** 品名 */
	private String goodsName;

	/** 规格 */
	private String goodsModel;

	/** 毛重 */
	private BigDecimal grossWeight;

	/** 净重 */
	private BigDecimal netWeight;

	/** 体积 */
	private BigDecimal volume;

	/** 批属性 */
	private String batchAttribute;

	/** 是否良品 */
	private boolean isQualifd;

	/** 创建人 */
	private String createBy;

	/** 创建日期 */
	private Date createDate;

	/** 发票号 */
	private String invoiceNo;

	/** 生产批次 */
	private String lotNo;

	/** 生产日期 */
	private Date productionDate;

	/** 失效日期 */
	private Date expiryDate;

	/** 更新人 */
	private String lastUpdateBy;

	/** 更新时间 */
	private Date lastUpdateDate;

	/** 包装键 */
	private String packagingKey;

	/** 单价 */
	private BigDecimal unitPrice;

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
     * 获取订单表头的订单号
     * 
     * @return 订单表头的订单号
     */
    public String getOrderPo() {
        return this.orderPo;
    }

    /**
     * 设置订单表头的UUID
     * 
     * @param orderUuid
     *          订单表头的UUID
     */
    public void setOrderPo(String orderPo) {
        this.orderPo = orderPo;
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
	 *            订单号
	 */
	public void setPo(String po) {
		this.po = po;
	}

	/**
	 * 获取商品UUID
	 * 
	 * @return 商品UUID
	 */
	public String getGoodsUuid() {
		return this.goodsUuid;
	}

	/**
	 * 设置商品UUID
	 * 
	 * @param goodsUuid
	 *            商品UUID
	 */
	public void setGoodsUuid(String goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	/**
	 * 获取预收数量
	 * 
	 * @return 预收数量
	 */
	public Integer getExpectQty() {
		return this.expectQty == null ? 0 : expectQty;
	}

	/**
	 * 设置预收数量
	 * 
	 * @param expectQty
	 *            预收数量
	 */
	public void setExpectQty(Integer expectQty) {
		this.expectQty = expectQty;
	}

	/**
	 * 获取税率
	 * 
	 * @return 税率
	 */
	public String getTaxRate() {
		return this.taxRate;
	}

	/**
	 * 设置税率
	 * 
	 * @param taxRate
	 *            税率
	 */
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * 获取金额
	 * 
	 * @return 金额
	 */
	public BigDecimal getAmount() {
		return this.amount == null ? new BigDecimal(0) : amount;
	}

	/**
	 * 设置金额
	 * 
	 * @param amount
	 *            金额
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
	 * 获取主单位代码
	 * 
	 * @return 主单位代码
	 */
	public String getMu() {
		return this.mu;
	}

	/**
	 * 设置主单位代码
	 * 
	 * @param mu
	 *            主单位代码
	 */
	public void setMu(String mu) {
		this.mu = mu;
	}

	/**
	 * 获取料号
	 * 
	 * @return 料号
	 */
	public String getPn() {
		return this.pn;
	}

	/**
	 * 设置料号
	 * 
	 * @param pn
	 *            料号
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}

	/**
	 * 获取账册号
	 * 
	 * @return 账册号
	 */
	public String getEmsNo() {
		return this.emsNo;
	}

	/**
	 * 设置账册号
	 * 
	 * @param emsNo
	 *            账册号
	 */
	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	/**
	 * 获取项号
	 * 
	 * @return 项号
	 */
	public String getItem() {
		return this.item;
	}

	/**
	 * 设置项号
	 * 
	 * @param item
	 *            项号
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 获取品名
	 * 
	 * @return 品名
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	/**
	 * 设置品名
	 * 
	 * @param goodsName
	 *            品名
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 获取规格
	 * 
	 * @return 规格
	 */
	public String getGoodsModel() {
		return this.goodsModel;
	}

	/**
	 * 设置规格
	 * 
	 * @param goodsModel
	 *            规格
	 */
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
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
	 *            毛重
	 */
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
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
	 *            净重
	 */
	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
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
	 *            体积
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	/**
	 * 获取批属性
	 * 
	 * @return 批属性
	 */
	public String getBatchAttribute() {
		return this.batchAttribute;
	}

	/**
	 * 设置批属性
	 * 
	 * @param batchAttribute
	 *            批属性
	 */
	public void setBatchAttribute(String batchAttribute) {
		this.batchAttribute = batchAttribute;
	}

	/**
	 * 获取是否良品
	 * 
	 * @return 是否良品
	 */
	public boolean getIsQualifd() {
		return this.isQualifd;
	}

	/**
	 * 设置是否良品
	 * 
	 * @param isQualifd
	 *            是否良品
	 */
	public void setIsQualifd(boolean isQualifd) {
		this.isQualifd = isQualifd;
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
	 * 获取发票号
	 * 
	 * @return 发票号
	 */
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * 设置发票号
	 * 
	 * @param invoiceNo
	 *            发票号
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * 获取生产批次
	 * 
	 * @return 生产批次
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * 设置生产批次
	 * 
	 * @param lotNo
	 *            生产批次
	 */
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 获取生产日期
	 * 
	 * @return 生产日期
	 */
	public Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * 设置生产日期
	 * 
	 * @param productionDate
	 *            生产日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * 获取失效日期
	 * 
	 * @return 失效日期
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * 设置失效日期
	 * 
	 * @param expiryDate
	 *            失效日期
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 *            更新人
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
	 *            更新时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * 获取包装键
	 * 
	 * @return 包装键
	 */
	public String getPackagingKey() {
		return this.packagingKey;
	}

	/**
	 * 设置包装键
	 * 
	 * @param packagingKey
	 *            包装键
	 */
	public void setPackagingKey(String packagingKey) {
		this.packagingKey = packagingKey;
	}

	/**
	 * 获取单价
	 * 
	 * @return 单价
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice == null ? new BigDecimal(0) : unitPrice;
	}

	/**
	 * 设置单价
	 * 
	 * @param unitPrice
	 *            单价
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "PurchaseDetails [id=" + id + ", orderPo=" + orderPo + ", po=" + po + ", goodsUuid=" + goodsUuid
				+ ", expectQty=" + expectQty + ", taxRate=" + taxRate + ", amount=" + amount + ", currency=" + currency
				+ ", mu=" + mu + ", pn=" + pn + ", emsNo=" + emsNo + ", item=" + item + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", grossWeight=" + grossWeight + ", netWeight=" + netWeight
				+ ", volume=" + volume + ", batchAttribute=" + batchAttribute + ", isQualifd=" + isQualifd
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", invoiceNo=" + invoiceNo + ", lotNo="
				+ lotNo + ", productionDate=" + productionDate + ", expiryDate=" + expiryDate + ", lastUpdateBy="
				+ lastUpdateBy + ", lastUpdateDate=" + lastUpdateDate + ", packagingKey=" + packagingKey
				+ ", unitPrice=" + unitPrice + "]";
	}

}