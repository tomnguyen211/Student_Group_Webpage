package Homework_02;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/StudentListHW2")
public class StudentListHW2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentListHW2() {
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
    		ResultSet rs = stmt.executeQuery("select * from student");
    		List<StudentEntry> entries = new ArrayList<StudentEntry>();
    		
    		while(rs.next())
    		{
    			StudentEntry entry = new StudentEntry();
    			entry.setId(rs.getInt("id"));
    			entry.setName(rs.getString("name"));
    			entry.setBirthYear(rs.getInt("birth"));
    			entry.setParentName(rs.getString("parent"));
    			entry.setParentEmail(rs.getString("parent_email"));
    			if(rs.getInt("id_group") != 0)
    			{
    				int id = rs.getInt("id_group");
    				Statement stmt1 = c.createStatement();
    				ResultSet rs_group = stmt1.executeQuery("select * from student_groups");
    				while(rs_group.next())
    				{
    					if(id == rs_group.getInt("id"))
    					{
    						entry.setGroupName(rs_group.getString("name"));
    						break;
    					}
    				}
    			}
    			else
    				entry.setGroupName("");
    			
    			entries.add(entry);
    		} 
    		request.setAttribute( "entries_student", entries );
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
      
		request.getRequestDispatcher("/WEB-INF/StudentListHW2.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
