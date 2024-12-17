package grade;

import java.io.IOException;
import java.net.URLEncoder;
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
				String sql1 = "SELECT     Term  "
							+ "FROM       Term  "
							+ "ORDER BY   TermID";
				pstmt1 = conn.prepareStatement(sql1);
				rset1 = pstmt1.executeQuery();
				final ArrayList<String> TERMS = new ArrayList<String>();
				
				while (rset1.next()) {
					TERMS.add(rset1.getString(1));
				}
				
				String sql2 = "SELECT     Mark  "
							+ "FROM       Mark  "
							+ "ORDER BY   MarkID";
				pstmt2 = conn.prepareStatement(sql2);
				rset2 = pstmt2.executeQuery();
				final ArrayList<String> MARKS = new ArrayList<String>();
				
				while (rset2.next()) {
					MARKS.add(rset2.getString(1));
				}
				
				request.setAttribute("userId", USER_ID);
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
			PreparedStatement pstmt3 = null;
			ResultSet rset1 = null;
			ResultSet rset2 = null;
			
			String userId = request.getParameter("user_id");
			String term = request.getParameter("term");
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String grade = request.getParameter("grade");
			final boolean USER_AUTHENTICATION = userId.equals(USER_ID) ? true : false;
			
			try {
				conn = USER_DAO.getConnection();
				String sql1 = "SELECT   TermID  "
							+ "FROM     Term    "
							+ "WHERE    Term = ?";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, term);
				rset1 = pstmt1.executeQuery();
				String termId = "";
				if (rset1.next()) {
					termId = rset1.getString(1);
				}
				
				
				String sql2 = "SELECT   MarkID  "
							+ "FROM     Mark    "
							+ "WHERE    Mark = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, grade);
				rset2 = pstmt2.executeQuery();
				String markId = "";
				while (rset2.next()) {
					markId = rset2.getString(1);
				}
				
				
				if (USER_AUTHENTICATION) {
					String sql3 = "INSERT INTO Grade (UserID, Term, Code, Course, Grade) "
								+ "VALUES            (?, ?, ?, ?, ?)                     ";
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setString(1, USER_ID);
					pstmt3.setInt(2, Integer.parseInt(termId));
					pstmt3.setString(3, code);
					pstmt3.setString(4, name);
					pstmt3.setInt(5, Integer.parseInt(markId));
					pstmt3.executeUpdate();
				}
				
				
				userId = URLEncoder.encode(userId, "UTF-8");
				term = URLEncoder.encode(term, "UTF-8");
				code = URLEncoder.encode(code, "UTF-8");
				name = URLEncoder.encode(name, "UTF-8");
				grade = URLEncoder.encode(grade, "UTF-8");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt1.close();
					pstmt2.close();
					if (USER_AUTHENTICATION) {
						pstmt3.close();
					}
					rset1.close();
					rset2.close();
					conn.close();
				} catch (SQLException e) { }
			}
			
			if (USER_AUTHENTICATION) {
				response.sendRedirect(request.getContextPath() + "/index/grade");
			} else {
				response.sendRedirect(request.getContextPath() + "/add/grade");
			}
		}
	}
}
