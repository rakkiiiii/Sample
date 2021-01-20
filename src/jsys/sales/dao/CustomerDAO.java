/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerDAO.java
 *
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.Customer;

public class CustomerDAO {

	/** データベースの接続 */
	private Connection con;

	/**
	 * コンストラクタ
	 * @param con データベースの接続
	 */
	public CustomerDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 得意先を1件検索する。
	 * @param custCode 得意先コード
	 * @return 得意先
	 * @throws SQLException
	 */
	public Customer findCustomer(String custCode) throws SQLException {
		String sql = "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate"
				+ " FROM customer WHERE customer_code = ? AND delete_flag = false";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Customer customer = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, custCode);
			res = stmt.executeQuery();

			// 検索結果がある場合、戻り値に設定する。
			if (res.next()) {
				customer = new Customer(
						res.getString("customer_code"),
						res.getString("customer_name"),
						res.getString("customer_telno"),
						res.getString("customer_postalcode"),
						res.getString("customer_address"),
						res.getInt("discount_rate"));
			}
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return customer;
	}

	/**
	 * 引数で指定した得意先を追加する。
	 * @param customer 得意先インスタンス
	 * @return 得意先インスタンス
	 */
	public Customer insertCustomer(Customer customer) throws SQLException {

		String sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?,false)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customer.getCustCode());
			stmt.setString(2, customer.getCustName());
			stmt.setString(3, customer.getTelNo());
			stmt.setString(4, customer.getPostalCode());
			stmt.setString(5, customer.getAddress());
			stmt.setInt(6, customer.getDiscountRate());

			stmt.executeUpdate();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return customer;
	}

	/**
	 * 引数で指定した得意先を削除する。
	 * @param customer 得意先インスタンス
	 * @return 削除結果
	 */
	public boolean deleteCustomer(Customer customer) throws SQLException {
		boolean result = false;

		String sql = "UPDATE customer SET delete_flag = true " +
				"WHERE customer_code = ?  AND delete_flag = false";
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customer.getCustCode());

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

	/**
	 * 引数で指定した得意先を更新する。
	 * @param customer 得意先インスタンス
	 * @param con コネクション
	 * @return 更新結果
	 */
	public boolean updateCustomer(Customer customer) throws SQLException {
		boolean result = false;

		String sql = "UPDATE customer SET customer_name=?, customer_telno=?, customer_postalcode=?, customer_address=?, discount_rate=? "
				+ "WHERE customer_code = ?  AND delete_flag = false";
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customer.getCustName());
			stmt.setString(2, customer.getTelNo());
			stmt.setString(3, customer.getPostalCode());
			stmt.setString(4, customer.getAddress());
			stmt.setInt(5, customer.getDiscountRate());
			stmt.setString(6, customer.getCustCode());

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

	/**
	 * 全得意先のリストを返す。
	 * @param con コネクション
	 * @return 得意先リスト
	 */
	public ArrayList<Customer> findAllCustomer() throws SQLException {
		ArrayList<Customer> customerList = new ArrayList<>();

		String sql = "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate"
				+ " FROM customer WHERE delete_flag = false";
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();

			while (res.next()) {
				Customer customer = new Customer(
						res.getString("customer_code"),
						res.getString("customer_name"),
						res.getString("customer_telno"),
						res.getString("customer_postalcode"),
						res.getString("customer_address"),
						res.getInt("discount_rate"));

				customerList.add(customer);
			}
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return customerList;
	}
    /**
     * 削除済みフラグを無視して、得意先を1件検索する
     * @param custCode 得意先コード
     * @return 得意先
     * @throws SQLException
     */
    public Customer findCustomerIgnoreDeleteFlag(String custCode) throws SQLException {
        String sql = "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate"
                + " FROM customer WHERE customer_code = ?";
        PreparedStatement stmt = null;
        ResultSet res = null;
        Customer customer = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, custCode);
            res = stmt.executeQuery();

            // 検索結果がある場合、戻り値に設定する。
            if (res.next()) {
                customer = new Customer(
                        res.getString("customer_code"),
                        res.getString("customer_name"),
                        res.getString("customer_telno"),
                        res.getString("customer_postalcode"),
                        res.getString("customer_address"),
                        res.getInt("discount_rate"));
            }
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return customer;
    }
}