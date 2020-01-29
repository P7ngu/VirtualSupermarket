import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloDAO {
	
	private Connection con;
	
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

}
