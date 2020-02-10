import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EliminaDaMagazzinoFrame extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JComboBox <Articolo> articoloBox;
	NegozioController Controller;

	public EliminaDaMagazzinoFrame(NegozioController ctrl) {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Controller = ctrl;
		setTitle("Rimuovi dal Magazzino");
		setBounds(100, 100, 404, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
		
			JComboBox <Articolo> ArticoloBox  = new JComboBox <Articolo>();
			ArticoloBox.setBounds(99, 30, 211, 27);
			articoloBox=ArticoloBox;
			try {
				Controller.riempiComboEliminaDaMagazzino(ArticoloBox);
			} catch (Exception e2) {
				e2.printStackTrace();
				Controller.creaMessaggioErroreDuranteOperazione("ERRORE", "RIPROVARE");
			}
			contentPanel.add(ArticoloBox);
			
		
		{
			JLabel lblArticolo = new JLabel("Articolo");
			lblArticolo.setBounds(27, 34, 61, 16);
			contentPanel.add(lblArticolo);
		}
		
		JLabel lblQuantit = new JLabel("Quantità");
		lblQuantit.setBounds(27, 73, 61, 16);
		contentPanel.add(lblQuantit);
		
		JComboBox<Integer> QuantitaBox = new JComboBox();
		QuantitaBox.addItem(1);
		QuantitaBox.addItem(2);
		QuantitaBox.addItem(3);
		QuantitaBox.addItem(4);
		QuantitaBox.addItem(5);
		QuantitaBox.setBounds(99, 69, 122, 27);
		contentPanel.add(QuantitaBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					JButton cancelButton = new JButton("Annulla");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							Controller.apriSchermataHome();
						}
					});
					JButton okButton = new JButton("Elimina");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Articolo ArticoloSelezionato = (Articolo) ArticoloBox.getSelectedItem();
							
		
			try {
				int QuantitaSelezionata = (int) QuantitaBox.getSelectedItem();
					for (int i=0; i<QuantitaSelezionata; i++) {
						int flag=i;
						Controller.rimuoviArticoloDalMagazzino(ArticoloSelezionato);
						if(flag==0) Controller.creaMessaggioOperazioneEffettuataConSuccesso("Articolo eliminato dal magazzino");
			
					}
			} catch (SQLException e1) {
				Controller.creaMessaggioErroreDuranteOperazione("ERRORE", "RIPROVARE");
			}
			      
						}
					});
					
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
}
