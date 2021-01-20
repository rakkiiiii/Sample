/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO01_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerNumberingDAO;

public class TestCustomerNumberingDAO02_01 {

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
			CustomerNumberingDAO custNumberingDAO = new CustomerNumberingDAO(con);
			boolean result = custNumberingDAO.updateCustomerCode();

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
