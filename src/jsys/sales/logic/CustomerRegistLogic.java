/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerRegistLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.Customer;

public class CustomerRegistLogic {

	/**
	 * 得意先を登録する。
	 * @param customer 得意先
	 * @return 得意先
	 * @throws SalesBusinessException 業務エラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public Customer registCustomer(Customer customer) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Customer resultCustomer = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 自動コミットモードを無効にする
			con.setAutoCommit(false);

			// 得意先採番テーブルおよび得意先テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			CustomerDAO customerDAO = new CustomerDAO(con);
			CustomerNumberingDAO customerNumberingDAO = new CustomerNumberingDAO(con);

			// 採番テーブルから得意先コードを取得する
			int latestCutomerCode = customerNumberingDAO.findCustomerCode();
			// 登録する得意先コードを作成
			String newCustomerCode = "KA" + String.format("%04d", latestCutomerCode + 1);
			customer.setCustCode(newCustomerCode);

			// 得意先テーブルに得意先情報を登録する
			resultCustomer = customerDAO.insertCustomer(customer);

			// 得意先採番テーブルを更新する
			boolean result = customerNumberingDAO.updateCustomerCode();

			// 登録に失敗した場合、エラーを発生させる。
			if (resultCustomer == null || !result) {
				con.rollback();
				throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
			}

			// トランザクションを確定する
			con.commit();

		} catch (SQLException e) {
			try {
				e.printStackTrace();
				con.rollback();
				throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new SalesSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
			}
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
		return resultCustomer;
	}
}
