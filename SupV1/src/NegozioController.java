
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class NegozioController {
	 Magazzino MagazzinoTransazionale;
	 Magazzino MagazzinoTemporaneo;
	CarrelloUtente CarrelloUtente;
	CarrelloFrame CarrelloFrame;
	HomePage HomePageFrame;
	MagazzinoFrame MagazzinoFrame;
	EliminaDaMagazzinoFrame EliminaDaMagazzinoFrame;
	InserimentoArticoloInMagazzinoFrame InserimentoArticoloInMagazzinoFrame;
	AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame;
	RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame;
	VetrinaFrame VetrinaFrame;
	PagamentoFrame PagamentoFrame;
	static Connection connessione;
	static MagazzinoDAO MagazzinoDAO;
	static ArticoloDAO ArticoloDAO;
	AcquistoDAO AcquistoDAO;
	
	

	public NegozioController() throws Exception {
		
		MagazzinoTransazionale = new Magazzino();
		MagazzinoTemporaneo = new Magazzino();
		CarrelloUtente = new CarrelloUtente();
		
		connessione = getConnectionLocale();
		
		MagazzinoDAO MagazzinoDAO = new MagazzinoDAO(connessione);
		ArticoloDAO ArticoloDAO = new ArticoloDAO(connessione);
		AcquistoDAO AcquistoDAO = new AcquistoDAO(connessione);
		
		riempiMagazzinoDaDB();
		
		
		EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame(this);
		AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		CarrelloFrame = new CarrelloFrame(this);
		VetrinaFrame = new VetrinaFrame(this);
		MagazzinoFrame = new MagazzinoFrame(this);
		InserimentoArticoloInMagazzinoFrame = new InserimentoArticoloInMagazzinoFrame(this);
		EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame (this);
		MagazzinoFrame = new MagazzinoFrame(this);
		RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(this);
		VetrinaFrame= new VetrinaFrame(this);
		AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		
		HomePageFrame = new HomePage(this);
		HomePageFrame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) throws Exception {
		NegozioController TheController = new NegozioController();
		MagazzinoDAO = new MagazzinoDAO(connessione);
		ArticoloDAO = new ArticoloDAO(connessione);
		

	}
	
	

	private void riempiMagazzinoDaDB() throws SQLException, IOException {
		ArticoloDAO = new ArticoloDAO(connessione);
		riempiTabellaArticoloDaDB();
		String sql = "SELECT* FROM Magazzino";
		PreparedStatement getArticolo = connessione.prepareStatement(sql);
		ResultSet result = getArticolo.executeQuery();
		while(result.next()) {
			Integer quantita = new Integer(result.getString(2));
			String id = (result.getString(1));
			Articolo ArticoloTrovato = trovaDatiArticoloInMagazzino(id);
			ArticoloTrovato.setQuantita(quantita);
			
			if(quantita>0) {
			MagazzinoTransazionale.add(ArticoloTrovato);
			MagazzinoTemporaneo.add(ArticoloTrovato);
			}
			
		}
		
		
	}
	
	private Articolo trovaDatiArticoloInMagazzino(String id) throws SQLException, IOException {
		String sqlnest = "SELECT* FROM Articolo where id=?";
		PreparedStatement getArticoloNest = connessione.prepareStatement(sqlnest);
		getArticoloNest.setString(1, id);
		ResultSet resultNest = getArticoloNest.executeQuery();
		while(resultNest.next()) {
		String nome = (resultNest.getString(1));
		Double prezzo = new Double(resultNest.getString(3));
		String pathfoto = resultNest.getString(4);
		String taglia = (resultNest.getString(5));
		String colore = (resultNest.getString(6));
		Articolo ArticoloTrovato = new Articolo (nome, id, prezzo, pathfoto, taglia, colore);
		ArticoloDAO.JdbcReadImage(ArticoloTrovato);
		return ArticoloTrovato;
		}
		return null;
	}

	private void riempiTabellaArticoloDaDB() throws SQLException, IOException {
		String sqlnest = "SELECT* FROM Articolo";
		PreparedStatement getArticoloNest = connessione.prepareStatement(sqlnest);
		ResultSet resultNest = getArticoloNest.executeQuery();
		while(resultNest.next()) {
		String nome = (resultNest.getString(1));
		String id = (resultNest.getString(2));
		Double prezzo = new Double(resultNest.getString(3));
		String pathfoto = "";
		String taglia = (resultNest.getString(5));
		String colore = (resultNest.getString(6));
		Articolo ArticoloTrovato = new Articolo (nome, id, prezzo, pathfoto, taglia, colore);
		ArticoloDAO.JdbcReadImage(ArticoloTrovato);
		
	}
	}
	
	public Connection getConnectionLocale() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giraffe";
			String username = "root";
			String password = "password";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			creaMessaggioOperazioneEffettuataConSuccesso("Accesso al Database effettuato!");
			return conn;
		} catch (Exception e) { creaMessaggioErroreDuranteOperazione("Impossibile accedere al Database", "Riprovare"); }
		return null;
		
	}
	

	public Connection getConnection1() {
		  String dbName = ("sql7317801");
		  String userName = ("sql7317801");
		  String password = ("JSfK3lPNgq");
		  String hostname = ("sql7.freesqldatabase.com");
		  String port = ("3306");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		  try {
		    System.out.println("Loading driver...");
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded!");
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
		  try {
		   Connection conn = DriverManager.getConnection(jdbcUrl);
		    return conn;
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		  }
		return null;
	}
	
	public Connection getConnection() throws Exception{
		  String userName = ("udsjzyti");
		  String password = ("Xgwixw9770_hB0C8XiTyM17hNZY5zANf");
		  String jdbcUrl ="jdbc:postgresql://manny.db.elephantsql.com:5432/udsjzyti";
		  try {
		    System.out.println("Loading driver...");
		    Class.forName("org.postgresql.Driver");
		    System.out.println("Driver loaded!");
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
		  try {
		   Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
		    return conn;
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		  }
		return null;
	}
		

	
	public void aggiungiArticoloAlMagazzino(String Nome, String Codice, String prezzo, String fotoPath, String Taglia, String Colore, int flag) throws SQLException {
		Double Prezzo = new Double(prezzo);
		Articolo ArticoloDaAggiungere = new Articolo(Nome, Codice, Prezzo, fotoPath, Taglia, Colore);
		try{ 
			if(MagazzinoDAO.checkQuantitaArticoloMagazzinoSQL(ArticoloDaAggiungere)==null //L'articolo non è presente in Magazzino
				&& (!CorrispondenzaValori(ArticoloDaAggiungere))) {	 // e non è presente nella tabella Articolo, fallisce il primo IF
					ArticoloDAO.CreaArticolo(ArticoloDaAggiungere); // lo inserisco nella tabella Articolo
					MagazzinoTransazionale.add(ArticoloDaAggiungere);
					MagazzinoTemporaneo.add(ArticoloDaAggiungere);
			}
			
				if(MagazzinoDAO.checkQuantitaArticoloMagazzinoSQL(ArticoloDaAggiungere)==null  //se non è presente in Magazzino
					&& (MagazzinoDAO.AggiungiArticoloAlMagazzinoSQL(ArticoloDaAggiungere))) { //e lo aggiungo con successo nel database
					ArticoloDaAggiungere.setQuantita(1);
					creaMessaggioOperazioneEffettuataConSuccesso("Articoli aggiunti correttamente");
					
				}else if(CorrispondenzaValori(ArticoloDaAggiungere)) { //altrimenti è già presente in magazzino, e se i valori sono corretti
							MagazzinoDAO.incrementaQuantitaArticoloMagazzinoDB(ArticoloDaAggiungere); //ne incremento la quantità nel DB
							if(flag==0)creaMessaggioOperazioneEffettuataConSuccesso("Quantità articolo incrementata!"); //mostra il messaggio solo una volta
							ArticoloDaAggiungere.setQuantita(MagazzinoDAO.checkQuantitaArticoloMagazzinoSQL(ArticoloDaAggiungere)+ 1);
							MagazzinoTransazionale.add(ArticoloDaAggiungere);
							MagazzinoTemporaneo.add(ArticoloDaAggiungere);
				}
			else creaMessaggioErroreDuranteOperazione("ERRORE: VALORI INESATTI", "RIPROVARE"); 	
				
				
		} catch (Exception e) { //catch per inserimento articolo in magazzino
			if(flag==0)
			creaMessaggioErroreDuranteOperazione("ERRORE: ID DUPLICATO", "RIPROVARE"); 
			e.printStackTrace();
		}
	}
		
	public void setFoto (String path, String Codice) throws SQLException {
		if (!ArticoloDAO.checkImmagine(Codice))
		ArticoloDAO.JdbcWriteImage(path, Codice);
		else {
			creaMessaggioErroreDuranteOperazione("Foto già inserita per l'articolo selezionato!", "Errore");
		}
	}
	
	private boolean checkPresenzaArticoloNelDB (Articolo ArticoloDaControllare) {
		if (ArticoloDaControllare!= null) return true;
		return false;
	}
	
	private boolean CorrispondenzaValori(Articolo articoloDaAggiungere) throws SQLException {
		Articolo ArticoloTrovato = ArticoloDAO.getArticoloById(articoloDaAggiungere.getId());
			if (checkPresenzaArticoloNelDB(ArticoloTrovato) &&
				ArticoloTrovato.getName().equals(articoloDaAggiungere.getName()) &&
				ArticoloTrovato.getColore().equals(articoloDaAggiungere.getColore()) &&
				ArticoloTrovato.getPrice() == articoloDaAggiungere.getPrice() &&
				ArticoloTrovato.getTaglia().equals(articoloDaAggiungere.getTaglia())) {
			return true;
		}
		return false;
	}

	public void creaMessaggioErroreDuranteOperazione(String testoDaMostrare, String titolo) {
		JOptionPane.showMessageDialog(new JFrame(), testoDaMostrare, titolo,
		        JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void creaMessaggioOperazioneEffettuataConSuccesso(String testoDaMostrare) {
		JFrame parent = new JFrame();
		JOptionPane.showMessageDialog(parent, testoDaMostrare);
	}
	
	public void rimuoviArticoliDalCarrello (Articolo ArticoloDaRimuovere, int quantita) {
		while(quantita>0 && CarrelloUtente.contains(ArticoloDaRimuovere)) {
			if(CarrelloUtente.remove(ArticoloDaRimuovere)) {
				ArticoloDaRimuovere.setQuantita(ArticoloDaRimuovere.getQuantita() + 1);
				AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
				quantita--;
				}
			if(isCarrelloEmpty()) {
				chiudiTutteLeFinestre();
				CarrelloFrame = new CarrelloFrame(this);
				CarrelloFrame.setVisible(true);
			}	
		}
		aggiornaLabelCarrello();
		RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(this);
	}
	
	public boolean isCarrelloEmpty () {
		if (CarrelloUtente.isEmpty()) {
			creaMessaggioErroreDuranteOperazione("Carrello Vuoto", "Carrello vuoto");
			CarrelloFrame.setVisible(true);
		}
		return CarrelloUtente.isEmpty();
	}
		
	public void riempiComboAggiungiAlCarrello (JComboBox<Articolo> articoloBox) {
		for (Articolo a: MagazzinoTemporaneo.getElencoArticoli()) {
			articoloBox.addItem(a);
		}		
	}
	
	public void riempiComboRimuoviDalCarrello (JComboBox<Articolo> articoloBox) {
		for (Articolo a: CarrelloUtente.getElencoArticoli()) {
			articoloBox.addItem(a);
		}
	}
	
	public void openAggiungiAlCarrello () {
		AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		AggiungiAlCarrelloFrame.setVisible(true);
	}
	
	public void openRimuoviDalCarrello() {
		if(!isCarrelloEmpty()) {
			RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(this);
			RimuoviDalCarrelloFrame.setVisible(true);
		}
	}
	
	public void terminaInserimentoArticoli() {
		creaVetrina();
		VetrinaFrame.setVisible(false);
		HomePageFrame.setVisible(true);
	}
	
	public void aggiungiAlCarrello (Articolo articoloSelezionato, int quantitaSelezionata) throws SQLException{
		int quantitaDisponibileInMagazzino = MagazzinoDAO.checkQuantitaArticoloMagazzinoSQL(articoloSelezionato);
		if(quantitaDisponibileInMagazzino >= quantitaSelezionata && articoloSelezionato.getQuantita()>0 ) {
			while(quantitaSelezionata>0 && !checkCarrelloPieno()) {
				aggiungiArticoloAlCarrelloUtente(articoloSelezionato);
				quantitaSelezionata--;
				articoloSelezionato.setQuantita(articoloSelezionato.getQuantita() - 1);
			}
			if(checkCarrelloPieno()) creaMessaggioErroreDuranteOperazione("Carrello Pieno!", "Quantità massima inserita");
			aggiornaLabelCarrello();
			CarrelloFrame.setVisible(true);
			AggiungiAlCarrelloFrame.setVisible(false);
			AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		} else creaMessaggioErroreDuranteOperazione("Quantità in magazzino non disponibile",
				"Riduci la quantità");
	}
	
	public void aggiungiArticoloAlCarrelloUtente (Articolo articoloSelezionato) {
		if(!checkCarrelloPieno()) 
			CarrelloUtente.add(articoloSelezionato);
	}
		
	public double eseguiTotale () {
		double Totale=0.0;
		for (Articolo a: CarrelloUtente.getElencoArticoli())
			Totale = Totale + a.getPrice();
		
		return Totale;
	}
	
	public void aggiornaLabelCarrello () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame = new CarrelloFrame(this);
		creaLabelTabella();
		int y=107, max=450, x=1; 
		for(Articolo a: CarrelloUtente.getElencoArticoli()) {
			if(y>=max){
				y=107;
				x=x+300; 
			}
			creaLabelArticoloCarrello (x, y, a);
			y=y+17;
		}
		aggiornaFrameCarrello();
	}
	
	public void creaLabelTabella () {
		int y=90;
		int max=900;
		for (int x=1; x<max; x=x+300) {
			JLabel LabelNome = new JLabel();
			LabelNome.setText("Nome ");
			LabelNome.setBounds(x, y, 360, 18);
			CarrelloFrame.contentPane.add(LabelNome);
		
			JLabel LabelPrezzo = new JLabel();
			LabelPrezzo.setText(" -  $");
			LabelPrezzo.setBounds(x+80, y, 360, 18);
			CarrelloFrame.contentPane.add(LabelPrezzo);
		}	
	}
	
	public void aggiornaFrameCarrello () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame.setVisible(true);
	}
	
	public void aggiornaFrameVetrina() {
		VetrinaFrame.setVisible(false);
		VetrinaFrame.setVisible(true);
	}
		
	public void creaLabelArticoloCarrello (int x, int y, Articolo a) {
		JLabel LabelNome = new JLabel();
		LabelNome.setText(a.getName());
		LabelNome.setBounds(x, y, 360, 18);
		CarrelloFrame.contentPane.add(LabelNome);
		
		JLabel LabelPrezzo = new JLabel();
		Double d = new Double(a.getPrice());
		String prezzo = Double.toString(d);
		LabelPrezzo.setText(" - " +prezzo);
		LabelPrezzo.setBounds(x+80, y, 360, 18);
		
		CarrelloFrame.contentPane.add(LabelPrezzo);
		creaPulsanteVisualizzaArticolo(x, y, a);
		
		SwingUtilities.updateComponentTreeUI(CarrelloFrame);
		
	}
	
	public void creaPulsanteVisualizzaArticolo(int x, int y,Articolo articoloCliccato){
		JButton btnVisualizzaArticolo = new JButton("Visualizza");
		ArticoloDaVisualizzare articoloVisualizzato = new ArticoloDaVisualizzare(articoloCliccato, this);
		btnVisualizzaArticolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				articoloVisualizzato.setVisible(true);
			}
		});
		btnVisualizzaArticolo.setBounds(x+147, y+2, 130, 15);
		CarrelloFrame.contentPane.add(btnVisualizzaArticolo);
	}
	
	public void creaArticoloPerVetrina(int x, int y, Articolo articoloCliccato) {
		ArticoloDaVisualizzare ArticoloVisualizzato = new ArticoloDaVisualizzare (articoloCliccato, this);
		JLabel fotoLabel = ArticoloVisualizzato.getFotoLabel();
		JLabel articoloLabel = new JLabel(articoloCliccato.getId()+"-"+ articoloCliccato.getTaglia() +"-"+
			articoloCliccato.getPrice()+"$");
		JButton BottoneAggiungi = ArticoloVisualizzato.getBottone();
		fotoLabel.setBounds(x, y, 100, 100);
		articoloLabel.setBounds(x, y+105, 360, 18);
		BottoneAggiungi.setBounds(x, y+125, 100, 15);
		VetrinaFrame.aggiungiInVetrina(fotoLabel, articoloLabel, BottoneAggiungi);
		
		SwingUtilities.updateComponentTreeUI(VetrinaFrame);
		
	}
	
	public void creaVetrina() {
		int x=30, max=350, y=30; 
		for(Articolo a: MagazzinoTransazionale.getElencoArticoli()) {
			if(y>=max){
				y=30;
				x=x+120; 
				}
			creaArticoloPerVetrina(x, y, a);
			y=y+150;
			}
		aggiornaFrameVetrina();
	}
	
	public boolean checkCarrelloPieno () {
		if (CarrelloUtente.getSize() >= 63) {
			return true;
		}
		return false;
			
	}


	public void apriSchermataPagamento() {
		if (CarrelloUtente.getSize()==0) 
			creaMessaggioErroreDuranteOperazione("Il carrello è vuoto!", "Carrello vuoto!");
		else {	
		PagamentoFrame = new PagamentoFrame(this);
		PagamentoFrame.setVisible(true);
		}
	}


	public void effettuaTransazione() throws SQLException {
		AcquistoDAO AcquistoDAO=new AcquistoDAO(connessione);
		AcquistoDAO.creaAcquisto(CarrelloUtente.getElencoArticoli());
		
		for (Articolo a: CarrelloUtente.getElencoArticoli())
		rimuoviArticoloDalMagazzino(a);
		
		creaMessaggioOperazioneEffettuataConSuccesso("Pagamento effettuato!");
		chiudiProgramma();
	}
	 

	public void chiudiProgramma() {
		System.exit(0);	
	}


	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		return new ImageIcon(imgURL, description);		
	}


	public void apriSchermataVetrina() {
		chiudiTutteLeFinestre();
		VetrinaFrame = new VetrinaFrame(this);
		creaVetrina();
		VetrinaFrame.setVisible(true);
	}


	public void apriSchermataCarrello() throws SQLException, IOException {
		MagazzinoTransazionale.clear();
		MagazzinoTemporaneo.clear();
		
		riempiMagazzinoDaDB();
		riempiMagazzinoFrame();
		if(MagazzinoTransazionale.getSize()!=0) {
			chiudiTutteLeFinestre();
			CarrelloFrame = new CarrelloFrame(this);
			CarrelloFrame.setVisible(true);
		}
		else creaMessaggioErroreDuranteOperazione("Il magazzino è vuoto, inserire un articolo", "Riprovare");
	}


	public void apriSchermataMagazzino() throws SQLException, IOException {
		if(MagazzinoTransazionale.getSize()==0) 
			creaMessaggioErroreDuranteOperazione("Magazzino vuoto!", "Magazzino vuoto!");
			else {
				chiudiTutteLeFinestre();
				
				MagazzinoTransazionale.clear();
				MagazzinoTemporaneo.clear();
				
				riempiMagazzinoDaDB();
				riempiMagazzinoFrame();
				
				MagazzinoFrame.setVisible(true);
			}
	}


	public void apriSchermataAggiungiAlMagazzino() {
		InserimentoArticoloInMagazzinoFrame = new InserimentoArticoloInMagazzinoFrame(this);
		InserimentoArticoloInMagazzinoFrame.setVisible(true);
	}


	public void apriSchermataEliminaDaMagazzino() {
		if(MagazzinoTransazionale.getSize()==0)
			creaMessaggioErroreDuranteOperazione("Magazzino vuoto!", "Magazzino vuoto!");
			else{
				EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame(this);
				EliminaDaMagazzinoFrame.setVisible(true);
			}	
	}
	
		
	public void cancellaDatiMagazzino() throws SQLException {
		if(MagazzinoTransazionale.getSize()==0) creaMessaggioErroreDuranteOperazione("Magazzino vuoto!", "Magazzino vuoto!");
			else {
				for (Articolo a: MagazzinoTransazionale.getElencoArticoli()) {
					MagazzinoDAO.eliminaArticoloDalMagazzinoSQL(a.getId());
				}
				MagazzinoTransazionale.clear();
				creaMessaggioOperazioneEffettuataConSuccesso("Magazzino resettato con successo!");
			}
	}
		
	private void chiudiTutteLeFinestre() {
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 
	}


	public void apriSchermataHome() {
		chiudiTutteLeFinestre();
		HomePageFrame = new HomePage(this);
		HomePageFrame.setVisible(true);	
	}


	public void riempiComboEliminaDaMagazzino(JComboBox<Articolo> articoloBox) {
		for (Articolo a: MagazzinoTemporaneo.getElencoArticoli()) {
			articoloBox.addItem(a);
		}	
	}


	public void rimuoviArticoloDalMagazzino(Articolo articoloSelezionato) throws SQLException {
			articoloSelezionato.setQuantita(articoloSelezionato.getQuantita() - 1);
			if (articoloSelezionato.getQuantita() == 0) MagazzinoDAO.eliminaArticoloDalMagazzinoSQL(articoloSelezionato.getId());
			else MagazzinoDAO.decrementaQuantitaArticoloMagazzinoDB(articoloSelezionato);
			
			EliminaDaMagazzinoFrame.setVisible(false);
			VetrinaFrame.setVisible(false);
			HomePageFrame.setVisible(true);
	}

	public void riempiMagazzinoFrame() {
		MagazzinoFrame = new MagazzinoFrame(this);
		int y=20, max=1200, x=10; 
		for(Articolo a: MagazzinoTransazionale.getElencoArticoli()) {
			if(y>=max){
				y=20;
				x=x+375; 
			}
			creaLabelArticoloMagazzino(x, y, a);
			y=y+15;
		}
		MagazzinoFrame.setVisible(false);
		MagazzinoFrame.setVisible(true);
	}



	private void creaLabelArticoloMagazzino(int x, int y, Articolo articoloDaMostrare) {
		JLabel articoloLabel = new JLabel(articoloDaMostrare.StampaPerMagazzino());
		articoloLabel.setBounds(x, y+10, 350, 18);
		MagazzinoFrame.AggiungiInMagazzinoFrame(articoloLabel);
		SwingUtilities.updateComponentTreeUI(MagazzinoFrame);
	}
			
			
	


	
}
	
	



