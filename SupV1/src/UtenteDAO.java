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
	
	public void CreaUtenteDB(String nomeUtente, String password) throws SQLException{
		String sql = "INSERT INTO Utente (nome, password) VALUES (?, ?)";
		PreparedStatement inserisciUtente = connessione.prepareStatement(sql);
		inserisciUtente.setString(1, nomeUtente);
		inserisciUtente.setString(2, password);
		inserisciUtente.executeUpdate();
	}
	
	public boolean VerificaDatiUtente (String nomeUtente, String password) throws SQLException{
		String sql = "SELECT password FROM Utente WHERE nome = ?";
		PreparedStatement checkPassword = connessione.prepareStatement(sql);
		checkPassword.setString(1, nomeUtente);
		ResultSet result = checkPassword.executeQuery();
		while(result.next()) {
			if (result.getString(1).equals(password))
				return true;
			}
		return false;
	}
	

}
