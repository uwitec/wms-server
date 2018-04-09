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
 * 出货预告明细(DELIVERY_NOTE_ASN)
 * 
 * @author su
 * @version 1.0.0 2018-01-05
 */
public class DeliveryNoteAsn {
    /** 版本号 */
    private static final long serialVersionUID = 2110425166770772164L;

    /**  */
    private Long id;

    /**  */
    private String uuid;

    /** 出货单号 */
    private String deliveryNoteNo;

    /** 商品关联唯一码 */
    private String goodsUuid;

    /** 项号 */
    private String item;

    /** 账册号 */
    private String emsNo;

    /**  */
    private String sku;

    /** 主单位代码 */
    private String mu;

    /** 料号 */
    private String pn;

    /** 预出数量 */
    private Integer expectQty;

    /**  */
    private Integer allocationQty;

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

    /** 币制 */
    private String currency;

    /** 包装键 */
    private String packagingKey;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;

    /** 金额 */
    private BigDecimal amount;

    /** 发票号 */
    private String invoiceNo;

    /** 批次号 */
    private String lotNo;

    /** 生产日期 */
    private Date productionDate;

    /** 失效日期 */
    private Date expiryDate;

    /** 订单号 */
    private String orderNo;

    /** 采购单号 */
    private String po;

    /** 客户包装号 */
    private String packagingNo;

    /** 是否合并（null：未分配过，1：合并分配 0：单个分配） */
    private Boolean isMerged;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 合并批次号 */
    private String mergeLotNo;
    
    /** 状态(0：未分配，1：已分配） */
    private Integer status;

    public DeliveryNoteAsn() {
		super();
	}
	
