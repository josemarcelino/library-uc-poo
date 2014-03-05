import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Class Biblioteca
 *  
 * @author mariobalca 2012139135
 * @author josemarcelino 2012138018
 *
 */

public class Biblioteca {
	private ArrayList <Utilizador> listaUtilizadores = new ArrayList<Utilizador>();
	private ArrayList <Livro> listaLivro = new ArrayList<Livro>();
	private ArrayList <DVD> listaDVD = new ArrayList<DVD>();
	private ArrayList <Requisicao> listaRequisicoes = new ArrayList<Requisicao>();
	private ArrayList <Autor> listaAutores = new ArrayList<Autor>();
	private int numUser = 0;
	private int numAdmin = 0;
	
	Biblioteca()
	{			
		for(int i=1;i<=5;i++)
		{
			try {
					load(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(listaUtilizadores.isEmpty())
		{
			this.adicionarUser(2, "root","morada", 915485612, "root@whoknows.com", new Date(), "root", "1234", "pro");
			try {
				save(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(Utilizador u: listaUtilizadores)
		{
			if(u.getClass().equals(Admin.class))
			{
				numAdmin++;
			}
			else if(u.getClass().equals(Leitor.class))
			{
				numUser++;
			}
		}
	}
	
	/**
	 * Cria leitor e adiciona-o à ArrayList listaUtilizadores
	 * 
	 * @param tipo
	 * @param nome
	 * @param morada
	 * @param telefone
	 * @param email
	 * @param dataNascimento
	 * @param username
	 * @param palavraChave
	 * @param categoriaProfissional
	 */
	void adicionarUser(int tipo, String nome, String morada, int telefone, String email, Date dataNascimento, String username, String palavraChave, String categoriaProfissional)
	{
		String numInterno = "u#".concat(Integer.toString(numUser));
		if(tipo == 0) // professor
		{
			Leitor professor = new Leitor(true, nome, numInterno, morada, telefone, email, dataNascimento,username, palavraChave);
			listaUtilizadores.add(professor);
			numUser++;
		}
		else if(tipo==1) // aluno
		{
			Leitor aluno = new Leitor(false, nome, numInterno, morada, telefone, email, dataNascimento,username, palavraChave);
			listaUtilizadores.add(aluno);
			numUser++;
		}
		else if(tipo==2) // admin
		{
			numInterno = "a#".concat(Integer.toString(numAdmin));
			Admin admin = new Admin(nome, numInterno, morada, telefone, email, dataNascimento,username, palavraChave, categoriaProfissional);
			listaUtilizadores.add(admin);
			numAdmin++;
		}
	}
	/**
	 * Funcao que valida o login
	 * @param username String
	 * @param password String
	 * @return 0 caso o login seja bem sucedido, -1 se os dados de login estiverem incorrectos, -2 se o utilizador não existir, -3 se a lista de Utilizadores estiver vazia
	 */
	int login(String username, String password)
	{
		int verifica = -3; // caso não exista nenhum utilizador
		
		for (Utilizador user : listaUtilizadores)
		{
			if(user.username.equals(username) == true && user.palavraChave.equals(password) == true) // login bem sucedido
			{
				verifica = 0;
				return verifica;
			}
			else if(user.username.equals(username) == true && user.palavraChave.equals(password) == false) // dados de login errados
			{
				verifica = -1;
			}
			else if(user.username.equals(username) == false) // utilizador não existe
			{
				verifica = -2;
			}
		}
		return verifica;
	}

	
	ArrayList <Requisicao> getlistaRequisicoes()
	{
		return this.listaRequisicoes;
	}
	
	ArrayList <Utilizador> getlistaUtilizadores()
	{
		return this.listaUtilizadores;
	}
	ArrayList <DVD> getlistaDvds()
	{
		return this.listaDVD;
	}
	
	ArrayList <Livro> getlistaLivros()
	{
		return this.listaLivro;
	}
	
	ArrayList <Autor> getlistaAutores()
	{
		return this.listaAutores;
	}
	
	Utilizador getUtilizador(String username)
	{		
		for (Utilizador user : listaUtilizadores)
		{
			if(user.username.equals(username))
			{
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Faz save do programa em ficheiros
	 * @param opcao
	 * @throws IOException
	 */
	void save(int opcao) throws IOException {
		if(opcao == 1) // Utilizadores
		{
			FileOutputStream leitoresFOS = new FileOutputStream("leitores.tmp");
			ObjectOutputStream leitoresOOS = new ObjectOutputStream(leitoresFOS);
			FileOutputStream adminsFOS = new FileOutputStream("admin.tmp");
			ObjectOutputStream adminsOOS = new ObjectOutputStream(adminsFOS);
			
			for(Utilizador u: listaUtilizadores)
			{
				if(u.getClass().equals(Admin.class))
				{
					adminsOOS.writeObject(u);
				}
				else if(u.getClass().equals(Leitor.class))
				{
					leitoresOOS.writeObject(u);
				}
			}
			leitoresOOS.close();
			adminsOOS.close();
		}
		
		else if(opcao==2) // Autores
		{
			FileOutputStream fos = new FileOutputStream("autores.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listaAutores);
			oos.close();
		}
		
		else if(opcao==3) // Livros
		{
			FileOutputStream fos = new FileOutputStream("livros.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listaLivro);
			oos.close();
		}
		
		else if(opcao==4) // DVDs
		{
			FileOutputStream fos = new FileOutputStream("dvds.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listaDVD);
			oos.close();
		}
		
		else if(opcao==5) // Requisições
		{
			FileOutputStream fos = new FileOutputStream("reqs.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listaRequisicoes);
			oos.close();
		}
	}
	void load(int opcao) throws IOException, ClassNotFoundException {
				
		if(opcao == 1) // Utilizadores
		{
			FileInputStream leitoresFIS = new FileInputStream("leitores.tmp");
			ObjectInputStream leitoresOIS = new ObjectInputStream(leitoresFIS);
			FileInputStream adminsFIS = new FileInputStream("admin.tmp");
			ObjectInputStream adminsOIS = new ObjectInputStream(adminsFIS);
			while(leitoresFIS.available() > 0)
			{
				listaUtilizadores.add((Leitor)leitoresOIS.readObject());
			}
			while(adminsFIS.available() > 0)
			{
				listaUtilizadores.add((Admin)adminsOIS.readObject());
			}
			adminsOIS.close();
			leitoresOIS.close();
		}
		
		else if(opcao==2) // Autores
		{
			FileInputStream fis = new FileInputStream("autores.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listaAutores = (ArrayList<Autor>)ois.readObject();
			ois.close();
		}
		
		else if(opcao==3) // Livros
		{
			FileInputStream fis = new FileInputStream("livros.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listaLivro = (ArrayList<Livro>) ois.readObject();
			ois.close();
		}
		
		else if(opcao==4) // DVDs
		{
			FileInputStream fis = new FileInputStream("dvds.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listaDVD = (ArrayList<DVD>) ois.readObject();
			ois.close();
		}
		
		else if(opcao==5) // Requisições
		{
			FileInputStream fis = new FileInputStream("reqs.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listaRequisicoes = (ArrayList<Requisicao>) ois.readObject();
			ois.close();
		}
	}
}
