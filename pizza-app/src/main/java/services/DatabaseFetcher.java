package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.utils.SystemProperty;

import conn.ConnectionsPizza;
import data.Topping;
import data.Menu;

@Path("/db")
public class DatabaseFetcher {  
	
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/getAllToppings")
	    public ArrayList<Topping> getToppings() {
	    	String sql = "select * from fillings";
	    	ResultSet RS = null;
	    	ArrayList<Topping> list = new ArrayList<>();
	    	
	 	    Connection conn=null;   
		    try {
			    if (SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
				    conn = ConnectionsPizza.getProductionConnection();
			    } else {
				    conn = ConnectionsPizza.getDevConnection();
			    }
		    } catch (Exception e) {	    
			    e.printStackTrace();
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
		    	e.printStackTrace();
		    }
		    
		    if (conn!=null) {
		    	try {
		    		conn.close();
		    	} catch (SQLException e) {
		    		e.printStackTrace();
		    	}
		    }
		    return list;
	    }
	    
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/getAllMenus")
	    public ArrayList<Menu> getMenus() {
	    	String sql = "select * from menus";
	    	ResultSet RS = null;
	    	ArrayList<Menu> list = new ArrayList<>();
	    	
	    	Connection conn=null;   
		    try {
			    if (SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
				    conn = ConnectionsPizza.getProductionConnection();
			    } else {
				    conn = ConnectionsPizza.getDevConnection();
			    }
		    } catch (Exception e) {	    
			    e.printStackTrace();
		    }
		    
		    Statement stmt;
		    try {
		    	stmt = conn.createStatement();
		    	RS=stmt.executeQuery(sql);
		    	while (RS.next()) {
		    		Menu m = new Menu();
		    		m.setId(RS.getInt("id"));		    		
		    		m.setTayte1(RS.getString("tayte1"));
		    		m.setTayte2(RS.getString("tayte2"));
		    		m.setTayte3(RS.getString("tayte3"));
		    		m.setPrice(RS.getDouble("price"));
		    		m.setRestaurant(RS.getString("restaurant"));
		    		list.add(m);
		    	}
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		    
		    if (conn!=null) {
		    	try {
		    		conn.close();
		    	} catch (SQLException e) {
		    		e.printStackTrace();
		    	}
		    }
	    	return list;
	    }
}
