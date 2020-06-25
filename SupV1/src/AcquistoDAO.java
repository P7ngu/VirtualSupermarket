import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class AcquistoDAO {
private Connection connessione;
private ArrayList <String> Codici;

public AcquistoDAO(Connection conn) {
	connessione=conn;
	Codici  = new ArrayList <String>();
}

public void creaAcquisto(ArrayList <Articolo> CarrelloUtente, String utente) throws SQLException {
	Integer codiceAcquisto = CreaAcquistoDB(utente);
	for (Articolo a: CarrelloUtente) {
	if (checkQuantitaArticoloAcquistato(a, codiceAcquisto) == null) {
		InserisciArticoloInAcquisto(a, codiceAcquisto);
	} else if (checkQuantitaArticoloAcquistato(a, codiceAcquisto)!= null) {
		incrementaQuantitaArticoloAcquistatoDB(a, codiceAcquisto);
	}
	}
}

public void incrementaQuantitaArticoloAcquistatoDB (Articolo articoloSelezionato, Integer codiceAcquisto) throws SQLException {
	String ID = articoloSelezionato.getId();
	int quantitaPrecedente = checkQuantitaArticoloAcquistato(articoloSelezionato, codiceAcquisto);
	String sql = "UPDATE ComposizioneAcquisto SET quantita=? WHERE idArticolo = ? AND codiceAcquisto=?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	updateQuantita.setLong(1, (quantitaPrecedente+1));
	updateQuantita.setString(2, ID);
	updateQuantita.setLong(3,  codiceAcquisto);
	updateQuantita.executeUpdate();
}

public void decrementaQuantitaArticoloAcquistatoDB (Articolo a, Integer codiceAcquisto) throws SQLException {
	String ID = a.getId();
	String sql = "UPDATE ComposizioneAcquisto SET quantita=? WHERE idArticolo =?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	int quantitaPrecedente = checkQuantitaArticoloAcquistato(a, codiceAcquisto);
	updateQuantita.setLong(1, quantitaPrecedente-1);
	updateQuantita.setString(2, ID);
	updateQuantita.executeUpdate();
}

public Integer checkQuantitaArticoloAcquistato (Articolo articoloDaControllare, Integer codiceAcquisto) throws SQLException {
	String sql = "SELECT quantita FROM ComposizioneAcquisto WHERE idArticolo=? AND codiceAcquisto=?";
	PreparedStatement getQuantita = connessione.prepareStatement(sql);
	getQuantita.setString(1, articoloDaControllare.getId());
	getQuantita.setLong(2, codiceAcquisto);
	ResultSet result = getQuantita.executeQuery();
	while(result.next()) {
		Integer quantita = new Integer(result.getString(1));
		return quantita;
		}
	return null;
}

public Integer generaCodiceAcquisto() throws SQLException {
	String sql = "SELECT CodiceAcquisto FROM Acquisto";
	PreparedStatement getCodice = connessione.prepareStatement(sql);
	ResultSet result = getCodice.executeQuery();
	while (result.next()) {
		Codici.add(result.getString(1));
	}
	int i = 1;
	for (String c: Codici) {
		i=i+2;
	}
	return i;
		
}



public Integer CreaAcquistoDB(String utente) throws SQLException{
	String sql = "INSERT INTO Acquisto (data, codiceAcquisto, utente) VALUES (?, ?, ?)";
	PreparedStatement inserisciArticolo = connessione.prepareStatement(sql);
	LocalDate date = java.time.LocalDate.now();
	inserisciArticolo.setString(1, date.toString());
	Integer codiceAcquisto = generaCodiceAcquisto();
	inserisciArticolo.setLong(2, codiceAcquisto);
	inserisciArticolo.setString(3, utente);
	inserisciArticolo.executeUpdate();
	return codiceAcquisto;
}

public Articolo InserisciArticoloInAcquisto (Articolo a, Integer codiceAcquisto) throws SQLException{
	String sql = "INSERT INTO ComposizioneAcquisto (codiceAcquisto, idArticolo, quantita) VALUES (?, ?, ?)";
	PreparedStatement inserisciComposizioneAcquisto = connessione.prepareStatement(sql);
	inserisciComposizioneAcquisto.setLong(1, codiceAcquisto);
	inserisciComposizioneAcquisto.setString(2, a.getId());
	inserisciComposizioneAcquisto.setLong(3, 1);
	inserisciComposizioneAcquisto.executeUpdate();
	return null;
}

public ResultSet RiempiTabellaAcquisti() throws SQLException {
	Statement stmt = connessione.createStatement();
	  ResultSet rs = stmt.executeQuery("select * from ComposizioneAcquisto as c natural join acquisto as a");
	 return rs;
}

}

