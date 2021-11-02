package com.multi;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.Util;
import com.multi.controller.MemberController;
import com.multi.controller.boardController;
import com.multi.dto.GalleryDto;
import com.multi.dto.JobtalkDto;
import com.multi.model.GalleryDao;
import com.multi.model.JobtalkDao;
import com.multi.newmember.boardDto;


@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
	HashMap<String, String>map = new HashMap<String, String>();
    public IndexServlet() {
       map.put("/", "index.jsp");
       map.put("board", "list.jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		String job = request.getParameter("job");
		String path="";
		
		if(cmd ==null || cmd.equals("")) cmd ="/";		
		if(job ==null || job.equals("")) job ="list";
		
		if (cmd.equals("/")) {
							
			path = "/index.jsp";
		
		}		
		
		if (cmd.equals("board")) {
			
			doBoard(cmd, job, request, response);
			return; //�븿�닔醫낅즺
			
		}		
		
		if (cmd.equals("member")) {
			
			doMember(cmd, job, request, response);
			return; //�븿�닔醫낅즺
			
		}	
		
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(path);
		disp.forward(request, response);

}
	
	
	
	
	
	
	//http://127.0.0.1/MVC1/index.do?cmd=member&job=write
	private void doMember(String cmd, String job, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher disp;
		MemberController memController = new MemberController();
		String path = "/member/member_write.jsp";
		
		if(job.contentEquals("write")) {
			
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		} else if (job.equals("insert")) {
			
			memController.insert(request, response);
			response.sendRedirect("/member/member_write_result.jsp");
			  
		} else if (job.equals("logon_form")) {
			
			path = "/member/logon.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		}  else if (job.equals("logon")) {
			
			memController.logon(request, response);
			response.sendRedirect("/index.jsp");
						
		}  else if (job.equals("logout")) {
			
			memController.logout(request, response);
			response.sendRedirect("/index.jsp");
			
			//http://127.0.0.1:8080/MVC1/index.do?cmd=member&job=idCheck&userid=test
		} else if (job.equals("idCheck")) {
			memController.idCheck(request, response);
			
		} else if (job.equals("findid")) { //�럹�씠吏� �씠�룞留�
			path = "/member/findid.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
					
		}else if (job.equals("findidresult")) {
			memController.findId(request, response);
			path = "/member/findidresult.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		} else if (job.equals("findpassword")) {			
			path = "/member/findpassword.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		} else if (job.equals("findpwdresult")) {
			memController.findPwd(request, response);
			path = "/member/findpwdresult.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		} else if (job.equals("delete")) {
			
			memController.delete(request, response);
			path = "/index.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		}else if(cmd.equals("update")) {
								
			path="/index.jsp";
			
		}
		
		
		
		
	}

	void doBoard(String cmd, String job, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boardController boardController = new boardController();
		boardDto dto = new boardDto();
		String path="";
		RequestDispatcher disp;
		
		if (job.equals("list")) {
			
			boardController.getList(request, dto);	
			path="/board/list.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		} else if (job.equals("write")) {
			
			path="/board/write.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		} else if (job.equals("view")) {
			
			boardController.view(request);
			path="/board/view.jsp";
			disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
			
		}else if (job.equals("save")) {
			
			boardController.save(request);
			path = request.getContextPath()+"/index.do?cmd=board&job=list";
			response.sendRedirect(path);
			
			//�뿬湲곗꽌�뒗 forward媛��븘�땲�씪 redirect瑜� �빐以섏빞�븳�떎 request�뿉 �엯�젰諛쏆� 媛� 紐⑤몢 �젣嫄고븯湲�.
		}

	}
	
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
