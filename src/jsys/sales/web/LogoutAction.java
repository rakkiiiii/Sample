/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LogoutAction.java
 *
 */

package jsys.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction {

	/**
	 * ログアウトボタンが押された場合の処理を実行する。
	 * @param req リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		// 結果画面を戻り値に設定する。
		String page = "/Login.jsp";

		// セッションを取得する。
		HttpSession session = req.getSession();

		//セッションを開放する。
		session.invalidate();

		return page;
	}

}
