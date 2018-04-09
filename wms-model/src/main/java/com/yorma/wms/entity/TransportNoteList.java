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

/**
 * 运输配送明细(TRANSPORT_NOTE_LIST)
 * 
 * @author su
 * @version 1.0.0 2017-11-02
 */
public class TransportNoteList {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 5749801987025181999L;

    /**  */
    private Long id;

    /**  */
    private String uuid;

    /** 送货单号 */
    private String transportNoteNo;

    /** 货主	 */
    private String ownerCode;

    /** 拣货货物信息表UUID */
    private String plUuid;

    /** 集装容积条码号 */
    private String palletNo;

    /** 包装条码号 */
    private String packagingNo;

    /** 包装键 */
    private String packagingKey;

    /** 主单位 */
    private String mu;

    /** 料号 */
    private String pn;

    /** 出库数量 */
    private Integer deliveryQty;

    /** 品名 */
    private String goodsName;

    /** 出库单号 */
    private String deliveryNoteNo;

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
     * 获取
     * 
     * @return 
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * 设置
     * 
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * 获取货主	
     * 
     * @return 货主	
     */
    public String getOwnerCode() {
        return this.ownerCode;
    }

    /**
     * 设置货主	
     * 
     * @param ownerCode
     *          货主	
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * 获取拣货货物信息表UUID
     * 
     * @return 拣货货物信息表UUID
     */
    public String getPlUuid() {
        return this.plUuid;
    }

    /**
     * 设置拣货货物信息表UUID
     * 
     * @param plUuid
     *          拣货货物信息表UUID
     */
    public void setPlUuid(String plUuid) {
        this.plUuid = plUuid;
    }

    /**
     * 获取集装容积条码号
     * 
     * @return 集装容积条码号
     */
    public String getPalletNo() {
        return this.palletNo;
    }

    /**
     * 设置集装容积条码号
     * 
     * @param palletNo
     *          集装容积条码号
     */
    public void setPalletNo(String palletNo) {
        this.palletNo = palletNo;
    }

    /**
     * 获取包装条码号
     * 
     * @return 包装条码号
     */
    public String getPackagingNo() {
        return this.packagingNo;
    }

    /**
     * 设置包装条码号
     * 
     * @param packagingNo
     *          包装条码号
     */
    public void setPackagingNo(String packagingNo) {
        this.packagingNo = packagingNo;
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

    /**
     * 获取主单位
     * 
     * @return 主单位
     */
    public String getMu() {
        return this.mu;
    }

    /**
     * 设置主单位
     * 
     * @param mu
     *          主单位
     */
    public void setMu(String mu) {
        this.mu = mu;
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
     * 获取出库数量
     * 
     * @return 出库数量
     */
    public Integer getDeliveryQty() {
        return this.deliveryQty == null ? 0 : deliveryQty;
    }

    /**
     * 设置出库数量
     * 
     * @param deliveryQty
     *          出库数量
     */
    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
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

	@Override
	public String toString() {
		return "TransportNoteList [id=" + id + ", uuid=" + uuid + ", transportNoteNo=" + transportNoteNo
				+ ", ownerCode=" + ownerCode + ", plUuid=" + plUuid + ", palletNo=" + palletNo + ", packagingNo="
				+ packagingNo + ", packagingKey=" + packagingKey + ", mu=" + mu + ", pn=" + pn + ", deliveryQty="
				+ deliveryQty + ", goodsName=" + goodsName + ", deliveryNoteNo=" + deliveryNoteNo + "]";
	}
    
}