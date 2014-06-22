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
 * Servlet implementation class GetOnlineGames
 */
@WebServlet("/GetOnlineGames")
public class GetOnlineGames extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOnlineGames() {
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
		String pathGame = this.getServletContext().getRealPath("game.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "SELECT * FROM GAME WHERE IS_ACTIVE = 1 AND ONLINE < MAX";
			rs = stmt.executeQuery(sql);
			JSONObject resp = new JSONObject();
			ArrayList<Integer> array = new ArrayList<Integer>();
			while (rs.next()) {
				array.clear();
				array.add(rs.getInt("ID"));
				array.add(rs.getInt("ONLINE"));
				array.add(rs.getInt("MAX"));
				array.add(rs.getInt("SIZE"));
				resp.put(rs.getString("NAME"), array);
			}
			response.getWriter().write(resp.toJSONString());
		} catch (ClassNotFoundException e) {
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
	}

}
