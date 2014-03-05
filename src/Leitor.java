import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class Leitor
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class Leitor extends Utilizador{
	private int maxRequisicoes;
	private int numRequisicoes;
	private boolean tipo;
	
	Leitor(boolean tipo, String nome, String numeroInterno, String morada, int telefone, String email, Date dataNascimento,String username, String palavraChave){
		this.nome = nome;
		this.numeroInterno = numeroInterno;
		this.morada = morada;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.username = username;
		this.palavraChave = palavraChave;
		this.tipo = tipo;
		if(this.tipo == true)
		{
			this.maxRequisicoes = 5;
		}
		else if(this.tipo == false)
		{
			this.maxRequisicoes = 2;
		}
		this.numRequisicoes = 0;
	}
	
	int getMaxRequisicoes()
	{
		return this.maxRequisicoes;
	}
	
	void setMaxRequisicoes(int maxRequisicoes)
	{
		this.maxRequisicoes = maxRequisicoes;
	}
	
	int getNumRequisicoes()
	{
		return this.numRequisicoes;
	}
	
	void setNumRequisicoes(int numRequisicoes)
	{
		this.numRequisicoes = numRequisicoes;
	}
	
	boolean getTipo()
	{
		return this.tipo;
	}
	
	void setTipo(boolean tipo)
	//0 é aluno, 1 é professor
	{
		this.tipo = tipo;
	}

	/**
	 * Requisita um item da biblioteca e atribuia ao leitor
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	void requisitarItem(Item item, ArrayList<Requisicao> listaRequisicoes) 
	{
		Date dataActual = new Date();
		Date dataLimite = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		if(this.tipo == true) // professor
		{
			c.add(Calendar.DATE, 90);
			dataLimite = c.getTime();
		}
		else if(this.tipo == false) // aluno
		{
			c.add(Calendar.DATE, 5);
			dataLimite = c.getTime();
		}
		
		Requisicao aux = new Requisicao(dataActual, null, dataLimite, this, item);
		listaRequisicoes.add(aux);
		this.numRequisicoes++;
	}
	/**
	 * entrega o item à biblioteca
	 * @param item Item
	 * @param listaRequisicoes ArrayList<Requisicao>
	 */
	void entregarItem(Item item, ArrayList<Requisicao> listaRequisicoes) 
	{
		Date dataActual = new Date();
		for(Requisicao r: listaRequisicoes)
		{
			if(r.getItemRequisitado().getTitulo().equals(item.getTitulo()) && r.getItemRequisitado().getEditora().equals(item.getEditora()) && r.getItemRequisitado().getAnoPublicacao() == item.getAnoPublicacao())
			{
				r.setDataEntrega(dataActual);
			}
		}
		this.numRequisicoes--;
	}
}
