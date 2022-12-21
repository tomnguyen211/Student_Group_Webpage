package Homework_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomepageHW2")
public class HomepageHW2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageHW2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * public void init( ServletConfig config ) throws ServletException {
	 * super.init( config );
	 * 
	 * List<GroupEntry> entries_group = new ArrayList<GroupEntry>();
	 * List<StudentEntry> entries_student = new ArrayList<StudentEntry>();
	 * 
	 * entries_group.add(new GroupEntry("Monday")); entries_group.add(new
	 * GroupEntry("Tuesday")); entries_group.add(new GroupEntry("Wednesday"));
	 * 
	 * entries_student.add(new
	 * StudentEntry("Dat",1996,"Nam","tomnguyen2111996@gmail.com"));
	 * entries_student.add(new
	 * StudentEntry("Trang",1996,"Nam","tomnguyen2111996@gmail.com"));
	 * 
	 * 
	 * getServletContext().setAttribute( "entries_group", entries_group);
	 * getServletContext().setAttribute( "entries_student", entries_student); }
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// How you get Session
		HttpSession session = request.getSession(true);		
		if(session.getAttribute("Index") != null)
			session.removeAttribute("Index");
		
		
		// Request Link
		request.getRequestDispatcher("/WEB-INF/HomepageHW2.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
