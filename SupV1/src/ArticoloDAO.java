import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloDAO {
	
	private static Connection con;
	
	public ArticoloDAO(Connection c){ 
	con = c;
	
	}

	
	public Articolo getArticoloById (String Id) throws SQLException {
		String sql = "SELECT * FROM articolo where Id =?";
	
		PreparedStatement getArticolo = con.prepareStatement(sql);
		getArticolo.setString(1, Id);
		
		ResultSet result = getArticolo.executeQuery();
		while(result.next()) {
			String nome = (result.getString(1));
			String id = (result.getString(2));
			Double prezzo = new Double(result.getString(3));
			Articolo ArticoloTrovato = new Articolo (nome, id, prezzo);
			
			return ArticoloTrovato;
			}
		return null;
	}
	
	public Articolo InserisciArticoloInMagazzino (Articolo a) throws SQLException{
		String sql = "INSERT INTO Magazzino VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement inserisciArticolo = con.prepareStatement(sql);
		inserisciArticolo.setString(1, a.getName());
		inserisciArticolo.setString(2, a.getId());
		inserisciArticolo.setLong(3, (long) a.getPrice());
		inserisciArticolo.setString(4, a.getPathFoto());
		inserisciArticolo.setString(5, a.getTaglia());
		inserisciArticolo.setString(6, a.getColore());
		inserisciArticolo.setLong(7, 1);
		
		inserisciArticolo.executeUpdate();
		
		return null;
	}


	
		
	

}
