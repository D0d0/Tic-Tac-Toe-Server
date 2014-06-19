/**
 * 
 * @author Jozef Slovak translate for server responses
 * 
 */
public final class ResponsesSlovak extends Responses {
	private final String toString = "Trieda pre slovensk� preklad.";
	private final String welcome = "Vitajte na hlavnej str�nke servletu pre online hru Pi�kvorky.";
	private final String databaseCreatedSucc = "Datab�za bola �spe�ne vytvoren�.";
	private final String databaseCreatedUnsucc = "Vytvorenie datab�zy skon�ilo s chybou.";
	private final String newDatabaseQuestion = "";
	private final String databaseStatus = "Status datab�zy: ";
	private final String databaseExists = "Datab�za existuje.";
	private final String databaseNotExists = "Datab�za neexistuje.";
	private final String createDatabase = "Vytvor nov� datab�zu.";
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
