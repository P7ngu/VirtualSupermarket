import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class MagazzinoFrame extends JFrame {

	JPanel contentPane;
	JScrollPane scrollPane;
	NegozioController Controller;
	
	public MagazzinoFrame(NegozioController ctrl) {
		setResizable(false);
		setTitle("Magazzino");
		Controller=ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 400);
		Controller=ctrl;
		
		contentPane = new JPanel();

		scrollPane=new JScrollPane(contentPane, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		getContentPane().add(scrollPane);
		
		contentPane.setPreferredSize(new Dimension(300, 3000));
	
		
		
	
		JButton btnTornaInHome = new JButton("Torna in Home");
		btnTornaInHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.apriSchermataHome();
			}
			
		});
		btnTornaInHome.setBounds(172, 5, 137, 29);
		contentPane.add(btnTornaInHome);
		
		
	}
	
	public void AggiungiInMagazzinoFrame(JLabel articoloLabel) {
		contentPane.add(articoloLabel);
	
	
	}
	

}
