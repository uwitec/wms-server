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
 * 商品基础资料表(GOODS)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-09
 */
public class Goods {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -3256927913860742819L;

    /**  */
    private Long id;

    /** 全球唯一标识，其他表关联字段 */
    private String uuid;

    /** 商品SKU编码 */
    private String sku;

    /** 货主料号(货号) */
    private String pn;

    /** 商品条形码 */
    private String barcode;

    /** 货主代码(海关10位编码或社会统一代码) */
    private String ownerCode;

    /** 品名 */
    private String name;

    /** 规格型号 */
    private String model;

    /** 默认计量单位代码(海关) */
    private String defaultUnit;

    /** 商品状态 */
    private String status;

    /** 长(MM) */
    private BigDecimal length;

    /** 宽(MM) */
    private BigDecimal width;

    /** 高(MM) */
    private BigDecimal height;

    /** 存储类型 */
    private String storageType;

    /** 商品类别 */
    private String category;

    /** 库存下限 */
    private BigDecimal stockLowest;

    /** 库存上限 */
    private BigDecimal stockHighest;

    /** 过期预警天数 */
    private Integer expWarnDays;

    /** 保质期天数 */
    private Integer shelfLifeDays;

    /** 销售单价 */
    private BigDecimal salePrice;

    /** 采购单价 */
    private BigDecimal purchasePrice;

    /** 出库策略 */
    private String deliveryMode;

    /** 拆包装规则 */
    private String unpackingMode;

    /** 入库验货 */
    private Boolean inventoryChecking;

    /** 默认项号 */
    private String defaultItemNo;

    /** 默认账册号 */
    private String defaultEmsNo;

    /** 是否启用 */
    private Boolean isEnable;

    /** 描述及备注 */
    private String memo;

    /** 计费标准：W 按重量 L 按体积 */
    private String chargingStandand;

    /** 图片附件等 */
    private String attachments;

    /** 创建人 */
    private String createBy;

    /** 创建日期 */
    private Date createDate;

    /** 更新人 */
    private String lastUpdateBy;

    /** 更新日期 */
    private Date lastUpdateDate;

    /** 重量(kg) */
    private BigDecimal weight;

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
     * 获取全球唯一标识，其他表关联字段
     * 
     * @return 全球唯一标识
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * 设置全球唯一标识，其他表关联字段
     * 
     * @param uuid
     *          全球唯一标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取商品SKU编码
     * 
     * @return 商品SKU编码
     */
    public String getSku() {
        return this.sku;
    }

    /**
     * 设置商品SKU编码
     * 
     * @param sku
     *          商品SKU编码
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * 获取货主料号(货号)
     * 
     * @return 货主料号(货号)
     */
    public String getPn() {
        return this.pn;
    }

    /**
     * 设置货主料号(货号)
     * 
     * @param pn
     *          货主料号(货号)
     */
    public void setPn(String pn) {
        this.pn = pn;
    }

    /**
     * 获取商品条形码
     * 
     * @return 商品条形码
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * 设置商品条形码
     * 
     * @param barcode
     *          商品条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
     * @param owner
     *          货主代码(海关10位编码或社会统一代码)
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * 获取品名
     * 
     * @return 品名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置品名
     * 
     * @param name
     *          品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取规格型号
     * 
     * @return 规格型号
     */
    public String getModel() {
        return this.model;
    }

    /**
     * 设置规格型号
     * 
     * @param model
     *          规格型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取默认计量单位代码(海关)
     * 
     * @return 默认计量单位代码(海关)
     */
    public String getDefaultUnit() {
        return this.defaultUnit;
    }

    /**
     * 设置默认计量单位代码(海关)
     * 
     * @param defaultUnit
     *          默认计量单位代码(海关)
     */
    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    /**
     * 获取商品状态
     * 
     * @return 商品状态
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置商品状态
     * 
     * @param status
     *          商品状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取长(MM)
     * 
     * @return 长(MM)
     */
    public BigDecimal getLength() {
        return this.length == null ? new BigDecimal(0) : length;
    }

