package util;

import java.io.PrintWriter;

import java.util.List;

import data.Pizza;



public class HTML {
	public static void printStart(PrintWriter out) {
		out.println("<!DOCTYPE html><html><head><title>Pizzalist</title></head><body>");
	}

	public static void printEnd(PrintWriter out) {
		out.println("</body></html>");
	}
	public static void printTable(PrintWriter out, List<Pizza> pizzalist) {
		out.println("<h1>Cloudlist</h1>");
		out.println("<table border='1'>");
		for (int i=0;i<pizzalist.size();i++) {
			Pizza f=pizzalist.get(i);
			out.println("<tr><td>"+f.getId()+"<td>"+f.getName()+"<td>"+f.getBirthyear()+"<td>"+f.getCountry());
		}
		out.println("</table>");
//		out.println("<a href='./index.html'>Back to form</a>");
	}
	
}

