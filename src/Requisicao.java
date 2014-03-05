import java.io.Serializable;
import java.util.Date;

/**
 * Class Requesicao
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class Requisicao implements Serializable {
	private Date dataRequisicao;
	private Date dataEntrega;
	private Date dataLimite;
	private Utilizador user;
	private Item itemRequisitado;
	
	
	
	
	Requisicao(Date dataRequisicao, Date dataEntrega, Date dataLimite, Utilizador user, Item itemRequisitado)
	{
		this.dataRequisicao = dataRequisicao;
		this.dataEntrega = dataEntrega;
		this.dataLimite = dataLimite;
		this.user = user;
		this.itemRequisitado= itemRequisitado;
	}

	
	Date getDataRequisicao()
	{
		return this.dataRequisicao;
	}
	
	void setDataRequisicao(Date dataRequisicao)
	{
		this.dataRequisicao = dataRequisicao;
	}
	
	
	Date getDataEntrega()
	{
		return this.dataEntrega;
	}
	
	void setDataEntrega(Date dataEntrega)
	{
		this.dataEntrega = dataEntrega;
	}
	
	
	Date getDataLimite()
	{
		return this.dataLimite;
	}
	
	void setDataLimite(Date dataLimite)
	{
		this.dataLimite = dataLimite;
	}
	
	Utilizador getUser()
	{
		return this.user;
	}
	
	void setUser(Utilizador user)
	{
		
		this.user = user;
	}
	
	Item getItemRequisitado()
	{
		return this.itemRequisitado;
	}
	
	void setItemRequisitado(Item itemRequisitado)
	{
		this.itemRequisitado = itemRequisitado;
	
	}
}