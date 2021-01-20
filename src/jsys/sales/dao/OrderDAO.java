/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderDAO.java
 *
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.entity.OrderTotalByItem;

public class OrderDAO {

	/** データベースの接続 */
	private Connection con;

	/**
	 * コンストラクタ
	 * @param con データベースの接続
	 */
	public OrderDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 引数で指定された期間の得意先別の受注金額集計リストを返す。
	 * @param startDate 開始日
	 * @param endDate 終了日
	 * @param con コネクション
	 * @return 得意先別受注集計リスト
	 */
	public ArrayList<OrderTotalByCustomer> createOrderTotalListByCustomer(String startDate, String endDate)
			throws SQLException {
		ArrayList<OrderTotalByCustomer> orderTotalListByCustomer = new ArrayList<>();

		String sql = "SELECT orders.customer_code, customer.customer_name, "
				+ "SUM(orders.total_price) AS customer_total_price "
				+ "FROM orders, customer "
				+ "WHERE orders.customer_code = customer.customer_code "
				+ "AND orders.order_date BETWEEN ? AND ? "
				+ "GROUP BY orders.customer_code, customer.customer_name "
				+ "ORDER BY orders.customer_code";

		PreparedStatement stmt = null;
		ResultSet res = null;

		try {

			stmt = con.prepareStatement(sql);
			stmt.setString(1, startDate);
			stmt.setString(2, endDate);

			res = stmt.executeQuery();

			while (res.next()) {
				OrderTotalByCustomer orderTotal = new OrderTotalByCustomer(
						res.getString("customer_code"),
						res.getString("customer_name"),
						res.getInt("customer_total_price"));
				orderTotalListByCustomer.add(orderTotal);
			}

		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return orderTotalListByCustomer;
	}

	/**
	 * 引数で指定された期間の得意先別の受注金額集計リストを返す。
	 * @param code 得意先コード
	 * @param con コネクション
	 * @return 商品別受注集計リスト
	 */
	public ArrayList<OrderTotalByItem> createOrderTotalListByItem(String code) throws SQLException {
		ArrayList<OrderTotalByItem> orderTotalListByItem = new ArrayList<>();

		String sql = "SELECT order_details.item_code, item.item_name, "
				+ "SUM(order_details.order_num) AS total_num, item.price, "
				+ "SUM(order_details.order_price) AS item_total_price "
				+ "FROM orders,order_details,item "
				+ "WHERE orders.order_no = order_details.order_no AND order_details.item_code = item.item_code "
				+ "GROUP BY orders.customer_code, order_details.item_code, item.item_name, item.price "
				+ "HAVING orders.customer_code= ? "
				+ "ORDER BY order_details.item_code";

		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, code);

			res = stmt.executeQuery();

			while (res.next()) {
				OrderTotalByItem orderTotal = new OrderTotalByItem(
						res.getString("item_code"),
						res.getString("item_name"),
						res.getInt("total_num"),
						res.getInt("price"),
						res.getInt("item_total_price"));
				orderTotalListByItem.add(orderTotal);
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}

		return orderTotalListByItem;
	}
}
