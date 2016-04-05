package nomad;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class videoe
 */
@WebServlet("/videoe")
public class videoe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public videoe() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    	void videoenc(String name) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException
    	{
    		
    	Cipher cipher=Cipher.getInstance("AES");
    	KeyGenerator keygen=KeyGenerator.getInstance("AES");
    	Key key=keygen.generateKey();
    	cipher.init(Cipher.ENCRYPT_MODE,key);
    	CipherInputStream cin=new CipherInputStream(new FileInputStream(new File(name)),cipher);;
    	System.out.println("1");
    	FileOutputStream fos=new FileOutputStream(new File("sap8.mp4"));
    	System.out.println("1");
    	int i;
    	while((i=cin.read())!=-1){
    	fos.write(i);
    	System.out.println("2");
    	}
    	System.out.println("2");
    	
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String f=request.getParameter("fileupload");
		System.out.println(f);
		try {
			videoenc(f);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("videomenu.jsp");
		   rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
