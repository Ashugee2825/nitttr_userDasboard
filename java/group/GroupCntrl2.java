package group;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GroupCntrl2
 */
@WebServlet("/group/GroupCntrl2")
public class GroupCntrl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GroupCntrl2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String opr = request.getParameter("opr");
		
	/*
	 * if(opr.equals("Save")) { // add new record
	 * 
	 * String code=request.getParameter("code"); String
	 * value=request.getParameter("value");
	 * 
	 * System.out.println(code);
	 * 
	 * Group group = new Group(); group.setCode(code); group.setValue(value);
	 * 
	 * GroupDBService groupDBService = new GroupDBService();
	 * groupDBService.createGroup(group);
	 * 
	 * response.sendRedirect("saveSuccessGroup.jsp"); //RequestDispatcher rd =
	 * request.getRequestDispatcher("saveSuccessGroup.jsp"); //rd.forward(request,
	 * response); }
	 */
		
		if(opr.equals("view")) { // view record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Group group = new Group();
				GroupDBService groupDBService = new GroupDBService();
				group = groupDBService.getGroup(id);
				System.out.println(group.getCode());
				System.out.println(group.getValue());
				System.out.println(group.getId());
				request.setAttribute("group",group);
				//response.sendRedirect("viewGroup.jsp");
			    RequestDispatcher rd = request.getRequestDispatcher("viewGroup.jsp");
				rd.forward(request, response);
				
				
				
			}
		
	    }
		else if(opr.equals("edit")) { // view record
			{
				int id = Integer.parseInt(request.getParameter("id"));
						
				Group group = new Group();
				GroupDBService groupDBService = new GroupDBService();
				group = groupDBService.getGroup(id);
				
				request.setAttribute("Group",group);
								
			    RequestDispatcher rd = request.getRequestDispatcher("editGroup.jsp");
				rd.forward(request, response);
				
			}
		
	    }
		
		else if(opr.equals("update")) { // view record
			{
				int id = Integer.parseInt(request.getParameter("id"));
						
				String code=request.getParameter("code");
				String value=request.getParameter("value");
				
				//System.out.println(code);
				
				Group group = new Group();
				group.setId(id);
				group.setCode(code);
				group.setValue(value);
				
				GroupDBService groupDBService = new GroupDBService();
				groupDBService.updateGroup(group);
				System.out.println(code);
				System.out.println(value);
				System.out.println(id);
				
				response.sendRedirect("updateSuccessGroup.jsp");
								
			
				
			}
		
	    }
		
		else if(opr.equals("delete")) { // view record
			{
				int id = Integer.parseInt(request.getParameter("id"));	
				Group group = new Group();
				GroupDBService groupDBService = new GroupDBService();
				group.setId(id);
				groupDBService.deleteGroup(id);
				request.setAttribute("group",group);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("deleteGroup.jsp");
				rd.forward(request, response);
				
			}
		
	    }
		else if(opr.equals("print")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Group group = new Group();
			GroupDBService groupDBService = new GroupDBService();
			group = groupDBService.getGroup(id);
			System.out.println(group.getCode());
			System.out.println(group.getValue());
			System.out.println(group.getId());
			request.setAttribute("group",group);
			//response.sendRedirect("viewGroup.jsp");
		    RequestDispatcher rd = request.getRequestDispatcher("viewGroup.jsp");
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
		
		String code=request.getParameter("code");
		String value=request.getParameter("value");
		
		System.out.println(code);
		
		Group group = new Group();
		group.setCode(code);
		group.setValue(value);
		
		GroupDBService groupDBService = new GroupDBService();
		groupDBService.createGroup(group);
		
		response.sendRedirect("saveSuccessGroup.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("saveSuccessGroup.jsp");
		//rd.forward(request, response);
		}
		
	

}
}
