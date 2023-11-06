package br.edu.ufcg.computacao.mrbet;

import java.util.Objects;

/**
 * Representação de uma aposta.
 * Toda aposta possui um campeonato, um valor, um time e sua colocação.
 *
 * @author Bruno Osório
 */
public class Aposta {
    private Time time;
    private Campeonato campeonato;
    private double valor;
    private int colocacao;

    /**
     * Constrói uma aposta a partir do time e sua colocação, campeonato e valor da posta.
     *
     * @param time time escolhido na aposta.
     * @param campeonato campeonato escolhido na aposta.
     * @param colocacao coloção do time na aposta.
     * @param valor valor da aposta.
     */
    public Aposta(Time time, Campeonato campeonato, int colocacao, double valor) {
        this.time = time;
        this.campeonato = campeonato;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aposta aposta = (Aposta) o;
        return Double.compare(valor, aposta.valor) == 0 && colocacao == aposta.colocacao && Objects.equals(time, aposta.time) && Objects.equals(campeonato, aposta.campeonato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, campeonato, valor, colocacao);
    }

    /**
     * Representação textual de uma aposta no formato:
     * TIME
     * CAMPEONATO
     * COLOCAÇÃO_DO_TIME / PARTICIPANTES_DO_CAMPEONATO
     * VALOR_DA_APOSTA
     *
     * @return a representação em String de uma aposta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.time.getCodigo()).append(" ").append(this.time.getNome()).append(" / ").append(this.time.getMascote());
        sb.append("\n").append(this.campeonato.getNome());
        sb.append("\n").append(this.colocacao).append("/").append(campeonato.qtdeDeTimesNoCampeonato());
        sb.append("\n").append("R$ ").append(this.valor);
        return sb.toString();
    }



}
