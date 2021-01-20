/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestOrderDAO02_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.OrderTotalByItem;

public class TestOrderDAO02_01 {
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
			ArrayList<OrderTotalByItem> orderTotalListByItem = orderDAO.createOrderTotalListByItem("KA0001");

			if (orderTotalListByItem.isEmpty()) {
				System.out.println("要素数：" + orderTotalListByItem.size());
			}

			for (OrderTotalByItem order : orderTotalListByItem) {
				System.out.print("商品コード：" + order.getItemCode() + "\t");
				System.out.print("商品名　　：" + order.getItemName() + "\t");
				System.out.print("合計数量　　：" + order.getTotalAmount() + "\t");
				System.out.print("単価　　：" + order.getPrice() + "\t");
				System.out.println("商品別受注合計金額　　：" + order.getTotalPrice() + "\t");
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
