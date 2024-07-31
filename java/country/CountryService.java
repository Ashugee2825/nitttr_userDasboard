package country;

import java.util.ArrayList;

import  group.Group;

public class CountryService {

	public void displayList(ArrayList<Country> groupList)
	{
		groupList.forEach((country) -> print(country));
	}
	
	public void print(Country country)
	{
		country.displayValues();
	}
	
}
