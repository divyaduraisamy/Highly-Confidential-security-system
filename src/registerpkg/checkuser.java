package registerpkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkuser
 */
@WebServlet("/checkuser")
public class checkuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		if(type.equals("Admin"))
		{
		RequestDispatcher rd=request.getRequestDispatcher("checkadmin");
		   rd.forward(request,response);
		}
		else if(type.equals("Supervisor"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("checksupervisor");
			   rd.forward(request,response);
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("checklogin");
			   rd.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
