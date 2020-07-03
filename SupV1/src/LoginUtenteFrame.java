import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUtenteFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Nome_textField;
	private JTextField Password_textField;
	private NegozioController Controller;


	public LoginUtenteFrame(NegozioController ctrl) {
		setResizable(false);
		Controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBenvenutoaInserisciLe = new JLabel("Benvenuto/a, inserisci le tue credenziali per accedere. ");
		lblBenvenutoaInserisciLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoaInserisciLe.setFont(new Font("Malayalam MN", Font.BOLD, 19));
		lblBenvenutoaInserisciLe.setBounds(6, 6, 612, 77);
		contentPane.add(lblBenvenutoaInserisciLe);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(248, 95, 267, 26);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		Password_textField = new JTextField();
		Password_textField.setColumns(10);
		Password_textField.setBounds(248, 146, 267, 26);
		contentPane.add(Password_textField);
		
		JLabel lblNomeUtente = new JLabel("Nome Utente:");
		lblNomeUtente.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		lblNomeUtente.setBounds(94, 95, 142, 16);
		contentPane.add(lblNomeUtente);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		lblPassword.setBounds(130, 148, 106, 16);
		contentPane.add(lblPassword);
		
		JButton btnAccedi = new JButton("ACCEDI");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ControllaCorrettezzaPerLogin();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAccedi.setFont(new Font("PT Sans Caption", Font.BOLD, 18));
		btnAccedi.setBounds(373, 205, 142, 29);
		contentPane.add(btnAccedi);
		
		JButton btnRegistrati = new JButton("REGISTRATI");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ControllaCorrettezzaPerRegistrazione();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistrati.setFont(new Font("PT Sans Caption", Font.BOLD, 18));
		btnRegistrati.setBounds(130, 205, 142, 29);
		contentPane.add(btnRegistrati);
		
		
		
	}
	
	private void ControllaCorrettezzaPerLogin() throws SQLException {
		int lunghezzaNome = Nome_textField.getText().length();
		int lunghezzaPassword = Password_textField.getText().length();
		
		if((lunghezzaNome>0 && lunghezzaNome<=10) && (lunghezzaPassword>0 && lunghezzaPassword<=7)){
			try{
				Controller.verificaDatiUtente(Nome_textField.getText(), Password_textField.getText());
			} catch (Exception e) {
				Controller.creaMessaggioErroreDuranteOperazione("Nome utente già inserito", "Errore");
			}
				
		} else
			Controller.creaMessaggioErroreDuranteOperazione("Inserire Valori Corretti", "Errore Inserimento");
		
	}
	
	private void ControllaCorrettezzaPerRegistrazione() throws SQLException {
		int lunghezzaNome = Nome_textField.getText().length();
		int lunghezzaPassword = Password_textField.getText().length();
		
		if ((lunghezzaNome>0 && lunghezzaNome<=20) && (lunghezzaPassword>0 && lunghezzaPassword<=10)){
			Controller.CreaUtente(Nome_textField.getText(), Password_textField.getText());
				
		} else
			Controller.creaMessaggioErroreDuranteOperazione("Inserire Valori Corretti", "Errore Inserimento");
		
	}
}
