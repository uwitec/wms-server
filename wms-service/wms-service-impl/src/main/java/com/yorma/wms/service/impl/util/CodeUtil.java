package com.yorma.wms.service.impl.util;

public class CodeUtil {
	//用于生成仓库。库区、储位code
	public static String getCode(String type, int size, Long id) {

		Long i = id + 1;
		String a = "";
		for (int j = 0; j < size - 1 - i.toString().length(); j++) {
			a += "0";
		}
		String code = type + a + i;

		return code;
	}
	
}
