/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LoginAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Employee;
import jsys.sales.logic.LoginLogic;

public class LoginAction {

	/**
	 * ログイン画面のログインボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 結果画面を戻り値に設定する。
		String page = "/MainMenu.jsp";
		ArrayList<String> messageList = new ArrayList<>();

		// クライアントの入力値を取得する。
		String employeeNo = req.getParameter("EMPLOYEE_NO");
		String password = req.getParameter("PASSWORD");

		// 未入力の場合、エラーメッセージをリクエストスコープに設定し、ログイン画面を返却する。
		if (employeeNo != null && employeeNo.equals("")) {
			messageList.add("従業員番号を入力してください。");
		}

		if (password != null && password.equals("")) {
			messageList.add("パスワードを入力してください。");
		}

		if (!messageList.isEmpty()) {
			req.setAttribute("messageList", messageList);
			page = "/Login.jsp";
			return page;
		}

		try {
			// ログイン用の業務Logicを生成し、メソッドを呼び出す。
			LoginLogic logic = new LoginLogic();
			Employee employee = logic.login(employeeNo, password);

			// 検索結果をセッションスコープに設定する。
			HttpSession session = req.getSession(true);
			session.setAttribute("employee", employee);

		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// ログイン画面を戻り値に設定する。
			page = "/Login.jsp";
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// システムエラー画面を戻り値に設定する。
			page = "/SalesErrorView.jsp";
		}

		return page;
	}

}
