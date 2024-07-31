package gender;

import java.util.ArrayList;


import gender.Gender;

public class GenderService {

	public void displayList(ArrayList<Gender> genderList)
	{
		genderList.forEach((gender) -> print(gender));
	}
	
	public void print(Gender gender)
	{
		gender.displayValues();
	}
	
}
