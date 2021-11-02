package com.multi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.multi.newmember.boardDao;
import com.multi.newmember.boardDto;

public class BoardController {
	
	boardDao dao =new boardDao();
	
	public void getList(HttpServletRequest request, boardDto dto) {
		
		request.setAttribute("boardList", dao.getList(dto));		
	}
	
	//write.jsp 페이지로 이동만 할 함수 - 단순한 페이지 이동용
	
	public void save(HttpServletRequest request) {
		
		boardDto dto = new boardDto();
		
		dto.setWriter(request.getParameter("writer"));
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		
		dao.save(dto);
	}
	
	public void view(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		boardDto boardDto = dao.getView(id);
		request.setAttribute("boardDto", boardDto);
	}

}
