package jtm.activity13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import jdk.internal.org.jline.utils.Log;

public class TeacherManager {

	protected Connection conn;
	static final String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false&characterEncoding=utf8";
	static final String user = "root";
	static final String pass = "Krejums123";

	ResultSet rs = null;
	Teacher teacher = null;
	PreparedStatement stmt;

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
				e.getMessage();
			}
		}
	}

	/**
	 * Returns a Teacher instance represented by the specified ID.
	 * 
	 * @param id the ID of teacher
	 * @return a Teacher object
	 * @throws ClassNotFoundException
	 */

	public Teacher findTeacher(int id) throws ClassNotFoundException {
		// TODO #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "database_activity.Teacher"

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement("SELECT * FROM database_activity.teacher where id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setFirstName(rs.getString("firstName"));
				teacher.setLastName(rs.getString("lastname"));

			} else {
				teacher = new Teacher();
				teacher.setId(rs.getInt(0));
				teacher.setFirstName(rs.getString(null));
				teacher.setLastName(rs.getString(null));

			}
		} catch (SQLException se) {

			se.printStackTrace();

		} finally {

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
	public List<Teacher> findTeacher(String firstName, String lastName) throws Exception {

		// TODO #3 Write an sql statement that searches teacher by first and
		// last name and returns results as ArrayList<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!

		List<Teacher> list = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement(
					"SELECT * FROM database_activity.teacher where firstname LIKE ? and lastname LIKE ?");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs = stmt.executeQuery();

			if (!rs.next()) {
				return Collections.emptyList();
			} else {
				while (rs.next()) {
					teacher.setFirstName(rs.getString("firstName"));
					teacher.setLastName(rs.getString("lastname"));
					list.add(teacher);
				}
			}

		} catch (Exception ex) {
			throw new Exception("Error: " + ex.getMessage());
		}

		return list;
	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 * @throws ClassNotFoundException
	 * 
	 */

	public boolean insertTeacher(String firstName, String lastName) throws ClassNotFoundException {
		// TODO #4 Write an sql statement that inserts teacher in database.
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement("INSERT INTO database_activity.teacher (firstname, lastname) VALUES (?, ?)");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs = stmt.executeQuery();

			int i = stmt.executeUpdate();
			if (i > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Insert teacher object into database
	 * 
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public boolean insertTeacher(Teacher teacher) throws SQLException, ClassNotFoundException {
		// TODO #5 Write an sql statement that inserts teacher in database.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.prepareStatement("INSERT INTO database_activity.teacher VALUES (?,?,?)");
			stmt.setObject(1, teacher);
			rs = stmt.executeQuery();
	 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 * 
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public boolean updateTeacher(Teacher teacher) throws SQLException, ClassNotFoundException {
		boolean status = false;
		// TODO #6 Write an sql statement that updates teacher information.
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, pass);
		stmt = conn.prepareStatement("UPDATE database_activity.teacher SET id = ?, firstname = ?, lastname = ? ");
		stmt.setObject(1, teacher);
		rs = stmt.executeQuery();
		conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteTeacher(int id) throws SQLException, ClassNotFoundException {
		// TODO #7 Write an sql statement that deletes teacher from database.
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, pass);
		stmt = conn.prepareStatement("DELETE FROM database_activity.teacher WHERE id= ?");
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		conn.commit();
	}catch(SQLException e) {
			e.printStackTrace();
	}
		return false;
	}

	public void closeConnecion() {
		// TODO Close connection to the database server and reset conn object to null
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
