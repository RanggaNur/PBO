package obat;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class kasir extends JInternalFrame {
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblIdTransaksi;
	private JButton btnCari;
	private JLabel lblNamaProduct;
	private JTextField textField_3;
	private JLabel lblHarga;
	private JTextField textField_4;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel label_1;
	private JLabel lblJumlahBarang;
	private JLabel label_2;
	private JLabel lblBayar;
	private JTextField textField_1;
	private JLabel lblRp;
	private JButton btnHitung;
	private JTable table;
	private DefaultTableModel tabelModel;
	private DefaultTableModel tabelModel1;
	private JLabel label;
	private JLabel lblNamaPembeli;
	private JButton btnMasuk;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli=0;
	private JPanel panel;
	private JComboBox comboBox;
	DefaultComboBoxModel model = new DefaultComboBoxModel();
	private JTable table_1;
	/**
	 * Create the frame.
	 */
	public void disable()
	{
		lblNamaPembeli.setEnabled(false);
		textField.setEnabled(false);
		btnMasuk.setEnabled(false);
	}
	public void enabled()
	{
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		comboBox.setEnabled(true);
		btnCari.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		textField_1.setEnabled(true);
		btnHitung.setEnabled(true);
		textField_3.setEnabled(true);
		textField_4.setEnabled(true);
	}
	public void setTanggal()
    {
        java.util.Date skrng = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        lblNewLabel.setText(kal.format(skrng));
    }
	public void autoNumber()
	{
		try
		{
			Connection konek = Konek.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM transaksi ";
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				label.setText("T001");
			}
			else{	
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					label.setText("T00"+IDPesan);

			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	public void autoDtrans()
	{
		try
		{
			Connection konek = Konek.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dtransaksi ";
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				dtrans="dt001";
			}
				else
				{
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					String IDFix = "00" + IDPesan;
					dtrans="dt" + IDFix;
				}
					
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	
	public void tampilTotal()
    {
        String kpesan=label.getText();
         try {
            Connection konek = Konek.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select SUM(subtotal) from dtransaksi where idtrans='"+kpesan+"'");
            while (rs.next()) {
                label_1.setText(""+rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
	public kasir() {
		autoDtrans();
		this.setTitle("Kasir");
		setBounds(100, 100, 1150, 600);
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli :");
		lblNamaPembeli.setBounds(10, 11, 90, 14);
		getContentPane().add(lblNamaPembeli);
		
		textField = new JTextField();
		textField.setBounds(121, 8, 170, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnMasuk = new JButton("Beli");
		btnMasuk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Konek.getCon();
		        String query = "INSERT INTO transaksi VALUES(?,?,?,?,0)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, label.getText());
		        prepare.setString(2, lblNewLabel.getText());
		        prepare.setString(3, Login.nama);
		        prepare.setString(4, textField.getText());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        tampilTabel();
		        disable();
		        enabled();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
			}
		});
		btnMasuk.setBounds(301, 7, 89, 23);
		getContentPane().add(btnMasuk);
		
		lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(705, 537, 83, 23);
		getContentPane().add(lblNewLabel);
		setTanggal();
		
		lblIdTransaksi = new JLabel("ID transaksi :");
		lblIdTransaksi.setBounds(665, 11, 100, 14);
		getContentPane().add(lblIdTransaksi);
		
		panel = new JPanel();
		panel.setBounds(10, 36, 830, 498);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIdProduct = new JLabel("ID Product :");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(10, 324, 69, 20);
		panel.add(lblIdProduct);
		
		btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String id=comboBox.getSelectedItem().toString();
		        try {
		            Connection konek = Konek.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama,harga from Product where id='"+id+"'");
		            while (rs.next()) {
		            	textField_3.setText(rs.getString(1));
		            	textField_4.setText(rs.getString(2));
		            }
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null, "Product yang anda masukkan Tidak Tersedia");
		        }
			}
		});
		btnCari.setEnabled(false);
		btnCari.setBounds(338, 323, 69, 23);
		panel.add(btnCari);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(10, 358, 95, 14);
		panel.add(lblNamaProduct);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(132, 355, 275, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(10, 389, 46, 14);
		panel.add(lblHarga);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(132, 386, 275, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(10, 420, 46, 14);
		panel.add(lblJumlah);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(132, 417, 46, 20);
		panel.add(spinner);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(850, 36, 274, 303);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		tabelModel1 = new DefaultTableModel();
        tabelModel1.addColumn("ID Produk");
        tabelModel1.addColumn("Nama Produk");
		table_1.setModel(tabelModel1);	
        tampilTabel1();
		
		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Konek.getCon();
		        String query = "INSERT INTO dtransaksi VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, dtrans);
		        prepare.setString(2, label.getText());
		        prepare.setString(3, comboBox.getSelectedItem().toString());
		        prepare.setInt(4, (Integer)spinner.getValue());
		        prepare.setInt(5,  Integer.valueOf(textField_4.getText())*(Integer) spinner.getValue());

		        prepare.executeUpdate();
		        jumlahBeli+=1;
		        label_2.setText(""+jumlahBeli);
		        JOptionPane.showMessageDialog(null, "Data pembelian disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data pembelian gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
		        
		        //tampil total
				autoDtrans();
		        
				tampilTotal();
		         
		         //update total database
		          try{
		        Connection konek = Konek.getCon();
		        String query = "UPDATE transaksi SET total = ? WHERE idtrans = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, Integer.parseInt(label_1.getText()));
		        prepare.setString(2, label.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal diubah");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		          
		          
		        //kurang stok  
		        try{
		        Connection konek = Konek.getCon();
		        String query = "UPDATE stok SET stok = stok-? WHERE id = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) spinner.getValue());
		        prepare.setString(2, (String) comboBox.getSelectedItem().toString());
		        
		        prepare.executeUpdate();
		        tampilTabel();
	            table.setModel(tabelModel);	
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(318, 448, 89, 23);
		panel.add(btnTambah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 810, 290);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Harga");
        tabelModel.addColumn("Jumlah");
        tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
        
		
		lblTotal = new JLabel("Total :");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(581, 327, 46, 14);
		panel.add(lblTotal);
		
		label_1 = new JLabel("-");
		label_1.setBounds(680, 327, 46, 14);
		panel.add(label_1);
		
		lblJumlahBarang = new JLabel("Jumlah Barang :");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(581, 358, 95, 14);
		panel.add(lblJumlahBarang);
		
		label_2 = new JLabel("-");
		label_2.setBounds(680, 358, 46, 14);
		panel.add(label_2);
		
		lblBayar = new JLabel("Bayar :");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(581, 389, 46, 14);
		panel.add(lblBayar);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(650, 386, 108, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblRp = new JLabel("Rp.");
		lblRp.setBounds(623, 389, 23, 14);
		panel.add(lblRp);
		
		btnHitung = new JButton("Hitung");
		btnHitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(textField_1.getText())-Integer.parseInt(label_1.getText());
	            JOptionPane.showMessageDialog(null, "Kembalian Anda : "+kembalian);
				autoNumber();
				tampilTabel();
				jumlahBeli=0;
			}
		});
		btnHitung.setEnabled(false);
		btnHitung.setBounds(669, 448, 89, 23);
		panel.add(btnHitung);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(132, 324, 139, 17);
		panel.add(comboBox);
		
		label = new JLabel("-");
		label.setBounds(742, 11, 46, 14);
		getContentPane().add(label);
		
		//akses method isiProduct
				isiProduct();
				//model comboBox di set sesuai comboBoxModel pada method isiProduct
				comboBox.setModel(model);
				
		
		autoNumber();
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
            String query = "SELECT dt.idbarang,p.nama,p.harga,dt.jumlah,dt.subtotal FROM transaksi t join dtransaksi dt on t.idtrans=dt.idtrans join Product p on dt.idbarang=p.id where dt.idtrans='"+label.getText()+"'";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
             
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	public void tampilTabel1()
    {
        tabelModel1.getDataVector().removeAllElements();
        tabelModel1.fireTableDataChanged();
        try
        {
            Connection konek = Konek.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT id, nama FROM Product "; //mengambil id product
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel1.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
