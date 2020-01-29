
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JComboBox;
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
import javax.swing.UIManager;

public class SupermercatoController {

	ArrayList<Articolo> Magazzino;
	ArrayList<Articolo> CarrelloComboBox;
	ArrayList<Articolo> CarrelloUtente;
	SchermataCarrello CarrelloFrame;
	AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame;
	RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame;
	
	
	
	
	public static void main(String[] args) {
		SupermercatoController TheController = new SupermercatoController();
		InserimentoArticoloInMagazzinoFrame frame = new InserimentoArticoloInMagazzinoFrame(TheController);
		frame.setVisible(true);
		AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(TheController);
		RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(TheController);

	}
	
	
	public SupermercatoController() {
		Magazzino = new ArrayList<Articolo>();
		CarrelloUtente = new ArrayList <Articolo>();
		CarrelloFrame = new SchermataCarrello(this);
		CarrelloComboBox = new ArrayList<Articolo>();
	}
	
	//File file
	public void AggiungiArticolo(String Nome, String Codice, String prezzo, String fotoPath, String Taglia, String Colore) {
		try {
		Double d = new Double(prezzo);
		Articolo ArticoloDaAggiungere = new Articolo(Nome, Codice, fotoPath, Taglia, Colore);
		int IdArtDaAgg = Integer.parseInt(ArticoloDaAggiungere.getId());
		Magazzino.add(ArticoloDaAggiungere);
		AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Inserire codice valido", "Inserire codice valido",
			        JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
	public void RimuoviArticoliDalCarrello (String s, int quantita) {
		while(quantita>0) {
			RimuoviArticoloFantasma(s);
			Articolo ArticoloDaRimuovere = new Articolo (s, " - ", 0);
			if(EliminaArticoloDalCarrelloCombobox(ArticoloDaRimuovere)) quantita--;
			if(CarrelloVuoto()) RimuoviDalCarrelloFrame.setVisible(false);
		
	}
		AggiornaFrameCarrello();
}
	
	
	public boolean EliminaArticoloDalCarrelloCombobox (Articolo a) {
		for(Articolo art: CarrelloComboBox) {
			if(art.getName() == a.getName()) {
					if(CarrelloComboBox.remove(art)) {
						AggiornaLabelArticolo();
						
						break; } }
	} return true; 
}
		

	public boolean CarrelloVuoto () {
		if (CarrelloComboBox.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Carrello Vuoto", "Carrello vuoto",
			        JOptionPane.ERROR_MESSAGE);
			RimuoviDalCarrelloFrame.setVisible(false);
			AggiungiAlCarrelloFrame.setVisible(true);
			CarrelloFrame.setVisible(true);
			return true;
		}
		else return false;
		
	}
		
	
	
	public void RiempiComboAggiungiAlCarrello (JComboBox<String> ArticoloBox) {
		for (Articolo a: Magazzino) {
			ArticoloBox.addItem(a.getName() + "-" + a.getPrice() + "-" + a.getId());
		}		
	}
	
	public void RiempiComboRimuoviDalCarrello (JComboBox <String> ArticoloBox) {
		for (Articolo a: CarrelloComboBox) {
			ArticoloBox.addItem(a.getName());
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
		CarrelloFrame.setVisible(true);
	}
	
	public void AggiungiAlCarrello (String articolo, int quantita){
		Articolo a = new Articolo (articolo, " ", 0);
		while(quantita>0) {
		CarrelloFrame.setVisible(false);
		AggiungiArticoloAlCarrelloUtente (articolo);
		CarrelloComboBox.add(a);
		AggiornaLabelArticolo();
		quantita--;
		}
		CarrelloFrame.setVisible(true);
	}
	
	
	public Articolo CreaElementoPerCarrelloUtente (String articolo) {
		String[] CampiArticolo = new String [3];
		if (articolo.contains("-"))
		CampiArticolo =  articolo.split("-");
		String Nome = new String (CampiArticolo[0]);
		Double d = new Double(CampiArticolo[1]);
		String Codice = new String (CampiArticolo[2]);
		Articolo tmp = new Articolo(Nome, Codice, d);
		return tmp;
		
	}
	
	public void AggiungiArticoloAlCarrelloUtente (String articolo) {
		if(CheckCarrelloPieno()) {
		Articolo tmp = CreaElementoPerCarrelloUtente(articolo);
		CarrelloUtente.add(tmp);
		}
	}
	
	public void RimuoviArticoloFantasma (String articolo) {
	Articolo tmp=CreaElementoPerCarrelloUtente(articolo);
			for(Articolo art: CarrelloUtente) {
				int i = Integer.parseInt(art.getId());
				int j = Integer.parseInt(tmp.getId());
					if((int)i==(int)j) {
						if(CarrelloUtente.remove(art)) 
							break; } } 
	}
		
		
	public double EseguiTotale () {
		double Totale=0.0;
		for (Articolo a: CarrelloUtente) {
			Double PrezzoSingolo = new Double(a.getPrice());
			Totale = Totale + PrezzoSingolo;
		}
	return Totale;
	}
	
	
	public void AggiornaLabelArticolo () {
		CarrelloFrame.setVisible(false);
	CarrelloFrame = new SchermataCarrello(this);
	CreaLabelTabella();
	int i=107, max=350, j=1; 
	for(Articolo a: CarrelloUtente) {
		if(i>=max){
			i=107;
			j=j+150; 
			}
		CreaLabelArticolo (i, j, a);
		i=i+17;
		}
	AggiornaFrameCarrello();
	
	}
	
	public void CreaLabelTabella () {
		int i=90;
		for (int j=1; j<700; j=j+150) {
		JLabel LabelNome = new JLabel();
		LabelNome.setText("Nome ");
		LabelNome.setBounds(j, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelNome);
		
		JLabel LabelPrezzo = new JLabel();
		LabelPrezzo.setText(" -  £");
		LabelPrezzo.setBounds(j+80, i, 360, 18);
		CarrelloFrame.contentPane.add(LabelPrezzo);
		
		}
		
	}
	
	public void AggiornaFrameCarrello () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame.setVisible(true);
	}
		
	
	
	
	
	public void CreaLabelArticolo (int i, int j, Articolo a) {
		
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
		
		SwingUtilities.updateComponentTreeUI(CarrelloFrame);
		
	}
	
	public boolean CheckCarrelloPieno () {
		if ( CarrelloUtente.size() >= 75) {
			JOptionPane.showMessageDialog(new JFrame(), "Carrello Pieno", "Carrello Pieno",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
			
	}

}
	
	



