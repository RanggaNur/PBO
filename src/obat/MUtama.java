package obat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;

public class MUtama extends JFrame {
	private JButton btnLogin;
	private JMenuItem mi = new JMenuItem("Login");
	private JPanel contentPane;
	private JLabel lblWallMDI;
	private static Login lg = new Login ();
	private static JButton btnLogOut;
	
	public static JDesktopPane desktop;
	private JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public MUtama() {
		super("Form Utama");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1200,600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBounds(0,0,1200,600);
		getContentPane().add(desktop);
		mi.setSize(0, 0);
		desktop.add(mi);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(450, 249, 300, 100);
		desktop.add(btnLogin);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setVisible(false);
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnLogOut.setVisible(false);
				Login.hehe();
			}
		});
		menuBar.add(btnLogOut);

		/*desktop.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me){
				if(me.getButton() == 3) //klik kanan
				{
					pop.show(me.getComponent(),me.getX(),me.getY());
				}
			}
		});*/
		
		btnLogin.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent act)
			{
				desktop.add(lg);
				btnLogin.setVisible(false);
			}
		});
		
	}
	public static void tampil(javax.swing.JInternalFrame f){
		Dimension size=desktop.getSize();
		desktop.add(f);
		f.setVisible(true);
		f.setSize(size);
		f.setLocation(0,0);
		btnLogOut.setVisible(true);
	}
	public static void tidaktampil(javax.swing.JInternalFrame f){
		f.setVisible(false);
		desktop.removeAll();
		desktop.add(lg);
		lg.setVisible(true);
		btnLogOut.setVisible(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MUtama frame = new MUtama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
