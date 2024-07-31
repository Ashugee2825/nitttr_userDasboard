package userProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DropDownUserProfileDBService {

Connection con;

	public DropDownUserProfileDBService()
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
	
	
	public ArrayList<userProfileDTO> getList(String tableName)
	{
		
		ArrayList<userProfileDTO> list = new ArrayList<userProfileDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				userProfileDTO userProfileDTO =new userProfileDTO();
				userProfileDTO.setId(rs.getInt("id"));
				userProfileDTO.setUserId(rs.getString("userId"));
				userProfileDTO.setUserName(rs.getString("userName"));
				userProfileDTO.setMobileNumber(rs.getString("mobileNumber"));
				userProfileDTO.setEmail(rs.getString("email"));
				userProfileDTO.setDistrict(rs.getString("district"));
				userProfileDTO.setState(rs.getString("state"));
				userProfileDTO.setCountry(rs.getString("country"));
				userProfileDTO.setGender(rs.getString("gender"));
				userProfileDTO.setPin(rs.getString("pin"));
				userProfileDTO.setDob(rs.getDate("dob"));
				userProfileDTO.setLocalAddress(rs.getString("localAddress"));
				userProfileDTO.setPermanentAddress(rs.getString("permanentAddress"));
				userProfileDTO.setUpdateBy(rs.getString("updateBy"));
				userProfileDTO.setUpdateDt(rs.getDate("updateDt"));
				userProfileDTO.setUpdateTime(rs.getString("updateTime"));
				list.add(userProfileDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}

