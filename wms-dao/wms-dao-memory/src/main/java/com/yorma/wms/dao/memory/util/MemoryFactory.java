package com.yorma.wms.dao.memory.util;

import cn.ffcs.memory.Memory;

public class MemoryFactory {
	private MemoryFactory() {
	}

	private static class SingletonHolder {
		public static final Memory MEMORY = new Memory(new SimpleDataSource());

	}

	public static Memory getInstance() {
		return SingletonHolder.MEMORY;
	}

}
