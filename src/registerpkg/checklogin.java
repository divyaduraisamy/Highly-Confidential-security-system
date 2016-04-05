package registerpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class checklogin
 */
@WebServlet("/checklogin")
public class checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checklogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int flag=0;
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
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
						  "select uname,pwd from ganesh where uname=?";
							PreparedStatement pst = 
									  con.prepareStatement(sql);
							pst.setString(1, uname);
							 ResultSet rs;
							  rs = pst.executeQuery();
								while(rs.next())
								{
										out.println("hi");
										String uname1=rs.getString("uname");
										String pwd1=rs.getString("pwd");
										out.println(uname1);
										out.println(pwd1);
												if(pwd1.equals(pwd) && uname1.equals(uname))
									{                               
									flag=1;
									break;
									}
								}
								
					if(flag==0)
					{
						
						RequestDispatcher rd= request.getRequestDispatcher("loginfail.html");
					    rd.forward(request, response);
						
					}
					
					else
					{
						//HttpSession session = request.getSession(true);
						
						 request.setAttribute("uname", uname);
					
						
						  RequestDispatcher rd=request.getRequestDispatcher("profilepage.jsp");
						   rd.forward(request,response);
		                    
		                
		               
		                						
					}
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
