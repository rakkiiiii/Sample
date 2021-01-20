/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByItemLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.Customer;
import jsys.sales.entity.OrderTotalByItem;

public class OrderTotalByItemLogic {

	/** 得意先 */
	private Customer customer;

	/** 総計 */
	private int sumTotal;

	/**
	 * 特定の得意先の受注情報を商品別に集計する。
	 * @param year 年
	 * @return 受注リスト
	 * @throws SalesBusinessException 業務エラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public ArrayList<OrderTotalByItem> total(String custCode) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		ArrayList<OrderTotalByItem> orderList = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();
			//得意先テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			CustomerDAO cutomerDAO = new CustomerDAO(con);
			customer = cutomerDAO.findCustomerIgnoreDeleteFlag(custCode);

			// 得意先情報がない場合、エラーを発生させる。
			if (customer == null) {
                throw new SalesBusinessException("該当する得意先コードは存在しません。");
            }

			// 受注テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			OrderDAO orderDAO = new OrderDAO(con);
			orderList = orderDAO.createOrderTotalListByItem(custCode);

			// 集計結果がない場合、エラーを発生させる。
			if (orderList.isEmpty()) {
				throw new SalesBusinessException("該当する受注はありません。");
			}

			// 総計を計算する
			for (OrderTotalByItem order : orderList) {
				sumTotal = sumTotal + order.getTotalPrice();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
			}
		}
		return orderList;
	}

	/**
	 * setter:得意先を設定する。
	 * @param customer 得意先
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * getter:得意先を返す。
	 * @return 得意先
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * setter:総計を設定する。
	 * @param sumTotal 総計
	 */
	public void setSumTotal(int sumTotal) {
		this.sumTotal = sumTotal;
	}

	/**
	 * getter:総計を返す。
	 * @return 総計
	 */
	public int getSumTotal() {
		return sumTotal;
	}
}
