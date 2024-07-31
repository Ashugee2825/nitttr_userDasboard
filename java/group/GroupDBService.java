package group;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class GroupDBService {
	private static ArrayList<Group> groupList;
	Connection con;
	
	
	public GroupDBService()
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
		String query="select count(*) from dd_group";
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
	public int getTotalPvalues(Group group,int limit)
	{
		String query=getDynamicQuery2(group);
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
	
	
	private String getDynamicQuery2(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getGroupId(Group group)
	{
		int id=0;
		String query="select id from dd_group";
String whereClause = " where "+ "code=? and value=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, group.getCode());
pstmt.setString(2, group.getValue());
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
	public void createGroup(Group group)
	{
		
String query="INSERT INTO dd_group(code,value) VALUES(?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, group.getCode());
pstmt.setString(2, group.getValue());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getGroupId(group);
		group.setId(id);
	}
	public void updateGroup(Group group)
	{
		
String query="update dd_group set "+"code=?,value=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, group.getCode());
pstmt.setString(2, group.getValue());
pstmt.setInt(3, group.getId());
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
	
	public Group getGroup(int id)
	{
		Group group =new Group();
		String query="select * from dd_group where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
group.setId(rs.getInt("id")==0?0:rs.getInt("id"));
group.setCode(rs.getString("code")==null?"":rs.getString("code"));
group.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return group;
	}
	
	
	public ArrayList<Group> getGroupList()
	{
		ArrayList<Group> groupList =new ArrayList<Group>();
		String query="select * from dd_group";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Group group =new Group();
group.setId(rs.getInt("id")==0?0:rs.getInt("id"));
group.setCode(rs.getString("code")==null?"":rs.getString("code"));
group.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	groupList.add(group);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return groupList;
	}
	
	public ArrayList<Group> getGroupList(int pvalueNo,int limit)
	{
		ArrayList<Group> groupList =new ArrayList<Group>();
String query="select * from dd_group limit "+limit +" offset "+limit*(pvalueNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Group group =new Group();
group.setId(rs.getInt("id")==0?0:rs.getInt("id"));
group.setCode(rs.getString("code")==null?"":rs.getString("code"));
group.setValue(rs.getString("value")==null?"":rs.getString("value"));
	    	groupList.add(group);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return groupList;
	}
	
	public void deleteGroup(int id) {
		
			String query="delete from dd_group where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(Group group)
{
String query="select * from dd_group ";
String whereClause="";
whereClause+=(group.getValue()==null?"":" value="+group.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery21(Group group)
{
String query="select count(*) from dd_group ";
String whereClause="";
whereClause+=(group.getValue()==null?"":" value="+group.getValue());
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<Group> getGroupList(Group group)
{
ArrayList<Group> groupList =new ArrayList<Group>();
String query=getDynamicQuery(group);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Group group2 =new Group();
group2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
group2.setCode(rs.getString("code")==null?"":rs.getString("code"));
group2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	groupList.add(group2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return groupList;
}
	
public ArrayList<Group> getGroupList(Group group,int pvalueNo,int limit)
{
ArrayList<Group> groupList =new ArrayList<Group>();
String query=getDynamicQuery(group);
query+= " limit "+limit +" offset "+limit*(pvalueNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
	Group group2 =new Group();
group2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
group2.setCode(rs.getString("code")==null?"":rs.getString("code"));
group2.setValue(rs.getString("value")==null?"":rs.getString("value"));
    	groupList.add(group2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return groupList;
}
public void testCreate() {
	GroupDBService groupDBService =new GroupDBService();
	
	Group group = new Group(); 
	group.setDefaultValues();
	groupDBService.createGroup(group);
	
	//Test2 for Read All Records
	ArrayList<Group> groupList = groupDBService.getGroupList();
	GroupService groupService =new GroupService();
	groupService.displayList(groupList);
	groupDBService.closeConnection();
	
}

public void testReadAll() {
	GroupDBService groupDBService =new GroupDBService();
	
	Group group = new Group(); 
	
	
	//Test2 for Read All Records
	ArrayList<Group> groupList = groupDBService.getGroupList();
	GroupService groupService =new GroupService();
	groupService.displayList(groupList);
	groupDBService.closeConnection();
}

public void testUpdate() {
	GroupDBService groupDBService =new GroupDBService();
	
	Group group = new Group(); 
	
	
	group.setId(5);
	group.setCode("Admin12");
	group.setValue("admin12");
	
	groupDBService.updateGroup(group);
	
	
	
	//Test3 for Read All Records
	ArrayList<Group> groupList = groupDBService.getGroupList();
	GroupService groupService =new GroupService();
	groupService.displayList(groupList);
	groupDBService.closeConnection();
}

public void testDelete() {
	GroupDBService groupDBService =new GroupDBService();
	
	Group group = new Group();
	
	//Test4 for Delete Record
	groupDBService.deleteGroup(9);
	
	
	//Test4 for Read All Records
	ArrayList<Group> groupList = groupDBService.getGroupList();
	GroupService groupService =new GroupService();
	groupService.displayList(groupList);
	groupDBService.closeConnection();
}

public void testSearch() {
	GroupDBService groupDBService =new GroupDBService();
	
	Group group = new Group();
	
	//Test4 for Delete Record
	group=groupDBService.getGroup(5);
	group.displayValues();
	
	
	//Test4 for Read All Records
	//ArrayList<Group> groupList = groupDBService.getGroupList();
	//GroupService groupService =new GroupService();
	//groupService.displayList(groupList);
	
}
	public static void main(String[] args) {
		
		GroupDBService groupDBService =new GroupDBService();
		
		groupDBService.testCreate();
		//groupDBService.testReadAll();
		//groupDBService.testUpdate();
		//groupDBService.testDelete();
		//groupDBService.testSearch();
		
		
		/*
		 //Test-1 : Create group
		  
		Group group = new Group(); 
		group.setDefaultValues();
		
			//test1 for Create Record
		//groupDBService.createGroup(group);
		  
			//Test2 for Read All Records 
		//ArrayList<Group> groupList = groupDBService.getGroupList();
		//GroupService groupService =new GroupService();
		//groupService.displayList(groupList);
		
		
		
			//Test3 for Update Record
		group.setId(5);
		group.setCode("Admin123");
		group.setValue("admin12");
		groupDBService.updateGroup(group);
		//group=groupDBService.getGroup(5);
		group.displayValues();
		
		//Test4 for Delete Record
		groupDBService.deleteGroup(4);
		ArrayList<Group> groupList = groupDBService.getGroupList();
		GroupService groupService =new GroupService();
		groupService.displayList(groupList);
		
		
		//Test5 for Read one Record
		group=groupDBService.getGroup(5);
		group.displayValues();
		*/
	}

	public ArrayList<Group> getGroupList11() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Group> getGroupList1() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getTotalPages(int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalPages(Group group, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}

