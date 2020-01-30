import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VetrinaFrame extends JFrame {

	private JPanel contentPane;
	private NegozioController Controller;


	
public void aggiungiInVetrina(JLabel fotoLabel, JLabel articoloLabel, JButton BottoneAggiungi) {
	contentPane.add(fotoLabel);
	contentPane.add(articoloLabel);
	contentPane.add(BottoneAggiungi);
}
	
	public VetrinaFrame(NegozioController controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setResizable(false);
		setTitle("Vetrina");
		Controller=controller;
		setBounds(100, 100, 988, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		JButton btnNewButton = new JButton("Visualizza Carrello");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.aggiornaFrameCarrello();
			}
		});
		btnNewButton.setBounds(21, 523, 192, 29);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Torna in Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
		});
		button.setBounds(293, 523, 192, 29);
		contentPane.add(button);
		
	}
}
