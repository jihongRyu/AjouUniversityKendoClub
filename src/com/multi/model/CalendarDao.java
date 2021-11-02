package com.multi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.multi.common.UtilDB;
import com.multi.dto.CalendarDto;

public class CalendarDao {
	
	public CalendarDao()
	{
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<CalendarDto> getList(CalendarDto dto)
	{
		List<CalendarDto> list 
		 	= new ArrayList<CalendarDto>();
	
		//int start = (pg-1) * pgSize + 1
		//int end = pg*pgSize;
		
		dto.setPgSize(9);
		
		int start=	(dto.getPg())*dto.getPgSize()+1;
		int end=	(dto.getPg()+1)*dto.getPgSize();
		
		Calendar cal=java.util.Calendar.getInstance(); //Calendar객체 cal생성
		int currentYear=cal.get(java.util.Calendar.YEAR); //현재 날짜 기억
		int currentMonth=cal.get(java.util.Calendar.MONTH);
		int currentDate=cal.get(java.util.Calendar.DATE);

		String Month = dto.getMonth();
		String Year = dto.getYear();
		  		  
		int year, month;		
			
		  if(Year == null && Month == null){ //처음 호출했을 때
			  year=currentYear;
			  month=currentMonth +1;
		  } else { //나타내고자 하는 날짜를 숫자로 변환
			   year=Integer.parseInt(Year);
			   month=Integer.parseInt(Month) +1;
			 
		  }
				
				
		System.out.println("pg : " + dto.getPg());
		System.out.println("pgSize : " + dto.getPgSize());
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("year : " + year);
		System.out.println("month : " + month);
		
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT calendarmemo_year, calendarmemo_month, calendarmemo_day, calendarmemo_contents FROM calendar" ); 
		buffer.append( " where calendarmemo_year = ? and calendarmemo_month = ?");
		
	    System.out.println(buffer.toString());
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			stmt.setInt(1, year);
			stmt.setInt(2, month);

			
			rs = stmt.executeQuery();
			
			//데이터 한건씩 읽을때마다 새로운 
			//dto 만들어서 list 에 추가 
			while(rs.next()) {
				CalendarDto tempDto  = new CalendarDto();
				
				tempDto.setCalendarmemoYear(rs.getString("calendarmemo_year"));
				tempDto.setCalendarmemoMonth(rs.getString("calendarmemo_month"));
				tempDto.setCalendarmemoDay(rs.getString("calendarmemo_day"));
				System.out.println(rs.getString("calendarmemo_day"));
				tempDto.setCalendarmemoContents(rs.getString("calendarmemo_contents"));
								
				System.out.println(tempDto.getCalendarmemoContents());
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
	
	
	
	public void insert(CalendarDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
	
		sql="insert into calendar(calendarmemo_year, calendarmemo_month , calendarmemo_day , calendarmemo_contents , wdate ) values (";
	    sql += " ?,?,?,?, now()) ";
	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, dto.getCalendarmemoYear());
			stmt.setString(2, dto.getCalendarmemoMonth());
			stmt.setString(3, dto.getCalendarmemoDay());			
			stmt.setString(4, dto.getCalendarmemoContents());

			
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
	
	
		
		public void delete(String id)
		   {
		      Connection conn=null;//디비연결
		      PreparedStatement stmt=null;//쿼리실행객체
		      ResultSet rs=null;//결과셋가져오는 객체
		      
		      String sql ="";
		      
		      sql="delete from Calendar ";
		      sql += " where id=?";
		      System.out.println(sql);
		      
		      try
		      {
		         conn = DriverManager.getConnection(   UtilDB.url, UtilDB.id,UtilDB.pwd);
		         stmt = conn.prepareStatement(sql);
		   
		         int pos=1;
		         
		         stmt.setString(pos++, id);
		         
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

		
		
	public CalendarDto getView(String id)
	{
		CalendarDto dto = null;
	
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		//조회수 증가하기 
		String updateSql="update Calendar set hit=IFNULL(hit, 0)+1 where id=?";
		
		String sql="select id, title, writer, contents,  DATE_FORMAT(wdate, '%Y%m%d') wdate, hit ";
		sql += ", nvl(filename1,' ') filename1, nvl(filename2, ' ') filename2, nvl(filename3, ' ') filename3 ";
		sql += ", group_id, group_depth, group_level ";
	    sql += " from calendar";
	    sql += " where id=? ";
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			
			//조회수 증가하기 
			stmt = conn.prepareStatement(updateSql);
			System.out.println(updateSql);
			stmt.setString(1,  id);
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  id);
			
			rs = stmt.executeQuery();
		
			if(rs.next()) {
				dto = new CalendarDto();
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setWdate(rs.getString("wdate"));
				dto.setContents(rs.getString("contents"));
				dto.setFileName1(rs.getString("filename1"));
				dto.setFileName2(rs.getString("filename2"));
				dto.setFileName3(rs.getString("filename3"));
				dto.setGroup_id(rs.getInt("group_id"));
				dto.setGroup_depth(rs.getInt("group_depth"));
				dto.setGroup_level(rs.getInt("group_level"));
				dto.setHit(rs.getInt("hit"));
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
	
	
	public int getTotalCount(CalendarDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql="select count(*) from Calendar";
		
	   	int result=0;
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(
				UtilDB.url, UtilDB.id,UtilDB.pwd);
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
