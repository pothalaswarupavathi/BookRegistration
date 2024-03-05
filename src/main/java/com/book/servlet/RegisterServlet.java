package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con;
		String url="jdbc:mysql://localhost:3306/bookregister";
		String username="sql6587673";
		String password="rAhpFRQqfd";
		PreparedStatement psmt;
		String sql="insert into `bookdata`(`bookName`,`bookEdition`,`bookPrice`) values(?,?,?)";
		
		
		PrintWriter out=res.getWriter();
		
		res.setContentType("text/html");
		
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			psmt=con.prepareStatement(sql);
			psmt.setString(1, bookName);
			psmt.setString(2, bookEdition);
			psmt.setFloat(3, bookPrice);
			
			int n=psmt.executeUpdate();
			if(n >0) {
				out.println("<h2>Record registerd succesfully</h2>");
			}
			else {
				out.println("<h2>Record register failed</h2>");
				
			}
			
		} catch (ClassNotFoundException e) {
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
