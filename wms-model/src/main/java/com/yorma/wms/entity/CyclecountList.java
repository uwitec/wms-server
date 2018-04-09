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
 * 盘点明细(CYCLECOUNT_LIST)
 * 
 * @author su
 * @version 1.0.0 2017-12-06
 */
public class CyclecountList {
	/** 版本号 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -6642650534878914688L;

	/**  */
	private Long id;

	/** 盘点单号 */
	private String cyclecountNo;

	/** 品名 */
	private String goodsName;

	/** 规格 */
	private String goodsModel;

	/** 项号 */
	private String item;

	/** 料号 */
	private String pn;

	/**  */
	private String sku;

	/** 条码号 */
	private String barCode;

	/** 计量单位 */
	private String mu;

	/** 库存数量 */
	private Integer stockQty;

	/** 盘点数量 */
	private Integer cyclecountQty;

	/** 盈亏数量 */
	private Integer profitLoss;

	/** 仓库代码 */
	private String warehouse;

	/** 库区 */
	private String storage;

	/** 储位 */
	private String location;

	/** 商品关联唯一码 */
	private String goodsUuid;

	/** 货主代码(海关10位编码或社会统一代码) */
	private String ownerCode;

	/** 出货单号 */
	private String deliveryNoteNo;

	/** 收货单号 */
	private String receivingNoteNo;

	/**
	 * 构造无参方法
	 */
	public CyclecountList() {
		super();
	}

	/**
	 * 构造有参方法
	 */
	public CyclecountList(Long id, String cyclecountNo, String goodsName, String goodsModel, String item, String pn,
			String sku, String barCode, String mu, Integer stockQty, Integer cyclecountQty, Integer profitLoss,
			String warehouse, String storage, String location, String goodsUuid, String ownerCode,
			String deliveryNoteNo, String receivingNoteNo) {
		this.id = id;
		this.cyclecountNo = cyclecountNo;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.item = item;
		this.pn = pn;
		this.sku = sku;
		this.barCode = barCode;
		this.mu = mu;
		this.stockQty = stockQty;
		this.cyclecountQty = cyclecountQty;
		this.profitLoss = profitLoss;
		this.warehouse = warehouse;
		this.storage = storage;
		this.location = location;
		this.goodsUuid = goodsUuid;
		this.ownerCode = ownerCode;
		this.deliveryNoteNo = deliveryNoteNo;
		this.receivingNoteNo = receivingNoteNo;

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
	 * 获取盘点单号
	 * 
	 * @return 盘点单号
	 */
	public String getCyclecountNo() {
		return this.cyclecountNo;
	}

	/**
	 * 设置盘点单号
	 * 
	 * @param cyclecountNo
	 *            盘点单号
	 */
	public void setCyclecountNo(String cyclecountNo) {
		this.cyclecountNo = cyclecountNo;
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
	 * 获取条码号
	 * 
	 * @return 条码号
	 */
	public String getBarCode() {
		return this.barCode;
	}

	/**
	 * 设置条码号
	 * 
	 * @param barCode
	 *            条码号
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	 *            计量单位
	 */
	public void setMu(String mu) {
		this.mu = mu;
	}

	/**
	 * 获取库存数量
	 * 
	 * @return 库存数量
	 */
	public Integer getStockQty() {
		return this.stockQty;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stockQty
	 *            库存数量
	 */
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	/**
	 * 获取盘点数量
	 * 
	 * @return 盘点数量
	 */
	public Integer getCyclecountQty() {
		return this.cyclecountQty;
	}

	/**
	 * 设置盘点数量
	 * 
	 * @param cyclecountQty
	 *            盘点数量
	 */
	public void setCyclecountQty(Integer cyclecountQty) {
		this.cyclecountQty = cyclecountQty;
	}

	/**
	 * 获取盈亏数量
	 * 
	 * @return 盈亏数量
	 */
	public Integer getProfitLoss() {
		return this.profitLoss;
	}

	/**
	 * 设置盈亏数量
	 * 
	 * @param profitLoss
	 *            盈亏数量
	 */
	public void setProfitLoss(Integer profitLoss) {
		this.profitLoss = profitLoss;
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
	 * 获取库区
	 * 
	 * @return 库区
	 */
	public String getStorage() {
		return this.storage;
	}

	/**
	 * 设置库区
	 * 
	 * @param storage
	 *            库区
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}

	/**
	 * 获取储位
	 * 
	 * @return 储位
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * 设置储位
	 * 
	 * @param location
	 *            储位
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 *            商品关联唯一码
	 */
	public void setGoodsUuid(String goodsUuid) {
		this.goodsUuid = goodsUuid;
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

	@Override
	public String toString() {
		return "CyclecountList [id=" + id + ", cyclecountNo=" + cyclecountNo + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", item=" + item + ", pn=" + pn + ", sku=" + sku + ", barCode="
				+ barCode + ", mu=" + mu + ", stockQty=" + stockQty + ", cyclecountQty=" + cyclecountQty
				+ ", profitLoss=" + profitLoss + ", warehouse=" + warehouse + ", storage=" + storage + ", location=" + location
				+ ", goodsUuid=" + goodsUuid + ", ownerCode=" + ownerCode + ", deliveryNoteNo=" + deliveryNoteNo
				+ ", receivingNoteNo=" + receivingNoteNo + "]";
	}
}