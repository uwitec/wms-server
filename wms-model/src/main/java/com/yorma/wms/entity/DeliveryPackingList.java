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
 * 出库货物信息(DELIVERY_PACKING_LIST)
 * 
 * @author su
 * @version 1.0.0 2018-01-05
 */
public class DeliveryPackingList {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 3267130513372040420L;

    /**  */
    private Long id;

    /** 唯一码 */
    private String uuid;

    /** 预告关联唯一码 */
    private String ansUuid;

    /** 出货单号 */
    private String deliveryNoteNo;

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

    /** 库存关联唯一码 */
    private String stockUuid;

    /** 出库数量(分配量) */
    private Integer deliveryQty;

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

    /** 集装毛重 */
    private BigDecimal palletGrossWeight;

    /** 集装净重 */
    private BigDecimal palletNetWeiht;

    /** 集装面积 */
    private BigDecimal palletArea;

    /** 良品 */
    private boolean isQualifd;

    /**  */
    private String createBy;

    /**  */
    private Date createDate;

    /** 状态（0：未拣货，10：已拣货，20：已出库） */
    private Integer status;

    /** 出库批次号 */
    private String deliveryLotNo;

    /** 运送单号 */
    private String transportNoteNo;

    /** 合并批次号 */
    private String mergeLotNo;
    
    /** 出库时间 */
    private Date deliveryDate;

    public DeliveryPackingList() {
		super();
	}

	public DeliveryPackingList(Long id, String uuid, String ansUuid, String deliveryNoteNo, String goodsUuid, String pn,
			String goodsName, String goodsModel, String item, String emsNo, String sku, String mu, String warehouse,
			String storage, String location, String stockUuid, Integer deliveryQty, String packagingNo,
			String packagingKey, BigDecimal packagingGrossWeight, BigDecimal packagingNetWeight,
			BigDecimal packagingVolume, String palletNo, BigDecimal palletGrossWeight, BigDecimal palletNetWeiht,
			BigDecimal palletArea, boolean isQualifd, String createBy, Date createDate, Integer status,
			String deliveryLotNo, String transportNoteNo, String mergeLotNo) {
		this.id = id;
		this.uuid = uuid;
		this.ansUuid = ansUuid;
		this.deliveryNoteNo = deliveryNoteNo;
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
		this.stockUuid = stockUuid;
		this.deliveryQty = deliveryQty;
		this.packagingNo = packagingNo;
		this.packagingKey = packagingKey;
		this.packagingGrossWeight = packagingGrossWeight;
		this.packagingNetWeight = packagingNetWeight;
		this.packagingVolume = packagingVolume;
		this.palletNo = palletNo;
		this.palletGrossWeight = palletGrossWeight;
		this.palletNetWeiht = palletNetWeiht;
		this.palletArea = palletArea;
		this.isQualifd = isQualifd;
		this.createBy = createBy;
		this.createDate = createDate;
		this.status = status;
		this.deliveryLotNo = deliveryLotNo;
		this.transportNoteNo = transportNoteNo;
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
     * 获取库存关联唯一码
     * 
     * @return 库存关联唯一码
     */
    public String getStockUuid() {
        return this.stockUuid;
    }

    /**
     * 设置库存关联唯一码
     * 
     * @param stockUuid
     *          库存关联唯一码
     */
    public void setStockUuid(String stockUuid) {
        this.stockUuid = stockUuid;
    }

    /**
     * 获取出库数量(分配量)
     * 
     * @return 出库数量(分配量)
     */
    public Integer getDeliveryQty() {
        return this.deliveryQty == null ? 0 : deliveryQty;
    }

    /**
     * 设置出库数量(分配量)
     * 
     * @param deliveryQty
     *          出库数量(分配量)
     */
    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
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
        return this.packagingVolume;
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
     * 获取集装毛重
     * 
     * @return 集装毛重
     */
    public BigDecimal getPalletGrossWeight() {
        return this.palletGrossWeight;
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
        return this.palletNetWeiht;
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
     * 获取集装面积
     * 
     * @return 集装面积
     */
    public BigDecimal getPalletArea() {
        return this.palletArea;
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
     * 获取状态（0：未拣货，10：已拣货，20：已出库）
     * 
     * @return 状态（0：未拣货
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态（0：未拣货，10：已拣货，20：已出库）
     * 
     * @param status
     *          状态（0：未拣货
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取出库批次号
     * 
     * @return 出库批次号
     */
    public String getDeliveryLotNo() {
        return this.deliveryLotNo;
    }

    /**
     * 设置出库批次号
     * 
     * @param deliveryLotNo
     *          出库批次号
     */
    public void setDeliveryLotNo(String deliveryLotNo) {
        this.deliveryLotNo = deliveryLotNo;
    }

    /**
     * 获取运送单号
     * 
     * @return 运送单号
     */
    public String getTransportNoteNo() {
        return this.transportNoteNo;
    }

    /**
     * 设置运送单号
     * 
     * @param transportNoteNo
     *          运送单号
     */
    public void setTransportNoteNo(String transportNoteNo) {
        this.transportNoteNo = transportNoteNo;
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
     * 获取出库时间
     * 
     * @return 出库时间
     */
    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    /**
     * 设置出库时间
     * 
     * @param deliveryDate
     *          出库时间
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

	@Override
	public String toString() {
		return "DeliveryPackingList [id=" + id + ", uuid=" + uuid + ", ansUuid=" + ansUuid + ", deliveryNoteNo="
				+ deliveryNoteNo + ", goodsUuid=" + goodsUuid + ", pn=" + pn + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", item=" + item + ", emsNo=" + emsNo + ", sku=" + sku + ", mu=" + mu
				+ ", warehouse=" + warehouse + ", storage=" + storage + ", location=" + location + ", stockUuid="
				+ stockUuid + ", deliveryQty=" + deliveryQty + ", packagingNo=" + packagingNo + ", packagingKey="
				+ packagingKey + ", packagingGrossWeight=" + packagingGrossWeight + ", packagingNetWeight="
				+ packagingNetWeight + ", packagingVolume=" + packagingVolume + ", palletNo=" + palletNo
				+ ", palletGrossWeight=" + palletGrossWeight + ", palletNetWeiht=" + palletNetWeiht + ", palletArea="
				+ palletArea + ", isQualifd=" + isQualifd + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", status=" + status + ", deliveryLotNo=" + deliveryLotNo + ", transportNoteNo=" + transportNoteNo
				+ ", mergeLotNo=" + mergeLotNo + ", deliveryDate=" + deliveryDate + "]";
	}

    
    
}