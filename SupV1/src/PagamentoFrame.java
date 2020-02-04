import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PagamentoFrame extends JFrame {

	private JPanel contentPane;
	private NegozioController Controller;
	private JTextField textField_Titolare;
	private JTextField textField_numCarta;
	private JTextField textField_cvc;

	public PagamentoFrame(NegozioController c) {
		setTitle("Paga ora");
		setResizable(false);
		setAlwaysOnTop(true);
		Controller=c;
		setBounds(100, 100, 440, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Titolare = new JTextField();
		textField_Titolare.setBounds(125, 79, 248, 26);
		contentPane.add(textField_Titolare);
		textField_Titolare.setColumns(10);
		
		textField_numCarta = new JTextField();
		textField_numCarta.setColumns(10);
		textField_numCarta.setBounds(125, 117, 248, 26);
		contentPane.add(textField_numCarta);
		
		textField_cvc = new JTextField();
		textField_cvc.setColumns(10);
		textField_cvc.setBounds(125, 155, 248, 26);
		contentPane.add(textField_cvc);
		
		JLabel lblNewLabel = new JLabel("Titolare Carta");
		lblNewLabel.setBounds(21, 84, 92, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Numero Carta");
		lblNewLabel_1.setBounds(21, 122, 87, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CVC/CVV");
		lblNewLabel_2.setBounds(21, 160, 92, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblScadenza = new JLabel("Scadenza");
		lblScadenza.setBounds(21, 198, 92, 16);
		contentPane.add(lblScadenza);
		
		JComboBox <Integer> meseBox = new JComboBox <Integer>();
		for (int i=1; i<13; i++)
			meseBox.addItem(i);
		meseBox.setBounds(125, 194, 64, 27);
		contentPane.add(meseBox);
		
		JComboBox <Integer>annoBox = new JComboBox <Integer>();
		for (int i=2020; i<2030; i++)
			annoBox.addItem(i);
		annoBox.setBounds(189, 194, 92, 27);
		contentPane.add(annoBox);
		
		JButton btnNewButton = new JButton("Invia Pagamento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.effettuaTransazione();
				} catch (SQLException e1) {
					Controller.creaMessaggioErroreDuranteOperazione("ERRORE", "RIPROVARE");
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(272, 226, 147, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("TOTALE = ");
		lblNewLabel_3.setText("TOTALE="+Controller.eseguiTotale()+"$");
		lblNewLabel_3.setBounds(21, 226, 210, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		ImageIcon icon = Controller.createImageIcon("scampay.png", "");
		lblNewLabel_4.setIcon(icon);
		lblNewLabel_4.setBounds(125, 6, 191, 53);
		contentPane.add(lblNewLabel_4);
	}
}
