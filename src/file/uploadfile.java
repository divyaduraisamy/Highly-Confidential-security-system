package file;

import nomad.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class uploadfile
 */
@WebServlet("/uploadfile")
@MultipartConfig
public class uploadfile extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload2";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadfile() {
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
		PrintWriter out=response.getWriter();
		
		 if (!ServletFileUpload.isMultipartContent(request)) {
	            // if not, we stop here
	            return;
	        }
	        // configures some settings
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(THRESHOLD_SIZE);
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	         
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	        upload.setSizeMax(REQUEST_SIZE);
	         
	        // constructs the directory path to store upload file
	        String uploadPath = "C:\\Users\\sapna\\Desktop\\" + UPLOAD_DIRECTORY;
	        // creates the directory if it does not exist
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	         
	        try {
	        	Context envContext = new InitialContext();
				  Context initContext  = (Context)envContext.lookup("java:/comp/env");
				  DataSource ds = (DataSource)initContext.lookup("jdbc/MyDataSource");
			// Get a database connection
			 Connection con = ds.getConnection(); 

	             //parses the request's content to extract file data
	        	 List formItems = upload.parseRequest(request);
	            Iterator iter = formItems.iterator();
	             String a=null;
	            // iterates over form's fields
	             String descrip=null;
	             String label=null;
	             
	             String fileName=null;
	             File storeFile=null;
	            while (iter.hasNext()) {
	            	
	            FileItem item = (FileItem) iter.next();  
	            
                if (item.isFormField()) {
                	String fieldName = item.getFieldName();  
                	 if (fieldName.equals("label")) {  
                         label = item.getString();  
                        
                	 }
                	
                    if (fieldName.equals("descrip")) 
                    {  
                    	descrip=item.getString();
                            
                                              
                                       
                                            
                                       
                    }
                      
                  }  
                    
                                 	
                                      // this item is file upload field  
               if(!item.isFormField())
               {
                                        // this item is regular field  
                    
                          
	            
	                    fileName = new File(item.getName()).getName();
	                    System.out.println(fileName);
	                    String filePath = uploadPath + File.separator + fileName;
	                    storeFile = new File(filePath);
	                     
	                    // saves the file on disk
	                    //item.write(storeFile);
	                    storeFile = new File(filePath);
	                    
	                 a="";
	                 
	                    a=a+item.getString();
	                     String passwordEnc = AESencrp.encrypt(a);
	                     FileOutputStream fo=new FileOutputStream(storeFile,false);
	                     fo.write(passwordEnc.getBytes());
	                    //out.println(a);
	                     
	                    
	                     
                }
	            }
	            
	            Statement stmt=con.createStatement();
	            //boolean generate = stmt.execute("select id from file", stmt.RETURN_GENERATED_KEYS);
	            ResultSet rs = stmt.getGeneratedKeys();
	            ResultSet rs1=stmt.executeQuery("select max(id) from savefile1");
	            System.out.println("ggggg");
	            int id=0;
	            if (rs1.next()) 
	            {
	                id = rs1.getInt(1);
	            }
	    System.out.println(id+"ajhddkasdhash");
	            
               String sql="insert into savefile1(uname,filename,file,descrip,filetype) values(?,?,?,?,?)";
               PreparedStatement pst = 
              		  con.prepareStatement(sql);
               FileInputStream fis=new FileInputStream(storeFile);
               int len=(int)storeFile.length();
              //pst.setInt(1,2);
               pst.setString(1,label);
               pst.setString(2,fileName);
               
               pst.setBinaryStream(3,(InputStream)fis,len);
               pst.setString(4,descrip);
               pst.setString(5,"Image");
                pst.executeUpdate();	      
              RequestDispatcher rd=request.getRequestDispatcher("uploadsuccess.jsp");
			   rd.forward(request,response);
	            
	}catch(Exception e)
	{
		e.printStackTrace();
	}

}


	            
	}        

	

