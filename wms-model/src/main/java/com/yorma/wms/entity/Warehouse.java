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
 * 仓库(WAREHOUSE)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-09
 */
public class Warehouse {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 8724239775941752869L;

    /**  */
    private Long id;

    /** 仓库代码 */
    private String code;

    /** 仓库简称 */
    private String name;

    /** 仓库全称 */
    private String fullName;

    /** 仓库类型：QNBS 区内保税 QNWS 区内完税 QWPH区外普货 */
    private String type;

    /** 企业代码 */
    private String ownerCode;

    /** 地址 */
    private String address;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String phones;

    /** 传真 */
    private String fax;

    /** 仓库描述 */
    private String description;

    /** 备注 */
    private String memo;

    /** 是否启用 */
    private Boolean isEnable;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;

    /** 最后更新人 */
    private String lastUpdateBy;

    /** 最后更新时间 */
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
     * 获取仓库代码
     * 
     * @return 仓库代码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置仓库代码
     * 
     * @param code
     *          仓库代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取仓库简称
     * 
     * @return 仓库简称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置仓库简称
     * 
     * @param name
     *          仓库简称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取仓库全称
     * 
     * @return 仓库全称
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * 设置仓库全称
     * 
     * @param fullName
     *          仓库全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取仓库类型：QNBS 区内保税 QNWS 区内完税 QWPH区外普货
     * 
     * @return 仓库类型
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置仓库类型：QNBS 区内保税 QNWS 区内完税 QWPH区外普货
     * 
     * @param type
     *          仓库类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取企业代码
     * 
     * @return 企业代码
     */
    public String getOwnerCode() {
        return this.ownerCode;
    }

    /**
     * 设置企业代码
     * 
     * @param ownerCode
     *          企业代码
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * 获取地址
     * 
     * @return 地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置地址
     * 
     * @param address
     *          地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联系人
     * 
     * @return 联系人
     */
    public String getContacts() {
        return this.contacts;
    }

    /**
     * 设置联系人
     * 
     * @param contacts
     *          联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取联系电话
     * 
     * @return 联系电话
     */
    public String getPhones() {
        return this.phones;
    }

    /**
     * 设置联系电话
     * 
     * @param phones
     *          联系电话
     */
    public void setPhones(String phones) {
        this.phones = phones;
    }

    /**
     * 获取传真
     * 
     * @return 传真
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * 设置传真
     * 
     * @param fax
     *          传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取仓库描述
     * 
     * @return 仓库描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置仓库描述
     * 
     * @param description
     *          仓库描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取备注
     * 
     * @return 备注
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 设置备注
     * 
     * @param memo
     *          备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取是否启用
     * 
     * @return 是否启用
     */
    public Boolean getIsEnable() {
        return this.isEnable == null ? true	:isEnable;
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
     * 获取最后更新人
     * 
     * @return 最后更新人
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * 设置最后更新人
     * 
     * @param lastUpdateBy
     *          最后更新人
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * 获取最后更新时间
     * 
     * @return 最后更新时间
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 设置最后更新时间
     * 
     * @param lastUpdateDate
     *          最后更新时间
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", code=" + code + ", name=" + name + ", fullName=" + fullName + ", type=" + type
				+ ", ownerCode=" + ownerCode + ", address=" + address + ", contacts=" + contacts + ", phones=" + phones
				+ ", fax=" + fax + ", description=" + description + ", memo=" + memo + ", isEnable=" + isEnable
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", lastUpdateBy=" + lastUpdateBy
				+ ", lastUpdateDate=" + lastUpdateDate + "]";
	}
    
    
    
}