package obat;

import java.awt.EventQueue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produk extends JInternalFrame {
	private JTable table;
	private JTextField idProd;
	private JTextField idSup;
	private JTextField nmP;
	private JTextField hrg;
	private JComboBox nmS;
	private JLabel lblIdProduk;
	private JLabel lblNewLabel;
	private JLabel lblNamaProduk;
	private JLabel lblHarga;
	private DefaultTableModel tabelModel;
	DefaultComboBoxModel model = new DefaultComboBoxModel(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produk frame = new Produk();
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
	public Produk() {
		setBounds(100, 100, 450, 508);
		this.setTitle("Product");
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
		        
		        String idp = (String) tabelModel.getValueAt(a, 0);
		        idProd.setText(idp);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        nmP.setText(nama);
		        String ids = (String) tabelModel.getValueAt(a, 2);
		        idSup.setText(ids);
		        int hrgP = (Integer) tabelModel.getValueAt(a, 3);
		        hrg.setText(""+hrgP);
            	//nmS.setModel(null);
		        try {
		            Connection konek = Konek.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama from Supplier where idSupp='"+idp+"'");
		            while (rs.next()) {
		                nmS.addItem(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
		        
		        idProd.setEnabled(false);
		        nmP.setEnabled(false);
		        idSup.setEnabled(false);
		        hrg.setEnabled(true);
		        nmS.setEnabled(false);
			}
		});

		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
		table.setModel(tabelModel);	
        tampilTabel();
		
		idProd = new JTextField();
		idProd.setBounds(109, 272, 315, 20);
		getContentPane().add(idProd);
		idProd.setColumns(10);
		
		idSup = new JTextField();
		idSup.setColumns(10);
		idSup.setBounds(109, 304, 89, 20);
		getContentPane().add(idSup);
		
		nmP = new JTextField();
		nmP.setColumns(10);
		nmP.setBounds(109, 335, 315, 20);
		getContentPane().add(nmP);
		
		hrg = new JTextField();
		hrg.setColumns(10);
		hrg.setBounds(109, 366, 315, 20);
		getContentPane().add(hrg);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = Konek.getCon();
			        String query = "INSERT INTO Product VALUES(?,?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, idProd.getText());
			        prepare.setString(2, nmP.getText());
			        prepare.setString(3, idSup.getText());
			        prepare.setInt(4, Integer.parseInt(hrg.getText()));

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
		btnTambah.setBounds(109, 407, 89, 23);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Konek.getCon();
			        String query = "UPDATE Product SET  harga = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setInt(1, Integer.parseInt(hrg.getText()));
			        prepare.setString(2, idProd.getText());
			        
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
			            idProd.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(208, 407, 89, 23);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection konek = Konek.getCon();
			            String query = "DELETE FROM Product WHERE id = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, idProd.getText());
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
			            idProd.setEnabled(true);
			            //refresh();
			        }
			}
		});
		btnHapus.setBounds(307, 407, 89, 23);
		getContentPane().add(btnHapus);
		
		nmS = new JComboBox();
		nmS.setBounds(208, 304, 216, 20);
		getContentPane().add(nmS);
		
		
		//tampil kode supplier dari nama supplier yang dipilih di combo box
		nmS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama=nmS.getSelectedItem().toString();
		        try {
		            Connection konek = Konek.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select idSupp from Supplier where nama='"+nama+"'");
		            while (rs.next()) {
		                idSup.setText(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		// end
		
		lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(10, 275, 67, 14);
		getContentPane().add(lblIdProduk);
		
		lblNewLabel = new JLabel("ID Supplier :");
		lblNewLabel.setBounds(10, 307, 67, 14);
		getContentPane().add(lblNewLabel);
		
		lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(10, 338, 89, 14);
		getContentPane().add(lblNamaProduk);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setBounds(10, 369, 46, 14);
		getContentPane().add(lblHarga);
		
		//akses method isiSupplier
		isiSupplier();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		nmS.setModel(model);
		
		

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Konek.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM Product";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[4];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	//method isiSupplier untuk tampilkan nama supplier ke comboBox. Semua nama supplier di masukan ke comboBoxmodel
	public void isiSupplier()
	{
		try {
            Connection konek = Konek.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select nama from Supplier");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}

}
