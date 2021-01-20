/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * MonthlyOrderTotalAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.logic.MonthlyOrderTotalLogic;

public class MonthlyOrderTotalAction {

	/**
	 * 月別受注集計画面の集計ボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 集計画面を戻り値に設定する。
		String page = "/MonthlyOrderTotalView.jsp";
		ArrayList<String> messageList = new ArrayList<>();

		// クライアントの入力値を取得する。
		String stryear = req.getParameter("YEAR");
		String strMonth = req.getParameter("MONTH");
		int year = 0;
		int month = 0;

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、集計画面を返却する。
		try {
			if (stryear != null && stryear.equals("")) {
				messageList.add("年を入力してください。");
			} else {
				year = Integer.parseInt(stryear);
			}

			if (strMonth != null && strMonth.equals("")) {
				messageList.add("月を入力してください。");
			} else {
				month = Integer.parseInt(strMonth);
			}

		} catch (NumberFormatException e) {
			messageList.add("数値を入力してください。");
		}

		if (!messageList.isEmpty()) {
			req.setAttribute("messageList", messageList);
			page = "/MonthlyOrderTotalView.jsp";
			return page;
		}

		try {
			// 月別受注集計用の業務Logicを生成し、メソッドを呼び出す。
			MonthlyOrderTotalLogic logic = new MonthlyOrderTotalLogic();
			ArrayList<OrderTotalByCustomer> orderList = logic.total(year, month);

			// 集計結果をリクエストスコープに設定する。
			req.setAttribute("sumTotal", logic.getSumTotal());
			req.setAttribute("orderList", orderList);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// 集計画面を戻り値に設定する。
			page = "/MonthlyOrderTotalView.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}