/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-03-14 21:39:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.math.BigDecimal;
import Model.ChallengeModel;
import Model.FriendListModel;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.text.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import quiz.*;
import java.text.*;
import database.*;

public final class QuizSummaryPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<link rel=\"icon\" href=\"/favicon.ico\">\n");
      out.write("\n");
      out.write("<title>\n");

String quizName = "Test Quiz";
System.out.println("haliluya" + request.getParameter("ID"));


session = request.getSession();
String current_user = (String) session.getAttribute("username");
//System.out.println(current_user);
//ID is sent from quiz_summary page
String QUIZID = request.getParameter("ID");//ID
//System.out.println("Pei-Chen : ID : " + QUIZID);

//QUIZID = "Q1"; //FOR TESTING ONLY

FriendListModel friends = new FriendListModel(current_user); //current_user;
ArrayList<String> friendList = friends.GetFriendList();
int friend_num = friendList.size();	



Connection con = DBConnection.getConnection();
Statement stmt = con.createStatement();
if(request.getParameter("ID")!=null) {
	String query = new String("SELECT quizName FROM quiz_summary WHERE quizID = \""+request.getParameter("ID") + "\"");
	ResultSet rs = stmt.executeQuery(query);
	if(rs.next()) {
		quizName = rs.getString("quizName");
	}
}

if(request.getParameter("ID")==null) QUIZID = "Q1";


      out.write("quizName</title>\n");
      out.write("<link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<link href=\"./css/style.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");

MyQuiz myquiz = new MyQuiz(quizName);
System.out.println("line61");
if (request.getParameter("ID") != null) {
	myquiz = new MyQuiz(request.getParameter("ID"), 1); 
	System.out.println("line64");
}

      out.write('\n');
      out.write('\n');

	//session = request.getSession();
	//String current_user = (String) session.getAttribute("username");
	//System.out.println(current_user);
	//ID is sent from quiz_summary page
	//String QUIZID = request.getParameter("ID");//ID
	//System.out.println("Pei-Chen : ID : " + QUIZID);
	
	//QUIZID = "Q1"; //FOR TESTING ONLY
	
	//FriendListModel friends = new FriendListModel(current_user); //current_user;
	//ArrayList<String> friendList = friends.GetFriendList();
	//int friend_num = friendList.size();	

      out.write("\n");
      out.write("\n");
      out.write("<div class=\"site-wrapper\">\n");
      out.write("      <a href=\"./homepage.jsp\"><div class=\"site-logo\"></div></a>\n");
      out.write("\t  <div class=\"lpanel\">\n");
      out.write("\t\t<div class = \"nav-menu\">\n");
      out.write("\t\t  ");
 String guest="guest";
		  if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"Review.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"profile\">X</span>");
        	  out.println("<span class=\"nav-title\">See reviews</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }
      out.write("\n");
      out.write("          ");
if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"StartQuizServlet?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"message\">X</span>");
        	  out.println("<span class=\"nav-title\">Start</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }
      out.write("\n");
      out.write("          ");
if(!myquiz.isPractice()||!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"PracticeMode.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"quiz\">P</span>");
        	  out.println("<span class=\"nav-title\">Practice</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }
      out.write("\n");
      out.write("            ");
if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li id=\"challenge-b-button\">");
        	  out.println("<span class=\"nav-icon\" id=\"setting\">F</span>");
        	  out.println("<span class=\"nav-title\">Challenge</span>");
        	  out.println("</li>");
            }
          
      out.write("\n");
      out.write("          ");
