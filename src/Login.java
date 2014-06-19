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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		String pathGame = this.getServletContext().getRealPath("players.db");
		JSONObject resp = new JSONObject();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "SELECT * FROM PLAYERS WHERE NAME='" + user
					+ "' AND PASSWORD='" + password + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resp.put("result", new Boolean(true));
				resp.put("id", new Integer(rs.getInt("id")));
			} else {
				resp.put("result", new Boolean(false));
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
		}
		response.getWriter().write(resp.toJSONString());
	}
}
