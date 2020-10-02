package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.utils.SystemProperty;

import conn.Connections;
import data.Topping;

@Path("/db")

public class DatabaseFetcher {
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/getAllToppings")
	    public ArrayList<Topping> getToppings() {
	    	String sql = "select * from fillings;";
	    	ResultSet RS = null;
	    	ArrayList<Topping> list = new ArrayList<>();
	    	
			Connection conn = null;
		    if (SystemProperty.environment.value()==SystemProperty.Environment.Value.Production) {  
		    	try {
					conn=Connections.getProductionConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    Statement stmt;
		    try {
		    	stmt = conn.createStatement();
		    	RS=stmt.executeQuery(sql);
		    	while (RS.next()) {
		    		Topping t = new Topping();
		    		t.setId(RS.getInt("id"));
		    		t.setTopping(RS.getString("fillings"));
		    		t.setPrice(RS.getDouble("price"));
		    		list.add(t);
		    	}
		    } catch (SQLException e) {
		    	
		    }
		    
		    if (conn!=null) {
		    	try {
		    		conn.close();
		    	} catch (SQLException e) {
		    		
		    	}
		    }
		    return list;
	    }
}
