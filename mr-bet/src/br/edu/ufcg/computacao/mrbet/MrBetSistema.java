package br.edu.ufcg.computacao.mrbet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representação de uma sistema para gerenciamento de apostas.
 * Controla times, campeonatos e as apostas realizadas.
 *
 * @author Bruno Osório
 */
public class MrBetSistema {
    private Map<String, Time> times;
    private Map<String, Campeonato> campeonatos;
    private List<Aposta> apostas;

    /**
     * Constói um controlador do sistema.
     */
    public MrBetSistema() {
        this.times = new HashMap<>();
        this.campeonatos = new HashMap<>();
        this.apostas = new ArrayList<>();
    }

    /**
     * Cadastra um novo time no sistema a partir do seu código, nome e mascote;
     * Todos os times são armazendos em um mesmo Mapa.
     *
     * @param codigo código do time.
     * @param nome nome do time.
     * @param mascote mascote do time.
     *
     * @return uma mensagem com o resultado da operação.
     */
    public String cadastraTime(String codigo, String nome, String mascote) {
        if (codigo == null || nome == null || mascote == null) { throw new NullPointerException(); }
        if (codigo.isBlank() || nome.isBlank() || mascote.isBlank()) { throw new IllegalArgumentException(); }
        if (times.containsKey(codigo)) { return "TIME JÁ EXISTE"; }
        times.put(codigo, new Time(codigo, nome, mascote));
        return "INCLUSÃO REALIZADA!";
    }

    /**
     * Busca um time a partir de seu código e retorna sua representação textual.
     * Caso o time não exista, uma exceção é lançada.
     *
     * @param codigo código do Time.
     * @return a representação textual de um time.
     */
    public String recuperaTime(String codigo) {
        if (codigo == null) { throw new NullPointerException(); }
        if (codigo.isBlank()) { throw new IllegalArgumentException(); }
        verificaSeTimeExiste(codigo);
        return this.times.get(codigo).toString();
    }

    /**
     * Adiciona um novo campeonato ao sistema a partir de seu nome e quantidade de máxima de times.
     *
     * @param nome nome do campeonato.
     * @param qtdeTimes quantidade máxima de times no campeonato.
     * @return uma mensagem com o resultado da operação.
     */
    public String adicionaCampeonato(String nome, int qtdeTimes) {
        if (nome == null || qtdeTimes == 0) { throw new NullPointerException(); }
        if (nome.isBlank()) { throw new IllegalArgumentException(); }
        if (this.campeonatos.containsValue(new Campeonato(nome, qtdeTimes))) { return "CAMPEONATO JÁ EXISTE!"; }
        this.campeonatos.put(nome, new Campeonato(nome, qtdeTimes));
        return "INCLUSÃO REALIZADA!";
    }

    /**
     * Adiciona um novo time a um campeonato.
     * Caso o time ou o campeonato não existam, uma exceção é lançada. É verificado se ainda há vagas no campenato,
     * assim como se o time já faz parte do campeonato.
     *
     * @param codigoTime código do time.
     * @param nomeCampeonato nome do campeonato.
     * @return mensagem com o resultado da operação.
     */
    public String incluiTimeNoCampeonato(String codigoTime, String nomeCampeonato) {
        if (codigoTime == null || nomeCampeonato == null) { throw new NullPointerException(); }
        if (codigoTime.isBlank() || nomeCampeonato.isBlank()) { throw new IllegalArgumentException(); }
        verificaSeTimeExiste(codigoTime);
        verificaSeCampeonatoExiste(nomeCampeonato);
        if (!this.campeonatos.get(nomeCampeonato).verificaLimiteDeTimes()) {
            return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
        }
        if (this.campeonatos.get(nomeCampeonato).verificaTimeNoCampeonato(this.times.get(codigoTime))) {
            return "TIME INCLUÍDO NO CAMPEONATO!";
        }
        this.campeonatos.get(nomeCampeonato).adicionaTime(this.times.get(codigoTime));
        this.times.get(codigoTime).adicionaCampeonato(this.campeonatos.get(nomeCampeonato));
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }

    /**
     * Verifica se um time está no campeonato a partir do seu código.
     * Caso o time ou o campeonato não existam, uma exceção é lançada.
     *
     * @param codigoTime código do time.
     * @param nomeCampeonato nome do campeonato.
     * @return mensagem dizendo se o time está ou não no campeonato.
     */
    public String verificaTimeNoCampeonato(String codigoTime, String nomeCampeonato) {
        Time time = this.times.get(codigoTime);
        Campeonato campeonato = this.campeonatos.get(nomeCampeonato);
        verificaSeTimeExiste(codigoTime);
        verificaSeCampeonatoExiste(nomeCampeonato);
        return campeonato.verificaTimeNoCampeonato(time) ? "O TIME ESTÁ NO CAMPEONATO!" : "O TIME NÃO ESTÁ NO CAMPEONATO!";
    }

