/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerDeleteExecuteAction.java
 *
 */

package jsys.sales.web;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerDeleteLogic;

public class CustomerDeleteExecuteAction {

	/**
	 * 得意先削除画面の削除ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 結果画面を戻り値に設定する。
		String page = "/CustomerDeleteResultView.jsp";
		String message = "";

		// クライアントの入力値を取得する。
		String custCode = req.getParameter("CUST_CODE");

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、削除画面を返却する。
		if (custCode != null && custCode.equals("")) {
			message = "得意先コードを入力してください。";
		}

		if (!message.equals("")) {
			req.setAttribute("message", message);
			page = "/CustomerDeleteView.jsp";
			return page;
		}

		Customer customer = new Customer();
		customer.setCustCode(custCode);

		try {
			// 得意先削除用の業務Logicを生成し、メソッドを呼び出す。
			CustomerDeleteLogic logic = new CustomerDeleteLogic();

			customer = logic.deleteCustomer(customer);

			// 削除結果をリクエストスコープに設定する。
			req.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 削除画面を戻り値に設定する。
			page = "/CustomerDeleteView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}

		return page;
	}

}
