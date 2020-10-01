package app;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.google.appengine.api.utils.SystemProperty;
import conn.Connections;
import data.Pizza;


@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

	  public void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws IOException {
		  doGet(request, response);
		  
		  
	  }
	  
	  
		
		
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		    response.setContentType("text/html");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out=response.getWriter();
		    
		    String name=request.getParameter("name");
		    String birthyear=request.getParameter("birthyear");
		    String country=request.getParameter("country");
		    
		    
		    
		    ArrayList<Pizza> cloudlist=new ArrayList<>();
			util.HTML.printStart(out);
		    Connection conn=null;
		    if (SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {  
		    	out.println("Production version");
		    	try {
					conn=Connections.getProductionConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    else {    
		    	out.println("Development version");
				try {
					conn=Connections.getDevConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    try {
		    	if (conn!=null) {
					Statement stmt=conn.createStatement();
					ResultSet RS=stmt.executeQuery("select * from cloud");
					
					while (RS.next()) {
						Pizza f=new Pizza();
						f.setId(RS.getInt("id"));
						f.setName(RS.getString("name"));
						f.setBirthyear(RS.getString("birthyear"));
						f.setCountry(RS.getString("country"));
						cloudlist.add(f);
					}
					conn.close();
					util.HTML.printTable(out, cloudlist);
		    	}
		    	else {
		    		out.println("No connection to database!");
		    	}
		    	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			util.HTML.printEnd(out);
	  }
	}