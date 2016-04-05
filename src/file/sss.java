package file;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MultipartDataSource;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sss
 */
@WebServlet("/sss")
public class sss extends HttpServlet implements MultipartDataSource {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sss() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see MultipartDataSource#getCount()
     */
    public int getCount() {
        // TODO Auto-generated method stub
			return 0;
    }

	/**
     * @see MultipartDataSource#getBodyPart(int)
     */
    public MimeBodyPart getBodyPart(int arg0) {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see DataSource#getName()
     */
    public String getName() {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see DataSource#getContentType()
     */
    public String getContentType() {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see DataSource#getOutputStream()
     */
    public OutputStream getOutputStream() {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see DataSource#getInputStream()
     */
    public InputStream getInputStream() {
        // TODO Auto-generated method stub
			return null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
