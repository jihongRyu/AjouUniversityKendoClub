package com.multi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.websocket.SendResult;

import org.json.simple.JSONObject;

import com.multi.dto.MemberManageDto;
import com.multi.model.MemberManageDao;
import com.multi.newmember.MemberDao;
import com.multi.newmember.MemberDto;

public class MemberController {
	
	MemberDao memberDao = new MemberDao();
	
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_id("1");
		System.out.println(request.getParameter("level")+"관리자 가입용 비밀먼호 반영 성공.");
		if (request.getParameter("level").equals("4885827")) {
			memberDto.setLevel(2);//관리자로 설정
		} else {
			memberDto.setLevel(9);//일반회원으로 설정	
		} 		
		memberDto.setUserid(request.getParameter("userid"));
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setUsername(request.getParameter("username"));
		memberDto.setSchoolnum(request.getParameter("schoolnum"));
		memberDto.setSchoolsub(request.getParameter("schoolsub"));
		memberDto.setEnteryear(request.getParameter("enteryear"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress1(request.getParameter("address1"));
		memberDto.setAddress2(request.getParameter("address2"));
		memberDto.setPhone(request.getParameter("phone"));
		
		memberDao.insert(memberDto);
	
	}
	
	public void logon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(); // session 개체를 request 객체를 이용해 가져온다.
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		MemberDto dto = memberDao.logon(userid, password);
		
		//DB구축전 테스트용
		if (dto==null) {	
		  if(userid.equals("admin")&&password.equals("q12345")) {
			
			String Userid = "admin";
			String username = "유지홍";
			String email = "aju201122870@gmail.com";
			String phone = "010-2321-9195";
			
			dto = new MemberDto();
			dto.setUserid(Userid);
			dto.setUsername(username);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddress1("동작구 상도동");
			dto.setPassword("q12345");	
			dto.setLevel(1);
			
		  }else if(userid.equals("liujihong")&&password.equals("q12345")) {
			
			String Userid = "liujihong";
			String username = "홍길동";
			String email = "liujihong@gmail.com";
			String phone = "010-1234-5678";
			
			dto = new MemberDto();
			dto.setUserid(Userid);
			dto.setUsername(username);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddress1("관악구 봉천동");
			dto.setPassword("q12345");	
			dto.setLevel(0);
			
		  }
		
		};
		
		
		
		if (dto!=null) {
			
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("username", dto.getUsername());
			session.setAttribute("email", dto.getEmail());
			session.setAttribute("phone", dto.getPhone());	
			session.setAttribute("address1", dto.getAddress1());
			session.setAttribute("level", dto.getLevel());
			session.setAttribute("schoolnum", dto.getSchoolnum());
			session.setAttribute("schoolsub", dto.getSchoolsub());
			session.setAttribute("enteryear", dto.getEnteryear());
			
			response.sendRedirect("/index.do");
			
		} else {
			
			session.setAttribute("userid","");
			session.setAttribute("username","");
			session.setAttribute("email","");
			session.setAttribute("phone","");
			session.setAttribute("address1", "");
			session.setAttribute("loginFail", "1");

			//JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해 주세요.", "경고!", 0);				
			//response.sendRedirect("/index.do?cmd=member&job=logon_form");
			
			response.setContentType("text/html; charset=UTF-8");			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디와 비밀번호를 확인해 주세요.'); location.href='/index.do?cmd=member&job=logon_form';</script>");
			out.flush();			
		}			
	}
	


	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.setAttribute("userid", "");
		session.setAttribute("username", "");
		session.setAttribute("email", "");
		session.setAttribute("phone", "");	
		session.setAttribute("level", null);	
		
	}
	
	public void idCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		memberDao.idCheck(userid);
		boolean result = memberDao.idCheck(userid);
		
		JSONObject object = new JSONObject();
		object.put("idcheck", result);
		
		PrintWriter output = response.getWriter();
		output.print(object);
		output.flush();
	}
	
	public void findId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		String userid = memberDao.findId(email, phone);
		PrintWriter output = response.getWriter();
		request.setAttribute("userid", userid);
		
		
	}
	
	public void findPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");
		
		String password = memberDao.findPwd(userid, email);
		PrintWriter output = response.getWriter();
		request.setAttribute("password", password);
		
		
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(); 
		String userid = (String)session.getAttribute("userid");
		String address1 = (String)session.getAttribute("address1");
		System.out.println("ssession:"+userid);			
		boolean delete = memberDao.delete(userid);
		
		session.setAttribute("userid", "");
		session.setAttribute("username", "");
		session.setAttribute("email", "");
		session.setAttribute("phone", "");	
		session.setAttribute("address1", "");	
		session.setAttribute("password", "");	
		
	}
	
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			MemberManageDao dao = new MemberManageDao();
			MemberManageDto dto = new MemberManageDto();	
			
			dto.setUserid(request.getParameter("userid"));
			dto.setUsername(request.getParameter("username"));
			dto.setSchoolNum(request.getParameter("schoolnum"));
			dto.setAddress1(request.getParameter("address1"));
			dto.setPhone(request.getParameter("phone"));
			System.out.println("레벨 : "+request.getParameter("level"));
			System.out.println("레벨코드 : "+request.getParameter("levelcode"));
			if (request.getParameter("level").equals("1")) {
				dto.setLevel(1);
			} else if (request.getParameter("level").equals("2")) {
				dto.setLevel(2);
			}
				
			dao.update(dto);//업데이트 실행.
		}
	
	
	
	
	
	
	
	
	
	
}
