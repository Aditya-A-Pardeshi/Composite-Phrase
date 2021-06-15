package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DataBase;

/**
 * Servlet implementation class AdminProfile
 */
@WebServlet("/AdminProfile")
public class AdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		int adminId=Integer.parseInt(session.getAttribute("adminId").toString());
	   
	    int updateStatus=Integer.parseInt(request.getParameter("updatests"));
	    Connection conn=DataBase.getConnection();
	    try {
	    	if(updateStatus==0) {
	    String name=request.getParameter("name");
	    PreparedStatement stmnt=conn.prepareStatement("update admin set name=? where adminId=?");
	    stmnt.setString(1,name);
	    stmnt.setInt(2,adminId);
	    session.setAttribute("name", name);
	    stmnt.executeUpdate();
	    response.getWriter().print(1);
	    	}else {
	   String password=request.getParameter("password");
	   PreparedStatement stmnt=conn.prepareStatement("update admin set password=? where adminId=?");
	    stmnt.setString(1,password);
	    stmnt.setInt(2,adminId);
	    stmnt.executeUpdate();
	    response.getWriter().print(1);
	    	}
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    }

}
