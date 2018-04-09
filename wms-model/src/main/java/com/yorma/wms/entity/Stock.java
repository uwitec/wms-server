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
 * 库存(STOCK)
 * 
 * @author su
 * @version 1.0.0 2017-10-25
 */
public class Stock {
	/** 版本号 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -5748551303679354801L;

	/**  */
	private Long id;

	/** 库存编号 */
	private String uuid;

	/** 入库日期 */
	private Date entryDate;

	/** 入库类型 */
	private String entryType;

	/** 货主代码 */
	private String ownerCode;

	/** 货主料号 */
	private String pn;

	/** 物料SKU */
	private String sku;

	/** 物料条码 */
	private String barcode;

	/** 物料唯一码(关联) */
	private String goodsUuid;

	/** 项号 */
	private String item;

	/** 海关账册号 */
	private String emsNo;

	/** 入库数量 */
	private Integer entryQty;

	/** 在库数量 */
	private Integer inStock;

	/** 预分配数量 */
	private Integer preAllocationStock;

	/** 已分配数量 */
	private Integer allocatedStock;

	/** 主数量单位代码 */
	private String mu;

	/** 仓库代码 */
	private String warehouse;

	/** 库区代码 */
	private String storage;

	/** 库位代码 */
	private String location;

	/** 包装条码 */
	private String packagingNo;

	/** 托盘条码 */
	private String palletNo;

	/** 1 合格品 */
	private Boolean isQualifed;

	/** 金额 */
	private BigDecimal amounts;

	/** 币制 */
	private String currency;

	/** 入库明细关联唯一码 */
	private String plUuid;

	/** 收货单号 */
	private String receivingNoteNo;

	/** 包装数 */
	private BigDecimal packagingQty;

	/** 二级包装数 */
	private BigDecimal secondaryPackagingQty;

	/** 版本号 */
	private Integer version;

	/** 有效日期 */
	private Date expirationDate;

	/** 生产批次号 */
	private String manufactureLotNo;

	/** 入库批次号 */
	private String receivingLotNo;
	
	/** 状态（1：期初） */
    private boolean status;
    
    /** 品名 */
    private String goodsName;

    /** 商品规格 */
    private String goodsModel;

    /** 包装键 */
    private String packagingKey;

	/**
	 * 构造无参方法
	 */
	public Stock() {
		
	}
	/**
	 * 构造有参方法
	 */
	public Stock(Long id, String uuid, Date entryDate, String entryType, String ownerCode, String pn, String sku,
			String barcode, String goodsUuid, String item, String emsNo, Integer entryQty, Integer inStock,
			Integer preAllocationStock, Integer allocatedStock, String mu, String warehouse, String storage,
			String location, String packagingNo, String palletNo, Boolean isQualifed, BigDecimal amounts,
			String currency, String plUuid, String receivingNoteNo, BigDecimal packagingQty,
			BigDecimal secondaryPackagingQty, Integer version, Date expirationDate, String manufactureLotNo,
			String receivingLotNo, String goodsName, String goodsModel, String packagingKey) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.entryDate = entryDate;
		this.entryType = entryType;
		this.ownerCode = ownerCode;
		this.pn = pn;
		this.sku = sku;
		this.barcode = barcode;
		this.goodsUuid = goodsUuid;
		this.item = item;
		this.emsNo = emsNo;
		this.entryQty = entryQty;
		this.inStock = inStock;
		this.preAllocationStock = preAllocationStock;
		this.allocatedStock = allocatedStock;
		this.mu = mu;
		this.warehouse = warehouse;
		this.storage = storage;
		this.location = location;
		this.packagingNo = packagingNo;
		this.palletNo = palletNo;
		this.isQualifed = isQualifed;
		this.amounts = amounts;
		this.currency = currency;
		this.plUuid = plUuid;
		this.receivingNoteNo = receivingNoteNo;
		this.packagingQty = packagingQty;
		this.secondaryPackagingQty = secondaryPackagingQty;
		this.version = version;
		this.expirationDate = expirationDate;
		this.manufactureLotNo = manufactureLotNo;
		this.receivingLotNo = receivingLotNo;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.packagingKey = packagingKey;
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
	 * 获取库存编号
	 * 
	 * @return 库存编号
	 */
	public String getUuid() {
		return this.uuid;
	}

	/**
	 * 设置库存编号
	 * 
	 * @param uuid
	 *            库存编号
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 获取入库日期
	 * 
	 * @return 入库日期
	 */
	public Date getEntryDate() {
		return this.entryDate;
	}

	/**
	 * 设置入库日期
	 * 
	 * @param entryDate
	 *            入库日期
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * 获取入库类型
	 * 
	 * @return 入库类型
	 */
	public String getEntryType() {
		return this.entryType;
	}

