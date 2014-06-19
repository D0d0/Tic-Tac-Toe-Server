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
 * Servlet implementation class CreatePlayer
 */
@WebServlet("/CreatePlayer")
public class CreatePlayer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePlayer() {
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
		String pathPlayers = this.getServletContext().getRealPath("players.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathPlayers);
			stmt = c.createStatement();
			String user = request.getParameter("user");
			String sql = "SELECT * FROM PLAYERS WHERE NAME='" + user + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resp.put("result", new Boolean(false));
			} else {
				String password = request.getParameter("password");
				sql = "INSERT INTO PLAYERS (NAME, PASSWORD, GAMES_WIN, LOST_GAMES) VALUES ('"
						+ user + "', '" + password + "', 0, 0)";
				stmt.executeUpdate(sql);
				resp.put("result", new Boolean(true));
			}
		} catch (ClassNotFoundException e) {
			resp.put("result", new Boolean(false));
		} catch (SQLException e) {
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
				e.printStackTrace();
			}
		}
		response.getWriter().write(resp.toJSONString());
	}

}
