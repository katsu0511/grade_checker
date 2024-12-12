package home;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		final HttpSession SESSION = request.getSession();
		final String USER_ID = (String) SESSION.getAttribute("user_id");
		final String PASSWORD = (String) SESSION.getAttribute("password");

		if (USER_ID == null || PASSWORD == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			SESSION.removeAttribute("user_id");
			SESSION.removeAttribute("password");
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
