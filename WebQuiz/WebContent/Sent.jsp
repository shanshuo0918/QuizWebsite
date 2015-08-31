<%@ page import="java.math.BigDecimal" %>
<%@ page import="Model.MessageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sent</title>
</head>
<body>
<%
	session = request.getSession();
	String fromuser = (String) session.getAttribute("username");
	
	MessageModel boxInfo = new MessageModel(fromuser); //fromuser
	ArrayList<MessageModel> SentboxList = boxInfo.GetSentboxInfo(fromuser);
	int total = SentboxList.size();
%>

<form action="SentboxServlet" method="post">
    <ul>
        <%
        	if(total!=0){
	            for (MessageModel eachmail : SentboxList) {
	                out.print(eachmail.touser);
	                SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
	        		String s = df.format(eachmail.sent_time);
	
	                if(!eachmail.read) 
	                	out.print(" <a href=" + 
	                	"SentboxServlet?emailID=" +
	                	eachmail.emailID_ + "&from=" + eachmail.fromuser + "&to=" + eachmail.touser + "&readornot=" + eachmail.read + ">" + 
	                	"<b>" + eachmail.title + 
	                	"</b>" + "</a> ");
	                else 
	                	out.print(" <a href=" + 
	                	"SentboxServlet?emailID=" + 
	                	eachmail.emailID_ + "&from=" + eachmail.fromuser + "&to=" + eachmail.touser + "&readornot=" + eachmail.read + ">" + 
	                			"<font color=\"" + "grey\"" + ">" + eachmail.title + "</font>" + "</a> ");
	                out.print(s);
	                
					String combine = eachmail.emailID_ + "," + eachmail.fromuser + "," + eachmail.touser;
	                
	                out.print("<input type=" + "\"radio\"" + " name=" + "\"emailID\" " + "value=" + combine + " />");
	                
	               out.print("<br/>");
	            }
	            out.print(" <input type=submit name=emailID value=delete> <br/>");
        	}
        %>
    </ul>
    Total: <%= total %>
</form>
	<a href="Mail.jsp"><input type="button" value="Go Back"/></a>

</body>
</html>