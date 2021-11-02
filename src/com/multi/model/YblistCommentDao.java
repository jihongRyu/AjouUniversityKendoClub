package com.multi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.multi.common.Util;
import com.multi.common.UtilDB;

import com.multi.dto.YblistCommentDto;


public class YblistCommentDao {
	
	public YblistCommentDao()
	{
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<YblistCommentDto> getList(YblistCommentDto dto)
	{
		List<YblistCommentDto> list 
		 	= new ArrayList<YblistCommentDto>();
	
		//int start = (pg-1) * pgSize + 1
		//int end = pg*pgSize;
		
		int start=	(dto.getPg())*dto.getPgSize()+1;
		int end=	(dto.getPg()+1)*dto.getPgSize();
		
		System.out.println("pg : " + dto.getPg());
		System.out.println("pgSize : " + dto.getPgSize());
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("     select board_id, comment_id, ip, contents, writer, wdate  ");
		buffer.append("     from yblistCOMMENT                       ");
		buffer.append("     where board_id=?                         ");
		buffer.append("     order by comment_id desc                 ");
		                         
      
	    System.out.println(buffer.toString());
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			stmt.setString(1, dto.getBoard_id() );
			
			
			rs = stmt.executeQuery();
			
			//데이터 한건씩 읽을때마다 새로운 
			//dto 만들어서 list 에 추가 
			while(rs.next()) {
				YblistCommentDto tempDto  = new YblistCommentDto();
				tempDto.setBoard_id(rs.getString("board_id"));
				tempDto.setComment_id(rs.getString("comment_id"));
				tempDto.setIp(rs.getString("ip"));
				tempDto.setWriter(rs.getString("writer"));
				tempDto.setWdate(rs.getString("wdate"));
				tempDto.setContents(Util.textToDb(rs.getString("contents")));
					
				System.out.println(tempDto.getContents());
				list.add(tempDto);
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
		
		return list;
	}
	
	public int getId()
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		int id=1;
		
		String sql = "(select nvl(max(comment_id),0)+1 from yblistCOMMENT)";
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if( rs.next())
				id = rs.getInt(1);
			
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
		return id;
	}
	
	public void insert(YblistCommentDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
		int id = getId();
		sql="insert into yblistCOMMENT(board_id, comment_id, writer, contents, ip, ";
		sql += " wdate) values (";
		sql += " ?, ?,?,?,?, sysdate) ";
	
	    System.out.println(sql);
	    
	    System.out.println(dto.getBoard_id());
	    System.out.println(dto.getWriter());
	    System.out.println(dto.getComment_id());
	    System.out.println(dto.getContents());
	    System.out.println("asdasd");
	    
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			
			int pos=1;
			stmt.setString(pos++, dto.getBoard_id());
			stmt.setInt(pos++, id);
			stmt.setString(pos++, dto.getWriter());
			stmt.setString(pos++, dto.getContents());
			stmt.setString(pos++, dto.getIp());
			
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
	public  void delete(YblistCommentDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
		
		
		sql="delete from yblistCOMMENT where comment_id=?";
		
		System.out.println("comment_id:  " + dto.getComment_id());
		
		System.out.println(sql);
		
		System.out.println("delete함수");
		
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			
			int pos = 1;
			stmt.setString(pos++, dto.getComment_id());
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
	
	
	//답글 달기 
		public void update(YblistCommentDto dto)
		{
			Connection conn=null;//디비연결
			PreparedStatement stmt=null;//쿼리실행객체
			ResultSet rs=null;//결과셋가져오는 객체
			
			String sql ="";
			
			sql="update board set title =?, contents=?, filename1=?, filename2=?, filename3=? ";
			sql += " where id=?";
			System.out.println(sql);
			
			try
			{
				conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
				stmt = conn.prepareStatement(sql);
		
				int pos=1;
				
				stmt.setString(pos++, dto.getBoard_id());
				stmt.setString(pos++, dto.getComment_id());
				stmt.setString(pos++, dto.getWriter());
				stmt.setString(pos++, dto.getContents());
				stmt.setString(pos++, dto.getIp());
				stmt.setString(pos++, dto.getWdate());
				
				
				System.out.println("********************");
				stmt.execute();
				System.out.println("********************");
			
			}
			catch(Exception ex)
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
		
		
	public YblistCommentDto getView(String id)
	{
		YblistCommentDto dto = null;
	
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		
		/*
		//조회수 증가하기 
		String updateSql="update board set hit=hit+1 where id=?";
		*/
		
		
		String sql="select board_id, comment_id, writer, contents, ip,  DATE_FORMAT(wdate, '%Y%m%d') wdate ";
	    sql += " from yblistCOMMENT";
	    sql += " where id=? ";
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			
			//조회수 증가하기 
//			stmt = conn.prepareStatement(updateSql);
			stmt.setString(1,  id);
			stmt.execute();
			stmt.close();
			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  id);
			
			rs = stmt.executeQuery();
		
			if(rs.next()) {
				dto = new YblistCommentDto();
				dto.setBoard_id(rs.getString("board_id"));
				dto.setComment_id(rs.getString("comment_id"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setIp("ip");
				dto.setWdate(rs.getString("wdate"));
			}
			
		}
		catch(Exception ex)
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
	
	
	public int getTotalCount(YblistCommentDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql="select count(*) from yblistCOMMENT";
		sql = sql;
	   	int result=0;
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		
			if(rs.next()) {
				result=rs.getInt(1);
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
		
		return result;
	}
}
