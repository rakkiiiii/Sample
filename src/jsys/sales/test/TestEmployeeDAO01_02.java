/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestEmployeeDAO01_02.java
 *
 */
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.EmployeeDAO;
import jsys.sales.entity.Employee;

public class TestEmployeeDAO01_02 {
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
			Employee employee = empDAO.findEmployee("HAAAAA", "zy0001");

			if (employee == null) {
				System.out.println("戻り値：" + employee);
			} else {
				System.out.println("従業員番号：" + employee.getEmployeeNo());
				System.out.println("従業員名　　：" + employee.getEmployeeName());
				System.out.println("パスワード：" + employee.getPassword());
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