	/**
	 * 设置入库类型
	 * 
	 * @param entryType
	 *            入库类型
	 */
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	/**
	 * 获取货主代码
	 * 
	 * @return 货主代码
	 */
	public String getOwnerCode() {
		return this.ownerCode;
	}

	/**
	 * 设置货主代码
	 * 
	 * @param ownerCode
	 *            货主代码
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
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
	 *            货主料号
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}

	/**
	 * 获取物料SKU
	 * 
	 * @return 物料SKU
	 */
	public String getSku() {
		return this.sku;
	}

	/**
	 * 设置物料SKU
	 * 
	 * @param sku
	 *            物料SKU
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * 获取物料条码
	 * 
	 * @return 物料条码
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * 设置物料条码
	 * 
	 * @param barcode
	 *            物料条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 获取物料唯一码(关联)
	 * 
	 * @return 物料唯一码(关联)
	 */
	public String getGoodsUuid() {
		return this.goodsUuid;
	}

	/**
	 * 设置物料唯一码(关联)
	 * 
	 * @param goodsUuid
	 *            物料唯一码(关联)
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
	 *            项号
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 获取海关账册号
	 * 
	 * @return 海关账册号
	 */
	public String getEmsNo() {
		return this.emsNo;
	}

	/**
	 * 设置海关账册号
	 * 
	 * @param emsNo
	 *            海关账册号
	 */
	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	/**
	 * 获取入库数量
	 * 
	 * @return 入库数量
	 */
	public Integer getEntryQty() {
		if (entryQty==null) {
			entryQty=0;
		}
		return this.entryQty;
	}

	/**
	 * 设置入库数量
	 * 
	 * @param entryQty
	 *            入库数量
	 */
	public void setEntryQty(Integer entryQty) {
		
		this.entryQty = entryQty;
	}

	/**
	 * 获取在库数量
	 * 
	 * @return 在库数量
	 */
	public Integer getInStock() {
		if (inStock==null) {
			inStock=0;
		}
		return this.inStock;
	}

	/**
	 * 设置在库数量
	 * 
	 * @param inStock
	 *            在库数量
	 */
	public void setInStock(Integer inStock) {
		
		this.inStock = inStock;
	}

	/**
	 * 获取预分配数量
	 * 
	 * @return 预分配数量
	 */
	public Integer getPreAllocationStock() {
		if (preAllocationStock==null) {
			preAllocationStock=0;
		}
		return this.preAllocationStock;
	}

	/**
	 * 设置预分配数量
	 * 
	 * @param preAllocationStock
	 *            预分配数量
	 */
	public void setPreAllocationStock(Integer preAllocationStock) {
		
		this.preAllocationStock = preAllocationStock;
	}

	/**
	 * 获取已分配数量
	 * 
	 * @return 已分配数量
	 */
	public Integer getAllocatedStock() {
		if (allocatedStock==null) {
			allocatedStock=0;
		}
		return this.allocatedStock;
	}

	/**
	 * 设置已分配数量
	 * 
	 * @param allocatedStock
	 *            已分配数量
	 */
	public void setAllocatedStock(Integer allocatedStock) {
		
		this.allocatedStock = allocatedStock;
	}

	/**
	 * 获取主数量单位代码
	 * 
	 * @return 主数量单位代码
	 */
	public String getMu() {
		return this.mu;
	}

	/**
	 * 设置主数量单位代码
	 * 
	 * @param mu
	 *            主数量单位代码
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
	 *            仓库代码
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
	 *            库区代码
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
		if (location==null) {
			location="QC";
		}
		return this.location;
	}

	/**
	 * 设置库位代码
	 * 
	 * @param location
	 *            库位代码
	 */
	public void setLocation(String location) {
		
		this.location = location;
	}

	/**
	 * 获取包装条码
	 * 
	 * @return 包装条码
	 */
	public String getPackagingNo() {
		return this.packagingNo;
	}

	/**
	 * 设置包装条码
	 * 
	 * @param packagingNo
	 *            包装条码
	 */
	public void setPackagingNo(String packagingNo) {
		this.packagingNo = packagingNo;
	}

	/**
	 * 获取托盘条码
	 * 
	 * @return 托盘条码
	 */
	public String getPalletNo() {
		return this.palletNo;
	}

	/**
	 * 设置托盘条码
	 * 
	 * @param palletNo
	 *            托盘条码
	 */
	public void setPalletNo(String palletNo) {
		this.palletNo = palletNo;
	}

