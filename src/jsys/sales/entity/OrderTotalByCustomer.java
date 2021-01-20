/************************************************************************************
 *
 /**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByCustomer.java
 *
 */

package jsys.sales.entity;

public class OrderTotalByCustomer {

	/** 得意先コード */
	private String custCode;
	/** 得意先名 */
	private String custName;
	/** 得意先別合計金額 */
	private int totalPrice;

	/**
	 * 引数のないコンストラクタ:何もしない。
	 */
	public OrderTotalByCustomer() {
	}

	/**
	 * 引数のあるコンストラクタタ:引数で指定した値を設定する。
	 * @param custCode 得意先コード
	 * @param custName 得意先名
	 * @param totalPrice 得意先別合計金額
	 */
	public OrderTotalByCustomer(String custCode, String custName, int totalPrice) {
		this.custCode = custCode;
		this.custName = custName;
		this.totalPrice = totalPrice;
	}

	/**
	 * setter:得意先コードを設定する。
	 * @param custCode 得意先コード
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * setter:得意先名を設定する。
	 * @param name 得意先名
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * setter:得意先別合計金額を設定する。
	 * @param totalPrice 得意先別合計金額
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * getter:得意先コードを返す。
	 * @return 得意先コード
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * getter:得意先名を返す。
	 * @return 得意先名
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * getter:得意先別合計金額を返す。
	 * @return 得意先別合計金額
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
}
