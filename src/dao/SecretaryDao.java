package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Secretary;

public class SecretaryDao {
		//增加数据
		public int Add(Secretary secretary) {
			int result = 0;//增加失败
			String sql;
			Connection connection = DatabaseBean.getConnection();
			PreparedStatement preStmt = null;
			sql = "insert into secretary(username,password,name) values(?,?,?)";
			try {
				preStmt = connection.prepareStatement(sql);
				preStmt.setString(1, secretary.getUsername());
				preStmt.setString(2, secretary.getPassword());
				preStmt.setString(3, secretary.getName());
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
			String sql = "select * from secretary where username = ?";
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
			String sql = "select * from secretary where username = ?";
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
			String sql = "delete from secretary where id = ?";
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
		public Secretary Find(int id) {
			Secretary secretary = null;
			String sql = "select * from secretary where id = ?";
			Connection connection = DatabaseBean.getConnection();
			PreparedStatement preStmt = null;
			try {
				preStmt = connection.prepareStatement(sql);
				preStmt.setInt(1, id);
				ResultSet rs = preStmt.executeQuery();
				if(rs.next()) {
					secretary = new Secretary(rs.getString("username"),rs.getString("password"),rs.getString("name"));
					secretary.setId(rs.getInt(rs.getInt("id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return secretary;
		}
		
		//修改数据
		public int Modify(int id, String type, String s) {
			int result = 0;
			String sql = null;
			Connection connection = DatabaseBean.getConnection();
			PreparedStatement preStmt = null;
			switch(type) {
				case "username":
					sql = "Update secretary set username = ? where id = ?";
					break;
				case "password":
					sql = "Update secretary set password = ? where id = ?";
					break;
				case "name":
					sql = "Update secretary set name = ? where id = ?";
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
