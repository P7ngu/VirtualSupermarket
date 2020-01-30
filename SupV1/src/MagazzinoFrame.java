import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MagazzinoFrame extends JFrame {

	JPanel contentPane;
	NegozioController Controller;
	
	public MagazzinoFrame(NegozioController ctrl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Controller=ctrl;
		setResizable(false);
		setTitle("Magazzino");
		Controller=ctrl;
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTornaInHome = new JButton("Torna in Home");
		btnTornaInHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
			
		});
		btnTornaInHome.setBounds(60, 714, 165, 29);
		contentPane.add(btnTornaInHome);
	}
	
	public void AggiungiInMagazzinoFrame(JLabel articoloLabel) {
		contentPane.add(articoloLabel);
	}

}
