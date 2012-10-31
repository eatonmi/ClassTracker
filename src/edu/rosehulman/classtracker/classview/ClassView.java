package edu.rosehulman.classtracker.classview;

import java.util.Collection;

import android.content.Context;
import edu.rosehulman.classtracker.datamanagement.DataManager;

public class ClassView {

	private static ClassView singleton;

	private DataSet set;
	private DataManager manager;
	private Class currentClass;
	private AssignmentType currentType;
	private Assignment currentAssignment;
	
	public static ClassView getInstance(Context appContext)
	{
		if(singleton == null)
			singleton = new ClassView(appContext);
		return singleton;
	}

	private ClassView(Context appContext) {
		this.manager = new DataManager(appContext);
		this.set = this.manager.retreiveData();
	}

	public void resetData() {
		this.set = new DataSet();
		this.manager.writeData(this.set);
	}
	
	public Collection<Class> getClasses()
	{
		return this.set.getClasses();
	}
	
	public Class getCurrentClass()
	{
		return this.currentClass;
	}
	
	public void setCurrentClass(Class setTo)
	{
		this.currentClass = setTo;
	}
	
	public AssignmentType getCurrentAssignmentType()
	{
		return this.currentType;
	}
	
	public void setCurrentAssignmentType(AssignmentType setTo)
	{
		this.currentType = setTo;
	}
	
	public Assignment getCurrentAssignment()
	{
		return this.currentAssignment;
	}
	
	public void setCurrentAssignment(Assignment setTo)
	{
		this.currentAssignment = setTo;
	}
	
	public void addClass(Class add)
	{
		this.set.addClass(add);
		this.manager.writeData(set);
	}
}
