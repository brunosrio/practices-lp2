package br.edu.ufcg.computacao.mrbet;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representação de um time. Todo time precisa ter uma código e é identificado unicamente por este código.
 *
 * @author Bruno Osório
 */
public class Time {
    private String codigo;
    private String nome;
    private String mascote;
    private List<Campeonato> campeonatos;
    private int popularidadeEmApostas;

    /**
     * Constrói um time a partir de seu código, nome e mascote.
     *
     * @param codigo código do time.
     * @param nome nome do time.
     * @param mascote mascote do time.
     */
    public Time(String codigo, String nome, String mascote) {
        this.codigo = codigo;
        this.nome = nome;
        this.mascote = mascote;
        this.campeonatos = new ArrayList<>();
        this.popularidadeEmApostas = 0;
    }


    public String getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMascote() {
        return this.mascote;
    }

    public List<Campeonato> getCampeonatos() {
        return this.campeonatos;
    }

    /**
     * Retorna a quantidade de times que um campeonato participa.
     *
     * @return um inteiro com a quantidade.
     */
    public int getQtdeCampeonatos() {
        return this.campeonatos.size();
    }

    /**
     * Retorna a quantidade de vezes que o time apareceu em primeiro lugar em uma aposta.
     * @return um inteiro com a quantidade.
     */
    public int getPopularidadeEmApostas() {
        return this.popularidadeEmApostas;
    }

    /**
     * Aumenta em um o número de vezes que o time apareceu em primeiro lugar em uma aposta.
     */
    public void aumentaPopularidade() {
        this.popularidadeEmApostas++;
    }

    /**
     * Adiciona um novo campeonato que o time está participando.
     *
     * @param campeonato novo campeonato a ser adicionado.
     */
    public void adicionaCampeonato(Campeonato campeonato) {
        this.campeonatos.add(campeonato);
    }

    /**
     * Representação textual de um time no formato:
     * CODIGO NOME / MASCOTE
     *
     * @return a representação em String de um time.
     */
    @Override
    public String toString() {
        return this.codigo + " " + this.nome + " / " + this.mascote;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(codigo, time.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
