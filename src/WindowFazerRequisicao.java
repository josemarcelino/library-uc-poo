import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


 class WindowFazerRequisicao {

	 JFrame frame;
	 private JTextField txtAnoLivro;
	 private JTextField txtTituloLivro;
	 private JTextField txtEditoraLivro;
	 private JTextField txtIsbnLivro;
	 private JTextField txtCotaLivro;
	 private JTextField textFieldAnoDVD;
	 private JTextField textFieldTituloDVD;
	 private JTextField textFieldEditoraDVD;
	 private Utilizador user;
	 private Biblioteca biblioteca;
	 
	/**
	 * Create the application.
	 */
	WindowFazerRequisicao(Biblioteca b, Utilizador u) {
		user = u;
		biblioteca = b;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 448, 277);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelDVD = new JPanel();
		tabbedPane.addTab("DVD", null, panelDVD, null);
		panelDVD.setLayout(null);
		
		JLabel label = new JLabel("Lista de Itens");
		label.setBounds(24, 10, 97, 15);
		panelDVD.add(label);
		
		final JButton btnRequisitarDVD = new JButton("Requisitar");
		btnRequisitarDVD.setEnabled(false);
		btnRequisitarDVD.setBounds(117, 213, 107, 25);
		panelDVD.add(btnRequisitarDVD);
		
		JButton btnRetrocederDVD = new JButton("Retroceder");
		btnRetrocederDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRetrocederDVD.setBounds(242, 213, 113, 25);
		panelDVD.add(btnRetrocederDVD);
		
		JLabel label_1 = new JLabel("Ano");
		label_1.setBounds(242, 106, 27, 15);
		panelDVD.add(label_1);
		
		JLabel label_2 = new JLabel("Titulo");
		label_2.setBounds(242, 44, 40, 15);
		panelDVD.add(label_2);
		
		JLabel label_3 = new JLabel("Editora");
		label_3.setBounds(242, 77, 51, 15);
		panelDVD.add(label_3);
		
		textFieldAnoDVD = new JTextField();
		textFieldAnoDVD.setEditable(false);
		textFieldAnoDVD.setColumns(10);
		textFieldAnoDVD.setBounds(300, 104, 114, 19);
		panelDVD.add(textFieldAnoDVD);
		
		textFieldTituloDVD = new JTextField();
		textFieldTituloDVD.setEditable(false);
		textFieldTituloDVD.setColumns(10);
		textFieldTituloDVD.setBounds(300, 42, 114, 19);
		panelDVD.add(textFieldTituloDVD);
		
		textFieldEditoraDVD = new JTextField();
		textFieldEditoraDVD.setEditable(false);
		textFieldEditoraDVD.setColumns(10);
		textFieldEditoraDVD.setBounds(300, 75, 114, 19);
		panelDVD.add(textFieldEditoraDVD);
		
		DefaultListModel listDVDsDisp = new DefaultListModel(); 
		for(Item i : user.listarItemsDisponiveis(biblioteca.getlistaRequisicoes(), biblioteca.getlistaLivros(), biblioteca.getlistaDvds()))
		{
			if(i.getClass().equals(DVD.class))
				listDVDsDisp.addElement(i.getTitulo());
		}
		final JList listDVD = new JList(listDVDsDisp);
		listDVD.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				DVD d = (DVD) user.pesquisarItem(true, listDVD.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				textFieldAnoDVD.setText(Integer.toString(d.getAnoPublicacao()));
				textFieldEditoraDVD.setText(d.getEditora());
				textFieldTituloDVD.setText(d.getTitulo());
				btnRequisitarDVD.setEnabled(true);
			}
		});
		listDVD.setBounds(24, 41, 142, 143);
		panelDVD.add(listDVD);
		
		JPanel panelLivro = new JPanel();
		tabbedPane.addTab("Livro", null, panelLivro, null);
		panelLivro.setLayout(null);
		
		JLabel lblListaDeItens = new JLabel("Lista de Itens");
		lblListaDeItens.setBounds(24, 10, 97, 15);
		panelLivro.add(lblListaDeItens);
		
		final JButton btnRequisitarLivro = new JButton("Requisitar");
		
		btnRequisitarLivro.setEnabled(false);
		btnRequisitarLivro.setBounds(117, 213, 107, 25);
		panelLivro.add(btnRequisitarLivro);
		
		JButton btnRetrocederLivro = new JButton("Retroceder");
		btnRetrocederLivro.setBounds(242, 213, 113, 25);
		panelLivro.add(btnRetrocederLivro);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(242, 106, 27, 15);
		panelLivro.add(lblAno);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(242, 44, 40, 15);
		panelLivro.add(lblTitulo);
		
		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(242, 77, 51, 15);
		panelLivro.add(lblEditora);
		
		JLabel lblIsbn = new JLabel("Isbn");
		lblIsbn.setBounds(242, 166, 30, 15);
		panelLivro.add(lblIsbn);
		
		JLabel lblCota = new JLabel("Cota");
		lblCota.setBounds(242, 137, 33, 15);
		panelLivro.add(lblCota);
		
		txtAnoLivro = new JTextField();
		txtAnoLivro.setBounds(300, 104, 114, 19);
		panelLivro.add(txtAnoLivro);
		txtAnoLivro.setEditable(false);
		txtAnoLivro.setColumns(10);
		
		txtTituloLivro = new JTextField();
		txtTituloLivro.setBounds(300, 42, 114, 19);
		panelLivro.add(txtTituloLivro);
		txtTituloLivro.setEditable(false);
		txtTituloLivro.setColumns(10);
		
		txtEditoraLivro = new JTextField();
		txtEditoraLivro.setBounds(300, 75, 114, 19);
		panelLivro.add(txtEditoraLivro);
		txtEditoraLivro.setEditable(false);
		txtEditoraLivro.setColumns(10);
		
		txtIsbnLivro = new JTextField();
		txtIsbnLivro.setBounds(300, 164, 114, 19);
		panelLivro.add(txtIsbnLivro);
		txtIsbnLivro.setEditable(false);
		txtIsbnLivro.setColumns(10);
		
		txtCotaLivro = new JTextField();
		txtCotaLivro.setBounds(300, 135, 114, 19);
		panelLivro.add(txtCotaLivro);
		txtCotaLivro.setEditable(false);
		txtCotaLivro.setColumns(10);

		DefaultListModel listLivrosDisp = new DefaultListModel(); 
		for(Item i : user.listarItemsDisponiveis(biblioteca.getlistaRequisicoes(), biblioteca.getlistaLivros(), biblioteca.getlistaDvds()))
		{
			if(i.getClass().equals(Livro.class))
				listLivrosDisp.addElement(i.getTitulo());
		}
		final JList listLivro = new JList(listLivrosDisp);
		listLivro.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Livro l = (Livro) user.pesquisarItem(false, listLivro.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				txtAnoLivro.setText(Integer.toString(l.getAnoPublicacao()));
				txtEditoraLivro.setText(l.getEditora());
				txtTituloLivro.setText(l.getTitulo());
				txtCotaLivro.setText(l.getCota());
				txtIsbnLivro.setText(l.getISBN());
				btnRequisitarLivro.setEnabled(true);
			}
		});
		listLivro.setBounds(24, 41, 142, 143);
		panelLivro.add(listLivro);
		btnRetrocederLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		btnRequisitarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.requisitarItem(user.pesquisarItem(false, listLivro.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros()), biblioteca.getlistaRequisicoes());
				try {
					biblioteca.save(5);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					WindowSucesso window = new WindowSucesso();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});

		btnRequisitarDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.requisitarItem(user.pesquisarItem(true, listDVD.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros()), biblioteca.getlistaRequisicoes());
				try {
					biblioteca.save(5);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					WindowSucesso window = new WindowSucesso();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
