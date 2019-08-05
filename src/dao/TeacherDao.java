package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao {
	//增加数据
	public int Add(Teacher teacher) {
		int result = 0;//增加失败
		String sql;
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		sql = "insert into teacher(username,password,name) values(?,?,?)";
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setString(1, teacher.getUsername());
			preStmt.setString(2, teacher.getPassword());
			preStmt.setString(3, teacher.getName());
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
			String sql = "select * from teacher where username = ?";
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
	
	//删除数据
	public int Delete(int id) {
		int result = 0;
		String sql = "delete from teacher where id = ?";
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
	
	//获取id
		public int Login(String username) {
			int result = 0;
			String sql = "select * from teacher where username = ?";
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
	
	//查找数据
	public Teacher Find(int id) {
		Teacher teacher = null;
		String sql = "select * from teacher where id = ?";
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		try {
			preStmt = connection.prepareStatement(sql);
			preStmt.setInt(1, id);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
				teacher = new Teacher(rs.getString("username"),rs.getString("password"),rs.getString("name"));
				teacher.setId(rs.getInt(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}
	
	//修改数据
	public int Modify(int id, String type, String s) {
		int result = 0;
		String sql = null;
		Connection connection = DatabaseBean.getConnection();
		PreparedStatement preStmt = null;
		switch(type) {
			case "username":
				sql = "Update teacher set username = ? where id = ?";
				break;
			case "password":
				sql = "Update teacher set password = ? where id = ?";
				break;
			case "name":
				sql = "Update teacher set name = ? where id = ?";
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
