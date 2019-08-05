package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Student;

public class StudentDao {
	//增加数据
	public int Add(Student student) {
		int result = 0;//增加失败
		String sql;
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		sql = "insert into student(username,password,name,item) values(?,?,?,?)";
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setString(1, student.getUsername());
			preStmt.setString(2, student.getPassword());
			preStmt.setString(3, student.getName());
			preStmt.setString(4, student.getItem());
			result = preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DatabaseBean.closeDB(null, preStmt, connection);
		}
		return result;
	}
	
	//验证密码
	public boolean Login(String username, String password) {
		boolean result = false;
		String sql = "select * from student where username = ?";
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setString(1, username);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
				if(password.equals(rs.getString("password"))) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//获取id
	public int Login(String username) {
		int result = 0;
		String sql = "select * from student where username = ?";
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setString(1, username);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
					result = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	//删除数据
	public int Delete(int id) {
		int result = 0;
		String sql = "delete from student where id = ?";
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setInt(1, id);
			result = preStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	//查找数据
	public Student Find(int id) {
		Student student = null;
		String sql = "select * from student where id = ?";
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setInt(1, id);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
				student = new Student(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("item"));
				student.setId(rs.getInt(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	//修改数据
	public int Modify(int id, String type, String s) {
		int result = 0;
		String sql = null;
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		switch(type) {
			case "username":
				sql = "Update student set username = ? where id = ?";
				break;
			case "password":
				sql = "Update student set password = ? where id = ?";
				break;
			case "name":
				sql = "Update student set name = ? where id = ?";
				break;
			case "item":
				sql = "Update student set item = ? where id = ?";
				break;
		}
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setString(1, s);
			preStmt.setInt(2, id);
			result = preStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return result;
	}
	
}
