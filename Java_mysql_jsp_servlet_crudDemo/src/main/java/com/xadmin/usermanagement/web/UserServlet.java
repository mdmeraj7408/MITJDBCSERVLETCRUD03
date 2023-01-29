package com.xadmin.usermanagement.web;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.usermanagement.bean1.User;
import com.xadmin.usermanagement.dao.UserDao;

@WebServlet ("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao;
	public void init(ServletConfig config) throws ServletException {
		userdao=new UserDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action =req.getServletPath();
		switch (action) {
		case "/new":
			showNewForm(req,res);
			break;

		case "/insert":
			insertUsers(req,res);
			break;

		case "/delete":
			deleteUser(req,res);
			break;
		case "/edit":
			showEditForm(req,res);
			break;

		case "/update":
			updateUser(req,res);
			break;
		default :
			listUser(req,res);
			break;
		}


	}


	private void listUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			List<User> listUser=userdao.selectAllUser();
			req.setAttribute("listUser", listUser);
			RequestDispatcher reqdis=req.getRequestDispatcher("user-list.jsp");
			reqdis.forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse res)  {
		int id=Integer.parseInt(req.getParameter("id"));
		User exitingUser;
		try
		{
			exitingUser=userdao.selectUser(id);
			RequestDispatcher disp=req.getRequestDispatcher("user-form.jsp");
			req.setAttribute("user", exitingUser);
			disp.forward(req, res);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse res) throws IOException  {
		int id=Integer.parseInt(req.getParameter("id"));
		try {
			userdao.deleteUser(id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.sendRedirect("list");


	}

	private void insertUsers(HttpServletRequest req, HttpServletResponse res) throws  IOException{
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		User newUser=new User(name, email, country);
		System.out.println(newUser);
		try {
			userdao.insertUser(newUser);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.sendRedirect("list");
	}
	public void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		User user=new User(name, email, country);
		try {
			userdao.insertUser(user);
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.sendRedirect("list");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher("user-form.jsp");
		rd.forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
