import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ArticoloDaVisualizzare extends JFrame {
	
private Articolo articolo;
private NegozioController Controller;
private JLabel articoloLabel;

public JLabel getArticoloLabel() {
	return articoloLabel;
}

public void setArticoloLabel(JLabel articoloLabel) {
	this.articoloLabel = articoloLabel;
}

public JLabel getFotoLabel() {
	return fotoLabel;
}

public void setFotoLabel(JLabel fotoLabel) {
	this.fotoLabel = fotoLabel;
}

public JButton getBottone() {
	return bottone;
}

public void setBottone(JButton bottone) {
	this.bottone = bottone;
}

private JLabel fotoLabel;
private JButton bottone;

public Articolo getArticolo() {
	return articolo;
}

public void setArticolo(Articolo articolo) {
	this.articolo = articolo;
}

public void chiudiFrame() {
	this.setVisible(false);
}
public ArticoloDaVisualizzare(Articolo articoloVisualizzato, NegozioController ctrl) {
	setTitle("Articolo Visualizzato");
	setBounds(300, 300, 270, 250);
	setResizable(false);
	setAlwaysOnTop(true);
	Controller=ctrl;
	setArticolo(articoloVisualizzato);
	getContentPane().setLayout(null);
	
	JButton btnAggiungiAlCarrello = new JButton("Aggiungi");
	btnAggiungiAlCarrello.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Controller.aggiungiAlCarrello(articolo, 1);
				chiudiFrame();
			} catch (SQLException e1) {
				Controller.creaMessaggioErroreDuranteOperazione("ERRORE", "RIPROVARE");
			}
		}
	});
	btnAggiungiAlCarrello.setBounds(42, 151, 169, 29);
	setBottone(btnAggiungiAlCarrello);
	
	JLabel ArticoloLabel = new JLabel(articolo.toString());
	ArticoloLabel.setBounds(32, 123, 253, 16);
	getContentPane().add(ArticoloLabel);
	setArticoloLabel(ArticoloLabel);
	
	JLabel FotoLabel = new JLabel("Nessuna Foto");
	FotoLabel.setIcon(new ImageIcon(articolo.getPathFoto()));
	FotoLabel.setBounds(59, 6, 131, 116);
	getContentPane().add(FotoLabel);
	setFotoLabel(FotoLabel);
	
	JButton btnRimuovi = new JButton("Rimuovi");
	btnRimuovi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Controller.rimuoviArticoliDalCarrello(articolo,1);
			chiudiFrame();
		}
	});
	btnRimuovi.setBounds(42, 179, 169, 29);
	getContentPane().add(btnRimuovi);
	
}
}
