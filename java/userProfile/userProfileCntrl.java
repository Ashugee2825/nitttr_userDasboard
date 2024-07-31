package userProfile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import userProfile.userProfile;
import userProfile.userProfileDBService;
import userProfile.userProfile;
import userProfile.userProfileDBService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class userProfileCntrl
 */

//localhost:8080/loginDetail/login/userProfileCntrl?page=userProfileDashboard&opr=showAll&pageNo=1&limit=100

@WebServlet("/userProfile/userProfileCntrl")
public class userProfileCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userProfileCntrl() {
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
		 * userProfile userProfile = new userProfile(); userProfile.setCode(code); userProfile.setValue(value);
		 * 
		 * userProfileDBService userProfileDBService = new userProfileDBService();
		 * userProfileDBService.createuserProfile(userProfile);
		 * 
		 * response.sendRedirect("saveSuccessuserProfile.jsp"); //RequestDispatcher rd =
		 * request.getRequestDispatcher("saveSuccessuserProfile.jsp"); //rd.forward(request,
		 * response); }
		 */
			
			if(opr.equals("view")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
					userProfile userProfile = new userProfile();
					userProfileDBService userProfileDBService = new userProfileDBService();
					userProfile = userProfileDBService.getuserProfile(id);
					
					
					System.out.println(userProfile.getId());
					System.out.println(userProfile.getUserId());
					System.out.println(userProfile.getUserName());
					System.out.println(userProfile.getMobileNumber());
					System.out.println(userProfile.getEmail());
					System.out.println(userProfile.getDistrict());
					System.out.println(userProfile.getState());
					System.out.println(userProfile.getCountry());
					System.out.println(userProfile.getGender());
					System.out.println(userProfile.getPin());
					System.out.println(userProfile.getDob());
					System.out.println(userProfile.getLocalAddress());
					System.out.println(userProfile.getPermanentAddress());
					System.out.println(userProfile.getUpdateBy());
					System.out.println(userProfile.getUpdateDt());
					System.out.println(userProfile.getUpdateTime());

					request.setAttribute("userProfile",userProfile);
					//response.sendRedirect("viewuserProfile.jsp");
				    RequestDispatcher rd = request.getRequestDispatcher("viewUserProfile.jsp");
					rd.forward(request, response);
					
					
					
				}
			
		    }
			else if(opr.equals("edit")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					userProfile userProfile = new userProfile();
					userProfileDBService userProfileDBService = new userProfileDBService();
					userProfile = userProfileDBService.getuserProfile(id);
					
					request.setAttribute("userProfile",userProfile);
									
				    RequestDispatcher rd = request.getRequestDispatcher("editUserProfile.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			
			else if(opr.equals("update")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					String userId=request.getParameter("userId");
					String userName=request.getParameter("userName");
					String mobileNumber=request.getParameter("mobileNumber");
					String email=request.getParameter("email");
					String district=request.getParameter("district");
					String state=request.getParameter("state");
					String country=request.getParameter("country");
					String gender=request.getParameter("gender");
					String pin=request.getParameter("pin");
					String dob=request.getParameter("dob");
					String localAddress=request.getParameter("localAddress");
					String permanentAddress=request.getParameter("permanentAddress");
					String updateBy=request.getParameter("updateBy");
					String updateDt=request.getParameter("updateDt");
					String updateTime=request.getParameter("updateTime");
					
					//System.out.println(code);
					
					userProfile userProfile = new userProfile();
					
					userProfile.setId(id);
					userProfile.setUserId(userId);
					userProfile.setUserName(userName);
					userProfile.setMobileNumber(mobileNumber);
					userProfile.setEmail(email);
					userProfile.setDistrict(district);
					userProfile.setState(state);
					userProfile.setCountry(country);
					userProfile.setGender(gender);
					userProfile.setPin(pin);
					userProfile.setDob(DateService.getSTDYYYMMDDFormat(dob));
					userProfile.setLocalAddress(localAddress);
					userProfile.setPermanentAddress(permanentAddress);
					userProfile.setUpdateBy(updateBy);
					userProfile.setUpdateDt(DateService.getSTDYYYMMDDFormat(updateDt));
					userProfile.setUpdateTime(updateTime);
					
					userProfileDBService userProfileDBService = new userProfileDBService();
					userProfileDBService.updateuserProfile(userProfile);
					//System.out.println(code);
					//System.out.println(value);
					//System.out.println(id);
					
					response.sendRedirect("updateSuccessUserProfile.jsp");
									
				
					
				}
			
		    }
			
			else if(opr.equals("delete")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));	
					userProfile userProfile = new userProfile();
					userProfileDBService userProfileDBService = new userProfileDBService();
					userProfile.setId(id);
					userProfileDBService.deleteuserProfile(id);
					request.setAttribute("userProfile",userProfile);
					
					
					RequestDispatcher rd = request.getRequestDispatcher("deleteUserProfile.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			else if(opr.equals("print")) {
				int id = Integer.parseInt(request.getParameter("id"));
				userProfile userProfile = new userProfile();
				userProfileDBService userProfileDBService = new userProfileDBService();
				userProfile = userProfileDBService.getuserProfile(id);
				
				System.out.println(userProfile.getId());
				System.out.println(userProfile.getUserId());
				System.out.println(userProfile.getUserName());
				System.out.println(userProfile.getMobileNumber());
				System.out.println(userProfile.getEmail());
				System.out.println(userProfile.getDistrict());
				System.out.println(userProfile.getState());
				System.out.println(userProfile.getCountry());
				System.out.println(userProfile.getGender());
				System.out.println(userProfile.getPin());
				System.out.println(userProfile.getDob());
				System.out.println(userProfile.getLocalAddress());
				System.out.println(userProfile.getPermanentAddress());
				System.out.println(userProfile.getUpdateBy());
				System.out.println(userProfile.getUpdateDt());
				System.out.println(userProfile.getUpdateTime());
				request.setAttribute("userProfile",userProfile);
				//response.sendRedirect("viewuserProfile.jsp");
			    RequestDispatcher rd = request.getRequestDispatcher("viewUserProfile.jsp");
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
		String userName=request.getParameter("userName");
		String mobileNumber=request.getParameter("mobileNumber");
		String email=request.getParameter("email");
		String district=request.getParameter("district");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String gender=request.getParameter("gender");
		String pin=request.getParameter("pin");
		String dob=request.getParameter("dob");
		String localAddress=request.getParameter("localAddress");
		String permanentAddress=request.getParameter("permanentAddress");
		String updateBy=request.getParameter("updateBy");
		String updateDt=request.getParameter("updateDt");
		String updateTime=request.getParameter("updateTime");
		
		//System.out.println(code);
		
		userProfile userProfile = new userProfile();
		
		userProfile.setUserId(userId);
		userProfile.setUserName(userName);
		userProfile.setMobileNumber(mobileNumber);
		userProfile.setEmail(email);
		userProfile.setDistrict(district);
		userProfile.setState(state);
		userProfile.setCountry(country);
		userProfile.setGender(gender);
		userProfile.setPin(pin);
		userProfile.setDob(DateService.getSTDYYYMMDDFormat(dob));
		userProfile.setLocalAddress(localAddress);
		userProfile.setPermanentAddress(permanentAddress);
		userProfile.setUpdateBy(updateBy);
		userProfile.setUpdateDt(DateService.getSTDYYYMMDDFormat(updateDt));
		userProfile.setUpdateTime(updateTime);
		
		userProfileDBService userProfileDBService = new userProfileDBService();
		userProfileDBService.createuserProfile(userProfile);
		
		response.sendRedirect("saveSuccessUserProfile.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("saveSuccessuserProfile.jsp");
		//rd.forward(request, response);
		}
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updateuserProfile&opr=close&homePage=userProfileDashboard");
		String v = uri.getQuery();
		
	}
}
