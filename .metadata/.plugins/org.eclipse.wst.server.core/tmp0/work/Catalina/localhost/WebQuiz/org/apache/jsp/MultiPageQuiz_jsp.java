/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-03-14 21:39:22 UTC
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

public final class MultiPageQuiz_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"en\">\n");

	String quizID = request.getParameter("ID");
	MyQuiz quiz = new MyQuiz(quizID, 1);
	List<Question> questionList = (List<Question>)session.getAttribute("questionList");
	int currentIndex = (Integer)session.getAttribute("currentIndex");
	Question question = questionList.get(currentIndex);

      out.write("\n");
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("    <link rel=\"icon\" href=\"/favicon.ico\">\n");
      out.write("\n");
      out.write("    <title>Quiz Now</title>\n");
      out.write("    <link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <link href=\"./css/style.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div class=\"site-wrapper\">\n");
      out.write("      <a href=\"./homepage.jsp\"><div class=\"site-logo\"></div></a>\n");
      out.write("\t  <div class=\"quiz-background\">\n");

	if (question.getTimeLimit() != 0) {
		out.println("<form name=\"counter\" id = \"timer\">This is a timed question! <br> <input type=\"text\" id=\"time\" name=\"d2\">seconds</form>");
	}

      out.write("\n");
      out.write("\n");
      out.write("<form action=\"MultiPageQuizServlet\" method=\"post\">\n");

	out.println(question.doQuizPage());

	if (currentIndex == questionList.size() - 1) {
		out.println("Almost there! This is your last question!");
	}
	

      out.write("\n");
      out.write("<input name=\"quizID\" type=\"hidden\" value=\"");
      out.print( quiz.getId() );
      out.write("\">\n");
      out.write("<input type = \"submit\" value = \"Submit Answer\" id=\"submit-test\">\n");
      out.write("</form>\n");
      out.write("<hr>\n");

 	if (quiz.isCorrection()) {
 		out.println("<h4> Your Score here: </h4>");
 		out.println("<table style=\"width:100%\">");
 		out.println("<tr>");
 		out.println("<th>Question Index</th>");
 		out.println("<th>Your Answer</th>");
 		out.println("<th>Correct Answer</th>");
 		out.println("<th>Your Score</th>");
 		out.println("</tr>");
 		
 		List<String> userAnswer = (List<String>)session.getAttribute("userAnswer");
		List<String> correctAnswer = (List<String>)session.getAttribute("correctAnswer");
		List<String> userScoreList = (List<String>)session.getAttribute("userScoreList");
		
		for (int i = 1; i <= userAnswer.size(); i++) {
 				out.println("<tr>");
 				out.println("<td>" + i + "</td>");
 				out.println("<td>" + userAnswer.get(i-1) + "</td>");
 				out.println("<td>" + correctAnswer.get(i-1) + "</td>");
 				out.println("<td>" + userScoreList.get(i-1) + "</td>");
 				out.println("</tr>");
 			}	
 		out.println("</table>");
 	}

      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- Bootstrap core JavaScript\n");
      out.write("      ================================================== -->\n");
      out.write("      <!-- Placed at the end of the document so the pages load faster  -->\n");
      out.write("      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n");
      out.write("      <script>\n");
      out.write("\t  $(function() {\n");
      out.write("\t\tvar height = $(\".match-left\").height();\n");
      out.write("\t\tvar canvas = $(\"#myCanvas\");\n");
      out.write("\t\tcanvas.height(height);\n");
      out.write("\t\tvar c = canvas[0];\n");
      out.write("\t\tvar ctx= c.getContext(\"2d\");\n");
      out.write("\t\tctx.canvas.width = 500;\n");
      out.write("\t\tctx.canvas.height = height;\n");
      out.write("\t\t\t\n");
      out.write("\t\tvar leftSelected = \"\", rightSelected = \"\";\n");
      out.write("\t\t\n");
      out.write("\t\tvar drawLine = function() {\t\t\t\n");
      out.write("\t\t\tvar leftN = leftSelected[1], rightN = rightSelected[1];\n");
      out.write("\t\t\tvar leftHeight = 25 + 67 * (leftN - 1);\n");
      out.write("\t\t\tvar rightHeight = 25 + 67 * (rightN - 1);\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tctx.beginPath();\n");
      out.write("\t\t\tctx.moveTo(0,leftHeight);\n");
      out.write("\t\t\tctx.lineTo(500,rightHeight);\n");
      out.write("\t\t\tctx.stroke();\n");
      out.write("\t\t\tconsole.log(leftHeight, rightHeight);\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar leftInv = document.getElementById(leftSelected+'inv');\n");
      out.write("\t\t\tvar rightInv = document.getElementById(rightSelected+'inv');\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tleftInv.value = rightSelected;\n");
      out.write("\t\t\trightInv.value = leftSelected;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tconsole.log(leftInv);\n");
      out.write("\t\t\tleftSelected = '';\n");
      out.write("\t\t\trightSelected = '';\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t$(\".left-op\").click(function(e) {\n");
      out.write("\t\t\tvar item = $(e.target);\n");
      out.write("\t\t\tif(document.getElementById(item.attr('id')+'inv').value!=\"\"){\n");
      out.write("\t\t\t\talert(\"already selected\");\n");
      out.write("\t\t\t\treturn;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tleftSelected = item.attr('id');\n");
      out.write("\t\t\tif (leftSelected != '' && rightSelected != '') {\n");
      out.write("\t\t\t\tdrawLine();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$(\".right-op\").click(function(e) {\n");
      out.write("\t\t\tvar item = $(e.target);\n");
      out.write("\t\t\tif(document.getElementById(item.attr('id')+'inv').value!=\"\"){\n");
      out.write("\t\t\t\talert(\"already selected\");\n");
      out.write("\t\t\t\treturn;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\trightSelected = item.attr('id');\n");
      out.write("\t\t\tif (leftSelected != '' && rightSelected != '') {\n");
      out.write("\t\t\t\tdrawLine();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t\t$(\"#clear-but\").click(function() {\n");
      out.write("\t\t\tctx.clearRect(0,0,500,height);\n");
      out.write("\t\t\tvar allInv = document.getElementsByClassName(\"inv\");\n");
      out.write("\t\t\tfor (i=0;i<allInv.length;i++){\n");
      out.write("\t\t\t\tallInv[i].value = \"\";\n");
      out.write("\t\t\t}\n");
      out.write("\t\t})\n");
      out.write("\t\t\n");
      out.write("\t  });\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t  var milisec=0;\n");
      out.write("\t  var seconds= ");
      out.print(question.getTimeLimit() );
      out.write("; \n");
      out.write("\t  var boo = false;\n");
      out.write("\t  document.counter.d2.value='");
      out.print(question.getTimeLimit() );
      out.write("' ;\n");
      out.write("\n");
      out.write("\tfunction display(){ \n");
      out.write("\t  if (milisec<=0){ \n");
      out.write("\t     milisec=9; \n");
      out.write("\t     seconds-=1;\n");
      out.write("\t  } \n");
      out.write("\t  if (seconds<=-1){ \n");
      out.write("\t     milisec=0;\n");
      out.write("\t     seconds+=1 ;\n");
      out.write("\t \tif(boo==false){\n");
      out.write("\t \t\t$(\"#submit-test\").click();\n");
      out.write("\t \t\tboo = true;\n");
      out.write("\t \t}\n");
      out.write("\t  } \n");
      out.write("\t  if (seconds==4){\n");
      out.write("\t\t\tdocument.getElementById(\"time\").style.color = \"red\";\n");
      out.write("\t}\n");
      out.write("\t     if(boo==false){\n");
      out.write("\t     milisec-=1 \n");
      out.write("\t     }\n");
      out.write("\t     document.counter.d2.value=seconds+\".\"+milisec ;\n");
      out.write("\t     setTimeout(\"display()\",100);\n");
      out.write("\t } \n");
      out.write("\t display();\n");
      out.write("\t \n");
      out.write("\t  /*$(document).ready(function(){\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\tvar elem = document.getElementById(\"test-text\");\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\tvar id = \"#L1\";\n");
      out.write("\t\tvar position = getPosition(id);\n");
      out.write("\t\t\n");
      out.write("\t\tvar myC = document.getElementById(\"myCanvas\");\n");
      out.write("\t\tmyC.height = getPosition(\"#L1\").mY-getPosition(\"#L2\").mY;\n");
      out.write("\t\tmyC.width = getPosition(\"#R1\").mX-getPosition(\"#L1\").mX;\n");
      out.write("\t\tmyC.position = \"absolute\";\n");
      out.write("\t\tconsole.log(myC);\n");
      out.write("\t\tmyC.left = getPosition(\"#L1\").mX + \"px\";\n");
      out.write("\t\tmyC.top = getPosition(\"#L1\").mY + \"px\";\n");
      out.write("\t\t\n");
      out.write("\t\talert(\"The image is located at: \" + myC.height +\"  \"+ myC.width +\"  \"+ myC.left +\"  \"+ myC.top);\n");
      out.write("\t\t\n");
      out.write("\t\t$(\".l1\").click(function(){\n");
      out.write("\t\t\t\t$(\".l2\").toggle();\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\tvar ctx=myC.getContext(\"2d\");\n");
      out.write("\t\t\t\tctx.beginPath();\n");
      out.write("\t\t\t\tctx.moveTo(0,0);\n");
      out.write("\t\t\t\tctx.lineTo(100,100);\n");
      out.write("\t\t\t\tctx.stroke();\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("\tfunction getPosition(id) {\n");
      out.write("\t\t\tvar element = document.getElementById(id.substring(1));\n");
      out.write("\t\t\tvar xPosition = 0;\n");
      out.write("\t\t\tvar yPosition = 0;\n");
      out.write("\t\t\tvar xWidth = 0;\n");
      out.write("\t\t\tvar yHeight = 0;\n");
      out.write("\t\t\tvar matchX = 0;\n");
      out.write("\t\t\tvar matchY = 0;\n");
      out.write("  \n");
      out.write("\t\t\t\n");
      out.write("\t\t\twhile(element) {\n");
      out.write("\t\t\t\txPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);\n");
      out.write("\t\t\t\tyPosition += (element.offsetTop - element.scrollTop + element.clientTop);\n");
      out.write("\t\t\t\txWidth = $(id).width();\n");
      out.write("\t\t\t\tyHeight = $(id).height();\n");
      out.write("\t\t\t\telement = element.offsetParent;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tmatchX = xPosition;\n");
      out.write("\t\t\tmatchY = yPosition+yHeight/2;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tif(id.substring(1,2)==\"L\"){\n");
      out.write("\t\t\t\tmatchX = xPosition+xWidth;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\treturn { x: xPosition, y: yPosition, width:xWidth, height:yHeight, mX: matchX, mY: matchY};\n");
      out.write("\t\t\t\n");
      out.write("\t\t}*/\n");
      out.write("      </script>\n");
      out.write("      \n");
      out.write("      \n");
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
