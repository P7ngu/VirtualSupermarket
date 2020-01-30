import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MagazzinoFrame extends JFrame {

	JPanel contentPane;
	NegozioController Controller;
	
	public MagazzinoFrame(NegozioController ctrl) {
		Controller=ctrl;
		setResizable(false);
		setTitle("Magazzino");
		Controller=ctrl;
		setBounds(100, 100, 988, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	public void AggiungiInMagazzinoFrame(JLabel articoloLabel) {
		contentPane.add(articoloLabel);
	}

}
