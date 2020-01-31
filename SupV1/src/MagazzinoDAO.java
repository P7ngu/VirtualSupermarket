import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MagazzinoDAO {
private static Connection connessione;
	
	public MagazzinoDAO(Connection c){ 
	connessione = c;
	
	}
	
	
public boolean AggiungiArticoloAlMagazzinoSQL(Articolo ArticoloDaAggiungere) throws SQLException{
		ArticoloDAO ArticoloDAO = new ArticoloDAO(connessione);
		ArticoloDAO.InserisciArticoloInMagazzino(ArticoloDaAggiungere);
		return true;
	}
	
public static void creaTabellaMagazzinoSQL() throws Exception {
	try {
		PreparedStatement create = connessione.prepareStatement("CREATE TABLE IF NOT EXISTS Magazzino (nome varchar(255),"
				+ "id int NOT NULL AUTO_INCREMENT, prezzo double, pathfoto varchar(200), taglia char(2),"
				+ "colore varchar(10), quantita int NOT NULL, PRIMARY KEY (id))");
		create.executeUpdate();	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
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


public static void eliminaArticoloDalMagazzinoSQL(String Id) throws SQLException {
	try {
		ArticoloDAO ArticoloDAO = new ArticoloDAO(connessione);
		PreparedStatement st = connessione.prepareStatement("DELETE FROM Magazzino WHERE id=?");
		st.setString(1, Id);
		st.executeUpdate(); 
		//ArticoloDAO.eliminaArticolo(Id);
	}
	catch(Exception e) {
		e.printStackTrace();
		}
	}



}
