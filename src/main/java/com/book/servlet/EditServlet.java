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
@WebServlet("/editurl")
public class EditServlet extends HttpServlet {

	
	private static final String sql="update bookdata set bookName=?,bookEdition=?,bookPrice=?  where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con;
		String url="jdbc:mysql://localhost:3306/bookregister";
		String username="sql6587673";
		String password="rAhpFRQqfd";
		PreparedStatement psmt;
		
		
		
		PrintWriter out=res.getWriter();
		
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			psmt=con.prepareStatement(sql);
			
			psmt.setString(1, bookName);
			psmt.setString(2,bookEdition);
			psmt.setFloat(3, bookPrice);
			psmt.setInt(4, id);
			int count=psmt.executeUpdate();
			if(count == 1) {
				out.println("<h2>Record is edited successfully</h2>");
			}
			else {
				out.println("<h2> record is not edited</h2>");
			}
			
			//ResultSet rs=psmt.executeQuery();
			
				
			
		}
			catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h1>");
		}
		out.println("<a href='index.html'>Home Page</a>");
		out.println("<br>");
		out.println("<a href='bookList'>BooKList</a>");
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
