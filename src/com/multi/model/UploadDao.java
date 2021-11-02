package com.multi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.multi.common.UtilDB;

public class UploadDao {
	//������ 
	public UploadDao()
	{
		try 
		{
			Class.forName(UtilDB.driverClass);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public  void insert(UploadDto dto)
	{
		Connection conn=null;//��񿬰�
		PreparedStatement stmt=null;//�������ఴü
		ResultSet rs=null;//����°������� ��ü
		
		String sql="insert into tb_upload(";
		sql += "id, name, email, photo ";
		sql += ",wdate) values (";
		sql += " (select nvl(max(id),0)+1 ";
		sql += " from tb_upload) , ";
		sql += " ?,?,?, sysdate) ";
	    	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(
				UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getName());
			stmt.setString(2, dto.getEmail());
			stmt.setString(3, dto.getPhoto());
			
			stmt.execute();
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public UploadDto getView( String id)
	{
		UploadDto dto = null;
	
		Connection conn=null;//��񿬰�
		PreparedStatement stmt=null;//�������ఴü
		ResultSet rs=null;//����°������� ��ü
		
		String sql="select id,name,email,photo ";
		sql += ", DATE_FORMAT(wdate, '%Y%m%d') wdate";
	    sql += " from tb_upload";
	    sql += " where id=? ";
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(
				UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  id);
			
			rs = stmt.executeQuery();
		
			if(rs.next()) {
				dto = new UploadDto();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setWdate(rs.getString("wdate"));
				dto.setPhoto(rs.getString("photo"));
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return dto;
	}
	
}
