package user;

import java.util.ArrayList;



import gender.Gender;

public class userService {

	public void displayList(ArrayList<user> userList)
	{
		userList.forEach((user) -> print(user));
	}
	
	public void print(user user)
	{
		user.displayValues();
	}
	
}
