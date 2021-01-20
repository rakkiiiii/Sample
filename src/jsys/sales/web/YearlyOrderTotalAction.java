/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * YearlyOrderTotalAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.logic.YearlyOrderTotalLogic;

public class YearlyOrderTotalAction {

	/**
	 * 年次受注集計画面の集計ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 集計画面を戻り値に設定する。
		String page = "/YearlyOrderTotalView.jsp";
		String message = "";

		// クライアントの入力値を取得する。
		String stryear = req.getParameter("YEAR");
		int year = 0;

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、集計画面を返却する。
		try {
			if (stryear != null && stryear.equals("")) {
				message = "年を入力してください。";
			} else {
				year = Integer.parseInt(stryear);
				if (year < 1998) {
					message = "年には1998以降を指定してください。";
				}
			}
		} catch (NumberFormatException e) {
			message = "年には数値を入力してください。";
		}

		if (!message.equals("")) {
			req.setAttribute("message", message);
			page = "/YearlyOrderTotalView.jsp";
			return page;
		}

		try {
			// 年次受注集計用の業務Logicを生成し、メソッドを呼び出す。
			YearlyOrderTotalLogic logic = new YearlyOrderTotalLogic();
			ArrayList<OrderTotalByCustomer> orderList = logic.total(year);

			// 集計結果をリクエストスコープに設定する。
			req.setAttribute("sumTotal", logic.getSumTotal());
			req.setAttribute("orderList", orderList);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 集計画面を戻り値に設定する。
			page = "/YearlyOrderTotalView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}