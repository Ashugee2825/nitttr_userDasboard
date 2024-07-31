package group;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class GroupCntrl32
 */
@WebServlet("/group/GroupCntrl33")
public class GroupCntrl3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GroupCntrl3() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		GroupDBService groupDBService =new GroupDBService();
		Group group =new Group();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));
		
		if(page.equals("groupDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl3?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<Group> GroupList =new ArrayList<Group>();
				
				if(pageNo==0)
					GroupList = groupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = groupDBService.getGroupList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("groupDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				group.setDefaultValues();
				group.displayValues();
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("addGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("updateGroup.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("updateNextGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				group.setDefaultValues();
				group.displayValues();
				request.setAttribute("Group",group);
				
				ArrayList<Group> GroupList =new ArrayList<Group>();
				
				if(pageNo==0)
				GroupList = groupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = groupDBService.getGroupList(pageNo,limit);
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
				group.setId(id);
				groupDBService.deleteGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("deleteGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("viewGroup.jsp");
				rd.forward(request, response);
				
				
				
				//Test4 for Delete Record
				
				group.displayValues();
			}
			
		}
		else if(page.equals("addNewGroup")) 
		{
			if(opr.equals("save"))
			{
				group.setRequestParam(request);
				group.displayValues();
				groupDBService.createGroup(group);
				request.setAttribute("Group",group);
				if(pageNo!=0) {//pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					homeURL="GroupCntrl3?page=groupDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
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
				group.setRequestParam(request);
				groupDBService.updateGroup(group);
				request.setAttribute("Group",group);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("GroupCntrl3?page=groupDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextGroup")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				group.setRequestParam(request);
				group.displayValues();
				groupDBService.createGroup(group);
				request.setAttribute("Group",group);
				if(pageNo!=0) {//pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					homeURL="GroupCntrl3?page=groupDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = groupDBService.getGroupList();
				else { //pagination
					int totalPages= GroupDBService.getTotalPages(limit);
					GroupList = groupDBService.getGroupList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("saveShowNextGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				group.setRequestParam(request);
				groupDBService.updateGroup(group);
				request.setAttribute("Group",group);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					group.setId(id);
					groupDBService.deleteGroup(id);
					request.setAttribute("Group",group);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("GroupCntrl3?page=groupDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateGroup")) 
		{
			if(opr.equals("update"))
			{
				group.setRequestParam(request);
				groupDBService.updateGroup(group);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("updateGroupSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewGroup")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("printGroup.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchGroup"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="GroupCntrl3?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				group.setRequestParam(request);
				group.displayValues();
				request.getSession().setAttribute("GroupSearch",group);
				request.setAttribute("opr","search");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = groupDBService.getGroupList(group);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=groupDBService.getTotalPages(group,limit);
					pageNo=1;
					GroupList = groupDBService.getGroupList(group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				group.setRequestParam(request);
				group.displayValues();
				request.getSession().setAttribute("GroupSearch",group);
				request.setAttribute("opr","Group");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = groupDBService.getGroupList(group);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=groupDBService.getTotalPages(group,limit);
					pageNo=1;
					GroupList = groupDBService.getGroupList(group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroupDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				group=(Group)request.getSession().getAttribute("GroupSearch");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = groupDBService.getGroupList(group);
				else { //pagination
					int totalPages= groupDBService.getTotalPages(group,limit);
					GroupList = groupDBService.getGroupList(group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				group=(Group)request.getSession().getAttribute("GroupSearch");
				ArrayList<Group> GroupList =new ArrayList<Group>();
				if(pageNo==0)
				GroupList = groupDBService.getGroupList(group);
				else { //pagination
					int totalPages= groupDBService.getTotalPages(group,limit);
					GroupList = groupDBService.getGroupList(group,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("GroupList",GroupList);
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				group.setDefaultValues();
				group.displayValues();
				request.getSession().setAttribute("GroupSearch",group);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchGroup.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("updateGroup.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				group.setId(id);
				groupDBService.deleteGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("deleteGroupSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				group = groupDBService.getGroup(id);
				request.setAttribute("Group",group);
				rd = request.getRequestDispatcher("viewGroup.jsp");
				rd.forward(request, response);
			}
		}
	}}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
