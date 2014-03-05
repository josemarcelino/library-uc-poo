import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


class WindowEstatisticas {

	JFrame frame;
	private JTextField txtTitulo;
	private JTextField txtAno;
	private JTextField txtEditora;
	private JTextField txtCotaTop;
	private JTextField txtIsbnTop;
	private JTextField textFieldAnoActualReq;
	private JTextField textFieldEditoraActualReq;
	private JTextField textFieldISBNActuaReq;
	private JTextField textFieldCotaActualReq;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldTipoActualReq;
	private JTextField textFieldTipoNuncaReq;
	private JTextField textFieldTituloNuncaReq;
	private JTextField textFieldAnoNuncaReq;
	private JTextField textFieldEditoraNuncaReq;
	private JTextField textFieldISBNNuncaReq;
	private JTextField textFieldCotaNuncaReq;
	private JTextField textFieldTituloActualReq;
	private Biblioteca biblioteca;
	private Admin admin;
	private JTextField textFieldAutoresTop;
	private JTextField textFieldAutoresActualReq;
	private JTextField textFieldAutoresNuncaReq;

	/**
	 * Create the application.
	 */
	WindowEstatisticas(Biblioteca b, Utilizador u) {
		this.biblioteca = b;
		this.admin = (Admin) u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 699, 277);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelTop = new JPanel();
		tabbedPane.addTab("Top Item", null, panelTop, null);
		panelTop.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(12, 75, 70, 15);
		panelTop.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setText("Titulo");
		txtTitulo.setBounds(71, 73, 114, 19);
		panelTop.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(12, 102, 70, 15);
		panelTop.add(lblAno);
		
		txtAno = new JTextField();
		txtAno.setEditable(false);
		txtAno.setText("Ano");
		txtAno.setBounds(71, 100, 114, 19);
		panelTop.add(txtAno);
		txtAno.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(12, 129, 70, 15);
		panelTop.add(lblEditora);
		
		txtEditora = new JTextField();
		txtEditora.setEditable(false);
		txtEditora.setText("Editora");
		txtEditora.setBounds(71, 127, 114, 19);
		panelTop.add(txtEditora);
		txtEditora.setColumns(10);
		
		
		final JLabel lblIsbnTop = new JLabel("Isbn");
		lblIsbnTop.setBounds(246, 75, 70, 15);
		panelTop.add(lblIsbnTop);
		
		final JLabel lblCotaTop = new JLabel("Cota");
		lblCotaTop.setBounds(246, 102, 70, 15);
		panelTop.add(lblCotaTop);
		
		final JLabel lblAutoresTop = new JLabel("Autores");
		lblAutoresTop.setBounds(246, 129, 70, 15);
		panelTop.add(lblAutoresTop);
		
		txtCotaTop = new JTextField();
		txtCotaTop.setEditable(false);
		txtCotaTop.setText("Cota");
		txtCotaTop.setBounds(322, 100, 114, 19);
		panelTop.add(txtCotaTop);
		txtCotaTop.setColumns(10);
		
		txtIsbnTop = new JTextField();
		txtIsbnTop.setEditable(false);
		txtIsbnTop.setText("Isbn");
		txtIsbnTop.setBounds(322, 73, 114, 19);
		panelTop.add(txtIsbnTop);
		txtIsbnTop.setColumns(10);
		
		textFieldAutoresTop = new JTextField();
		textFieldAutoresTop.setText("Autores");
		textFieldAutoresTop.setEditable(false);
		textFieldAutoresTop.setColumns(10);
		textFieldAutoresTop.setBounds(322, 127, 114, 19);
		panelTop.add(textFieldAutoresTop);
		JButton Retroceder = new JButton("Retroceder");
		Retroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		Retroceder.setBounds(565, 213, 117, 25);
		panelTop.add(Retroceder);