	public DeliveryNoteAsn(Long id, String uuid, String deliveryNoteNo, String goodsUuid, String item, String emsNo,
			String sku, String mu, String pn, Integer expectQty, Integer allocationQty, String goodsName,
			String goodsModel, BigDecimal grossWeight, BigDecimal netWeight, BigDecimal volume, String batchAttribute,
			String currency, String packagingKey, String createBy, Date createDate, BigDecimal amount, String invoiceNo,
			String lotNo, Date productionDate, Date expiryDate, String orderNo, String po, String packagingNo,
			Boolean isMerged, BigDecimal unitPrice, String mergeLotNo,Integer status) {
		this.id = id;
		this.uuid = uuid;
		this.deliveryNoteNo = deliveryNoteNo;
		this.goodsUuid = goodsUuid;
		this.item = item;
		this.emsNo = emsNo;
		this.sku = sku;
		this.mu = mu;
		this.pn = pn;
		this.expectQty = expectQty;
		this.allocationQty = allocationQty;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.volume = volume;
		this.batchAttribute = batchAttribute;
		this.currency = currency;
		this.packagingKey = packagingKey;
		this.createBy = createBy;
		this.createDate = createDate;
		this.amount = amount;
		this.invoiceNo = invoiceNo;
		this.lotNo = lotNo;
		this.productionDate = productionDate;
		this.expiryDate = expiryDate;
		this.orderNo = orderNo;
		this.po = po;
		this.packagingNo = packagingNo;
		this.isMerged = isMerged;
		this.unitPrice = unitPrice;
		this.mergeLotNo = mergeLotNo;
		this.status=status;
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
     *          出货单号
     */
    public void setDeliveryNoteNo(String deliveryNoteNo) {
        this.deliveryNoteNo = deliveryNoteNo;
    }

    /**
     * 获取商品关联唯一码
     * 
     * @return 商品关联唯一码
     */
    public String getGoodsUuid() {
        return this.goodsUuid;
    }

    /**
     * 设置商品关联唯一码
     * 
     * @param goodsUuid
     *          商品关联唯一码
     */
    public void setGoodsUuid(String goodsUuid) {
        this.goodsUuid = goodsUuid;
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
     *          项号
     */
    public void setItem(String item) {
        this.item = item;
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
     *          账册号
     */
    public void setEmsNo(String emsNo) {
        this.emsNo = emsNo;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getSku() {
        return this.sku;
    }

    /**
     * 设置
     * 
     * @param sku
     */
    public void setSku(String sku) {
        this.sku = sku;
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
     *          主单位代码
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
     *          料号
     */
    public void setPn(String pn) {
        this.pn = pn;
    }

    /**
     * 获取预出数量
     * 
     * @return 预出数量
     */
    public Integer getExpectQty() {
        return this.expectQty == null ? 0 : expectQty;
    }

    /**
     * 设置预出数量
     * 
     * @param expectQty
     *          预出数量
     */
    public void setExpectQty(Integer expectQty) {
        this.expectQty = expectQty;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Integer getAllocationQty() {
        return this.allocationQty;
    }

    /**
     * 设置
     * 
     * @param allocationQty
     */
    public void setAllocationQty(Integer allocationQty) {
        this.allocationQty = allocationQty;
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
     *          品名
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
     *          规格
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
     *          毛重
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
     *          净重
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
     *          体积
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
     *          批属性
     */
    public void setBatchAttribute(String batchAttribute) {
        this.batchAttribute = batchAttribute;
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
     *          包装键
     */
    public void setPackagingKey(String packagingKey) {
        this.packagingKey = packagingKey;
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
     *          金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     *          发票号
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * 获取批次号
     * 
     * @return 批次号
     */
    public String getLotNo() {
        return this.lotNo;
    }

    /**
     * 设置批次号
     * 
     * @param lotNo
     *          批次号
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
     *          生产日期
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
     *          失效日期
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 获取订单号
     * 
     * @return 订单号
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置订单号
     * 
     * @param orderNo
     *          订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取采购单号
     * 
     * @return 采购单号
     */
    public String getPo() {
        return this.po;
    }

    /**
     * 设置采购单号
     * 
     * @param po
     *          采购单号
     */
    public void setPo(String po) {
        this.po = po;
    }

    /**
     * 获取客户包装号
     * 
     * @return 客户包装号
     */
    public String getPackagingNo() {
        return this.packagingNo;
    }

    /**
     * 设置客户包装号
     * 
     * @param packagingNo
     *          客户包装号
     */
    public void setPackagingNo(String packagingNo) {
        this.packagingNo = packagingNo;
    }

    /**
     * 获取是否合并（null：未分配过，1：合并分配 0：单个分配）
     * 
     * @return 是否合并（null：未分配过
     */
    public Boolean getIsMerged() {
        return this.isMerged;
    }

    /**
     * 设置是否合并（null：未分配过，1：合并分配 0：单个分配）
     * 
     * @param isMerged
     *          是否合并（null：未分配过
     */
    public void setIsMerged(Boolean isMerged) {
        this.isMerged = isMerged;
    }

    /**
     * 获取单价
     * 
     * @return 单价
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice == null ? new BigDecimal(0) : unitPrice;
    }

    /**
     * 设置单价
     * 
     * @param unitPrice
     *          单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取合并批次号
     * 
     * @return 合并批次号
     */
    public String getMergeLotNo() {
        return this.mergeLotNo;
    }

    /**
     * 设置合并批次号
     * 
     * @param mergeLotNo
     *          合并批次号
     */
    public void setMergeLotNo(String mergeLotNo) {
        this.mergeLotNo = mergeLotNo;
    }

    /**
     * 获取状态(0：未分配，1：已分配）
     * 
     * @return 状态(0：未分配
     */
    public Integer getStatus() {
        return this.status == null ? 0 : status;
    }

    /**
     * 设置状态(0：未分配，1：已分配）
     * 
     * @param status
     *          状态(0：未分配
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
	@Override
	public String toString() {
		return "DeliveryNoteAsn [id=" + id + ", uuid=" + uuid + ", deliveryNoteNo=" + deliveryNoteNo + ", goodsUuid="
				+ goodsUuid + ", item=" + item + ", emsNo=" + emsNo + ", sku=" + sku + ", mu=" + mu + ", pn=" + pn
				+ ", expectQty=" + expectQty + ", allocationQty=" + allocationQty + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", grossWeight=" + grossWeight + ", netWeight=" + netWeight
				+ ", volume=" + volume + ", batchAttribute=" + batchAttribute + ", currency=" + currency
				+ ", packagingKey=" + packagingKey + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", amount=" + amount + ", invoiceNo=" + invoiceNo + ", lotNo=" + lotNo + ", productionDate="
				+ productionDate + ", expiryDate=" + expiryDate + ", orderNo=" + orderNo + ", po=" + po
				+ ", packagingNo=" + packagingNo + ", isMerged=" + isMerged + ", unitPrice=" + unitPrice
				+ ", mergeLotNo=" + mergeLotNo + ", status=" + status + "]";
	}
    
    
}