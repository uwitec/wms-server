package com.yorma.wms.entity.dto;

import com.yorma.wms.entity.TransportNoteList;

public class TransportNoteListDto extends TransportNoteList {

	private String departName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Override
	public String toString() {
		return "TransportNoteListDto [getDepartName()=" + getDepartName() + ", getId()=" + getId() + ", getUuid()="
				+ getUuid() + ", getTransportNoteNo()=" + getTransportNoteNo() + ", getOwnerCode()=" + getOwnerCode()
				+ ", getPlUuid()=" + getPlUuid() + ", getPalletNo()=" + getPalletNo() + ", getPackagingNo()="
				+ getPackagingNo() + ", getPackagingKey()=" + getPackagingKey() + ", getMu()=" + getMu() + ", getPn()="
				+ getPn() + ", getDeliveryQty()=" + getDeliveryQty() + ", getGoodsName()=" + getGoodsName()
				+ ", getDeliveryNoteNo()=" + getDeliveryNoteNo() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
