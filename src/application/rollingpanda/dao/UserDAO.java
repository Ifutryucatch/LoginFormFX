package application.rollingpanda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.rollingpanda.models.User;

public class UserDAO {
	
	public static User getUserUsername(Connection conn, String username) {
		User retVal = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT user_id, user_password FROM user_info WHERE user_username = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, username);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				index = 1;
				String password = rset.getString(index++);
				
				retVal = new User(password, username);				
			}
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return retVal;
	}
	
	public static User getUserPassword(Connection conn, String password) {
		User retVal = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT user_id, user_username FROM user_info WHERE user_password = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, password);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				index = 1;
				String username = rset.getString(index++);
				
				retVal = new User(password, username);				
			}
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return retVal;
	}

	public static boolean addUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO user_info(user_username, user_password) VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			
			return pstmt.executeUpdate() == 1;
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
}
