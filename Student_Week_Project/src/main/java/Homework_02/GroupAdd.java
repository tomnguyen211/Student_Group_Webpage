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

@WebServlet("/GroupAdd")
public class GroupAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/GroupAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * List<GroupEntry> entries = (List<GroupEntry>)
		 * getServletContext().getAttribute("entries_group"); String name =
		 * request.getParameter("a"); entries.add(new GroupEntry(name));
		 * response.sendRedirect("GroupListHW2");
		 */
		
		String name = request.getParameter("a");		
		Connection c = null;
    	try
    	{
    		String url  = "jdbc:mysql://cs3.calstatela.edu/cs3220stu61";
    		String username = "cs3220stu61";
    		String password = "PgzAZxFl92Fv";
    		c = DriverManager.getConnection(url, username, password);
    		PreparedStatement ps = c.prepareStatement("INSERT INTO student_groups (name)" + "VALUES (?);");
    		ps.setString(1, name);
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
    	response.sendRedirect("GroupListHW2");
	}

}
