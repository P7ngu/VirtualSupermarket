import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InserimentoArticoloFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Nome_textField;
	private JTextField Codice_textField;
	private JTextField Prezzo_textField;
	private SupermercatoController Controller;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InserimentoArticolo frame = new InserimentoArticolo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public InserimentoArticoloFrame(SupermercatoController ctrl) {
		Controller = ctrl;
		setTitle("Aggiunta Articoli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(48, 80, 66, 20);
		contentPane.add(lblNome);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(118, 83, 231, 19);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodice.setBounds(48, 130, 66, 20);
		contentPane.add(lblCodice);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrezzo.setBounds(48, 184, 66, 20);
		contentPane.add(lblPrezzo);
		
		Codice_textField = new JTextField();
		Codice_textField.setColumns(10);
		Codice_textField.setBounds(118, 133, 231, 19);
		contentPane.add(Codice_textField);
		
		Prezzo_textField = new JTextField();
		Prezzo_textField.setColumns(10);
		Prezzo_textField.setBounds(118, 187, 137, 19);
		contentPane.add(Prezzo_textField);
		
		JButton btnNewButton = new JButton("Aggiungi Articolo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllaCorrettezzaPerInserimento();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(69, 353, 173, 81);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Termina");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.TerminaInserimentoArticoli();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(315, 353, 115, 81);
		contentPane.add(btnNewButton_1);
	}
	
	
	private void ControllaCorrettezzaPerInserimento() {
		if ((Nome_textField.getText().length()>0)&&(Codice_textField.getText().length()>0)&&(Prezzo_textField.getText().length()>0)){
			Controller.AggiungiArticolo(Nome_textField.getText(), Codice_textField.getText(), Prezzo_textField.getText());
			Nome_textField.setText("");
			Codice_textField.setText("");
			Prezzo_textField.setText("");					
		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "Inserire Valori", "Errore Inserimento",
			        JOptionPane.ERROR_MESSAGE);
	}
	
}
