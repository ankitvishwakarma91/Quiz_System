package com.model;

import com.db.Provider;
import com.db.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.ArrayList;
//
//import oes.db.Provider;
//import oes.db.Students;

public class StudentsDao {

	public static boolean doValidate(Students sd) {
		// TODO Auto-generated method stub
		boolean status = false;
		try
		{
		
		Connection con = Provider.getOracleConnection();
		String sql = "select * from studenttable where username=? and password=?";//SQL
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, sd.getUsername());
		pst.setString(2, sd.getPassword());
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
		{
			sd.setName(rs.getString("name"));
			status = true;
		}
		else
		{
			status = false;
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}

//	public static boolean isUserNameExists(String username) {
//
//		boolean exists =  false;
//
//
//
//		try {
//
//			Connection con = Provider.getOracleConnection();
//			String sql = "SELECT 1 FROM studenttable WHERE username=?";
//			PreparedStatement pst = con.prepareStatement(sql);
//			pst.setString(1,username);
//			ResultSet rs = pst.executeQuery();
//
//			if (rs.next()){
//				exists = true;
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return exists;
//	}

	public static boolean isUserNameExists(String username) {
		if (username == null || username.trim().length() <= 0) {
			return true;
		}

		boolean exists = false;
		try {
			Connection con = Provider.getOracleConnection();
			String sql = "SELECT 1 FROM studenttable WHERE username=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	public static boolean isNameString(String name){
		if (name.length() <= 0){
			return false;
		}
		return name.matches("[a-zA-Z\\s]+");
	}

	public static boolean isPasswordLength(String password){
		if (password ==  null || password.trim().isEmpty() || password.length() < 6 || password.length()>10){
			return false;
		}
		return true;
	}

	public static boolean insertStudent(Students st) {

		boolean status = false;
		try
		{

			if (isUserNameExists(st.getUsername())){
				return false;
			}

			if (!isNameString(st.getName())){
				return false;
			}

			if (!isPasswordLength(st.getPassword())){
				return false;
			}
		
		Connection con = Provider.getOracleConnection();
		String sql = "insert into studenttable values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, st.getUsername());
		pst.setString(2, st.getPassword());
		pst.setString(3, st.getName());
		int val = pst.executeUpdate();
		if(val > 0)
		{
			status = true;
		}
		else
		{
			status = false;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public static ArrayList<Students> getAllRecords()
	{
        ArrayList<Students>  samp =new ArrayList<Students>();
		try
		{
			
		   samp.clear();
		   Connection con = Provider.getOracleConnection();
		   String sql = "select * from studenttable";
		   PreparedStatement pst = con.prepareStatement(sql);
		   
		   ResultSet rs = pst.executeQuery(sql);
		   while(rs.next())
		   {
			  Students s = new Students();
			   s.setUsername(rs.getString(1));
			  s.setPassword(rs.getString(2));
			  s.setName(rs.getString(3));
			   samp.add(s);
			 
			  
			   
		   }
		  

		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		return samp;
	}
	public static int deleteRecord(Students st) {

		int status = 0;
		try
		{
		
		Connection con = Provider.getOracleConnection();
		String sql = "delete from  studenttable where username=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, st.getUsername());
	
		int val = pst.executeUpdate();
		if(val > 0)
		{
			status = 1;
		}
		else
		{
			status = 0;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public static String getStudentName(String username) {
		// TODO Auto-generated method stub
		String name = null;
		try
		{
		
		Connection con = Provider.getOracleConnection();
		String sql = "select name from studenttable where username=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,username);
	
		ResultSet rs = pst.executeQuery();
		if(rs.next())
		{
			name = rs.getString(1);
		}
		else
		{
			name="DB-Error";
		}
		
	    }
		catch(Exception e)
		{
			name = e.getMessage();
		}
		return name;

   }
	public static int doUpdateNowRecord(String originalusername,String newuserid,String newpassword,String newname)
	{
		int status = 0;
		try
		{
		
		Connection con = Provider.getOracleConnection();
		String sql = "update studenttable set username=?,password=?,name=? where username=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,newuserid);
		pst.setString(2, newpassword);
		pst.setString(3, newname);
		pst.setString(4, originalusername);
	
		int val = pst.executeUpdate();
		if(val > 0)
		{
			status = 1;
		}
		else
		{
			status = -1;
		}
		}
		catch(Exception f)
		{
			status = 2;
			f.printStackTrace();
		}
		
	    
		return status;
	}
		
}
