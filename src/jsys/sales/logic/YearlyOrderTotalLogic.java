/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * YearlyOrderTotalLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.OrderTotalByCustomer;

public class YearlyOrderTotalLogic {

	/** 総計 */
	private int sumTotal;

	/**
	 * 得意先ごとに年間の受注を集計する。
	 * @param custCode 得意先コード
	 * @return 受注リスト
	 * @throws SalesBusinessException 業務エラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public ArrayList<OrderTotalByCustomer> total(int year) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		ArrayList<OrderTotalByCustomer> orderList = null;
		String startDate = year + "/01/" + "01";
		String endDate = year + "/12/" + "31";

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 受注テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			OrderDAO orderDAO = new OrderDAO(con);
			orderList = orderDAO.createOrderTotalListByCustomer(startDate, endDate);

			// 集計結果がない場合、エラーを発生させる。
			if (orderList.isEmpty()) {
				throw new SalesBusinessException("該当する受注はありません。");
			}

			// 総計を計算する
			for (OrderTotalByCustomer order : orderList) {
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
