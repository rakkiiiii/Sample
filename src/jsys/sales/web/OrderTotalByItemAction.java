/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalBuItemAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.OrderTotalByItem;
import jsys.sales.logic.OrderTotalByItemLogic;

public class OrderTotalByItemAction {

	/**
	 * 商品別受注集計画面の集計ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 集計画面を戻り値に設定する。
		String page = "/OrderTotalByItemView.jsp";
		String message = "";

		// クライアントの入力値を取得する。
		String custCode = req.getParameter("CUST_CODE");

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、集計画面を返却する。
		if (custCode != null && custCode.equals("")) {
			message = "得意先コードを入力してください。";
		}

		if (!message.equals("")) {
			req.setAttribute("message", message);
			page = "/OrderTotalByItemView.jsp";
			return page;
		}

		try {
			// 商品別受注集計の業務Logicを生成し、メソッドを呼び出す。
			OrderTotalByItemLogic logic = new OrderTotalByItemLogic();
			ArrayList<OrderTotalByItem> orderList = logic.total(custCode);

			// 集計結果をリクエストスコープに設定する
			req.setAttribute("customer", logic.getCustomer());
			req.setAttribute("sumTotal", logic.getSumTotal());
			req.setAttribute("orderList", orderList);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 集計画面を戻り値に設定する。
			page = "/OrderTotalByItemView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}