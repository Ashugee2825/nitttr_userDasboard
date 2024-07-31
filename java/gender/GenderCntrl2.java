
package gender;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GenderCntrl2
 */
@WebServlet("/gender/GenderCntrl2")
public class GenderCntrl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenderCntrl2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opr = request.getParameter("opr");
		if (opr.equals("Save")) {                    // ADD NEW Record

			String code = request.getParameter("code");
			String value = request.getParameter("value");

			System.out.println(code);
			System.out.println(value);

			Gender gender = new Gender();
			gender.setCode(code);
			gender.setValue(value);

			GenderDBService genderDBService = new GenderDBService();
			genderDBService.createGender(gender);

			response.sendRedirect("saveSuccessGender.jsp");
			/* response.sendRedirect("genderDashboard.jsp"); */

		} else if (opr.equals("view")) { // View record
			{
			int id = Integer.parseInt(request.getParameter("id"));
			Gender gender = new Gender();
			GenderDBService genderDBService = new GenderDBService();
			gender = genderDBService.getgender(id);

			request.setAttribute("gender", gender);
		    	//response.sendRedirect("viewGender.jsp");
			
			RequestDispatcher rd = request.getRequestDispatcher("viewGender.jsp");
			rd.forward(request, response);

			// gender.displayValues();
			// response.sendRedirect("viewGender.jsp");
		}
	}		

		else if (opr.equals("edit")) {            // Edit record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				
				Gender gender = new Gender();
				GenderDBService genderDBService = new GenderDBService();
				gender = genderDBService.getgender(id);

				request.setAttribute("Gender", gender);
				
				RequestDispatcher rd = request.getRequestDispatcher("editGender.jsp");
				rd.forward(request, response);

				// String id=request.getParameter("id");
				// response.sendRedirect("editGender.jsp");
			}
		} 
		else if (opr.equals("Update")) { // UPDATE record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				
				String code=request.getParameter("code");
				String value=request.getParameter("value");
				System.out.println(code);
				
				Gender gender = new Gender();
				gender.setId(id);
				gender.setCode(code);
				gender.setValue(value);
                
				GenderDBService genderDBService = new GenderDBService();
				genderDBService.updateGender(gender);
				System.out.println(code);
				System.out.println(value);
				System.out.println(id);
				
				
				// String id= request.getParameter("id");

				response.sendRedirect("updateGender.jsp");
			}
		}
		
		
		else if (opr.equals("delete")) { // DELETE record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				Gender gender = new Gender();
				gender.setId(id);
				GenderDBService genderDBService = new GenderDBService();
				genderDBService.deletegender(id);
                request.setAttribute("gender", gender);
                
				RequestDispatcher rd = request.getRequestDispatcher("deleteGender.jsp");
				rd.forward(request, response);
				// String id= request.getParameter("id");

				// response.sendRedirect("deleteGender.jsp");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opr = request.getParameter("opr");
		if (opr.equals("Save")) {

			String code = request.getParameter("code");
			String value = request.getParameter("value");

			System.out.println(code);
			System.out.println(value);

			Gender gender = new Gender();
			gender.setCode(code);
			gender.setValue(value);

			GenderDBService genderDBService = new GenderDBService();
			genderDBService.createGender(gender);

			response.sendRedirect("saveSuccessGender.jsp");
	}
	}
}
	
	