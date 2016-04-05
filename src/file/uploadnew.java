package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

/**
 * Servlet implementation class uploadnew
 */
@WebServlet("/uploadnew")
@MultipartConfig(location = "C:\\tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 )
 
public class uploadnew extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadnew() {
        super();
        // TODO Auto-generated constructor stub
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
		String label = request.getParameter("label");
        String descrip = request.getParameter("descrip");
        String name=request.getParameter("fileupload");
         System.out.println(label+""+descrip);
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("fileupload");
        String fname=filePart.getName();
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        try {
        	Context envContext = new InitialContext();
			  Context initContext  = (Context)envContext.lookup("java:/comp/env");
			  DataSource ds = (DataSource)initContext.lookup("jdbc/MyDataSource");
		// Get a database connection
			  PrintWriter out=response.getWriter();
		 Connection con = ds.getConnection(); 
		 String sql="insert into uploadfile values(?,?,?,?,?)";
         PreparedStatement pst = 
        		  con.prepareStatement(sql);
         pst.setString(1,label);
         pst.setString(2,fname);
         File fileIn = new File("cloud.pdf");
             // fetches input stream of the upload file for the blob column
         if(inputStream!=null)
         {
             pst.setBinaryStream(3, inputStream,(int)fileIn.length());
         }
             pst.setString(4,descrip);
             pst.setString(5,"Image");
              pst.executeUpdate();
              int row = pst.executeUpdate();
              if (row > 0) {
                  System.out.println("File uploaded and saved into database");
              }
              RequestDispatcher rd=request.getRequestDispatcher("uploadsuccess.jsp");
			   rd.forward(request,response);
        }
         catch(Exception e)
     	{
     		e.printStackTrace();
     	}

             
         

	}

}
