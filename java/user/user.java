package user;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;

public class user {
 
int id;
String userId;
String pwd;
String groupId;
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

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}

public String getGroupId() {
	return groupId;
}

public void setGroupId(String groupId) {
	this.groupId = groupId;
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
this.setPwd(null!=request.getParameter("Pwd")?request.getParameter("Pwd"):"");
this.setGroupId(null!=request.getParameter("groupId")?request.getParameter("groupId"):"");
this.setUpdateBy(null!=request.getParameter("updateBy")?request.getParameter("updateBy"):"");
this.setUpdateDt(null!=request.getParameter("updateDt")?DateService.getSTDYYYMMDDFormat(request.getParameter("updateDt")):DateService.getSTDYYYMMDDFormat("11-11-1111"));
this.setUpdateTime(null!=request.getParameter("updateTime")?request.getParameter("updateTime"):"");


}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("userId = "+request.getParameter("userId"));
System.out.println("Pwd = "+request.getParameter("Pwd"));
System.out.println("groupId = "+request.getParameter("groupId"));
System.out.println("updateBy = "+request.getParameter("updateBy"));
System.out.println("updateDt = "+request.getParameter("updateDt"));
System.out.println("updateTime = "+request.getParameter("updateTime"));



System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("userId = "+this.getUserId());
System.out.println("Pwd = "+this.getPwd());
System.out.println("groupId = "+this.getGroupId());
System.out.println("updateBy = "+this.getUpdateBy());
System.out.println("updateDt = "+this.getUpdateDt());
System.out.println("updateTime = "+this.getUpdateTime());


}

public void setDefaultValues() {

this.setUserId("Admin1112");
this.setPwd("Dean123");
this.setGroupId("Dean123456");
this.setUpdateBy("Dean");
this.setUpdateDt(DateService.getSTDYYYMMDDFormat("22-03-2023"));
this.setUpdateTime("02:38");
}

/*public void setUpdateValues() {
	this.setCode("Admin111");
	this.setValue("Admin222");
}*/
}
