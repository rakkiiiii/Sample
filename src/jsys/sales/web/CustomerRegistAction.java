/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerRegistAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerRegistLogic;

public class CustomerRegistAction {

	/**
	 * 得意先登録画面の登録ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 結果画面を戻り値に設定する。
		String page = "/CustomerRegistResultView.jsp";
		ArrayList<String> messageList = new ArrayList<>();
		Customer customer = null;

		// クライアントの入力値を取得する。
		String custName = req.getParameter("CUST_NAME");
		String telNo = req.getParameter("TELNO");
		String postalCode = req.getParameter("POSTALCODE");
		String address = req.getParameter("ADDRESS");
		int discountRate = 0;

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、登録画面を返却する。
		if (custName != null && custName.equals("")) {
			messageList.add("得意先名を入力してください。");
		}

		if (telNo != null && telNo.equals("")) {
			messageList.add("電話番号を入力してください。");
		}

		if (postalCode != null && postalCode.equals("")) {
			messageList.add("郵便番号を入力してください。");
		}

		if (address != null && address.equals("")) {
			messageList.add("住所を入力してください。");
		}

		try {
			discountRate = Integer.parseInt(req.getParameter("DISCOUNT_RATE"));
			if (0 > discountRate || discountRate > 99) {
				messageList.add("割引率には2桁までの正の数を指定してください。");
			}
		} catch (NumberFormatException e) {
			messageList.add("割引率には2桁までの正の数を指定してください。");
		}

		if (!messageList.isEmpty()) {
			req.setAttribute("messageList", messageList);
			page = "/CustomerRegistView.jsp";
			return page;
		}

		customer = new Customer(null, custName, telNo, postalCode, address, discountRate);

		try {
			// 得意先登録用の業務Logicを生成し、メソッドを呼び出す。
			CustomerRegistLogic logic = new CustomerRegistLogic();
			Customer resultCustomer = logic.registCustomer(customer);

			// 結果をリクエストスコープに設定する。
			req.setAttribute("customer", resultCustomer);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 登録画面を戻り値に設定する。
			page = "/CustomerRegistView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}
