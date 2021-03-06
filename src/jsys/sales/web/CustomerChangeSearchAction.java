/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerChangeSearchAction.java
 *
 */

package jsys.sales.web;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerChangeLogic;

public class CustomerChangeSearchAction {

	/**
	 * 得意先変更画面の検索ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 変更画面を戻り値に設定する。
		String page = "/CustomerChangeView.jsp";
		String message = "";

		// クライアントの入力値を取得する。
		String custCode = req.getParameter("CUST_CODE");

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、変更画面を返却する。
		if (custCode != null && custCode.equals("")) {
			message = "得意先コードを入力してください。";
		}

		if (!message.equals("")) {
			req.setAttribute("message", message);
			page = "/CustomerChangeView.jsp";
			return page;
		}

		try {
			// 得意先変更用の業務Logicを生成し、メソッドを呼び出す。
			CustomerChangeLogic logic = new CustomerChangeLogic();
			Customer customer = logic.findCustomer(custCode);

			// 変更結果をリクエストスコープに設定する。
			req.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 変更画面を戻り値に設定する。
			page = "/CustomerChangeView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}
