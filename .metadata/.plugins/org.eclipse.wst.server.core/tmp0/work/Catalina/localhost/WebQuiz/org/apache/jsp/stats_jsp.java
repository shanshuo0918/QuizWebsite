/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-03-14 21:51:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import quiz.*;
import java.text.*;
import database.*;

public final class stats_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>Statistics</title>\n");
      out.write("<style>\n");
      out.write("   #firstDiv {float: left;\n");
      out.write("              width: 350px;\n");
      out.write("            font-family: sans-serif;\n");
      out.write("            margin: 10px;\n");
      out.write("   }\n");
      out.write("   #secondDiv {float: left;\n");
      out.write("              width: 350px;\n");
      out.write("            font-family: sans-serif;\n");
      out.write("            margin: 10px;\n");
      out.write("   }\n");
      out.write("   </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<table align=\"left\" style=\"width:100%\" border=1>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<th><a href=\"admin.jsp\"><font size=4 color=red>Announcement</font></a></th>\n");
      out.write("\t\t\t<th><a href=\"admin-account.jsp\"><font size=4 color=red>Account Manager</font></a></th>\n");
      out.write("\t\t\t<th><a href=\"QuizManagement.jsp\"><font size=4 color=red>Quiz Manager</font></a></th>\n");
      out.write("\t\t\t<th><a href=\"stats.jsp\"><font size=4 color=red>Statistics</font></a></th>\n");
      out.write("\t\t</tr>\n");
      out.write("\t</table>\n");
      out.write("\t<br><br>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"firstDiv\">\n");
      out.write("<h4>User Statistics</h4>\n");
 
Connection con = DBConnection.getConnection();
try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as num FROM userinfo");
	if(rs.next()){
		String num = rs.getString("num");
		out.println("<p>Total number of users: " + num + "</p>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}


      out.write("\n");
      out.write("<table style=\"width:80%\">\n");
      out.write("<tr>\n");
      out.write("    <th>Username</th>\n");
      out.write("    <th>#Quiz Take</th> \n");
      out.write("    <th>#Quiz Create</th>\n");
      out.write("  </tr>\n");
 
try {
	Statement stmt = con.createStatement();
	Statement stmtone = con.createStatement();
	Statement stmttwo = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT username FROM userinfo ORDER BY username");
	while(rs.next()) {
		out.println("<tr>");
		String user = rs.getString("username");
		String take;
		String create;
		out.println("<td>"+ user + "</td>");
		ResultSet rsone = stmtone.executeQuery("SELECT COUNT(quizID) AS take FROM quiz_history WHERE username=\"" + user +"\"");
		if(rsone.next()) {
			out.println("<td>"+ rsone.getString("take") + "</td>");
		}else {
			out.println("<td>0</td>");
		}
		ResultSet rstwo = stmttwo.executeQuery("SELECT COUNT(*) AS a FROM quiz_summary WHERE creator=\"" + user +"\"");
		if(rstwo.next()) {
			out.println("<td>" + rstwo.getString("a") + "</td>");
		}else {
			out.println("<td>0</td>");
		}
		out.println("</tr>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}


      out.write("\n");
      out.write("</table>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"secondDiv\">\n");
      out.write("   <h4>Quiz Statistics</h4>\n");
      out.write("   ");
 
try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as numquiz FROM quiz_summary");
	if(rs.next()){
		String numquiz = rs.getString("numquiz");
		out.println("<p>Total number of quizzes: " + numquiz + "</p>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}


      out.write("\n");
      out.write("   <table style=\"width:80%\">\n");
      out.write("   <tr>\n");
      out.write("    <th>Quiz</th>\n");
      out.write("    <th>Times Taken</th> \n");
      out.write("    <th>Rating</th>\n");
      out.write("    <th>Avg Score</th>\n");
      out.write("  </tr>\n");
 
 try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_summary ORDER BY quizName");
	while(rs.next()){
		out.println("<tr>");
		String quiz = rs.getString("quizName");
		String times=rs.getString("numTaken");
		String rate=rs.getString("avgRating");
		String score=rs.getString("avgScore");
		out.println("<td>"+ quiz + "</td>");
		out.println("<td>"+ times + "</td>");
		out.println("<td>"+ rate + "</td>");
		out.println("<td>"+ score + "</td>");
		out.println("</tr>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}


      out.write("\n");
      out.write("</table>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<br><br>\n");
      out.write("<a href=\"homepage.jsp\"><font size=4>Home</font></a>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
