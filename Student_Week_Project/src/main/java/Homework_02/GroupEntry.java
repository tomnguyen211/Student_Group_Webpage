package Homework_02;

import java.util.ArrayList;
import java.util.List;

public class GroupEntry {
	static int idSeed = 1;

    private int id;

    private String group_name;

    List<StudentEntry> student_entry; 
    
    public GroupEntry()
    {
    	student_entry = new ArrayList<StudentEntry>();
    }
    
    public void setId(int id) {
		this.id = id;
	}

	public GroupEntry(String group_name) {
    	//this.id = idSeed++;
    	this.group_name = group_name;  
    	student_entry = new ArrayList<StudentEntry>();
    }
     
    public int getId()
    {
    	return this.id;
    }
    public String getGroupNumer() {
    	return this.group_name;
    }
    
    public void setGroupNumber(String name)
    {
    	this.group_name = name;
    }
    
    public void setStudent(StudentEntry student)
    {
    	this.student_entry.add(student);
    }
    
    public StudentEntry getStudent(int index)
    {
    	return student_entry.get(index);
    }
    
    public void removeStudent(int index)
    {
    	student_entry.remove(index);
    }
    
    public int getLength() {
    	return student_entry.size();
    }
    
    public List<StudentEntry> getStudentList()
    {
    	return student_entry;
    }
    public void setStudentList(List<StudentEntry> students)
    {
    	student_entry = students;
    }
}
