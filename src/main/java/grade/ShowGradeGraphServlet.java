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
 * Servlet implementation class ShowGradeGraphServlet
 */
@WebServlet("/graph/grade")
public class ShowGradeGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGradeGraphServlet() {
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
				String sql1 = "SELECT            Term.Term, AVG(Gpa)      "
							+ "FROM              Grade                    "
							+ "LEFT OUTER JOIN   Gpa                      "
							+ "ON                Grade.Grade = Gpa.GpaID  "
							+ "LEFT OUTER JOIN   Term                     "
							+ "ON                Grade.Term = Term.TermID "
							+ "WHERE             UserID = BINARY ?        "
							+ "GROUP BY          Grade.Term               ";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, USER_ID);
				rset1 = pstmt1.executeQuery();
				final ArrayList<Map<String, String>> GRADES = new ArrayList<Map<String, String>>();
				
				while (rset1.next()) {
					final Map<String, String> GRADE = new HashMap<>();
					String[] term = rset1.getString(1).split(" ");
					GRADE.put("term", term[1] + term[0]);
					GRADE.put("avgGpa", String.valueOf(Math.floor(rset1.getDouble(2) * 100) / 100));
					GRADES.add(GRADE);
				}
				
				
				String sql2 = "SELECT      Term   "
							+ "FROM        Term   "
							+ "ORDER BY    TermID ";
				pstmt2 = conn.prepareStatement(sql2);
				rset2 = pstmt2.executeQuery();
				final ArrayList<String> TERMS = new ArrayList<String>();
				
				while (rset2.next()) {
					TERMS.add(rset2.getString(1));
				}
				
				request.setAttribute("grades", GRADES);
				request.setAttribute("terms", TERMS);
				
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
			
			request.getRequestDispatcher("/WEB-INF/app/grade/graph_grade.jsp").forward(request, response);
		}
	}
}
