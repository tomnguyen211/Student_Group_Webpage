package Homework_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StudentEdit")
public class StudentEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	
		String id = request.getParameter("id");
		
		Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student");
    		StudentEntry entry = new StudentEntry();
    		while(rs.next())
    		{   			   			
    			if(Integer.parseInt(id) == rs.getInt("id"))
    			{
    				entry.setId(rs.getInt("id"));
        			entry.setName(rs.getString("name"));
        			entry.setBirthYear(rs.getInt("birth"));
        			entry.setParentName(rs.getString("parent"));
        			entry.setParentEmail(rs.getString("parent_email"));
        			if(rs.getInt("id_group") != 0)
        			{
        				int id_1 = rs.getInt("id_group");
        				Statement stmt1 = c.createStatement();
        				ResultSet rs_group = stmt1.executeQuery("select * from student_groups");
        				while(rs_group.next())
        				{
        					if(id_1 == rs_group.getInt("id"))
        					{
        						entry.setGroupName(rs_group.getString("name"));
        						break;
        					}
        				}
        			}
        			else
        				entry.setGroupName("");
    			}   			
    		}
    		
    		session.setAttribute("Index", Integer.parseInt(id));
    		request.setAttribute( "a", entry);
    		// Group
    		Statement stmt2 = c.createStatement();
    		ResultSet rs2 = stmt2.executeQuery("select * from student_groups");
    		List<GroupEntry> entries = new ArrayList<GroupEntry>();
    		
    		while(rs2.next())
    		{
    			GroupEntry entry1 = new GroupEntry();
    			entry1.setId(rs2.getInt("id"));
    			entry1.setGroupNumber(rs2.getString("name"));    
    			entries.add(entry1);
    		}    		
    		request.setAttribute( "entries_group", entries);
    	}
    	catch( SQLException e)
    	{
    		throw new ServletException(e);
    	}
    	finally
    	{
    		try
    		{
    			if (c != null) c.close();
    		}
    		catch (SQLException e)
    		{
    			throw new ServletException(e);
    		}
    	}
		
		
		
		
		/*
		 * for(int n = 0; n < entries.size();n++) { if(entries.get(n).getId() ==
		 * Integer.parseInt(id)) { session.setAttribute("Index", n);
		 * request.setAttribute( "a", entries.get(n)); break; }
		 * 
		 * }
		 */
		request.getRequestDispatcher("/WEB-INF/StudentEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * 
		 * List<GroupEntry> entries_group = (List<GroupEntry>)
		 * getServletContext().getAttribute("entries_group"); List<StudentEntry>
		 * entries_student = (List<StudentEntry>)
		 * getServletContext().getAttribute("entries_student");
		 */
        
        
        
        int index = (int) request.getSession().getAttribute("Index"); //id of session
        String name = request.getParameter("b"); //Student name
        int year = Integer.parseInt(request.getParameter("c")); // birth
        String parent_name = request.getParameter("d"); // parent
        String email = request.getParameter("e"); //email
        String group = request.getParameter("f");  //group name
        
        
        
        Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student_groups");
    		PreparedStatement ps = c.prepareStatement("update student set name = ?, birth = ?, parent = ?, parent_email = ?, id_group = ? where id = ?");

    		ps.setString(1, name);
    		ps.setInt(2, year);
    		ps.setString(3, parent_name);
    		ps.setString(4, email);
    		if(group != "")
    		{   			
    			while(rs.next())
    			{
    				if(group.equals(rs.getString("name")))
    					ps.setInt(5, rs.getInt("id"));
    			}
    		}
    		else
    			ps.setInt(5, 0);
    		
    		ps.setInt(6, index);
    		ps.executeUpdate(); 
    	}
    	catch( SQLException e)
    	{
    		throw new ServletException(e);
    	}
    	finally
    	{
    		try
    		{
    			if (c != null) c.close();
    		}
    		catch (SQLException e)
    		{
    			throw new ServletException(e);
    		}
    	}                     
		/*
		 * entries_student.get(index).setName(name);
		 * entries_student.get(index).setBirthYear(year);
		 * entries_student.get(index).setParentName(parent_name);
		 * entries_student.get(index).setParentEmail(email);
		 * 
		 * 
		 * 
		 * if(CompareGroup(group, entries_student.get(index).getGroupName())) { int
		 * indexG = Group_Name(group,entries_group); int indexS = GetStudentIndex(
		 * entries_student.get(index).getId(),entries_group.get(indexG));
		 * 
		 * entries_group.get(indexG).getStudent(indexS).setName(name);
		 * entries_group.get(indexG).getStudent(indexS).setBirthYear(year);
		 * entries_group.get(indexG).getStudent(indexS).setParentName(parent_name);
		 * entries_group.get(indexG).getStudent(indexS).setParentEmail(email); } else {
		 * int indexG_N = Group_Name(group,entries_group);
		 * 
		 * 
		 * if(entries_student.get(index).getGroupName() != "") { int indexG_O =
		 * Group_Name(entries_student.get(index).getGroupName(),entries_group); int
		 * indexO =
		 * GetStudentIndex(entries_student.get(index).getId(),entries_group.get(indexG_O
		 * )); entries_group.get(indexG_O).removeStudent(indexO); out.print(indexG_O);
		 * out.print(indexO); }
		 * 
		 * if(group != "") {
		 * entries_group.get(indexG_N).setStudent(entries_student.get(index));
		 * entries_student.get(index).setGroupName(group); } }
		 */
		 
		  
		  
		  response.sendRedirect("StudentListHW2");
		 
    }
	
	private int Group_Name(String name,List<GroupEntry> entries) throws IOException
	{
		for(int i = 0; i < entries.size();i++)
		{
			if(name.equalsIgnoreCase(entries.get(i).getGroupNumer()))
				return i;
		}
		return -1;
	}
	
	private boolean CompareGroup(String oldGroup, String newGroup)
	{
		if(newGroup.equals(oldGroup))
			return true;
		return false;
	}
	
	private int GetStudentIndex(int id, GroupEntry newGroup)
	{
		for(int n = 0 ; n < newGroup.getStudentList().size(); n++)
		{
			if(newGroup.getStudent(n).getId() == id)
				return n;
		}
		return -1;
	}
}
