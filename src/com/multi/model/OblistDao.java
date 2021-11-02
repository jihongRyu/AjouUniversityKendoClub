package com.multi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.multi.common.UtilDB;
import com.multi.dto.OblistDto;

public class OblistDao {
	
	public OblistDao()
	{
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getWhere(OblistDto dto)
	{
		String where ="";
		if( !dto.getKey().equals(""))
		{
			if(dto.getSel().equals("all"))
			{
				where = " where title like '%" + dto.getKey() +"%' "
						+ " or contents like '%" + dto.getKey() +"' "
						+ " or writer like '%" + dto.getKey() +"' ";
			}
			else if(dto.getSel().equals("1"))
			{
				where = " where title like '%" + dto.getKey() + "%' ";
			}
			else if(dto.getSel().equals("2"))
			{
				where = " where contents like '%" + dto.getKey() + "%' ";
			}
			else if(dto.getSel().equals("3"))
			{
				where = " where writer like '%" +  dto.getKey() + "%' ";
			}
		}
		
		System.out.println("where : " + where);
		return where;
	}
	public List<OblistDto> getList(OblistDto dto)
	{
		List<OblistDto> list 
		 	= new ArrayList<OblistDto>();
	
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
		buffer.append(" select aa.num, DATE_FORMAT(aa.wdate, '%Y%m%d') wdate, hit " ); 
		buffer.append("  ,aa.writer,  aa.userid , aa.title, aa.id, aa.group_id, aa.group_depth, aa.group_level ");
		buffer.append("  ,IFNULL(aa.filename1, ' ')filename1,  IFNULL(aa.filename2, ' ')filename2,  IFNULL(aa.filename3,' ')filename3 ");
		buffer.append("  from      ");
		buffer.append(" (                                             ");
		buffer.append("  select @ROWNUM:=@ROWNUM+1 AS num, a.* from        ");
		buffer.append("   (                                           ");
		buffer.append("    select userid , id, title, wdate, writer, group_id, group_depth, group_level, hit  ");
		buffer.append("     ,filename1, filename2, filename3           ");
		buffer.append("     from oblistboard  CROSS JOIN (SELECT @ROWNUM := 0) AS Rn                                    ");
		buffer.append( getWhere(dto));
		buffer.append("   order by group_id desc, group_level asc    ");
		buffer.append("   )a     ");
		buffer.append("    )aa where num>=? and num <=? ; ");
      
	    
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			stmt.setInt(1, start ); //where (SELECT @ROWNUM:=0)<=? 의 ? 값
			stmt.setInt(2, end); //where num>=? 의 ? 값
			
			System.out.println(buffer.toString());
			System.out.println("start : " + start);
			System.out.println("end : " + end);
					
			rs = stmt.executeQuery();
			
			
			
			
			//데이터 한건씩 읽을때마다 새로운 
			//dto 만들어서 list 에 추가 
			while(rs.next()) {
				
				OblistDto tempDto  = new OblistDto();
				
				tempDto.setNum(rs.getInt("num"));
				tempDto.setId(rs.getString("id"));
				tempDto.setTitle(rs.getString("title"));
				tempDto.setWriter(rs.getString("writer"));
				tempDto.setWdate(rs.getString("wdate"));
				tempDto.setHit(rs.getInt("hit"));
				tempDto.setGroup_id(rs.getInt("group_id"));
				tempDto.setGroup_depth(rs.getInt("group_depth"));
				tempDto.setGroup_level(rs.getInt("group_level"));
				tempDto.setFileName1(rs.getString("filename1"));
				tempDto.setFileName2(rs.getString("filename2"));
				tempDto.setFileName3(rs.getString("filename3"));
					
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
		
		String sql = "(select IFNULL(max(id),0)+1 from oblistboard )";
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
	
	public  void insert(OblistDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
		int id = getId();
		sql="insert into oblistboard (id, title, contents, writer, group_id, group_depth, group_level, ";
		sql += " filename1, filename2, filename3, userid, wdate, hit) values (";
		sql += " ?, ?,?,?, ?,?,?,?,?,?,?, now(), 0) ";
	    
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(sql);
			
			int pos=1;
			stmt.setInt(pos++, id);
			stmt.setString(pos++, dto.getTitle());
			stmt.setString(pos++, dto.getContents());
			stmt.setString(pos++, dto.getWriter());
			stmt.setInt(pos++, id);
			stmt.setInt(pos++, 0);
			stmt.setInt(pos++, 1);
			stmt.setString(pos++, dto.getFileName1());
			stmt.setString(pos++, dto.getFileName2());
			stmt.setString(pos++, dto.getFileName3());
			stmt.setString(pos++, dto.getUserid());
			
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
	public  void reply(OblistDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
		int id = getId();
		
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
			
			sql = "update oblistboard set group_level=group_level+1 where group_id=? and group_level>?";
			stmt = conn.prepareStatement(sql);
			int pos=1;
			stmt.setInt(pos++, dto.getGroup_id());
			stmt.setInt(pos++, dto.getGroup_level());
			System.out.println(sql);
			System.out.println(dto.getGroup_id());
			System.out.println(dto.getGroup_level());
			
			stmt.execute();
			stmt.close();
			
			
			sql="insert into oblistboard(id, title, contents, writer, group_id, group_depth, group_level, ";
			sql += " filename1, filename2, filename3, wdate, hit) values (";
			sql += " ?, ?,?,?, ?,?,?,?,?,?, sysdate, 0) ";
		
			stmt = conn.prepareStatement(sql);

			pos=1;
			stmt.setInt(pos++, id);
			stmt.setString(pos++, dto.getTitle());
			stmt.setString(pos++, dto.getContents());
			stmt.setString(pos++, dto.getWriter());
			stmt.setInt(pos++, dto.getGroup_id());
			stmt.setInt(pos++, dto.getGroup_depth()+1); //부모글 깊이 +1 
			stmt.setInt(pos++, dto.getGroup_level()+1); //부모글 그룹 레벨 + 1
			stmt.setString(pos++, dto.getFileName1());
			stmt.setString(pos++, dto.getFileName2());
			stmt.setString(pos++, dto.getFileName3());
			
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
		public  void update(OblistDto dto)
		{
			Connection conn=null;//디비연결
			PreparedStatement stmt=null;//쿼리실행객체
			ResultSet rs=null;//결과셋가져오는 객체
			
			String sql ="";
			
			sql="update oblistboard set title =?, contents=?, filename1=?, filename2=?, filename3=? ";
			sql += " where id=?";
			System.out.println(sql);
			
			try
			{
				conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
				stmt = conn.prepareStatement(sql);
		
				int pos=1;
				
				stmt.setString(pos++, dto.getTitle());
				stmt.setString(pos++, dto.getContents());
				stmt.setString(pos++, dto.getFileName1());
				stmt.setString(pos++, dto.getFileName2());
				stmt.setString(pos++, dto.getFileName3());
				stmt.setString(pos++, dto.getId());
				
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
		
		public void delete(String id)
		   {
		      Connection conn=null;//디비연결
		      PreparedStatement stmt=null;//쿼리실행객체
		      ResultSet rs=null;//결과셋가져오는 객체
		      
		      String sql ="";
		      
		      sql="delete from oblistboard ";
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

		
		
	public OblistDto getView(String id)
	{
		OblistDto dto = null;
	
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		//조회수 증가하기 
		String updateSql="update oblistboard set hit=IFNULL(hit, 0)+1 where id=?";
		
		String sql="select userid, id, title, writer, contents, DATE_FORMAT(wdate, '%Y%m%d') wdate, hit ";
		sql += ", IFNULL(filename1,' ') filename1, IFNULL(filename2, ' ') filename2, IFNULL(filename3, ' ') filename3 ";
		sql += ", group_id, group_depth, group_level ";
	    sql += " from oblistboard";
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
				dto = new OblistDto();
				dto.setUserid(rs.getString("userid"));
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
	
	
	public int getTotalCount(OblistDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql="select count(*) from oblistboard";
		sql = sql +  getWhere(dto);
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
