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

/**
 * 移库记录明细表(STOCK_TRANSFER_LIST)
 * 
 * @author su
 * @version 1.0.0 2017-12-12
 */
public class StockTransferList {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 3530453861836811981L;

    /**  */
    private Long id;

    /**  */
    private String uuid;

    /** 移库单号 */
    private String transferNo;

    /** 货主料号 */
    private String pn;

    /** 商品UUID */
    private String goodsUuid;

    /** 品名 */
    private String goodsName;

    /** 移出仓库 */
    private String fromWarehouse;

    /** 移入仓库 */
    private String toWarehouse;

    /** 移出库区 */
    private String fromStorage;

    /** 移入库区 */
    private String toStorage;

    /** 移出储位 */
    private String fromLocation;

    /** 移入储位 */
    private String toLocation;

    /** 数量 */
    private Integer transferQty;

    /** 计量单位 */
    private String mu;
    
    /** 库存关联唯一码 */
    private String stockUuid;

    public StockTransferList() {
	}

	public StockTransferList(Long id, String uuid, String transferNo, String pn, String goodsUuid, String goodsName,
			String fromWarehouse, String toWarehouse, String fromStorage, String toStorage, String fromLocation,
			String toLocation, Integer transferQty, String mu, String stockUuid) {
		this.id = id;
		this.uuid = uuid;
		this.transferNo = transferNo;
		this.pn = pn;
		this.goodsUuid = goodsUuid;
		this.goodsName = goodsName;
		this.fromWarehouse = fromWarehouse;
		this.toWarehouse = toWarehouse;
		this.fromStorage = fromStorage;
		this.toStorage = toStorage;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.transferQty = transferQty;
		this.mu = mu;
		this.stockUuid = stockUuid;
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
     * 获取移库单号
     * 
     * @return 移库单号
     */
    public String getTransferNo() {
        return this.transferNo;
    }

    /**
     * 设置移库单号
     * 
     * @param transferNo
     *          移库单号
     */
    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo;
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
     *          商品UUID
     */
    public void setGoodsUuid(String goodsUuid) {
        this.goodsUuid = goodsUuid;
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
     * 获取移出仓库
     * 
     * @return 移出仓库
     */
    public String getFromWarehouse() {
        return this.fromWarehouse;
    }

    /**
     * 设置移出仓库
     * 
     * @param fromWarehouse
     *          移出仓库
     */
    public void setFromWarehouse(String fromWarehouse) {
        this.fromWarehouse = fromWarehouse;
    }

    /**
     * 获取移入仓库
     * 
     * @return 移入仓库
     */
    public String getToWarehouse() {
        return this.toWarehouse;
    }

    /**
     * 设置移入仓库
     * 
     * @param toWarehouse
     *          移入仓库
     */
    public void setToWarehouse(String toWarehouse) {
        this.toWarehouse = toWarehouse;
    }

    /**
     * 获取移出库区
     * 
     * @return 移出库区
     */
    public String getFromStorage() {
        return this.fromStorage;
    }

    /**
     * 设置移出库区
     * 
     * @param fromStorage
     *          移出库区
     */
    public void setFromStorage(String fromStorage) {
        this.fromStorage = fromStorage;
    }

    /**
     * 获取移入库区
     * 
     * @return 移入库区
     */
    public String getToStorage() {
        return this.toStorage;
    }

    /**
     * 设置移入库区
     * 
     * @param toStorage
     *          移入库区
     */
    public void setToStorage(String toStorage) {
        this.toStorage = toStorage;
    }

    /**
     * 获取移出储位
     * 
     * @return 移出储位
     */
    public String getFromLocation() {
        return this.fromLocation;
    }

    /**
     * 设置移出储位
     * 
     * @param fromLocation
     *          移出储位
     */
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    /**
     * 获取移入储位
     * 
     * @return 移入储位
     */
    public String getToLocation() {
        return this.toLocation;
    }

    /**
     * 设置移入储位
     * 
     * @param toLocation
     *          移入储位
     */
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    /**
     * 获取数量
     * 
     * @return 数量
     */
    public Integer getTransferQty() {
        return this.transferQty == null ? 0 : this.transferQty;
    }

    /**
     * 设置数量
     * 
     * @param transferQty
     *          数量
     */
    public void setTransferQty(Integer transferQty) {
        this.transferQty = transferQty;
    }

    /**
     * 获取计量单位
     * 
     * @return 计量单位
     */
    public String getMu() {
        return this.mu;
    }

    /**
     * 设置计量单位
     * 
     * @param mu
     *          计量单位
     */
    public void setMu(String mu) {
        this.mu = mu;
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

	@Override
	public String toString() {
		return "StockTransferList [id=" + id + ", uuid=" + uuid + ", transferNo=" + transferNo + ", pn=" + pn
				+ ", goodsUuid=" + goodsUuid + ", goodsName=" + goodsName + ", fromWarehouse=" + fromWarehouse
				+ ", toWarehouse=" + toWarehouse + ", fromStorage=" + fromStorage + ", toStorage=" + toStorage
				+ ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", transferQty=" + transferQty
				+ ", mu=" + mu + ", stockUuid=" + stockUuid + "]";
	}
    
}