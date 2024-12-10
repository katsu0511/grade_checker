package home;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		final HttpSession SESSION = request.getSession();
		final String USER_ID = (String) SESSION.getAttribute("user_id");
		final String PASSWORD = (String) SESSION.getAttribute("password");

		if (USER_ID != null && PASSWORD != null) {
			request.getRequestDispatcher("/WEB-INF/app/home/top.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
