 package gender;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class GenderDBService {
	Connection con;
	
	
	public GenderDBService()
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
		String query="select count(*) from dd_gender";
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
	public int getTotalPvalues(Gender gender,int limit)
	{
		String query=getDynamicQuery2(gender);
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
	
	
	private String getDynamicQuery2(Gender gender) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getgenderId(Gender gender)
	{
		int id=0;
		String query="select id from dd_gender";
String whereClause = " where "+ "code=? and value=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, gender.getCode());
pstmt.setString(2, gender.getValue());
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
	public void createGender(Gender gender)
	{
	// insert   	
String query="INSERT INTO dd_gender(code,value) VALUES(?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, gender.getCode());
pstmt.setString(2, gender.getValue());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getgenderId(gender);
		gender.setId(id);
	}
	// Update  Gender
	public void updateGender(Gender gender)
	{
		
String query="update dd_gender set "+"code=?,value=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, gender.getCode());
pstmt.setString(2, gender.getValue());
pstmt.setInt(3, gender.getId());
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
	
	public Gender getgender(int id)
	{
		Gender gender =new Gender();
		String query="select * from dd_gender where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
gender.setId(rs.getInt("id")==0?0:rs.getInt("id"));
gender.setCode(rs.getString("code")==null?"":rs.getString("code"));
gender.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return gender;
	}
	
	
	public ArrayList<Gender> getgenderList()
	{
		ArrayList<Gender> genderList =new ArrayList<Gender>();
		String query="select * from dd_gender";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Gender gender =new Gender();
gender.setId(rs.getInt("id")==0?0:rs.getInt("id"));
gender.setCode(rs.getString("code")==null?"":rs.getString("code"));
gender.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	genderList.add(gender);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return genderList;
	}
	
	public ArrayList<Gender> getgenderList(int pvalueNo,int limit)
	{
		ArrayList<Gender> genderList =new ArrayList<Gender>();
String query="select * from dd_gender limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Gender gender =new Gender();
gender.setId(rs.getInt("id")==0?0:rs.getInt("id"));
gender.setCode(rs.getString("code")==null?"":rs.getString("code"));
gender.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	genderList.add(gender);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return genderList;
	}
	
	public void deletegender(int id) {
		
			String query="delete from dd_gender where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(Gender gender)
{
String query="select * from dd_gender ";
String whereClause="";
whereClause+=(gender.getValue()==null?"":" ="+gender.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(Gender gender)
{
String query="select count(*) from dd_gender ";
String whereClause="";
whereClause+=(gender.getValue()==null?"":" value="+gender.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<Gender> getgenderList(Gender gender)
{
ArrayList<Gender> genderList =new ArrayList<Gender>();
String query=getDynamicQuery(gender);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Gender gender2 =new Gender();
gender2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
gender2.setCode(rs.getString("code")==null?"":rs.getString("code"));
gender2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	genderList.add(gender2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return genderList;
}
	
public ArrayList<Gender> getgenderList(Gender gender,int pvalueNo,int limit)
{
ArrayList<Gender> genderList =new ArrayList<Gender>();
String query=getDynamicQuery(gender);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Gender gender2 =new Gender();
gender2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
gender2.setCode(rs.getString("code")==null?"":rs.getString("code"));
gender2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	genderList.add(gender2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return genderList;
}	
// test cases are test to test
public void testCreate() {
	
	GenderDBService genderDBService =new GenderDBService();
  
			Gender gender = new Gender();
			gender.setDefaultValues();
		    genderDBService.createGender(gender); // Test for create Record
			  
			//Test2 for read All records
			  ArrayList<Gender> genderList = genderDBService.getgenderList();
			  GenderService genderService =new GenderService();
			  genderService.displayList(genderList);
			  genderDBService.closeConnection();
	
}

public void testReadAll() {
	GenderDBService genderDBService =new GenderDBService();
	  
	Gender gender = new Gender();
		
	//Test2 for read All records
	  ArrayList<Gender> genderList = genderDBService.getgenderList();
	  GenderService genderService =new GenderService();
	  genderService.displayList(genderList);
	  genderDBService.closeConnection();
}
   
public void testUpdate()
{
	 GenderDBService genderDBService =new GenderDBService();
	 
	Gender gender = new Gender();
	gender.setId(3);
	gender.setCode("New Code");
	gender.setValue("New Value");
	
	genderDBService.updateGender(gender);
	
	//Test3 for read all records
	ArrayList<Gender> genderList = genderDBService.getgenderList();
	GenderService genderService =new GenderService();
	genderService.displayList(genderList);
	genderDBService.closeConnection();
	
}

public void testDelete() {
	 GenderDBService genderDBService =new GenderDBService();
     Gender gender = new Gender();
     gender.setDefaultValues();
	 
	//Test4 for Delete  Record
	 genderDBService.deletegender(4);
	 gender.displayValues();
	 
	//Test4 for read all records
	// ArrayList<Gender> genderList = genderDBService.getGenderList();
	// GenderService genderService =new GenderService();
	// genderService.displayList(genderList);
	// genderDBService.closeConnection();
}

public void testSearch() {
	GenderDBService genderDBService =new GenderDBService();
	 
	 Gender gender = new Gender();
	 gender=genderDBService.getgender(4);
     gender.displayValues();
}



	public static void main(String[] args) {
	
		GenderDBService genderDBService =new GenderDBService();
		
		genderDBService.testCreate();
		genderDBService.testReadAll();
		genderDBService.testUpdate();
		genderDBService.testDelete();
		genderDBService.testSearch();
		
		
		
		
		 
		
		//Test for Update Record
		//genderDBService.updategender(gender);
	
		// test for Delete Record    
		//genderDBService.deletegender(4);
	
	
	//Test-1 : Create gender
	  
			Gender gender = new Gender(); 
			gender.setDefaultValues();
			
				//test1 for Create Record
			//genderDBService.createGender(gender);
			  
				//Test2 for Read All Records 
			//ArrayList<Gender> genderList = genderDBService.getGenderList();
			//GenderService genderService =new GenderService();
			//genderService.displayList(genderList);
			
			
		//Test3 for Update Record
		/*
		 * gender.setId(5); gender.setCode("Admin123"); gender.setValue("admin12")
		 * genderDBService.updateGender(gender); 
		 * //gender=genderDBService.getGender(5);
		 * gender.displayValues();
		 */
			//gender=genderDBService.getGender(3);
			//gender.displayValues();
			
	//Test4 for Delete Record
			//genderDBService.deleteGender(4);
			//ArrayList<Gender> genderList = genderDBService.getGenderList();
			//GenderService genderService =new GenderService();
		    //genderService.displayList(genderList);
			
			
	//Test5 for Read one Record
			//gender=genderDBService.getgender(4);
			//gender.displayValues();
	
	}

	
	public ArrayList<Gender> getgenderList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Gender> getgenderList1() {
		// TODO Auto-generated method stub
		return null;
	}
	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages(Gender gender, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}


