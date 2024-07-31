package userProfile;

import java.util.ArrayList;

import  userProfile.userProfile;

public class userProfileService {

	public void displayList(ArrayList<userProfile> userProfileList)
	{
		userProfileList.forEach((userProfile) -> print(userProfile));
	}
	
	public void print(userProfile userProfile)
	{
		userProfile.displayValues();
	}
	
}
