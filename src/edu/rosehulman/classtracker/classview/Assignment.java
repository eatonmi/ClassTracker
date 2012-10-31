package edu.rosehulman.classtracker.classview;

import java.io.Serializable;
import java.util.Date;

public class Assignment implements Serializable {
	private static final long serialVersionUID = -1108813748902537401L;
	private String Name;
	private Date dueDate;
	private int pointsPossible;
	private int pointsReceived;
	
	public Assignment(String name, int pointsPossible)
	{
		this.Name = name;
		this.pointsPossible = pointsPossible;
	}
	
	public Assignment(String name, int pointsPossible, int pointsReceived)
	{
		this.Name = name;
		this.pointsPossible = pointsPossible;
		this.pointsReceived = pointsReceived;
	}
	
	public Assignment(String name, int pointsPossible, Date dueDate)
	{
		this.Name = name;
		this.pointsPossible = pointsPossible;
		this.dueDate = dueDate;
	}
	
	public Assignment(String name, int pointsPossible, int pointsReceived, Date dueDate)
	{
		this.Name = name;
		this.pointsPossible = pointsPossible;
		this.pointsReceived = pointsReceived;
		this.dueDate = dueDate;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public void setName(String name)
	{
		this.Name = name;
	}
	
	public Date getDueDate()
	{
		return this.dueDate;
	}
	
	public void setDueDate(Date due)
	{
		this.dueDate = due;
	}
	
	public void setPointsPossible(int points)
	{
		this.pointsPossible = points;
	}
	
	public int getPointsPossible()
	{
		return this.pointsPossible;
	}
	
	public void setPointsReceived(int received)
	{
		this.pointsReceived = received;
	}
	
	public int getPointsReceived()
	{
		return this.pointsReceived;
	}
	
}
