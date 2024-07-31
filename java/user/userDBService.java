package user;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class userDBService {
	private static ArrayList<user> userList;
	Connection con;
	
	
	public userDBService()
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
		String query="select count(*) from user";
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
	public int getTotalPvalues(user user,int limit)
	{
		String query=getDynamicQuery2(user);
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
	
	
	private String getDynamicQuery2(user user) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getuserId(user user)
	{
		int id=0;
		String query="select id from user";
String whereClause = " where "+ "userId=?,pwd=?,groupId=?, updateBy=?, updateDt=? and updateTime=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, user.getUserId());
pstmt.setString(2, user.getPwd());
pstmt.setString(3, user.getGroupId());
pstmt.setString(4, user.getUpdateBy());
pstmt.setString(5, DateService.getDTSYYYMMDDFormat(user.getUpdateDt()));
pstmt.setString(6, user.getUpdateTime());

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
	public void createuser(user user)
	{
		
String query="INSERT INTO user(userId,pwd,groupId,updateBy,updateDt,updateTime) VALUES(?,?,?,?,?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, user.getUserId());
pstmt.setString(2, user.getPwd());
pstmt.setString(3, user.getGroupId());
pstmt.setString(4, user.getUpdateBy());
pstmt.setString(5, DateService.getDTSYYYMMDDFormat(user.getUpdateDt()));
pstmt.setString(6, user.getUpdateTime());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getuserId(user);
		user.setId(id);
	}
	//update
	public void updateuser(user user)
	{
		
String query="update user set "+"userId=?, pwd=?, groupId=?, updateBy=?, updateDt=?, updateTime=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, user.getUserId());
pstmt.setString(2, user.getPwd());
pstmt.setString(3, user.getGroupId());
pstmt.setString(4, user.getUpdateBy());
pstmt.setString(5, DateService.getDTSYYYMMDDFormat(user.getUpdateDt()));
pstmt.setString(6, user.getUpdateTime());
pstmt.setInt(7, user.getId());
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
	
	public user getuser(int id)
	{
		user user =new user();
		String query="select * from user where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
user.setId(rs.getInt("id")==0?0:rs.getInt("id"));
user.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
user.setPwd(rs.getString("pwd")==null?"":rs.getString("pwd"));
user.setGroupId(rs.getString("groupId")==null?"":rs.getString("groupId"));
user.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
user.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
user.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return user;
	}
	
	
	public ArrayList<user> getuserList()
	{
		ArrayList<user> userList =new ArrayList<user>();
		String query="select * from user";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	user user =new user();
	    	user.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	    	user.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	    	user.setPwd(rs.getString("pwd")==null?"":rs.getString("pwd"));
	    	user.setGroupId(rs.getString("groupId")==null?"":rs.getString("groupId"));
	    	user.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	    	user.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	    	user.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	userList.add(user);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return userList;
	}
	
	public ArrayList<user> getuserList(int pvalueNo,int limit)
	{
		ArrayList<user> userList =new ArrayList<user>();
String query="select * from userprofile limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	user user =new user();
	    	user.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	    	user.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	    	user.setPwd(rs.getString("pwd")==null?"":rs.getString("pwd"));
	    	user.setGroupId(rs.getString("groupId")==null?"":rs.getString("groupId"));
	    	user.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	    	user.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	    	user.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
	    	userList.add(user);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return userList;
	}
	
	public void deleteuser(int id) {
		
			String query="delete from user where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(user user)
{
String query="select * from user ";
String whereClause="";
whereClause+=(user.getUserId()==null?"":" userName="+user.getUserId());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(user user)
{
String query="select count(*) from user ";
String whereClause="";
whereClause+=(user.getUserId()==null?"":" userName="+user.getUserId());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<user> getuserList(user user)
{
ArrayList<user> userList =new ArrayList<user>();
String query=getDynamicQuery(user);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	user user2 =new user();
	user.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	user.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	user.setPwd(rs.getString("pwd")==null?"":rs.getString("pwd"));
	user.setGroupId(rs.getString("groupId")==null?"":rs.getString("groupId"));
	user.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	user.setUpdateDt(rs.getDate("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	user.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
    	userList.add(user2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return userList;
}
	
public ArrayList<user> getuserList(user user,int pvalueNo,int limit)
{
ArrayList<user> userList =new ArrayList<user>();
String query=getDynamicQuery(user);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	user user2 =new user();
	user2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
	user2.setUserId(rs.getString("userId")==null?"":rs.getString("userId"));
	user2.setPwd(rs.getString("pwd")==null?"":rs.getString("pwd"));
	user2.setGroupId(rs.getString("groupId")==null?"":rs.getString("groupId"));
	user2.setUpdateBy(rs.getString("updateBy")==null?"":rs.getString("updateBy"));
	user2.setUpdateDt(rs.getString("updateDt")==null?DateService.getSTDYYYMMDDFormat("1111-11-11"):rs.getDate("updateDt"));
	user2.setUpdateTime(rs.getString("updateTime")==null?"":rs.getString("updateTime"));
    	userList.add(user2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return userList;
}
public void testCreate() {
	userDBService userDBService =new userDBService();
	
	user user = new user(); 
	user.setDefaultValues();
	userDBService.createuser(user);
	
	//Test2 for Read All Records
	ArrayList<user> userList = userDBService.getuserList();
	userService userService =new userService();
	userService.displayList(userList);
	userDBService.closeConnection();
	
}

public void testReadAll() {
	userDBService userDBService =new userDBService();
	
	user user = new user(); 
	
	
	//Test2 for Read All Records
	ArrayList<user> userList = userDBService.getuserList();
	userService userService =new userService();
	userService.displayList(userList);
	userDBService.closeConnection();
}

public void testUpdate() {
	userDBService userDBService =new userDBService();
	
	user user = new user(); 
	
	
	user.setId(1);
	user.setUserId("Admin12");
	user.setPwd("admin12");
	user.setGroupId("admin123");
	user.setUpdateBy("Admin");
	user.setUpdateDt(DateService.getSTDYYYMMDDFormat("2012-12-22"));
	user.setUpdateTime("12:12");
	
	System.out.println("Admin");
	
	userDBService.updateuser(user);
	
	
	
	//Test3 for Read All Records
	ArrayList<user> userList = userDBService.getuserList();
	userService userService =new userService();
	userService.displayList(userList);
	userDBService.closeConnection();
}

public void testDelete() {
	userDBService userDBService =new userDBService();
	
	user user = new user();
	
	//Test4 for Delete Record
	userDBService.deleteuser(9);
	
	
	//Test4 for Read All Records
	ArrayList<user> userList = userDBService.getuserList();
	userService userService =new userService();
	userService.displayList(userList);
	userDBService.closeConnection();
}

public void testSearch() {
	userDBService userDBService =new userDBService();
	
	user user = new user();
	
	//Test4 for Delete Record
	user=userDBService.getuser(1);
	user.displayValues();
	
	
	//Test4 for Read All Records
	//ArrayList<user> userList = userDBService.getuserList();
	//userService userService =new userService();
	//userService.displayList(userList);
	
}
	public static void main(String[] args) {
		
		userDBService userDBService =new userDBService();
		
		userDBService.testCreate();
		//userDBService.testReadAll();
		//userDBService.testUpdate();
		//userDBService.testDelete();
		//userDBService.testSearch();
		
		
		/*
		 //Test-1 : Create user
		  
		user user = new user(); 
		user.setDefaultValues();
		
			//test1 for Create Record
		//userDBService.createuser(user);
		  
			//Test2 for Read All Records 
		//ArrayList<user> userList = userDBService.getuserList();
		//userService userService =new userService();
		//userService.displayList(userList);
		
		
		
			//Test3 for Update Record
		user.setId(5);
		user.setCode("Admin123");
		user.setValue("admin12");
		userDBService.updateuser(user);
		//user=userDBService.getuser(5);
		user.displayValues();
		
		//Test4 for Delete Record
		userDBService.deleteuser(4);
		ArrayList<user> userList = userDBService.getuserList();
		userService userService =new userService();
		userService.displayList(userList);
		
		
		//Test5 for Read one Record
		user=userDBService.getuser(5);
		user.displayValues();
		*/
	}

	public ArrayList<user> getuserList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<user> getuserList1() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages(user user, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}

