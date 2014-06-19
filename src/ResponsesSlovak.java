/**
 * 
 * @author Jozef Slovak translate for server responses
 * 
 */
public final class ResponsesSlovak extends Responses {
	private final String toString = "Trieda pre slovenský preklad.";
	private final String welcome = "Vitajte na hlavnej stránke servletu pre online hru Piškvorky.";
	private final String databaseCreatedSucc = "Databáza bola úspešne vytvorená.";
	private final String databaseCreatedUnsucc = "Vytvorenie databázy skonèilo s chybou.";
	private final String newDatabaseQuestion = "";
	private final String databaseStatus = "Status databázy: ";
	private final String databaseExists = "Databáza existuje.";
	private final String databaseNotExists = "Databáza neexistuje.";
	private final String createDatabase = "Vytvor novú databázu.";
	private final String encoding = "UTF-8";

	@Override
	public String getWelcome() {
		return welcome;
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
