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
 * Servlet implementation class GetArea
 */
@WebServlet("/GetArea")
public class GetArea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetArea() {
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
		JSONObject resp = new JSONObject();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "SELECT AREA, WINNER FROM GAME WHERE ID="
					+ request.getParameter("id");
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				resp.put("result", new Boolean(false));
			} else {
				resp.put("result", new Boolean(true));
				resp.put("area", rs.getString("area"));
				resp.put("winner", rs.getInt("winner"));
			}
		} catch (Exception e) {
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
