import java.io.Serializable;

/**
 * Class DVD
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class DVD extends Item {
	
	DVD(String titulo, int anoPub, String editora)
	{	
		this.titulo = titulo;
		this.anoPublicacao = anoPub;
		this.editora = editora;
	}
	
}
