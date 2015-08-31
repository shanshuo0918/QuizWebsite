package admin;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import database.DBConnection;

/**
 * Application Lifecycle Listener implementation class QuizListener
 *
 */
@WebListener
public class QuizListener implements ServletContextListener, HttpSessionListener {

    public QuizListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
    	arg0.getSession().setAttribute("username", "guest");
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	Connection con = DBConnection.getConnection();
    	try {
    		if(con != null)
    			con.close();
			System.out.println("connection to DB closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
