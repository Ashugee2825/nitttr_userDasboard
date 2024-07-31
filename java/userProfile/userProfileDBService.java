package userProfile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class userProfileDBService {
	private static ArrayList<userProfile> userProfileList;
	Connection con;
	
	
	public userProfileDBService()
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
		String query="select count(*) from userprofile";
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
	public int getTotalPvalues(userProfile userProfile,int limit)
	{
		String query=getDynamicQuery2(userProfile);
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
	
	
	private String getDynamicQuery2(userProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getuserProfileId(userProfile userProfile)
	{
		int id=0;
		String query="select id from userprofile";
String whereClause = " where "+ "userId=?,userName=?, mobileNumber=?, email=?, district=?, state=?, country=?, gender=?, pin=?, dob=?, localAddress=?, permanentAddress=?, updateBy=?, updateDt=? and updateTime=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, userProfile.getUserId());
pstmt.setString(2, userProfile.getUserName());
pstmt.setString(3, userProfile.getMobileNumber());
pstmt.setString(4, userProfile.getEmail());
pstmt.setString(5, userProfile.getDistrict());
pstmt.setString(6, userProfile.getState());
pstmt.setString(7, userProfile.getCountry());
pstmt.setString(8, userProfile.getGender());
pstmt.setString(9, userProfile.getPin());
pstmt.setString(10, DateService.getDTSYYYMMDDFormat(userProfile.getDob()));
pstmt.setString(11, userProfile.getLocalAddress());
pstmt.setString(12, userProfile.getPermanentAddress());
pstmt.setString(13, userProfile.getUpdateBy());
pstmt.setString(14, DateService.getDTSYYYMMDDFormat(userProfile.getUpdateDt()));
pstmt.setString(15, userProfile.getUpdateTime());

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
	public void createuserProfile(userProfile userProfile)
	{
		
String query="INSERT INTO userprofile(userId,userName,mobileNumber,email,district,state,country,gender,pin,dob,localAddress,permanentAddress,updateBy,updateDt,updateTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, userProfile.getUserId());
pstmt.setString(2, userProfile.getUserName());
pstmt.setString(3, userProfile.getMobileNumber());
pstmt.setString(4, userProfile.getEmail());
pstmt.setString(5, userProfile.getDistrict());
pstmt.setString(6, userProfile.getState());
pstmt.setString(7, userProfile.getCountry());
pstmt.setString(8, userProfile.getGender());
pstmt.setString(9, userProfile.getPin());
//System.out.println("prakash:"+DateService.getDTSYYYMMDDFormat(userProfile.getDob()));
pstmt.setString(10, DateService.getDTSYYYMMDDFormat(userProfile.getDob()));
pstmt.setString(11, userProfile.getLocalAddress());
pstmt.setString(12, userProfile.getPermanentAddress());
pstmt.setString(13, userProfile.getUpdateBy());
pstmt.setString(14, DateService.getDTSYYYMMDDFormat(userProfile.getUpdateDt()));
pstmt.setString(15, userProfile.getUpdateTime());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getuserProfileId(userProfile);
		userProfile.setId(id);
	}
	public void updateuserProfile(userProfile userProfile)
	{
		
String query="update userprofile set "+"userId=?, userName=?, mobileNumber=?, email=?, district=?, state=?, country=?, gender=?, pin=?, dob=?, localAddress=?, permanentAddress=?, updateBy=?, updateDt=?, updateTime=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, userProfile.getUserId());
pstmt.setString(2, userProfile.getUserName());
pstmt.setString(3, userProfile.getMobileNumber());
pstmt.setString(4, userProfile.getEmail());
pstmt.setString(5, userProfile.getDistrict());
pstmt.setString(6, userProfile.getState());
pstmt.setString(7, userProfile.getCountry());
pstmt.setString(8, userProfile.getGender());
pstmt.setString(9, userProfile.getPin());
pstmt.setString(10, DateService.getDTSYYYMMDDFormat(userProfile.getDob()));
pstmt.setString(11, userProfile.getLocalAddress());
pstmt.setString(12, userProfile.getPermanentAddress());
pstmt.setString(13, userProfile.getUpdateBy());
pstmt.setString(14, DateService.getDTSYYYMMDDFormat(userProfile.getUpdateDt()));
pstmt.setString(15, userProfile.getUpdateTime());
pstmt.setInt(16, userProfile.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String userId,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+userId+"'";
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
	
	public userProfile getuserProfile(int id)
	{
		userProfile userProfile =new userProfile();
		String query="select * from userprofile where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
userProfile.setId(rs.getInt("id")==0?0:rs.getInt("id"));
userProfile.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
userProfile.setUserName(rs.getString("userName")==null?"":rs.getString("userName"));
userProfile.setMobileNumber(rs.getString("mobileNumber")==null?"":rs.getString("mobileNumber"));
userProfile.setEmail(rs.getString("email")==null?"":rs.getString("email"));
userProfile.setDistrict(rs.getString("district")==null?"":rs.getString("district"));
userProfile.setState(rs.getString("state")==null?"":rs.getString("state"));
userProfile.setCountry(rs.getString("country")==null?"":rs.getString("country"));
userProfile.setGender(rs.getString("gender")==null?"":rs.getString("gender"));
userProfile.setPin(rs.getString("pin")==null?"":rs.getString("pin"));
userProfile.setDob(rs.getDate("dob")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("dob"));
userProfile.setLocalAddress(rs.getString("localAddress")==null?"":rs.getString("localAddress"));
userProfile.setPermanentAddress(rs.getString("permanentAddress")==null?"":rs.getString("permanentAddress"));
userProfile.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
userProfile.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
userProfile.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return userProfile;
	}
	
	
	public ArrayList<userProfile> getuserProfileList()
	{
		ArrayList<userProfile> userProfileList =new ArrayList<userProfile>();
		String query="select * from userprofile";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	userProfile userProfile =new userProfile();
	    	userProfile.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	    	userProfile.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	    	userProfile.setUserName(rs.getString("userName")==null?"":rs.getString("userName"));
	    	userProfile.setMobileNumber(rs.getString("mobileNumber")==null?"":rs.getString("mobileNumber"));
	    	userProfile.setEmail(rs.getString("email")==null?"":rs.getString("email"));
	    	userProfile.setDistrict(rs.getString("district")==null?"":rs.getString("district"));
	    	userProfile.setState(rs.getString("state")==null?"":rs.getString("state"));
	    	userProfile.setCountry(rs.getString("country")==null?"":rs.getString("country"));
	    	userProfile.setGender(rs.getString("gender")==null?"":rs.getString("gender"));
	    	userProfile.setPin(rs.getString("pin")==null?"":rs.getString("pin"));
	    	userProfile.setDob(rs.getDate("dob")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("dob"));
	    	userProfile.setLocalAddress(rs.getString("localAddress")==null?"":rs.getString("localAddress"));
	    	userProfile.setPermanentAddress(rs.getString("permanentAddress")==null?"":rs.getString("permanentAddress"));
	    	userProfile.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	    	userProfile.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	    	userProfile.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	userProfileList.add(userProfile);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return userProfileList;
	}
	
	public ArrayList<userProfile> getuserProfileList(int pvalueNo,int limit)
	{
		ArrayList<userProfile> userProfileList =new ArrayList<userProfile>();
String query="select * from userprofile limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	userProfile userProfile =new userProfile();
	    	userProfile.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	    	userProfile.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	    	userProfile.setUserName(rs.getString("userName")==null?"":rs.getString("userName"));
	    	userProfile.setMobileNumber(rs.getString("mobileNumber")==null?"":rs.getString("mobileNumber"));
	    	userProfile.setEmail(rs.getString("email")==null?"":rs.getString("email"));
	    	userProfile.setDistrict(rs.getString("district")==null?"":rs.getString("district"));
	    	userProfile.setState(rs.getString("state")==null?"":rs.getString("state"));
	    	userProfile.setCountry(rs.getString("country")==null?"":rs.getString("country"));
	    	userProfile.setGender(rs.getString("gender")==null?"":rs.getString("gender"));
	    	userProfile.setPin(rs.getString("pin")==null?"":rs.getString("pin"));
	    	userProfile.setDob(rs.getDate("dob")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("dob"));
	    	userProfile.setLocalAddress(rs.getString("localAddress")==null?"":rs.getString("localAddress"));
	    	userProfile.setPermanentAddress(rs.getString("permanentAddress")==null?"":rs.getString("permanentAddress"));
	    	userProfile.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	    	userProfile.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	    	userProfile.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	userProfileList.add(userProfile);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return userProfileList;
	}
	
	public void deleteuserProfile(int id) {
		
			String query="delete from userprofile where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(userProfile userProfile)
{
String query="select * from userprofile ";
String whereClause="";
whereClause+=(userProfile.getUserId()==null?"":" userName="+userProfile.getUserId());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(userProfile userProfile)
{
String query="select count(*) from userprofile ";
String whereClause="";
whereClause+=(userProfile.getUserId()==null?"":" userName="+userProfile.getUserId());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<userProfile> getuserProfileList(userProfile userProfile)
{
ArrayList<userProfile> userProfileList =new ArrayList<userProfile>();
String query=getDynamicQuery(userProfile);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	userProfile userProfile2 =new userProfile();
	userProfile.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	userProfile.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	userProfile.setUserName(rs.getString("userName")==null?"":rs.getString("userName"));
	userProfile.setMobileNumber(rs.getString("mobileNumber")==null?"":rs.getString("mobileNumber"));
	userProfile.setEmail(rs.getString("email")==null?"":rs.getString("email"));
	userProfile.setDistrict(rs.getString("district")==null?"":rs.getString("district"));
	userProfile.setState(rs.getString("state")==null?"":rs.getString("state"));
	userProfile.setCountry(rs.getString("country")==null?"":rs.getString("country"));
	userProfile.setGender(rs.getString("gender")==null?"":rs.getString("gender"));
	userProfile.setPin(rs.getString("pin")==null?"":rs.getString("pin"));
	userProfile.setDob(rs.getDate("dob")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("dob"));
	userProfile.setLocalAddress(rs.getString("localAddress")==null?"":rs.getString("localAddress"));
	userProfile.setPermanentAddress(rs.getString("permanentAddress")==null?"":rs.getString("permanentAddress"));
	userProfile.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	userProfile.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	userProfile.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
    	userProfileList.add(userProfile2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return userProfileList;
}
	
public ArrayList<userProfile> getuserProfileList(userProfile userProfile,int pvalueNo,int limit)
{
ArrayList<userProfile> userProfileList =new ArrayList<userProfile>();
String query=getDynamicQuery(userProfile);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	userProfile userProfile2 =new userProfile();
	userProfile2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	userProfile2.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	userProfile2.setUserName(rs.getString("userName")==null?"":rs.getString("userName"));
	userProfile2.setMobileNumber(rs.getString("mobileNumber")==null?"":rs.getString("mobileNumber"));
	userProfile2.setEmail(rs.getString("email")==null?"":rs.getString("email"));
	userProfile2.setDistrict(rs.getString("district")==null?"":rs.getString("district"));
	userProfile2.setState(rs.getString("state")==null?"":rs.getString("state"));
	userProfile2.setCountry(rs.getString("country")==null?"":rs.getString("country"));
	userProfile2.setGender(rs.getString("gender")==null?"":rs.getString("gender"));
	userProfile2.setPin(rs.getString("pin")==null?"":rs.getString("pin"));
	userProfile2.setDob(rs.getDate("dob")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("dob"));
	userProfile2.setLocalAddress(rs.getString("localAddress")==null?"":rs.getString("localAddress"));
	userProfile2.setPermanentAddress(rs.getString("permanentAddress")==null?"":rs.getString("permanentAddress"));
	userProfile2.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	userProfile2.setUpdateDt(rs.getString("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	userProfile2.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
    	userProfileList.add(userProfile2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return userProfileList;
}
public void testCreate() {
	userProfileDBService userProfileDBService =new userProfileDBService();
	
	userProfile userProfile = new userProfile(); 
	userProfile.setDefaultValues();
	userProfileDBService.createuserProfile(userProfile);
	
	//Test2 for Read All Records
	ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
	userProfileService userProfileService =new userProfileService();
	userProfileService.displayList(userProfileList);
	userProfileDBService.closeConnection();
	
}

public void testReadAll() {
	userProfileDBService userProfileDBService =new userProfileDBService();
	
	userProfile userProfile = new userProfile(); 
	
	
	//Test2 for Read All Records
	ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
	userProfileService userProfileService =new userProfileService();
	userProfileService.displayList(userProfileList);
	userProfileDBService.closeConnection();
}

public void testUpdate() {
	userProfileDBService userProfileDBService =new userProfileDBService();
	
	userProfile userProfile = new userProfile(); 
	
	
	userProfile.setId(1);
	userProfile.setUserId("Admin12");
	userProfile.setUserName("admin12");
	userProfile.setMobileNumber("7845128985");
	userProfile.setEmail("Admin12@gmail.com");
	userProfile.setDistrict("Gwalior");
	userProfile.setState("MP");
	userProfile.setCountry("IN");
	userProfile.setGender("male");
	userProfile.setPin("Admin12");
	userProfile.setDob(DateService.getSTDYYYMMDDFormat("2012-12-21"));
	userProfile.setLocalAddress("MP np 98");
	userProfile.setPermanentAddress("mp np 98");
	userProfile.setUpdateBy("Admin");
	userProfile.setUpdateDt(DateService.getSTDYYYMMDDFormat("2012-12-22"));
	userProfile.setUpdateTime("12:12");
	
	
	
	userProfileDBService.updateuserProfile(userProfile);
	
	
	
	//Test3 for Read All Records
	ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
	userProfileService userProfileService =new userProfileService();
	userProfileService.displayList(userProfileList);
	userProfileDBService.closeConnection();
}

public void testDelete() {
	userProfileDBService userProfileDBService =new userProfileDBService();
	
	userProfile userProfile = new userProfile();
	
	//Test4 for Delete Record
	userProfileDBService.deleteuserProfile(9);
	
	
	//Test4 for Read All Records
	ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
	userProfileService userProfileService =new userProfileService();
	userProfileService.displayList(userProfileList);
	userProfileDBService.closeConnection();
}

public void testSearch() {
	userProfileDBService userProfileDBService =new userProfileDBService();
	
	userProfile userProfile = new userProfile();
	
	//Test4 for Delete Record
	userProfile=userProfileDBService.getuserProfile(1);
	userProfile.displayValues();
	
	
	//Test4 for Read All Records
	//ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
	//userProfileService userProfileService =new userProfileService();
	//userProfileService.displayList(userProfileList);
	
}
	public static void main(String[] args) {
		
		userProfileDBService userProfileDBService =new userProfileDBService();
		
		userProfileDBService.testCreate();
		//userProfileDBService.testReadAll();
		//userProfileDBService.testUpdate();
		//userProfileDBService.testDelete();
		//userProfileDBService.testSearch();
		
		
		/*
		 //Test-1 : Create userProfile
		  
		userProfile userProfile = new userProfile(); 
		userProfile.setDefaultValues();
		
			//test1 for Create Record
		//userProfileDBService.createuserProfile(userProfile);
		  
			//Test2 for Read All Records 
		//ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
		//userProfileService userProfileService =new userProfileService();
		//userProfileService.displayList(userProfileList);
		
		
		
			//Test3 for Update Record
		userProfile.setId(5);
		userProfile.setCode("Admin123");
		userProfile.setValue("admin12");
		userProfileDBService.updateuserProfile(userProfile);
		//userProfile=userProfileDBService.getuserProfile(5);
		userProfile.displayValues();
		
		//Test4 for Delete Record
		userProfileDBService.deleteuserProfile(4);
		ArrayList<userProfile> userProfileList = userProfileDBService.getuserProfileList();
		userProfileService userProfileService =new userProfileService();
		userProfileService.displayList(userProfileList);
		
		
		//Test5 for Read one Record
		userProfile=userProfileDBService.getuserProfile(5);
		userProfile.displayValues();
		*/
	}

	public ArrayList<userProfile> getuserProfileList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<userProfile> getuserProfileList1() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages(userProfile userProfile, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}

