package supervisor_reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


/**
 * Servlet implementation class regis
 */
@WebServlet("/regis")
public class regis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String eid=request.getParameter("eid");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String mobno=request.getParameter("mobno");
		Connection con = null; 
		out.println("1");
		 Context envContext = null;
		try { 
			envContext = new InitialContext();
			  Context initContext  = (Context)envContext.lookup("java:/comp/env");
			  DataSource ds = (DataSource)initContext.lookup("jdbc/MyDataSource");
		// Get a database connection
		 con = ds.getConnection(); 
		 out.println("2");
		 } catch(java.lang.Exception e) { 
		 e.printStackTrace(); 
		 System.err.print(e.getClass().getName()); 
		 System.err.println(e.getMessage()); 
		 }
		try{
						String sql = 
					  "insert into supervisor values (?,?,?,?,?,?,?)";
					  PreparedStatement pst = 
					  con.prepareStatement(sql);
					  out.println("3");
					  pst.setString(1, title);
					  out.println("a");
					  pst.setString(2, fname);
					  out.println("b");
					  pst.setString(3, lname);
					  out.println("c");
					  pst.setString(4, eid);
					  out.println("d");
					  pst.setString(5, pwd);
					  out.println("e");
					  pst.setString(6, email);
					  out.println("f");
					  pst.setString(7, mobno);
					  out.println("g");
					  pst.executeUpdate();
					  out.println("4");
					  pst.close();
					  con.close();
					 request.setAttribute("email", email);
					 request.setAttribute("pwd",pwd);
					  RequestDispatcher rd=request.getRequestDispatcher("SendMail");
					   rd.forward(request,response);
					  
		}
		catch(SQLException e){
			  out.println("SQLException caught: " 
			  + e.getMessage());
			  }
			  catch (Exception e){
			  out.println(e);
			  }


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
