package registerpkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class pwdretrieve
 */
@WebServlet("/pwdretrieve")
public class pwdretrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdretrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email1=request.getParameter("fpwd");
Connection con = null; 
		 Context envContext = null;
		 System.out.print("jjjj");
			try { 
				envContext = new InitialContext();
				  Context initContext  = (Context)envContext.lookup("java:/comp/env");
				  DataSource ds = (DataSource)initContext.lookup("jdbc/MyDataSource");
			// Get a database connection
			 con = ds.getConnection(); 
			
			 } catch(java.lang.Exception e) { 
			 e.printStackTrace(); 
			 System.err.print(e.getClass().getName()); 
			 System.err.println(e.getMessage()); 
			 }
			try{
				String uname1=null;
				 String pwd1=null;			
				String sql = 
						  "select uname,pwd from ganesh where email=?";
							PreparedStatement pst = 
									  con.prepareStatement(sql);
							pst.setString(1, email1);
							 ResultSet rs;
							  rs = pst.executeQuery();
								while(rs.next())
								{
										
										uname1=rs.getString("uname");
										pwd1=rs.getString("pwd");
								}
								
						 
						request.setAttribute("uname", uname1);
							 request.setAttribute("pwd",pwd1);
							 request.setAttribute("email", email1);
							 RequestDispatcher rd=request.getRequestDispatcher("SendForgotMsg");
							   rd.forward(request,response);
							  
				}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
catch(java.lang.Exception e) {
e.printStackTrace();
System.err.print(e.getClass().getName());
System.err.println(e.getMessage());
}



			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
