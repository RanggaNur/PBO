package obat;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Login extends JInternalFrame {
	private JButton btnLogin;
	private JTextField txtUser;
	private JTextField txtPass;
	private JLabel lblWallMDI;
	private JLabel lblIcon;
	public static  String nama;
	private static int status;
	public static javax.swing.JInternalFrame f;
	public static javax.swing.JInternalFrame f2;

	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 375);
		getContentPane().setLayout(null);
		setVisible(true);
		f=new admin(); //memanggil form admin
		f2=new kasir(); // memanggil form kasir
		
		JLabel lblNo = new JLabel("Username : ");
		lblNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNo.setForeground(new Color(225,225,225));
		lblNo.setBounds(6, 150, 100, 15);
		getContentPane().add(lblNo);
		
		JLabel lblNama = new JLabel("Password : ");
		lblNama.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNama.setForeground(new Color(225,225,225));
		lblNama.setBounds(6, 190, 100, 15);
		getContentPane().add(lblNama);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection Con=Konek.getCon();
			        String sql = "Select * from login where username='"+txtUser.getText()+"' and password='" + txtPass.getText() + "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(3).equalsIgnoreCase("admin"))
			        {
			            JOptionPane.showMessageDialog(null, "Login Admin Berhasil");
			            status=0;
			            MUtama.tampil(f); 
			            nama=txtUser.getText(); //membedakan user login yang masuk
			            //this.dispose();
			        }else{
			        	JOptionPane.showMessageDialog(null, "Login Kasir Berhasil");
			        	status=1;
			        	nama=txtUser.getText();
			            MUtama.tampil(f2);  
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
			}
		});
		btnLogin.setBounds(63, 276, 111, 36);
		getContentPane().add(btnLogin);
		
		txtUser = new JTextField();
		txtUser.setBounds(100, 150, 209, 27);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(100, 190, 323, 27);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("/C:/Users/RANGGA/workspace/Kasir/src/Gambar/Pelanggan.png"));
		lblIcon.setBounds(10, 10, 160, 129);
		getContentPane().add(lblIcon);
		
		JTextPane txtpnPenjualanObatHerbal = new JTextPane();
		txtpnPenjualanObatHerbal.setForeground(Color.WHITE);
		txtpnPenjualanObatHerbal.setFont(new Font("Papyrus", Font.BOLD, 24));
		txtpnPenjualanObatHerbal.setBackground(Color.DARK_GRAY);
		txtpnPenjualanObatHerbal.setText("TOKO OBAT HERBAL");
		txtpnPenjualanObatHerbal.setBounds(140, 51, 354, 45);
		getContentPane().add(txtpnPenjualanObatHerbal);
		
		lblWallMDI = new JLabel("");
		lblWallMDI.setForeground(Color.WHITE);
		lblWallMDI.setIcon(new ImageIcon("/C:/Users/RANGGA/workspace/Kasir/src/Gambar//wallMDI.png"));
		lblWallMDI.setBounds(0, 0, 617, 375);
		getContentPane().add(lblWallMDI);

	}

	public static void hehe()
	{
		if (status==0){
		MUtama.tidaktampil(f);
		}else if (status==1)
		{
			MUtama.tidaktampil(f2);
		}
	}
}
