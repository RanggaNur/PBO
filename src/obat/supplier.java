package obat;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class supplier extends JInternalFrame {
	private JTable table;
	private JTextField txtIDSup;
	private JTextField txtNSup;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplier frame = new supplier();
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
	public supplier() {
		setBounds(100, 100, 450, 508);
		this.setTitle("Supplier");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 600, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String ids = (String) tabelModel.getValueAt(a, 0);
		        txtIDSup.setText(ids);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        txtNSup.setText(nama);
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);	
        tampilTabel();
		
		txtIDSup = new JTextField();
		txtIDSup.setBounds(192, 291, 163, 20);
		getContentPane().add(txtIDSup);
		txtIDSup.setColumns(10);
		
		txtNSup = new JTextField();
		txtNSup.setColumns(10);
		txtNSup.setBounds(192, 322, 220, 20);
		getContentPane().add(txtNSup);
		
		JLabel lblIdSupplier = new JLabel("ID Supplier :");
		lblIdSupplier.setBounds(10, 294, 87, 14);
		getContentPane().add(lblIdSupplier);
		
		JLabel lblNamaSupplier = new JLabel("Nama Supplier :");
		lblNamaSupplier.setBounds(10, 325, 100, 14);
		getContentPane().add(lblNamaSupplier);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 try
		        {
		        Connection konek = Konek.getCon();
		        String query = "INSERT INTO Supplier VALUES(?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, txtIDSup.getText());
		        prepare.setString(2, txtNSup.getText());

		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            //refresh();
		        }
		}
	});
		btnTambah.setBounds(167, 375, 89, 23);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Konek.getCon();
			        String query = "UPDATE Supplier SET  nama = ? WHERE idSupp = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtNSup.getText());
			        prepare.setString(2, txtIDSup.getText());
			        
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
			           txtIDSup.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(266, 375, 89, 23);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection konek = Konek.getCon();
			            String query = "DELETE FROM Supplier WHERE idSupp = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, txtIDSup.getText());
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
			            prepare.close();
			        }
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtIDSup.setEnabled(true);
			            //refresh();
			        }
			}
		});
		btnHapus.setBounds(365, 375, 89, 23);
		getContentPane().add(btnHapus);

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Konek.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM Supplier";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

}
