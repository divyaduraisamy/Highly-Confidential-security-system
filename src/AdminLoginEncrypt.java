

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nomad.AESencrp;




/**
 * Servlet implementation class AdminLoginEncrypt
 */
@WebServlet("/AdminLoginEncrypt")
public class AdminLoginEncrypt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginEncrypt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String pwd=request.getParameter("pwd");
		String passwordEnc;
		String passwordDec;
		try {
			passwordEnc = AESencrp.encrypt(pwd);
			passwordDec = AESencrp.decrypt(passwordEnc);
			out.println("Plain Text : " + pwd);
	        out.println("Encrypted Text : " + passwordEnc);
	        out.println("Decrypted Text : " + passwordDec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
