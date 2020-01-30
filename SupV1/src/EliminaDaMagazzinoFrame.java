import java.awt.BorderLayout;
import java.awt.EventQueue;
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
	private JComboBox articoloBox;
	NegozioController Controller;

	public EliminaDaMagazzinoFrame(NegozioController ctrl) {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Controller = ctrl;
		setTitle("Rimuovi dal Magazzino");
		setBounds(100, 100, 408, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
		
			JComboBox <Articolo> ArticoloBox  = new JComboBox();
			ArticoloBox.setBounds(100, 46, 211, 27);
			articoloBox=ArticoloBox;
			Controller.riempiComboEliminaDaMagazzino(ArticoloBox);
			contentPanel.add(ArticoloBox);
			
		
		{
			JLabel lblArticolo = new JLabel("Articolo");
			lblArticolo.setBounds(28, 50, 61, 16);
			contentPanel.add(lblArticolo);
		}
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
					Controller.rimuoviArticoloDalMagazzino(ArticoloSelezionato);
					Controller.creaMessaggioOperazioneEffettuataConSuccesso("Articolo eliminato dal magazzino");
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
