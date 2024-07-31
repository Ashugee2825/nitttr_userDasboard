package userProfile;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;

public class userProfile {
 
int id;
String userId;
String userName;
String mobileNumber;
String email;
String district;
String state;
String country;
String gender;
String pin;
Date dob;
String localAddress;
String permanentAddress;
String updateBy;
Date updateDt;
String updateTime;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public String getLocalAddress() {
	return localAddress;
}

public void setLocalAddress(String localAddress) {
	this.localAddress = localAddress;
}

public String getPermanentAddress() {
	return permanentAddress;
}

public void setPermanentAddress(String permanentAddress) {
	this.permanentAddress = permanentAddress;
}

public String getUpdateBy() {
	return updateBy;
}

public void setUpdateBy(String updateBy) {
	this.updateBy = updateBy;
}

public Date getUpdateDt() {
	return updateDt;
}

public void setUpdateDt(Date updateDt) {
	this.updateDt = updateDt;
}

public String getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}







public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setUserId(null!=request.getParameter("userId")?request.getParameter("userId"):"");
this.setUserName(null!=request.getParameter("userName")?request.getParameter("userName"):"");
this.setMobileNumber(null!=request.getParameter("mobileNumber")?request.getParameter("mobileNumber"):"");
this.setEmail(null!=request.getParameter("email")?request.getParameter("email"):"");
this.setDistrict(null!=request.getParameter("district")?request.getParameter("district"):"");
this.setState(null!=request.getParameter("state")?request.getParameter("state"):"");
this.setCountry(null!=request.getParameter("country")?request.getParameter("country"):"");
this.setGender(null!=request.getParameter("gender")?request.getParameter("gender"):"");
this.setPin(null!=request.getParameter("pin")?request.getParameter("pin"):"");
this.setDob(null!=request.getParameter("dob")?DateService.getSTDYYYMMDDFormat(request.getParameter("dob")):DateService.getSTDYYYMMDDFormat("11-11-1111"));
this.setLocalAddress(null!=request.getParameter("localAddress")?request.getParameter("localAddress"):"");
this.setPermanentAddress(null!=request.getParameter("permanentAddress")?request.getParameter("permanentAddress"):"");
this.setUpdateBy(null!=request.getParameter("updateBy")?request.getParameter("updateBy"):"");
this.setUpdateDt(null!=request.getParameter("updateDt")?DateService.getSTDYYYMMDDFormat(request.getParameter("updateDt")):DateService.getSTDYYYMMDDFormat("11-11-1111"));
this.setUpdateTime(null!=request.getParameter("updateTime")?request.getParameter("updateTime"):"");


}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("userId = "+request.getParameter("userId"));
System.out.println("userName = "+request.getParameter("userName"));
System.out.println("mobileNumber = "+request.getParameter("mobileNumber"));
System.out.println("email = "+request.getParameter("email"));
System.out.println("district = "+request.getParameter("district"));
System.out.println("state = "+request.getParameter("state"));
System.out.println("country = "+request.getParameter("country"));
System.out.println("gender = "+request.getParameter("gender"));
System.out.println("pin = "+request.getParameter("pin"));
System.out.println("dob = "+request.getParameter("dob"));
System.out.println("localAddress = "+request.getParameter("localAddress"));
System.out.println("permanentAddress = "+request.getParameter("permanentAddress"));
System.out.println("updateBy = "+request.getParameter("updateBy"));
System.out.println("updateDt = "+request.getParameter("updateDt"));
System.out.println("updateTime = "+request.getParameter("updateTime"));



System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("userId = "+this.getUserId());
System.out.println("userName = "+this.getUserName());
System.out.println("mobileNumber = "+this.getMobileNumber());
System.out.println("email = "+this.getEmail());
System.out.println("district = "+this.getDistrict());
System.out.println("state = "+this.getState());
System.out.println("country = "+this.getCountry());
System.out.println("gender = "+this.getGender());
System.out.println("pin = "+this.getPin());
System.out.println("dob = "+this.getDob());
System.out.println("localAddress = "+this.getLocalAddress());
System.out.println("permanentAddress = "+this.getPermanentAddress());
System.out.println("updateBy = "+this.getUpdateBy());
System.out.println("updateDt = "+this.getUpdateDt());
System.out.println("updateTime = "+this.getUpdateTime());


}

public void setDefaultValues() {

this.setUserId("Admin1112");
this.setUserName("Teacher");
this.setMobileNumber("98754895");
this.setEmail("Dean2233@gmail.com");
this.setDistrict("Bhopal");
this.setState("MP");
this.setCountry("INDIA");
this.setGender("Male");
this.setPin("456394");
this.setDob(DateService.getSTDYYYMMDDFormat("20-02-2000"));
this.setLocalAddress("hills");
this.setPermanentAddress("Nitttr Bhopal");
this.setUpdateBy("Dean");
this.setUpdateDt(DateService.getSTDYYYMMDDFormat("22-03-2023"));
this.setUpdateTime("02:38");
}

/*public void setUpdateValues() {
	this.setCode("Admin111");
	this.setValue("Admin222");
}*/
}
