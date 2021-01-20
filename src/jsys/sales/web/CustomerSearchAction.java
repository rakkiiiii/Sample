/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerSearchAction.java
 *
 */
package jsys.sales.web;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerSearchLogic;

public class CustomerSearchAction {
	/**
	 * 得意先検索画面の検索ボタンが押された場合の処理を実行する。
	 * @param request リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest request) {
		// 結果画面を戻り値に設定する。
		String page = "/CustomerSearchResultView.jsp";

		// クライアントの入力値を取得する。
		String custCode = request.getParameter("CUST_CODE");

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、検索画面を返却する。
		if (custCode != null && custCode.equals("")) {
			request.setAttribute("message", "得意先コードを入力してください。");
			page = "/CustomerSearchView.jsp";
			return page;
		}

		try {
			// 得意先検索用の業務Logicを生成し、メソッドを呼び出す。
			CustomerSearchLogic logic = new CustomerSearchLogic();
			Customer customer = logic.findCustomer(custCode);

			// 検索結果をリクエストスコープに設定する。
			request.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			// 検索画面を戻り値に設定する。
			page = "/CustomerSearchView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}

		return page;
	}
}
