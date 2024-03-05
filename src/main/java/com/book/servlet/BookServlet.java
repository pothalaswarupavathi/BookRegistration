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

@WebServlet("/bookList")
public class BookServlet extends HttpServlet{
	private static final String sql="select id,bookName,bookEdition,bookPrice from bookdata";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con;
		String url="jdbc:mysql://localhost:3306/bookregister";
		String username="sql6587673";
		String password="rAhpFRQqfd";
		PreparedStatement psmt;
		
		
		
		PrintWriter out=res.getWriter();
		
		res.setContentType("text/html");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			psmt=con.prepareStatement(sql);
			
			ResultSet rs=psmt.executeQuery();
			
			out.println("<table border='1' align='center'>");
			out.println("<tr>");
			out.println("<th>Book Id</th>");
			out.println("<th>Book Name</th>");
			out.println("<th>Book Edition</th>");
			out.println("<th>Book Price</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			
			
			out.println("</tr>");
			
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getFloat(4)+"</td>");
				
				out.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
				out.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				
				out.println("</tr>");
			}
			out.println("</table>");
			
				
			
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



