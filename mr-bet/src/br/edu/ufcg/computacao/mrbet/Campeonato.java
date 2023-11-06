package br.edu.ufcg.computacao.mrbet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representação de um campeonato no sistema. Todo campeonato possui um nome e é identificado unicamente por ele.
 *
 * @author Bruno Osório
 */
public class Campeonato {

    private int limiteDeTimes;
    private String nome;
    private String nomeLower;
    private Set<Time> times;

    /**
     * Constói um Campeonato a partir de seu nome e o limite de times.
     * @param nome nome do campeonato.
     * @param qtdeTimes limite de times no campeonato.
     */
    public Campeonato(String nome, int qtdeTimes) {
        this.nome = nome;
        this.nomeLower = nome.toLowerCase();
        this.limiteDeTimes = qtdeTimes;
        this.times = new HashSet<>();
    }

    public String getNome() { return this.nome; }
    public int getLimiteDeTimes() { return this.limiteDeTimes; }

    /**
     * Adiciona um novo time ao campeonato.
     * @param time a ser adicionado.
     *
     * @return true caso o time seja novo, false caso o time já esteja no campeonato.
     */
    public boolean adicionaTime(Time time) {
        return this.times.add(time);
    }

    /**
     * Retorna a quantidade de times que estão participanto do campeoanto.
     * @return um inteiro que representa a quantidade.
     */
    public int qtdeDeTimesNoCampeonato() {
        return this.times.size();
    }

    /**
     * Verifica ainda há vagas no campeonato para um novo time.
     * @return true caso ainda tenha vagas.
     */
    public boolean verificaLimiteDeTimes() { return this.times.size() < this.limiteDeTimes; }

    /**
     * Verifica se a colocao de um time na aposta está dentro do limite do campeonato.
     *
     * @param colocacao colocacao de uma aposta.
     * @return true caso esteja dentro do limite.
     */
    public boolean verificaColocacao(int colocacao) { return colocacao <= this.limiteDeTimes; }

    /**
     * Verifica se um time existe no campeonato.
     * @param time time a ser verificado.
     * @return true caso o time esteja no campeonato.
     */
    public boolean verificaTimeNoCampeonato(Time time) { return this.times.contains(time); }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return Objects.equals(nomeLower, that.nomeLower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeLower);
    }

    /**
     * Representação textual de um campeonato no formato:
     * NOME - PARTICIPANTES / LIMITE
     * @return a representação em String de um campeonato.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("* ").append(this.nome).append(" - ").append(this.times.size()).append("/").append(this.limiteDeTimes);
        return sb.toString();
    }
}
