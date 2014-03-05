import java.io.Serializable;
import java.util.Date;

/**
 * Class Autor
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

class Autor implements Serializable {
	private String nome;
	private Date dataNascimento;
	private String email;	
	
	Autor(String nome, Date dataNascimento,String email)
	{
		this.nome = nome;
		this.dataNascimento=dataNascimento;
		this.email=email;
	}
	
	
	void setNome(String nome)
	{
		this.nome = nome;
	}
	String getNome()
	{
		return this.nome;
	}
	
	void setEmail(String email)
	{
		this.email = email;
	}
	String getEmail()
	{
		return this.email;
	}
	
	
	void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}
	Date getDataNascimento()
	{
		return this.dataNascimento;
	}
	
}
