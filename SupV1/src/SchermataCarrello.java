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
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 719, 470);
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
		btnAggiungiAlCarrello.setBounds(6, 413, 167, 29);
		contentPane.add(btnAggiungiAlCarrello);
		
		
		JButton RimuoviDalCarrellobutton = new JButton("Rimuovi dal carrello");
		RimuoviDalCarrellobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.openRimuoviDalCarrello();
			}
		});
		RimuoviDalCarrellobutton.setBounds(221, 413, 167, 29);
		contentPane.add(RimuoviDalCarrellobutton);
		
		JLabel Totale = new JLabel("New label");
		Totale.setBounds(438, 418, 120, 16);
		double Total = Controller.EseguiTotale();
		Totale.setText("Totale: " + Total);
		
		contentPane.add(Totale);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/carrello.png"));
		lblNewLabel_1.setBounds(161, 6, 457, 127);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/BottonePagamento.png"));
		btnNewButton.setBounds(567, 358, 146, 90);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Elenco articoli: ");
		 lblNewLabel.setBounds(40, 34, 365, 185);
		 
		
	}
}
