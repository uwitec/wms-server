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
 * 送货单(TRANSPORT_NOTE)
 * 
 * @author su
 * @version 1.0.0 2017-11-06
 */
public class TransportNote {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 1175558134705416585L;

    /**  */
    private Long id;

    /** 送货单号 */
    private String transportNoteNo;

    /** .仓库 */
    private String warehouse;

    /** 承运人 */
    private String transportCode;

    /** 车型 */
    private String truckType;

    /** 车牌号 */
    private String truckNumber;

    /** 收货人 */
    private String consigneeCode;

    /** 送货地址 */
    private String address;

    /** 送货要求 */
    private String remark;

    /** 联系人 */
    private String consigneeBy;

    /** 联系电话 */
    private String consigneePhone;

    /** 客户签收时间 */
    private Date consigneeDate;

    /** 客户签收人 */
    private String consigneeName;

    /** 创建人 */
    private String createBy;

    /** 创建日期 */
    private Date createDate;

    /** 最后修改人 */
    private String lastUpdateBy;

    /** 最后修改日期	 */
    private Date lastUpdateDate;

    /** 状态位(10:草稿、20:配载中、30:已装车 ) */
    private Integer status;

    /** 到货时间 */
    private Date arrivalDate;

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
     * 获取送货单号
     * 
     * @return 送货单号
     */
    public String getTransportNoteNo() {
        return this.transportNoteNo;
    }

    /**
     * 设置送货单号
     * 
     * @param transportNoteNo
     *          送货单号
     */
    public void setTransportNoteNo(String transportNoteNo) {
        this.transportNoteNo = transportNoteNo;
    }

    /**
     * 获取.仓库
     * 
     * @return .仓库
     */
    public String getWarehouse() {
        return this.warehouse;
    }

    /**
     * 设置.仓库
     * 
     * @param warehouse
     *          .仓库
     */
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * 获取承运人
     * 
     * @return 承运人
     */
    public String getTransportCode() {
        return this.transportCode;
    }

    /**
     * 设置承运人
     * 
     * @param transportCode
     *          承运人
     */
    public void setTransportCode(String transportCode) {
        this.transportCode = transportCode;
    }

    /**
     * 获取车型
     * 
     * @return 车型
     */
    public String getTruckType() {
        return this.truckType;
    }

    /**
     * 设置车型
     * 
     * @param truckType
     *          车型
     */
    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    /**
     * 获取车牌号
     * 
     * @return 车牌号
     */
    public String getTruckNumber() {
        return this.truckNumber;
    }

    /**
     * 设置车牌号
     * 
     * @param truckNumber
     *          车牌号
     */
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    /**
     * 获取收货人
     * 
     * @return 收货人
     */
    public String getConsigneeCode() {
        return this.consigneeCode;
    }

    /**
     * 设置收货人
     * 
     * @param consigneeCode
     *          收货人
     */
    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    /**
     * 获取送货地址
     * 
     * @return 送货地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置送货地址
     * 
     * @param address
     *          送货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取送货要求
     * 
     * @return 送货要求
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置送货要求
     * 
     * @param remark
     *          送货要求
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取联系人
     * 
     * @return 联系人
     */
    public String getConsigneeBy() {
        return this.consigneeBy;
    }

    /**
     * 设置联系人
     * 
     * @param consigneeBy
     *          联系人
     */
    public void setConsigneeBy(String consigneeBy) {
        this.consigneeBy = consigneeBy;
    }

    /**
     * 获取联系电话
     * 
     * @return 联系电话
     */
    public String getConsigneePhone() {
        return this.consigneePhone;
    }

    /**
     * 设置联系电话
     * 
     * @param consigneePhone
     *          联系电话
     */
    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    /**
     * 获取客户签收时间
     * 
     * @return 客户签收时间
     */
    public Date getConsigneeDate() {
        return this.consigneeDate;
    }

    /**
     * 设置客户签收时间
     * 
     * @param consigneeDate
     *          客户签收时间
     */
    public void setConsigneeDate(Date consigneeDate) {
        this.consigneeDate = consigneeDate;
    }

    /**
     * 获取客户签收人
     * 
     * @return 客户签收人
     */
    public String getConsigneeName() {
        return this.consigneeName;
    }

    /**
     * 设置客户签收人
     * 
     * @param consigneeName
     *          客户签收人
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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
     * 获取最后修改人
     * 
     * @return 最后修改人
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * 设置最后修改人
     * 
     * @param lastUpdateBy
     *          最后修改人
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * 获取最后修改日期	
     * 
     * @return 最后修改日期	
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * 设置最后修改日期	
     * 
     * @param lastUpdateDate
     *          最后修改日期	
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * 获取状态位(10:草稿、20:配载中、30:已装车 )
     * 
     * @return 状态位(10
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态位(10:草稿、20:配载中、30:已装车 )
     * 
     * @param status
     *          状态位(10
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取到货时间
     * 
     * @return 到货时间
     */
    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * 设置到货时间
     * 
     * @param arrivalDate
     *          到货时间
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}