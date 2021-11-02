package com.multi.newmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.multi.common.*;

public class MemberDao {
	
	public MemberDao() {
		try {
			Class.forName(UtilDB.driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(MemberDto memberDto) {
		
		Connection conn =null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs =null;
		String sql = "select IFNULL(max(member_id),0)+1 from tb_member";
		StringBuffer buffer = new StringBuffer();
		
		//1. 쿼리만들기
		/* insert into TB_MEMBER(MEMBER_ID,USERID,PASSWORD,USERNAME,ZIPCODE,ADDRESS1,ADDRESS2,PHONE,wdate,EMAIL) 
			values (?,?,?,?,?,?,?,?,?,sysdate); */
		
		
		buffer.append("insert into tb_member(SCHOOLSUB,ENTERYEAR,LEVEL, MEMBER_ID, USERID,PASSWORD,EMAIL,USERNAME,SCHOOLNUM,ZIPCODE,ADDRESS1,ADDRESS2,PHONE,wdate)");
		buffer.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())");
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
			stmt2.setString(pos++, memberDto.getSchoolsub());
			stmt2.setString(pos++, memberDto.getEnteryear());
			stmt2.setInt(pos++, memberDto.getLevel());
			stmt2.setString(pos++, member_id+"");			
			stmt2.setString(pos++, memberDto.getUserid());
			stmt2.setString(pos++, memberDto.getPassword());
			stmt2.setString(pos++, memberDto.getEmail());
			stmt2.setString(pos++, memberDto.getUsername());
			stmt2.setString(pos++, memberDto.getSchoolnum());
			//System.out.println(memberDto.getSchoolnum());
			stmt2.setString(pos++, memberDto.getZipcode());
			stmt2.setString(pos++, memberDto.getAddress1());
			stmt2.setString(pos++, memberDto.getAddress2());
			stmt2.setString(pos++, memberDto.getPhone());
			
			System.out.println(member_id);
			
			stmt2.execute();
			conn.commit();
			
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
	
	public MemberDto logon(String userid, String password) {
				
		MemberDto dto =null;
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		
		buffer.append(" select level, member_id, password, userid, username, phone, email, address1, schoolnum, schoolsub, enteryear from tb_member where userid =? and password =? ");
		
		System.out.println(buffer.toString());
		System.out.println(userid);
		System.out.println(password);
		
	
		
		
		try {
			
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			
			int pos =1;			
			stmt.setString(pos++, userid);
			stmt.setString(pos++, password);			
		
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				dto = new MemberDto();
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setLevel(rs.getInt("level"));
				dto.setSchoolnum(rs.getString("schoolnum"));
				dto.setSchoolsub(rs.getString("schoolsub"));
				dto.setEnteryear(rs.getString("enteryear"));
				dto.setAddress1(rs.getString("address1"));
				System.out.println(rs.getInt("level"));
				System.out.println(rs.getString("address1"));
				System.out.println(rs.getString("schoolnum"));
				System.out.println(rs.getString("enteryear"));
			}			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}	finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException s) {
				// TODO: handle exception
				s.printStackTrace();
			}
		}
		return dto;
	}	
	
	public String findId(String email, String phone) {
		
		MemberDto dto =null;
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		
		buffer.append(" select userid from tb_member where email=? and phone=? ");
		
		System.out.println(buffer.toString());
		System.out.println("email: " + email);
		System.out.println("phone: " + phone);
		
		
		try {
			
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			
			int pos =1;			
			stmt.setString(pos++, email);
			stmt.setString(pos++, phone);
		
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
			return rs.getString("userid");
			
			//return하면 여기서 함수가 중지된다. 즉 찾아서 userid를 리턴하는것
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}	finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException s) {
				// TODO: handle exception
				s.printStackTrace();
			}
		}
		return ""; //찾지 못했을 때는 ""가 반환된다.
	}	
	
	public String findPwd(String userid, String email) {
	
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		
		buffer.append(" select password from tb_member where userid=? and email=? ");
		
		System.out.println(buffer.toString());
		System.out.println("userid: " + userid);
		System.out.println("email: " + email);
		
		
		try {
			
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			
			int pos =1;			
			stmt.setString(pos++, userid);
			stmt.setString(pos++, email);
		
			rs = stmt.executeQuery();
			
			if (rs.next()) {
			return rs.getString("password");
			
			//return하면 여기서 함수가 중지된다. 즉 찾아서 password를 리턴하는것
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}	finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException s) {
				// TODO: handle exception
				s.printStackTrace();
			}
		}
		return ""; //찾지 못했을 때는 ""가 반환된다.
	}	
	
	
		public boolean idCheck(String userid) {
		
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		
		//select count(*) from tb_member where userid = 'test';
		buffer.append(" select count(*) from tb_member where userid =? ");
		
		System.out.println(buffer.toString());
		System.out.println(userid);
		
		
		
		try {
			
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			System.out.println("연결성공");
			int pos =1;			
			stmt.setString(pos++, userid);
					
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt(1)==0) return true;				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.out.println("DB 연결 실패");

		}	finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException s) {
				// TODO: handle exception
				s.printStackTrace();
			}
		}
		return false;
		
	}	
		
		public boolean delete(String userid) {
			
			boolean b = false;
			Connection conn =null;
			PreparedStatement stmt = null;
			
			System.out.println("USERID: "+ userid);
			StringBuffer buffer = new StringBuffer();
			
			//select count(*) from tb_member where userid = 'test';
			buffer.append(" DELETE FROM TB_MEMBER WHERE USERID =? ");
			
			System.out.println(buffer.toString());
			System.out.println("USERID: "+userid);		
			
			
			try {
				
				conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
				stmt = conn.prepareStatement(buffer.toString());					
				stmt.setString(1, userid);
				int rs = stmt.executeUpdate();
				
				if (rs>0) {
				
					System.out.println(userid);
					System.out.println("삭제성공");
					
				} else {
					System.out.println("삭제실패");
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}	finally {
				try {
					
					if (stmt!=null) stmt.close();
					if (conn!=null) conn.close();
				} catch (SQLException s) {
					// TODO: handle exception
					s.printStackTrace();
				}
			}
				
			return b;
		}	
}


