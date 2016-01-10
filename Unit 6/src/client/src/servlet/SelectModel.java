/**
 * @author Junjian Xie
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
		ServletOutputStream out = response.getOutputStream();
		out.print("<html>"
                + "<head><title>Automobile List Menu</title></head>"
                + "<body>Please select an Automobile<br>" 
                + "<form method=\"get\" action=\"SelectOption\">"
                + "<select name=\"modelName\">");
		ServerSocketIO s = new SocketIO("localhost", 8888);
		ArrayList<String> list = s.getAutomobileList();
		for(int i = 0; i < list.size(); i++) {
            out.println("<option value=\"" + list.get(i) + "\">" + list.get(i) + "</option>");
        }
		out.print("</select>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body></html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
