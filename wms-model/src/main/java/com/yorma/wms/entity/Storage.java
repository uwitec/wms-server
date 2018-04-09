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
 * 库区(REGION)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-10
 */
public class Storage {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 2873067578742701715L;

    /**  */
    private Long id;

    /** 库区代码 */
    private String code;

    /** 仓库代码 */
    private String warehouseCode;

    /** 库区名称 */
    private String name;

    /** 位置 */
    private String position;

    /** 面积 */
    private BigDecimal area;

    /** 负责人 */
    private String header;

    /** 库区类型：RA 收货区 PA 拣货区 OA 理货区 GQA 良品区 NGQA 不良品区 DA 死货区 */
    private String type;

    /**  */
    private String description;

    /**  */
    private String memo;

    /**  */
    private Boolean isEnable;

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
     * 获取库区代码
     * 
     * @return 库区代码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置库区代码
     * 
     * @param code
     *          库区代码
     */
    public void setCode(String code) {
        this.code = code;
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
     *          仓库代码
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * 获取库区名称
     * 
     * @return 库区名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置库区名称
     * 
     * @param name
     *          库区名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取位置
     * 
     * @return 位置
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 设置位置
     * 
     * @param position
     *          位置
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取面积
     * 
     * @return 面积
     */
    public BigDecimal getArea() {
        return this.area == null ? new BigDecimal(0) : area;
    }

    /**
     * 设置面积
     * 
     * @param area
     *          面积
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * 获取负责人
     * 
     * @return 负责人
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * 设置负责人
     * 
     * @param header
     *          负责人
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 获取库区类型：RA 收货区 PA 拣货区 OA 理货区 GQA 良品区 NGQA 不良品区 DA 死货区
     * 
     * @return 库区类型
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置库区类型：RA 收货区 PA 拣货区 OA 理货区 GQA 良品区 NGQA 不良品区 DA 死货区
     * 
     * @param type
     *          库区类型
     */
    public void setType(String type) {
        this.type = type;
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
		return "Storage [id=" + id + ", code=" + code + ", warehouseCode=" + warehouseCode + ", name=" + name
				+ ", position=" + position + ", area=" + area + ", header=" + header + ", type=" + type
				+ ", description=" + description + ", memo=" + memo + ", isEnable=" + isEnable + ", createBy="
				+ createBy + ", lastUpdateBy=" + lastUpdateBy + ", createDate=" + createDate + ", lastUpdateDate="
				+ lastUpdateDate + "]";
	}
    
    
    
}