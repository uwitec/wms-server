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
 * 收货预告明细(RECEIVING_NOTE_ASN)
 * 
 * @author su
 * @version 1.0.0 2018-01-05
 */
public class ReceivingNoteAsn implements Cloneable {
    /** 版本号 */
    private static final long serialVersionUID = 6648116310717918978L;

    /**  */
    private Long id;

    /**  */
    private String uuid;

    /** 收货单号 */
    private String receivingNoteNo;

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

    /** 预收数量 */
    private Integer expectQty;

    /** 包装数量 */
    private Integer packagingQty;

    /** 验收数量 */
    private Integer acceptanceQty;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;

    /** 金额 */
    private BigDecimal amount;

    /** 发票号 */
    private String invoiceNo;

    /** 生产批次 */
    private String lotNo;

    /** 生产日期 */
    private Date productionDate;

    /** 失效日期 */
    private Date expiryDate;

    /** 客户单号 */
    private String orderNo;

    /** 采购单号 */
    private String po;

    /** 客户包装号 */
    private String packagingNo;

    /** 良品 */
    private boolean isQualifd;

    /** 状态（0：未分配，20：已分配，20：已到货） */
    private Integer status;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 合并批次号 */
    private String mergeLotNo;

    /** 是否合并分配（null：未分配，false：单条分配，true：合并分配） */
    private Boolean isMerge;

    
    
    public ReceivingNoteAsn() {
		super();
	}

	public ReceivingNoteAsn(Long id, String uuid, String receivingNoteNo, String goodsUuid, String item, String emsNo,
			String sku, String mu, String pn, String goodsName, String goodsModel, BigDecimal grossWeight,
			BigDecimal netWeight, BigDecimal volume, String batchAttribute, String currency, String packagingKey,
			Integer expectQty, Integer packagingQty, Integer acceptanceQty, String createBy, Date createDate,
			BigDecimal amount, String invoiceNo, String lotNo, Date productionDate, Date expiryDate, String orderNo,
			String po, String packagingNo, boolean isQualifd, Integer status, BigDecimal unitPrice, String mergeLotNo,
			Boolean isMerge) {
		this.id = id;
		this.uuid = uuid;
		this.receivingNoteNo = receivingNoteNo;
		this.goodsUuid = goodsUuid;
		this.item = item;
		this.emsNo = emsNo;
		this.sku = sku;
		this.mu = mu;
		this.pn = pn;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.volume = volume;
		this.batchAttribute = batchAttribute;
		this.currency = currency;
		this.packagingKey = packagingKey;
		this.expectQty = expectQty;
		this.packagingQty = packagingQty;
		this.acceptanceQty = acceptanceQty;
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
		this.isQualifd = isQualifd;
		this.status = status;
		this.unitPrice = unitPrice;
		this.mergeLotNo = mergeLotNo;
		this.isMerge = isMerge;
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
     *          收货单号
     */
    public void setReceivingNoteNo(String receivingNoteNo) {
        this.receivingNoteNo = receivingNoteNo;
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
     *          预收数量
     */
    public void setExpectQty(Integer expectQty) {
        this.expectQty = expectQty;
    }

    /**
     * 获取包装数量
     * 
     * @return 包装数量
     */
    public Integer getPackagingQty() {
        return this.packagingQty == null ? 0 : packagingQty;
    }

    /**
     * 设置包装数量
     * 
     * @param packagingQty
     *          包装数量
     */
    public void setPackagingQty(Integer packagingQty) {
        this.packagingQty = packagingQty;
    }

    /**
     * 获取验收数量
     * 
     * @return 验收数量
     */
    public Integer getAcceptanceQty() {
        return this.acceptanceQty == null ? 0 : acceptanceQty;
    }

    /**
     * 设置验收数量
     * 
     * @param acceptanceQty
     *          验收数量
     */
    public void setAcceptanceQty(Integer acceptanceQty) {
        this.acceptanceQty = acceptanceQty;
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
     *          生产批次
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
     * 获取客户单号
     * 
     * @return 客户单号
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置客户单号
     * 
     * @param orderNo
     *          客户单号
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
     * 获取良品
     * 
     * @return 良品
     */
    public boolean getIsQualifd() {
        return this.isQualifd;
    }

    /**
     * 设置良品
     * 
     * @param isQualifd
     *          良品
     */
    public void setIsQualifd(boolean isQualifd) {
        this.isQualifd = isQualifd;
    }

    /**
     * 获取状态（0：未分配，20：已分配，20：已到货）
     * 
     * @return 状态（0：未分配
     */
    public Integer getStatus() {
        return this.status == null ? 0 : status;
    }

    /**
     * 设置状态（0：未分配，20：已分配，20：已到货）
     * 
     * @param status
     *          状态（0：未分配
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取是否合并分配（null：未分配，false：单条分配，true：合并分配）
     * 
     * @return 是否合并分配（null：未分配
     */
    public Boolean getIsMerge() {
        return this.isMerge;
    }

    /**
     * 设置是否合并分配（null：未分配，false：单条分配，true：合并分配）
     * 
     * @param isMerge
     *          是否合并分配（null：未分配
     */
    public void setIsMerge(Boolean isMerge) {
        this.isMerge = isMerge;
    }
    
    @Override
	public String toString() {
		return "ReceivingNoteAsn [id=" + id + ", uuid=" + uuid + ", receivingNoteNo=" + receivingNoteNo + ", goodsUuid="
				+ goodsUuid + ", item=" + item + ", emsNo=" + emsNo + ", sku=" + sku + ", mu=" + mu + ", pn=" + pn
				+ ", goodsName=" + goodsName + ", goodsModel=" + goodsModel + ", grossWeight=" + grossWeight
				+ ", netWeight=" + netWeight + ", volume=" + volume + ", batchAttribute=" + batchAttribute
				+ ", currency=" + currency + ", packagingKey=" + packagingKey + ", expectQty=" + expectQty
				+ ", packagingQty=" + packagingQty + ", acceptanceQty=" + acceptanceQty + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", amount=" + amount + ", invoiceNo=" + invoiceNo + ", lotNo=" + lotNo
				+ ", productionDate=" + productionDate + ", expiryDate=" + expiryDate + ", orderNo=" + orderNo + ", po="
				+ po + ", packagingNo=" + packagingNo + ", isQualifd=" + isQualifd + ", status=" + status
				+ ", unitPrice=" + unitPrice + ", mergeLotNo=" + mergeLotNo + ", isMerge=" + isMerge + "]";
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
    	ReceivingNoteAsn receivingNoteAsn = null;
		try {
			receivingNoteAsn=(ReceivingNoteAsn) super.clone();
		} catch (CloneNotSupportedException ignored) {
			 System.out.println(ignored.getMessage());  
		}
		return receivingNoteAsn;
    }
    
}