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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 储位，库位(LOCATION)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-09
 */
public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4490832866810433983L;

	/**  */
	private Long id;

	/** 库位代码 */
	private String code;

	/** 库位条码 */
	private String barcode;

	/** 库区代码 */
	private String storageCode;

	/** 仓库代码 */
	private String warehouseCode;

	/** 载重 */
	private BigDecimal loadWeight;

	/** 容积 */
	private BigDecimal volume;

	/** 宽 */
	private BigDecimal width;

	/** 长 */
	private BigDecimal length;

	/** 高 */
	private BigDecimal height;

	/** 库位类型：PT 普通 HW 恒温 */
	private String type;

	/** 库位类别：RACK 货架 BULK 散装 */
	private String kind;

	/** 标记 */
	private String tag;

	/**  */
	private Boolean isEnable;

	/**  */
	private String description;

	/**  */
	private String memo;

	/**  */
	private String createBy;

	/**  */
	private String lastUpdateBy;

	/**  */
	private Date createDate;

	/**  */
	private Date lastUpdateDate;

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
	 * 获取库位代码
	 * 
	 * @return 库位代码
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 设置库位代码
	 * 
	 * @param code
	 *            库位代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取库位条码
	 * 
	 * @return 库位条码
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * 设置库位条码
	 * 
	 * @param barcode
	 *            库位条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 获取库区代码
	 * 
	 * @return 库区代码
	 */
	public String getStorageCode() {
		return this.storageCode;
	}

	/**
	 * 设置库区代码
	 * 
	 * @param regionCode
	 *            库区代码
	 */
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	/**
	 * 获取仓库代码
	 * 
	 * @return 仓库代码
	 */
	public String getWarehouseCode() {
		return this.warehouseCode;
	}

	/**
	 * 设置仓库代码
	 * 
	 * @param warehouseCode
	 *            仓库代码
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * 获取载重
	 * 
	 * @return 载重
	 */
	public BigDecimal getLoadWeight() {
		if (loadWeight==null) {
			loadWeight=new BigDecimal(0.00);
		}
		return this.loadWeight;
	}

	/**
	 * 设置载重
	 * 
	 * @param loadWeight
	 *            载重
	 */
	public void setLoadWeight(BigDecimal loadWeight) {
		
		this.loadWeight = loadWeight;
	}

	/**
	 * 获取容积
	 * 
	 * @return 容积
	 */
	public BigDecimal getVolume() {
		if (volume==null) {
			volume=new BigDecimal(0.00);
		}
		return this.volume;
	}

	/**
	 * 设置容积
	 * 
	 * @param volume
	 *            容积
	 */
	public void setVolume(BigDecimal volume) {
		
		this.volume = volume;
	}

	/**
	 * 获取宽
	 * 
	 * @return 宽
	 */
	public BigDecimal getWidth() {
		if (width==null) {
			width=new BigDecimal(0.00);
		}
		return this.width;
	}

	/**
	 * 设置宽
	 * 
	 * @param width
	 *            宽
	 */
	public void setWidth(BigDecimal width) {
		
		this.width = width;
	}

	/**
	 * 获取长
	 * 
	 * @return 长
	 */
	public BigDecimal getLength() {
		if (length==null) {
			length=new BigDecimal(0.00);
		}
		return this.length;
	}

	/**
	 * 设置长
	 * 
	 * @param length
	 *            长
	 */
	public void setLength(BigDecimal length) {
		
		this.length = length;
	}

	/**
	 * 获取高
	 * 
	 * @return 高
	 */
	public BigDecimal getHeight() {
		if (height==null) {
			height=new BigDecimal(0.00);
		}
		return this.height;
	}

	/**
	 * 设置高
	 * 
	 * @param height
	 *            高
	 */
	public void setHeight(BigDecimal height) {
		
		this.height = height;
	}

	/**
	 * 获取库位类型：PT 普通 HW 恒温
	 * 
	 * @return 库位类型
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 设置库位类型：PT 普通 HW 恒温
	 * 
	 * @param type
	 *            库位类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取库位类别：RACK 货架 BULK 散装
	 * 
	 * @return 库位类别
	 */
	public String getKind() {
		return this.kind;
	}

	/**
	 * 设置库位类别：RACK 货架 BULK 散装
	 * 
	 * @param kind
	 *            库位类别
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 获取标记
	 * 
	 * @return 标记
	 */
	public String getTag() {
		return this.tag;
	}

	/**
	 * 设置标记
	 * 
	 * @param tag
	 *            标记
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Boolean getIsEnable() {
		return this.isEnable;
	}

	/**
	 * 设置
	 * 
	 * @param isEnable
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * 设置
	 * 
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/**
	 * 设置
	 * 
	 * @param lastUpdateBy
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
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
	 * 获取
	 * 
	 * @return
	 */
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * 设置
	 * 
	 * @param lastUpdateDate
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", code=" + code + ", barcode=" + barcode + ", storageCode=" + storageCode
				+ ", warehouseCode=" + warehouseCode + ", loadWeight=" + loadWeight + ", volume=" + volume + ", width="
				+ width + ", length=" + length + ", height=" + height + ", type=" + type + ", kind=" + kind + ", tag="
				+ tag + ", isEnable=" + isEnable + ", description=" + description + ", memo=" + memo + ", createBy="
				+ createBy + ", lastUpdateBy=" + lastUpdateBy + ", createDate=" + createDate + ", lastUpdateDate="
				+ lastUpdateDate + "]";
	}
	
	
	
}