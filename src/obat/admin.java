package obat;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class admin extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	public admin() {
		
		setBounds(100, 100, 450, 300);
		this.setTitle("Admin");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Maitenance Product", null, panel, null);
		panel.setLayout(null);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 750, 500);
		panel.add(desktopPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Maitenance Supplier", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(10, 11, 750, 500);
		panel_1.add(desktopPane_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Maitenance Stock", null, panel_2, null);
		panel_2.setLayout(null);
		
		final JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBounds(10, 11, 750, 500);
		panel_2.add(desktopPane_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Maintenance User", null, panel_3, null);
		panel_3.setLayout(null);
		
		//Dimension sizePanel = panel_3.getBounds();
		final JDesktopPane desktopPane1 = new JDesktopPane();
		desktopPane1.setBounds(10, 11, 750,500);
		panel_3.add(desktopPane1);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Laporan", null, panel_4, null);
		panel_4.setLayout(null);
		
		final JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBounds(10, 11, 750,500);
		panel_4.add(desktopPane_3);
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User u = new User();
				Dimension size=desktopPane1.getSize();
				desktopPane1.add(u);
				u.setVisible(true);
				u.setSize(size);
				u.setLocation(0,0);
				
			}
		});
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				Produk p = new Produk();
				Dimension size2=desktopPane.getSize();
				desktopPane.add(p);
				p.setVisible(true);
				p.setSize(size2);
				p.setLocation(0,0);
			}
		});
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				supplier s = new supplier();
				Dimension size3=desktopPane_1.getSize();
				desktopPane_1.add(s);
				s.setVisible(true);
				s.setSize(size3);
				s.setLocation(0,0);
				
				Stok st = new Stok();
				Dimension size4=desktopPane_2.getSize();
				desktopPane_2.add(st);
				st.setVisible(true);
				st.setSize(size4);
				st.setLocation(0,0);
				
				laporan lp = new laporan();
				Dimension size5=desktopPane_3.getSize();
				desktopPane_3.add(lp);
				lp.setVisible(true);
				lp.setSize(size5);
				lp.setLocation(0,0);
				
			}
		});
	}
}
