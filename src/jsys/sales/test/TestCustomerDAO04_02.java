/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO04_02.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class TestCustomerDAO04_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;

		// テストのための準備としてデータベースに接続する。

		try {
			con = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ここからテストを行う。
		try {
			CustomerDAO custDAO = new CustomerDAO(con);
			Customer customer = new Customer("KA9999", "テストストア2", "111-1111-1111", "111-1111", "東京都港区港南", 0);
			boolean result = custDAO.updateCustomer(customer);

			System.out.println("結果:" + result);

		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try {// データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}