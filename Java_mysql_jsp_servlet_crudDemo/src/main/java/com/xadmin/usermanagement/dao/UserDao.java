package com.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean1.User;



public class UserDao {
	private String jdbc_url="jdbc:mysql://localhost:3306/userdb";
	private String user_name="root";
	private String user_password="123456";
	private String jdbc_driver="com.mysql.jdbc.Driver";
	public static final String Insert_User_Sql="INSERT INTO users"+"(name,email,country)VALUES"+"(?,?,?)";
	public static final String Select_User_by_ID="select id,name,email,country from users where id=?";
	public static final String Select_All_User="select * from users";
	public static final String Delete_User_By_Id="delete from users where id=?";
	public static final String Update_User_Details="update users set name=?,email=?,country=? where id=?";
	public UserDao() {
	}

	protected Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName(jdbc_driver);
			con=DriverManager.getConnection(jdbc_url, user_name, user_password);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
     //-----------------------------------------------
	//insert user
	public  void insertUser(User user) throws SQLException
	{
		System.out.println(Insert_User_Sql);
		try 
		(Connection con= getConnection();
		PreparedStatement ps=con.prepareStatement(Insert_User_Sql);	)

		{
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3,user.getCountry());
			System.out.println(ps);
			ps.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
	}
	//---------------------------------------------------------------
	
	
//select user by id
	public User selectUser(int id) throws SQLException
	{
		User user=null;
		System.out.println(Insert_User_Sql);
		try 
		(Connection con= getConnection();
		PreparedStatement ps=con.prepareStatement(Select_User_by_ID);	)
		{
			ps.setInt(1, id);
			System.out.println(ps);
		
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				String name=rs.getString("name");
				String email=rs.getString("email");
				String country=rs.getString("country");
				user=new User(name, email, country);
			}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	//------------------------------------------------
	//select all user
	     public  List<User> selectAllUser()throws SQLException
	     {
	    	 List<User> users=new ArrayList();
	    	 try 
	 		(Connection con= getConnection();
	 		PreparedStatement ps=con.prepareStatement(Select_All_User);	)
	 		{
	 			
	 			System.out.println(ps);
	 		
	 			ResultSet rs=ps.executeQuery();
	 			while(rs.next())
	 			{
	 				int id=rs.getInt("Id");
	 				String name=rs.getString("name");
	 				String email=rs.getString("email");
	 				String country=rs.getString("country");
	 				users.add(new User(id,name, email, country));
	 			}
	 		}catch (SQLException e) {
	 			printSQLException(e);
	 		}
			return users;
	    	 
	     }
	 //----------------------------------------------
	     //Update user
	     public boolean updateUser(User user)throws SQLException
	     {
			
	    	 boolean rowUpdate=false;
			try 
			(Connection con= getConnection();
			PreparedStatement ps=con.prepareStatement(Update_User_Details);	)
			{
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3,user.getCountry());
				ps.setInt(4, user.getId());
				System.out.println(ps);
				rowUpdate =ps.executeUpdate()>0;
			}catch (SQLException e) {
				printSQLException(e);
			}
			
			
			return rowUpdate;
	    	 
	     }
	  //------------------------------------------------------------------
	 //delete user 
	     public boolean deleteUser(int id)throws SQLException
	     {
	    	 boolean rowDeleted=false;
				try 
				(Connection con= getConnection();
				PreparedStatement ps=con.prepareStatement(Delete_User_By_Id);	)
				{
				
					ps.setInt(1, id);
					System.out.println(ps);
					rowDeleted =ps.executeUpdate()>0;
				}catch (Exception e) {
					e.printStackTrace();
				}
			return rowDeleted;
	    	 
	     }
	//------------------------------------------------
	private void printSQLException(SQLException ex) {
		for(Throwable e:ex){
			if(e instanceof SQLException){
				e.printStackTrace(System.err);
				System.err.println("SQLState:"+((SQLException )ex).getSQLState());
				System.err.println("SQLError:"+((SQLException )ex).getErrorCode());
				System.err.println("Massage:"+e.getMessage());
				Throwable t=e.getCause();
				while(t!=null)
				{
					System.out.println(t);
					t=t.getCause();
				}
			}}}

}
