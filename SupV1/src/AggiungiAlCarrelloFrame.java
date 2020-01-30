import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AggiungiAlCarrelloFrame extends JFrame {
	private NegozioController Controller;
	private final JPanel contentPanel = new JPanel();
	private JComboBox articoloBox;

	public AggiungiAlCarrelloFrame(NegozioController ctrl) {
		setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		setAlwaysOnTop(true);
		Controller = ctrl;
		setTitle("Aggiungi al Carrello");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
		
			JComboBox <Articolo> ArticoloBox  = new JComboBox();
			ArticoloBox.setBounds(200, 6, 211, 27);
			Controller.RiempiComboAggiungiAlCarrello(ArticoloBox);
			articoloBox=ArticoloBox;
			contentPanel.add(ArticoloBox);
			
		
		
		{
			JLabel lblArticolo = new JLabel("Articolo");
			lblArticolo.setBounds(27, 10, 61, 16);
			contentPanel.add(lblArticolo);
		}
		{
			JLabel lblQuantit = new JLabel("Quantità");
			lblQuantit.setBounds(27, 64, 61, 16);
			contentPanel.add(lblQuantit);
		}
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(200, 60, 85, 16);
		 comboBox.addItem(1);
		 comboBox.addItem(2);
		 comboBox.addItem(3);
		 comboBox.addItem(4);
		 comboBox.addItem(5);

		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Articolo ArticoloSelezionato;
						
						ArticoloSelezionato =  (Articolo) ArticoloBox.getSelectedItem();
						int QuantitaSelezionata=0;
						QuantitaSelezionata = (int) comboBox.getSelectedItem();
						 System.out.println("Hai selezionato la quantità: " + QuantitaSelezionata);
		
			   try {
				Controller.AggiungiAlCarrello(ArticoloSelezionato, QuantitaSelezionata);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			          System.out.println("Ho aggiunto al tuo carrello l'elemento " + ArticoloSelezionato);
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Termina");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void rimuoviArticoloBox(Articolo a){
		articoloBox.removeItem(a);
		
	}
	
}
