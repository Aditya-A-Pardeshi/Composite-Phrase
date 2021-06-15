package db;

import java.sql.*;


public class DataBase
{
	
	static String DB_NAME="composite_phrase_genrator_db";
	static String USERNAME="root";
	static String PASSWORD="root@123";
	//static String PASSWORD="";
	public static Connection getConnection(String dbname,String uid,String pwd)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,uid,pwd);
		return con;
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return null;
	}
	public static Connection getConnection(String dbname)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,"root","");
		return con;
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return null;
	}
	
	public static Connection getConnection()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/" +DB_NAME + "?useSSL=false",USERNAME,PASSWORD);
		return con;
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return null;
	}
}