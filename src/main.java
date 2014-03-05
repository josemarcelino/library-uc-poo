import java.awt.EventQueue;


/**
 * Class Main
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

public class main {

	/** Gera a classe biblioteca bem como o menu grafico de acesso login
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Biblioteca biblioteca = new Biblioteca();
		try {
			WindowLogin window = new WindowLogin(biblioteca);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
