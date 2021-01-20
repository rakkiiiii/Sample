/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Employee.java
 *
 */

package jsys.sales.entity;

public class Employee {

	/** 従業員番号 */
	private String employeeNo;
	/** 従業員名 */
	private String employeeName;
	/** パスワード */
	private String password;

	/**
	 * 引数のないコンストラクタ:何もしない。
	 */
	public Employee() {
	}

	/**
	 * 引数のあるコンストラクタタ:引数で指定した値を設定する。
	 * @param employeeNo 従業員番号
	 * @param employeeName 従業員名
	 * @param password パスワード
	 */
	public Employee(String employeeNo, String employeeName, String password) {
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.password = password;
	}

	/**
	 * setter:従業員番号を設定する。
	 * @param employeeNo 従業員番号
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
	 * setter:従業員名を設定する。
	 * @param employeeName 従業員名
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * setter:パスワードを設定する。
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getter:従業員番号を返す。
	 * @return employeeNo 従業員番号
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * getter:従業員名を返す。
	 * @return employeeName 従業員名
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * getter:パスワードを返す。
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}

}
