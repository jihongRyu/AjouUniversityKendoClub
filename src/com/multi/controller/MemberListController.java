package com.multi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.Util;
import com.multi.dto.MemberListDto;
import com.multi.model.MemberListDao;
import com.multi.dto.meMberListDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class JobfesController
 */
@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		System.out.println("cmd : " + cmd);
		
		String url="/memberList/list.jsp";
		request.setCharacterEncoding("utf-8");
		boolean bForward=true;
			
		
		if(cmd==null || cmd.equals("") || cmd.equals("list"))
		{
			doList(request, response);
		}
		else if(cmd.equals("index"))
		{
			doList(request, response);
			url="/index.jsp";
		}
		else if(cmd.equals("write"))
		{ 
			url="/memberList/write.jsp";
		}
		else if(cmd.equals("insert"))
		{
			bForward=false; //뒤로가기 작동 안되게
			
			doWrite(request, response);
			url =request.getContextPath() + "/memberList.do?cmd=list";
			
		} else if(cmd.equals("delete"))
		{
		bForward=false; //뒤로가기 작동 안되게
		System.out.println("*******");
		doDeleteItem(request, response);
		url =request.getContextPath() + "/memberList.do?cmd=list";
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

		MemberListDao dao = new MemberListDao();
		MemberListDto dto = new MemberListDto();
		
		String key = Util.encoding(request.getParameter("key"));
		String sel = Util.nullToValue(request.getParameter("sel"), "0");
		System.out.println(key);
	
		dto.setKey(key);
		dto.setSel(sel);
		
		int pg=0;
		String temp = Util.nullToValue(request.getParameter("pg"), "0"); 
		pg = Integer.parseInt(temp);

		dto.setPg(pg);
		request.setAttribute("totalCount",  dao.getTotalCount(dto));
		request.setAttribute("list",  	dao.getList(dto));
	}
	

	
	void doWrite(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		MemberListDto memberListDto = new MemberListDto();
		MemberListDao memberListDao = new MemberListDao();
		
		memberListDto.setMember_id("1");
		memberListDto.setUsername(request.getParameter("username"));
		memberListDto.setSchoolnum(request.getParameter("schoolnum"));
		memberListDto.setSchoolsub(request.getParameter("schoolsub"));
		memberListDto.setEtc(request.getParameter("etc"));
		memberListDto.setEnteryear(request.getParameter("enteryear"));

		
		memberListDao.insert(memberListDto);
		

	
	}
	
	
	
	void doDeleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      MemberListDao dao = new MemberListDao();
	    
	      String schoolnum = request.getParameter("id");
	      
	      System.out.println("id : " + schoolnum);
	      
	      dao.delete(schoolnum);
	   }
}
