package obat;

import java.awt.EventQueue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stok extends JInternalFrame {
	private JTable table;
	private JTextField txtNProduk;
	private DefaultTableModel tabelModel;
	private JComboBox idp;
	DefaultComboBoxModel model = new DefaultComboBoxModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stok frame = new Stok();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Stok() {
		setBounds(100, 100, 450, 508);
		this.setTitle("Stok");
		getContentPane().setLayout(null);
		
		final JSpinner st = new JSpinner();
		st.setBounds(113, 352, 60, 20);
		getContentPane().add(st);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(10, 11, 600, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("Stok Produk");
		table.setModel(tabelModel);	
        tampilTabel();
		
		txtNProduk = new JTextField();
		txtNProduk.setBounds(113, 324, 349, 20);
		getContentPane().add(txtNProduk);
		txtNProduk.setColumns(10);
		
		final JComboBox idpr = new JComboBox();
		idpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String id=idpr.getSelectedItem().toString();
		        try {
		            Connection konek = Konek.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama from Product where id='"+id+"'");
		            while (rs.next()) {
		            	txtNProduk.setText(rs.getString(1));
		            }
		        } catch (Exception e1) {
		        }
			}
		});
		idpr.setBounds(113, 293, 122, 20);
		getContentPane().add(idpr);
		
		
		JButton btnUbah = new JButton("Ubah Stok");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Konek.getCon();
			        String query = "UPDATE stok SET  stok = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setInt(1, (Integer) st.getValue()); //menambah stok
			        prepare.setString(2, idpr.getSelectedItem().toString());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			           //txtIDSup.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(113, 393, 122, 23);
		getContentPane().add(btnUbah);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(10, 327, 104, 14);
		getContentPane().add(lblNamaProduk);
		
		JLabel lblStokProduk = new JLabel("Stok Produk :");
		lblStokProduk.setBounds(10, 355, 104, 14);
		getContentPane().add(lblStokProduk);
	
		
		JLabel lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(10, 296, 104, 14);
		getContentPane().add(lblIdProduk);
		
		
		
		//akses method isiProduct
		isiProduct();
		//model comboBox di set sesuai comboBoxModel pada method isiProduct
		idpr.setModel(model);

	}
	private void isiProduct() {
		// TODO Auto-generated method stub
		try {
            Connection konek = Konek.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select id from Product");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}

	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Konek.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT p.id, p.nama, s.stok FROM stok s, Product p where s.id=p.id"; //mengambil id product
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
