package user;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import user.user;
import user.userDBService;
import user.user;
import user.userDBService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class userCntrl
 */

//localhost:8080/loginDetail/login/userCntrl?page=userDashboard&opr=showAll&pageNo=1&limit=100

@WebServlet("/user/userCntrl")
public class userCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userCntrl() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String opr = request.getParameter("opr");
		
		/*
		 * if(opr.equals("Save")) { // add new record
		 * 
		 * String code=request.getParameter("code"); String
		 * value=request.getParameter("value");
		 * 
		 * System.out.println(code);
		 * 
		 * user user = new user(); user.setCode(code); user.setValue(value);
		 * 
		 * userDBService userDBService = new userDBService();
		 * userDBService.createuser(user);
		 * 
		 * response.sendRedirect("saveSuccessuser.jsp"); //RequestDispatcher rd =
		 * request.getRequestDispatcher("saveSuccessuser.jsp"); //rd.forward(request,
		 * response); }
		 */
			
			if(opr.equals("view")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
					user user = new user();
					userDBService userDBService = new userDBService();
					user = userDBService.getuser(id);
					
					
					System.out.println(user.getId());
					System.out.println(user.getUserId());
					System.out.println(user.getPwd());
					System.out.println(user.getGroupId());
					System.out.println(user.getUpdateBy());
					System.out.println(user.getUpdateDt());
					System.out.println(user.getUpdateTime());

					request.setAttribute("user",user);
					//response.sendRedirect("viewuser.jsp");
				    RequestDispatcher rd = request.getRequestDispatcher("viewUser.jsp");
					rd.forward(request, response);
					
					
					
				}
			
		    }
			else if(opr.equals("edit")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					user user = new user();
					userDBService userDBService = new userDBService();
					user = userDBService.getuser(id);
					
					request.setAttribute("user",user);
									
				    RequestDispatcher rd = request.getRequestDispatcher("editUser.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			
			else if(opr.equals("update")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					String userId=request.getParameter("userId");
					String Pwd=request.getParameter("Pwd");
					String groupId=request.getParameter("groupId");
					String updateBy=request.getParameter("updateBy");
					String updateDt=request.getParameter("updateDt");
					String updateTime=request.getParameter("updateTime");
					
					//System.out.println(code);
					
					user user = new user();
					
					user.setId(id);
					user.setUserId(userId);
					user.setPwd(Pwd);
					user.setGroupId(groupId);
					user.setUpdateBy(updateBy);
					user.setUpdateDt(DateService.getSTDYYYMMDDFormat(updateDt));
					user.setUpdateTime(updateTime);
					
					userDBService userDBService = new userDBService();
					userDBService.updateuser(user);
					//System.out.println(code);
					//System.out.println(value);
					//System.out.println(id);
					
					response.sendRedirect("updateSuccessUser.jsp");
									
				
					
				}
			
		    }
			
			else if(opr.equals("delete")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));	
					user user = new user();
					userDBService userDBService = new userDBService();
					user.setId(id);
					userDBService.deleteuser(id);
					request.setAttribute("user",user);
					
					
					RequestDispatcher rd = request.getRequestDispatcher("deleteUser.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			else if(opr.equals("print")) {
				int id = Integer.parseInt(request.getParameter("id"));
				user user = new user();
				userDBService userDBService = new userDBService();
				user = userDBService.getuser(id);
				
				System.out.println(user.getId());
				System.out.println(user.getUserId());
				System.out.println(user.getPwd());
				System.out.println(user.getGroupId());
				System.out.println(user.getUpdateBy());
				System.out.println(user.getUpdateDt());
				System.out.println(user.getUpdateTime());
				request.setAttribute("user",user);
				//response.sendRedirect("viewuser.jsp");
			    RequestDispatcher rd = request.getRequestDispatcher("viewUser.jsp");
				rd.forward(request, response);
			}
			
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String opr = request.getParameter("opr");
		
		if(opr.equals("Save")) { // add new record
		
		
		String userId=request.getParameter("userId");
		String pwd=request.getParameter("Pwd");
		String groupId=request.getParameter("groupId");
		String updateBy=request.getParameter("updateBy");
		String updateDt=request.getParameter("updateDt");
		String updateTime=request.getParameter("updateTime");
		
		//System.out.println(code);
		
		user user = new user();
		
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setGroupId(groupId);
		user.setUpdateBy(updateBy);
		user.setUpdateDt(DateService.getSTDYYYMMDDFormat(updateDt));
		user.setUpdateTime(updateTime);
		
		userDBService userDBService = new userDBService();
		userDBService.createuser(user);
		
		response.sendRedirect("saveSuccessUser.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("saveSuccessuser.jsp");
		//rd.forward(request, response);
		}
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updateuser&opr=close&homePage=userDashboard");
		String v = uri.getQuery();
		
	}
}
