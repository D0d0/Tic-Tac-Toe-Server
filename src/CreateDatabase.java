import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateDatabase
 */
@WebServlet("/CreateDatabase")
public class CreateDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateDatabase() {
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
		response.setContentType("text/html");
		Writer out = response.getWriter();
		Responses rsp = new ResponsesEnglish();
		String pathGame = this.getServletContext().getRealPath("game.db");
		String pathPlayers = this.getServletContext().getRealPath("players.db");
		String pathOnlinePlayers = this.getServletContext().getRealPath(
				"onlinePlayers.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "CREATE TABLE GAME " + "("
					+ " ID 			   INTEGER PRIMARY KEY     AUTOINCREMENT, "
					+ " NAME           TEXT    				   NOT NULL, "
					+ " HOST		   TEXT                    NOT NULL, "
					+ " ONLINE         INTEGER                 NOT NULL, "
					+ " MAX            INTEGER	               NOT NULL, "
					+ " SIZE           INTEGER 	               NOT NULL, "
					+ " AREA           TEXT                    NOT NULL, "
					+ " IS_ACTIVE      INTEGER     			   NOT NULL, "
					+ " RANK		   INTEGER				   NOT NULL,"
					+ " MOVE		   INTEGER				   NOT NULL,"
					+ " ROW_FOR_WIN	   INTEGER				   NOT NULL,"
					+ " WINNER		   INTEGER				   NOT NULL)";
			stmt.executeUpdate(sql);
			c = DriverManager.getConnection("jdbc:sqlite:" + pathPlayers);
			stmt = c.createStatement();
			sql = "CREATE TABLE PLAYERS " + "("
					+ " ID 			   INTEGER PRIMARY KEY     AUTOINCREMENT, "
					+ " NAME           TEXT 				   NOT NULL, "
					+ " PASSWORD       TEXT                    NOT NULL, "
					+ " GAMES_WIN      INTEGER				   NOT NULL, "
					+ " LOST_GAMES     INTEGER                 NOT NULL)";
			stmt.executeUpdate(sql);
			c = DriverManager.getConnection("jdbc:sqlite:" + pathOnlinePlayers);
			stmt = c.createStatement();
			sql = "CREATE TABLE ONLINE_PLAYERS " + "("
					+ " ID_PLAYER      INTEGER 			       NOT NULL, "
					+ " GAME           INTEGER                 NOT NULL)";
			stmt.executeUpdate(sql);
			out.write("<span style='color: green;'>"
					+ rsp.getdatabaseCreatedSucc() + "</span>");
		} catch (ClassNotFoundException e) {
			out.write("<span style='color: red;'>"
					+ rsp.getdatabaseCreatedUnsucc() + "<br>"
					+ e.getLocalizedMessage() + " : " + e.getMessage()
					+ "</span>");
		} catch (SQLException e) {
			out.write("<span style='color: red;'>"
					+ rsp.getdatabaseCreatedUnsucc() + "<br>"
					+ e.getErrorCode() + " : " + e.getMessage() + "</span>");
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
