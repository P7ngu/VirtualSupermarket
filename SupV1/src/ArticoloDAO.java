import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloDAO {
	
	private  Connection con;
	
	public ArticoloDAO(Connection c){ 
	con = c;
	
	}

	
	public Articolo getArticoloById (String Id) throws SQLException {
		String sql = "SELECT * FROM Articolo where Id=?";
		PreparedStatement getArticolo = con.prepareStatement(sql);
		getArticolo.setString(1, Id);
		ResultSet result = getArticolo.executeQuery();
		while(result.next()) {
			String nome = (result.getString(1));
			Double prezzo = new Double(result.getString(3));
			String pathfoto= (result.getString(4));
			String taglia = (result.getString(5));
			String colore = (result.getString(6));
			Articolo ArticoloTrovato = new Articolo(nome, Id, prezzo, pathfoto, taglia, colore);
			return ArticoloTrovato;
			}
		return null;
	}
	
	public Articolo InserisciArticoloInMagazzino (Articolo a) throws SQLException{
		String sql = "INSERT INTO Magazzino VALUES (?, ?)";
		PreparedStatement inserisciArticolo = con.prepareStatement(sql);
		inserisciArticolo.setString(1, a.getId());
		inserisciArticolo.setLong(2, 1);
		inserisciArticolo.executeUpdate();
		return null;
	}
	
	public Articolo CreaArticolo (Articolo a) throws SQLException{
		String sql = "INSERT INTO Articolo VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement inserisciArticolo = con.prepareStatement(sql);
		inserisciArticolo.setString(1, a.getName());
		inserisciArticolo.setString(2, a.getId());
		inserisciArticolo.setDouble(3, (double) a.getPrice());
		inserisciArticolo.setString(4, a.getPathFoto());
		inserisciArticolo.setString(5, a.getTaglia());
		inserisciArticolo.setString(6, a.getColore());
		
		inserisciArticolo.executeUpdate();
		
		return null;
		
	}
	
	public  void JdbcWriteImage(String pathfoto, String id) throws SQLException {

		File myFile = new File(pathfoto);
		 String sql = "INSERT INTO Images VALUES(?, ?)";
		 PreparedStatement pst = con.prepareStatement(sql); 
            try(FileInputStream fin = new FileInputStream(myFile)) {
                pst.setBinaryStream(1, fin, (int) myFile.length());
                  pst.setString(2, id);
                  pst.executeUpdate();     
            } catch (IOException ex) {
           ex.printStackTrace();

            }
        } 
	
	public void JdbcReadImage(Articolo articoloDiCuiImpostareFoto) throws SQLException, IOException{
		 String query = "SELECT Logo FROM Images WHERE id=? LIMIT 1";
	             PreparedStatement pst = con.prepareStatement(query);
	             pst.setString(1, articoloDiCuiImpostareFoto.getId());
	             ResultSet rs = pst.executeQuery();
	       
	             if(rs.next()){
	          //  Blob b=rs.getBlob(1);
	            // byte barr[]=b.getBytes(1,(int)b.length());
	            	 byte barr[]=rs.getBytes(1);
	             
	             String	url="./"+articoloDiCuiImpostareFoto.getId()+".jpg"; 
	             
	             FileOutputStream fout = new FileOutputStream(url); 
	             
	             articoloDiCuiImpostareFoto.setPathFoto(url);
	             
	             fout.write(barr);                
	             fout.close();  
	             }
	  
	}
	
	public void cancellaFotoRelativaAdArticolo(String id) throws SQLException {
		 String query = "DELETE FROM Images WHERE id=?";
         PreparedStatement pst = con.prepareStatement(query);
         pst.setString(1, id);
         pst.executeUpdate();
	}

	public void eliminaArticolo(String id) throws SQLException {
		cancellaFotoRelativaAdArticolo(id);
		 String query = "DELETE FROM Articolo WHERE id=?";
         PreparedStatement pst = con.prepareStatement(query);
         pst.setString(1, id);
         pst.executeUpdate();
	}
	
	public void creaTabellaArticoloSQL() throws Exception {
		try {
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Articolo (nome varchar(255),"
					+ "id int NOT NULL, prezzo double NOT NULL, pathfoto varchar(200), taglia char(2) NOT NULL,"
					+ "colore varchar(10) NOT NULL)");
			create.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public boolean checkImmagine(String codice) throws SQLException {
		 String query = "SELECT* FROM Images WHERE id=?";
         PreparedStatement pst = con.prepareStatement(query);
         pst.setString(1, codice);
         
         ResultSet result =  pst.executeQuery();
         if(result.next()) return true;
		return false;
	}
	
	



	
		
	

}
