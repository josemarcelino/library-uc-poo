import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


class WindowGerirItems {

	JFrame frame;
	private JTextField textFieldTituloLivro;
	private JTextField textFieldAnoPubLivro;
	private JTextField textFieldEditoraLivro;
	private JTextField textFieldISBNLivro;
	private JTextField textFieldCotaLivro;
	private boolean toogleEditarLivro=true, toogleSairLivro=true, toogleRemoverLivro=true;
	private boolean toogleEditarDVD=true, toogleSairDVD=true, toogleRemoverDVD=true;
	private JTextField textFieldTituloDVD;
	private JTextField textFieldAnoPubDVD;
	private JTextField textFieldEditoraDVD;;
	private Biblioteca biblioteca;
	private Admin admin;
	private JTextField textFieldAutoresLivro;

	/**
	 * Create the application.
	 */
	WindowGerirItems(Biblioteca b, Utilizador u) {
		biblioteca = b;
		admin = (Admin)u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowAdicionarItem window = new WindowAdicionarItem(biblioteca, admin);
					window.frame.setVisible(true);
					
					frame.setVisible(false);
					frame.dispose();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNovo.setBounds(419, 12, 117, 25);
		frame.getContentPane().add(btnNovo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 24, 548, 353);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelLivro = new JPanel();
		tabbedPane.addTab("Livro", null, panelLivro, null);
		panelLivro.setLayout(null);
		
		DefaultListModel listAuxLivro = new DefaultListModel(); 
		for(Livro l: biblioteca.getlistaLivros())
		{
			listAuxLivro.addElement(l.getTitulo());
		}
		final JButton btnRemoverLivro = new JButton("Remover");
		final JButton btnEditarLivro = new JButton("Editar");
		final JList listLivros = new JList(listAuxLivro);
		listLivros.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Livro l = (Livro) admin.pesquisarItem(false, listLivros.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				textFieldAnoPubLivro.setText(Integer.toString(l.getAnoPublicacao()));
				textFieldEditoraLivro.setText(l.getEditora());
				textFieldTituloLivro.setText(l.getTitulo());
				textFieldCotaLivro.setText(l.getCota());
				textFieldISBNLivro.setText(l.getISBN());
				btnRemoverLivro.setEnabled(true);
				btnEditarLivro.setEnabled(true);
				String autores = new String();
				for(Autor a : l.getAutor())
				{
					autores = autores.concat(a.getNome() + " ");
				}
				textFieldAutoresLivro.setText(autores);
			}
		});
		listLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listLivros.setBounds(12, 12, 172, 302);
		panelLivro.add(listLivros);
		
		textFieldTituloLivro = new JTextField();
		textFieldTituloLivro.setEditable(false);
		textFieldTituloLivro.setBounds(369, 24, 162, 19);
		panelLivro.add(textFieldTituloLivro);
		textFieldTituloLivro.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(202, 26, 70, 15);
		panelLivro.add(lblTitulo);
		
		btnEditarLivro.setEnabled(false);
		final JButton btnSairLivro = new JButton("Sair");		
		btnRemoverLivro.setEnabled(false);
		
		btnEditarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toogleEditarLivro)
				{
					btnEditarLivro.setText("Guardar");
					btnSairLivro.setText("Cancelar");
					btnRemoverLivro.setEnabled(false);
					textFieldAnoPubLivro.setEditable(true);
					textFieldCotaLivro.setEditable(true);
					textFieldISBNLivro.setEditable(true);
					textFieldTituloLivro.setEditable(true);
					textFieldEditoraLivro.setEditable(true);
					toogleEditarLivro=false;
					toogleSairLivro=false;		
					toogleRemoverLivro=false;
					
				}
				else
				{
					btnEditarLivro.setText("Editar");
					btnSairLivro.setText("Sair");
					btnRemoverLivro.setEnabled(true);
					textFieldAnoPubLivro.setEditable(false);
					textFieldCotaLivro.setEditable(false);
					textFieldISBNLivro.setEditable(false);
					textFieldTituloLivro.setEditable(false);
					textFieldEditoraLivro.setEditable(false);
					toogleEditarLivro=true;
					toogleSairLivro=true;
					toogleRemoverLivro=false;
					
					String titulo_anterior = listLivros.getSelectedValue().toString();
					try {
					admin.removerItem(false, titulo_anterior, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
					admin.adicionarItem(false, textFieldTituloLivro.getText(),Integer.parseInt(textFieldAnoPubLivro.getText()) , textFieldEditoraLivro.getText(), textFieldCotaLivro.getText(), textFieldISBNLivro.getText(), null, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());		
					biblioteca.save(4);
					frame.setVisible(false);
					frame.dispose();
					
					WindowSucesso window = new WindowSucesso();
					window.frame.setVisible(true);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (Exception e1) {
				e1.printStackTrace();
			}
					
				}
			}
		});
		btnEditarLivro.setBounds(202, 289, 101, 25);
		panelLivro.add(btnEditarLivro);
		
		btnRemoverLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.removerItem(false, listLivros.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				try {
					biblioteca.save(3);
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
		btnRemoverLivro.setBounds(317, 289, 101, 25);
		panelLivro.add(btnRemoverLivro);
		
		btnSairLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toogleSairLivro)
				{
					frame.setVisible(false);
					frame.dispose();
				}
				else
				{
					btnEditarLivro.setText("Editar");
					btnSairLivro.setText("Sair");
					btnRemoverLivro.setEnabled(true);
					textFieldAnoPubLivro.setEditable(false);
					textFieldCotaLivro.setEditable(false);
					textFieldISBNLivro.setEditable(false);
					textFieldTituloLivro.setEditable(false);
					textFieldEditoraLivro.setEditable(false);
					toogleEditarLivro=true;
					toogleSairLivro=true;
					toogleRemoverLivro=false;
				}
			}
		});
		btnSairLivro.setBounds(430, 289, 101, 25);
		panelLivro.add(btnSairLivro);
		
		JLabel lblAnoPublicao = new JLabel("Ano Publicação");
		lblAnoPublicao.setBounds(202, 57, 114, 15);
		panelLivro.add(lblAnoPublicao);
		
		textFieldAnoPubLivro = new JTextField();
		textFieldAnoPubLivro.setEditable(false);
		textFieldAnoPubLivro.setBounds(369, 55, 162, 19);
		panelLivro.add(textFieldAnoPubLivro);
		textFieldAnoPubLivro.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(202, 88, 70, 15);
		panelLivro.add(lblEditora);
		
		textFieldEditoraLivro = new JTextField();
		textFieldEditoraLivro.setEditable(false);
		textFieldEditoraLivro.setBounds(369, 86, 162, 19);
		panelLivro.add(textFieldEditoraLivro);
		textFieldEditoraLivro.setColumns(10);
		
		textFieldISBNLivro = new JTextField();
		textFieldISBNLivro.setEditable(false);
		textFieldISBNLivro.setBounds(369, 115, 162, 19);
		panelLivro.add(textFieldISBNLivro);
		textFieldISBNLivro.setColumns(10);
		
		textFieldCotaLivro = new JTextField();
		textFieldCotaLivro.setEditable(false);
		textFieldCotaLivro.setBounds(369, 146, 162, 19);
		panelLivro.add(textFieldCotaLivro);
		textFieldCotaLivro.setColumns(10);
		
		JLabel lblAutores = new JLabel("Autores");
		lblAutores.setBounds(202, 179, 70, 15);
		panelLivro.add(lblAutores);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(202, 117, 70, 15);
		panelLivro.add(lblIsbn);
		
		JLabel lblCota = new JLabel("Cota");
		lblCota.setBounds(202, 148, 70, 15);
		panelLivro.add(lblCota);
		
		textFieldAutoresLivro = new JTextField();
		textFieldAutoresLivro.setEditable(false);
		textFieldAutoresLivro.setColumns(10);
		textFieldAutoresLivro.setBounds(369, 176, 162, 19);
		panelLivro.add(textFieldAutoresLivro);
		
		JPanel panelDVD = new JPanel();
		tabbedPane.addTab("DVD", null, panelDVD, null);
		panelDVD.setLayout(null);
		
		DefaultListModel listAuxDVD = new DefaultListModel(); 
		for(DVD d: biblioteca.getlistaDvds())
		{
			listAuxDVD.addElement(d.getTitulo());
		}
		final JList listDVD = new JList(listAuxDVD);
		final JButton btnRemoverDVD = new JButton("Remover");
		final JButton btnEditarDVD = new JButton("Editar");
		listDVD.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DVD d = (DVD) admin.pesquisarItem(true, listDVD.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				textFieldAnoPubDVD.setText(Integer.toString(d.getAnoPublicacao()));
				textFieldEditoraDVD.setText(d.getEditora());
				textFieldTituloDVD.setText(d.getTitulo());
				btnRemoverDVD.setEnabled(true);
				btnEditarDVD.setEnabled(true);
			}
		});
		listDVD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDVD.setBounds(12, 12, 172, 302);
		panelDVD.add(listDVD);
		
		textFieldTituloDVD = new JTextField();
		textFieldTituloDVD.setEditable(false);
		textFieldTituloDVD.setColumns(10);
		textFieldTituloDVD.setBounds(369, 24, 162, 19);
		panelDVD.add(textFieldTituloDVD);
		
		JLabel lblTituloDVD = new JLabel("Titulo");
		lblTituloDVD.setBounds(202, 26, 70, 15);
		panelDVD.add(lblTituloDVD);
		
		btnEditarDVD.setEnabled(false);
		final JButton btnSairDVD = new JButton("Sair");
		btnRemoverDVD.setEnabled(false);
		btnEditarDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toogleEditarDVD)
				{
					btnEditarDVD.setText("Guardar");
					btnSairDVD.setText("Cancelar");
					btnRemoverDVD.setEnabled(false);
					textFieldAnoPubDVD.setEditable(true);
					textFieldTituloDVD.setEditable(true);
					textFieldEditoraDVD.setEditable(true);
					toogleEditarDVD=false;
					toogleSairDVD=false;		
					toogleRemoverDVD=false;
				}
				else
				{
					btnEditarDVD.setText("Editar");
					btnSairDVD.setText("Sair");
					btnRemoverDVD.setEnabled(true);
					textFieldAnoPubDVD.setEditable(false);
					textFieldTituloDVD.setEditable(false);
					textFieldEditoraDVD.setEditable(false);
					toogleEditarDVD=true;
					toogleSairDVD=true;		
					toogleRemoverDVD=true;
					
					
					String titulo_anterior = listDVD.getSelectedValue().toString();
					try {
					admin.removerItem(true, titulo_anterior, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
					admin.adicionarItem(true, textFieldTituloDVD.getText(),Integer.parseInt(textFieldAnoPubDVD.getText()) , textFieldEditoraDVD.getText(), null, null, null, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());		
					biblioteca.save(3);
					
					frame.setVisible(false);
					frame.dispose();
					
	
						WindowSucesso window = new WindowSucesso();
						window.frame.setVisible(true);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (Exception e1) {
				e1.printStackTrace();
			}

				}	
			}
		});
		btnEditarDVD.setBounds(202, 289, 101, 25);
		panelDVD.add(btnEditarDVD);
		
		btnRemoverDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.removerItem(true, listDVD.getSelectedValue().toString(), biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
				try {
					biblioteca.save(4);
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
		btnRemoverDVD.setBounds(317, 289, 101, 25);
		panelDVD.add(btnRemoverDVD);
		
		btnSairDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toogleSairDVD)
				{
					frame.setVisible(false);
					frame.dispose();
				}
				else
				{
					btnEditarDVD.setText("Editar");
					btnSairDVD.setText("Sair");
					btnRemoverDVD.setEnabled(true);
					textFieldAnoPubDVD.setEditable(false);
					textFieldTituloDVD.setEditable(false);
					textFieldEditoraDVD.setEditable(false);
					toogleEditarDVD=true;
					toogleSairDVD=true;		
					toogleRemoverDVD=true;
				}
			}
		});
		btnSairDVD.setBounds(430, 289, 101, 25);
		panelDVD.add(btnSairDVD);
		
		JLabel lblAnoPubDVD = new JLabel("Ano Publicação");
		lblAnoPubDVD.setBounds(202, 57, 114, 15);
		panelDVD.add(lblAnoPubDVD);
		
		textFieldAnoPubDVD = new JTextField();
		textFieldAnoPubDVD.setEditable(false);
		textFieldAnoPubDVD.setColumns(10);
		textFieldAnoPubDVD.setBounds(369, 55, 162, 19);
		panelDVD.add(textFieldAnoPubDVD);
		
		JLabel lblEditoraDVD = new JLabel("Editora");
		lblEditoraDVD.setBounds(202, 88, 70, 15);
		panelDVD.add(lblEditoraDVD);
		
		textFieldEditoraDVD = new JTextField();
		textFieldEditoraDVD.setEditable(false);
		textFieldEditoraDVD.setColumns(10);
		textFieldEditoraDVD.setBounds(369, 86, 162, 19);
		panelDVD.add(textFieldEditoraDVD);
		
	}
}
