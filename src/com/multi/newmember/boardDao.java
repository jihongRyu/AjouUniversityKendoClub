package com.multi.newmember;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.multi.common.UtilDB;

public class boardDao {
	//생성자 - jdbc driver 로딩하기
		
	public boardDao() {
		try {
			Class.forName(UtilDB.driverClass);
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*List<boardDto> getList(boardDto dto) {
		List<boardDto> boardList = new ArrayList<boardDto>();
		
		boardList.add(new boardDto());
		for (int i = 2; i <=10 ; i++) {
			boardList.add(new boardDto(String.valueOf(i), "홍길동"+i, "제목"+i,"내용"+i,"2019-03-20"+i));
		}
		for (int i = 0; i <boardList.size() ; i++) {
			System.out.println(boardList.get(i).getWriter());
		}
		return boardList;
	} */
	//boardDto dto - 매개변수, 외부에서 선택한 검색내용을 보내기 위해서
	//getList - 데이터 10개만 읽어서 ArrayList 객체에 담아보내면 된다.
	public List<boardDto> getList(boardDto dto){
		List<boardDto> boardList = new ArrayList<boardDto>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from tb_board1");
		
		try {
			
			conn=DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			rs=stmt.executeQuery();
			
			while (rs.next()) {
				
				boardDto boardDto = new boardDto();
				boardDto.setId(rs.getString("id"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setRegdate(rs.getString("regdate"));
				
				boardList.add(boardDto);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();				
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}				
		return boardList;
	}

	public void save(boardDto dto) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into tb_board1(id, title, contents, writer, regdate)");
		buffer.append("values (boardSeq.nextval,?,?,?,sysdate)");
		
		try {
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			int pos =1;
			stmt.setString(pos++, dto.getTitle());
			stmt.setString(pos++, dto.getContents());
			stmt.setString(pos++, dto.getWriter());
			
			stmt.execute();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (Exception s) {
				s.printStackTrace();
			}
		}
}
	
public boardDto getView(String id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(" select id, title, contents, writer, ");
		buffer.append(" to_char(regdate, 'yyyy-mm-dd') regdate ");
		buffer.append(" from tb_board1 ");
		buffer.append(" where id= ?");
		
		//쿼리가 제대로 작성되었는지 콘솔창에 완성된 쿼리를 찍어서 확인해봐야한다.
		System.out.println(buffer.toString());
		
		boardDto boardDto = new boardDto();
		
		try {
			conn = DriverManager.getConnection(UtilDB.url, UtilDB.id, UtilDB.pwd);
			stmt = conn.prepareStatement(buffer.toString());
			stmt.setString(1,  id+"");
			rs = stmt.executeQuery();
			if(rs.next()) {
				boardDto.setId(rs.getString("id"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				
				//\n을 <br>태그로 바꾸어서 보내줘야 줄바꿈이 된다.
				String contents = boardDto.getContents();
				contents = contents.replace("\n", "<br>");
				boardDto.setContents(rs.getString("contents"));
				boardDto.setRegdate(rs.getString("regdate"));			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (Exception s) {
				s.printStackTrace();
			}
		}
		return boardDto;	
}

}
