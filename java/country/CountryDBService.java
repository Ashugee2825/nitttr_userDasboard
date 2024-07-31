package country;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class CountryDBService {
	private static ArrayList<Country> countryList;
	Connection con;
	
	
	public CountryDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
public int getTotalPvalues(int limit)
	{
		String query="select count(*) from dd_Country";
	    int totalRecords=0;
	    int totalPvalues=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPvalues=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPvalues+=1;
		}
		return totalPvalues;
	}
	
	//pagination
	public int getTotalPvalues(Country country,int limit)
	{
		String query=getDynamicQuery2(country);
		int totalRecords=0;
	    int totalPvalues=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPvalues=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPvalues+=1;
		}
		return totalPvalues;
	}
	
	
	private String getDynamicQuery2(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCountryId(Country country)
	{
		int id=0;
		String query="select id from dd_Country";
String whereClause = " where "+ "code=? and value=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, country.getCode());
pstmt.setString(2, country.getValue());
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	       	id = rs.getInt("id");
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return id;
	}
	public void createCountry(Country country)
	{
		
String query="INSERT INTO dd_Country(code,value) VALUES(?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, country.getCode());
pstmt.setString(2, country.getValue());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getCountryId(country);
		country.setId(id);
	}
	public void updateCountry(Country country)
	{
		
String query="update dd_Country set "+"code=?,value=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, country.getCode());
pstmt.setString(2, country.getValue());
pstmt.setInt(3, country.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String code,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+code+"'";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	    	value=rs.getString("value");
	    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	    return value;
	}
	
	public Country getCountry(int id)
	{
		Country country =new Country();
		String query="select * from dd_Country where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
country.setId(rs.getInt("id")==0?0:rs.getInt("id"));
country.setCode(rs.getString("code")==null?"":rs.getString("code"));
country.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return country;
	}
	
	
	public ArrayList<Country> getCountryList()
	{
		ArrayList<Country> countryList =new ArrayList<Country>();
		String query="select * from dd_Country";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Country country =new Country();
country.setId(rs.getInt("id")==0?0:rs.getInt("id"));
country.setCode(rs.getString("code")==null?"":rs.getString("code"));
country.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	countryList.add(country);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return countryList;
	}
	
	public ArrayList<Country> getCountryList(int pvalueNo,int limit)
	{
		ArrayList<Country> countryList =new ArrayList<Country>();
String query="select * from dd_Country limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Country country =new Country();
country.setId(rs.getInt("id")==0?0:rs.getInt("id"));
country.setCode(rs.getString("code")==null?"":rs.getString("code"));
country.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	countryList.add(country);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return countryList;
	}
	
	public void deleteCountry(int id) {
		
			String query="delete from dd_Country where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(Country country)
{
String query="select * from dd_Country ";
String whereClause="";
whereClause+=(country.getValue()==null?"":" value="+country.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(Country country)
{
String query="select count(*) from dd_Country ";
String whereClause="";
whereClause+=(country.getValue()==null?"":" value="+country.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<Country> getCountryList(Country country)
{
ArrayList<Country> countryList =new ArrayList<Country>();
String query=getDynamicQuery(country);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Country country1 =new Country();
country1.setId(rs.getInt("id")==0?0:rs.getInt("id"));
country1.setCode(rs.getString("code")==null?"":rs.getString("code"));
country1.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	countryList.add(country1);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return countryList;
}
	
public ArrayList<Country> getCountryList(Country country,int pvalueNo,int limit)
{
ArrayList<Country> countryList =new ArrayList<Country>();
String query=getDynamicQuery(country);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Country country1 =new Country();
country1.setId(rs.getInt("id")==0?0:rs.getInt("id"));
country1.setCode(rs.getString("code")==null?"":rs.getString("code"));
country1.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	countryList.add(country1);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return countryList;
}
public void testCreate() {
	CountryDBService countryDBService =new CountryDBService();
	
	Country country = new Country(); 
	country.setDefaultValues();
	countryDBService.createCountry(country);
	
	//Test2 for Read All Records
	ArrayList<Country> countryList = countryDBService.getCountryList();
	CountryService countryService =new CountryService();
	countryService.displayList(countryList);
	countryDBService.closeConnection();
	
}

public void testReadAll() {
	CountryDBService countryDBService =new CountryDBService();
	
	Country country = new Country(); 
	
	
	//Test2 for Read All Records
	ArrayList<Country> countryList = countryDBService.getCountryList();
	CountryService countryService =new CountryService();
	countryService.displayList(countryList);
	countryDBService.closeConnection();
}

public void testUpdate() {
	CountryDBService countryDBService =new CountryDBService();
	
	Country country = new Country(); 
	
	
	country.setId(3);
	country.setCode("PK");
	country.setValue("PAK");
	
	countryDBService.updateCountry(country);
	
	
	
	//Test3 for Read All Records
	ArrayList<Country> countryList = countryDBService.getCountryList();
	CountryService CountryService =new CountryService();
	CountryService.displayList(countryList);
	countryDBService.closeConnection();
}

public void testDelete() {
	CountryDBService countryDBService =new CountryDBService();
	
	Country country = new Country();
	
	//Test4 for Delete Record
	countryDBService.deleteCountry(9);
	
	
	//Test4 for Read All Records
	ArrayList<Country> countryList = countryDBService.getCountryList();
	CountryService countryService =new CountryService();
	countryService.displayList(countryList);
	countryDBService.closeConnection();
}

public void testSearch() {
	CountryDBService countryDBService =new CountryDBService();
	
	Country country = new Country();
	
	//Test4 for Delete Record
	country=countryDBService.getCountry(3);
	country.displayValues();
	
	
	//Test4 for Read All Records
	//ArrayList<Country> CountryList = CountryDBService.getCountryList();
	//CountryService CountryService =new CountryService();
	//CountryService.displayList(CountryList);
	
}
	public static void main(String[] args) {
		
		CountryDBService countryDBService =new CountryDBService();
		
		countryDBService.testCreate();
		//countryDBService.testReadAll();
		//countryDBService.testUpdate();
		//countryDBService.testDelete();
		//countryDBService.testSearch();
		
		
		/*
		 //Test-1 : Create Country
		  
		Country Country = new Country(); 
		Country.setDefaultValues();
		
			//test1 for Create Record
		//CountryDBService.createCountry(Country);
		  
			//Test2 for Read All Records 
		//ArrayList<Country> CountryList = CountryDBService.getCountryList();
		//CountryService CountryService =new CountryService();
		//CountryService.displayList(CountryList);
		
		
		
			//Test3 for Update Record
		Country.setId(5);
		Country.setCode("Admin123");
		Country.setValue("admin12");
		CountryDBService.updateCountry(Country);
		//Country=CountryDBService.getCountry(5);
		Country.displayValues();
		
		//Test4 for Delete Record
		CountryDBService.deleteCountry(4);
		ArrayList<Country> CountryList = CountryDBService.getCountryList();
		CountryService CountryService =new CountryService();
		CountryService.displayList(CountryList);
		
		
		//Test5 for Read one Record
		Country=CountryDBService.getCountry(5);
		Country.displayValues();
		*/
	}

	public ArrayList<Country> getCountryList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Country> getCountryList1() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int getTotalPages(Country country, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	

}

