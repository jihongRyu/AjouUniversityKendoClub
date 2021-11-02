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
import com.multi.dto.MemberManageDto;
import com.multi.model.MemberListDao;
import com.multi.model.MemberManageDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class memberManageController
 */
@WebServlet("/memberManage.do")
public class MemberManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		System.out.println("cmd : " + cmd);
		
		String url="/memberManage/list.jsp";
		request.setCharacterEncoding("utf-8");
		boolean bForward=true;
			
		
		if(cmd==null || cmd.equals("") || cmd.equals("list"))
		{
			doList(request, response);
		}
		else if(cmd.equals("view"))
		{
			doView(request, response);
			url="/memberManage/write.jsp";
		}
		else if(cmd.equals("update"))
		{
						
			doUpdate(request, response);			
			doList(request, response);
			url="/memberManage/list.jsp";
			
		}
		else if(cmd.equals("delete"))
		{
		bForward=false; //뒤로가기 작동 안되게
		System.out.println("*******");
		doDeleteItem(request, response);
		url =request.getContextPath() + "/memberManage.do?cmd=list";
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

		MemberManageDao dao = new MemberManageDao();
		MemberManageDto dto = new MemberManageDto();
	
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
	
	void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberManageDao dao = new MemberManageDao();
		MemberManageDto dto = new MemberManageDto();

		String userid= request.getParameter("id");
		//String userid="liujihong3";
		
		System.out.println("id : " +userid);
		
		request.setAttribute("dto",dao.getView(userid));
		
	}
	

	void doUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			MemberManageDao dao = new MemberManageDao();
			MemberManageDto dto = new MemberManageDto();		
			
			dto.setUserid(request.getParameter("userid"));
			dto.setUsername(request.getParameter("username"));
			dto.setSchoolNum(request.getParameter("schoolnum"));
			dto.setSchoolsub(request.getParameter("schoolsub"));
			dto.setEnteryear(request.getParameter("enteryear"));
			dto.setAddress1(request.getParameter("address1"));
			dto.setPhone(request.getParameter("phone"));
			System.out.println("레벨 : "+request.getParameter("level"));
			System.out.println("레벨코드 : "+request.getParameter("levelcode"));
			if (request.getParameter("level").equals("1")) {
				dto.setLevel(1);
			} else if (request.getParameter("level").equals("2")) {
				dto.setLevel(2);
			}else if (request.getParameter("level").equals("9")) {
				dto.setLevel(9);
			}
				

			dao.update(dto);//업데이트 실행.
		}
	
	void doDeleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberManageDao dao = new MemberManageDao();
			
		
	      String userid = request.getParameter("userid");
	      System.out.println("id : " + userid);
	      
	      dao.delete(userid);
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}