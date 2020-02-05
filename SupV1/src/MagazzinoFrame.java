import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MagazzinoFrame extends JFrame {

	JPanel contentPane;
	NegozioController Controller;
	
	public MagazzinoFrame(NegozioController ctrl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 800);
		Controller=ctrl;
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,2000));
		
		JScrollPane scrollFrame = new JScrollPane(contentPane);
		contentPane.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800,300));
		
		getContentPane().add(scrollFrame);
		setResizable(false);
		setTitle("Magazzino");
		Controller=ctrl;
		
		contentPane.setLayout(null);
		
		JButton btnTornaInHome = new JButton("Torna in Home");
		btnTornaInHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
			
		});
		btnTornaInHome.setBounds(566, 6, 165, 29);
		contentPane.add(btnTornaInHome);
	}
	
	public void AggiungiInMagazzinoFrame(JLabel articoloLabel) {
		contentPane.add(articoloLabel);
	}

}
