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
import java.awt.event.ActionEvent;

public class RimuoviDalCarrelloFrame extends JFrame {
	private SupermercatoController Controller;
	private final JPanel contentPanel = new JPanel();

	
	public RimuoviDalCarrelloFrame(SupermercatoController ctrl) {
		Controller = ctrl;
		setTitle("Rimuovi dal Carrello");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
		
			JComboBox <String> ArticoloBox  = new JComboBox();
			ArticoloBox.setBounds(200, 6, 211, 27);
			Controller.RiempiComboRimozione(ArticoloBox);
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
						String ArticoloSelezionato;
						
						ArticoloSelezionato = (String) ArticoloBox.getSelectedItem();
						int QuantitaSelezionata=0;
						QuantitaSelezionata = (int) comboBox.getSelectedItem();
						 System.out.println("Hai selezionato: " + QuantitaSelezionata);
		
			   Controller.RimuoviArticoloDalCarrello(ArticoloSelezionato, QuantitaSelezionata);
			         //  System.out.println("Frame " + ArticoloSelezionato);
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
}

