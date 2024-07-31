package country;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import country.Country;
import country.CountryDBService;
import country.Country;
import country.CountryDBService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CountryCntrl
 */

//localhost:8080/loginDetail/login/CountryCntrl?page=CountryDashboard&opr=showAll&pageNo=1&limit=100

@WebServlet("/country/CountryCntrl")
public class CountryCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryCntrl() {
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
		 * Country country = new Country(); country.setCode(code); country.setValue(value);
		 * 
		 * CountryDBService countryDBService = new CountryDBService();
		 * countryDBService.createCountry(country);
		 * 
		 * response.sendRedirect("saveSuccessCountry.jsp"); //RequestDispatcher rd =
		 * request.getRequestDispatcher("saveSuccessCountry.jsp"); //rd.forward(request,
		 * response); }
		 */
			
			if(opr.equals("view")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
					Country country = new Country();
					CountryDBService countryDBService = new CountryDBService();
					country = countryDBService.getCountry(id);
					System.out.println(country.getCode());
					System.out.println(country.getValue());
					System.out.println(country.getId());
					request.setAttribute("country",country);
					//response.sendRedirect("viewCountry.jsp");
				    RequestDispatcher rd = request.getRequestDispatcher("viewCountry.jsp");
					rd.forward(request, response);
					
					
					
				}
			
		    }
			else if(opr.equals("edit")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					Country country = new Country();
					CountryDBService countryDBService = new CountryDBService();
					country = countryDBService.getCountry(id);
					
					request.setAttribute("Country",country);
									
				    RequestDispatcher rd = request.getRequestDispatcher("editCountry.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			
			else if(opr.equals("update")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
							
					String code=request.getParameter("code");
					String value=request.getParameter("value");
					
					//System.out.println(code);
					
					Country country = new Country();
					country.setId(id);
					country.setCode(code);
					country.setValue(value);
					
					CountryDBService countryDBService = new CountryDBService();
					countryDBService.updateCountry(country);
					System.out.println(code);
					System.out.println(value);
					System.out.println(id);
					
					response.sendRedirect("updateSuccessCountry.jsp");
									
				
					
				}
			
		    }
			
			else if(opr.equals("delete")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));	
					Country country = new Country();
					CountryDBService countryDBService = new CountryDBService();
					country.setId(id);
					countryDBService.deleteCountry(id);
					request.setAttribute("country",country);
					
					
					RequestDispatcher rd = request.getRequestDispatcher("deleteCountry.jsp");
					rd.forward(request, response);
					
				}
			
		    }
			else if(opr.equals("print")) { // view record
				{
					int id = Integer.parseInt(request.getParameter("id"));
					Country country = new Country();
					CountryDBService countryDBService = new CountryDBService();
					country = countryDBService.getCountry(id);
					System.out.println(country.getCode());
					System.out.println(country.getValue());
					System.out.println(country.getId());
					request.setAttribute("country",country);
					//response.sendRedirect("viewCountry.jsp");
				    RequestDispatcher rd = request.getRequestDispatcher("viewCountry.jsp");
					rd.forward(request, response);
					
				}
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
		
		Country country = new Country();
		country.setCode(code);
		country.setValue(value);
		
		CountryDBService countryDBService = new CountryDBService();
		countryDBService.createCountry(country);
		
		response.sendRedirect("saveSuccessCountry.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("saveSuccessCountry.jsp");
		//rd.forward(request, response);
		}
		
	

}
	
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updateCountry&opr=close&homePage=CountryDashboard");
		String v = uri.getQuery();
		
	}
}
