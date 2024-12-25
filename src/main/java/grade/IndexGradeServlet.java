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
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset1 = null;
			ResultSet rset2 = null;
			
			try {
				conn = USER_DAO.getConnection();
				String sql1 = "SELECT            Term.Term, Grade.Code, Grade.Course, Mark.Mark, Gpa.Gpa "
							+ "FROM              Grade                                                   "
							+ "LEFT OUTER JOIN   Term                                                    "
							+ "ON                Grade.Term = Term.TermID                                "
							+ "LEFT OUTER JOIN   Mark                                                    "
							+ "ON                Grade.Grade = Mark.MarkID                               "
							+ "LEFT OUTER JOIN   Gpa                                                     "
							+ "ON                Grade.Grade = Gpa.GpaID                                 "
							+ "WHERE             UserID = BINARY ?                                       ";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, USER_ID);
				rset1 = pstmt1.executeQuery();
				final ArrayList<Map<String, String>> GRADES = new ArrayList<Map<String, String>>();
				
				while (rset1.next()) {
					final Map<String, String> GRADE = new HashMap<>();
					GRADE.put("term", rset1.getString(1));
					GRADE.put("code", rset1.getString(2));
					GRADE.put("course", rset1.getString(3));
					GRADE.put("grade", rset1.getString(4));
					GRADE.put("gpa", rset1.getString(5));
					GRADES.add(GRADE);
				}
				
				
				String sql2 = "SELECT            AVG(Gpa)                "
							+ "FROM              Grade                   "
							+ "LEFT OUTER JOIN   Gpa                     "
							+ "ON                Grade.Grade = Gpa.GpaID "
							+ "WHERE             UserID = BINARY ?       ";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, USER_ID);
				rset2 = pstmt2.executeQuery();
				Double avgGpa = 0.0;
				
				if (rset2.next()) {
					avgGpa = Math.floor(rset2.getDouble(1) * 100) / 100;
				}
				
				request.setAttribute("grades", GRADES);
				request.setAttribute("avgGpa", avgGpa);
				
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
			
			request.getRequestDispatcher("/WEB-INF/app/grade/index_grade.jsp").forward(request, response);
		}
	}
}
