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
		contentPane.setBackground(Color.WHITE);
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
				try {
					Controller.apriSchermataCarrello();
				} catch (Exception e1) {
					Controller.creaMessaggioErroreDuranteOperazione("ERRORE!", "ERRORE");
				}
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
				} catch (Exception e1) {
					Controller.creaMessaggioErroreDuranteOperazione("Errore durante l'apertura del magazzino", "Errore");
					e1.printStackTrace();
				}
			}
		});
		ImageIcon iconVisualizzaMagazzino = Controller.createImageIcon("visualizzamagazzino2.png", "");
		visualizzaMagazzinobtn.setIcon(iconVisualizzaMagazzino);
		visualizzaMagazzinobtn.setBackground(Color.WHITE);
		visualizzaMagazzinobtn.setBounds(405, 218, 170, 120);
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
		resettaMagazzinobtn.setBounds(769, 218, 170, 120);
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
		aggiungiAMagazzinobtn.setBounds(405, 392, 170, 120);
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
		eliminaDaMagazzinobtn.setBounds(769, 392, 170, 120);
		contentPane.add(eliminaDaMagazzinobtn);
		
		JLabel lblNewLabel_1 = new JLabel("Pannello");
		ImageIcon iconPannelloUt = Controller.createImageIcon("pannelloutente1.png", "");
		lblNewLabel_1.setIcon(iconPannelloUt);
		lblNewLabel_1.setBounds(-12, 95, 310, 91);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPannello = new JLabel("Pannello");
		ImageIcon iconPannelloAdm = Controller.createImageIcon("pannelloadmin.png", "");
		lblPannello.setIcon(iconPannelloAdm);
		lblPannello.setBounds(514, 95, 310, 91);
		contentPane.add(lblPannello);
		
		JLabel lblNewLabel_2 = new JLabel("barra");
		ImageIcon iconBarra = Controller.createImageIcon("barra1.png", "");
		lblNewLabel_2.setIcon(iconBarra);
		lblNewLabel_2.setBounds(-23, 83, 128, 452);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblBarra_2 = new JLabel("barra");
		lblBarra_2.setIcon(iconBarra);
		lblBarra_2.setBounds(235, 83, 93, 452);
		contentPane.add(lblBarra_2);
		
		JLabel lblBarra = new JLabel("barra");
		ImageIcon iconBarraRossaVerticale = Controller.createImageIcon("barrarossaverticale.png", "");
		lblBarra.setIcon(iconBarraRossaVerticale);
		lblBarra.setBounds(328, 83, 93, 452);
		contentPane.add(lblBarra);
		
		JLabel lblBarra_1 = new JLabel("barra");
		lblBarra_1.setIcon(iconBarraRossaVerticale);
		lblBarra_1.setBounds(921, 83, 93, 452);
		contentPane.add(lblBarra_1);
		
		JButton buttonVisualizzaAcquisti = new JButton("");
		buttonVisualizzaAcquisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.AcquistiFrame.setVisible(true);
			}
		});
		ImageIcon iconVisAcquisti = Controller.createImageIcon("visualizzaacquisti.png", "");
		buttonVisualizzaAcquisti.setIcon(iconVisAcquisti);
		buttonVisualizzaAcquisti.setBackground(Color.WHITE);
		buttonVisualizzaAcquisti.setBounds(587, 218, 170, 120);
		contentPane.add(buttonVisualizzaAcquisti);
		
		JButton button_1 = new JButton("");
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(587, 392, 170, 120);
		contentPane.add(button_1);
	}
}
