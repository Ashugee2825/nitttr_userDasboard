package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DrownDownUserDBService {

Connection con;

	public DrownDownUserDBService()
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
	
	
	public ArrayList<userDTO> getList(String tableName)
	{
		
		ArrayList<userDTO> list = new ArrayList<userDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				userDTO userDTO =new userDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setUserId(rs.getString("userId"));
				userDTO.setPwd(rs.getString("Pwd"));
				userDTO.setGroupId(rs.getString("groupId"));
				userDTO.setUpdateBy(rs.getString("updateBy"));
				userDTO.setUpdateDt(rs.getDate("updateDt"));
				userDTO.setUpdateBy(rs.getString("updateTime"));
				list.add(userDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

