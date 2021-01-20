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

public class TestCustomerNumberingDAO01_01 {

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
			int latestCustomerCode  = custNumberingDAO.findCustomerCode();

			System.out.println("得意先コード：" + latestCustomerCode);

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
