package group;

import java.util.ArrayList;

import  group.Group;

public class GroupService {

	public void displayList(ArrayList<Group> groupList)
	{
		groupList.forEach((group) -> print(group));
	}
	
	public void print(Group group)
	{
		group.displayValues();
	}
	
}
