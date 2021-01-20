/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * FrontController.java
 *
 */
package jsys.sales.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsys")
public class FrontController extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		// 画面のボタンIDを取得する。
		request.setCharacterEncoding("UTF-8");
		String buttonID = request.getParameter("BUTTON_ID");

		// 初回アクセス時はログイン画面を表示する。
		if (buttonID == null) {
			buttonID = "101_01";
		}

		// どのボタンが押されたかによって処理を振り分ける。
		switch (buttonID) {
			// ログイン画面（初回アクセス）
			case "101_01":
				page = "/Login.jsp";
				break;
			// ログイン画面：ログインボタン
			case "101_01_01":
				LoginAction loginAction = new LoginAction();
				page = loginAction.execute(request);
				break;

			// 全画面：メインメニューへ戻るボタン
			case "M01":
				page = "/MainMenu.jsp";
				break;
			// メインメニュー画面：得意先管理ボタン
			case "M01_01":
				page = "/CustomerManagementMenu.jsp";
				break;
			// メインメニュー：画面：得意先別集計ボタン
			case "M01_02":
				page = "/OrderTotalMenu.jsp";
				break;
			// メインメニュー：画面：ログアウトボタン
			case "M01_03":
				LogoutAction logoutAction = new LogoutAction();
				page = logoutAction.execute(request);
				break;
			// 得意先管理：メニュー画面へ戻るボタン
			case "M02":
				page = "/CustomerManagementMenu.jsp";
				break;
			// 得意先管理メニュー画面：得意先検索ボタン
			// 得意先検索結果画面：前画面へ戻るボタン
			case "M02_01":
				page = "/CustomerSearchView.jsp";
				break;
			// 得意先管理メニュー画面：得意先登録ボタン
			// 得意先登録結果画面：前画面へ戻るボタン
			case "M02_02":
				page = "/CustomerRegistView.jsp";
				break;
			// 得意先管理メニュー画面：得意先削除ボタン
			// 得意先削除結果画面：前画面へ戻るボタン
			case "M02_03":
				page = "/CustomerDeleteView.jsp";
				break;
			// 得意先管理メニュー画面：得意先変更ボタン
			// 得意先変更結果画面：前画面へボタン
			case "M02_04":
				page = "/CustomerChangeView.jsp";
				break;
			// 得意先管理メニュー画面：得意先一覧ボタン
			case "M02_05":
				CustomerListAction customerListAction = new CustomerListAction();
				page = customerListAction.execute(request);
				break;
			// 得意先検索画面：検索ボタン
			case "201_01_01":
				CustomerSearchAction action = new CustomerSearchAction();
				page = action.execute(request);
				break;
			// 得意先登録画面：登録ボタン
			case "202_01_01":
				CustomerRegistAction customerRegistAction = new CustomerRegistAction();
				page = customerRegistAction.execute(request);
				break;
			// 得意先削除画面：検索ボタン
			case "203_01_01":
				CustomerDeleteSearchAction customerDeleteSearchAction = new CustomerDeleteSearchAction();
				page = customerDeleteSearchAction.execute(request);
				break;
			// 得意先削除画面：削除ボタン
			case "203_01_02":
				CustomerDeleteExecuteAction customerDeleteExecuteAction = new CustomerDeleteExecuteAction();
				page = customerDeleteExecuteAction.execute(request);
				break;
			// 得意先削除画面：クリアボタン
			case "203_01_03":
				page = "/CustomerDeleteView.jsp";
				break;
			// 得意先変更画面：検索ボタン
			case "204_01_01":
				CustomerChangeSearchAction customerChangeSearchAction = new CustomerChangeSearchAction();
				page = customerChangeSearchAction.execute(request);
				break;
			// 得意先変更画面：確定ボタン
			case "204_01_02":
				CustomerChangeExecuteAction customerChangeExecuteAction = new CustomerChangeExecuteAction();
				page = customerChangeExecuteAction.execute(request);
				break;
			// 得意先変更画面：クリアボタン
			case "204_01_03":
				page = "/CustomerChangeView.jsp";
				break;

			// 受注管理：メニュー画面へ戻るボタン
			case "M03":
				page = "/OrderTotalMenu.jsp";
				break;
			// 得意先別集計メニュー画面：月別受注集計ボタン
			case "M03_01":
				page = "/MonthlyOrderTotalView.jsp";
				break;
			// 得意先別集計メニュー画面：年次受注集計ボタン
			case "M03_02":
				page = "/YearlyOrderTotalView.jsp";
				break;
			// 得意先別集計メニュー画面：商品別受注集計ボタン
			case "M03_03":
				page = "/OrderTotalByItemView.jsp";
				break;

			// 月別受注集計画面：集計ボタン
			case "301_01_01":
				MonthlyOrderTotalAction monthlyOrderTotalAction = new MonthlyOrderTotalAction();
				page = monthlyOrderTotalAction.execute(request);
				break;
			// 月別受注集計画面：クリアボタン
			case "301_01_02":
				page = "/MonthlyOrderTotalView.jsp";
				break;
			// 年次受注集計画面：集計ボタン
			case "302_01_01":
				YearlyOrderTotalAction yearlyOrderTotalAction = new YearlyOrderTotalAction();
				page = yearlyOrderTotalAction.execute(request);
				break;
			// 年次受注集計画面：クリアボタン
			case "302_01_02":
				page = "/YearlyOrderTotalView.jsp";
				break;
			// 商品別受注集計画面：集計ボタン
			case "303_01_01":
				OrderTotalByItemAction orderTotalByItemAction = new OrderTotalByItemAction();
				page = orderTotalByItemAction.execute(request);
				break;
			// 商品別受注集計画面：クリアボタン
			case "303_01_02":
				page = "/OrderTotalByItemView.jsp";
				break;

			// エラー画面：ログイン画面へ戻るボタン
			case "901_01_01":
				page = "/Login.jsp";
				break;

		}

		request.getRequestDispatcher(page).forward(request, response);
	}

}
