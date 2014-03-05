import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


class WindowSucesso {

	JFrame frame;

	
	/**
	 * Create the application.
	 */
	WindowSucesso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 383, 131);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblErro = new JLabel("Operação realizada com sucesso!");
		lblErro.setBounds(12, 12, 357, 61);
		lblErro.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblErro);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(151, 65, 79, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnNewButton);
	}

}
