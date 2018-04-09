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
 * 入库货物信息(RECEIVING_PACKING_LIST)
 * 
 * @author su
 * @version 1.0.0 2018-01-05
 */
public class ReceivingPackingList {
	/** 版本号 */
    private static final long serialVersionUID = 1076404360635797221L;

    /**  */
    private Long id;

    /** 唯一码 */
    private String uuid;

    /** 预告关联唯一码 */
    private String ansUuid;

    /** 收货单号 */
    private String receivingNoteNo;

    /** 商品关联唯一码 */
    private String goodsUuid;

    /** 货主料号 */
    private String pn;

    /** 品名 */
    private String goodsName;

    /** 规格 */
    private String goodsModel;

    /** 项号 */
    private String item;

    /** 账册号 */
    private String emsNo;

    /**  */
    private String sku;

    /** 主单位代码 */
    private String mu;

    /** 仓库代码 */
    private String warehouse;

    /** 库区代码 */
    private String storage;

    /** 库位代码 */
    private String location;

    /** 入库数量 */
    private Integer entryQty;

    /** 包装条码号 */
    private String packagingNo;

    /** 包装键 */
    private String packagingKey;

    /** 包装毛重 */
    private BigDecimal packagingGrossWeight;

    /** 包装净重 */
    private BigDecimal packagingNetWeight;

    /** 包装体积 */
    private BigDecimal packagingVolume;

    /** 集装条码号 */
    private String palletNo;

    /** 集装容器单位 */
    private String palletUnit;

    /** 集装毛重 */
    private BigDecimal palletGrossWeight;

    /** 集装净重 */
    private BigDecimal palletNetWeiht;

    /** 集装容器体积 */
    private BigDecimal palletVolume;

    /** 集装面积 */
    private BigDecimal palletArea;

    /** 良品 */
    private boolean isQualifd;

    /**  */
    private String createBy;

    /**  */
    private Date createDate;

    /** 包装数 */
    private BigDecimal packagingQty;

    /** 二级包装数 */
    private BigDecimal secondaryPackagingQty;

    /** 状态（0：未上架，10：已上架，20：已入库） */
    private Integer status;

    /** 合并批次号 */
    private String mergeLotNo;

    /** 生产批次 */
    private String lotNo;
    
    
    public ReceivingPackingList() {
		super();
	}

