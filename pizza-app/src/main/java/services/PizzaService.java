package services;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.appengine.api.utils.SystemProperty;
import java.sql.Statement;
import conn.Connections;
import data.Pizza;


@Path("/pizzaservice")
public class PizzaService {
	
	
	
	   @POST
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes("application/x-www-form-urlencoded")
       @Path("/addpizza")
	   public Pizza addDogBreedByPost(@FormParam("name") String name, @FormParam("birthyear") float birthyear, @FormParam("country") String country) {
		   Pizza pizza=new Pizza();
		   pizza.setName(name);
		   pizza.setBirthyear(birthyear);
		   pizza.setCountry(country);
		   String sql="insert into cloud(name, birthyear, country) values(?,?,?)";
		   
		 
		   
	   Connection conn=null;   
	   try {
		   if(SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
			   conn = Connections.getProductionConnection();
		   }
		   else {
			   conn = Connections.getDevConnection();
		   }
		  
	   }   catch (Exception e) {
		   
		   e.printStackTrace();
	   }
	   PreparedStatement pstmt;
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, name);
		   pstmt.setFloat(2, birthyear);
		   pstmt.setString(3, country);
		   pstmt.execute();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return pizza;
	   
	   
	   
	   }
}