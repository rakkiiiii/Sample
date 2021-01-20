/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestEmployeeDAO01_01.java
 *
 */
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.EmployeeDAO;
import jsys.sales.entity.Employee;

public class TestEmployeeDAO01_01 {
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
			EmployeeDAO empDAO = new EmployeeDAO(con);
			Employee employee = empDAO.findEmployee("H20001", "zy0001");

			System.out.println("従業員番号(H20001)：" + employee.getEmployeeNo());
			System.out.println("従業員名　(安藤直也)：" + employee.getEmployeeName());
			System.out.println("パスワード(zy0001)：" + employee.getPassword());

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
