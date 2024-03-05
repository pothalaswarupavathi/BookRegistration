package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet{

	private static final String sql="select bookName,bookEdition,bookPrice from bookdata where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con;
		String url="jdbc:mysql://localhost:3306/bookregister";
		String username="sql6587673";
		String password="rAhpFRQqfd";
		PreparedStatement psmt;
		
		
		
		PrintWriter out=res.getWriter();
		
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, id);
			
			ResultSet rs=psmt.executeQuery();
			rs.next();
			out.println("<form action='editurl?id="+id+"' method='post'>");
			out.println("<table align='center'>");
			out.println("<tr>");
			out.println("<td>Book Name</td>");
			out.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book Edition</td>");
			out.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book Price</td>");
			out.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td><input type='submit' value='Edit'></td>");
			out.println("<td><input type='reset' value='cancel'></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
		
			
				
			
		}
			catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h1>");
		}
		out.println("<a href='index.html'>Home Page</a>");
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