    /**
     * 设置长(MM)
     * 
     * @param length
     *          长(MM)
     */
    public void setLength(BigDecimal length) {
        this.length = length;
    }

    /**
     * 获取宽(MM)
     * 
     * @return 宽(MM)
     */
    public BigDecimal getWidth() {
        return this.width == null ? new BigDecimal(0) : width;
    }

    /**
     * 设置宽(MM)
     * 
     * @param width
     *          宽(MM)
     */
    public void setWidth(BigDecimal width) {
    	if (width== null) {
    		width=new BigDecimal(0);
		}
        this.width = width;
    }

    /**
     * 获取高(MM)
     * 
     * @return 高(MM)
     */
    public BigDecimal getHeight() {
        return this.height == null ? new BigDecimal(0) : height;
    }

    /**
     * 设置高(MM)
     * 
     * @param height
     *          高(MM)
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * 获取存储类型
     * 
     * @return 存储类型
     */
    public String getStorageType() {
        return this.storageType;
    }

    /**
     * 设置存储类型
     * 
     * @param storageType
     *          存储类型
     */
    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * 获取商品类别
     * 
     * @return 商品类别
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 设置商品类别
     * 
     * @param category
     *          商品类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取库存下限
     * 
     * @return 库存下限
     */
    public BigDecimal getStockLowest() {
        return this.stockLowest  == null ? new BigDecimal(0) : stockLowest;
    }

    /**
     * 设置库存下限
     * 
     * @param stockLowest
     *          库存下限
     */
    public void setStockLowest(BigDecimal stockLowest) {
        this.stockLowest = stockLowest;
    }

    /**
     * 获取库存上限
     * 
     * @return 库存上限
     */
    public BigDecimal getStockHighest() {
        return this.stockHighest;
    }

    /**
     * 设置库存上限
     * 
     * @param stockHighest
     *          库存上限
     */
    public void setStockHighest(BigDecimal stockHighest) {
        this.stockHighest = stockHighest;
    }

    /**
     * 获取过期预警天数
     * 
     * @return 过期预警天数
     */
    public Integer getExpWarnDays() {
        return this.expWarnDays  == null ? 0 : expWarnDays;
    }

    /**
     * 设置过期预警天数
     * 
     * @param expWarnDays
     *          过期预警天数
     */
    public void setExpWarnDays(Integer expWarnDays) {
        this.expWarnDays = expWarnDays;
    }

    /**
     * 获取保质期天数
     * 
     * @return 保质期天数
     */
    public Integer getShelfLifeDays() {
        return this.shelfLifeDays == null ? 0 : shelfLifeDays;
    }

    /**
     * 设置保质期天数
     * 
     * @param shelfLifeDays
     *          保质期天数
     */
    public void setShelfLifeDays(Integer shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }

    /**
     * 获取销售单价
     * 
     * @return 销售单价
     */
    public BigDecimal getSalePrice() {
        return this.salePrice == null ? new BigDecimal(0) : salePrice;
    }

    /**
     * 设置销售单价
     * 
     * @param salePrice
     *          销售单价
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 获取采购单价
     * 
     * @return 采购单价
     */
    public BigDecimal getPurchasePrice() {
        return this.purchasePrice == null ? new BigDecimal(0) : purchasePrice;
    }

    /**
     * 设置采购单价
     * 
     * @param purchasePrice
     *          采购单价
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * 获取出库策略
     * 
     * @return 出库策略
     */
    public String getDeliveryMode() {
        return this.deliveryMode;
    }

    /**
     * 设置出库策略
     * 
     * @param deliveryMode
     *          出库策略
     */
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    /**
     * 获取拆包装规则
     * 
     * @return 拆包装规则
     */
    public String getUnpackingMode() {
        return this.unpackingMode;
    }

    /**
     * 设置拆包装规则
     * 
     * @param unpackingMode
     *          拆包装规则
     */
    public void setUnpackingMode(String unpackingMode) {
        this.unpackingMode = unpackingMode;
    }

