import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


 class WindowEntregasAtrasadas {

	 JFrame frame;
	 private JTextField textFieldDataLimite;
	 private Admin admin;
	 private Biblioteca biblioteca;
	 private JTextField textFieldItem;
	 private JTextField textFieldUtilizador;
	 private JTextField textFieldTitulo;

	/**
	 * Create the application.
	 */
	 WindowEntregasAtrasadas(Biblioteca b, Utilizador u) {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DefaultListModel listAux = new DefaultListModel(); 
		for(Requisicao r: admin.listarItemsAtrasados(biblioteca.getlistaRequisicoes()))
		{
			listAux.addElement(r.getItemRequisitado().titulo);
		}
		final JList list = new JList(listAux);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Requisicao aux = admin.pesquisarRequisicao(list.getSelectedValue().toString(), biblioteca.getlistaRequisicoes());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				textFieldDataLimite.setText(sdf.format(aux.getDataLimite()).toString());
				textFieldTitulo.setText(aux.getItemRequisitado().getTitulo());
				textFieldUtilizador.setText(aux.getUser().getUsername());
				if(aux.getItemRequisitado().getClass().equals(DVD.class))
				{
					textFieldItem.setText("DVD");
				}
				else if(aux.getItemRequisitado().getClass().equals(Livro.class))
				{
					textFieldItem.setText("Livro");
				}					
			}
		});
		list.setBounds(27, 39, 199, 170);
		frame.getContentPane().add(list);
		
		JLabel lblListaDeItens = new JLabel("Lista de itens em atraso");
		lblListaDeItens.setBounds(27, 12, 199, 15);
		frame.getContentPane().add(lblListaDeItens);
		
		JButton btnRetroceder = new JButton("Retroceder");
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRetroceder.setBounds(275, 221, 117, 25);
		frame.getContentPane().add(btnRetroceder);
		
		textFieldDataLimite = new JTextField();
		textFieldDataLimite.setEditable(false);
		textFieldDataLimite.setBounds(275, 190, 114, 19);
		frame.getContentPane().add(textFieldDataLimite);
		textFieldDataLimite.setColumns(10);
		
		JLabel lblDataLimite = new JLabel("Data Limite");
		lblDataLimite.setBounds(275, 174, 117, 15);
		frame.getContentPane().add(lblDataLimite);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(275, 28, 70, 15);
		frame.getContentPane().add(lblItem);
		
		JLabel lblUtilizador = new JLabel("Utilizador");
		lblUtilizador.setBounds(275, 121, 70, 15);
		frame.getContentPane().add(lblUtilizador);
		
		textFieldItem = new JTextField();
		textFieldItem.setEditable(false);
		textFieldItem.setColumns(10);
		textFieldItem.setBounds(275, 43, 114, 19);
		frame.getContentPane().add(textFieldItem);
		
		textFieldUtilizador = new JTextField();
		textFieldUtilizador.setEditable(false);
		textFieldUtilizador.setColumns(10);
		textFieldUtilizador.setBounds(275, 139, 114, 19);
		frame.getContentPane().add(textFieldUtilizador);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(275, 75, 70, 15);
		frame.getContentPane().add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(275, 90, 114, 19);
		frame.getContentPane().add(textFieldTitulo);
	}
}
