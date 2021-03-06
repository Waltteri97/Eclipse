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
import conn.ConnectionsPizza;
import data.Pizza;
import data.Order;



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
			   conn = ConnectionsPizza.getProductionConnection();
		   }
		   else {
			   conn = ConnectionsPizza.getDevConnection();
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
	   
	   
	   @POST
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes("application/x-www-form-urlencoded")
       @Path("/addordertest")
	   public Order addOrderByPost(@FormParam("pizza") String pizza, @FormParam("price") float price, @FormParam("restaurant") String restaurant, @FormParam("first_name") String first_name, @FormParam("last_name") String last_name, @FormParam("email") String email, @FormParam("phonenumber") String phonenumber, @FormParam("address") String address) {
		   Order order=new Order();
		   order.setPizza(pizza);
		   order.setPrice(price);
		   order.setRestaurant(restaurant);
		   order.setFirstname(first_name);
		   order.setLastname(last_name);
		   order.setEmail(email);
		   order.setPhonenumber(phonenumber);
		   order.setAddress(address);
		   String sql="insert into orders(pizza, price, restaurant, first_name, last_name, email, phonenumber, address) values(?,?,?,?,?,?,?,?)";
		   
		 
		   
	   Connection conn=null;   
	   try {
		   if(SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
			   conn = ConnectionsPizza.getProductionConnection();
		   }
		   else {
			   conn = ConnectionsPizza.getDevConnection();
		   }
		  
	   }   catch (Exception e) {
		   
		   e.printStackTrace();
	   }
	   PreparedStatement pstmt;
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, pizza);
		   pstmt.setFloat(2, price);
		   pstmt.setString(3, restaurant);
		   pstmt.setString(4, first_name);
		   pstmt.setString(5, last_name);
		   pstmt.setString(6, email);
		   pstmt.setString(7, phonenumber);
		   pstmt.setString(8, address);

		   pstmt.execute();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return order;
	   
	   
	   
	   }
	   
	    @POST
		@Produces(MediaType.APPLICATION_JSON)//Method returns object as a JSON string
		@Consumes(MediaType.APPLICATION_JSON)//Method receives object as a JSON string
		@Path("/addorder")
		public Order receiveJsonOrder(Order order) {
			String sql="insert into orders(pizza, price, restaurant, first_name, last_name, email, phonenumber, address) values(?,?,?,?,?,?,?,?)";
			
			Connection conn=null;
			try {
			    if (SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {  
			    	conn = ConnectionsPizza.getProductionConnection();
			    }
			    else {
			    	conn = ConnectionsPizza.getDevConnection();
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, order.getPizza());
				pstmt.setFloat(2,  order.getPrice());
				pstmt.setString(3,  order.getRestaurant());
				pstmt.setString(4,  order.getFirstname());
				pstmt.setString(5,  order.getLastname());
				pstmt.setString(6,  order.getEmail());
				pstmt.setString(7,  order.getPhonenumber());
				pstmt.setString(8,  order.getAddress());
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			
			order.setEmail(order.getEmail()+" modified");
			return order;
		}
	    
	    @GET
		@Produces(MediaType.APPLICATION_JSON)//Method returns object as a JSON string
		@Path("/getOrders")
		public ArrayList<Order> getAllOrders() {
			String sql="select * from orders";
			ResultSet RS=null;
			ArrayList<Order> list=new ArrayList<>();
			Connection conn=null;
			try {
			    if (SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {  
			    	conn = ConnectionsPizza.getProductionConnection();
			    }
			    else {
			    	conn = ConnectionsPizza.getDevConnection();
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt;
			try {
				stmt = conn.createStatement();
				RS=stmt.executeQuery(sql);
				while (RS.next()) {
					Order f=new Order();
					f.setId(RS.getInt("id"));
					f.setPizza(RS.getString("pizza"));
					f.setPrice(RS.getString("price"));
					f.setRestaurant(RS.getString("restaurant"));
					f.setFirstname(RS.getString("first_name"));
					f.setLastname(RS.getString("last_name"));
					f.setEmail(RS.getString("email"));
					f.setPhonenumber(RS.getString("phonenumber"));
					f.setAddress(RS.getString("address"));

					list.add(f);
					
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			return list;
		}
	   
	   
}