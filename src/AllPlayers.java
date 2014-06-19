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
 * Servlet implementation class AllPlayers
 */
@WebServlet("/AllPlayers")
public class AllPlayers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllPlayers() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		String pathPlayers = this.getServletContext().getRealPath("players.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathPlayers);
			stmt = c.createStatement();
			String sql = "SELECT NAME, GAMES_WIN, LOST_GAMES FROM PLAYERS ORDER BY NAME DESC";
			rs = stmt.executeQuery(sql);
			JSONObject resp = new JSONObject();
			ArrayList<Integer> array = new ArrayList<>();
			while (rs.next()) {
				array.clear();
				array.add(rs.getInt("GAMES_WIN"));
				array.add(rs.getInt("LOST_GAMES"));
				resp.put(rs.getString("NAME"), array);
			}
			response.getWriter().write(resp.toJSONString());
		} catch (ClassNotFoundException e) {
			response.getWriter().write("ClassNotFoundException");
		} catch (SQLException e) {
			response.getWriter().write(
					e.getErrorCode() + " : " + e.getLocalizedMessage());
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
	}

}
