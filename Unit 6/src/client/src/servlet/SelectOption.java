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
import model.Automobile;

/**
 * Servlet implementation class SelectOption
 */
@WebServlet("/SelectOption")
public class SelectOption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		ServerSocketIO s = new SocketIO("localhost", 8888);
		String modelName = request.getParameter("modelName");
		if(modelName == null) {
			System.out.println("Nothing to be configured!");
			return;
		}
		Automobile auto = s.getAutomobileObject(modelName);
		out.print("<html>"
                + "<head><title>Automobile Configuration</title></head><body>"
                + "<form method=\"post\" action=\"SelectOption\">"
                + "<table border=\"1\" style=\"width:70%\">"
                + "<tr><td>Make/Model</td><td>"
                + auto.getModel()
                + "</td></tr>");
		ArrayList<String> optionSetList = auto.getOptionSetList();
		for(int i=0; i<optionSetList.size(); ++i) {
			String optionSetName = optionSetList.get(i);
			ArrayList<String> optionList = auto.getOptionList(optionSetName);
			ArrayList<Float> optionPriceList = auto.getOptionPriceList(optionSetName);
			out.println("<tr>"
                    + "<td>" + optionSetList.get(i) + "</td>"
                    + "<td><select name=\"" + optionSetList.get(i) + "\">");
			for(int j=0; j<optionList.size(); ++j) {
				out.println("<option value=\"" + optionList.get(j) + "\">"
                         + optionList.get(j) + " ($"+optionPriceList.get(j) + ")"
                         + "</option>");
			}
			out.println("</select></td>");
		}
		out.print("</table>"
                + "<input type=\"hidden\" name=\"modelName\" value=\"" + auto.getModel() + "\">"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerSocketIO s = new SocketIO("localhost", 8888);
		String modelName = request.getParameter("modelName");
		if(modelName == null) {
			System.out.println("Nothing to be configured!");
			return;
		}
		Automobile auto = s.getAutomobileObject(modelName);
		ArrayList<String> optionSetList = auto.getOptionSetList();
		for(String optionSet : optionSetList) {
			String choice = request.getParameter(optionSet);
			auto.setOptionChoice(optionSet, choice);
		}

		request.setAttribute("auto", auto);
		request.getRequestDispatcher("/Result.jsp").forward(request, response);
	}
}
