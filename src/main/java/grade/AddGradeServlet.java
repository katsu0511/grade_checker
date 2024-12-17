package grade;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddGradeServlet
 */
@WebServlet("/add/grade")
public class AddGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGradeServlet() {
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
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			final UserDAO USER_DAO = new UserDAO();
			Connection conn = null;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset1 = null;
			ResultSet rset2 = null;
			
			try {
				conn = USER_DAO.getConnection();
				String sql1 = "SELECT     Term "
							+ "FROM       Term "
							+ "ORDER BY   Term ";
				pstmt1 = conn.prepareStatement(sql1);
				rset1 = pstmt1.executeQuery();
				final ArrayList<String> TERMS = new ArrayList<String>();
				
				while (rset1.next()) {
					TERMS.add(rset1.getString(1));
				}
				
				String sql2 = "SELECT     Mark "
							+ "FROM       Mark ";
				pstmt2 = conn.prepareStatement(sql2);
				rset2 = pstmt2.executeQuery();
				final ArrayList<String> MARKS = new ArrayList<String>();
				
				while (rset2.next()) {
					MARKS.add(rset2.getString(1));
				}
				
				request.setAttribute("terms", TERMS);
				request.setAttribute("marks", MARKS);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt1.close();
					pstmt2.close();
					rset1.close();
					rset2.close();
					conn.close();
				} catch (SQLException e) { }
			}
			
			request.getRequestDispatcher("/WEB-INF/app/grade/add_grade.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
