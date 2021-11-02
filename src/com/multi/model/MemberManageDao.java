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
import com.multi.dto.MemberManageDto;

public class MemberManageDao {
	
	public MemberManageDao()
	{
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getWhere(MemberManageDto dto)
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
	
	public List<MemberManageDto> getList(MemberManageDto dto)
	{
		List<MemberManageDto> list 
		 	= new ArrayList<MemberManageDto>();
	
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
		buffer.append("select aa.num, DATE_FORMAT(aa.wdate, '%Y%m%d') wdate,  " ); 
		buffer.append(" aa.ENTERYEAR, aa.SCHOOLSUB, aa.SCHOOLNUM, aa.MEMBER_ID,  aa.USERID, aa.EMAIL, aa.USERNAME, aa.LEVEL, aa.ADDRESS1 ");
		buffer.append(" , aa.ADDRESS2 , aa.PHONE  ");
		buffer.append(" from                                          ");
		buffer.append(" (                                             ");
		buffer.append("   select @ROWNUM:=@ROWNUM+1 AS num, a.* from                 ");
		buffer.append("   (                                           ");
		buffer.append("     select ENTERYEAR, SCHOOLSUB, SCHOOLNUM, MEMBER_ID,USERID, EMAIL, wdate, USERNAME, LEVEL, ADDRESS1, ADDRESS2, PHONE   ");
		buffer.append("     from tb_member CROSS JOIN (SELECT @ROWNUM := 0) AS Rn                             ");
		buffer.append(getWhere(dto));
		buffer.append("     order by MEMBER_ID asc   ");
		buffer.append("   )a    ");
		buffer.append("  )aa where num>=? and num <=?       ");
      
	    System.out.println(buffer.toString());
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			stmt.setInt(1, start );
			stmt.setInt(2, end);
			
			rs = stmt.executeQuery();
			
			//데이터 한건씩 읽을때마다 새로운 
			//dto 만들어서 list 에 추가 
			while(rs.next()) {
				MemberManageDto tempDto  = new MemberManageDto();
				
				tempDto.setNum(rs.getInt("num"));
				tempDto.setUserid(rs.getString("userid"));
				if (rs.getString("email")!=null) {
					tempDto.setEmail(rs.getString("email"));
				} else {
					tempDto.setEmail("정보없음");
				}
				if (rs.getString("username")!=null) {
					tempDto.setUsername(rs.getString("username"));
				} else {
					tempDto.setUsername("정보없음");
				}
				if (rs.getString("wdate")!=null) {
					tempDto.setRegdate(rs.getString("wdate"));
				} else {
					tempDto.setRegdate("정보없음");
				}
				if (rs.getString("address1")!=null) {
					tempDto.setAddress1(rs.getString("address1"));
				} else {
					tempDto.setAddress1("정보없음");
				}
				tempDto.setAddress2(rs.getString("address2"));
				
				if (rs.getInt("level")==1) {
					tempDto.setLevelcode("일반회원");
				} else if(rs.getInt("level")==2) {
					tempDto.setLevelcode("관리자");
				} else if(rs.getInt("level")==9) {
					tempDto.setLevelcode("예비회원");
				} else {
					tempDto.setLevelcode("정보없음");
				}
				
				if (rs.getString("phone")!=null) {
					tempDto.setPhone(rs.getString("phone"));
				} else {
					tempDto.setPhone("정보없음");
				}
				if (rs.getString("schoolnum")!=null) {
					tempDto.setSchoolNum(rs.getString("schoolnum"));
				} else {
					tempDto.setSchoolNum("정보없음");
				}
				if (rs.getString("schoolsub")!=null) {
					tempDto.setSchoolsub(rs.getString("schoolsub"));
				} else {
					tempDto.setSchoolsub("정보없음");
				}
				if (rs.getString("enteryear").equals("")) {
					tempDto.setEnteryear("정보없음");
				} else {
					tempDto.setEnteryear(rs.getString("enteryear"));
				}
				

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
		
		String sql = "(select IFNULL(max(id),0)+1 from MEMBERMANAGE)";
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
	
	public  void insert(MemberManageDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql ="";
		int id = getId();
		sql="insert into JOBSTUDYBOARD(id, title, contents, writer, group_id, group_depth, group_level, ";
		sql += " filename1, filename2, filename3, wdate, hit) values (";
		sql += " ?, ?,?,?, ?,?,?,?,?,?, sysdate, 0) ";
	
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
	
        //회원정보 수정
		public  void update(MemberManageDto dto)
		{
			Connection conn=null;//디비연결
			PreparedStatement stmt=null;//쿼리실행객체
			ResultSet rs=null;//결과셋가져오는 객체
			
			String sql ="";
			
			sql="update tb_member set username =?, schoolnum=?, level=?, phone=?, address1=? , schoolsub=? , enteryear=? ";
			sql += " where userid=?";
			System.out.println(sql);
			
			
			try
			{
				conn = DriverManager.getConnection(	UtilDB.url, UtilDB.id,UtilDB.pwd);
				stmt = conn.prepareStatement(sql);
		
				int pos=1;
				
				stmt.setString(pos++, dto.getUsername());
				stmt.setString(pos++, dto.getSchoolNum());
				stmt.setInt(pos++, dto.getLevel());
				stmt.setString(pos++, dto.getPhone());
				stmt.setString(pos++, dto.getAddress1());
				stmt.setString(pos++, dto.getSchoolsub());
				stmt.setString(pos++, dto.getEnteryear());				
				stmt.setString(pos++, dto.getUserid());
				
				System.out.println(dto.getUserid());
				System.out.println(dto.getUsername());
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
		      
		      sql="delete  from tb_member ";
		      sql += " where userid=?";
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

		
		
	public MemberManageDto getView(String userid)
	{
		MemberManageDto dto = null;	
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체		
		
		
		String sql="select SCHOOLNUM, MEMBER_ID,USERID, EMAIL, wdate, USERNAME, LEVEL, ADDRESS1, ADDRESS2, PHONE, ENTERYEAR, SCHOOLSUB from tb_member";
			     sql += " where userid=? ";
	   	
	    System.out.println(sql);
		try
		{
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id,UtilDB.pwd);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  userid);
			
			rs = stmt.executeQuery();
		
			if(rs.next()) {
				dto = new MemberManageDto();
				dto.setUserid(rs.getString("userid"));
				dto.setSchoolNum(rs.getString("SCHOOLNUM"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("wdate"));
				dto.setUsername(rs.getString("username"));
				dto.setLevel(rs.getInt("LEVEL"));
				dto.setAddress1(rs.getString("address1"));
				dto.setPhone(rs.getString("phone"));
				dto.setSchoolsub(rs.getString("schoolsub"));
				dto.setEnteryear(rs.getString("enteryear"));
				System.out.println(dto.getPhone());
				System.out.println(dto.getLevel());
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
	
	
	public int getTotalCount(MemberManageDto dto)
	{
		Connection conn=null;//디비연결
		PreparedStatement stmt=null;//쿼리실행객체
		ResultSet rs=null;//결과셋가져오는 객체
		
		String sql="select count(*) from tb_member";
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
