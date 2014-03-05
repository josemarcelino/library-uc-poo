import java.util.ArrayList;

/**
 * Class Livro
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class Livro extends Item {
	private String cota;
	private String isbn;
	private ArrayList<Autor> autores = new ArrayList<Autor>();
	
	Livro(String titulo, int anoPub, String editora, String cota, String isbn, ArrayList<Autor> autores)
	{	
		this.titulo = titulo;
		this.anoPublicacao = anoPub;
		this.editora = editora;
		this.cota = cota;
		this.isbn = isbn;
		this.autores = autores;
	}

	//////////////////////////////////////////////
	void setCota(String cota)
	{
		this.cota = cota;
	}
	
	String getCota()
	{
		return this.cota;
	}
	//////////////////////////////////////////////
	void setISBN(String isbn)
	{
		this.isbn = isbn;
	}
	String getISBN()
	{
		return this.isbn;
	}
	//////////////////////////////////////////////
	void setAutor(ArrayList<Autor> autores)
	{
		this.autores = autores;
	}
	ArrayList<Autor> getAutor()
	{
		return this.autores;
	}
}