
import java.awt.Graphics;
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
	ArrayList<Articolo> CarrelloUtente;
	
	SchermataCarrello CarrelloFrame;
	AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame;
	RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame;
	
	
	
	
	public static void main(String[] args) {
		SupermercatoController TheController = new SupermercatoController();
		InserimentoArticoloFrame frame = new InserimentoArticoloFrame(TheController);
		frame.setVisible(true);
		AggiungiAlCarrelloFrame AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(TheController);
		RimuoviDalCarrelloFrame RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(TheController);

	}
	
	
	public SupermercatoController() {
		Magazzino = new ArrayList<Articolo>();
		CarrelloFrame = new SchermataCarrello(this);
		CarrelloUtente = new ArrayList<Articolo>();
		// RimuoviDalCarrelloFrame = new RimuoviDalCarrelloFrame(this);
		 //AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
	}
	
	
	public void AggiungiArticolo(String Nome, String Codice, String prezzo) {
		Double d = new Double(prezzo);
		Articolo tmp = new Articolo(Nome, Codice, d);
		Magazzino.add(tmp);
		System.out.println("Ho aggiunto in magazzino un oggetto di nome "+tmp.getName());
		AggiungiAlCarrelloFrame = new AggiungiAlCarrelloFrame(this);
		
		
	}
	
	public void RimuoviArticoloDalCarrello (String s, int quantita) {
		while(quantita>0) {
		Articolo a = new Articolo (s, " - ", 0);
			AggiornaLabelArticolo();
		for(Articolo art: CarrelloUtente) {
	if(art.getName() == a.getName()) {
			if(CarrelloUtente.remove(art)) {
			System.out.println("Ho eliminato un oggetto di nome "+ a.getName());
			quantita--;
			AggiornaLabelArticolo();
			break; } }
		} }
		if(CarrelloVuoto())
			RimuoviDalCarrelloFrame.setVisible(false);
	}
		

	public boolean CarrelloVuoto () {
		if (CarrelloUtente.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Carrello Vuoto", "Carrello vuoto",
			        JOptionPane.ERROR_MESSAGE);
		}
		return (CarrelloUtente.isEmpty());
		
	}
		
	
	
	public void RiempiCombo (JComboBox<String> ArticoloBox) {
		for (Articolo a: Magazzino) {
			ArticoloBox.addItem(a.getName() + " - " + a.getPrice() + " - " + a.getId());
		}		
	}
	
	public void RiempiComboRimozione (JComboBox <String> ArticoloBox) {
		for (Articolo a: CarrelloUtente) {
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
	
	public void AggiungiAlCarrello (String s, int q) {
		
		Articolo a = new Articolo (s, " - ", 0);
		while(q>0) {
		CarrelloUtente.add(a);
		AggiornaLabelArticolo();
		q--;
		StampaCarrelloUtente();
		}
		
	}
	
	
	public void AggiornaLabelArticolo () {
		CarrelloFrame.setVisible(false);
		CarrelloFrame = new SchermataCarrello(this);
		int i=3;
		int max=150;
		int j=1;
	for(Articolo a: CarrelloUtente) {
	if(i>=max) {
				i=3;
				j=j+100;
				}
		CreaLabelArticolo (i, j, a);
			i=i+13;
			}
		}
		
	
	
	
	
	public void CreaLabelArticolo (int i, int j, Articolo a) {
		CarrelloFrame.setVisible(false);
		JLabel NewLabel = new JLabel();
		NewLabel.setText(a.getName());
		NewLabel.setBounds(j, i, 360, 18);
		CarrelloFrame.contentPane.add(NewLabel);
		SwingUtilities.updateComponentTreeUI(CarrelloFrame);
		CarrelloFrame.setVisible(true);
		
	}
	
	public void StampaCarrelloUtente () {
		for(Articolo a: CarrelloUtente)
		System.out.println(a.getName());
	}
	
	
	
}


