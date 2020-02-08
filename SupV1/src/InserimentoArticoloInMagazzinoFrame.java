import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class InserimentoArticoloInMagazzinoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Nome_textField;
	private JTextField Codice_textField;
	private JTextField Prezzo_textField;
	private NegozioController Controller;
	private JFileChooser fileChooser;
	private String pathFoto;

	public InserimentoArticoloInMagazzinoFrame(NegozioController ctrl) {
		setResizable(false);
		setAlwaysOnTop(true);
		Controller = ctrl;
		setTitle("Aggiunta Articoli al Magazzino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> TagliaBox = new JComboBox<String>();
		TagliaBox.addItem("S");
		TagliaBox.addItem("M");
		TagliaBox.addItem("L");
		TagliaBox.addItem("XL");
		TagliaBox.setBounds(102, 176, 127, 27);
		contentPane.add(TagliaBox);
		
		JComboBox<String> ColoreBox = new JComboBox<String>();
		ColoreBox.addItem("Rosso");
		ColoreBox.addItem("Blu");
		ColoreBox.addItem("Bianco");
		ColoreBox.addItem("Giallo");
		ColoreBox.addItem("Verde");
		ColoreBox.addItem("Nero");
		ColoreBox.addItem("Grigio");
		ColoreBox.setBounds(102, 207, 127, 27);
		contentPane.add(ColoreBox);
		
		JComboBox<Integer> QuantitaBox = new JComboBox<Integer>();
		QuantitaBox.setBounds(102, 239, 127, 27);
		QuantitaBox.addItem(1);
		QuantitaBox.addItem(2);
		QuantitaBox.addItem(3);
		QuantitaBox.addItem(4);
		QuantitaBox.addItem(5);
		QuantitaBox.addItem(50);
		contentPane.add(QuantitaBox);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(43, 80, 66, 20);
		contentPane.add(lblNome);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(102, 81, 231, 19);
		contentPane.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodice.setBounds(43, 112, 66, 20);
		contentPane.add(lblCodice);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrezzo.setBounds(43, 144, 66, 20);
		contentPane.add(lblPrezzo);
		
		Codice_textField = new JTextField();
		Codice_textField.setColumns(10);
		Codice_textField.setBounds(102, 112, 231, 19);
		contentPane.add(Codice_textField);
		
		Prezzo_textField = new JTextField();
		Prezzo_textField.setColumns(10);
		Prezzo_textField.setBounds(102, 145, 137, 19);
		contentPane.add(Prezzo_textField);
		
		
		JButton btnNewButton_1 = new JButton("Termina");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.terminaInserimentoArticoli();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(315, 350, 159, 35);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Magazzino");
		ImageIcon iconMagazzino = Controller.createImageIcon("magazzino.png", "");
		lblNewLabel.setIcon(iconMagazzino);
		lblNewLabel.setBounds(86, 6, 337, 137);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		ImageIcon iconScatola = Controller.createImageIcon("box.png", "");
		lblNewLabel_1.setIcon(iconScatola);
		lblNewLabel_1.setBounds(263, 58, 268, 204);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NB: Il codice del prodotto dev'essere composto di soli numeri");
		lblNewLabel_2.setBounds(41, 322, 467, 16);
		contentPane.add(lblNewLabel_2);
		JFileChooser fileChooser = new JFileChooser();
		
		JButton btnSelezionaImmagine = new JButton("Seleziona Immagine");
		btnSelezionaImmagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		pathFoto = 	SelezionaImmagine(fileChooser);
			}
		});
		btnSelezionaImmagine.setBounds(102, 272, 224, 29);
		contentPane.add(btnSelezionaImmagine);
		
		JLabel lblFoto = new JLabel("Foto");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFoto.setBounds(43, 274, 66, 20);
		contentPane.add(lblFoto);
		
		JLabel lblTaglia = new JLabel("Taglia");
		lblTaglia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTaglia.setBounds(43, 176, 66, 20);
		contentPane.add(lblTaglia);
		
		JLabel lblColore = new JLabel("Colore");
		lblColore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblColore.setBounds(43, 208, 66, 20);
		contentPane.add(lblColore);
		
		JLabel lblQuantit = new JLabel("Quantità");
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantit.setBounds(43, 240, 66, 20);
		contentPane.add(lblQuantit);
		
		
		JButton btnNewButton = new JButton("Aggiungi Articoli");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ControllaCorrettezzaPerInserimento(TagliaBox, ColoreBox, pathFoto, QuantitaBox);		
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(31, 350, 169, 35);
		contentPane.add(btnNewButton);
		
		
	
	}
	
	public String SelezionaImmagine (JFileChooser fileChooser) {
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    return selectedFile.getAbsolutePath();
		}
		return null;
	}
	
	private void ControllaCorrettezzaPerInserimento(JComboBox<String> TagliaBox, JComboBox<String> ColoreBox, String path, JComboBox<Integer> QuantitaBox) {
		int lunghezzaNome = Nome_textField.getText().length();
		int lunghezzaCodice = Codice_textField.getText().length();
		int lunghezzaPrezzo = Prezzo_textField.getText().length();
		if (lunghezzaNome>10) Controller.creaMessaggioErroreDuranteOperazione("ERRORE: NOME TROPPO LUNGO, MASSIMO CARATTERI=10", "RIPROVARE");
		if (lunghezzaPrezzo>7) Controller.creaMessaggioErroreDuranteOperazione("ERRORE: ID TROPPO LUNGO, MASSIMO CARATTERI=7", "RIPROVARE");
		
		if ((lunghezzaNome>0 && lunghezzaNome<10) && (lunghezzaCodice>0 && lunghezzaCodice<7) && (lunghezzaPrezzo>0)){
				try {
					String Taglia = (String)TagliaBox.getSelectedItem();
					String Colore = (String)ColoreBox.getSelectedItem();
				
					int QuantitaSelezionata = (int) QuantitaBox.getSelectedItem();

					for (int i=0; i<QuantitaSelezionata; i++) {
					 int flag = i;
					 Controller.aggiungiArticoloAlMagazzino(Nome_textField.getText(), Codice_textField.getText(), Prezzo_textField.getText(),
							 								 path, Taglia, Colore, flag);
					}
					if(path!=null) Controller.setFoto(path, Codice_textField.getText());
				 	} catch (Exception e) {
					Controller.creaMessaggioErroreDuranteOperazione("Immagine già inserita", "Errore Inserimento");
				}
		} else if (lunghezzaCodice == 0 || lunghezzaNome == 0 || lunghezzaPrezzo == 0)
			Controller.creaMessaggioErroreDuranteOperazione("Inserire Valori", "Errore Inserimento");
		
	}
	
}
