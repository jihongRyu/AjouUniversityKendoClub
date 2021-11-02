package com.multi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.UtilDB;
import com.multi.dto.MemberListDto;
import com.multi.dto.MemberManageDto;

public class MemberListDao {
	
	public MemberListDao()
	{
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getWhere(MemberListDto dto)
	{
		String where ="";
		if( !dto.getKey().equals(""))
		{
			if(dto.getSel().equals("all"))
			{
				where = " where username like '%" + dto.getKey() +"%' "
						+ " or schoolnum like '%" + dto.getKey() +"' "
						+ " or enteryear like '%" + dto.getKey() +"' ";
			}
			else if(dto.getSel().equals("1"))
			{
				where = " where username like '%" + dto.getKey() + "%' ";
			}
			else if(dto.getSel().equals("2"))
			{
				where = " where schoolnum like '%" + dto.getKey() + "%' ";
			}
			else if(dto.getSel().equals("3"))
			{
				where = " where enteryear like '%" +  dto.getKey() + "%' ";
			}
		}
		
		System.out.println("where : " + where);
		return where;
	}
	
	
	
	public List<MemberListDto> getList(MemberListDto dto)
	{
		List<MemberListDto> list 
		 	= new ArrayList<MemberListDto>();
	
		//int start = (pg-1) * pgSize + 1
		//int end = pg*pgSize;
		
		int start=	1;
		int end=	10;

		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체		
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(" select member_id, USERNAME, SCHOOLSUB, SCHOOLNUM, ETC, ENTERYEAR, wdate from MEMBERLISTBOARD " ); 
		buffer.append( getWhere(dto));
      
	    
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			
			System.out.println(buffer.toString());

			rs = stmt.executeQuery();
			
			
			
			
			//데이터 한건씩 읽을때마다 새로운 
			//dto 만들어서 list 에 추가 
			while(rs.next()) {
				
				MemberListDto tempDto  = new MemberListDto();
	
				
				tempDto.setUsername(rs.getString("username"));
				tempDto.setSchoolnum(rs.getString("schoolnum"));
				tempDto.setSchoolsub(rs.getString("schoolsub"));
				tempDto.setEnteryear(rs.getString("enteryear"));
				tempDto.setEtc(rs.getString("etc"));
					
				System.out.println("제목을 담는 중:" + tempDto.getTitle());
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
		
		String sql = "(select IFNULL(max(id),0)+1 from MEMBERLISTBOARD )";
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
	
	public void insert(MemberListDto memberListDto)
	{
		
		
		Connection conn =null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs =null;
		String sql = "select IFNULL(max(member_id),0)+1 from MEMBERLISTBOARD";
		StringBuffer buffer = new StringBuffer();
		
		//1. 쿼리만들기
		/* insert into TB_MEMBER(MEMBER_ID,USERID,PASSWORD,USERNAME,ZIPCODE,ADDRESS1,ADDRESS2,PHONE,wdate,EMAIL) 
			values (?,?,?,?,?,?,?,?,?,sysdate); */
		
		
		buffer.append("insert into MEMBERLISTBOARD( MEMBER_ID, USERNAME,SCHOOLNUM,SCHOOLSUB, ENTERYEAR, ETC, wdate)");
		buffer.append("values(?,?,?,?,?,?,NOW())");
		//프로그램상의 쿼리에  ; 붙이면 오류난다
		
		try {
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			//새로 추가할 아이디 가져오기
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int member_id = 1;
			if(rs.next()) member_id = rs.getInt(1); // 첫번째 항목 데이터를 가져온다.
			
			stmt2 = conn.prepareStatement(buffer.toString());
					
			
			int pos =1;			
			stmt2.setString(pos++, member_id+"");			
			stmt2.setString(pos++, memberListDto.getUsername());
			stmt2.setString(pos++, memberListDto.getSchoolnum());
			//System.out.println(memberListDto.getSchoolnum());
			stmt2.setString(pos++, memberListDto.getSchoolsub());
			System.out.println("schoolsub : "+memberListDto.getSchoolsub());
			stmt2.setString(pos++, memberListDto.getEnteryear());
			stmt2.setString(pos++, memberListDto.getEtc());

			
			System.out.println(member_id);
			
			stmt2.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}	finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (stmt2!=null) stmt2.close();
				if (conn!=null) conn.close();
			} catch (SQLException s) {
				// TODO: handle exception
				s.printStackTrace();
			}
		}

		
	}


		
	public void delete(String id)
		   {
		      Connection conn=null;//디비연결
		      PreparedStatement stmt=null;//쿼리실행객체
		      ResultSet rs=null;//결과셋가져오는 객체
		      
		      String sql ="";
		      
		      sql="delete from MEMBERLISTBOARD ";
		      sql += " where schoolnum=?";
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

		
		
	public MemberListDto getView(String id)
	{
		MemberListDto dto = null;
	
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		//조회수 증가하기 
		String updateSql="update MEMBERLISTBOARD set hit=IFNULL(hit, 0)+1 where id=?";
		
		String sql="select id, title, writer, contents,  DATE_FORMAT(wdate, '%Y%m%d') wdate, hit ";
		sql += ", IFNULL(filename1,' ') filename1, IFNULL(filename2, ' ') filename2, IFNULL(filename3, ' ') filename3 ";
		sql += ", group_id, group_depth, group_level ";
	    sql += " from MEMBERLISTBOARD";
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
				dto = new MemberListDto();
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
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
	
	
	public int getTotalCount(MemberListDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql="select count(*) from MEMBERLISTBOARD";
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
