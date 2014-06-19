/**
 * @author Jozef Abstract class for servlet responses.
 */
public abstract class Responses {

	abstract String getWelcome();

	abstract String getdatabaseCreatedSucc();

	abstract String getdatabaseCreatedUnsucc();

	abstract String getnewDatabaseQuestion();

	abstract String gendatabaseStatus();

	abstract String getdatabaseExists();

	abstract String getdatabaseNotExists();

	abstract String getCreateDatabase();

	abstract String getencoding();
}
