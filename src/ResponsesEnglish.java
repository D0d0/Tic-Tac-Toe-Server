/**
 * 
 * @author Jozef English translate for server responses
 */
public final class ResponsesEnglish extends Responses {
	private final String toString = "Class for english translate.";
	private final String welcome = "Welcome on main page of servlet for online game Tic-Tac-Toe.";
	private final String databaseCreatedSucc = "Database created successfully.";
	private final String databaseCreatedUnsucc = "Error occured while creating database.";
	private final String newDatabaseQuestion = "";
	private final String databaseStatus = "Database status: ";
	private final String databaseExists = "Database exists.";
	private final String databaseNotExists = "Database does not exist.";
	private final String createDatabase = "Create new database.";
	private final String encoding = "UTF-8";

	@Override
	String getWelcome() {
		return this.welcome;
	}

	@Override
	public String getdatabaseCreatedSucc() {
		return this.databaseCreatedSucc;
	}

	@Override
	public String getnewDatabaseQuestion() {
		return this.newDatabaseQuestion;
	}

	@Override
	public String toString() {
		return toString;
	}

	@Override
	public String gendatabaseStatus() {
		return this.databaseStatus;
	}

	@Override
	public String getdatabaseExists() {
		return this.databaseExists;
	}

	@Override
	public String getdatabaseNotExists() {
		return this.databaseNotExists;
	}

	@Override
	String getCreateDatabase() {
		return this.createDatabase;
	}

	@Override
	String getdatabaseCreatedUnsucc() {
		return this.databaseCreatedUnsucc;
	}

	@Override
	String getencoding() {
		return this.encoding;
	}
}
