package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.PackInfomation;

/**
 * 包装分配Dto
 * @author subiao
 * 2017年9月19日
 */
public class PackInfomationDto extends PackInfomation {

	/** 货主名 */
	private String departName;

	/** 货主料号 */
	private String pn;
	
	private String unit1Name;
	private String unit2Name;
	private String unit3Name;
	private String unit4Name;
	private String unit5Name;

	/**
	 * 获取货主名
	 * @return	货主名
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 设置货主名
	 * @param departName	货主名
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * 获取货主料号
	 * @return	货主料号
	 */
	public String getPn() {
		return pn;
	}

	/**
	 * 设置货主料号
	 * @param pn	货主料号
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}
	
	/**
	 * 获取第一计量单位名称
	 * @return	第一计量单位名称
	 */
	public String getUnit1Name() {
		return unit1Name;
	}

	/**
	 * 设置第一计量单位名称
	 * @param unit1Name 第一计量单位名称
	 */
	public void setUnit1Name(String unit1Name) {
		this.unit1Name = unit1Name;
	}
	
	/**
	 * 获取第二计量单位名称
	 * @return	第二计量单位名称
	 */
	public String getUnit2Name() {
		return unit2Name;
	}


	/**
	 * 设置第二计量单位名称
	 * @param unit1Name 第二计量单位名称
	 */
	public void setUnit2Name(String unit2Name) {
		this.unit2Name = unit2Name;
	}

	/**
	 * 获取第三计量单位名称
	 * @return	第三计量单位名称
	 */
	public String getUnit3Name() {
		return unit3Name;
	}

	/**
	 * 设置第三计量单位名称
	 * @param unit1Name 第三计量单位名称
	 */
	public void setUnit3Name(String unit3Name) {
		this.unit3Name = unit3Name;
	}

	/**
	 * 获取第四计量单位名称
	 * @return	第四计量单位名称
	 */
	public String getUnit4Name() {
		return unit4Name;
	}

	/**
	 * 设置第四计量单位名称
	 * @param unit1Name 第四计量单位名称
	 */
	public void setUnit4Name(String unit4Name) {
		this.unit4Name = unit4Name;
	}

	/**
	 * 获取第五计量单位名称
	 * @return	第五计量单位名称
	 */
	public String getUnit5Name() {
		return unit5Name;
	}

	/**
	 * 设置第五计量单位名称
	 * @param unit1Name 第五计量单位名称
	 */
	public void setUnit5Name(String unit5Name) {
		this.unit5Name = unit5Name;
	}

	@Override
	public String toString() {
		return "PackInfomationDto [getDepartName()=" + getDepartName() + ", getPn()=" + getPn() + ", getUnit1Name()="
				+ getUnit1Name() + ", getUnit2Name()=" + getUnit2Name() + ", getUnit3Name()=" + getUnit3Name()
				+ ", getUnit4Name()=" + getUnit4Name() + ", getUnit5Name()=" + getUnit5Name() + ", getId()=" + getId()
				+ ", getGoodsUuid()=" + getGoodsUuid() + ", getUnit1()=" + getUnit1() + ", getQty1()=" + getQty1()
				+ ", getUnit2()=" + getUnit2() + ", getQty2()=" + getQty2() + ", getUnit3()=" + getUnit3()
				+ ", getQty3()=" + getQty3() + ", getUnit4()=" + getUnit4() + ", getQty4()=" + getQty4()
				+ ", getUnit5()=" + getUnit5() + ", getQty5()=" + getQty5() + ", getIsDefault()=" + getIsDefault()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
