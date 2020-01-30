import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		setResizable(false);
		setTitle("Home");
		
		Controller=ctrl;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 991, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/welcome.png"));
		lblNewLabel.setBounds(39, 0, 946, 101);
		contentPane.add(lblNewLabel);
		
		JButton visualizzaVetrinabtn = new JButton("");
		visualizzaVetrinabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataVetrina();
			}
		});
		visualizzaVetrinabtn.setBackground(Color.WHITE);
		visualizzaVetrinabtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/visualizzavetrina2.png"));
		visualizzaVetrinabtn.setBounds(61, 392, 170, 120);
		contentPane.add(visualizzaVetrinabtn);
		
		JButton visualizzaCarrellobtn = new JButton("");
		visualizzaCarrellobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataCarrello();
			}
		});
		visualizzaCarrellobtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/visualizzacarrello2.png"));
		visualizzaCarrellobtn.setBackground(Color.WHITE);
		visualizzaCarrellobtn.setBounds(61, 218, 170, 120);
		contentPane.add(visualizzaCarrellobtn);
		
		JButton visualizzaMagazzinobtn = new JButton("");
		visualizzaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataMagazzino();
			}
		});
		visualizzaMagazzinobtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/visualizzamagazzino2.png"));
		visualizzaMagazzinobtn.setBackground(Color.WHITE);
		visualizzaMagazzinobtn.setBounds(479, 218, 170, 120);
		contentPane.add(visualizzaMagazzinobtn);
		
		JButton resettaMagazzinobtn = new JButton("");
		resettaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.CancellaDatiMagazzino();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		resettaMagazzinobtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/resettamagazzino2.png"));
		resettaMagazzinobtn.setBackground(Color.WHITE);
		resettaMagazzinobtn.setBounds(739, 218, 170, 120);
		contentPane.add(resettaMagazzinobtn);
		
		JButton aggiungiAMagazzinobtn = new JButton("");
		aggiungiAMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataAggiungiAlMagazzino();
			}
		});
		aggiungiAMagazzinobtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/aggiungiamagazzino2.png"));
		aggiungiAMagazzinobtn.setBackground(Color.WHITE);
		aggiungiAMagazzinobtn.setBounds(479, 392, 170, 120);
		contentPane.add(aggiungiAMagazzinobtn);
		
		JButton eliminaDaMagazzinobtn = new JButton("");
		eliminaDaMagazzinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataEliminaDaMagazzino();
			}
		});
		eliminaDaMagazzinobtn.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/eliminadamagazzino1.png"));
		eliminaDaMagazzinobtn.setBackground(Color.WHITE);
		eliminaDaMagazzinobtn.setBounds(739, 392, 170, 120);
		contentPane.add(eliminaDaMagazzinobtn);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/pannelloutente1.png"));
		lblNewLabel_1.setBounds(-12, 95, 310, 91);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/pannelloadmin.png"));
		label.setBounds(529, 95, 310, 91);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/barra1.png"));
		lblNewLabel_2.setBounds(-23, 83, 128, 452);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/barra1.png"));
		label_1.setBounds(235, 83, 93, 452);
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/barrarossaverticale.png"));
		label_3.setBounds(374, 83, 93, 452);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/barrarossaverticale.png"));
		label_4.setBounds(921, 83, 93, 452);
		contentPane.add(label_4);
	}
}
