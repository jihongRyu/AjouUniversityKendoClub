package com.multi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.Util;
import com.multi.dto.CalendarDto;
import com.multi.model.CalendarDao;
import com.multi.dto.CalendarDto;
import com.multi.newmember.MemberDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class CalendarController
 */
@WebServlet("/calendar.do")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		System.out.println("cmd : " + cmd);
		
		String url="/calendar/list.jsp";
		request.setCharacterEncoding("utf-8");
		boolean bForward=true;
			
		
		if(cmd==null || cmd.equals("") || cmd.equals("list"))
		{
			doList(request, response);
		}
		else if(cmd.equals("insert"))
		{
			
			insert(request, response);	
			url = "/index.do";
			
		}
		else if(cmd.equals("delete"))
		{
		bForward=false; //뒤로가기 작동 안되게
		System.out.println("*******");
		doDeleteItem(request, response);
		url =request.getContextPath() + "/Calendar.do?cmd=list";
		}
		
		
		if( bForward)
		{
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,  response);
		}
		else
		{
			//뒤로가기 할때 캐쉬 없애기 
			response.setHeader("Cache-Control","no-store");   
			response.setHeader("Pragma","no-cache");   
			response.setDateHeader("Expires",0);   
			if (request.getProtocol().equals("HTTP/1.1"))   response.setHeader("Cache-Control", "no-cache"); 
			
			response.sendRedirect(url);
		}
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		doGet(request, response);
	}
	
	void doList(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		CalendarDao dao = new CalendarDao();
		CalendarDto dto = new CalendarDto();
	
		String key = Util.encoding(request.getParameter("key"));
		String sel = Util.nullToValue(request.getParameter("sel"), "0");
		  
		String Year=request.getParameter("year"); //나타내고자 하는 날짜
		String Month=request.getParameter("month");
		
		System.out.println(key);
	
		dto.setKey(key);
		dto.setSel(sel);
		dto.setMonth(Month);
		dto.setYear(Year);
		
		
		int pg=0;
		String temp = Util.nullToValue(request.getParameter("pg"), "0"); 
		pg = Integer.parseInt(temp);
		
		dto.setPg(pg);
		request.setAttribute("totalCount",  dao.getTotalCount(dto));
		request.setAttribute("list",  	dao.getList(dto));
	}
	
	
	
	
	
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		
		CalendarDto CalendarDto = new CalendarDto();
		CalendarDao CalendarDao = new CalendarDao();
		
		String date = request.getParameter("year");
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		
		System.out.println(date);
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		
		CalendarDto.setCalendarmemoYear(year);
		CalendarDto.setCalendarmemoMonth(month);
		CalendarDto.setCalendarmemoDay(day);
		CalendarDto.setCalendarmemoContents(request.getParameter("contents"));

		
		CalendarDao.insert(CalendarDto);
	
	}
	
	
	
	
	void doDeleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      CalendarDao dao = new CalendarDao();
	      CalendarDto dto = new CalendarDto();

	      String id = request.getParameter("id");
	      
	      System.out.println("id : " + id);
	      
	      dao.delete(id);
	   }
}