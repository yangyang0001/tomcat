package com.inspur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: YANG
 * Date: 2019/7/13-15:35
 * Description: No Description
 */
@WebServlet(urlPatterns = {"/demo"})
public class DemoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String message = request.getParameter("message");
		System.out.println("message ------------->:" + message);
		String contextPath = request.getContextPath();
		response.getWriter().println("message ----->:" + message + ", contextPath ----->:" + contextPath);
	}
}
