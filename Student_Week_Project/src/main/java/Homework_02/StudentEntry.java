package Homework_02;

public class StudentEntry {
	static int idSeed = 1;

    private int id;
    
    private String name;
    
    private int birth_year;
    
    private String parent_name;
    
    private String parent_email;
    
    private String group_name;
    
    public StudentEntry()
    {
    	this.group_name = "";
    }
    
    public StudentEntry(String name, int birth_year, String parent_name, String parent_email )
    {
    	//this.id = idSeed++;
    	this.name = name;
    	this.birth_year = birth_year;
    	this.parent_name = parent_name;
    	this.parent_email = parent_email;
    	this.group_name = "";
    }
    
    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public int getBirthYear()
    {
    	return this.birth_year;
    }
    
    public void setBirthYear(int year)
    {
    	this.birth_year = year;
    }
    
    public String getParentName()
    {
    	return this.parent_name;
    }
    
    public void setParentName(String name)
    {
    	this.parent_name = name;
    }
    
    public String getParentEmail()
    {
    	return this.parent_email;
    }
    
    public void setParentEmail(String email)
    {
    	this.parent_email = email;
    }
    
    public void setGroupName(String name)
    {
    	this.group_name = name;
    }
    
    public String getGroupName()
    {
    	return this.group_name;
    }
}
