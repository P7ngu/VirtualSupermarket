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
import javax.swing.ImageIcon;
import java.awt.Color;

public class InserimentoArticoloInMagazzinoFrame extends JFrame {

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
	public InserimentoArticoloInMagazzinoFrame(SupermercatoController ctrl) {
		Controller = ctrl;
		setTitle("Aggiunta Articoli al Magazzino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(43, 106, 66, 20);
		contentPane.add(lblNome);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(102, 107, 231, 19);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodice.setBounds(43, 149, 66, 20);
		contentPane.add(lblCodice);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrezzo.setBounds(43, 194, 66, 20);
		contentPane.add(lblPrezzo);
		
		Codice_textField = new JTextField();
		Codice_textField.setColumns(10);
		Codice_textField.setBounds(102, 150, 231, 19);
		contentPane.add(Codice_textField);
		
		Prezzo_textField = new JTextField();
		Prezzo_textField.setColumns(10);
		Prezzo_textField.setBounds(102, 195, 137, 19);
		contentPane.add(Prezzo_textField);
		
		JButton btnNewButton = new JButton("Aggiungi Articolo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllaCorrettezzaPerInserimento();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(43, 291, 137, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Termina");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.TerminaInserimentoArticoli();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(290, 291, 137, 55);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/magazzino.png"));
		lblNewLabel.setBounds(84, 6, 337, 137);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/valentinaperotta/Desktop/GFX/box.png"));
		lblNewLabel_1.setBounds(263, 58, 268, 204);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NB: Il codice del prodotto dev'essere composto di soli numeri");
		lblNewLabel_2.setBounds(43, 246, 467, 16);
		contentPane.add(lblNewLabel_2);
	}
	
	
	private void ControllaCorrettezzaPerInserimento() {
		if ((Nome_textField.getText().length()>0)&&(Codice_textField.getText().length()>0)&&(Prezzo_textField.getText().length()>0)){
			try {
				Controller.AggiungiArticolo(Nome_textField.getText(), Codice_textField.getText(), Prezzo_textField.getText());
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Inserire Valori Validi", "Errore Inserimento",
				        JOptionPane.ERROR_MESSAGE);
				
			}
			Nome_textField.setText("");
			Codice_textField.setText("");
			Prezzo_textField.setText("");					
		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "Inserire Valori", "Errore Inserimento",
			        JOptionPane.ERROR_MESSAGE);
	}
}
