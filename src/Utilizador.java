import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class Utilizador
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

abstract class Utilizador implements Serializable {
	protected String nome;
	protected String numeroInterno;
	protected String morada;
	protected int telefone;
	protected String email;
	protected Date dataNascimento;
	protected String username;
	protected String palavraChave;
	
	String getNome()
	{
		return this.nome;
	}
	
	void setNome(String nome)
	{
		this.nome = nome;
	}
	
	String getNumeroInterno()
	{
		return this.numeroInterno;
	}
	
	void setNumeroInterno(String num)
	{
		this.numeroInterno= num;
	}
	
	String getMorada()
	{
		return this.morada;
	}
	
	void setMorada(String morada)
	{
		this.morada = morada;
	}
	
	int getTelefone()
	{
		return this.telefone;
	}
	
	void setTelefone(int tel)
	{
		this.telefone = tel;
	}
	
	String getEmail()
	{
		return this.email;
	}
	
	void setEmail(String email)
	{
		this.email = email;
	}
	
	Date getDataNascimento()
	{
		return this.dataNascimento;
	}
	
	void setDataNascimento(Date data)
	{
		this.dataNascimento = data;
	}
	
	String getUsername()
	{
		return this.username;
	}
	
	void setUsername(String user)
	{
		this.username = user;
	}
	
	String getPalavraChave()
	{
		return this.palavraChave;
	}
	
	void setPalavraChave(String pass)
	{
		this.palavraChave = pass;
	}
	

	
	/**
	 * Pesquisa um item atraves do nome
	 * @param item
	 * @param nome
	 * @param listaDVDs
	 * @param listaLivros
	 * @return Item ou então caso não encontre nenhum Item correspondente retorna null
	 */
	Item pesquisarItem(boolean item, String nome,ArrayList<DVD> listaDVDs,  ArrayList<Livro> listaLivros)
	{
		if(item == true) // DVD
		{
			for(DVD d: listaDVDs)
			{
				if(d.titulo.equals(nome))
				{
					return d;
				}
			}
		}
		else if(item == false) // Livro
		{	
			for(Livro l: listaLivros)
			{
				if(l.titulo.equals(nome))
				{
					return l;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Pesquisa uma requisição atraves do nome
	 * @param titulo
	 * @param listaRequisicoes
	 * @return Requisicao
	 */
	Requisicao pesquisarRequisicao(String titulo,  ArrayList<Requisicao> listaRequisicoes)
	{
		
		for(Requisicao r : listaRequisicoes)
		{
			if(r.getItemRequisitado().getTitulo().equals(titulo) && r.getDataEntrega()==null)
			{
				return r;
			}
		}
		
		return null;
	}
	
	/**
	 * Lista os items disponiveis para serem requisitados
	 * @param listaRequisicoes
	 * @param listaLivros
	 * @param listaDVDs
	 * @return ArrayList<Item>
	 */
	ArrayList<Item> listarItemsDisponiveis(ArrayList<Requisicao>listaRequisicoes, ArrayList<Livro> listaLivros, ArrayList<DVD> listaDVDs)
	{
		ArrayList<Item> res = new ArrayList<Item>();
		for(Livro l : listaLivros)
		{
			boolean ver=true;
			for(Requisicao r: listaRequisicoes)
			{
				if(r.getItemRequisitado().getTitulo().equals(l.getTitulo()) && r.getItemRequisitado().getEditora().equals(l.getEditora()) && r.getItemRequisitado().anoPublicacao == l.anoPublicacao)
				{
					if(r.getDataEntrega() == null)
						ver = false;
				}
			}
			if(ver)
			{
				res.add(l);
			}
		}
		for(DVD d : listaDVDs)
		{
			boolean ver=true;
			for(Requisicao r: listaRequisicoes)
			{
				if(r.getItemRequisitado().getTitulo().equals(d.getTitulo()) && r.getItemRequisitado().getEditora().equals(d.getEditora()) && r.getItemRequisitado().anoPublicacao == d.anoPublicacao)
				{
					if(r.getDataEntrega()==null)
						ver = false;
				}
			}
			if(ver)
			{
				res.add(d);
			}
		}
		return res;		
	}
	/**
	 * 
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	abstract void requisitarItem(Item item, ArrayList<Requisicao> listaRequisicoes);
	/**
	 * 
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	abstract void entregarItem(Item item, ArrayList<Requisicao> listaRequisicoes);
}
