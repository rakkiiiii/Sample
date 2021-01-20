package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerNumberingDAO {

	/** データベースの接続 */
	private Connection con;

	/**
	 * コンストラクタ
	 * @param con データベースの接続
	 */
	public CustomerNumberingDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 採番テーブルから得意先コードを取得する。
	 * @return 得意先コード
	 * @throws SQLException
	 */
	public int findCustomerCode() throws SQLException {
		String sql = "SELECT customer_code FROM customer_numbering";
		PreparedStatement stmt = null;
		ResultSet res = null;
		int latestCutomerCode = 0;

		try {
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();

			if (res.next()) {
				//最新の得意先コードを取得する
				latestCutomerCode = res.getInt("customer_code");
			}
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return latestCutomerCode;
	}

	/**
	 * 得意先登録後、採番テーブルを更新する。
	 * @return 更新結果
	 * @throws SQLException
	 */
	public boolean updateCustomerCode() throws SQLException {
		boolean result = false;

		String sql = "UPDATE customer_numbering SET customer_code = customer_code + 1;";
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);

			int count = stmt.executeUpdate();
			if (count == 1) {
				result = true;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
	}

}
