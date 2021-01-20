/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByItem.java
 *
 */

package jsys.sales.entity;

public class OrderTotalByItem {

	/** 商品コード */
	private String itemCode;
	/** 商品名 */
	private String itemName;
	/** 合計数量 */
	private int totalAmount;
	/** 単価 */
	private int price;
	/** 商品別合計金額 */
	private int totalPrice;

	/**
	 * 引数のないコンストラクタ:何もしない。
	 */
	public OrderTotalByItem() {
	}

	/**
	 * 引数のあるコンストラクタタ:引数で指定した値を設定する。
	 * @param itemCode 商品コード
	 * @param itemName 商品名
	 * @param totalAmount 合計数量
	 * @param price 単価
	 * @param totalPrice 商品別合計金額
	 */
	public OrderTotalByItem(String itemCode, String itemName, int totalAmount, int price, int totalPrice) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.totalAmount = totalAmount;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	/**
	 * setter:商品コードを設定する。
	 * @param itemCode 商品コード
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * setter:商品名を設定する。
	 * @param itemName 商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * setter:合計数量を設定する。
	 * @param totalAmount 合計数量
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * setter:単価を設定する。
	 * @param price 単価
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * setter:商品別合計金額を設定する。
	 * @param totalPrice 商品別合計金額
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * getter:商品コードを返す。
	 * @return 商品コード
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * getter:商品名を返す。
	 * @return 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * getter:合計数量を返す。
	 * @return 合計数量
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * getter:単価を返す。
	 * @return 単価
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * getter:商品別合計金額を返す。
	 * @return 商品別合計金額
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

}
