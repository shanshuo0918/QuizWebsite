/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-03-14 21:39:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quiz.*;
import java.util.*;

public final class SearchByCategory_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("    <link rel=\"icon\" href=\"/favicon.ico\">\n");
      out.write("\n");
      out.write("    <title>Search By Category</title>\n");
      out.write("    <link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <link href=\"./css/style.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"site-wrapper\">\n");
      out.write("      <a href=\"./homepage.jsp\"><div class=\"site-logo\"></div></a>\n");
      out.write("\t  <div class=\"quiz-background\">\n");
      out.write("<h4> Please Select Quiz Category and Press Search!</h4>\n");
      out.write("<form action=\"SearchByCategoryServlet\" method=\"post\">\n");
      out.write("Category: <select name=\"category\" >\n");

	List<String> categories = QuizHelper.categories;
	for (int i = 0; i < categories.size(); i ++) {
		out.println("<option value =\"" + categories.get(i) + "\">" + categories.get(i) + "</option>");
	}

      out.write("\n");
      out.write("</select>\n");
      out.write("<input type=\"submit\" value=\"Search\">\n");
      out.write("</form>\n");
      out.write("<hr>\n");
      out.write("\n");

	if (request.getParameter("category") != null) {
		
		List<MyQuiz> quizList = QuizHelper.searchQuizByCategory(request.getParameter("category"));
		out.println("<table style=\"width:100%\">");
		out.println("<tr>");
		out.println("<th>Quiz Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>Quiz Rating</th>");
		out.println("<th>Quiz Average Score</th>");
		out.println("<th>Quiz Taken Times</th>");
		out.println("</tr>");
		
		for(int i = 0; i < quizList.size(); i++) {
			out.println("<tr>");
			out.println("<td><a href=\"QuizSummaryPage.jsp?ID=" + quizList.get(i).getId() + "\">"+ quizList.get(i).getQuizName() + "</a></td>");
			out.println("<td>" + quizList.get(i).getQuizDescription() + "</td>");
			out.println("<td>" + quizList.get(i).getAvgRating() + "</td>");
			out.println("<td>" + quizList.get(i).getAvgScore() + "/" + quizList.get(i).getTotalScore() + "</td>");
			out.println("<td>" + quizList.get(i).getTakenTimes() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

      out.write("\n");
      out.write("</div>\n");
      out.write("</div>\n");
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
