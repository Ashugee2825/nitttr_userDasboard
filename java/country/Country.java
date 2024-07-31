package country;

import jakarta.servlet.http.HttpServletRequest;

public class Country {
 
int id;
String code;
String value;




public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setCode(null!=request.getParameter("code")?request.getParameter("code"):"");
this.setValue(null!=request.getParameter("value")?request.getParameter("value"):"");
}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("code = "+request.getParameter("code"));
System.out.println("value = "+request.getParameter("value"));

System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("code = "+this.getCode());
System.out.println("value = "+this.getValue());

}

public void setDefaultValues() {

this.setCode("UK");
this.setValue("United Kingdom");

}

/*public void setUpdateValues() {
	this.setCode("Admin111");
	this.setValue("Admin222");
}*/
}