	/**
	 * 获取1 合格品
	 * 
	 * @return 1 合格品
	 */
	public Boolean getIsQualifed() {
		if (isQualifed==null) {
			isQualifed=true;
		}
		return this.isQualifed;
	}

	/**
	 * 设置1 合格品
	 * 
	 * @param isQualifed
	 *            1 合格品
	 */
	public void setIsQualifed(Boolean isQualifed) {
		
		this.isQualifed = isQualifed;
	}

	/**
	 * 获取金额
	 * 
	 * @return 金额
	 */
	public BigDecimal getAmounts() {
		return this.amounts;
	}

	/**
	 * 设置金额
	 * 
	 * @param amounts
	 *            金额
	 */
	public void setAmounts(BigDecimal amounts) {
		this.amounts = amounts;
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
	 * 获取入库明细关联唯一码
	 * 
	 * @return 入库明细关联唯一码
	 */
	public String getPlUuid() {
		return this.plUuid;
	}

	/**
	 * 设置入库明细关联唯一码
	 * 
	 * @param plUuid
	 *            入库明细关联唯一码
	 */
	public void setPlUuid(String plUuid) {
		this.plUuid = plUuid;
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
	 *            包装数
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
		return this.secondaryPackagingQty;
	}

	/**
	 * 设置二级包装数
	 * 
	 * @param secondaryPackagingQty
	 *            二级包装数
	 */
	public void setSecondaryPackagingQty(BigDecimal secondaryPackagingQty) {
		this.secondaryPackagingQty = secondaryPackagingQty;
	}

	/**
	 * 获取版本号
	 * 
	 * @return 版本号
	 */
	public Integer getVersion() {
		return this.version;
	}

	/**
	 * 设置版本号
	 * 
	 * @param version
	 *            版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 获取有效日期
	 * 
	 * @return 有效日期
	 */
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * 设置有效日期
	 * 
	 * @param expirationDate
	 *            有效日期
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * 获取生产批次号
	 * 
	 * @return 生产批次号
	 */
	public String getManufactureLotNo() {
		return this.manufactureLotNo;
	}

	/**
	 * 设置生产批次号
	 * 
	 * @param manufactureLotNo
	 *            生产批次号
	 */
	public void setManufactureLotNo(String manufactureLotNo) {
		this.manufactureLotNo = manufactureLotNo;
	}

	/**
	 * 获取入库批次号
	 * 
	 * @return 入库批次号
	 */
	public String getReceivingLotNo() {
		return this.receivingLotNo;
	}

	/**
	 * 设置入库批次号
	 * 
	 * @param receivingLotNo
	 *            入库批次号
	 */
	public void setReceivingLotNo(String receivingLotNo) {
		this.receivingLotNo = receivingLotNo;
	}
	
	/**
     * 获取状态（ 1：期初）
     * 
     * @return 状态
     */
    public boolean getStatus() {
        return this.status ;
    }

    /**
     * 设置状态（ 1：期初）
     * 
     * @param status
     *          状态（10
     */
    public void setStatus(boolean status) {
        this.status = status;
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
     * 获取商品规格
     * 
     * @return 商品规格
     */
    public String getGoodsModel() {
        return this.goodsModel;
    }

    /**
     * 设置商品规格
     * 
     * @param goodsModel
     *          商品规格
     */
    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
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

	@Override
	public String toString() {
		return "Stock [id=" + id + ", uuid=" + uuid + ", entryDate=" + entryDate + ", entryType=" + entryType
				+ ", ownerCode=" + ownerCode + ", pn=" + pn + ", sku=" + sku + ", barcode=" + barcode + ", goodsUuid="
				+ goodsUuid + ", item=" + item + ", emsNo=" + emsNo + ", entryQty=" + entryQty + ", inStock=" + inStock
				+ ", preAllocationStock=" + preAllocationStock + ", allocatedStock=" + allocatedStock + ", mu=" + mu
				+ ", warehouse=" + warehouse + ", storage=" + storage + ", location=" + location + ", packagingNo="
				+ packagingNo + ", palletNo=" + palletNo + ", isQualifed=" + isQualifed + ", amounts=" + amounts
				+ ", currency=" + currency + ", plUuid=" + plUuid + ", receivingNoteNo=" + receivingNoteNo
				+ ", packagingQty=" + packagingQty + ", secondaryPackagingQty=" + secondaryPackagingQty + ", version="
				+ version + ", expirationDate=" + expirationDate + ", manufactureLotNo=" + manufactureLotNo
				+ ", receivingLotNo=" + receivingLotNo + ", status=" + status + ", goodsName=" + goodsName
				+ ", goodsModel=" + goodsModel + ", packagingKey=" + packagingKey + "]";
	}

}