    /**
     * 获取入库验货
     * 
     * @return 入库验货
     */
    public Boolean getInventoryChecking() {
        return this.inventoryChecking;
    }

    /**
     * 设置入库验货
     * 
     * @param inventoryChecking
     *          入库验货
     */
    public void setInventoryChecking(Boolean inventoryChecking) {
        this.inventoryChecking = inventoryChecking;
    }

    /**
     * 获取默认项号
     * 
     * @return 默认项号
     */
    public String getDefaultItemNo() {
        return this.defaultItemNo;
    }

    /**
     * 设置默认项号
     * 
     * @param defaultItemNo
     *          默认项号
     */
    public void setDefaultItemNo(String defaultItemNo) {
        this.defaultItemNo = defaultItemNo;
    }

    /**
     * 获取默认账册号
     * 
     * @return 默认账册号
     */
    public String getDefaultEmsNo() {
        return this.defaultEmsNo;
    }

    /**
     * 设置默认账册号
     * 
     * @param defaultEmsNo
     *          默认账册号
     */
    public void setDefaultEmsNo(String defaultEmsNo) {
        this.defaultEmsNo = defaultEmsNo;
    }

    /**
     * 获取是否启用
     * 
     * @return 是否启用
     */
    public Boolean getIsEnable() {
    	if (isEnable==null) {
    		isEnable=true;
		}
        return this.isEnable;
    }

    /**
     * 设置是否启用
     * 
     * @param isEnable
     *          是否启用
     */
    public void setIsEnable(Boolean isEnable) {
    	
        this.isEnable = isEnable;
    }

    /**
     * 获取描述及备注
     * 
     * @return 描述及备注
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 设置描述及备注
     * 
     * @param memo
     *          描述及备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取计费标准：W 按重量 L 按体积
     * 
     * @return 计费标准
     */
    public String getChargingStandand() {
        return this.chargingStandand;
    }

    /**
     * 设置计费标准：W 按重量 L 按体积
     * 
     * @param chargingStandand
     *          计费标准
     */
    public void setChargingStandand(String chargingStandand) {
        this.chargingStandand = chargingStandand;
    }

    /**
     * 获取图片附件等
     * 
     * @return 图片附件等
     */
    public String getAttachments() {
        return this.attachments;
    }

    /**
     * 设置图片附件等
     * 
     * @param attachments
     *          图片附件等
     */
    public void setAttachments(String attachments) {
        this.attachments = attachments;
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
     *          创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * 获取更新日期
     * 
     * @return 更新日期
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 设置更新日期
     * 
     * @param lastUpdateDate
     *          更新日期
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * 获取重量(kg)
     * 
     * @return 重量(kg)
     */
    public BigDecimal getWeight() {
        return this.weight == null ? new BigDecimal(0) : weight;
    }

    /**
     * 设置重量(kg)
     * 
     * @param weight
     *          重量(kg)
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

	@Override
	public String toString() {
		return "Goods [id=" + id + ", uuid=" + uuid + ", sku=" + sku + ", pn=" + pn + ", barcode=" + barcode
				+ ", ownerCode=" + ownerCode + ", name=" + name + ", model=" + model + ", defaultUnit=" + defaultUnit
				+ ", status=" + status + ", length=" + length + ", width=" + width + ", height=" + height
				+ ", storageType=" + storageType + ", category=" + category + ", stockLowest=" + stockLowest
				+ ", stockHighest=" + stockHighest + ", expWarnDays=" + expWarnDays + ", shelfLifeDays=" + shelfLifeDays
				+ ", salePrice=" + salePrice + ", purchasePrice=" + purchasePrice + ", deliveryMode=" + deliveryMode
				+ ", unpackingMode=" + unpackingMode + ", inventoryChecking=" + inventoryChecking + ", defaultItemNo="
				+ defaultItemNo + ", defaultEmsNo=" + defaultEmsNo + ", isEnable=" + isEnable + ", memo=" + memo
				+ ", chargingStandand=" + chargingStandand + ", attachments=" + attachments + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", weight=" + weight + "]";
	}
    
    
    
    
}