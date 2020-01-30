
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;

public class NegozioController {

	static ArrayList<Articolo> MagazzinoTransazionale;
	ArrayList <Articolo> MagazzinoTemporaneo;
	ArrayList<Articolo> CarrelloUtente;
	CarrelloFrame CarrelloFrame;
	static HomePage HomePage;
	MagazzinoFrame MagazzinoFrame;
	EliminaDaMagazzinoFrame EliminaDaMagazzinoFrame;
	InserimentoArticoloInMagazzinoFrame InserimentoArticoloInMagazzinoFrame;
	AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame;
	RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame;
	VetrinaFrame VetrinaFrame;
	PagamentoFrame PagamentoFrame;
	static Connection connessione;
	
	
	
	
	public static void main(String[] args) throws Exception {
		NegozioController TheController = new NegozioController();
		HomePage = new HomePage(TheController);
		HomePage.setVisible(true);
		InserimentoArticoloInMagazzinoFrame InserimentoArticoloInMagazzinoFrame = new InserimentoArticoloInMagazzinoFrame(TheController);
		EliminaDaMagazzinoFrame EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame (TheController);
		MagazzinoFrame MagazzinoFrame = new MagazzinoFrame(TheController);
		RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(TheController);
		VetrinaFrame VetrinaFrame= new VetrinaFrame(TheController);
		AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(TheController);
		CarrelloFrame CarrelloFrame = new CarrelloFrame(TheController);

	}
	
	

	private static void RiempiMagazzinoDaDB() throws SQLException {
		String sql = "SELECT * FROM Magazzino";
		PreparedStatement getArticolo = connessione.prepareStatement(sql);
		ResultSet result = getArticolo.executeQuery();
		while(result.next()) {
			String nome = (result.getString(1));
			String id = (result.getString(2));
			Double prezzo = new Double(result.getString(3));
			String pathfoto = (result.getString(4));
			String taglia = (result.getString(5));
			String colore = (result.getString(6));
			Articolo ArticoloTrovato = new Articolo (nome, id, prezzo, pathfoto, taglia, colore);
			MagazzinoTransazionale.add(ArticoloTrovato);
			}
	}


	public NegozioController() throws Exception {
		MagazzinoTransazionale = new ArrayList<Articolo>();
		MagazzinoTemporaneo = new ArrayList<Articolo>();
		CarrelloUtente = new ArrayList <Articolo>();
		
		connessione = getConnection();
		creaTabellaMagazzinoSQL();
		RiempiMagazzinoDaDB();
		
		MagazzinoTemporaneo = MagazzinoTransazionale;
		
		EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame(this);
		AggiungiAlCarrelloFrame= new AggiungiAlCarrelloFrame(this);
		VetrinaFrame = new VetrinaFrame(this);
		MagazzinoFrame = new MagazzinoFrame(this);
		//CarrelloFrame = new CarrelloFrame(this);
		
		
		
	
		
	}
	
