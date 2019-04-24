package application.rollingpanda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.rollingpanda.models.User;

public class UserDAO {
	
	public static boolean delete(Connection conn, boolean id) {
		Statement stmt = null;
		try {
			
			String query = "DELETE FROM user_info WHERE user_id = " + id;
			
			stmt = conn.createStatement();
			return stmt.executeUpdate(query) == 1;
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	public static List<User> getAll(Connection conn){
		List<User> retVal = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT user_username, user_password, user_role FROM user_info";
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				int index = 1;
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				String role = rset.getString(index++);
				
				User u = new User(username, password, role);
				retVal.add(u);
			}
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return retVal;
	}
	
	public static User getUserUsername(Connection conn, String username) {
		User retVal = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT user_id, user_password, user_role FROM user_info WHERE user_username = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, username);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				index = 2;
				String password = rset.getString(index++);
				String role = rset.getString(index++);
				
				retVal = new User(username, password, role);				
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
			String query = "SELECT user_id, user_username, user_role FROM user_info WHERE user_password = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, password);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				index = 2;
				String username = rset.getString(index++);
				String role = rset.getString(index++);
				
				retVal = new User(username, password, role);				
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
			String query = "INSERT INTO user_info(user_username, user_password, user_role) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getRole());
			
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
