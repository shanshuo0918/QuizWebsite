package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.FriendListModel;
import Model.FriendRequestModel;

/**
 * Servlet implementation class FriendListUpdateServlet
 */
@WebServlet("/FriendListUpdateServlet")
public class FriendListUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendListUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String getuser(String input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
    }
    private String getrequesteduser(String input, String username){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = username.length()+1; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
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
		String ok = request.getParameter("ok");
		String combine = request.getParameter("Request_Info");
		
		
		System.out.println(combine);
		
		if(combine!=null){
			
			String receiver = getuser(combine);
			String user_name = getrequesteduser(combine, receiver);
			
			System.out.println("PeiChen: HERE"+receiver);
			System.out.println("PeiChen: HERE"+user_name);
			
			
			if(ok.equals("accept")){
				FriendListModel adding_1 = new FriendListModel(receiver);
				adding_1.AddFriend(user_name);
				
				FriendListModel adding_2 = new FriendListModel(user_name);
				adding_2.AddFriend(receiver);
				
				FriendRequestModel delete_request = new FriendRequestModel(user_name, receiver);
				delete_request.DeleteFriendRequest();
				
				//System.out.println("PeiChen: HERE");
				//forward to MyPageServlet
				String desti = "MyPageServlet?id=" + receiver;
				response.sendRedirect(desti);
				
			}
			else if(ok.equals("ignore")){
				FriendRequestModel delete_request = new FriendRequestModel(user_name, receiver);
				delete_request.DeleteFriendRequest();
				//forward to MyPageServlet
				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
		        rd.forward(request,response);
			}

		}
		else if(combine==null){
			//
			//
			//
		}
	}
}
