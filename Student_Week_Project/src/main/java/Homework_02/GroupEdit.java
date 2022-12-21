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
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GroupEdit
 */
@WebServlet("/GroupEdit")
public class GroupEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * 
		 * HttpSession session = request.getSession(false); List<GroupEntry> entries =
		 * (List<GroupEntry>) getServletContext().getAttribute("entries_group");
		 */	
		HttpSession session = request.getSession(false);
		
		String id = request.getParameter("id");	// This is how you get the values from Id Link
		
		Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student_groups");
    		GroupEntry entry = new GroupEntry();
    		while(rs.next())
    		{   			   			
    			if(Integer.parseInt(id) == rs.getInt("id"))
    			{
    				entry.setId(rs.getInt("id"));
        			entry.setGroupNumber(rs.getString("name"));
        			List<StudentEntry> entries_student = new ArrayList<StudentEntry>();
        			Statement stmt1 = c.createStatement();
    				ResultSet rs_student= stmt1.executeQuery("select * from student");
    				while(rs_student.next())
    				{
    					if(rs_student.getInt("id_group") == rs.getInt("id"))
    					{
    						StudentEntry student = new StudentEntry();
    						student.setId(rs_student.getInt("id"));
    						student.setName(rs_student.getString("name"));
    						student.setBirthYear(rs_student.getInt("birth"));
    						student.setParentName(rs_student.getString("parent"));
    						student.setParentEmail(rs_student.getString("parent_email"));
    						student.setGroupName(rs.getString("name"));
    						entries_student.add(student);
    					}
    				}
    				entry.setStudentList(entries_student); 
    				break;
    			}   			
    		} 
    		
    		session.setAttribute("Index", Integer.parseInt(id));
    		request.setAttribute( "a", entry);
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
		request.getRequestDispatcher("/WEB-INF/GroupEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		List<GroupEntry> entries_group = (List<GroupEntry>) getServletContext().getAttribute("entries_group");
        List<StudentEntry> entries_student = (List<StudentEntry>) getServletContext().getAttribute("entries_student");*/
        
        String[] object = request.getParameterValues("values");
        
        int index = (int) request.getSession().getAttribute("Index"); //id of session

        Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		
    		PreparedStatement ps = c.prepareStatement("update student_groups set name = ? where id = ?");
    		ps.setString(1, object[0]);
    		ps.setInt(2, index);
    		ps.executeUpdate();
    		
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student");
    		while(rs.next())
    		{   
    			for(int n = 1; n < object.length; n++)
    			{
    				if(rs.getInt("id") == Integer.parseInt(object[n]))
    				{
    					PreparedStatement ps1 = c.prepareStatement("update student set id_group = 0 where id = ?");
    					ps1.setInt(1, rs.getInt("id"));
    					ps1.executeUpdate();
    					break;
    				}
    			}
    		}
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
        
        
        
        
       /* for(int n = 0; n < object.length;n++)
        {
        	out.println(object[n]);
        }
		
		  int index = (int)request.getSession().getAttribute("Index");
		  
		  //Remove Student
		  for(int n = 1;n < object.length;n++)
		  {
			  entries_group.get(index).removeStudent(Group_Student(Integer.parseInt(object[n]),
					  entries_group.get(index).getStudentList()));
			  
			 for(int i = 0; i < entries_student.size();i++)
			 {
				 if(entries_student.get(i).getId() == Integer.parseInt(object[n]))
						 entries_student.get(i).setGroupName(""); 
			 }
		  }
		  
		  
		  //Change Group Name		 
		  entries_group.get(index).setGroupNumber(object[0]); // Group List
		  ChangeGroupName_Student(entries_student,entries_group.get(index).getStudentList(),object[0]);*/

		  response.sendRedirect("GroupListHW2");
		 
        
	}
	// Change all Group name in StudentList
	private void ChangeGroupName_Student(List<StudentEntry> studentList, List<StudentEntry> studentList_group,String name)
	{
		for(int n = 0; n < studentList_group.size();n++)
		{
			for(int i = 0;i < studentList.size();i++)
			{
				if(studentList_group.get(n).getId() == studentList.get(i).getId())
					studentList.get(i).setGroupName(name);
			}
		}
	}
	
	private int Group_Student(int index,List<StudentEntry> entries) throws IOException
	{
		for(int i = 0; i < entries.size();i++)
		{
			if(entries.get(i).getId() == index)
				return i;
		}
		return -1;
	}

}
