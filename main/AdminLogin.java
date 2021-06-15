package main;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DataBase;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");

		HttpSession session = request.getSession();
		Connection con = (Connection) DataBase.getConnection();
		String msqlx = "select u.adminId,u.name from admin u where u.userName=? and u.password=?";
		PreparedStatement smtx;
		try {
			smtx = con.prepareStatement(msqlx);
			smtx.setString(1, username);
			smtx.setString(2, pwd);
			ResultSet rs = smtx.executeQuery();
			if (rs.next()) {
				int adminId = rs.getInt(1);
				String name = rs.getString(2);
				
				session.setAttribute("adminId", adminId);
				session.setAttribute("name", name);
				session.setAttribute("username", username);

				response.getWriter().print('1');
			} else
				response.getWriter().print('0');
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(e);
		}

	}

}
