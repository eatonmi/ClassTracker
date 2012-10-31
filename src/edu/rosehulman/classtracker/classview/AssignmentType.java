package edu.rosehulman.classtracker.classview;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class AssignmentType implements Serializable {
	private static final long serialVersionUID = 684911394306128213L;
	private double weight;
	private String name;
	private HashMap<String, Assignment> assignments;
	
	public AssignmentType(double weight, String name)
	{
		this.weight = weight;
		this.name = name;
		this.assignments = new HashMap<String, Assignment>();
	}
	
	public AssignmentType(double weight, String name, HashMap<String, Assignment> assignments)
	{
		this.weight = weight;
		this.name = name;
		this.assignments = assignments;
	}
	
	public AssignmentType(double weight, String name, Set<Assignment> assignments)
	{
		this.weight = weight;
		this.name = name;
		this.assignments = new HashMap<String, Assignment>();
		for(Assignment assign : assignments)
		{
			this.assignments.put(assign.getName(), assign);
		}
	}
	
	public int getPointsReceived()
	{
		int total = 0;
		for(String key : this.assignments.keySet())
		{
			total += this.assignments.get(key).getPointsReceived();
		}
		return total;
	}
	
	public int getPointsPossible()
	{
		int total = 0;
		for(String key : this.assignments.keySet())
		{
			total += this.assignments.get(key).getPointsPossible();
		}
		return total;
	}
	
	public void insertAssignment(Assignment insert)
	{
		this.assignments.put(insert.getName(), insert);
	}
	
	public Set<String> getAssignmentNames()
	{
		return assignments.keySet();
	}
	
	public Collection<Assignment> getAssignments()
	{
		return this.assignments.values();
	}
	
	public Assignment getAssignment(String name)
	{
		return assignments.get(name);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public double getWeight()
	{
		return this.weight;
	}
	
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
}
