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
 * 货物包装信息(PACK_INFOMATION)
 * 
 * @author subiao
 * @version 1.0.0 2017-08-29
 */
public class PackInfomation {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -1354776713356567193L;

    /**  */
    private Long id;

    /** 商品唯一码 */
    private String goodsUuid;

    /** 第一单位(主单位) */
    private String unit1;

    /** 第一数量 */
    private Integer qty1;

    /** 第二单位 */
    private String unit2;

    /** 第二数量 */
    private Integer qty2;

    /** 第三单位 */
    private String unit3;

    /** 第三数量 */
    private Integer qty3;

    /** 第四单位 */
    private String unit4;

    /** 第四数量 */
    private Integer qty4;

    /** 第五单位 */
    private String unit5;

    /** 第五数量 */
    private Integer qty5;

    /** 默认 */
    private Integer isDefault;

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
     * 获取商品唯一码
     * 
     * @return 商品唯一码
     */
    public String getGoodsUuid() {
        return this.goodsUuid;
    }

    /**
     * 设置商品唯一码
     * 
     * @param goodsUuid
     *          商品唯一码
     */
    public void setGoodsUuid(String goodsUuid) {
        this.goodsUuid = goodsUuid;
    }

    /**
     * 获取第一单位(主单位)
     * 
     * @return 第一单位(主单位)
     */
    public String getUnit1() {
        return this.unit1;
    }

    /**
     * 设置第一单位(主单位)
     * 
     * @param unit1
     *          第一单位(主单位)
     */
    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    /**
     * 获取第一数量
     * 
     * @return 第一数量
     */
    public Integer getQty1() {
        return this.qty1;
    }

    /**
     * 设置第一数量
     * 
     * @param qty1
     *          第一数量
     */
    public void setQty1(Integer qty1) {
        this.qty1 = qty1;
    }

    /**
     * 获取第二单位
     * 
     * @return 第二单位
     */
    public String getUnit2() {
        return this.unit2;
    }

    /**
     * 设置第二单位
     * 
     * @param unit2
     *          第二单位
     */
    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    /**
     * 获取第二数量
     * 
     * @return 第二数量
     */
    public Integer getQty2() {
        return this.qty2;
    }

    /**
     * 设置第二数量
     * 
     * @param qty2
     *          第二数量
     */
    public void setQty2(Integer qty2) {
        this.qty2 = qty2;
    }

    /**
     * 获取第三单位
     * 
     * @return 第三单位
     */
    public String getUnit3() {
        return this.unit3;
    }

    /**
     * 设置第三单位
     * 
     * @param unit3
     *          第三单位
     */
    public void setUnit3(String unit3) {
        this.unit3 = unit3;
    }

    /**
     * 获取第三数量
     * 
     * @return 第三数量
     */
    public Integer getQty3() {
        return this.qty3;
    }

    /**
     * 设置第三数量
     * 
     * @param qty3
     *          第三数量
     */
    public void setQty3(Integer qty3) {
        this.qty3 = qty3;
    }

    /**
     * 获取第四单位
     * 
     * @return 第四单位
     */
    public String getUnit4() {
        return this.unit4;
    }

    /**
     * 设置第四单位
     * 
     * @param unit4
     *          第四单位
     */
    public void setUnit4(String unit4) {
        this.unit4 = unit4;
    }

    /**
     * 获取第四数量
     * 
     * @return 第四数量
     */
    public Integer getQty4() {
        return this.qty4;
    }

    /**
     * 设置第四数量
     * 
     * @param qty4
     *          第四数量
     */
    public void setQty4(Integer qty4) {
        this.qty4 = qty4;
    }

    /**
     * 获取第五单位
     * 
     * @return 第五单位
     */
    public String getUnit5() {
        return this.unit5;
    }

    /**
     * 设置第五单位
     * 
     * @param unit5
     *          第五单位
     */
    public void setUnit5(String unit5) {
        this.unit5 = unit5;
    }

    /**
     * 获取第五数量
     * 
     * @return 第五数量
     */
    public Integer getQty5() {
        return this.qty5;
    }

    /**
     * 设置第五数量
     * 
     * @param qty5
     *          第五数量
     */
    public void setQty5(Integer qty5) {
        this.qty5 = qty5;
    }

    /**
     * 获取默认
     * 
     * @return 默认
     */
    public Integer getIsDefault() {
    	if (isDefault==null) {
    		isDefault=0;
		}
        return this.isDefault;
    }

    /**
     * 设置默认
     * 
     * @param isDefault
     *          默认
     */
    public void setIsDefault(Integer isDefault) {
    	
        this.isDefault = isDefault;
    }

	@Override
	public String toString() {
		return "PackInfomation [id=" + id + ", goodsUuid=" + goodsUuid + ", unit1=" + unit1 + ", qty1=" + qty1
				+ ", unit2=" + unit2 + ", qty2=" + qty2 + ", unit3=" + unit3 + ", qty3=" + qty3 + ", unit4=" + unit4
				+ ", qty4=" + qty4 + ", unit5=" + unit5 + ", qty5=" + qty5 + ", isDefault=" + isDefault + "]";
	}
    
    
    
}