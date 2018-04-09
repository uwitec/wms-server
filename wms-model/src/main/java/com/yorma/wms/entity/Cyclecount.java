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

import java.util.Date;

/**
 * 盘点表(CYCLECOUNT)
 * 
 * @author su
 * @version 1.0.0 2017-12-06
 */
public class Cyclecount {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -3999249008461709451L;

    /**  */
    private Long id;

    /** 盘点单号 */
    private String cyclecountNo;

    /** 盘点时间 */
    private Date cyclecountTime;

    /** 盘点仓库 */
    private String warehouse;

    /** 盘点库区 */
    private String storage;

    /** 盘点储位 */
    private String location;

    /** 料号 */
    private String pn;

    /** 盘点人 */
    private String cyclecountName;

    /** 备注 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;
    
    /** 状态（0：未校正，10：已校正） */
    private Integer status;
    
    /** 修改人 */
    private String lastUpdateBy;

    /** 修改日期 */
    private Date lastUpdateDate;
    
    /** 货主代码(海关10位编码或社会统一代码) */
    private String ownerCode;

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
     *          盘点单号
     */
    public void setCyclecountNo(String cyclecountNo) {
        this.cyclecountNo = cyclecountNo;
    }

    /**
     * 获取盘点时间
     * 
     * @return 盘点时间
     */
    public Date getCyclecountTime() {
        return this.cyclecountTime;
    }

    /**
     * 设置盘点时间
     * 
     * @param cyclecountTime
     *          盘点时间
     */
    public void setCyclecountTime(Date cyclecountTime) {
        this.cyclecountTime = cyclecountTime;
    }

    /**
     * 获取盘点仓库
     * 
     * @return 盘点仓库
     */
    public String getWarehouse() {
        return this.warehouse;
    }

    /**
     * 设置盘点仓库
     * 
     * @param warehouse
     *          盘点仓库
     */
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * 获取盘点库区
     * 
     * @return 盘点库区
     */
    public String getStorage() {
        return this.storage;
    }

    /**
     * 设置盘点库区
     * 
     * @param storage
     *          盘点库区
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取盘点储位
     * 
     * @return 盘点储位
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置盘点储位
     * 
     * @param location
     *          盘点储位
     */
    public void setLocation(String location) {
        this.location = location;
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
     * 获取盘点人
     * 
     * @return 盘点人
     */
    public String getCyclecountName() {
        return this.cyclecountName;
    }

    /**
     * 设置盘点人
     * 
     * @param cyclecountName
     *          盘点人
     */
    public void setCyclecountName(String cyclecountName) {
        this.cyclecountName = cyclecountName;
    }

    /**
     * 获取备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置备注
     * 
     * @param remark
     *          备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取状态（0：未校正，10：已校正）
     * 
     * @return 状态（0：未校正
     */
    public Integer getStatus() {
        return this.status == null ? 0 : status;
    }

    /**
     * 设置状态（0：未校正，10：已校正）
     * 
     * @param status
     *          状态（0：未校正
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取修改人
     * 
     * @return 修改人
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }
    
    /**
     * 设置修改人
     * 
     * @param lastUpdateBy
     *          修改人
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * 获取修改日期
     * 
     * @return 修改日期
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 设置修改日期
     * 
     * @param lastUpdateDate
     *          修改日期
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
     *          货主代码(海关10位编码或社会统一代码)
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }
    
	@Override
	public String toString() {
		return "Cyclecount [id=" + id + ", cyclecountNo=" + cyclecountNo + ", cyclecountTime=" + cyclecountTime
				+ ", warehouse=" + warehouse + ", storage=" + storage + ", location=" + location + ", pn=" + pn
				+ ", cyclecountName=" + cyclecountName + ", remark=" + remark + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", status=" + status + ", lastUpdateBy=" + lastUpdateBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", ownerCode=" + ownerCode + "]";
	}
    
    
    
}