package home;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import db.DBManager;
import db.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

		if (USER_ID == null || PASSWORD == null) {
			final Cookie[] COOKIES = request.getCookies();
			String cookieUserId = null;
			String cookiePassword = null;

			if (COOKIES != null) {
				for  (final Cookie COOKIE : COOKIES) {
					if (COOKIE.getName().equals("user_id")) {
						cookieUserId = COOKIE.getValue();
					} else if (COOKIE.getName().equals("password")) {
						cookiePassword = COOKIE.getValue();
					}
				}
			}

			if (cookieUserId != null) {
				cookieUserId = URLDecoder.decode(cookieUserId, "UTF-8");
			}

			if (cookiePassword != null) {
				cookiePassword = URLDecoder.decode(cookiePassword, "UTF-8");
			}

			request.setAttribute("user_id", cookieUserId);
			request.setAttribute("password", cookiePassword);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/top");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String errorMessage = null;
		final DBManager DB_MANAGER = new DBManager();
		final UserDTO USER = DB_MANAGER.getLoginUser(user_id, password);

		if (user_id.equals("") || password.equals("")) {
			errorMessage = "Input your e-mail and password";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("user_id", user_id);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);
		} else if (USER != null) {
			final HttpSession SESSION = request.getSession();
			SESSION.setAttribute("user_id", user_id);
			SESSION.setAttribute("password", password);
			user_id = URLEncoder.encode(user_id, "UTF-8");
			password = URLEncoder.encode(password, "UTF-8");

			final Cookie[] COOKIES = request.getCookies();
			Cookie cookieUserId = null;
			Cookie cookiePassword = null;

			if (COOKIES != null) {
				for  (final Cookie COOKIE : COOKIES) {
					if (COOKIE.getName().equals("user_id")) {
						cookieUserId = COOKIE;
					} else if (COOKIE.getName().equals("password")) {
						cookiePassword = COOKIE;
					}
				}
			}

			if (cookieUserId != null) {
				cookieUserId.setValue(user_id);
			} else {
				cookieUserId = new Cookie("user_id", user_id);
			}

			if (cookiePassword != null) {
				cookiePassword.setValue(password);
			} else {
				cookiePassword = new Cookie("password", password);
			}

			response.addCookie(cookieUserId);
			response.addCookie(cookiePassword);
			response.sendRedirect(request.getContextPath() + "/top");

		} else {
			errorMessage = "Email or password is wrong";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("user_id", user_id);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);
		}
	}
}
