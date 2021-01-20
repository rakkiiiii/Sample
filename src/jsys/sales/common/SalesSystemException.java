/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * SalesSystemException.java
 *
 */
package jsys.sales.common;

public class SalesSystemException extends Exception {
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public SalesSystemException(String message) {
		super(message);
	}
}
