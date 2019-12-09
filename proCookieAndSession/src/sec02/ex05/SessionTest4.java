package sec02.ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/login")
public class SessionTest4 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}

	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		// 로그인 창에서 전송된 id와 비밀번호를 가져 옵니다.
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		// 최초 요청시 수행입니다
		if (session.isNew()) {
			// 로그인창에서 서블릿으로 요청했다면 ID가 null이 아니므로 세션에 ID를 바인딩합니다.
			if (user_id != null) {
				session.setAttribute("user_id", user_id);
				out.println("<a href='login'>로그인 상태 확인</a>");
			} else {
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate(); // invalidate() 생성된 세션을 강제로 삭제
			}
		} else {
			//재요청시 세션에서 ID를 가져와 이전에 로그인 했는지 여부를 확인합니다.
			user_id = (String) session.getAttribute("user_id");
			if(user_id !=null && user_id.length() != 0) {
				out.print("안녕하세요" + user_id + "님!!!");
			}else {
				out.print("<a href='login2.html'>다시 로그인 하세요!!!</a>");
				session.invalidate();
			}

		}

	}
}
