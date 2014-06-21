import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class CreateDatabase
 */
@WebServlet("/CreateGame")
public class CreateGame extends HttpServlet {

	private static final long serialVersionUID = 5553650924623625670L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGame() {
		super();
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
			String area = "";
			for (int i = 0; i < Integer.valueOf(request.getParameter("size")); i++) {
				for (int j = 0; j < Integer.valueOf(request
						.getParameter("size")); j++) {
					area += "0 ";
				}
			}
			area = area.trim();
			String sql = "INSERT INTO GAME (NAME, HOST, ONLINE, MAX, SIZE, AREA, IS_ACTIVE, MOVE, RANK, ROW_FOR_WIN, WINNER) VALUES ('"
					+ request.getParameter("name")
					+ "', '"
					+ request.getParameter("host")
					+ "', 1, "
					+ request.getParameter("max")
					+ ", "
					+ request.getParameter("size")
					+ ", '"
					+ area
					+ "', 1, 1, 2, " + request.getParameter("rows") + ", -1)";
			stmt.executeUpdate(sql);
			sql = "SELECT max(id) as id FROM GAME";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resp.put("result", new Boolean(true));
				resp.put("id", rs.getInt("id"));
			} else {
				resp.put("result", new Boolean(false));
			}
		} catch (ClassNotFoundException | SQLException e) {
			resp.put("result", new Boolean(false));
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				c.close();
			} catch (SQLException e) {
			}
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		response.getWriter().write(resp.toJSONString());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
