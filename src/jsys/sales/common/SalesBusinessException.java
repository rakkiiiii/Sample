/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * SalesBusinessException.java
 *
 */
package jsys.sales.common;

public class SalesBusinessException extends Exception {
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public SalesBusinessException(String message) {
		super(message);
	}
}
