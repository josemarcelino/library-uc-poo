import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * Classe Admin
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class Admin extends Utilizador{
	private String categoriaProfissional;

	/**
	 * 
	 * @param categoriaProfissional
	 * @param nome
	 * @param morada
	 * @param numeroInterno
	 * @param telefone
	 * @param email
	 * @param dataNascimento
	 * @param palavraChave
	 */
	Admin(String nome, String numeroInterno, String morada, int telefone, String email, Date dataNascimento,String username, String palavraChave, String categoriaProfissional)
	{
		this.nome = nome;
		this.numeroInterno = numeroInterno;
		this.morada = morada;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.username = username;
		this.palavraChave = palavraChave;
		this.categoriaProfissional = categoriaProfissional;
	}
	
	
	void setCategoriaProfissional(String categoriaProfissional)
	{
		this.categoriaProfissional = categoriaProfissional;
	}
	String getCategoriaProfissional()
	{
		return this.categoriaProfissional;
	}
	
	/**
	 *  Adiciona item (Dvd ou Livro)
	 * @param tipo
	 * @param titulo
	 * @param anoPub
	 * @param editora
	 * @param cota
	 * @param isbn
	 * @param autores
	 * @param listaDvd
	 * @param listalivro
	 */
	void adicionarItem(boolean tipo,String titulo, int anoPub ,String editora, String cota, String isbn, ArrayList <Autor> autores,ArrayList<DVD> listaDvd,ArrayList<Livro> listalivro){
		
		if(tipo){
			 DVD novoDvd = new DVD(titulo,anoPub,editora);
			 listaDvd.add(novoDvd);
		}
		
		else {
			 Livro novoLivro = new Livro(titulo,anoPub,editora,cota,isbn,autores);
			 listalivro.add(novoLivro);
		}		
	}
	
	/**
	 * Remove item(Dvd ou Livro)
	 * @param tipo
	 * @param titulo
	 * @param listaDvds
	 * @param listaLivros
	 */
	
	void removerItem(boolean tipo, String titulo,ArrayList<DVD> listaDvds,ArrayList<Livro> listaLivros){

		if(tipo){
			DVD dvd = (DVD) pesquisarItem(true, titulo, listaDvds, listaLivros);
			listaDvds.remove(dvd);
		}
		
		else {
			Livro livro = (Livro) pesquisarItem(false, titulo, listaDvds, listaLivros);
			listaLivros.remove(livro);
		}	

	}
	
	/**
	 *  Calcula o item mais utilizado (Livro ou Dvd)
	 * @param tipo
	 * @param listaDvds
	 * @param listaLivros
	 * @param listaRequisicoes
	 * @return Item
	 */
	Item topItem(boolean tipo, ArrayList<DVD> listaDvds, ArrayList<Livro> listaLivros,ArrayList<Requisicao> listaRequisicoes)
	{		
		Hashtable<String, Integer> livros  = new Hashtable<String, Integer>();
		Hashtable<String, Integer> Dvds = new Hashtable<String, Integer>();

		Date dataInicio = new Date();
		Date dataFim = new Date();

		Calendar mesInicio = Calendar.getInstance();
		Calendar mesFim = Calendar.getInstance();
		mesInicio.setTime(new Date());
		mesFim.setTime(new Date());
		
		mesInicio.add(Calendar.MONTH, -1);
		mesFim.add(Calendar.MONTH, -1);
		mesInicio.set(Calendar.DAY_OF_MONTH, 1);
		if(mesInicio.get(Calendar.MONTH)==1 || mesInicio.get(Calendar.MONTH)==3 || mesInicio.get(Calendar.MONTH)==5 || mesInicio.get(Calendar.MONTH)==7 || mesInicio.get(Calendar.MONTH)==8 || mesInicio.get(Calendar.MONTH)==10 || mesInicio.get(Calendar.MONTH)==12)
		{
			mesFim.set(Calendar.DAY_OF_MONTH, 31);
		}
		else if(mesInicio.get(Calendar.MONTH)==4 || mesInicio.get(Calendar.MONTH)==6 || mesInicio.get(Calendar.MONTH)==9 || mesInicio.get(Calendar.MONTH)==11 || mesInicio.get(Calendar.MONTH)==11)
		{
			mesFim.set(Calendar.DAY_OF_MONTH, 30);
		}
		else if(mesInicio.get(Calendar.MONTH)==2)
		{
			mesFim.set(Calendar.DAY_OF_MONTH, 28);
		}
		
		dataInicio = mesInicio.getTime();
		dataFim = mesFim.getTime();
		
		for(Requisicao r :listaRequisicoes)
		{
			String keyTitulo = r.getItemRequisitado().getTitulo();
			if(r.getItemRequisitado().getClass().equals(DVD.class) && r.getDataRequisicao().compareTo(dataInicio)>=0 && r.getDataRequisicao().compareTo(dataFim)<=0)
			{
				if(Dvds.containsKey(keyTitulo)==false)
				{
					Dvds.put(keyTitulo, 1);
				}
				else
				{
					Dvds.put(r.getItemRequisitado().getTitulo(), Dvds.get(keyTitulo)+1);
				}
			}
			
			else if(r.getItemRequisitado().getClass().equals(Livro.class) && r.getDataRequisicao().compareTo(dataInicio)>=0 && r.getDataRequisicao().compareTo(dataFim)<=0)
			{
				if(livros.containsKey(keyTitulo)==false)
				{
					livros.put(keyTitulo, 1);
				}
				else
				{
					livros.put(r.getItemRequisitado().getTitulo(), livros.get(keyTitulo)+1);
				}
			}
		}
	
		if(tipo)
		{
			int intMaxDVD=0;
			String dvdmax = null;
			Set<String> keys = Dvds.keySet();
			for(String f : keys)
			{
				if(Dvds.get(f) > intMaxDVD) 
				{
					intMaxDVD = Dvds.get(f);
					dvdmax = f;
				}					
			}
			return pesquisarItem(true, dvdmax, listaDvds, null);
		}
		
		else
		{
			int intMaxLivro=0;
			String livroMax = null;
			Set<String> keys = livros.keySet();
			for(String f : keys)
			{
				if(livros.get(f) > intMaxLivro) 
				{
					intMaxLivro = livros.get(f);
					livroMax = f;
				}					
			}
			return pesquisarItem(false, livroMax, null, listaLivros);
		}		
	}

	/**
	 *  Requisita um item da biblioteca (Livro ou Dvd)
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	void requisitarItem(Item item, ArrayList<Requisicao> listaRequisicoes) 
	{
		Date dataActual = new Date();
		Date dataLimite = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		c.add(Calendar.DATE, 90);
		dataLimite = c.getTime();
		
		Requisicao aux = new Requisicao(dataActual, null, dataLimite, this, item);
		listaRequisicoes.add(aux);
	}
	
	/**
	 * Devolve um item da biblioteca (Livro ou dvd)
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	void entregarItem(Item item, ArrayList<Requisicao> listaRequisicoes) 
	{
		Date dataActual = new Date();
		for(Requisicao r: listaRequisicoes)
		{
			if(r.getItemRequisitado().getTitulo().equals(item.getTitulo()) && r.getItemRequisitado().getEditora().equals(item.getEditora()) && r.getItemRequisitado().getAnoPublicacao() == item.getAnoPublicacao() && r.getUser().getUsername().equals(this.username) && r.getDataEntrega()==null)
			{
				r.setDataEntrega(dataActual);
			}
		}
	}
	/**
	 * 
	 * Lista os itens nunca requisitados e devolve uma arraylist
	 * @param listaRequisicoes
	 * @param listaLivros
	 * @param listaDVD
	 * @return ArrayList<Item>
	 */
	
	ArrayList<Item> listarItemsNuncaRequisitados(ArrayList<Requisicao> listaRequisicoes, ArrayList<Livro> listaLivros, ArrayList<DVD> listaDVD)
	{
		ArrayList<Item> res = new ArrayList<Item>();
		Date dataInicio = new Date();
		Date dataFim = new Date();

		Calendar mesInicio = Calendar.getInstance();
		mesInicio.setTime(new Date());
		mesInicio.set(Calendar.DAY_OF_MONTH, 1);

		dataInicio = mesInicio.getTime();
		
		for(Livro l:listaLivros)
		{
			boolean verifica=true;
			for(Requisicao r:listaRequisicoes)
			{
				if(r.getItemRequisitado().equals(l) && r.getDataRequisicao().compareTo(dataInicio)>=0 && r.getDataRequisicao().compareTo(dataFim)<=0)
				{
					verifica=false;
				}
			}
			if(verifica)
			{
				res.add(l);
			}
		}
		
		return res;
	}
	
	/**
	 * Lista os itens requisitados e devolve uma arraylist
	 * @param listaRequisicoes
	 * @return ArrayList<Item>
	 */
	
	ArrayList<Item> listarItemsRequisitados(ArrayList<Requisicao> listaRequisicoes)
	{
		ArrayList<Item> res = new ArrayList<Item>();
		for(Requisicao r: listaRequisicoes)
		{
			if(r.getDataEntrega() == null)
			{
				res.add(r.getItemRequisitado());
			}
		}
		
		return res;
	}
	
	/**
	 * Lista os itens em atraso e devolve numa arraylist
	 * @param listaRequisicoes
	 * @return ArrayList<Requisicao>
	 */
	
	ArrayList<Requisicao> listarItemsAtrasados(ArrayList<Requisicao> listaRequisicoes)
	{
		ArrayList<Requisicao> res = new ArrayList<Requisicao>();
		Date dataActual = new Date();
		for(Requisicao r: listaRequisicoes)
		{
			if(r.getDataLimite().compareTo(dataActual)<0 && r.getDataEntrega() == null)
			{
				res.add(r);
			}
		}
		
		return res;
	}
	
	/**
	 * Procura um user atraves do username
	 * @param username
	 * @param listaUsers
	 * @return Utilizador
	 */
	Utilizador procurarUser(String username, ArrayList<Utilizador> listaUsers)
	{
		for(Utilizador u: listaUsers)
		{
			if(u.username.equals(username))
			{
				return u;
			}
		}
		return null;
	}
	
	/**
	 *  remove um user atraves do username
	 * @param username
	 * @param listaUsers
	 */
	
	void removerUser(String username, ArrayList<Utilizador> listaUsers)
	{
		Utilizador aEliminar = procurarUser(username, listaUsers);
		if(aEliminar != null)
		{
			listaUsers.remove(aEliminar);
		}
	}
	 /**
	  * Procura um autor na lista de Autores atraves do username e devolve em seguida
	  * @param nome
	  * @param listaAutores
	  * @return Autor
	  */
	Autor procurarAutor(String nome, ArrayList<Autor> listaAutores)
	{
		for(Autor a:listaAutores)
		{
			if(a.getNome().equals(nome))
			{
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Adiciona um novo autor à biblioteca
	 * @param nome
	 * @param dataNasc
	 * @param email
	 * @param listaAutores
	 */
	void adicionarAutor(String nome, Date dataNasc, String email, ArrayList<Autor> listaAutores)
	{
		Autor a = new Autor(nome, dataNasc, email);
		listaAutores.add(a);
	}
	
	/**
	 * Remove um autor da biblioteca
	 * @param nome
	 * @param listaAutores
	 */
	
	void removerAutor(String nome, ArrayList<Autor> listaAutores)
	{
		Autor aEliminar = procurarAutor(nome, listaAutores);
		if(aEliminar != null)
		{
			listaAutores.remove(aEliminar);
		}
	}
}
