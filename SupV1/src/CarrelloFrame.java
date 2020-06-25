
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class CarrelloFrame extends JFrame {

	JPanel contentPane;
	private NegozioController Controller;

	public CarrelloFrame(NegozioController ctrl)  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		  Totale.setHorizontalAlignment(SwingConstants.TRAILING);
		  Totale.setBounds(645, 568, 166, 16);
		  double Total = Controller.eseguiTotale();
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
				Controller.apriSchermataPagamento();
			}
		});
		btnNewButton.setBounds(823, 514, 146, 90);
		ImageIcon pagaImg = Controller.createImageIcon("BottonePagamento.png", "");
		btnNewButton.setIcon(pagaImg);
		contentPane.add(btnNewButton);
		
		JButton HomeBotton = new JButton("Home");
		HomeBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
		});
		HomeBotton.setBounds(560, 563, 117, 29);
		contentPane.add(HomeBotton);
		
		
		JLabel lblNewLabel = new JLabel("Elenco articoli: ");
		 lblNewLabel.setBounds(40, 34, 365, 185);
		 
		
	}
	

}
