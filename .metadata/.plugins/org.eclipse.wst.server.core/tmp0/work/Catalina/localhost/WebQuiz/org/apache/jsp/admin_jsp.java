/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-03-14 21:49:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import admin.*;
import java.util.*;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
ArrayList<Announce> announces = Announce.getRecentAnnounce(-1);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=UTF-8 />\r\n");
      out.write("\t<title>Admin</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<table align=\"left\" style=\"width:100%\" border=1>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th><a href=\"admin.jsp\"><font size=4 color=red>Announcement</font></a></th>\r\n");
      out.write("\t\t\t<th><a href=\"admin-account.jsp\"><font size=4 color=red>Account Manager</font></a></th>\r\n");
      out.write("\t\t\t<th><a href=\"QuizManagement.jsp\"><font size=4 color=red>Quiz Manager</font></a></th>\r\n");
      out.write("\t\t\t<th><a href=\"stats.jsp\"><font size=4 color=red>Statistics</font></a></th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<br><br>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<form method=post action=\"DelAnnServlet\">\r\n");
      out.write("\t<table align=\"left\" style=\"width:45%\">\r\n");
      out.write("\t\t<caption>Delete Announcement</caption>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t<th>Announcement</th>\r\n");
      out.write("\t\t<th>Select</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
 
		for(int i = 0; i < announces.size();i++ ){
			out.println("<tr>");
			out.println("<td align=center><textarea rows=1 cols=50 readonly>"+announces.get(i).content+"</textarea></td>");
			out.println("<td align=center><input type=checkbox name=aBox value=\""+announces.get(i).datetime+"\"></textarea></td>");
			out.println("</tr>");
		}
		
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t<td></td>\r\n");
      out.write("\t\t<td align=right><input type=\"submit\" value=\"Delete\"/></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t\r\n");
      out.write("\t<form method=post action=\"MakeAnnServlet\">\r\n");
      out.write("\t<table align=\"right\" style=\"width:45%\">\r\n");
      out.write("\t\t<caption>Make Announcement</caption>\r\n");
      out.write("\t\t<tr><td align=center><input type=text name=content style=\"width:300px; height:300px;\"/></td></tr>\r\n");
      out.write("\t\t<tr><td align=center><input type=\"submit\" value=\"Announce\"/></td></tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t\r\n");
      out.write("\t<br><br>\r\n");
      out.write("\t<a href=\"homepage.jsp\"><font size=4>Home</font></a>\r\n");
      out.write("</body>\r\n");
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