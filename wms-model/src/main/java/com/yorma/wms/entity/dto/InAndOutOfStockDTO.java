package com.yorma.wms.entity.dto;

import java.util.Date;

/**
 * 料号明细收发存DTO
 * @author su
 * 2018年3月27日
 */
public class InAndOutOfStockDTO {

	
	/** 货主代码 */
	private String ownerCode;
	
	 /** 品名 */
    private String goodsName;
    
    /** 商品规格 */
    private String goodsModel;
    
    /** 货主料号 */
	private String pn;

    /** 包装键 */
    private String packagingKey;
    
    /** 仓库代码 */
	private String warehouse;

	/** 库区代码 */
	private String storage;

	/** 库位代码 */
	private String location;
	
	/** 主数量单位代码 */
	private String mu;
	
	/** 物料唯一码(关联) */
	private String goodsUuid;
	
	/** 出库总量 */
	private Integer sumDeliveryQty;
	
	/** 入库总量 */
	private Integer sumEntryQty;

	/**
	 * GET 货主代码
	 * @return 货主代码
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 * SET 货主代码
	 * @param ownerCode 货主代码
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 * GET 品名
	 * @return 品名
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * SET 品名
	 * @param goodsName 品名
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * GET 商品规格
	 * @return 商品规格
	 */
	public String getGoodsModel() {
		return goodsModel;
	}

	/**
	 * SET 商品规格
	 * @param goodsModel 商品规格
	 */
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	/**
	 * GET 货主料号
	 * @return 货主料号
	 */
	public String getPn() {
		return pn;
	}

	/**
	 * SET 货主料号
	 * @param pn 货主料号
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}

	/**
	 * GET 包装键
	 * @return 包装键
	 */
	public String getPackagingKey() {
		return packagingKey;
	}

	/**
	 * SET 包装键
	 * @param packagingKey 
	 */
	public void setPackagingKey(String packagingKey) {
		this.packagingKey = packagingKey;
	}
	
	/**
	 * GET 仓库代码
	 * @return 仓库代码
	 */
	public String getWarehouse() {
		return warehouse;
	}

	/**
	 * SET 仓库代码
	 * @param warehouse 仓库代码
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * GET 库区代码 
	 * @return 库区代码 
	 */
	public String getStorage() {
		return storage;
	}

	/**
	 * SET 库区代码 
	 * @param storage 库区代码 
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}

	/**
	 * GET 库区代码 
	 * @return 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * SET 库区代码 
	 * @param location 库区代码 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * GET 主数量单位代码
	 * @return 主数量单位代码
	 */
	public String getMu() {
		return mu;
	}

	/**
	 * SET 主数量单位代码
	 * @param mu 主数量单位代码
	 */
	public void setMu(String mu) {
		this.mu = mu;
	}

	/**
	 * GET 物料唯一码(关联) 
	 * @return 物料唯一码(关联)
	 */
	public String getGoodsUuid() {
		return goodsUuid;
	}

	/**
	 * SET 物料唯一码(关联)
	 * @param goodsUuid 物料唯一码(关联)
	 */
	public void setGoodsUuid(String goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	/**
	 * GET 出库总量
	 * @return 出库总量
	 */
	public Integer getSumDeliveryQty() {
		return sumDeliveryQty;
	}

	/**
	 * SET 出库总量
	 * @param sumDeliveryQty 出库总量
	 */
	public void setSumDeliveryQty(Integer sumDeliveryQty) {
		this.sumDeliveryQty = sumDeliveryQty;
	}

	/**
	 * GET 入库总量
	 * @return 入库总量
	 */
	public Integer getSumEntryQty() {
		return sumEntryQty;
	}

	/**
	 * SET 入库总量
	 * @param sumEntryQty 入库总量
	 */
	public void setSumEntryQty(Integer sumEntryQty) {
		this.sumEntryQty = sumEntryQty;
	}

	@Override
	public String toString() {
		return "InAndOutOfStockDTO [ownerCode=" + ownerCode + ", goodsName=" + goodsName + ", goodsModel=" + goodsModel
				+ ", pn=" + pn + ", packagingKey=" + packagingKey + ", warehouse=" + warehouse + ", storage=" + storage
				+ ", location=" + location + ", mu=" + mu + ", goodsUuid=" + goodsUuid + ", sumDeliveryQty="
				+ sumDeliveryQty + ", sumEntryQty=" + sumEntryQty + "]";
	}
	
	
	
}
