package edu.rosehulman.classtracker.classview;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Class implements Serializable {
	private static final long serialVersionUID = -1872459540926263001L;
	public HashMap<String, AssignmentType> types;
	public String name;
	
	public Class(String name, Collection<AssignmentType> types)
	{
		this.name = name;
		this.setTypesFromSet(types);
	}
	
	public Set<String> getAssignmentTypeNames()
	{
		return this.types.keySet();
	}
	
	public Collection<AssignmentType> getAssigmentTypes()
	{
		return this.types.values();
	}
	
	public void setAssignmentTypes(Set<AssignmentType> types)
	{
		this.setTypesFromSet(types);
	}
	
	public AssignmentType getAssignmentType(String name)
	{
		return this.types.get(name);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 * NOTE:  Everything non-UI should be passed as decimals, not percentages.
	 * The UI will convert from a decimal to a percentage ( * 100), and should pass down as
	 * a decimal, not a percentage.
	 */
	public double getClassPercentageGrade()
	{
		double result = 0;
		
		for(AssignmentType type : this.types.values())
		{
			result += type.getWeight() * (type.getPointsReceived()/type.getPointsPossible());
		}
		
		return result;
	}
	
	private void setTypesFromSet(Collection<AssignmentType> types)
	{
		this.types = new HashMap<String, AssignmentType>();
		
		for(AssignmentType type : types)
		{
			this.types.put(type.getName(), type);
		}
	}
}
