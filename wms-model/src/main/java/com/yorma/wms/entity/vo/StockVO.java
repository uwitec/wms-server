package com.yorma.wms.entity.vo;

import java.math.BigDecimal;

import com.yorma.wms.entity.Stock;

public class StockVO extends Stock {

	// 总在库量
	private int sumInStock;
	// 总预分配量
	private int sumPreAllocationStock;
	// 总已分配量
	private int sumAllocationStock;
	// 总包装数
	private BigDecimal sumPackagingQty;
	// 可用库存量
	private int sumQty;
	
	//计算包装数
	private String packQtyCount;
	
	//计算在库量
	private String inStockCount;

	
	/**
	 * 无参构造
	 */
	public StockVO() {
		super();
	}

	/**
	 * 有参构造
	 * @param sumInStock 总在库量
	 * @param sumPreAllocationStock 总预分配量
	 * @param sumAllocationStock 总已分配量
	 * @param sumPackagingQty 总包装数
	 * @param sumQty 可用库存量
	 */
	public StockVO(int sumInStock, int sumPreAllocationStock, int sumAllocationStock, BigDecimal sumPackagingQty,
			int sumQty) {
		super();
		this.sumInStock = sumInStock;
		this.sumPreAllocationStock = sumPreAllocationStock;
		this.sumAllocationStock = sumAllocationStock;
		this.sumPackagingQty = sumPackagingQty;
		this.sumQty = sumQty;
	}
	
	

	/**
	 * GET 总在库量
	 * @return 总在库量
	 */
	public int getSumInStock() {
		return sumInStock;
	}

	/**
	 * SET 总在库量
	 * @param sumInStock 总在库量
	 */
	public void setSumInStock(int sumInStock) {
		this.sumInStock = sumInStock;
	}

	/**
	 * GET 总预分配量
	 * @return 总预分配量
	 */
	public int getSumPreAllocationStock() {
		return sumPreAllocationStock;
	}

	/**
	 * SET 总预分配量
	 * @param sumPreAllocationStock 总预分配量
	 */
	public void setSumPreAllocationStock(int sumPreAllocationStock) {
		this.sumPreAllocationStock = sumPreAllocationStock;
	}

	/**
	 * GET 总已分配量
	 * @return 总已分配量
	 */
	public int getSumAllocationStock() {
		return sumAllocationStock;
	}

	/**
	 * SET 总已分配量
	 * @param sumAllocationStock 总已分配量
	 */
	public void setSumAllocationStock(int sumAllocationStock) {
		this.sumAllocationStock = sumAllocationStock;
	}

	/**
	 * GET 总包装数
	 * @return 总包装数
	 */
	public BigDecimal getSumPackagingQty() {
		return sumPackagingQty;
	}

	/**
	 * SET 总包装数
	 * @param sumPackagingQty 总包装数
	 */
	public void setSumPackagingQty(BigDecimal sumPackagingQty) {
		this.sumPackagingQty = sumPackagingQty;
	}

	/**
	 * GET 可用库存量
	 * @return 可用库存量
	 */
	public int getSumQty() {
		return sumQty;
	}

	/**
	 * SET 可用库存量
	 * @param sumQty 可用库存量
	 */
	public void setSumQty(int sumQty) {
		this.sumQty = sumQty;
	}

	/**
	 * GET 计算包装数
	 * @return 计算包装数
	 */
	public String getPackQtyCount() {
		return packQtyCount;
	}

	/**
	 * SET 计算包装数
	 * @param packQtyCounts 计算包装数
	 */
	public void setPackQtyCount(String packQtyCount) {
		this.packQtyCount = packQtyCount;
	}

	/**
	 * GET 计算在库量
	 * @return 在库量
	 */
	public String getInStockCount() {
		return inStockCount;
	}

	/**
	 * SET 计算包装数
	 * @param inStockCounts 计算包装数
	 */
	public void setInStockCount(String inStockCount) {
		this.inStockCount = inStockCount;
	}

	@Override
	public String toString() {
		return "StockVO [sumInStock=" + sumInStock + ", sumPreAllocationStock=" + sumPreAllocationStock
				+ ", sumAllocationStock=" + sumAllocationStock + ", sumPackagingQty=" + sumPackagingQty + ", sumQty="
				+ sumQty + ", packQtyCount=" + packQtyCount + ", inStockCount=" + inStockCount + "]";
	}
	
	

}
