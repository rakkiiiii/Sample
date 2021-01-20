/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerListAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerListLogic;

public class CustomerListAction {

	/**
	 * 得意先管理メニュー画面の得意先一覧ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 一覧画面を戻り値に設定する。
		String page = "/CustomerListView.jsp";
		ArrayList<Customer> customerList = null;

		try {
			// 得意先一覧用の業務Logicを生成し、メソッドを呼び出す。
			CustomerListLogic logic = new CustomerListLogic();
			customerList = logic.findAllCustomer();

			// 検索結果をリクエストスコープに設定する。
			req.setAttribute("customerList", customerList);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}

		return page;
	}

}
