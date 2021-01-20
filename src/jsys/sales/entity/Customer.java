/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Customer.java
 *
 */

package jsys.sales.entity;

public class Customer {

	/** 得意先コード */
	private String custCode;
	/** 得意先名 */
	private String custName;
	/** 電話番号 */
	private String telNo;
	/** 郵便番号 */
	private String postalCode;
	/** 住所 */
	private String address;
	/** 割引率 */
	private int discountRate;

	/**
	 * 引数のないコンストラクタ:何もしない。
	 */
	public Customer() {
	}

	/**
	 * 引数のあるコンストラクタタ:引数で指定した値を設定する。
	 * @param custCode 得意先コード
	 * @param custName 得意先名
	 * @param telNo 電話番号
	 * @param postalCode 郵便番号
	 * @param address 住所
	 * @param discountRate 割引率
	 */
	public Customer(String custCode, String custName, String telNo, String postalCode, String address,
			int discountRate) {
		this.custCode = custCode;
		this.custName = custName;
		this.telNo = telNo;
		this.postalCode = postalCode;
		this.address = address;
		this.discountRate = discountRate;
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
	 * @param custName 得意先名
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * setter:電話番号を設定する。
	 * @param telNo 電話番号
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * setter:郵便番号を設定する。
	 * @param postalCode 郵便番号
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * setter:住所を設定する。
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * setter:割引率を設定する。
	 * @param discountRate 割引率
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
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
	 * getter:電話番号を返す。
	 * @return 電話番号
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * getter:郵便番号を返す。
	 * @return 郵便番号
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * getter:住所を返す。
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * getter:割引率を返す。
	 * @return 割引率
	 */
	public int getDiscountRate() {
		return discountRate;
	}
}
