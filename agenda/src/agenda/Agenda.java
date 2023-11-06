package agenda;

import java.util.Arrays;

/**
 * Representação de uma agenda para controle de contatos.
 *
 * @author Bruno Osório
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	private static final int TAMANHO_FAVORITOS = 10;

	private Contato[] contatos;
	private Contato[] favoritos;

	/**
	 * Constrói uma Agenda.
	 * Cada agenda armazena no máximo 100 contatos e 10 favoritos.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}

	/**
	 * Retorna a lista de contatos armazenados.
	 * O segue segue o formato: " ID_CONTATO - NOME_COMPLETO"
	 *
	 * @return uma String com a lista de contatos.
	 */
	public String getContatos() {
		StringBuilder out = new StringBuilder();
		out.append(1).append(" - ").append(this.contatos[0].getNomeCompleto());
		for (int i=1; i<this.contatos.length; i++){
			if (this.contatos[i] != null) {
				out.append("\n").append(i + 1).append(" - ");
				out.append(this.contatos[i].getNomeCompleto());
			}
		}
		return out.toString();
	}

	/**
	 * Retorna o Contato na posição informada.
	 * Caso a posição seja inválida ou o não tenha contato nessa posição
	 * uma mensagem de erro é retornada.
	 *
	 * @param posicao a posicao do Contato.
	 *
	 * @return uma String com o Contato.
	 */
	public String getContato(int posicao) {
		if (!verificaPosicao(posicao)) { return "POSIÇÃO INVÁLIDA"; }
		if(verificaPosicaoVazia(posicao)) { return "POSIÇÃO SEM CONTATO CADASTRADO"; }
		return this.contatos[posicao - 1].toString();
	}

	/**
	 * Verifica se existe contato cadastrado naquela posição.
	 *
	 * @param posicao posição a ser verificada.
	 *
	 * @return true caso tenha contato, false caso contrário.
	 */
	private boolean verificaPosicaoVazia(int posicao) {
		return this.contatos[posicao - 1] == null;
	}

	/**
	 * Cadastra um novo contato na posicao informada.
	 * Casa a posição seja inválida ou um Contato igual ja esteja cadastrado,
	 * uma mensagem de erro é retornada.
	 *
	 * @param posicao posição a ser cadastrado o novo contato.
	 * @param nome nome do Contato.
	 * @param sobrenome sobrenome do Contato.
	 * @param telefone telefone do Contato.
	 *
	 * @return uma String que representa o resultado da operação de cadastro.
	 */
	public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		Contato novo = new Contato(nome, sobrenome, telefone);
		if (!verificaPosicao(posicao)) { return "POSIÇÃO INVÁLIDA!"; }
		if (!verificaSeEhNovoContato(novo)) { return "CONTATO JA CADASTRADO"; }
		this.contatos[posicao - 1] = novo;
		return "CADASTRO REALIZADO";
	}

	/**
	 * Verifica se uma posição é valida.
	 * Uma posição é valida se estiver entre 1 e 100, inclusive.
	 * Caso não seja, uma exceção é lançada.
	 *
	 * @param posicao posicao a ser verificada.
	 *
	 * @return true caso a posicao seja válida, false caso seja inválida.
	 */
	private boolean verificaPosicao(int posicao) {
        return !(posicao < 1 || posicao > 100);
    }

	/**
	 * Verifica se já existe um Contato igual cadastrado.
	 *
	 * @param contato novo contato a ser cadastrado.
	 *
	 * @return true caso o contato seja novo, false caso já exista um Contato igual.
	 */
	private boolean verificaSeEhNovoContato(Contato contato) {
		for (Contato c : this.contatos) {
			if (c != null && c.equals(contato)){ return false; }
		}
		return true;
	}

	/**
	 * Gera uma lista com os contatos que são favoritos, no formato
	 * "POSICAO - NOME COMPLETO"
	 *
	 * @return uma String com uma lista dos contatos favoritados.
	 */
	public String listaFavoritos() {
		StringBuilder out = new StringBuilder();
		out.append(1).append(" - ").append(this.favoritos[0].getNomeCompleto());
		for (int i=1; i<this.favoritos.length;i++) {
			if (this.favoritos[i] != null) {
				out.append("\n").append(i + 1).append(" - ").append(this.favoritos[i].getNomeCompleto());
			}
		}
		return out.toString();
	}

	/**
	 * Adiciona um novo Contato aos favoritos.
	 * Posições inválidas e Contatos duplicados não são permitidos.
	 *
	 * @param contato contato a ser favoritado
	 * @param posicao posição em que o contato será adicionado.
	 *
	 * @return uma String com o resultado da tentativa de adição.
	 */
	public String adicionaFavorito(int contato, int posicao) {
		if (!verificaPosicao(posicao)) { return "POSIÇÃO INVÁLIDA!"; }
		if (verificaSeJaEhFavorito(contato)) { return "CONTATO JÁ É FAVORITO"; }
		if (verificaSeTemContatoNaPosicao(posicao)) { this.favoritos[posicao - 1].desfavoritaContato(); }
		adicionaFavoritoNaLista(contato, posicao);
		return "CONTATO FAVORITADO NA POSICAO " + posicao;
	}

	/**
	 * Verifica se um contato já é favorito.
	 *
 	 * @param contato contato a ser verificado.
	 * @return true caso o contato já seja favorito, false caso contrário.
	 */
	private boolean verificaSeJaEhFavorito(int contato) {
		for (Contato f : this.favoritos) {
			if (f != null && f.equals(this.contatos[contato - 1])){ return true; };
		}
		return false;
	}

	/**
	 * Adiciona um Contato no array de favoritos.
	 *
	 * @param contato contato a ser adicionado.
	 * @param posicao posicao do novo contato.
	 */
	private void adicionaFavoritoNaLista(int contato, int posicao) {
		this.favoritos[posicao - 1] = this.contatos[contato - 1];
		this.favoritos[posicao - 1].favoritaContato();
	}

	/**
	 * Verifica se já existe um contato cadastrado no array de favoritos na posição informada.
	 *
	 * @param posicao posição a ser verificada.
	 * @return true caso tenha contato na posição, false caso não tenha.
	 */
	private boolean verificaSeTemContatoNaPosicao(int posicao) {
		return this.favoritos[posicao - 1] != null;
	}

	/**
	 * Remove o favorito da posição informada.
	 * Caso a posicao seja invalida, a operação não é feita.
	 *
	 * @param posicao do favorito a ser removido.
	 * @return true caso a operação seja concluida, false caso contrario.
	 */
	public boolean removeFavorito(int posicao) {
		if (!verificaPosicao(posicao)) { return false; }
		this.contatos[posicao - 1].desfavoritaContato();
		this.favoritos[posicao - 1] = null;
		return true;
	}

	/**
	 * Altera o telefone do contato iformado.
	 * Caso a posicao seja invalida a operação não é concluida.
	 *
	 * @param contato posicao do contato a ser alterado.
	 * @param telefone novo telefone a ser adicionado.
	 *
	 * @return true caso a operação seja feita, false caso contrario.
	 */
	public boolean alteraTelefone(int contato, String telefone) {
		if (!verificaPosicao(contato)) { return false; }
		this.contatos[contato - 1].alteraTelefone(telefone);
		return true;
	}

	/**
	 * Adicona uma tag a um ou mais contatos.
	 * Recebe uma String com as posições dos contatos separados por espaços.
	 *
	 * @param posicoesContatos uma String com as posições dos contatos.
	 * @param tag tag a ser adicinada no(s) contato(s).
	 *
	 * @return true caso a operação seja concluida.
	 */
	public boolean adicionaTags(String posicoesContatos, String tag) {
		int[] intPosicoes = Arrays.stream(posicoesContatos.split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int posicao : intPosicoes) { this.contatos[posicao - 1].adicionaTag(tag); }
		return true;
	}

	/**
	 * Remove contato da array de contatos.
	 * Caso a posicao seja invalida a operação não é concluida.
	 *
	 * @param posicao posicao do contato a ser removido.
	 * @return true caso a operação seja concluida.
	 */
	public boolean removeContato(int posicao) {
		if (!verificaPosicao(posicao)) { return false; }
		removeFavorito(posicao);
		this.contatos[posicao - 1] = null;
		return true;
	}

	/**
	 * Realiza uma busca dos contatos com o nome informado.
	 * É retornado as informções de todos os contatos com esse nome.
	 *
	 * @param nome nome a ser pesquisado.
	 * @return String com a lista de todos os contatos com o nome desejado.
	 */
	public String buscaContatoPeloNome(String nome) {
		StringBuilder out = new StringBuilder();
		for (Contato c : this.contatos) {
			if (c != null && c.getNome().equalsIgnoreCase(nome)) {
				out.append(c.toString()).append("\n");
			}
		}
		return out.toString();
	}

	/**
	 * Realiza uma busca dos contatos com o sobrenome informado.
	 * É retornado as informções de todos os contatos com esse sobrenome.
	 *
	 * @param sobrenome nome a ser pesquisado.
	 * @return String com a lista de todos os contatos com o sobrenome desejado.
	 */
	public String buscaContatoPeloSobrenome(String sobrenome) {
		StringBuilder out = new StringBuilder();
		for (Contato c : this.contatos) {
			if (c != null && c.getSobrenome().equalsIgnoreCase(sobrenome)) {
				out.append(c.toString()).append("\n");
			}
		}
		return out.toString();
	}

}
