import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.BoxLayout;
import javax.swing.JTable;

public class AcquistiFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	NegozioController controller;
	Connection Connessione;


	public AcquistiFrame(NegozioController ctrl, Connection con) throws SQLException, Exception {
		controller = ctrl;
		Connessione = con;
		setResizable(false);
		setTitle("Storico Acquisti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 808, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		 Statement stmt = Connessione.createStatement();
		  ResultSet rs = stmt.executeQuery("select * from ComposizioneAcquisto as c natural join acquisto as a");
		  JTable table = new JTable(buildTableModel(rs));
		  table.setFillsViewportHeight(true);
		 contentPane.add(new JScrollPane(table));

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