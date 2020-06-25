import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;

public class AcquistiFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	NegozioController controller;
	Connection Connessione;


	public AcquistiFrame(NegozioController ctrl) throws SQLException, Exception {
		controller = ctrl;
		Connessione = controller.returnConn();
		setResizable(false);
		setTitle("Storico Acquisti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 808, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		  ResultSet rs = controller.riempiTabellaAcquistiFrame();
		  contentPane.setLayout(null);
		  JTable table = new JTable(buildTableModel(rs));
		  table.setForeground(Color.BLACK);
		  table.setFillsViewportHeight(true);
		 JScrollPane scrollPane = new JScrollPane(table);
		 scrollPane.setBounds(124, 6, 679, 527);
		 JButton btnNewButton = new JButton("Home");
		  btnNewButton.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		controller.apriSchermataHome();
		  	}
		  });
		  btnNewButton.setBounds(6, 36, 82, 29);
		  contentPane.add(btnNewButton);
		 contentPane.add(scrollPane);

	}
	
 
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException{
		java.sql.ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	       columnNames.add(metaData.getColumnName(column));
	    }
	
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }
	    };


	}
	
	


}