if(myquiz.getCreator().equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"EditQuiz.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"achieve\">Z</span>");
        	  out.println("<span class=\"nav-title\">Edit</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }
          
      out.write("\n");
      out.write("      </div>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("\t  <div class = \"cpanel\">\n");
      out.write("\t\t<div class = \"popular-quiz cpanel-item cpanel-item-quiz\">\n");
      out.write("\t\t<h2> High Score </h2>\n");
      out.write("\t\t<ol>\n");
      out.write("\t\t\t\n");

for(MyQuiz.QuizEvent q: myquiz.getHighScoreEvents(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}

      out.write("\t\t\t\t\n");
      out.write("\t\t</ol>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class = \"recent-quiz cpanel-item cpanel-item-quiz\">\n");
      out.write("\t\t<h2> High Score Yesterday </h2>\n");
      out.write("\t\t<ol>\n");

for(MyQuiz.QuizEvent q: myquiz.getHighScoreEventsLastDay(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}

      out.write("\t\n");
      out.write("\t\t</ol>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div class = \"b-summary cpanel-item cpanel-item-quiz\">\n");
      out.write("\t\t<h2> Recent Score </h2>\n");
      out.write("\t\t<ol>\n");

for(MyQuiz.QuizEvent q: myquiz.getRecentTakenEvents(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}

      out.write("\t\n");
      out.write("\t\t</ol>\n");
      out.write("\t\t</div>\n");
      out.write("\t<div id = \"challenge-bubble\">\n");
      out.write("\t<form action=\"ChallengingServlet\" method=\"post\">\n");
      out.write("    <ul>\n");
      out.write("        ");

        	if(friend_num!=0){
	            for (String eachfriend : friendList) {
	                
	                String combine = current_user + "," + eachfriend + "," + QUIZID;
	                //System.out.println(combine);
	                out.print(" <input type=" + "\"radio\"" + " name=" + "\"Info\" " + "value=" + combine + " />");
	                out.print("  "+eachfriend);
	                out.print("<br/>");
	            }
	            out.print(" <input type=submit value=Challenge!! class=\"login-submit\"> <br/>");
        	}
        
      out.write("\n");
      out.write("    </ul>\n");
      out.write("    Total Friend Number: ");
      out.print( friend_num );
      out.write("\n");
      out.write("</form>\n");
      out.write("\t</div>\n");
      out.write("\t  </div>\n");
      out.write("\n");
      out.write("\t  <div class = \"tpanel tpanel-quiz\">\n");
      out.write("\t  <ul class = \"quiz-main\">\n");
      out.write("\t\t\t<li class=\"quiz-name\">\n");
      out.write("\t\t\t");
      out.print(myquiz.getQuizName() );
      out.write("\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t");
      out.print(myquiz.getCategory() );
      out.write('\n');

out.println(", ");
for (String tag: myquiz.getTag()) {
	out.println("#" + tag);
}

      out.write("\n");
      out.write("\t\t\t</li>\n");

NumberFormat nf = NumberFormat.getInstance();
nf.setMaximumFractionDigits(0);
nf.setMinimumFractionDigits(0);

      out.write("\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\tRating: ");
      out.print(nf.format(myquiz.getAvgRating()) );
      out.write("/5<span class=\"star\">\n");
      out.write("\t\t\t");

			int rate = (int)Math.ceil(myquiz.getAvgRating());
			if (rate != -1){
				while(rate > 0){
					out.println("J");
					rate = rate-1;
				}
				
			}
			
      out.write("\n");
      out.write("\t\t\t</span>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<ul class = \"graph-table\">\n");
      out.write("\t\t\n");
      out.write("\t\t<li>\n");
      out.write("\t\t<span>Low: ");
      out.print(myquiz.getLowestScore() );
      out.write('/');
      out.print(myquiz.getTotalScore() );
      out.write("</span>\n");
      out.write("\t\t <div class = \"low-graph bar-graph\">\n");
      out.write("\t\t\t<div class=\"inner-left-cap\" id = \"low-left-cap\"></div>\n");
      out.write("\t\t\t<div class=\"inner-left-bar\" id = \"low-left\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-bar\" id = \"low-right\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-cap\" id = \"low-right-cap\"></div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t\t</li>\n");
      out.write("\t\t<li>\n");
      out.write("\t\t<span>Avg: ");
      out.print(nf.format(myquiz.getAvgScore()));
      out.write('/');
      out.print(myquiz.getTotalScore() );
      out.write("</span>\n");
      out.write("\t\t <div class = \"low-graph bar-graph\">\n");
      out.write("\t\t\t<div class=\"inner-left-cap\" id = \"avg-left-cap\"></div>\n");
      out.write("\t\t\t<div class=\"inner-left-bar\" id = \"avg-left\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-bar\" id = \"avg-right\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-cap\" id = \"avg-right-cap\"></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t</li>\n");
      out.write("\t\t<li>\n");
      out.write("\t\t<span>High: ");
      out.print(myquiz.getBestScore() );
      out.write('/');
      out.print(myquiz.getTotalScore() );
      out.write("</span>\n");
      out.write("\t\t <div class = \"low-graph bar-graph\">\n");
      out.write("\t\t\t<div class=\"inner-left-cap\" id = \"high-left-cap\"></div>\n");
      out.write("\t\t\t<div class=\"inner-left-bar\" id = \"high-left\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-bar\" id = \"high-right\"></div>\n");
      out.write("\t\t\t<div class=\"inner-right-cap\" id = \"high-right-cap\"></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t\n");
      out.write("\t\t<p class = \"description\">\n");
      out.write("\t\t\t");
      out.print(myquiz.getQuizDescription() );
      out.write("\n");
      out.write("\t\t</p>\n");
      out.write("\t\t\n");
      out.write("\t  </div>\n");
      out.write("\t  \n");
      out.write("\t  \n");
      out.write("\t\n");
      out.write("\t  \n");
      out.write("\t  \n");
      out.write("\t  </div>\n");
      out.write("\t  \n");
      out.write("\t  \n");
      out.write("\n");
      out.write("<!-- Bootstrap core JavaScript\n");
      out.write("      ================================================== -->\n");
      out.write("      <!-- Placed at the end of the document so the pages load faster  -->\n");
      out.write("      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n");
      out.write("      <script>\n");
      out.write("      $(document).ready(function(){\n");
      out.write("\t\t\tvar total = ");
      out.print(myquiz.getTotalScore());
      out.write(";\n");
      out.write("\t\t\tvar low = ");
      out.print(myquiz.getLowestScore());
      out.write(";\n");
      out.write("\t\t\tvar avg = ");
      out.print(nf.format(myquiz.getAvgScore()));
      out.write(";\n");
      out.write("\t\t\tvar high = ");
      out.print(myquiz.getBestScore());
      out.write(";\n");
      out.write("\t\t\tvar lowp = low/total*100;\n");
      out.write("\t\t\tvar avgp = avg/total*100;\n");
      out.write("\t\t\tvar highp = high/total*100;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tdocument.getElementById(\"low-left\").style.width = lowp*0.98+\"%\";\n");
      out.write("\t\t\tdocument.getElementById(\"low-right\").style.width = (100-lowp)*0.98+\"%\";\n");
      out.write("\t\t\tif(low==0){\n");
      out.write("\t\t\t\tdocument.getElementById(\"low-left-cap\").style.background = \"red\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(low==total){\n");
      out.write("\t\t\t\tdocument.getElementById(\"low-right-cap\").style.background = \"orange\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tdocument.getElementById(\"avg-left\").style.width = avgp*0.98+\"%\";\n");
      out.write("\t\t\tdocument.getElementById(\"avg-right\").style.width = (100-avgp)*0.98+\"%\";\n");
      out.write("\t\t\tif(avg==0){\n");
      out.write("\t\t\t\tdocument.getElementById(\"avg-left-cap\").style.background = \"red\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(avg==total){\n");
      out.write("\t\t\t\tdocument.getElementById(\"avg-right-cap\").style.background = \"orange\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tdocument.getElementById(\"high-left\").style.width = highp*0.98+\"%\";\n");
      out.write("\t\t\tdocument.getElementById(\"high-right\").style.width = (100-highp)*0.98+\"%\";\n");
      out.write("\t\t\tif(high==0){\n");
      out.write("\t\t\t\tdocument.getElementById(\"high-left-cap\").style.background = \"red\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(high==total){\n");
      out.write("\t\t\t\tdocument.getElementById(\"high-right-cap\").style.background = \"orange\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t$(\"#challenge-b-button\").click(function() {\n");
      out.write("\t  \t\t\t$(\"#challenge-bubble\").toggle();\n");
      out.write("\t  \t\t\t$(\".cpanel-item\").toggle();\n");
      out.write("\t  \t\t});\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("      </script>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
