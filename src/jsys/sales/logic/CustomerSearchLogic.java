/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerSearchLogic.java
 *
 */
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerSearchLogic {
	/**
	 * 得意先を検索する。
	 * @param custCode 得意先コード
	 * @return 得意先
	 * @throws SalesBusinessException 業務エラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public Customer findCustomer(String custCode) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Customer customer = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 得意先テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			CustomerDAO customerDAO = new CustomerDAO(con);
			customer = customerDAO.findCustomer(custCode);

			// 検索結果がない場合、エラーを発生させる。
			if (customer == null) {
				throw new SalesBusinessException("該当する得意先コードは存在しません。");
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

		return customer;
	}
}
