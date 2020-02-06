import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private NegozioController Controller;
	
	public HomePage(NegozioController ctrl) {
		Controller=ctrl;
		
		setResizable(false);
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon icon = Controller.createImageIcon("welcome.png", "");
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(39, 0, 946, 101);
		contentPane.add(lblNewLabel);
		
		JButton visualizzaVetrinabtn = new JButton("");
		visualizzaVetrinabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataVetrina();
			}
		});
		visualizzaVetrinabtn.setBackground(Color.WHITE);
		ImageIcon iconVetrina = Controller.createImageIcon("visualizzavetrina2.png", "");
		visualizzaVetrinabtn.setIcon(iconVetrina);
		visualizzaVetrinabtn.setBounds(61, 392, 170, 120);
		contentPane.add(visualizzaVetrinabtn);
		
		JButton visualizzaCarrellobtn = new JButton("");
		visualizzaCarrellobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataCarrello();
			}
		});
		ImageIcon visualizzaCarrelloicon = Controller.createImageIcon("visualizzacarrello2.png", "");
		visualizzaCarrellobtn.setIcon(visualizzaCarrelloicon);
		visualizzaCarrellobtn.setBackground(Color.WHITE);
		visualizzaCarrellobtn.setBounds(61, 218, 170, 120);
		contentPane.add(visualizzaCarrellobtn);
		
		JButton visualizzaMagazzinobtn = new JButton("");
		visualizzaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.apriSchermataMagazzino();
				} catch (SQLException | IOException e1) {
					Controller.creaMessaggioErroreDuranteOperazione("Errore durante l'apertura del magazzino", "Errore");
					e1.printStackTrace();
				}
			}
		});
		ImageIcon iconVisualizzaMagazzino = Controller.createImageIcon("visualizzamagazzino2.png", "");
		visualizzaMagazzinobtn.setIcon(iconVisualizzaMagazzino);
		visualizzaMagazzinobtn.setBackground(Color.WHITE);
		visualizzaMagazzinobtn.setBounds(479, 218, 170, 120);
		contentPane.add(visualizzaMagazzinobtn);
		
		JButton resettaMagazzinobtn = new JButton("");
		resettaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.cancellaDatiMagazzino();
				} catch (SQLException e1) {
					Controller.creaMessaggioErroreDuranteOperazione("ERRORE", "RIPROVARE");
				}
			}
		});
		ImageIcon iconResettaMagazzino = Controller.createImageIcon("resettamagazzino2.png", "");
		resettaMagazzinobtn.setIcon(iconResettaMagazzino);
		resettaMagazzinobtn.setBackground(Color.WHITE);
		resettaMagazzinobtn.setBounds(739, 218, 170, 120);
		contentPane.add(resettaMagazzinobtn);
		
		JButton aggiungiAMagazzinobtn = new JButton("");
		aggiungiAMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataAggiungiAlMagazzino();
			}
		});
		ImageIcon iconAggiungiMagazzino = Controller.createImageIcon("aggiungiamagazzino2.png", "");
		aggiungiAMagazzinobtn.setIcon(iconAggiungiMagazzino);
		aggiungiAMagazzinobtn.setBackground(Color.WHITE);
		aggiungiAMagazzinobtn.setBounds(479, 392, 170, 120);
		contentPane.add(aggiungiAMagazzinobtn);
		
		JButton eliminaDaMagazzinobtn = new JButton("");
		eliminaDaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataEliminaDaMagazzino();
			}
		});
		ImageIcon iconEliminaDaMag = Controller.createImageIcon("eliminadamagazzino1.png", "");
		eliminaDaMagazzinobtn.setIcon(iconEliminaDaMag);
		eliminaDaMagazzinobtn.setBackground(Color.WHITE);
		eliminaDaMagazzinobtn.setBounds(739, 392, 170, 120);
		contentPane.add(eliminaDaMagazzinobtn);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		ImageIcon iconPannelloUt = Controller.createImageIcon("pannelloutente1.png", "");
		lblNewLabel_1.setIcon(iconPannelloUt);
		lblNewLabel_1.setBounds(-12, 95, 310, 91);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		ImageIcon iconPannelloAdm = Controller.createImageIcon("pannelloadmin.png", "");
		label.setIcon(iconPannelloAdm);
		label.setBounds(529, 95, 310, 91);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		ImageIcon iconBarra = Controller.createImageIcon("barra1.png", "");
		lblNewLabel_2.setIcon(iconBarra);
		lblNewLabel_2.setBounds(-23, 83, 128, 452);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(iconBarra);
		label_1.setBounds(235, 83, 93, 452);
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("New label");
		ImageIcon iconBarraRossaVerticale = Controller.createImageIcon("barrarossaverticale.png", "");
		label_3.setIcon(iconBarraRossaVerticale);
		label_3.setBounds(374, 83, 93, 452);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setIcon(iconBarraRossaVerticale);
		label_4.setBounds(921, 83, 93, 452);
		contentPane.add(label_4);
	}
}
