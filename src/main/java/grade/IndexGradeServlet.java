package grade;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class IndexGradeServlet
 */
@WebServlet("/index/grade")
public class IndexGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGradeServlet() {
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
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			try {
				conn = USER_DAO.getConnection();
				String sql = "SELECT            Term.Term, Grade.Code, Grade.Course, Mark.Mark "
						   + "FROM              Grade                                          "
						   + "LEFT OUTER JOIN   Term                                           "
						   + "ON                Grade.Term = Term.TermID                       "
						   + "LEFT OUTER JOIN   Mark                                           "
						   + "ON                Grade.Grade = Mark.MarkID                      "
						   + "WHERE             UserID = ?                                     ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, USER_ID);
				rset = pstmt.executeQuery();
				final ArrayList<Map<String, String>> GRADES = new ArrayList<Map<String, String>>();
				
				while (rset.next()) {
					final Map<String, String> GRADE = new HashMap<>();
					GRADE.put("term", rset.getString(1));
					GRADE.put("code", rset.getString(2));
					GRADE.put("course", rset.getString(3));
					GRADE.put("grade", rset.getString(4));
					GRADES.add(GRADE);
				}
				
				request.setAttribute("grades", GRADES);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					rset.close();
					conn.close();
				} catch (SQLException e) { }
			}
			
			request.getRequestDispatcher("/WEB-INF/app/grade/index_grade.jsp").forward(request, response);
		}
	}
}
