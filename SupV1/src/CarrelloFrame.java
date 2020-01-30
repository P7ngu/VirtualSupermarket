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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CarrelloFrame extends JFrame {

	static JPanel contentPane;
	static JScrollPane ScrollPane;
	private NegozioController Controller;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public CarrelloFrame(NegozioController ctrl)  {
		setBounds(100, 100, 1000, 650);
		setResizable(false);
		setTitle("Carrello Acquisti");
		Controller=ctrl;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAggiungiAlCarrello = new JButton("Aggiungi articoli al carrello");
		btnAggiungiAlCarrello.setBounds(27, 563, 213, 29);
		btnAggiungiAlCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.openAggiungiAlCarrello();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAggiungiAlCarrello);
		
		
		JButton RimuoviDalCarrellobutton = new JButton("Rimuovi articoli dal carrello");
		RimuoviDalCarrellobutton.setBounds(249, 563, 209, 29);
		RimuoviDalCarrellobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.openRimuoviDalCarrello();
			}
		});
		contentPane.add(RimuoviDalCarrellobutton);
		
		
		  JLabel Totale = new JLabel("New label");
		  Totale.setBounds(731, 568, 120, 16);
		  double Total = Controller.EseguiTotale();
		  Totale.setText("Totale: " + Total);
		  contentPane.add(Totale);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(312, 6, 457, 127); 
		ImageIcon icon = Controller.createImageIcon("carrello.png", "");
		lblNewLabel_1.setIcon(icon);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.ApriSchermataPagamento();
			}
		});
		btnNewButton.setBounds(823, 514, 146, 90);
		ImageIcon pagaImg = Controller.createImageIcon("BottonePagamento.png", "");
		btnNewButton.setIcon(pagaImg);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Torna in home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
		});
		btnNewButton_1.setBounds(514, 563, 192, 29);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblNewLabel = new JLabel("Elenco articoli: ");
		 lblNewLabel.setBounds(40, 34, 365, 185);
		 
		
	}
	

}
