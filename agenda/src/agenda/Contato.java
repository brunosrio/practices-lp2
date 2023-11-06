package agenda;

import java.util.Objects;

/**
 * Representação de um Contato da Agenda. Todo contato precisa ter nome e telefone.
 * Um contato é identificado unicamente por seu nome e sobrenome.
 *
 * @author Bruno osório
 */
public class Contato {
    private static final int QTDE_TAGS = 5;
    private String nome;
    private String sobrenome;
    private String telefone;
    private boolean ehFavorito;
    private String[] tags;
    private int contaTags;

    /**
     * Constrói um contato a partir de seu nome, sobrenome e telefone.
     * Todo contato começa como não favorito.
     * Atributos nulos não são aceitos, assim como nome ou telefone vazio.
     *
     * @param nome o nome do Contato.
     * @param sobrenome o sobrenome do Contato.
     * @param telefone o telefone do Contato.
     */
    public Contato(String nome, String sobrenome, String telefone) {
        verificaAtributoInvalido(nome, telefone);
        verificaAtributoNulo(nome, sobrenome, telefone);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.ehFavorito = false;
        this.tags = new String[QTDE_TAGS];
        this.contaTags = 0;
    }

    /**
     * Retorna o nome do Contato.
     *
     * @return uma String com o nome do Contato.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o sobrenome do Contato.
     *
     * @return uma String com o sobrenome do Contato.
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /**
     * Retorna o telefone do Contato.
     *
     * @return uma String com o telefone do Contato.
     */
    public String getTelefone() { return this.telefone; }

    /**
     * Retorna o nome completo do Contato, incluindo nome + sobrenome.
     * Caso o sobrenome seja vazio, é retornado apenas o nome.
     *
     * @return uma String com o nome e sobrenome do Contato.
     */
    public String getNomeCompleto(){
        if (this.sobrenome.isBlank()) { return this.nome; }
        return this.nome + " " + this.sobrenome;
    }

    /**
     * Retorna a condiçõa de favorito do Contato.
     *
     * @return true se o Contato for favorito, false caso não seja.
     */
    public boolean getEhFavorito() {
        return this.ehFavorito;
    }

    /**
     * Altera o telefone do Contato.
     *
     * @param novoTelefone o novo telefone a ser adicionado.
     */
    public void alteraTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    /**
     * Adiciona uma tag ao array de tags.
     * @param tag tag a ser adicionada.
     */
    public void adicionaTag(String tag) {
        this.tags[contaTags++] = tag;
    }

    /**
     * Favorita o Contato. Muda sua condição de favorito para verdadeiro.
     */
    public void favoritaContato() {
        this.ehFavorito = true;
    }

    /**
     * Desfavorita o Contato. Muda sua condição de favorito para falso.
     */
    public void desfavoritaContato() {
        this.ehFavorito = false;
    }

    /**
     * Verifica se existe algum atribulo nulo usado na criação do Contato.
     * Caso exista, é lançada uma exceção.
     *
     * @param nome nome do Contato
     * @param sobrenome sobrenome do Contato.
     * @param telefone telefone do Contato.
     */
    private void verificaAtributoNulo(String nome, String sobrenome, String telefone) {
        if (nome == null || sobrenome == null || telefone == null) {
            throw new NullPointerException("ATRIBUTO NULO");
        }
    }

    /**
     * Verifica se o nome ou telefone do contato estão vazios.
     * Caso um deles esteja vazio, é lançada uma exceção.
     * @param nome
     * @param telefone
     */
    private void verificaAtributoInvalido(String nome, String telefone) {
        if (nome.isBlank() || telefone.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVALIDO");
        }
    }

    /**
     * Retorna a String que representa o Contato. A representação segue o formato
     * "NOME_COMPLETO"
     * "TELEFONE"
     * Caso o contato seja favorito, um emoji é adicionado antes do nome do Contato.
     *
     * @return a representção em String do Contato.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (this.ehFavorito) { out.append("❤ "); }
        out.append(this.getNomeCompleto()).append("\n").append(this.telefone);
        return out.toString();
    }

    /**
     * Verifica se dois Contatos são iguais.
     * Eles serão iguais caso seu nome e sobrenome sejam iguais.
     *
     * @param o contato a ser comparado.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobrenome, contato.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }
}
