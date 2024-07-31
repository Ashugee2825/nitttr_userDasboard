package gender;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class genderCntrl
 */

//localhost:8080/Detail//genderCntrl?page=genderDashboard&opr=showAll&pageNo=1&limit=100

@WebServlet("/gender/GenderCntrl")
public class GenderCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   // public GenderCntrl() {
       // super();
        // TODO Auto-generated constructor stubS
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		GenderDBService genderDBService =new GenderDBService();
		Gender gender =new Gender();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));		
		if(page.equals("genderDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="genderCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				
				if(pageNo==0)
					genderList = genderDBService.getgenderList();
				else { //pagination
					int totalPages= genderDBService.getTotalPvalues(limit);
					genderList = genderDBService.getgenderList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("genderDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				gender.setDefaultValues();
				gender.displayValues();
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("addNewgender.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("updategender.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("updateNextgender.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				gender.setDefaultValues();
				gender.displayValues();
				request.setAttribute("gender",gender);
				
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				
				if(pageNo==0)
				genderList = genderDBService.getgenderList();
				else { //pagination
					int totalPages= genderDBService.getTotalPvalues(limit);
					genderList = genderDBService.getgenderList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("saveShowNextgender.jsp");
				rd.forward(request, response);
			}
			//End: modified by Dr PNH on 06-12-2022
			else if(opr.equals("delete")) //DELETE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender.setId(id);
				genderDBService.deletegender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("deletegenderSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("viewgender.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(page.equals("addNewgender")) 
		{
			if(opr.equals("save"))
			{
				gender.setRequestParam(request);
				gender.displayValues();
				genderDBService.updateGender(gender);
				request.setAttribute("gender",gender);
				if(pageNo!=0) {//pagination
					int totalPages= genderDBService.getTotalPvalues(limit);
					homeURL="genderCntrl?page=genderDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				rd = request.getRequestDispatcher("addNewgenderSuccess.jsp");
				rd.forward(request, response);
			}
		}
		//Begin: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateNextgender")) 
		{
			if(opr.equals("update"))
			{
				gender.setRequestParam(request);
				genderDBService.updateGender(gender);
				request.setAttribute("gender",gender);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("genderCntrl?page=genderDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextgender")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				gender.setRequestParam(request);
				gender.displayValues();
				genderDBService.updateGender(gender);
				request.setAttribute("gender",gender);
				if(pageNo!=0) {//pagination
					int totalPages= genderDBService.getTotalPvalues(limit);
					homeURL="genderCntrl?page=genderDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				if(pageNo==0)
				genderList = genderDBService.getgenderList();
				else { //pagination
					int totalPages= genderDBService.getTotalPvalues(limit);
					genderList = genderDBService.getgenderList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("saveShowNextgender.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				gender.setRequestParam(request);
				genderDBService.updateGender(gender);
				request.setAttribute("gender",gender);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					gender.setId(id);
					genderDBService.deletegender(id);
					request.setAttribute("gender",gender);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("genderCntrl?page=genderDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updategender")) 
		{
			if(opr.equals("update"))
			{
				gender.setRequestParam(request);
				genderDBService.updateGender(gender);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("updategenderSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewgender")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("printgender.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchgender"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="genderCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				gender.setRequestParam(request);
				gender.displayValues();
				request.getSession().setAttribute("genderSearch",gender);
				request.setAttribute("opr","search");
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				if(pageNo==0)
				genderList = genderDBService.getgenderList(gender);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=genderDBService.getTotalPvalues(gender,limit);
					pageNo=1;
					genderList = genderDBService.getgenderList(gender,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("searchgender.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				gender.setRequestParam(request);
				gender.displayValues();
				request.getSession().setAttribute("genderSearch",gender);
				request.setAttribute("opr","gender");
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				if(pageNo==0)
				genderList = genderDBService.getgenderList(gender);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=genderDBService.getTotalPvalues(gender,limit);
					pageNo=1;
					genderList = genderDBService.getgenderList(gender,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("searchgenderDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				gender=(Gender)request.getSession().getAttribute("genderSearch");
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				if(pageNo==0)
				genderList = genderDBService.getgenderList(gender);
				else { //pagination
					int totalPages= genderDBService.getTotalPvalues(gender,limit);
					genderList = genderDBService.getgenderList(gender,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("searchgender.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				gender=(Gender)request.getSession().getAttribute("genderSearch");
				ArrayList<Gender> genderList =new ArrayList<Gender>();
				if(pageNo==0)
				genderList = genderDBService.getgenderList(gender);
				else { //pagination
					int totalPages= genderDBService.getTotalPvalues(gender,limit);
					genderList = genderDBService.getgenderList(gender,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("genderList",genderList);
				rd = request.getRequestDispatcher("searchgender.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				gender.setDefaultValues();
				gender.displayValues();
				request.getSession().setAttribute("genderSearch",gender);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchgender.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("updategender.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				gender.setId(id);
				genderDBService.deletegender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("deletegenderSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				gender = genderDBService.getgender(id);
				request.setAttribute("gender",gender);
				rd = request.getRequestDispatcher("viewgender.jsp");
				rd.forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updategender&opr=close&homePage=genderDashboard");
		String v = uri.getQuery();
		
	}
}