    /**
     * Busca todos os campeonatos que um time participa a partir do seu código.
     * Caso o time não exista uma exceção é lançada.
     *
     * @param codigoTime código do time.
     * @return um array com a representação textual de cada campeonato que o time participa.
     */
    public String[] buscaCampeonatosDoTime(String codigoTime) {
        if (codigoTime == null) { throw new NullPointerException(); }
        if (codigoTime.isBlank()) { throw new IllegalArgumentException(); }
        verificaSeTimeExiste(codigoTime);
        String[] campeonatos = new String[100];
        int contador = 0;
        for (Campeonato camp : this.times.get(codigoTime).getCampeonatos()) {
            campeonatos[contador++] = camp.toString();
        }
        return campeonatos;
    }

    /**
     * Adiciona uma nova aposta ao sistema.
     * Uma aposta contem um time e sua colocação, campeonato e valor da aposta.
     * Caso time ou campeonato não existam uma exceção é lançada.
     *
     * @param codigoTime código do time.
     * @param nomeCampeonato nome do campeonato.
     * @param colocacao colocação do time no campeonato.
     * @param valor valor do aposta.
     * @return mensagem com o resultado da operação.
     */
    public String adicionaAposta(String codigoTime, String nomeCampeonato, int colocacao, double valor) {
        if (codigoTime == null || nomeCampeonato == null || colocacao == 0 || valor == 0.0) { throw new NullPointerException(); }
        if (codigoTime.isBlank() || nomeCampeonato.isBlank()) { throw new IllegalArgumentException(); }
        verificaSeTimeExiste(codigoTime);
        verificaSeCampeonatoExiste(nomeCampeonato);
        if (!this.campeonatos.get(nomeCampeonato).verificaColocacao(colocacao)) { return "APOSTA NÃO REGISTRADA"; }
        this.apostas.add(new Aposta(this.times.get(codigoTime), this.campeonatos.get(nomeCampeonato), colocacao, valor));
        if (colocacao == 1) { this.times.get(codigoTime).aumentaPopularidade(); }
        return "APOSTA REGISTRADA";
    }

    /**
     * Mostra represtação textual de todas as apostas registrada no sistema, em ordem.
     * @return array com as apostas registradas no sistema.
     */
    public String[] statusDasApostas() {
        int contador = 0;
        String[] status = new String[this.apostas.size()];
        for (int i = 0; i < this.apostas.size(); i++) {
            status[contador++] = (i+1) + ". " + this.apostas.get(i).toString() + "\n";
        }
        return status;
    }

    /**
     * Busca o time que participa do maior número de campeonatos, caso exista mais um com a maior
     * quantidade, todos os encontrada com essa quantidade são retornados.
     * @return um array com os times.
     */
    public String[] buscaTimesComMaisCampeonatos() {
        int contador = 0;
        String[] times = new String[this.times.size()];
        for (Time t : this.times.values()) {
            if (t.getQtdeCampeonatos() == verificaMaiorQtdeDeCampeonatos()) {
                times[contador++] = t + " - " + t.getQtdeCampeonatos() + " campeonatos";
            }
        }
        return times;
    }

    /**
     * Verifica qual a maior quantidade de campeonatos que um time participa.
     * @return um inteiro com a quantidade de campeonatos.
     */
    private int verificaMaiorQtdeDeCampeonatos() {
        int maior = 0;
        for (Time t : this.times.values()) {
            if (t.getQtdeCampeonatos() > maior) {
                maior = t.getQtdeCampeonatos(); }
        }
        return maior;
    }

    /**
     * Busca todos os times que não participam de campeonatos.
     * @return um arrray com os times.
     */
    public String[] buscaTimesSemCampeonato() {
        int contador = 0;
        String[] times = new String[this.times.size()];
        StringBuilder sb = new StringBuilder();
        for (Time t : this.times.values()) {
            if (t.getQtdeCampeonatos() == 0) {
                times[contador++] = t.toString();
            }

        }
        return times;
    }

    /**
     * Busca a quantidade de vezes que cada time aparece na 1ª colocação de cada aposta.
     *
     * @return um array com os times.
     */
    public String[] buscaPopularidadeDosTimesEmApostas() {
        int contador = 0;
        String[] times = new String[this.times.size()];
        for (Time t : this.times.values()) {
            times[contador++] = t.getNome() + " / " + t.getPopularidadeEmApostas();
        }
        return times;
    }


    /**
     * Lança uma exceção caso o time não exista no sistema.
     * @param codigoTime código do time.
     */
    private void verificaSeTimeExiste(String codigoTime) {
        if (!this.times.containsKey(codigoTime)) { throw new IllegalArgumentException("O TIME NÃO EXISTE!"); }
    }

    /**
     * Lança uma exceção caso o campeonato não exista no sistema.
     * @param nomeCampeonato nome do Campeonato.
     */
    private void verificaSeCampeonatoExiste(String nomeCampeonato) {
        if (!this.campeonatos.containsKey(nomeCampeonato)) { throw new IllegalArgumentException("O CAMPEONATO NÃO EXISTE!"); }
    }

}
