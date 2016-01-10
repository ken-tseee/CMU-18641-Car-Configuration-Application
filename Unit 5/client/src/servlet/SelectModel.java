/**
 * @author Junjian Xie
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.ServerSocketIO;
import client.SocketIO;

/**
 * Servlet implementation class SelectModel
 */
@WebServlet("/SelectModel")
public class SelectModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerSocketIO s = new SocketIO("localhost", 8888);
		ArrayList<String> list = s.getAutomobileList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/SelectModel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
