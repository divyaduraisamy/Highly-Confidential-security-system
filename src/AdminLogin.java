

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			PrintWriter out=response.getWriter();
	String name=request.getParameter("uname");
	String pass=request.getParameter("pwd");
	out.println(name);
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
				  "insert into ulogin values (?,?)";
				  PreparedStatement pst = 
				  con.prepareStatement(sql);
				  out.println("3");
				  pst.setString(1, name);
				  pst.setString(2, pass);
				  pst.executeUpdate();
				  out.println("4");
				  pst.close();
				  con.close();
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
