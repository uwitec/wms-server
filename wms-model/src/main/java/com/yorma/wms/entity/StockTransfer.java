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
 * 移库(STOCK_TRANSFER)
 * 
 * @author su
 * @version 1.0.0 2017-12-14
 */
public class StockTransfer {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 5651605075862681176L;

    /**  */
    private Long id;

    /** 移库单号 */
    private String transferNo;

    /** 操作人 */
    private String operationName;

    /** 移库时间 */
    private Date transferDate;

    /** 出库单号 */
    private String deliveryNoteNo;

    /** 入库单号 */
    private String receivingNoteNo;

    /** 货主代码 */
    private String ownerCode;

    /** 创建人 */
    private String createBy;

    /** 创建日期 */
    private Date createDate;

    /** 备注 */
    private String remark;

    /** 移出仓库 */
    private String fromWarehouse;

    /** 移入仓库 */
    private String toWarehouse;

    /** 审核 */
    private boolean isAudit;

    /**
     * 构造无参方法
     */
    public StockTransfer() {
		
	}

    /**
     * 构造有参方法
     */
	public StockTransfer(Long id, String transferNo, String operationName, Date transferDate, String deliveryNoteNo,
			String receivingNoteNo, String ownerCode, String createBy, Date createDate, String remark,
			String fromWarehouse, String toWarehouse, boolean isAudit) {
		this.id = id;
		this.transferNo = transferNo;
		this.operationName = operationName;
		this.transferDate = transferDate;
		this.deliveryNoteNo = deliveryNoteNo;
		this.receivingNoteNo = receivingNoteNo;
		this.ownerCode = ownerCode;
		this.createBy = createBy;
		this.createDate = createDate;
		this.remark = remark;
		this.fromWarehouse = fromWarehouse;
		this.toWarehouse = toWarehouse;
		this.isAudit = isAudit;
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
     * 获取操作人
     * 
     * @return 操作人
     */
    public String getOperationName() {
        return this.operationName;
    }

    /**
     * 设置操作人
     * 
     * @param operationName
     *          操作人
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 获取移库时间
     * 
     * @return 移库时间
     */
    public Date getTransferDate() {
        return this.transferDate;
    }

    /**
     * 设置移库时间
     * 
     * @param transferDate
     *          移库时间
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * 获取出库单号
     * 
     * @return 出库单号
     */
    public String getDeliveryNoteNo() {
        return this.deliveryNoteNo;
    }

    /**
     * 设置出库单号
     * 
     * @param deliveryNoteNo
     *          出库单号
     */
    public void setDeliveryNoteNo(String deliveryNoteNo) {
        this.deliveryNoteNo = deliveryNoteNo;
    }

    /**
     * 获取入库单号
     * 
     * @return 入库单号
     */
    public String getReceivingNoteNo() {
        return this.receivingNoteNo;
    }

    /**
     * 设置入库单号
     * 
     * @param receivingNoteNo
     *          入库单号
     */
    public void setReceivingNoteNo(String receivingNoteNo) {
        this.receivingNoteNo = receivingNoteNo;
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
     *          货主代码
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
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
     * 获取审核
     * 
     * @return 审核
     */
    public boolean getIsAudit() {
        return this.isAudit;
    }

    /**
     * 设置审核
     * 
     * @param isAudit
     *          审核
     */
    public void setIsAudit(boolean isAudit) {
        this.isAudit = isAudit;
    }

	@Override
	public String toString() {
		return "StockTransfer [id=" + id + ", transferNo=" + transferNo + ", operationName=" + operationName
				+ ", transferDate=" + transferDate + ", deliveryNoteNo=" + deliveryNoteNo + ", receivingNoteNo="
				+ receivingNoteNo + ", ownerCode=" + ownerCode + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", remark=" + remark + ", fromWarehouse=" + fromWarehouse + ", toWarehouse=" + toWarehouse
				+ ", isAudit=" + isAudit + "]";
	}
    
}