/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO01_02.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class TestCustomerDAO01_02 {

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
			Customer customer = custDAO.findCustomer("KA9999");

			if (customer == null) {
				System.out.println("戻り値：" + customer);
			} else {
				System.out.println("得意先コード：" + customer.getCustCode());
				System.out.println("得意先名　　：" + customer.getCustName());
				System.out.println("電話番号　　：" + customer.getTelNo());
				System.out.println("郵便番号　　：" + customer.getPostalCode());
				System.out.println("住所　　　　：" + customer.getAddress());
				System.out.println("割引率　　　：" + customer.getDiscountRate());

			}

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
