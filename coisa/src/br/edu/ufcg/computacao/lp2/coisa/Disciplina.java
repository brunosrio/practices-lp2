package br.edu.ufcg.computacao.lp2.coisa;

import java.util.Arrays;

/**
 * Representação de uma disciplina cursada pelo aluno.
 * Toda disciplina tem um nome, notas, média e horas estudadas do aluno.
 * O aluno é aprovado se tiver uma média maior ou igual a 7.
 *
 * @author Bruno Osório
 **/
public class Disciplina {
    private String nomeDisciplina;
    private int horasEstudo = 0;
    private double[] notas;
    private int[] pesos;
    private double media = 0.0;
    private int numeroDeNotas = 0;

    /**
     * Constrói uma disciplina a partir de seu nome.
     * Por padrão a disciplina possui 4 notas.
     *
     * @param nomeDisciplina nome da disciplina informado.
     * */
    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.notas = new double[4];
    }

    /**
     * Constrói uma disciplina a partir de seu nome e número de notas.
     *
     * @param nomeDisciplina nome da disciplina informado.
     * @param numeroDeNotas número de notas da disciplina.
     * */
    public Disciplina(String nomeDisciplina, int numeroDeNotas) {
        this.nomeDisciplina = nomeDisciplina;
        this.numeroDeNotas = numeroDeNotas;
        this.notas = new double[numeroDeNotas];
    }

    /**
     * Constrói uma disciplina a partir de seu nome, número de notas e pesos para cada nota.
     *
     * @param nomeDisciplina nome da disciplina informado.
     * @param numeroDeNotas número de notas da disciplina.
     * @param pesos array de pesos com o peso de cada nota.
     * */
    public Disciplina(String nomeDisciplina, int numeroDeNotas, int[] pesos) {
        this.nomeDisciplina =  nomeDisciplina;
        this.numeroDeNotas = numeroDeNotas;
        this.pesos = pesos;
        this.notas = new double[numeroDeNotas];
    }

    /**
     * Calcula a média do aluno.
     * Se os pesos das notas forem informados, é calculado a média a ponderada das notas.
     * Caso os pesos não forem informados, é calculado a média aritmética das notas.
     *
     * @return a média das notas
     * */
    public double calculaMedia() {
        double somaNotas = 0.0, media = 0.0;
        if(this.pesos != null) {
            double somaPesos = 0.0;
            for (int i = 0; i < numeroDeNotas; i++) {
                somaNotas += this.notas[i] * this.pesos[i];
                somaPesos += this.pesos[i];
            }
            media = somaNotas / somaPesos;
        } else {
            for (double n : this.notas) {
                somaNotas += n;
            }
            media = somaNotas / this.notas.length;
        }
        this.media = media;
        return media;
    }

    /**
     * Cadastra mais horas de estudo dedicadas para a disciplina pelo aluno.
     * Caso não haja cadastro, o numero de horas é 0.
     *
     * @param horas número de horas dedicadas pelo aluno.
     **/
    public void cadastraHoras(int horas) {
        this.horasEstudo += horas;
    }

    /**
     * Cadastra uma das notas do aluno no array de notas.
     *
     * @param nota representa qual notá será cadastrada.
     * @param valorNota o valor da nota a ser cadastrada.
     * */
    public void cadastraNota(int nota, double valorNota) {
        this.notas[nota - 1] = valorNota;
    }

    /**
     * Calcula a média do aluno e retorna se ele foi aprovado ou não na disciplina.
     *
     * @return true caso seja aprovado ou false caso não.
     * */
    public boolean aprovado() {
        return calculaMedia() >= 7.0;
    }

    /**
     * Retorna a String que representa a disciplina. A representção segue o formato:
     * "NOME_DA_DISCIPLINA HORAS_DE_ESTUDO MEDIA NOTAS ".
     *
     * @return a representação em String de uma disciplina.
     * */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append(this.nomeDisciplina).append(" ").append(this.horasEstudo);
        out.append(" ").append(this.media).append(" ").append(Arrays.toString(this.notas));

        return out.toString();
    }

}
