/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LoginLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.EmployeeDAO;
import jsys.sales.entity.Employee;

public class LoginLogic {

	/**
	 * 従業員を認証する。
	 * @param empCode 従業員コード
	 * @param password パスワード
	 * @return 得意先
	 * @throws SalesBusinessException 業務エラーが発生した場合
	 * @throws SalesSystemException システムエラーが発生した場合
	 */
	public Employee login(String employeeNo, String password) throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Employee employee = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 従業員テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			EmployeeDAO employeeDAO = new EmployeeDAO(con);
			employee = employeeDAO.findEmployee(employeeNo, password);

			// 検索結果がない場合、エラーを発生させる。
			if (employee == null) {
				throw new SalesBusinessException("入力した従業員番号またはパスワードが間違っています。");
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
		return employee;
	}

}