	public ReceivingPackingList(Long id, String uuid, String ansUuid, String receivingNoteNo, String goodsUuid,
			String pn, String goodsName, String goodsModel, String item, String emsNo, String sku, String mu,
			String warehouse, String storage, String location, Integer entryQty, String packagingNo,
			String packagingKey, BigDecimal packagingGrossWeight, BigDecimal packagingNetWeight,
			BigDecimal packagingVolume, String palletNo, String palletUnit, BigDecimal palletGrossWeight,
			BigDecimal palletNetWeiht, BigDecimal palletVolume, BigDecimal palletArea, boolean isQualifd,
			String createBy, Date createDate, BigDecimal packagingQty, BigDecimal secondaryPackagingQty, Integer status,
			String mergeLotNo) {
		this.id = id;
		this.uuid = uuid;
		this.ansUuid = ansUuid;
		this.receivingNoteNo = receivingNoteNo;
		this.goodsUuid = goodsUuid;
		this.pn = pn;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.item = item;
		this.emsNo = emsNo;
		this.sku = sku;
		this.mu = mu;
		this.warehouse = warehouse;
		this.storage = storage;
		this.location = location;
		this.entryQty = entryQty;
		this.packagingNo = packagingNo;
		this.packagingKey = packagingKey;
		this.packagingGrossWeight = packagingGrossWeight;
		this.packagingNetWeight = packagingNetWeight;
		this.packagingVolume = packagingVolume;
		this.palletNo = palletNo;
		this.palletUnit = palletUnit;
		this.palletGrossWeight = palletGrossWeight;
		this.palletNetWeiht = palletNetWeiht;
		this.palletVolume = palletVolume;
		this.palletArea = palletArea;
		this.isQualifd = isQualifd;
		this.createBy = createBy;
		this.createDate = createDate;
		this.packagingQty = packagingQty;
		this.secondaryPackagingQty = secondaryPackagingQty;
		this.status = status;
		this.mergeLotNo = mergeLotNo;
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
     * 获取唯一码
     * 
     * @return 唯一码
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * 设置唯一码
     * 
     * @param uuid
     *          唯一码
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取预告关联唯一码
     * 
     * @return 预告关联唯一码
     */
    public String getAnsUuid() {
        return this.ansUuid;
    }

    /**
     * 设置预告关联唯一码
     * 
     * @param ansUuid
     *          预告关联唯一码
     */
    public void setAnsUuid(String ansUuid) {
        this.ansUuid = ansUuid;
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
     * 获取货主料号
     * 
     * @return 货主料号
     */
    public String getPn() {
        return this.pn;
    }

    /**
     * 设置货主料号
     * 
     * @param pn
     *          货主料号
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
     * 获取库区代码
     * 
     * @return 库区代码
     */
    public String getStorage() {
        return this.storage;
    }

    /**
     * 设置库区代码
     * 
     * @param storage
     *          库区代码
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取库位代码
     * 
     * @return 库位代码
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置库位代码
     * 
     * @param location
     *          库位代码
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取入库数量
     * 
     * @return 入库数量
     */
    public Integer getEntryQty() {
        return this.entryQty == null ? 0 : entryQty;
    }

    /**
     * 设置入库数量
     * 
     * @param entryQty
     *          入库数量
     */
    public void setEntryQty(Integer entryQty) {
        this.entryQty = entryQty;
    }

    /**
     * 获取包装条码号
     * 
     * @return 包装条码号
     */
    public String getPackagingNo() {
        return this.packagingNo;
    }

    /**
     * 设置包装条码号
     * 
     * @param packagingNo
     *          包装条码号
     */
    public void setPackagingNo(String packagingNo) {
        this.packagingNo = packagingNo;
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
     * 获取包装毛重
     * 
     * @return 包装毛重
     */
    public BigDecimal getPackagingGrossWeight() {
        return this.packagingGrossWeight == null ? new BigDecimal(0) : packagingGrossWeight;
    }

    /**
     * 设置包装毛重
     * 
     * @param packagingGrossWeight
     *          包装毛重
     */
    public void setPackagingGrossWeight(BigDecimal packagingGrossWeight) {
        this.packagingGrossWeight = packagingGrossWeight;
    }

    /**
     * 获取包装净重
     * 
     * @return 包装净重
     */
    public BigDecimal getPackagingNetWeight() {
        return this.packagingNetWeight == null ? new BigDecimal(0) : packagingNetWeight;
    }

    /**
     * 设置包装净重
     * 
     * @param packagingNetWeight
     *          包装净重
     */
    public void setPackagingNetWeight(BigDecimal packagingNetWeight) {
        this.packagingNetWeight = packagingNetWeight;
    }

    /**
     * 获取包装体积
     * 
     * @return 包装体积
     */
    public BigDecimal getPackagingVolume() {
        return this.packagingVolume == null ? new BigDecimal(0) : packagingVolume;
    }

    /**
     * 设置包装体积
     * 
     * @param packagingVolume
     *          包装体积
     */
    public void setPackagingVolume(BigDecimal packagingVolume) {
        this.packagingVolume = packagingVolume;
    }

    /**
     * 获取集装条码号
     * 
     * @return 集装条码号
     */
    public String getPalletNo() {
        return this.palletNo;
    }

    /**
     * 设置集装条码号
     * 
     * @param palletNo
     *          集装条码号
     */
    public void setPalletNo(String palletNo) {
        this.palletNo = palletNo;
    }

    /**
     * 获取集装容器单位
     * 
     * @return 集装容器单位
     */
    public String getPalletUnit() {
        return this.palletUnit;
    }

    /**
     * 设置集装容器单位
     * 
     * @param palletUnit
     *          集装容器单位
     */
    public void setPalletUnit(String palletUnit) {
        this.palletUnit = palletUnit;
    }

    /**
     * 获取集装毛重
     * 
     * @return 集装毛重
     */
    public BigDecimal getPalletGrossWeight() {
        return this.palletGrossWeight == null ? new BigDecimal(0) : palletGrossWeight;
    }

    /**
     * 设置集装毛重
     * 
     * @param palletGrossWeight
     *          集装毛重
     */
    public void setPalletGrossWeight(BigDecimal palletGrossWeight) {
        this.palletGrossWeight = palletGrossWeight;
    }

    /**
     * 获取集装净重
     * 
     * @return 集装净重
     */
    public BigDecimal getPalletNetWeiht() {
        return this.palletNetWeiht == null ? new BigDecimal(0) : palletNetWeiht;
    }

    /**
     * 设置集装净重
     * 
     * @param palletNetWeiht
     *          集装净重
     */
    public void setPalletNetWeiht(BigDecimal palletNetWeiht) {
        this.palletNetWeiht = palletNetWeiht;
    }

    /**
     * 获取集装容器体积
     * 
     * @return 集装容器体积
     */
    public BigDecimal getPalletVolume() {
        return this.palletVolume == null ? new BigDecimal(0) : palletVolume;
    }

    /**
     * 设置集装容器体积
     * 
     * @param palletVolume
     *          集装容器体积
     */
    public void setPalletVolume(BigDecimal palletVolume) {
        this.palletVolume = palletVolume;
    }

    /**
     * 获取集装面积
     * 
     * @return 集装面积
     */
    public BigDecimal getPalletArea() {
        return this.palletArea == null ? new BigDecimal(0) : palletArea;
    }

    /**
     * 设置集装面积
     * 
     * @param palletArea
     *          集装面积
     */
    public void setPalletArea(BigDecimal palletArea) {
        this.palletArea = palletArea;
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
     * 获取
     * 
     * @return 
     */
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 设置
     * 
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置
     * 
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取包装数
     * 
     * @return 包装数
     */
    public BigDecimal getPackagingQty() {
        return this.packagingQty == null ? new BigDecimal(0) : packagingQty;
    }

    /**
     * 设置包装数
     * 
     * @param packagingQty
     *          包装数
     */
    public void setPackagingQty(BigDecimal packagingQty) {
        this.packagingQty = packagingQty;
    }

    /**
     * 获取二级包装数
     * 
     * @return 二级包装数
     */
    public BigDecimal getSecondaryPackagingQty() {
        return this.secondaryPackagingQty == null ? new BigDecimal(0) : secondaryPackagingQty;
    }

    /**
     * 设置二级包装数
     * 
     * @param secondaryPackagingQty
     *          二级包装数
     */
    public void setSecondaryPackagingQty(BigDecimal secondaryPackagingQty) {
        this.secondaryPackagingQty = secondaryPackagingQty;
    }

    /**
     * 获取状态（0：未上架，10：已上架，20：已入库）
     * 
     * @return 状态（0：未上架
     */
    public Integer getStatus() {
        return this.status == null ? 0 : status;
    }

    /**
     * 设置状态（0：未上架，10：已上架，20：已入库）
     * 
     * @param status
     *          状态（0：未上架
     */
    public void setStatus(Integer status) {
        this.status = status;
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
    
	@Override
	public String toString() {
		return "ReceivingPackingList [id=" + id + ", uuid=" + uuid + ", ansUuid=" + ansUuid + ", receivingNoteNo="
				+ receivingNoteNo + ", goodsUuid=" + goodsUuid + ", pn=" + pn + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", item=" + item + ", emsNo=" + emsNo + ", sku=" + sku + ", mu=" + mu
				+ ", warehouse=" + warehouse + ", storage=" + storage + ", location=" + location + ", entryQty="
				+ entryQty + ", packagingNo=" + packagingNo + ", packagingKey=" + packagingKey
				+ ", packagingGrossWeight=" + packagingGrossWeight + ", packagingNetWeight=" + packagingNetWeight
				+ ", packagingVolume=" + packagingVolume + ", palletNo=" + palletNo + ", palletUnit=" + palletUnit
				+ ", palletGrossWeight=" + palletGrossWeight + ", palletNetWeiht=" + palletNetWeiht + ", palletVolume="
				+ palletVolume + ", palletArea=" + palletArea + ", isQualifd=" + isQualifd + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", packagingQty=" + packagingQty + ", secondaryPackagingQty="
				+ secondaryPackagingQty + ", status=" + status + ", mergeLotNo=" + mergeLotNo + ", lotNo=" + lotNo
				+ "]";
	}
    
    
}