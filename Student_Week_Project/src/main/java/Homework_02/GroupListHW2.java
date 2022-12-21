package Homework_02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class GroupListHW2
 */
@WebServlet("/GroupListHW2")
public class GroupListHW2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupListHW2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);		
		if(session.getAttribute("Index") != null)
			session.removeAttribute("Index");
		
		Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student_groups");
    		List<GroupEntry> entries = new ArrayList<GroupEntry>();
    		
    		while(rs.next())
    		{
    			GroupEntry entry = new GroupEntry();
    			entry.setId(rs.getInt("id"));
    			entry.setGroupNumber(rs.getString("name"));
    			
    			List<StudentEntry> entries_student = new ArrayList<StudentEntry>();
    			Statement stmt1 = c.createStatement();
        		ResultSet rs1 = stmt1.executeQuery("select * from student");
        		while(rs1.next())
        		{
        			if(rs.getInt("id") == rs1.getInt("id_group"))
        			{
        				StudentEntry student = new StudentEntry();
        				student.setId(rs1.getInt("id"));
        				student.setName(rs1.getString("name"));
        				student.setBirthYear(rs1.getInt("birth"));
        				student.setParentName(rs1.getString("parent"));
        				student.setParentEmail(rs1.getString("parent_email"));
        				student.setGroupName(rs.getString("name")); 
        				entries_student.add(student);
        			}       			
        		}
        		entry.setStudentList(entries_student);
        		entries.add(entry);
    		}   		
    		request.setAttribute( "entries_group", entries );
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
    	
		
		request.getRequestDispatcher("/WEB-INF/GroupListHW2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
