import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AcquistoDAO {
private Connection connessione;
private ArrayList <String> Codici;

public AcquistoDAO(Connection conn) {
	connessione=conn;
	Codici  = new ArrayList <String>();
}

public void creaAcquisto(ArrayList <Articolo> CarrelloUtente) throws SQLException {
	Integer codiceAcquisto = CreaAcquistoDB();
	for (Articolo a: CarrelloUtente) {
	if (checkQuantitaArticoloAcquistato(a)==null) {
		InserisciArticoloInAcquisto(a, codiceAcquisto);
	} else {
		incrementaQuantitaArticoloAcquistatoDB(a);
	}
	}
}

public void incrementaQuantitaArticoloAcquistatoDB (Articolo articoloSelezionato) throws SQLException {
	String ID = articoloSelezionato.getId();
	int quantitaPrecedente = checkQuantitaArticoloAcquistato(articoloSelezionato);
	String sql = "UPDATE ComposizioneAcquisto SET quantita=? WHERE idArticolo = ?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	updateQuantita.setLong(1, (quantitaPrecedente+1));
	updateQuantita.setString(2, ID);
	updateQuantita.executeUpdate();
}

public void decrementaQuantitaArticoloAcquistatoDB (Articolo a) throws SQLException {
	String ID = a.getId();
	String sql = "UPDATE ComposizioneAcquisto SET quantita=? WHERE idArticolo =?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	int quantitaPrecedente = checkQuantitaArticoloAcquistato(a);
	updateQuantita.setLong(1, quantitaPrecedente-1);
	updateQuantita.setString(2, ID);
	updateQuantita.executeUpdate();
}

public Integer checkQuantitaArticoloAcquistato (Articolo articoloDaControllare) throws SQLException {
	String sql = "SELECT quantita FROM ComposizioneAcquisto WHERE idArticolo=?";
	PreparedStatement getQuantita = connessione.prepareStatement(sql);
	getQuantita.setString(1, articoloDaControllare.getId());
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
		Codici.add("C");
	}
	int i = 1;
	for (String c: Codici) {
		i=i+2;
	}
	return i;
		
}



public Integer CreaAcquistoDB() throws SQLException{
	String sql = "INSERT INTO Acquisto (data, codiceAcquisto) VALUES (?, ?)";
	PreparedStatement inserisciArticolo = connessione.prepareStatement(sql);
	LocalDate date = java.time.LocalDate.now();
	inserisciArticolo.setString(1, date.toString());
	Integer codiceAcquisto = generaCodiceAcquisto();
	inserisciArticolo.setLong(2, codiceAcquisto);
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

}

