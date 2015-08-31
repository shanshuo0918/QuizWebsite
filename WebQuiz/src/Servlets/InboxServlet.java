package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import Model.MessageModel;

import java.sql.*;

/**
 * Servlet implementation class InboxServlet
 */
@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InboxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String getID(String input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
    }
    private String getfrom(String input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 18; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
    }
    private String getto(String input){
    	String from = getfrom(input);
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 18+from.length()+1; i < input.length(); i++){
	        final char c = input.charAt(i);
	        sb.append(c);
	    }
	    return sb.toString();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = request.getParameter("emailID");
		String from_ = request.getParameter("from");
		String to_ = request.getParameter("to");
		String readornot = request.getParameter("readornot");
		
		//System.out.println(ID);
		//System.out.println(from_);
		//System.out.println(to_);
		//System.out.println(readornot);
		
		MessageModel read_email = new MessageModel(ID, from_ , to_);
		
		if(readornot.equals("false")) read_email.Read_Ornot();

		request.setAttribute("CONTENT", read_email.GetContent_inbox());
		request.setAttribute("Sender", from_);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("Content.jsp");
        rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String combine = request.getParameter("emailID");
		
		if(!combine.equals("delete")){
			String ID = getID(combine);
			String from_ = getfrom(combine);
			String to_ = getto(combine);
			
			MessageModel delete_email = new MessageModel(ID, from_ , to_);
				
			delete_email.DeleteMail_inbox();
		}
		RequestDispatcher rd = request.getRequestDispatcher("Mail.jsp");
        rd.forward(request,response);
	}

}
