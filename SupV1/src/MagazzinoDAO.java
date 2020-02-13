import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MagazzinoDAO {
private Connection connessione;
	
	public MagazzinoDAO(Connection c){ 
	connessione = c;
	
	}
	
	
public boolean AggiungiArticoloAlMagazzinoSQL(Articolo ArticoloDaAggiungere) throws SQLException{
		ArticoloDAO ArticoloDAO = new ArticoloDAO(connessione);
		ArticoloDAO.InserisciArticoloInMagazzino(ArticoloDaAggiungere);
		return true;
	}
	

public void incrementaQuantitaArticoloMagazzinoDB (Articolo articoloSelezionato) throws SQLException {
	String ID = articoloSelezionato.getId();
	int quantitaPrecedente = checkQuantitaArticoloMagazzinoSQL(articoloSelezionato);
	String sql = "UPDATE Magazzino SET quantita=? WHERE id = ?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	updateQuantita.setLong(1, (quantitaPrecedente+1));
	updateQuantita.setString(2, ID);
	updateQuantita.executeUpdate();
}

public void decrementaQuantitaArticoloMagazzinoDB (Articolo a) throws SQLException {
	String ID = a.getId();
	String sql = "UPDATE Magazzino SET quantita=? WHERE id =?";
	PreparedStatement updateQuantita = connessione.prepareStatement(sql);
	int quantitaPrecedente = checkQuantitaArticoloMagazzinoSQL(a);
	updateQuantita.setLong(1, quantitaPrecedente-1);
	updateQuantita.setString(2, ID);
	updateQuantita.executeUpdate();
}

public Integer checkQuantitaArticoloMagazzinoSQL (Articolo articoloDaControllare) throws SQLException {
	String sql = "SELECT quantita FROM Magazzino WHERE id=?";
	PreparedStatement getQuantita = connessione.prepareStatement(sql);
	getQuantita.setString(1, articoloDaControllare.getId());
	ResultSet result = getQuantita.executeQuery();
	while(result.next()) {
		Integer quantita = new Integer(result.getString(1));
		return quantita;
		}
	return null;
}


public void eliminaArticoloDalMagazzinoSQL(String Id) throws SQLException {
	try {
		ArticoloDAO ArticoloDAO = new ArticoloDAO(connessione);
		PreparedStatement st = connessione.prepareStatement("DELETE FROM Magazzino WHERE id=?");
		st.setString(1, Id);
		st.executeUpdate(); 
	}
	catch(Exception e) {
		e.printStackTrace();
		}
	}



}