		txtCotaTop.setVisible(false);
		txtIsbnTop.setVisible(false);
		textFieldAutoresTop.setVisible(false);
		lblCotaTop.setVisible(false);
		lblAutoresTop.setVisible(false);
		lblIsbnTop.setVisible(false);
		final JRadioButton rdbtnDvd = new JRadioButton("DVD");
		rdbtnDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnDvd.isSelected())
				{					
					txtCotaTop.setVisible(false);
					txtIsbnTop.setVisible(false);
					lblCotaTop.setVisible(false);
					lblAutoresTop.setVisible(false);
					lblIsbnTop.setVisible(false);
					textFieldAutoresTop.setVisible(false);
					
					DVD d = (DVD) admin.topItem(true, biblioteca.getlistaDvds(), null, biblioteca.getlistaRequisicoes());
					if(d!=null)
					{
						txtAno.setText(Integer.toString(d.getAnoPublicacao()));
						txtTitulo.setText(d.getTitulo());
						txtEditora.setText(d.getEditora());
					}
					else
					{
						try {
							WindowErro window = new WindowErro("Erro não existe top dvd");
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		buttonGroup.add(rdbtnDvd);
		rdbtnDvd.setBounds(12, 24, 54, 23);
		panelTop.add(rdbtnDvd);
		
		final JRadioButton rdbtnLivro = new JRadioButton("Livro");
		rdbtnLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnLivro.isSelected())
				{
					txtCotaTop.setVisible(true);
					txtIsbnTop.setVisible(true);
					lblCotaTop.setVisible(true);
					lblAutoresTop.setVisible(true);
					lblIsbnTop.setVisible(true);
					textFieldAutoresTop.setVisible(true);
					
					Livro l = (Livro) admin.topItem(false, null , biblioteca.getlistaLivros(), biblioteca.getlistaRequisicoes());
					if(l!=null)
					{
						txtAno.setText(Integer.toString(l.getAnoPublicacao()));
						txtTitulo.setText(l.getTitulo());
						txtEditora.setText(l.getEditora());
						txtCotaTop.setText(l.getCota());
						txtIsbnTop.setText(l.getISBN());
						String autores = new String();
						for(Autor a : l.getAutor())
						{
							autores = autores.concat(a.getNome() + " ");
						}
						textFieldAutoresTop.setText(autores);
					}
					else
					{
						try {
							WindowErro window = new WindowErro("Erro não existe top livro");
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		buttonGroup.add(rdbtnLivro);
		rdbtnLivro.setBounds(71, 24, 70, 23);
		panelTop.add(rdbtnLivro);

		
		JPanel panelRequisitados = new JPanel();
		tabbedPane.addTab("Actualmente Requisitados", null, panelRequisitados, null);
		panelRequisitados.setLayout(null);
		
		
		DefaultListModel listRequisitados = new DefaultListModel();
		for(Item i: admin.listarItemsRequisitados(biblioteca.getlistaRequisicoes()))
		{
			listRequisitados.addElement(i.getTitulo());
		}
		final JList listActualReq = new JList(listRequisitados);
		
		listActualReq.setBounds(12, 59, 226, 141);
		panelRequisitados.add(listActualReq);
		
		JLabel lblListaDeItens = new JLabel("Lista de itens requisitados");
		lblListaDeItens.setBounds(12, 33, 226, 15);
		panelRequisitados.add(lblListaDeItens);
		
		JButton btnRetroceder = new JButton("Retroceder");
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRetroceder.setBounds(565, 213, 117, 25);
		panelRequisitados.add(btnRetroceder);
		
		JLabel label = new JLabel("Titulo");
		label.setBounds(296, 60, 70, 15);
		panelRequisitados.add(label);
		
		textFieldTituloActualReq = new JTextField();
		textFieldTituloActualReq.setEditable(false);
		textFieldTituloActualReq.setText("Titulo");
		textFieldTituloActualReq.setColumns(10);
		textFieldTituloActualReq.setBounds(368, 58, 114, 19);
		panelRequisitados.add(textFieldTituloActualReq);
		
		JLabel label_1 = new JLabel("Ano");
		label_1.setBounds(296, 87, 70, 15);
		panelRequisitados.add(label_1);
		
		textFieldAnoActualReq = new JTextField();
		textFieldAnoActualReq.setEditable(false);
		textFieldAnoActualReq.setText("Ano");
		textFieldAnoActualReq.setColumns(10);
		textFieldAnoActualReq.setBounds(368, 85, 114, 19);
		panelRequisitados.add(textFieldAnoActualReq);
		
		JLabel label_2 = new JLabel("Editora");
		label_2.setBounds(296, 116, 70, 15);
		panelRequisitados.add(label_2);
		
		textFieldEditoraActualReq = new JTextField();
		textFieldEditoraActualReq.setEditable(false);
		textFieldEditoraActualReq.setText("Editora");
		textFieldEditoraActualReq.setColumns(10);
		textFieldEditoraActualReq.setBounds(368, 114, 114, 19);
		panelRequisitados.add(textFieldEditoraActualReq);
		
		final JLabel lblISBNActualReq = new JLabel("Isbn");
		lblISBNActualReq.setBounds(296, 144, 70, 15);
		panelRequisitados.add(lblISBNActualReq);
		
		textFieldISBNActuaReq = new JTextField();
		textFieldISBNActuaReq.setEditable(false);
		textFieldISBNActuaReq.setText("Isbn");
		textFieldISBNActuaReq.setColumns(10);
		textFieldISBNActuaReq.setBounds(368, 142, 114, 19);
		panelRequisitados.add(textFieldISBNActuaReq);
		
		final JLabel lblCotaActualReq = new JLabel("Cota");
		lblCotaActualReq.setBounds(296, 171, 70, 15);
		panelRequisitados.add(lblCotaActualReq);
		
		textFieldCotaActualReq = new JTextField();
		textFieldCotaActualReq.setEditable(false);
		textFieldCotaActualReq.setText("Cota");
		textFieldCotaActualReq.setColumns(10);
		textFieldCotaActualReq.setBounds(368, 169, 114, 19);
		panelRequisitados.add(textFieldCotaActualReq);
		
		final JLabel lblAutoresActualReq = new JLabel("Autores");
		lblAutoresActualReq.setBounds(296, 196, 70, 15);
		panelRequisitados.add(lblAutoresActualReq);
		
		JLabel lblTipo = new JLabel("Tipo ");
		lblTipo.setBounds(296, 33, 70, 15);
		panelRequisitados.add(lblTipo);
		
		textFieldTipoActualReq = new JTextField();
		textFieldTipoActualReq.setEditable(false);
		textFieldTipoActualReq.setText("Tipo");
		textFieldTipoActualReq.setBounds(368, 31, 114, 19);
		panelRequisitados.add(textFieldTipoActualReq);
		textFieldTipoActualReq.setColumns(10);
		
		textFieldAutoresActualReq = new JTextField();
		textFieldAutoresActualReq.setText("Autores");
		textFieldAutoresActualReq.setEditable(false);
		textFieldAutoresActualReq.setColumns(10);
		textFieldAutoresActualReq.setBounds(368, 194, 114, 19);
		panelRequisitados.add(textFieldAutoresActualReq);
		
		JPanel panelNuncaRequisitados = new JPanel();
		tabbedPane.addTab("Nunca Requisitados", null, panelNuncaRequisitados, null);
		panelNuncaRequisitados.setLayout(null);
		
		JButton btnRetroceder_1 = new JButton("Retroceder");
		btnRetroceder_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRetroceder_1.setBounds(565, 213, 117, 25);
		panelNuncaRequisitados.add(btnRetroceder_1);
		
		JLabel lblListaDeItens_1 = new JLabel("Lista de itens nunca requisitados");
		lblListaDeItens_1.setBounds(12, 12, 257, 15);
		panelNuncaRequisitados.add(lblListaDeItens_1);
		
		DefaultListModel listNuncaRequisitados = new DefaultListModel();
		for(Item i: admin.listarItemsNuncaRequisitados(biblioteca.getlistaRequisicoes(), biblioteca.getlistaLivros(), biblioteca.getlistaDvds()))
		{
			listNuncaRequisitados.addElement(i.getTitulo());
		}
		final JList listNuncaReq = new JList(listNuncaRequisitados);
		
		listNuncaReq.setBounds(12, 59, 226, 141);
		panelNuncaRequisitados.add(listNuncaReq);
		
		JLabel label_7 = new JLabel("Tipo ");
		label_7.setBounds(296, 33, 70, 15);
		panelNuncaRequisitados.add(label_7);
		
		JLabel label_8 = new JLabel("Titulo");
		label_8.setBounds(296, 60, 70, 15);
		panelNuncaRequisitados.add(label_8);
		
		textFieldTipoNuncaReq = new JTextField();
		textFieldTipoNuncaReq.setEditable(false);
		textFieldTipoNuncaReq.setText("Tipo");
		textFieldTipoNuncaReq.setColumns(10);
		textFieldTipoNuncaReq.setBounds(368, 31, 114, 19);
		panelNuncaRequisitados.add(textFieldTipoNuncaReq);
		
		textFieldTituloNuncaReq = new JTextField();
		textFieldTituloNuncaReq.setEditable(false);
		textFieldTituloNuncaReq.setText("Titulo");
		textFieldTituloNuncaReq.setColumns(10);
		textFieldTituloNuncaReq.setBounds(368, 58, 114, 19);
		panelNuncaRequisitados.add(textFieldTituloNuncaReq);
		
		JLabel label_9 = new JLabel("Ano");
		label_9.setBounds(296, 87, 70, 15);
		panelNuncaRequisitados.add(label_9);
		
		textFieldAnoNuncaReq = new JTextField();
		textFieldAnoNuncaReq.setEditable(false);
		textFieldAnoNuncaReq.setText("Ano");
		textFieldAnoNuncaReq.setColumns(10);
		textFieldAnoNuncaReq.setBounds(368, 85, 114, 19);
		panelNuncaRequisitados.add(textFieldAnoNuncaReq);
		
		textFieldEditoraNuncaReq = new JTextField();
		textFieldEditoraNuncaReq.setEditable(false);
		textFieldEditoraNuncaReq.setText("Editora");
		textFieldEditoraNuncaReq.setColumns(10);
		textFieldEditoraNuncaReq.setBounds(368, 114, 114, 19);
		panelNuncaRequisitados.add(textFieldEditoraNuncaReq);
		
		JLabel label_10 = new JLabel("Editora");
		label_10.setBounds(296, 116, 70, 15);
		panelNuncaRequisitados.add(label_10);
		
		final JLabel lblISBNNuncaReq = new JLabel("Isbn");
		lblISBNNuncaReq.setBounds(296, 144, 70, 15);
		panelNuncaRequisitados.add(lblISBNNuncaReq);
		
		textFieldISBNNuncaReq = new JTextField();
		textFieldISBNNuncaReq.setEditable(false);
		textFieldISBNNuncaReq.setText("Isbn");
		textFieldISBNNuncaReq.setColumns(10);
		textFieldISBNNuncaReq.setBounds(368, 142, 114, 19);
		panelNuncaRequisitados.add(textFieldISBNNuncaReq);
		
		final JLabel lblCotaNuncaReq = new JLabel("Cota");
		lblCotaNuncaReq.setBounds(296, 171, 70, 15);
		panelNuncaRequisitados.add(lblCotaNuncaReq);
		
		textFieldCotaNuncaReq = new JTextField();
		textFieldCotaNuncaReq.setEditable(false);
		textFieldCotaNuncaReq.setText("Cota");
		textFieldCotaNuncaReq.setColumns(10);
		textFieldCotaNuncaReq.setBounds(368, 169, 114, 19);
		panelNuncaRequisitados.add(textFieldCotaNuncaReq);
		
		final JLabel lblAutoresNuncaReq = new JLabel("Autores");
		lblAutoresNuncaReq.setBounds(296, 196, 70, 15);
		panelNuncaRequisitados.add(lblAutoresNuncaReq);
		
		JLabel lblNesteMs = new JLabel("neste mês");
		lblNesteMs.setBounds(84, 33, 90, 15);
		panelNuncaRequisitados.add(lblNesteMs);
		
		textFieldAutoresNuncaReq = new JTextField();
		textFieldAutoresNuncaReq.setText("Autores");
		textFieldAutoresNuncaReq.setEditable(false);
		textFieldAutoresNuncaReq.setColumns(10);
		textFieldAutoresNuncaReq.setBounds(368, 194, 114, 19);
		panelNuncaRequisitados.add(textFieldAutoresNuncaReq);
		
		textFieldISBNNuncaReq.setVisible(false);
		textFieldCotaNuncaReq.setVisible(false);
		textFieldAutoresNuncaReq.setVisible(false);
		lblCotaNuncaReq.setVisible(false);
		lblISBNNuncaReq.setVisible(false);
		lblAutoresNuncaReq.setVisible(false);
		
		textFieldCotaActualReq.setVisible(false);
		textFieldISBNActuaReq.setVisible(false);
		textFieldAutoresActualReq.setVisible(false);
		lblAutoresActualReq.setVisible(false);
		lblCotaActualReq.setVisible(false);
		lblISBNActualReq.setVisible(false);
		
		listActualReq.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {	
				Item i;
				if((i = admin.pesquisarItem(true, listActualReq.getSelectedValue().toString(), biblioteca.getlistaDvds(), null))!=null)
				{
					DVD d = (DVD) i;
					textFieldEditoraActualReq.setText(d.getEditora());
					textFieldAnoActualReq.setText(Integer.toString(d.getAnoPublicacao()));
					textFieldTipoActualReq.setText("DVD");
					textFieldTituloActualReq.setText(d.getTitulo());
					

					textFieldCotaActualReq.setVisible(false);
					textFieldISBNActuaReq.setVisible(false);
					textFieldAutoresActualReq.setVisible(false);
					lblAutoresActualReq.setVisible(false);
					lblCotaActualReq.setVisible(false);
					lblISBNActualReq.setVisible(false);
				}
				else if((i = admin.pesquisarItem(false, listActualReq.getSelectedValue().toString(), null, biblioteca.getlistaLivros()))!=null)
				{
					Livro l = (Livro) i;
					textFieldEditoraActualReq.setText(l.getEditora());
					textFieldAnoActualReq.setText(Integer.toString(l.getAnoPublicacao()));
					textFieldTipoActualReq.setText("Livro");
					textFieldTituloActualReq.setText(l.getTitulo());
					textFieldISBNActuaReq.setText(l.getISBN());
					textFieldCotaActualReq.setText(l.getCota());
					String autores = new String();
					for(Autor a : l.getAutor())
					{
						autores = autores.concat(a.getNome() + " ");
					}
					textFieldAutoresActualReq.setText(autores);

					textFieldCotaActualReq.setVisible(true);
					textFieldISBNActuaReq.setVisible(true);
					textFieldAutoresActualReq.setVisible(true);
					lblAutoresActualReq.setVisible(true);
					lblCotaActualReq.setVisible(true);
					lblISBNActualReq.setVisible(true);
				}
			}
		});
		
		listNuncaReq.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Item i;
				if((i = admin.pesquisarItem(true, listNuncaReq.getSelectedValue().toString(), biblioteca.getlistaDvds(), null))!=null)
				{
					DVD d = (DVD) i;
					textFieldEditoraNuncaReq.setText(d.getEditora());
					textFieldAnoNuncaReq.setText(Integer.toString(d.getAnoPublicacao()));
					textFieldTipoNuncaReq.setText("DVD");
					textFieldTituloNuncaReq.setText(d.getTitulo());
					
					textFieldISBNNuncaReq.setVisible(false);
					textFieldCotaNuncaReq.setVisible(false);
					textFieldAutoresNuncaReq.setVisible(false);
					lblCotaNuncaReq.setVisible(false);
					lblISBNNuncaReq.setVisible(false);
					lblAutoresNuncaReq.setVisible(false);
				}
				else if((i = admin.pesquisarItem(false, listNuncaReq.getSelectedValue().toString(), null, biblioteca.getlistaLivros()))!=null)
				{
					Livro l = (Livro) i;
					textFieldEditoraNuncaReq.setText(l.getEditora());
					textFieldAnoNuncaReq.setText(Integer.toString(l.getAnoPublicacao()));
					textFieldTipoNuncaReq.setText("Livro");
					textFieldTituloNuncaReq.setText(l.getTitulo());
					textFieldISBNNuncaReq.setText(l.getISBN());
					textFieldCotaNuncaReq.setText(l.getCota());
					String autores = new String();
					for(Autor a : l.getAutor())
					{
						autores = autores.concat(a.getNome() + " ");
					}
					textFieldAutoresNuncaReq.setText(autores);
					
					textFieldISBNNuncaReq.setVisible(true);
					textFieldCotaNuncaReq.setVisible(true);
					textFieldAutoresNuncaReq.setVisible(true);
					lblCotaNuncaReq.setVisible(true);
					lblISBNNuncaReq.setVisible(true);
					lblAutoresNuncaReq.setVisible(true);
				}
			}
		});
	}
}
