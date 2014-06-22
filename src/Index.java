import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Responses rsp = new ResponsesSlovak();
		response.setContentType("text/html");
		response.setCharacterEncoding(rsp.getencoding());
		Writer out = response.getWriter();
		out.write(rsp.getWelcome());
		if (!new File(this.getServletContext().getRealPath("game.db")).exists()
				|| !new File(this.getServletContext().getRealPath("players.db"))
						.exists()
				|| !new File(this.getServletContext().getRealPath(
						"onlinePlayers.db")).exists()) {
			out.write("<br>" + rsp.getdatabaseNotExists()
					+ " <a href='CreateDatabase'>" + rsp.getCreateDatabase()
					+ "</a>");
		} else {
			out.write("<br><span style='color: green;'>"
					+ rsp.getdatabaseExists() + "</span>");
		}
	}
}