	public static Connection getConnectionLocale() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giraffe";
			String username = "root";
			String password = "password";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println ("connected");
			return conn;
		} catch (Exception e) { System.out.println(e); }
		return null;
		
	}
	
	public static Connection getConnection() throws Exception{

		    System.out.println("----MySQL JDBC Connection Testing -------");
		     try {
		        Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        System.out.println("JDBC Driver non trovato");
		        e.printStackTrace();
		        return null;
		    }
		     System.out.println("Driver registrato");
		    Connection connection = null;
		    try {
		        connection = DriverManager.getConnection("jdbc:mysql://" + "sql7.freesqldatabase.com" 
		        + ":" + 3306 + "/" + "sql7317801", "sql7317801" ,"JSfK3lPNgq");
		        if(connection!=null) System.out.println("connessione effettuata");
		    } catch (SQLException e) {
		        System.out.println("Connessione fallita!:\n" + e.getMessage());
		    }
			return connection;

		}
		

	
	public void AggiungiArticoloAlMagazzino(String Nome, String Codice, String prezzo, String fotoPath, String Taglia, String Colore) {
		Double d = new Double(prezzo);
		Articolo ArticoloDaAggiungere = new Articolo(Nome, Codice, d, fotoPath, Taglia, Colore);
		try {
			if(checkQuantitaArticoloMagazzinoSQL(ArticoloDaAggiungere)==null) {
				System.out.println("Nuovo articolo, non presente in magazzino");
			if(AggiungiArticoloAlMagazzinoSQL(ArticoloDaAggiungere)) {
			MagazzinoTransazionale.add(ArticoloDaAggiungere);
			 JFrame parent = new JFrame();
			 JOptionPane.showMessageDialog(parent, "Articoli aggiunti correttamente");
			}
			AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		}
			else {
				incrementaQuantitaArticoloMagazzinoDB(ArticoloDaAggiungere);
				JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(parent, "Quantità aggiornata!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "ERRORE: ID NON VALIDO", "Riprovare",
			        JOptionPane.ERROR_MESSAGE);
		}
	
	
	}
		
		
	public boolean AggiungiArticoloAlMagazzinoSQL(Articolo ArticoloDaAggiungere) throws SQLException{
			ArticoloDAO a = new ArticoloDAO(connessione);
			a.InserisciArticoloInMagazzino(ArticoloDaAggiungere);
			return true;
		}
		
	public static void creaTabellaMagazzinoSQL() throws Exception {
		try {
			PreparedStatement create = connessione.prepareStatement("CREATE TABLE IF NOT EXISTS Magazzino (nome varchar(255),"
					+ "id int NOT NULL AUTO_INCREMENT, prezzo double, pathfoto varchar(200), taglia char(2),"
					+ "colore varchar(10), quantita int NOT NULL, PRIMARY KEY (id))");
			create.executeUpdate();	
		} catch (Exception e) {System.out.println(e);}
		finally{System.out.println("Function Completed");}
	}
	
	public void incrementaQuantitaArticoloMagazzinoDB (Articolo a) throws SQLException {
		String ID = a.getId();
		int quantitaPrecedente = checkQuantitaArticoloMagazzinoSQL(a);
		String sql = "UPDATE Magazzino SET quantita=? WHERE id = ?";
		
		PreparedStatement updateQuantita = connessione.prepareStatement(sql);
		updateQuantita.setLong(1, (quantitaPrecedente+1));
		updateQuantita.setString(2, ID);
		updateQuantita.executeUpdate();
	}
	
	public void decrementaQuantitaArticoloMagazzinoDB (Articolo a) throws SQLException {
		String ID = a.getId();
		String sql = "UPDATE Magazzino"
				+ "set quantita=quantita-1"
				+ "where id =?";
		
		PreparedStatement updateQuantita = connessione.prepareStatement(sql);
		updateQuantita.setString(1, ID);
		updateQuantita.executeUpdate();
	}
	
	public void RimuoviArticoliDalCarrello (Articolo ArticoloDaRimuovere, int quantita) {
		while(quantita>0) {
			if(CarrelloUtente.remove(ArticoloDaRimuovere)) {
				quantita--;
				MagazzinoTemporaneo.add(ArticoloDaRimuovere);
				AggiornaLabelCarrello();
			}
			if(CarrelloVuoto()) {
				chiudiTutteLeFinestre();
				CarrelloFrame = new CarrelloFrame(this);
			}
		
	}
		AggiornaFrameCarrello();
}
	
	public boolean CarrelloVuoto () {
		if (CarrelloUtente.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Carrello Vuoto", "Carrello vuoto",
			      JOptionPane.ERROR_MESSAGE);
			AggiungiAlCarrelloFrame.setVisible(true);
			CarrelloFrame.setVisible(true);
			return true;
			
		}
		return false;
		
	}
		
	public void RiempiComboAggiungiAlCarrello (JComboBox<Articolo> articoloBox) {
		for (Articolo a: MagazzinoTemporaneo) {
			articoloBox.addItem(a);
		}		
	}
	
	public void RiempiComboRimuoviDalCarrello (JComboBox<Articolo> articoloBox) {
		for (Articolo a: CarrelloUtente) {
			articoloBox.addItem(a);
		}
	}
	
	public void openAggiungiAlCarrello () {
	 AggiungiAlCarrelloFrame.setVisible(true);
	}
	
	public void openRimuoviDalCarrello() {
		RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(this);
		RimuoviDalCarrelloFrame.setVisible(true);
	}
	
	public void TerminaInserimentoArticoli() {
		CreaVetrina();
		HomePage.setVisible(true);
	}
	
	public void AggiungiAlCarrello (Articolo articoloSelezionato, int quantita) throws SQLException{
		int quantitaDisponibileInMagazzino = checkQuantitaArticoloMagazzinoSQL(articoloSelezionato);
		if(quantitaDisponibileInMagazzino>= quantita && MagazzinoTemporaneo.contains(articoloSelezionato)) {
		while(quantita>0) {
		CarrelloFrame.setVisible(false);
		AggiungiArticoloAlCarrelloUtente(articoloSelezionato);
		AggiornaLabelCarrello();
		quantita--;
		MagazzinoTemporaneo.remove(articoloSelezionato);
		
		}
		CarrelloFrame.setVisible(true);
	}
		else JOptionPane.showMessageDialog(new JFrame(), "Quantita in magazzino non disponibile", "Riduci la quantita",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	public void AggiungiArticoloAlCarrelloUtente (Articolo articoloSelezionato) {
		if(CheckCarrelloPieno()) {
		CarrelloUtente.add(articoloSelezionato);
		}
	}
		
	public double EseguiTotale () {
		double Totale=0.0;
		for (Articolo a: CarrelloUtente) {
			Totale = Totale + a.getPrice();
		}
		return Totale;
		

	}
	
	public void AggiornaLabelCarrello () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame = new CarrelloFrame(this);
		CreaLabelTabella();
		int i=107, max=450, j=1; 
		for(Articolo a: CarrelloUtente) {
			if(i>=max){
				i=107;
				j=j+300; 
				}
			CreaLabelArticoloCarrello (i, j, a);
			i=i+17;
			}
		AggiornaFrameCarrello();
	
	}
	
	public void CreaLabelTabella () {
		int i=90;
		for (int j=1; j<900; j=j+300) {
		JLabel LabelNome = new JLabel();
		LabelNome.setText("Nome ");
		LabelNome.setBounds(j, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelNome);
		
		JLabel LabelPrezzo = new JLabel();
		LabelPrezzo.setText(" -  $");
		LabelPrezzo.setBounds(j+80, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelPrezzo);
		
		}
		
	}
	
	public void AggiornaFrameCarrello () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame.setVisible(true);
	}
	
	public void AggiornaFrameVetrina() {
		VetrinaFrame.setVisible(false);
		VetrinaFrame.setVisible(true);
	}
		
	public void CreaLabelArticoloCarrello (int i, int j, Articolo a) {

		JLabel LabelNome = new JLabel();
		LabelNome.setText(a.getName());
		LabelNome.setBounds(j, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelNome);
		
		JLabel LabelPrezzo = new JLabel();
		Double d = new Double(a.getPrice());
		String prezzo = Double.toString(d);
		LabelPrezzo.setText(" - " +prezzo);
		LabelPrezzo.setBounds(j+80, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelPrezzo);
		
		CreaPulsanteVisualizzaArticolo(i, j, a);
		
		SwingUtilities.updateComponentTreeUI(CarrelloFrame);
		
	}
	
	public void CreaPulsanteVisualizzaArticolo(int i, int j,Articolo a){
		JButton btnVisualizzaArticolo = new JButton("Visualizza");
		ArticoloDaVisualizzare articoloVisualizzato = new ArticoloDaVisualizzare(a, this);
		btnVisualizzaArticolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				articoloVisualizzato.setVisible(true);
			}
		});
		btnVisualizzaArticolo.setBounds(j+130, i, 169, 29);
		CarrelloFrame.contentPane.add(btnVisualizzaArticolo);
	}
	
	public void CreaArticoloPerVetrina(int i, int j, Articolo a) {
		ArticoloDaVisualizzare ArticoloVisualizzato = new ArticoloDaVisualizzare (a, this);
		JLabel fotoLabel = ArticoloVisualizzato.getFotoLabel();
		JLabel articoloLabel = ArticoloVisualizzato.getArticoloLabel();
		JButton BottoneAggiungi = ArticoloVisualizzato.getBottone();
		fotoLabel.setBounds(j, i, 100, 100);
		articoloLabel.setBounds(j, i+105, 360, 18);
		BottoneAggiungi.setBounds(j, i+125, 100, 15);
		VetrinaFrame.AggiungiInVetrina(fotoLabel, articoloLabel, BottoneAggiungi);
		
		SwingUtilities.updateComponentTreeUI(VetrinaFrame);
		
	}
	
	public void CreaVetrina() {
		int i=30, max=350, j=30; 
		for(Articolo a: MagazzinoTransazionale) {
			if(i>=max){
				i=30;
				j=j+115; 
				}
			CreaArticoloPerVetrina(i, j, a);
			i=i+150;
			}
		AggiornaFrameVetrina();
	}
	
	public boolean CheckCarrelloPieno () {
		if ( CarrelloUtente.size() >= 63) {
			JOptionPane.showMessageDialog(new JFrame(), "Carrello Pieno", "Carrello Pieno",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
			
	}


	public void ApriSchermataPagamento() {
		if (CarrelloUtente.size()==0) JOptionPane.showMessageDialog(new JFrame(), "Carrello Vuoto", "Carrello Vuoto",
		        JOptionPane.ERROR_MESSAGE);
		else {	
		PagamentoFrame = new PagamentoFrame(this);
		PagamentoFrame.setVisible(true);
		}
	}


	public void effettuaTransazione() {
		for (Articolo a: CarrelloUtente) {
			try {
				int quantita = checkQuantitaArticoloMagazzinoSQL(a);
				if(quantita>1) {
					decrementaQuantitaArticoloMagazzinoDB(a);
				}
				eliminaArticoloDalMagazzinoSQL(a.getId());
			} catch (SQLException e) {
			
		}
			MagazzinoTransazionale.remove(a);
			AggiungiAlCarrelloFrame.rimuoviArticoloBox(a);
			CarrelloFrame.setVisible(false);
			
			JFrame parent = new JFrame();
			 JOptionPane.showMessageDialog(parent, "Pagamento Effettuato Correttamente");
			 	ChiudiProgramma();
	
	}


	
}
	 
	public Integer checkQuantitaArticoloMagazzinoSQL (Articolo articoloDaControllare) throws SQLException {
		String sql = "SELECT quantita FROM Magazzino where id =? AND nome=? AND prezzo=? AND taglia=? AND colore=?";
		PreparedStatement getQuantita = connessione.prepareStatement(sql);
		getQuantita.setString(1, articoloDaControllare.getId());
		getQuantita.setString(2, articoloDaControllare.getName());
		getQuantita.setLong(3, (long) articoloDaControllare.getPrice());
		getQuantita.setString(4, articoloDaControllare.getTaglia());
		getQuantita.setString(5, articoloDaControllare.getColore());
		
		
		ResultSet result = getQuantita.executeQuery();
		while(result.next()) {
			Integer quantita = new Integer(result.getString(1));
			System.out.println(quantita);
			return quantita;
			}
		return null;
	}
	

	public static void eliminaArticoloDalMagazzinoSQL(String Id) throws SQLException {
		try {
		PreparedStatement st = connessione.prepareStatement("DELETE FROM Magazzino WHERE id = ?");
        st.setString(1, Id);
        st.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		}
	
		public void ChiudiProgramma() {
		System.exit(0);
		
	}


		public void CancellaDatiMagazzino() throws SQLException {
		if(MagazzinoTransazionale.size()==0) 
			JOptionPane.showMessageDialog(new JFrame(), "Magazzino Vuoto", "Il magazzino è vuoto",
		        JOptionPane.ERROR_MESSAGE);
			else {
				for (Articolo a: MagazzinoTransazionale) {
					eliminaArticoloDalMagazzinoSQL(a.getId());
					}
				MagazzinoTransazionale.clear();
				JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(parent, "Magazzino resettato con successo!");
			}
	}


		protected ImageIcon createImageIcon(String path, String description) {
			java.net.URL imgURL = getClass().getResource(path);
				return new ImageIcon(imgURL, description);
			
	}


		public void apriSchermataVetrina() {
			chiudiTutteLeFinestre();
			VetrinaFrame=new VetrinaFrame(this);
			CreaVetrina();
		VetrinaFrame.setVisible(true);
			
		}


		public void apriSchermataCarrello() {
			chiudiTutteLeFinestre();
			CarrelloFrame = new CarrelloFrame(this);
			CarrelloFrame.setVisible(true);
			
		}


		public void apriSchermataMagazzino() {
			if(MagazzinoTransazionale.size()==0) 
				JOptionPane.showMessageDialog(new JFrame(), "Magazzino Vuoto", "Il magazzino è vuoto",
			        JOptionPane.ERROR_MESSAGE);
				else {
				chiudiTutteLeFinestre();
				MagazzinoFrame = new MagazzinoFrame(this);
				MagazzinoFrame.setVisible(true);
				}
		}


		public void apriSchermataAggiungiAlMagazzino() {
			InserimentoArticoloInMagazzinoFrame = new InserimentoArticoloInMagazzinoFrame(this);
			InserimentoArticoloInMagazzinoFrame.setVisible(true);
			
		}


		public void apriSchermataEliminaDaMagazzino() {
			try {
				if(MagazzinoTransazionale.size()==0) 
					JOptionPane.showMessageDialog(new JFrame(), "Magazzino Vuoto", "Il magazzino è vuoto",
				        JOptionPane.ERROR_MESSAGE);
					else {
						EliminaDaMagazzinoFrame = new EliminaDaMagazzinoFrame(this);
						EliminaDaMagazzinoFrame.setVisible(true);
					}
			}
			catch(Exception e) {
				e.printStackTrace();
	JOptionPane.showMessageDialog(new JFrame(), "ERRORE MAGAZZINO", "ERRORE MAGAZZINO",
				        JOptionPane.ERROR_MESSAGE);
	
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
			HomePage = new HomePage(this);
			HomePage.setVisible(true);
			
		}


		public void RiempiComboEliminaDaMagazzino(JComboBox<Articolo> articoloBox) {
			for (Articolo a: MagazzinoTemporaneo) {
				articoloBox.addItem(a);
			}	
			
		}


		public void RimuoviArticoloDalMagazzino(Articolo articoloSelezionato) throws SQLException {
			if(MagazzinoTransazionale.remove(articoloSelezionato)) {
				MagazzinoTemporaneo.remove(articoloSelezionato);
					eliminaArticoloDalMagazzinoSQL(articoloSelezionato.getId());
					}
				else if(MagazzinoTemporaneo.isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Magazzino Vuoto", "Il magazzino è vuoto",
					        JOptionPane.ERROR_MESSAGE);
				}

				EliminaDaMagazzinoFrame.setVisible(false);
				HomePage.setVisible(true);
			
		
			
			
			
		}


		public void riempiMagazzinoFrame() {
			int i=30, max=350, j=30; 
			for(Articolo a: MagazzinoTransazionale) {
				if(i>=max){
					i=30;
					j=j+115; 
					}
				CreaLabelArticoloMagazzino(i, j, a);
				i=i+150;
				}
			MagazzinoFrame.setVisible(false);
			MagazzinoFrame.setVisible(true);
		}



		private void CreaLabelArticoloMagazzino(int i, int j, Articolo a) {
			JLabel articoloLabel = new JLabel(a.toString());
			articoloLabel.setBounds(j, i+105, 360, 18);
			MagazzinoFrame.AggiungiInMagazzinoFrame(articoloLabel);
			SwingUtilities.updateComponentTreeUI(MagazzinoFrame);
		}
			
			
	
	
	

	
}
	
	



