package supervisor_reg;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class checksupervisor
 */
@WebServlet("/checksupervisor")
public class checksupervisor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checksupervisor() {
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
		String eid=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		Connection con = null; 
		 Context envContext = null;
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
							String sql = 
						  "select uname,pwd from supervisor where uname=?";
							PreparedStatement pst = 
									  con.prepareStatement(sql);
							pst.setString(1, eid);
							 ResultSet rs;
							  rs = pst.executeQuery();
							  System.out.println("hi");
								while(rs.next())
								{
										out.println("hi");
										String eid1=rs.getString("uname");
										String pwd1=rs.getString("pwd");
										out.println(eid1);
										out.println(pwd1);
												if(pwd1.equals(pwd) && eid1.equals(eid))
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
						
						 request.setAttribute("eid", eid);
					
						
						  RequestDispatcher rd=request.getRequestDispatcher("supervisor_profile.jsp");
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
