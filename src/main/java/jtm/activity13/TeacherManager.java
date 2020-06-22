package jtm.activity13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;


public class TeacherManager {

	protected Connection conn;
	
	//Start
	private static Logger log = Logger.getLogger(TeacherManager.class);
	static final String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false&characterEncoding=utf8";
	static final String user = "root";
	static final String pass = "Krejums123";

	
	public TeacherManager() {

		// TODO #1 When new TeacherManager is created, create connection to the
		// database server:
		// url =
		// "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false&characterEncoding=utf8"
		// user = "root"
		// pass = "Student007"
		// Hints:
		// 1. Do not pass database name into url, because some statements
		// for tests need to be executed server-wise, not just database-wise.
		// 2. Set AutoCommit to false and use conn.commit() where necessary in
		// other methods

		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
	}

	/**
	 * Returns a Teacher instance represented by the specified ID.
	 * 
	 * @param id the ID of teacher
	 * @return a Teacher object
	 * 
	 */

	public Teacher findTeacher(int id) {
		// TODO #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "database_activity.Teacher"
		Teacher teacher = new Teacher(0,null,null);
		
		try {
			PreparedStatement pStmt = null;
			pStmt = conn.prepareStatement("SELECT * FROM database_activity.Teacher WHERE id = ? " );
			pStmt.setInt(1, id);
			conn.commit();
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				teacher = new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3));
				return teacher;
			}
				
		}catch(SQLException e) {
			log.debug(e.getMessage());	
		}
		return teacher;
	}

	/**
	 * Returns a list of Teacher object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of teacher.
	 * @param lastName  the last name of teacher.
	 * @return a list of Teacher object.
	 */
	public List<Teacher> findTeacher(String firstName, String lastName) {

		// TODO #3 Write an sql statement that searches teacher by first and
		// last name and returns results as ArrayList<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!

		List<Teacher> list = new ArrayList<Teacher>();

		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement ("SELECT * FROM database_activity.Teacher where firstname LIKE ? and lastname LIKE ? "
					+ "order by ID ASC");
			
			stmt.setString(1, "%" + firstName + "%");
			stmt.setString(2, "%" + lastName + "%");
			conn.commit();
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Teacher(rs.getInt("ID"), rs.getString("firstname"), rs.getString("lastname")));
			}
			
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			log.debug(e.getMessage());
		}

		return list;
	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 * 
	 * 
	 */

	public boolean insertTeacher(String firstName, String lastName) {
		// TODO #4 Write an sql statement that inserts teacher in database.
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO database_activity.Teacher (firstname, lastname) VALUES (?, ?)");
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Insert teacher object into database
	 * 
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertTeacher(Teacher teacher) {
		// TODO #5 Write an sql statement that inserts teacher in database.
		boolean status = false;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO database_activity.Teacher (id, firstname, lastname) VALUES (?,?,?)");
			stmt.setString(1, Integer.toString(teacher.getId()));
			stmt.setString(2, teacher.getFirstName());
			stmt.setString(3, teacher.getLastName());
			int rows = stmt.executeUpdate();
			conn.commit();
			
			if(rows == 1) {
				status = true;
			}
		}catch(Exception e) {
			log.debug(e.getMessage());
			status = false;
		}
		
		return status;
	}

	/**
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 * 
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateTeacher(Teacher teacher) {
		boolean status = false;
		// TODO #6 Write an sql statement that updates teacher information.
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE database_activity.Teacher SET firstname = ?, lastname = ? " + "where ID = ?");
			stmt.setString(1, teacher.getFirstName());
			stmt.setString(2, teacher.getLastName());
			stmt.setInt(3,  teacher.getId());
			int rows = stmt.executeUpdate();
			conn.commit();
			stmt.close();
			
			if(rows == 1) {
				status = true;
			}
		}catch(Exception e) {
			log.debug(e.getMessage());
			status = false;
		}
		return status;
	}

	/**
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 */
	public boolean deleteTeacher(int id) {
		// TODO #7 Write an sql statement that deletes teacher from database.
		boolean status = false;
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM database_activity.Teacher WHERE id= " + id);
			int rows = stmt.executeUpdate();
			conn.commit();
			stmt.close();
			
			if(rows == 1) {
				status = true;
			}else {
				status = false;
			}
		}catch(SQLException e) {
				log.debug(e.getMessage());
				status = false;
		}
			return status;
	}
	
	public void closeConnecion() {
		// TODO Close connection to the database server and reset conn object to null
		try {
			if (conn != null)
				conn.close();
			conn = null;
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
}
