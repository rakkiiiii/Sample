/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO05_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class TestCustomerDAO05_01 {
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
			ArrayList<Customer> customerList = custDAO.findAllCustomer();

			if (customerList.isEmpty()) {
				System.out.println("要素数：" + customerList.size());
			}

			for (Customer customer : customerList) {
				System.out.print("得意先コード：" + customer.getCustCode() + "\t");
				System.out.print("得意先名　　：" + customer.getCustName() + "\t");
				System.out.print("電話番号　　：" + customer.getTelNo() + "\t");
				System.out.print("郵便番号　　：" + customer.getPostalCode() + "\t");
				System.out.print("住所　　　　：" + customer.getAddress() + "\t");
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