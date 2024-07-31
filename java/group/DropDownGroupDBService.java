package group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DropDownGroupDBService {

Connection con;

	public DropDownGroupDBService()
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
	
	
	public ArrayList<GroupDTO> getList(String tableName)
	{
		
		ArrayList<GroupDTO> list = new ArrayList<GroupDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				GroupDTO GroupDTO =new GroupDTO();
				GroupDTO.setId(rs.getInt("id"));
				GroupDTO.setCode(rs.getString("code"));
				GroupDTO.setValue(rs.getString("value"));
				list.add(GroupDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

