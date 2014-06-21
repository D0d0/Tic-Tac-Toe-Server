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

/**
 * Servlet implementation class DumpDB
 */
@WebServlet("/DumpDB")
public class DumpDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DumpDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		response.setContentType("text/html");
		String param = request.getParameter("db");
		String path = this.getServletContext().getRealPath(param + ".db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + path);
			stmt = c.createStatement();
			String sql;
			switch (param.toLowerCase()) {
			case "game":
				sql = "SELECT * FROM GAME";
				rs = stmt.executeQuery(sql);
				response.getWriter().write("<table>");
				response.getWriter()
						.write("<tr><td>ID</td><td>ONLINE</td><td>MAX</td><td>SIZE</td><td>ACTIVE</td><td>HOST</td><td>AREA</td><td>NAME</td><td>MOVE</td><td>RANK</td><td>ROWS</td><td>WINNER</td></tr>");
				while (rs.next()) {
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>");
					response.getWriter().write(rs.getString("ID"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("ONLINE"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("MAX"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("SIZE"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("IS_ACTIVE"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("HOST"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("AREA"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("NAME"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("MOVE"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("RANK"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("ROW_FOR_WIN"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("WINNER"));
					response.getWriter().write("</td>");
					response.getWriter().write("</tr>");
				}
				response.getWriter().write("</table>");
				break;
			case "players":
				sql = "SELECT * FROM PLAYERS";
				rs = stmt.executeQuery(sql);
				response.getWriter().write("<table>");
				response.getWriter()
						.write("<tr><td>ID</td><td>NAME</td><td>PASSWORD</td><td>WIN</td><td>LOST</td></tr>");
				while (rs.next()) {
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>");
					response.getWriter().write(rs.getString("ID"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("NAME"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("PASSWORD"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("GAMES_WIN"));
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("LOST_GAMES"));
					response.getWriter().write("</td>");
					response.getWriter().write("</tr>");
				}
				response.getWriter().write("</table>");
				break;
			case "onlinePlayers":
			case "online_players":
				sql = "SELECT * FROM ONLINE_PLAYERS";
				rs = stmt.executeQuery(sql);
				response.getWriter().write("<table>");
				response.getWriter().write("<tr><td>ID</td><td>GAME</td></tr>");
				while (rs.next()) {
					response.getWriter().write("<tr>");
					response.getWriter().write("<td>");
					response.getWriter().write(rs.getString("ID_PLAYER") + " ");
					response.getWriter().write("</td><td>");
					response.getWriter().write(rs.getString("GAME") + "<br>");
					response.getWriter().write("</td>");
					response.getWriter().write("</tr>");
				}
				response.getWriter().write("</table>");
				break;
			}

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
