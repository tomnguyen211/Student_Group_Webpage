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

@WebServlet("/StudentAdd")
public class StudentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/StudentAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * List<GroupEntry> entries_group = (List<GroupEntry>)
		 * getServletContext().getAttribute("entries_group"); List<StudentEntry>
		 * entries_student = (List<StudentEntry>)
		 * getServletContext().getAttribute("entries_student");
		 */
        
        String name = request.getParameter("b"); // Student Name
        int year = Integer.parseInt(request.getParameter("c")); // birthyear
        String parent_name = request.getParameter("d"); // parent name
        String email = request.getParameter("e"); // email
        String group = request.getParameter("f"); // groupname
        
		/*
		 * StudentEntry newStudent = new StudentEntry(name,year,parent_name,email);
		 * entries_student.add(newStudent);
		 */
		/*
		 * if(group != "") { entries_group.get(Integer.parseInt(group) -
		 * 1).setStudent(newStudent); entries_student.get(entries_student.size() -
		 * 1).setGroupName(entries_group.get(Integer.parseInt(group) -
		 * 1).getGroupNumer()); }
		 */
        
        Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from student_groups");
    		PreparedStatement ps = c.prepareStatement("INSERT INTO student (name,birth,parent,parent_email,id_group)" + "VALUES (?,?,?,?,?);");
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
    		ps.executeUpdate(); // To execute the querry  		
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
       
        response.sendRedirect("StudentListHW2");
	}
}
