import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class UtenteDAO {

	private Connection connessione;


	public UtenteDAO(Connection conn) {
		connessione=conn;
	}
	
	public boolean CreaUtenteDB(String nomeUtente, String password) throws SQLException{
		if(!VerificaNomeUtente(nomeUtente)) {
		String sql = "INSERT INTO Utente (nomeUtente, password) VALUES (?, ?)";
		PreparedStatement inserisciUtente = connessione.prepareStatement(sql);
		inserisciUtente.setString(1, nomeUtente);
		inserisciUtente.setString(2, password);
		inserisciUtente.executeUpdate();
		return true;
		}
		else return false;
	}
	
	public boolean VerificaDatiUtente (String nomeUtente, String password) throws SQLException{
		String sql = "SELECT password FROM Utente WHERE nomeUtente = ?";
		PreparedStatement checkPassword = connessione.prepareStatement(sql);
		checkPassword.setString(1, nomeUtente);
		ResultSet result = checkPassword.executeQuery();
		while(result.next()) {
			if (result.getString(1).equals(password))
				return true;
			}
		return false;
	}
	
	public boolean VerificaNomeUtente(String nomeUtente) throws SQLException{
		String sql = "SELECT nomeUtente FROM Utente";
		PreparedStatement checkUtente = connessione.prepareStatement(sql);
		ResultSet result = checkUtente.executeQuery();
		while(result.next()) {
			if (result.getString(1).equals(nomeUtente))
				return true;
		}
		return false;
	}

}
