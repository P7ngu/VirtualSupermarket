import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormSpecs;

public class MagazzinoFrame extends JFrame {
	NegozioController Controller;
	Connection Connessione;
	JPanel contentPane;
	JScrollPane scrollPane;
	JPanel panel;
	
	public MagazzinoFrame(NegozioController ctrl) throws SQLException, Exception {
		setResizable(false);
		setTitle("Magazzino");
		Controller=ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 462);
		Connessione=Controller.returnConn();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		 Statement stmt = Connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("select * from Magazzino  NATURAL JOIN Articolo");
		 contentPane.setLayout(null);
		  
		  JButton btnNewButton = new JButton("Home");
		  btnNewButton.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		Controller.apriSchermataHome();
		  	}
		  });
		  btnNewButton.setBounds(6, 36, 82, 29);
		  contentPane.add(btnNewButton);
		  
		  JTable table = new JTable(buildTableModel(rs));
		  table.setFillsViewportHeight(true);
		  JScrollPane scrollPane_1 = new JScrollPane(table);
		  scrollPane_1.setBounds(100, 20, 635, 404);
		  contentPane.add(scrollPane_1);

	}
	
 
	
	public static DefaultTableModel buildTableModel(ResultSet rs)throws SQLException{
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
