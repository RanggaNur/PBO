package obat;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public User() {
		setBounds(100, 100, 450, 300);
		this.setTitle("User");
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(10, 130, 72, 14);
		getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(76, 127, 161, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 155, 72, 14);
		getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 152, 161, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Kasir"}));
		comboBox.setBounds(76, 180, 161, 20);
		getContentPane().add(comboBox);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(10, 183, 46, 14);
		getContentPane().add(lblStatus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 590, 108);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String uname = (String) tabelModel.getValueAt(a, 0);
		        textField.setText(uname);
		        String pass = (String) tabelModel.getValueAt(a, 1);
		        textField_1.setText(pass);
		        String stat = (String) tabelModel.getValueAt(a, 2);
		        comboBox.setSelectedItem(stat);
		        
		        textField.setEnabled(false);
			}
		});
		scrollPane_1.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
        tabelModel.addColumn("Status");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = Konek.getCon();
			        String query = "INSERT INTO login VALUES(?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, textField.getText());
			        prepare.setString(2, textField_1.getText());
			        prepare.setString(3, (String) comboBox.getSelectedItem());


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
		btnTambah.setBounds(76, 214, 89, 23);
		getContentPane().add(btnTambah);
		
		JButton button = new JButton("Ubah");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Konek.getCon();
			        String query = "UPDATE login SET  password = ?, status = ? WHERE username = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, textField_1.getText());
			        prepare.setString(2, (String) comboBox.getSelectedItem());
			        prepare.setString(3, textField.getText());
			        
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
			            textField.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		button.setBounds(175, 214, 89, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection konek = Konek.getCon();
			            String query = "DELETE FROM login WHERE username = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, textField.getText());
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
			            textField.setEnabled(true);
			            //refresh();
			        }
			}
		});
		button_1.setBounds(270, 214, 89, 23);
		getContentPane().add(button_1);

	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Konek.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM login";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
