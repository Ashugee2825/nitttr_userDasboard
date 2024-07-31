package country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DropDownCountryDBService {

Connection con;

	public DropDownCountryDBService()
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
	
	
	public ArrayList<CountryDTO> getList(String tableName)
	{
		
		ArrayList<CountryDTO> list = new ArrayList<CountryDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				CountryDTO countryDTO =new CountryDTO();
				countryDTO.setId(rs.getInt("id"));
				countryDTO.setCode(rs.getString("code"));
				countryDTO.setValue(rs.getString("value"));
				list.add(countryDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

