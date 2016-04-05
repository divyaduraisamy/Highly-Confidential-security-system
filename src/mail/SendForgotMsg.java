package mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Servlet implementation class SendForgotMsg
 */
@WebServlet("/SendForgotMsg")
public class SendForgotMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendForgotMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String username = "sapnajayavel@gmail.com";
		final String password = "saibaba23";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 String uname=(String)request.getAttribute("uname");
 String pwd=(String)request.getAttribute("pwd"); 
 String email=(String)request.getAttribute("email");
 
 try {
			 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sapnajayavel@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject("Forgot Password");
			message.setText("Your details:\nUsername:"+uname+"\nPassword:"+pwd);
 
			Transport.send(message);
 
			
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		RequestDispatcher rd= request.getRequestDispatcher("home.html");
	
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
