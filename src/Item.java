import java.io.Serializable;

/**
 * Class Item
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

abstract class Item implements Serializable{
	protected String titulo;
	protected int anoPublicacao;
	protected String editora;	
	
	void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	String getTitulo()
	{
		return this.titulo;
	}
	//////////////////////////////////////////////
	void setEditora(String editora)
	{
		this.editora = editora;
	}
	String getEditora()
	{
		return this.editora;
	}
	//////////////////////////////////////////////
	void setAnoPublicacao(int ano)
	{
		this.anoPublicacao = ano;
	}
	int getAnoPublicacao()
	{
		return this.anoPublicacao;
	}
}
