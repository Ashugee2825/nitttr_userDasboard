package gender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DrownDowngenderDBService {

Connection con;

	public DrownDowngenderDBService()
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
	
	
	public ArrayList<GenderDTO> getList(String tableName)
	{
		
		ArrayList<GenderDTO> list = new ArrayList<GenderDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				GenderDTO GenderDTO =new GenderDTO();
				GenderDTO.setId(rs.getInt("id"));
				GenderDTO.setCode(rs.getString("code"));
				GenderDTO.setValue(rs.getString("value"));
				list.add(GenderDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

