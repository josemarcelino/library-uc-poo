
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


class WindowGeral {

	JFrame frame;
	private Utilizador user;
	private Biblioteca biblioteca;

	/**
	 * Create the application.
	 */
	WindowGeral(Biblioteca biblioteca, Utilizador user) {
		this.biblioteca = biblioteca;
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
				try {
					WindowLogin window = new WindowLogin(biblioteca);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(584, 12, 102, 25);
		frame.getContentPane().add(btnLogout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 26, 698, 369);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelLeitor = new JPanel();
		tabbedPane.addTab("Leitor", null, panelLeitor, null);
		panelLeitor.setLayout(null);
		
		JButton btnRequisitarItem = new JButton("Requisitar Item");
		btnRequisitarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowFazerRequisicao window = new WindowFazerRequisicao(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnRequisitarItem.setBounds(117, 108, 180, 93);
		panelLeitor.add(btnRequisitarItem);
		
		JButton btnEntregarItem = new JButton("Entregar Item");
		btnEntregarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowEntregarRequisicao window = new WindowEntregarRequisicao(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEntregarItem.setBounds(404, 108, 180, 93);
		panelLeitor.add(btnEntregarItem);
		
		JLabel lblNumeroDeRequisies = new JLabel("Numero de Requisições Disponiveis:");
		lblNumeroDeRequisies.setBounds(117, 25, 320, 15);
		panelLeitor.add(lblNumeroDeRequisies);
		
		JLabel lblNumReq = new JLabel();
		lblNumReq.setBounds(396, 25, 70, 15);
		panelLeitor.add(lblNumReq);
		if(user.getClass().equals(Admin.class))
		{
			lblNumReq.setText("ilimitado");
		}
		else if(user.getClass().equals(Leitor.class))
		{
			Leitor aux = (Leitor) user;
			int numReqs = aux.getMaxRequisicoes() - aux.getNumRequisicoes(); 
			lblNumReq.setText(Integer.toString(numReqs));
		}
		
		JPanel panelAdmin = new JPanel();
		tabbedPane.addTab("Admin", null, panelAdmin, null);
		panelAdmin.setLayout(null);
		
		JButton btnAdicionarUtilizador = new JButton("Adicionar Utilizador");
		btnAdicionarUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowAdicionarUser window = new WindowAdicionarUser(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAdicionarUtilizador.setBounds(12, 60, 190, 79);
		panelAdmin.add(btnAdicionarUtilizador);
		
		JButton btnRemoverUtilizador = new JButton("Remover Utilizador");
		btnRemoverUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				WindowRemoverUser window = new WindowRemoverUser(user, biblioteca);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnRemoverUtilizador.setBounds(258, 60, 190, 79);
		panelAdmin.add(btnRemoverUtilizador);
		
		JButton btnEstatsticas = new JButton("Estatísticas");
		btnEstatsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowEstatisticas window = new WindowEstatisticas(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEstatsticas.setBounds(12, 188, 190, 79);
		panelAdmin.add(btnEstatsticas);
		
		JButton btnEntregasAtrasadas = new JButton("Entregas Atrasadas");
		btnEntregasAtrasadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowEntregasAtrasadas window = new WindowEntregasAtrasadas(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEntregasAtrasadas.setBounds(258, 188, 190, 79);
		panelAdmin.add(btnEntregasAtrasadas);
		
		JButton btnGerirItems = new JButton("Gerir Items");
		btnGerirItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowGerirItems window = new WindowGerirItems(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnGerirItems.setBounds(499, 60, 182, 79);
		panelAdmin.add(btnGerirItems);
		
		JButton btnGerirAutores = new JButton("Gerir Autores");
		btnGerirAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowGerirAutores window = new WindowGerirAutores(biblioteca, user);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnGerirAutores.setBounds(499, 188, 182, 79);
		panelAdmin.add(btnGerirAutores);
		
		JLabel lblBemVindo = new JLabel("Bem vindo(a),");
		lblBemVindo.setBounds(193, 17, 102, 15);
		frame.getContentPane().add(lblBemVindo);
		
		JLabel lblUsername = new JLabel(user.username);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(304, 17, 102, 15);
		frame.getContentPane().add(lblUsername);
		if(user.getClass().equals(Admin.class) == false)
		{
			tabbedPane.removeTabAt(1);
		}
	}
}
