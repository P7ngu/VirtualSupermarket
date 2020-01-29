import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SchermataCarrello extends JFrame {

	JPanel contentPane;
	private SupermercatoController Controller;

	/**
	 * Create the frame.
	 */
	public SchermataCarrello(SupermercatoController ctrl) {
		setTitle("Carrello Acquisti");
		Controller=ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAggiungiAlCarrello = new JButton("Aggiungi al carrello");
		btnAggiungiAlCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.openAggiungiAlCarrello();
			}
		});
		btnAggiungiAlCarrello.setBounds(6, 231, 167, 29);
		contentPane.add(btnAggiungiAlCarrello);
		
		
		JButton RimuoviDalCarrellobutton = new JButton("Rimuovi dal carrello");
		RimuoviDalCarrellobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.openRimuoviDalCarrello();
			}
		});
		RimuoviDalCarrellobutton.setBounds(277, 231, 167, 29);
		contentPane.add(RimuoviDalCarrellobutton);
		
		JLabel lblNewLabel = new JLabel("Elenco articoli: ");
		 lblNewLabel.setBounds(40, 34, 365, 185);
		 
		
	}
}
