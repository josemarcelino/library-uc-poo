import java.awt.EventQueue;

import javax.naming.ldap.Rdn;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


class WindowAdicionarItem {

	JFrame frame;
	private JTextField textFieldTitulo;
	private JTextField textFieldAnoPublicacao;
	private JTextField textFieldEditora;
	private JTextField textFieldCota;
	private JTextField textFieldISBN;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Biblioteca biblioteca;
	private Admin admin;

	/**
	 * Create the application.
	 */
	WindowAdicionarItem(Biblioteca biblio, Utilizador u) {
		biblioteca = biblio;
		admin = (Admin) u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(24, 26, 70, 15);
		frame.getContentPane().add(lblTipo);

		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(198, 293, 143, 25);
		frame.getContentPane().add(btnConcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(377, 293, 143, 25);
		frame.getContentPane().add(btnCancelar);
		
		final JRadioButton rdbtnDvd = new JRadioButton("DVD");
		buttonGroup.add(rdbtnDvd);
		rdbtnDvd.setBounds(176, 22, 54, 23);
		frame.getContentPane().add(rdbtnDvd);
		
		final JRadioButton rdbtnLivro = new JRadioButton("Livro");
		
		buttonGroup.add(rdbtnLivro);
		rdbtnLivro.setBounds(108, 22, 59, 23);
		frame.getContentPane().add(rdbtnLivro);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(24, 78, 70, 15);
		frame.getContentPane().add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(153, 76, 114, 19);
		frame.getContentPane().add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblAnoPublicacao = new JLabel("Ano Publicacao");
		lblAnoPublicacao.setBounds(24, 109, 117, 15);
		frame.getContentPane().add(lblAnoPublicacao);
		
		textFieldAnoPublicacao = new JTextField();
		textFieldAnoPublicacao.setBounds(153, 107, 114, 19);
		frame.getContentPane().add(textFieldAnoPublicacao);
		textFieldAnoPublicacao.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(318, 78, 70, 15);
		frame.getContentPane().add(lblEditora);
		
		textFieldEditora = new JTextField();
		textFieldEditora.setColumns(10);
		textFieldEditora.setBounds(406, 78, 114, 19);
		frame.getContentPane().add(textFieldEditora);
		
		textFieldCota = new JTextField();
		textFieldCota.setColumns(10);
		textFieldCota.setBounds(406, 109, 114, 19);
		frame.getContentPane().add(textFieldCota);
		
		final JLabel lblCota = new JLabel("Cota");
		lblCota.setBounds(318, 109, 70, 15);
		frame.getContentPane().add(lblCota);
		
		final JLabel lblAutores = new JLabel("Autores");
		lblAutores.setBounds(24, 141, 117, 15);
		frame.getContentPane().add(lblAutores);
		
		final JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(318, 141, 70, 15);
		frame.getContentPane().add(lblIsbn);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setColumns(10);
		textFieldISBN.setBounds(406, 141, 114, 19);
		frame.getContentPane().add(textFieldISBN);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 53, 502, 2);
		frame.getContentPane().add(separator);
		
		DefaultListModel listAux = new DefaultListModel(); 
		for(Autor a: biblioteca.getlistaAutores())
		{
			listAux.addElement(a.getNome());
		}
		final JList listAutores = new JList(listAux);
		listAutores.setBounds(155, 141, 112, 87);
		frame.getContentPane().add(listAutores);
		
		final JButton btnAdicionarAutor = new JButton("Adicionar Autor");
		btnAdicionarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowAdicionarAutor window = new WindowAdicionarAutor(biblioteca, admin);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAdicionarAutor.setBounds(24, 293, 143, 25);
		frame.getContentPane().add(btnAdicionarAutor);
		
		lblAutores.setVisible(false);
		lblCota.setVisible(false);
		lblIsbn.setVisible(false);
		listAutores.setVisible(false);
		textFieldCota.setVisible(false);
		textFieldISBN.setVisible(false);
		btnAdicionarAutor.setVisible(false);
		
		rdbtnLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnLivro.isSelected())
				{
					lblAutores.setVisible(true);
					lblCota.setVisible(true);
					lblIsbn.setVisible(true);
					listAutores.setVisible(true);
					textFieldCota.setVisible(true);
					textFieldISBN.setVisible(true);
					btnAdicionarAutor.setVisible(true);
				}
				else if(rdbtnDvd.isSelected())
				{
					lblAutores.setVisible(false);
					lblCota.setVisible(false);
					lblIsbn.setVisible(false);
					listAutores.setVisible(false);
					textFieldCota.setVisible(false);
					textFieldISBN.setVisible(false);
					btnAdicionarAutor.setVisible(false);
				}
			}
		});
		rdbtnDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnLivro.isSelected())
				{
					lblAutores.setVisible(true);
					lblCota.setVisible(true);
					lblIsbn.setVisible(true);
					listAutores.setVisible(true);
					textFieldCota.setVisible(true);
					textFieldISBN.setVisible(true);
					btnAdicionarAutor.setVisible(true);
				}
				else if(rdbtnDvd.isSelected())
				{
					lblAutores.setVisible(false);
					lblCota.setVisible(false);
					lblIsbn.setVisible(false);
					listAutores.setVisible(false);
					textFieldCota.setVisible(false);
					textFieldISBN.setVisible(false);
					btnAdicionarAutor.setVisible(false);
				}
			}
		});
		

		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldEditora.getText().equals("") || textFieldTitulo.getText().equals("") || textFieldAnoPublicacao.getText().equals("") || (rdbtnDvd.isSelected()==false && rdbtnLivro.isSelected()==false))
				{					
					try {
						WindowErro window = new WindowErro("Por favor preencha todos os campos");
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					if(rdbtnDvd.isSelected())
					{
						admin.adicionarItem(true, textFieldTitulo.getText(), Integer.parseInt(textFieldAnoPublicacao.getText()), textFieldEditora.getText(), null, null, null, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
						try {
							biblioteca.save(4);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
					else if(rdbtnLivro.isSelected())
					{
						if(textFieldCota.getText().equals("") || textFieldISBN.getText().equals("") || listAutores.getSelectedValues().equals(null))
						{					
							try {
								WindowErro window = new WindowErro("Por favor preencha todos os campos");
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else
						{
							ArrayList<Autor> autores = new ArrayList<Autor>();
							for(Object o: listAutores.getSelectedValues())
							{
								autores.add(admin.procurarAutor(o.toString(), biblioteca.getlistaAutores()));
							}
							
							admin.adicionarItem(false, textFieldTitulo.getText(), Integer.parseInt(textFieldAnoPublicacao.getText()), textFieldEditora.getText(), textFieldCota.getText(), textFieldISBN.getText(), autores, biblioteca.getlistaDvds(), biblioteca.getlistaLivros());
							try {
								biblioteca.save(3);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
					}
				}
			}
		});
	}
}
