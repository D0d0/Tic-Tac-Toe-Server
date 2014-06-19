import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class CanMove
 */
@WebServlet("/CanMove")
public class CanMove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CanMove() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		JSONObject resp = new JSONObject();
		String pathGame = this.getServletContext().getRealPath("game.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "SELECT * FROM GAME WHERE ID="
					+ request.getParameter("id") + " AND MOVE="
					+ request.getParameter("rank") + " AND AREA='"
					+ request.getParameter("area") + "'";
			rs = stmt.executeQuery(sql);
			resp.put("result", new Boolean(rs.next()));
		} catch (ClassNotFoundException e) {
			resp.put("result", new Boolean(false));
		} catch (SQLException e) {
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				c.close();
			} catch (SQLException e) {
			}
		}
		response.getWriter().write(resp.toJSONString());
	}

}
