package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager extends UserDAO {	
	public UserDTO getLoginUser(String user_id, String password) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT  UserId, Password "
				   + "FROM    User             "
				   + "WHERE   UserId       = ? "
				   + "AND     Password     = ? ";
		UserDTO user = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				user = new UserDTO();
				user.setUserId(rset.getString(1));
				user.setPassword(rset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}
}
