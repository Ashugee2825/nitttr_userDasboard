package group;

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
 * Servlet implementation class GroupCntrl
 */

//localhost:8080/loginDetail/login/GroupCntrl?page=GroupDashboard&opr=showAll&pageNo=1&limit=100

@WebServlet("/group/GroupCntrl")
public class GroupCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCntrl() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		GroupDBService GroupDBService =new GroupDBService();
		Group Group =new Group();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));		
		if(page.equals("GroupDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<Group> GroupList =new ArrayList<Group>();
				
				if(pageNo==0)
					GroupList = GroupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = GroupDBService.getGroupList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("GroupDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				Group.setDefaultValues();
				Group.displayValues();
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("addNewGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("updateGroup.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("updateNextGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				Group.setDefaultValues();
				Group.displayValues();
				request.setAttribute("Group",Group);
				
				ArrayList<Group> GroupList =new ArrayList<Group>();
				
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = GroupDBService.getGroupList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("saveShowNextGroup.jsp");
				rd.forward(request, response);
			}
			//End: modified by Dr PNH on 06-12-2022
			else if(opr.equals("delete")) //DELETE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group.setId(id);
				GroupDBService.deleteGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("deleteGroupSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("viewGroup.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(page.equals("addNewGroup")) 
		{
			if(opr.equals("save"))
			{
				Group.setRequestParam(request);
				Group.displayValues();
				GroupDBService.createGroup(Group);
				request.setAttribute("Group",Group);
				if(pageNo!=0) {//pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					homeURL="GroupCntrl?page=GroupDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				rd = request.getRequestDispatcher("addNewGroupSuccess.jsp");
				rd.forward(request, response);
			}
		}
		//Begin: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateNextGroup")) 
		{
			if(opr.equals("update"))
			{
				Group.setRequestParam(request);
				GroupDBService.updateGroup(Group);
				request.setAttribute("Group",Group);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("GroupCntrl?page=GroupDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextGroup")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				Group.setRequestParam(request);
				Group.displayValues();
				GroupDBService.createGroup(Group);
				request.setAttribute("Group",Group);
				if(pageNo!=0) {//pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					homeURL="GroupCntrl?page=GroupDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = GroupDBService.getGroupList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("saveShowNextGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				Group.setRequestParam(request);
				GroupDBService.updateGroup(Group);
				request.setAttribute("Group",Group);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					Group.setId(id);
					GroupDBService.deleteGroup(id);
					request.setAttribute("Group",Group);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("GroupCntrl?page=GroupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateGroup")) 
		{
			if(opr.equals("update"))
			{
				Group.setRequestParam(request);
				GroupDBService.updateGroup(Group);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("updateGroupSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewGroup")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("printGroup.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchGroup"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				Group.setRequestParam(request);
				Group.displayValues();
				request.getSession().setAttribute("GroupSearch",Group);
				request.setAttribute("opr","search");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList(Group);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=GroupDBService.getTotalPages(Group,limit);
					pageNo=1;
					GroupList = GroupDBService.getGroupList(Group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				Group.setRequestParam(request);
				Group.displayValues();
				request.getSession().setAttribute("GroupSearch",Group);
				request.setAttribute("opr","Group");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList(Group);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=GroupDBService.getTotalPages(Group,limit);
					pageNo=1;
					GroupList = GroupDBService.getGroupList(Group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroupDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				Group=(Group)request.getSession().getAttribute("GroupSearch");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList(Group);
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(Group,limit);
					GroupList = GroupDBService.getGroupList(Group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				Group=(Group)request.getSession().getAttribute("GroupSearch");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = GroupDBService.getGroupList(Group);
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(Group,limit);
					GroupList = GroupDBService.getGroupList(Group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				Group.setDefaultValues();
				Group.displayValues();
				request.getSession().setAttribute("GroupSearch",Group);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("updateGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group.setId(id);
				GroupDBService.deleteGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("deleteGroupSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				Group = GroupDBService.getGroup(id);
				request.setAttribute("Group",Group);
				rd = request.getRequestDispatcher("viewGroup.jsp");
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
		URI uri = new URI("page=updateGroup&opr=close&homePage=GroupDashboard");
		String v = uri.getQuery();
		
	}
}
