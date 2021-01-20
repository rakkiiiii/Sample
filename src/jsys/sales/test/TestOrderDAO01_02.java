/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestOrderDAO01_02.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.OrderTotalByCustomer;

public class TestOrderDAO01_02 {
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
			OrderDAO orderDAO = new OrderDAO(con);
			ArrayList<OrderTotalByCustomer> orderTotalListByCustomer = orderDAO
					.createOrderTotalListByCustomer("2006/12/1", "2006/12/31");

			if (orderTotalListByCustomer.isEmpty()) {
				System.out.println("要素数：" + orderTotalListByCustomer.size());
			}

			for (OrderTotalByCustomer order : orderTotalListByCustomer) {
				System.out.print("得意先コード：" + order.getCustCode() + "\t");
				System.out.print("得意先名　　：" + order.getCustName() + "\t");
				System.out.println("得意先別受注合計金額　　：" + order.getTotalPrice() + "\t");
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
