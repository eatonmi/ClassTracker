package edu.rosehulman.classtracker.classview;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class DataSet implements Serializable{
	private static final long serialVersionUID = -8740713891861439323L;
	private HashMap<String, Class> classes;
	
	
	public DataSet()
	{
		this.classes = new HashMap<String, Class>();
	}
	
	public DataSet(Set<Class> classes)
	{
		this.setClassesFromClassSet(classes);
	}
	
	public Collection<Class> getClasses()
	{
		return this.classes.values();
	}
	
	public Set<String> getClassNames()
	{
		return this.classes.keySet();
	}
	
	public void addClass(Class add)
	{
		this.classes.put(add.getName(), add);
	}
	
	public void removeClass(Class remove)
	{
		this.classes.remove(remove.getName());
	}
	
	public void removeClass(String remove)
	{
		this.classes.remove(remove);
	}
	
	private void setClassesFromClassSet(Set<Class> classes)
	{
		this.classes = new HashMap<String, Class>();
		for(Class cl : classes)
		{
			this.classes.put(cl.getName(), cl);
		}
	}
}
